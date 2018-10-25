package com.threedsoft.user.endpoint.listener;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import com.threedsoft.user.dto.converter.UserDTOConverter;
import com.threedsoft.user.service.UserService;
import com.threedsoft.user.streams.UserStreams;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class UserListener {
	@Autowired
	UserService userService;

	@Autowired
	UserDTOConverter userDTOConverter;

	@StreamListener(target=UserStreams.USER_INPUT, condition = "headers['eventName']=='UserSignedUpEvent'")
	public void handleUserSignedUpEvent(String userId) {
		log.info("Received UserSignedUpEvent, Allocation of User Started: {}" + ": at :" + LocalDateTime.now(), userId.toString());
		long startTime = System.currentTimeMillis();
		long endTime = System.currentTimeMillis();
		log.info("Completed UserSignedUpEvent for UserSignedUpEvent : " + userId + ": at :"
				+ LocalDateTime.now() + " : total time:" + (endTime - startTime) / 1000.00 + " secs");
	}
}
