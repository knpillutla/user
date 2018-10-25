package com.threedsoft.user.exception;

import com.threedsoft.util.dto.events.ExceptionEvent;
import com.threedsoft.util.dto.events.WMSEvent;

import lombok.Data;

@Data
public class UserException extends Exception{
	WMSEvent event = null;
	
	public UserException(ExceptionEvent event) {
		super(event.getErrorMsg());
		this.event = event;
	}

	public UserException(String errorMsg) {
		super(errorMsg);
	}
}
