package com.threedsoft.user.service;

import java.util.Optional;

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
import com.threedsoft.user.dto.requests.UserUpdateRequestDTO;
import com.threedsoft.user.dto.responses.UserResourceDTO;
import com.threedsoft.user.exception.UserException;
import com.threedsoft.user.util.UserConstants;
import com.threedsoft.util.service.EventPublisher;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
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
			if(userCreationReq.getAuthType() == null)
				userCreationReq.setAuthType("");
			User newUser = userDTOConverter.getUserEntity(userCreationReq);
			newUser.setUserStatus(UserStatus.CREATED.getUserStatus());
			User existingUserEntity = userDAO.findByUserName(newUser.getUserName());
			if(existingUserEntity == null) {
				User savedUserObj = userDAO.save(newUser);
				userResourceDTO = userDTOConverter.getUserResourceDTO(savedUserObj);
			}else {
				log.error("UserName already in use. Pls use a different UserName");
				UserCreationFailedEvent event = new UserCreationFailedEvent(userCreationReq,UserConstants.USER_SERVICE_NAME,
						"User Creation Error:" + "UserName in use");
				eventPublisher.publish(event);
				UserException userException = new UserException(event);
				throw userException;
			}
		} catch (Exception ex) {
			log.error("User Creation Error:" + ex.getMessage(), ex);
			UserCreationFailedEvent event = new UserCreationFailedEvent(userCreationReq,UserConstants.USER_SERVICE_NAME,
					"User Creation Error:" + ex.getMessage());
			UserException userException = new UserException(event);
			eventPublisher.publish(event);
			throw userException;

		}
		eventPublisher.publish(new UserCreatedEvent(userResourceDTO,UserConstants.USER_SERVICE_NAME));
		return userResourceDTO;
	}

	@Override
	public UserResourceDTO findById(Long id) throws UserException {
		Optional<User> userEntityOptional = userDAO.findById(id);
		if(!userEntityOptional.isPresent()) {
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
		if(userEntityOptional.isPresent()) {
			User userEntity = userEntityOptional.get();
			userEntity.setTheme(userUpdateReq.getTheme());
			returnEntity = userDAO.save(userEntity);
		}
		return userDTOConverter.getUserResourceDTO(returnEntity);
	}

	@Override
	public UserResourceDTO login(UserLoginInRequestDTO userLoginReq) throws UserException {
		log.info("UserServiceImpl::login::Received request:" + userLoginReq.toString());
		User userEntity = userDAO.findByUserName(userLoginReq.getUserName());
		if(userEntity == null) {
			log.error("UserName does not exist. Signup Required");
			UserLoginFailedEvent event = new UserLoginFailedEvent(userLoginReq,UserConstants.USER_SERVICE_NAME,
					"User Login Error:" + "UserName not found");
			eventPublisher.publish(event);
			UserException userException = new UserException(event);
			throw userException;
		}
		if(!userEntity.getAuthType().toLowerCase().equalsIgnoreCase(UserConstants.GOOGLE_AUTH_TYPE.toLowerCase()) &&
				!userEntity.getAuthType().toLowerCase().equalsIgnoreCase(UserConstants.FACEBOOK_AUTH_TYPE.toLowerCase()) &&
					!userEntity.getAuthToken().equals(userLoginReq.getAuthToken())) {
			log.error("Invalid UserName/Password");
			UserLoginFailedEvent event = new UserLoginFailedEvent(userLoginReq,UserConstants.USER_SERVICE_NAME,
					"User Login Error:" + "Invalid UserName/Password");
			eventPublisher.publish(event);
			UserException userException = new UserException(event);
			throw userException;
		}
		log.info("User logged in successfully");
		return userDTOConverter.getUserResourceDTO(userEntity);
	}
	
}
