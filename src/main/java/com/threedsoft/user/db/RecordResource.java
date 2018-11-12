package com.threedsoft.user.db;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
public class RecordResource {
	Long id;

	Long screenId;
	String recordName;
	String recordDisplayName;
	String searchUrl;
	String listUrl;
	String getRecordUrl;
	String addRecordUrl;
	String updateRecordUrl;
	String deleteRecordUrl;
	List<FieldResource> fieldList;
	List<String> searchFieldList;
	List<String> listDisplayFieldList;
	List<String> displayRecordFieldList;
	List<String> addRecordFieldList;
	List<String> updateRecordFieldList;
}
