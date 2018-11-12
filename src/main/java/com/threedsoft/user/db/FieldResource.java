package com.threedsoft.user.db;

public class FieldResource {
	Long id;
	Long screenId;
	Long recordId;
	String fieldName;
	String fieldDisplayName;
	String disableField;
	String hideField;
	String fieldDataType;
	String fieldLength;
	String allowedValues;
	String displayOptions;
	String fieldType;

	public FieldResource(String fieldName, String fieldDisplayName, String disableField, String hideField,
			String fieldDataType, String fieldLength, String allowedValues, String displayOptions) {
		this.fieldName=fieldName;
		this.fieldDisplayName=fieldDisplayName;
		this.disableField=disableField;
		this.hideField=hideField;
		this.fieldDataType=fieldDataType;
		this.fieldLength=fieldLength;
		this.allowedValues=allowedValues;
		this.displayOptions=displayOptions;
		this.fieldType="text";
	}
}
