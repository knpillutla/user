package com.threedsoft.user.util;

import org.springframework.beans.factory.annotation.Value;

public class UserConstants {
	@Value("${spring.application.name}")
	public static String USER_SERVICE_NAME;
	
	public static String GOOGLE_AUTH_TYPE="google";
	public static String FACEBOOK_AUTH_TYPE="facebook";
	public static String USER_AUTH_TYPE="user";

}
