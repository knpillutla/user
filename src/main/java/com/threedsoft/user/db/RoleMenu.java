package com.threedsoft.user.db;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

public class RoleMenu  implements Serializable{
	Long id;

	Long roleId;
	
	Long menuId;

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
