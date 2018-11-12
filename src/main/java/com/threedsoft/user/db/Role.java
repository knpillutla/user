package com.threedsoft.user.db;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Role  implements Serializable{
	Long id;

String busName;

	Integer locnNbr;
	
	String roleName;

	String isActive;

	String source;

	String transactionName;

	String refField1;

	String refField2;

	String hostName;

    LocalDateTime createdDttm;
	
	LocalDateTime updatedDttm;
		String createdBy;

	String updatedBy;

	Integer version; 	
}
