package com.threedsoft.user.db;

import java.io.Serializable;

public class RoleMenuScreen  implements Serializable{
	Long id;

	Long roleId;
	
	Long menuId;
	
	Long screenId;
	
	String accessLevel; //R, RW
	

	String isActive;

	String source;

	String transactionName;

	String refField1;

	String refField2;

String hostName;

}
