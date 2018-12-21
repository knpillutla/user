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

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Entity
@Data
@Table(name="USER_MASTER")
@EntityListeners(AuditingEntityListener.class)
@DynamicUpdate
public class User  implements Serializable{
	@Column(name="ID")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Column(name="BUS_NAME")
	String busName;

	@Column(name="DEF_LOCN_NBR")
	Integer defLocnNbr;
	
	@Column(name="USER_NAME")
	String userName;

	@Column(name="AUTH_TYPE")
	String authType;

	@Column(name="AUTH_TOKEN")
	String authToken;

	@Column(name="FIRST_NAME")
	String firstName;

	@Column(name="LAST_NAME")
	String lastName;

	@Column(name="MIDDLE_NAME")
	String middleName;

	@Column(name="ROLE_NAME")
	String roleName;

	@Column(name="ADDR_1")
	String addr1;

	@Column(name="ADDR_2")
	String addr2;

	@Column(name="ADDR_3")
	String addr3;

	@Column(name="CITY")
	String city;

	@Column(name="STATE")
	String State;

	@Column(name="COUNTRY")
	String country;

	@Column(name="THEME")
	String theme;

	@Column(name="LOCALE")
	String locale;

	@Column(name="ZIPCODE")
	String zipCode;

	@Column(name="STATUS")
	String userStatus;

	@Column(name="MENU_TYPE")
	String menuType;

	@Column(name="USER_TYPE")
	String userType;

	@Column(name="SOURCE")
	String source;

	@Column(name="TRANSACTION_NAME")
	String transactionName;

	@Column(name="REF_FIELD_1")
	String refField1;

	@Column(name="REF_FIELD_2")
	String refField2;

	@Column(name="HOST_NAME")
	String hostName;

    @CreatedDate
	@Column(name="CREATED_DTTM", nullable = false, updatable = false)
    LocalDateTime createdDttm;
	
    @Column(name = "UPDATED_DTTM", nullable = false)
    @LastModifiedDate
	LocalDateTime updatedDttm;
	
	@Column(name="CREATED_BY")
	String createdBy;

	@Column(name="UPDATED_BY")
	String updatedBy;

	@Version
 	@Column(name="VERSION")
	Integer version; 	
}
