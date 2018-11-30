package com.threedsoft.user.endpoint.rest;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.threedsoft.user.dto.requests.UserCreationRequestDTO;
import com.threedsoft.user.dto.requests.UserLoginInRequestDTO;
import com.threedsoft.user.dto.requests.UserUpdateRequestDTO;
import com.threedsoft.user.dto.responses.UserResourceDTO;
import com.threedsoft.user.exception.UserException;
import com.threedsoft.user.service.UserService;
import com.threedsoft.util.dto.ErrorResourceDTO;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
@Controller
@RequestMapping("/users/v1")
@Api(value="User Service", description="Operations pertaining to User")
@RefreshScope
@Slf4j
public class UserRestEndPoint {

    @Autowired
    UserService userService;
	
    @Value("${wms.service.health.msg: User Service - Config Server is not working..please check}")
    private String healthMsg;
    
    @Value("${wms.service.ready.msg: User Service - Not ready yet}")
    private String readyMsg;

	@GetMapping("/ready")
	public ResponseEntity ready() throws UserException {
		return ResponseEntity.ok(readyMsg);
	}
	
	@GetMapping("/health")
	public ResponseEntity health() throws UserException {
		return ResponseEntity.ok(healthMsg);
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity getById(@PathVariable("id") Long id) throws IOException {
		try {
			return ResponseEntity.ok(userService.findById(id));
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new ErrorResourceDTO(HttpStatus.NOT_FOUND.value(), "Error occured while getting the user:" + e.getMessage()));
		}
	}

	@PostMapping("/user")
	public ResponseEntity createUser(@RequestBody UserCreationRequestDTO userCreationReq) throws IOException {
		long startTime = System.currentTimeMillis();
		log.info("Received User Create request for : " + userCreationReq.toString() + ": at :" + LocalDateTime.now());
		ResponseEntity resEntity = null;
		try {
			resEntity = ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(userService.createUser(userCreationReq));
		} catch (UserException ex) {
			log.error("CreateUser Error:", ex.getEvent(), ex);
			resEntity = ResponseEntity.badRequest().body(new ErrorResourceDTO(HttpStatus.BAD_REQUEST.value(), "User Creation Error:" + ex.getMessage()));
		}
		long endTime = System.currentTimeMillis();
		log.info("Completed User Create request for : " + userCreationReq.toString() + ": at :" + LocalDateTime.now() + " : total time:" + (endTime-startTime)/1000.00 + " secs");
		return resEntity;
	}	

	@PostMapping("/user/signin")
	public ResponseEntity userSignIn(@RequestBody UserLoginInRequestDTO userLoginReq) throws IOException {
		UserResourceDTO userResourceDTO = null;
		try {
			log.info("Received signin request:" + userLoginReq);
			userResourceDTO = userService.login(userLoginReq);
			log.info("Completed signin request:" + userLoginReq);
			return ResponseEntity.ok(userResourceDTO);
		} catch (UserException e) {
			log.error("Error:", e);
			return ResponseEntity.badRequest().body(new ErrorResourceDTO(HttpStatus.UNAUTHORIZED.value(), e.getMessage()));
		}
	}

	@PostMapping("/user/id/{id}")
	public ResponseEntity updateUser(@RequestBody UserUpdateRequestDTO userUpdateReq) throws IOException {
		UserResourceDTO userResourceDTO = null;
		try {
			log.info("Received update request:" + userUpdateReq);
			userResourceDTO = userService.updateUser(userUpdateReq);
			log.info("Completed update request:" + userUpdateReq);
			return ResponseEntity.ok(userResourceDTO);
		} catch (UserException e) {
			log.error("Error:", e);
			return ResponseEntity.badRequest().body(new ErrorResourceDTO(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
		}
	}
}
