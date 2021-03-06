package com.threedsoft.user.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.threedsoft.user.db.User;
import com.threedsoft.user.db.UserRepository;
import com.threedsoft.user.dto.converter.UserDTOConverter;
import com.threedsoft.user.dto.events.UserCreatedEvent;
import com.threedsoft.user.dto.events.UserCreationFailedEvent;
import com.threedsoft.user.dto.events.UserLoginFailedEvent;
import com.threedsoft.user.dto.requests.UserCreationRequestDTO;
import com.threedsoft.user.dto.requests.UserLoginInRequestDTO;
import com.threedsoft.user.dto.requests.UserMenuTypeUpdateRequestDTO;
import com.threedsoft.user.dto.requests.UserPasswordUpdateRequestDTO;
import com.threedsoft.user.dto.requests.UserThemeUpdateRequestDTO;
import com.threedsoft.user.dto.requests.UserTypeUpdateRequestDTO;
import com.threedsoft.user.dto.requests.UserUpdateRequestDTO;
import com.threedsoft.user.dto.responses.UserResourceDTO;
import com.threedsoft.user.exception.UserException;
import com.threedsoft.user.util.UserConstants;
import com.threedsoft.util.service.EventPublisher;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
	private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	UserRepository userDAO;

	@Autowired
	EventPublisher eventPublisher;

	@Autowired
	UserDTOConverter userDTOConverter;

	public enum UserStatus {
		LOCKED("Locked"), CREATED("Created");
		UserStatus(String userStatus) {
			this.userStatus = userStatus;
		}

		private String userStatus;

		public String getUserStatus() {
			return userStatus;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 */
	@Override
	@Transactional
	public UserResourceDTO createUser(UserCreationRequestDTO userCreationReq) throws UserException {
		UserResourceDTO userResourceDTO = null;
		try {
			if (userCreationReq.getAuthType() == null)
				userCreationReq.setAuthType("");
			User newUser = userDTOConverter.getUserEntity(userCreationReq);
			newUser.setUserStatus(UserStatus.CREATED.getUserStatus());
			User existingUserEntity = userDAO.findByUserName(newUser.getUserName());
			if (existingUserEntity == null) {
				User savedUserObj = userDAO.save(newUser);
				userResourceDTO = userDTOConverter.getUserResourceDTO(savedUserObj);
			} else {
				log.error("UserName already in use. Pls use a different UserName");
				UserCreationFailedEvent event = new UserCreationFailedEvent(userCreationReq,
						UserConstants.USER_SERVICE_NAME, "User Creation Error:" + "UserName in use");
				eventPublisher.publish(event);
				UserException userException = new UserException(event);
				throw userException;
			}
		} catch (Exception ex) {
			log.error("User Creation Error:" + ex.getMessage(), ex);
			UserCreationFailedEvent event = new UserCreationFailedEvent(userCreationReq,
					UserConstants.USER_SERVICE_NAME, "User Creation Error:" + ex.getMessage());
			UserException userException = new UserException(event);
			eventPublisher.publish(event);
			throw userException;

		}
		eventPublisher.publish(new UserCreatedEvent(userResourceDTO, UserConstants.USER_SERVICE_NAME));
		return userResourceDTO;
	}

	@Override
	public UserResourceDTO findById(Long id) throws UserException {
		Optional<User> userEntityOptional = userDAO.findById(id);
		if (!userEntityOptional.isPresent()) {
			log.error("user does not exist. Signup Required");
			UserException userException = new UserException("Id:" + id + " not found");
			throw userException;
		}
		return userDTOConverter.getUserResourceDTO(userEntityOptional.get());
	}

	@Override
	public UserResourceDTO updateUser(UserUpdateRequestDTO userUpdateReq) throws UserException {
		User returnEntity = null;
		Optional<User> userEntityOptional = userDAO.findById(userUpdateReq.getId());
		if (userEntityOptional.isPresent()) {
			User userEntity = userEntityOptional.get();
			// userEntity.setTheme(userUpdateReq.getTheme());
			userEntity.setFirstName(userUpdateReq.getFirstName());
			userEntity.setLastName(userUpdateReq.getLastName());
			userEntity.setMiddleName(userUpdateReq.getMiddleName());
			userEntity.setAddr1(userUpdateReq.getAddr1());
			userEntity.setAddr2(userUpdateReq.getAddr2());
			userEntity.setAddr3(userUpdateReq.getAddr3());
			userEntity.setCity(userUpdateReq.getCity());
			userEntity.setCountry(userUpdateReq.getCountry());
			userEntity.setZipCode(userUpdateReq.getZipCode());
			userEntity.setBusName(userUpdateReq.getBusName());
			userEntity.setDefLocnNbr(userUpdateReq.getDefLocnNbr());
			userEntity.setLocale(userUpdateReq.getLocale());

			returnEntity = userDAO.save(userEntity);
		}
		return userDTOConverter.getUserResourceDTO(returnEntity);
	}

	@Override
	public UserResourceDTO login(UserLoginInRequestDTO userLoginReq) throws UserException {
		log.info("UserServiceImpl::login::Received request:" + userLoginReq.toString());
		User userEntity = userDAO.findByUserName(userLoginReq.getUserName());
		if (userEntity == null) {
			log.error("UserName does not exist. Signup Required");
			UserLoginFailedEvent event = new UserLoginFailedEvent(userLoginReq, UserConstants.USER_SERVICE_NAME,
					"User Login Error:" + "UserName not found");
			eventPublisher.publish(event);
			UserException userException = new UserException(event);
			throw userException;
		}
		if (!userEntity.getAuthType().toLowerCase().equalsIgnoreCase(UserConstants.GOOGLE_AUTH_TYPE.toLowerCase())
				&& !userEntity.getAuthType().toLowerCase()
						.equalsIgnoreCase(UserConstants.FACEBOOK_AUTH_TYPE.toLowerCase())
				&& !userEntity.getAuthToken().equals(userLoginReq.getAuthToken())) {
			log.error("Invalid UserName/Password");
			UserLoginFailedEvent event = new UserLoginFailedEvent(userLoginReq, UserConstants.USER_SERVICE_NAME,
					"User Login Error:" + "Invalid UserName/Password");
			eventPublisher.publish(event);
			UserException userException = new UserException(event);
			throw userException;
		}
		UserResourceDTO userResourceDTO = userDTOConverter.getUserResourceDTO(userEntity);
		// userDTOConverter.addUserMenu(userResourceDTO);
		log.info("User logged in successfully:" + userResourceDTO);
		return userResourceDTO;
	}

	@Override
	public UserResourceDTO updateUserTheme(UserThemeUpdateRequestDTO userThemeUpdateReq) throws Exception {
		User returnEntity = null;
		Optional<User> userEntityOptional = userDAO.findById(userThemeUpdateReq.getId());
		if (userEntityOptional.isPresent()) {
			User userEntity = userEntityOptional.get();
			userEntity.setTheme(userThemeUpdateReq.getTheme());
			returnEntity = userDAO.save(userEntity);
		}
		return userDTOConverter.getUserResourceDTO(returnEntity);
	}

	@Override
	public UserResourceDTO updateUserAuthToken(UserPasswordUpdateRequestDTO userPwdUpdateReq) throws Exception {
		User returnEntity = null;
		Optional<User> userEntityOptional = userDAO.findByIdAndAuthTokenAndAuthType(userPwdUpdateReq.getId(),
				userPwdUpdateReq.getOldPassword(), UserConstants.USER_AUTH_TYPE);
		if (userEntityOptional.isPresent()) {
			User userEntity = userEntityOptional.get();
			userEntity.setAuthToken(userPwdUpdateReq.getNewPassword());
			returnEntity = userDAO.save(userEntity);
		} else {
			throw new Exception("Unable to Change password. Incorrect User/Password/Social Login Combination entered");
		}
		return userDTOConverter.getUserResourceDTO(returnEntity);
	}

	@Override
	public UserResourceDTO updateUserMenuType(UserMenuTypeUpdateRequestDTO userMenuTypeUpdateReq) {
		User returnEntity = null;
		Optional<User> userEntityOptional = userDAO.findById(userMenuTypeUpdateReq.getId());
		if (userEntityOptional.isPresent()) {
			User userEntity = userEntityOptional.get();
			userEntity.setMenuType(userMenuTypeUpdateReq.getMenuType());
			returnEntity = userDAO.save(userEntity);
		}
		return userDTOConverter.getUserResourceDTO(returnEntity);
	}

	@Override
	public UserResourceDTO updateUserType(UserTypeUpdateRequestDTO userTypeUpdateReq) {
		User returnEntity = null;
		Optional<User> userEntityOptional = userDAO.findById(userTypeUpdateReq.getId());
		if (userEntityOptional.isPresent()) {
			User userEntity = userEntityOptional.get();
			userEntity.setUserType(userTypeUpdateReq.getUserType());
			returnEntity = userDAO.save(userEntity);
		}
		return userDTOConverter.getUserResourceDTO(returnEntity);
	}

}
