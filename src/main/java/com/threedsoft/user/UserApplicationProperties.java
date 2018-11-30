package com.threedsoft.user;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

public class UserApplicationProperties {
	@Value("${homestore: N}")
	public static String isHomeStore;

}
