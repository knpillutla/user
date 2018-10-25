package com.threedsoft.user.service;

import java.util.List;

import com.threedsoft.user.dto.requests.UserCreationRequestDTO;
import com.threedsoft.user.dto.requests.UserLoginInRequestDTO;
import com.threedsoft.user.dto.responses.UserResourceDTO;
import com.threedsoft.user.exception.UserException;

public interface UserService {
	UserResourceDTO createUser(UserCreationRequestDTO userCreationReq) throws UserException;
	UserResourceDTO updateUser(UserCreationRequestDTO userUpdateReq) throws UserException;
	UserResourceDTO login(UserLoginInRequestDTO userLoginReq) throws UserException;
	UserResourceDTO findById(Long id) throws UserException;
}