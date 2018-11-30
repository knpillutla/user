package com.threedsoft.user.menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.threedsoft.user.dto.responses.ActionResource;
import com.threedsoft.user.dto.responses.DataResource;
import com.threedsoft.user.dto.responses.FieldResource;
import com.threedsoft.user.dto.responses.ScreenResource;
import com.threedsoft.user.dto.responses.ViewEditFieldResource;
import com.threedsoft.user.util.UserConstants;

public class ConfigurtionScreen {
	public static ScreenResource createPickingScreen(String busName, Integer locnNbr) {
		ScreenResource screen = new ScreenResource("PickingMaintenance", "Picking Maintenance", "Picking Maintenance",
				"R", UserConstants.CONFIG_SCREEN, null);
		String listRecordUrl = "https://the3dsoft.com/api/v1/configs" + busName + "/" + locnNbr;
		String updateRecordUrl = "https://the3dsoft.com/picking/v1/" + busName + "/" + locnNbr;
		
		DataResource pickDataResource = new DataResource("systemConfig", "System Configuration", "", listRecordUrl, 
				"", updateRecordUrl, "");

		List<FieldResource> fieldList = new ArrayList();
		fieldList.add(FieldResource.createHiddenField("id", "config id", "int", "10"));
		fieldList.add(FieldResource.createHiddenField("wbcId", "bus config id", "int", "10"));
		fieldList.add(FieldResource.createHiddenField("wblcId", "bus locn config id", "int", "10"));
		fieldList.add(FieldResource.createField("busName","Bus Name", "string", "25"));
		fieldList.add(FieldResource.createField("locnNbr","Locn Nbr", "int", "10"));
		fieldList.add(FieldResource.createField("moduleName","Module", "string", "25"));
		fieldList.add(FieldResource.createField("key","name", "string", "15"));
		fieldList.add(FieldResource.createField("value","Value", "string", "10"));
		fieldList.add(FieldResource.createField("busOverride", "Business Override","string", "20"));
		fieldList.add(FieldResource.createField("busLocnOverride", "Location Override","string", "20"));
		fieldList.add(FieldResource.createField("modified","Modified", "string", "1"));
//		fieldList.add(FieldResource.createDropDownField("locked", "Locked", "string", "1","Y,N","N"));
//		fieldList.add(FieldResource.createField("userId", "userId", "int", "2"));

		List<ViewEditFieldResource> editRecordFieldList = new ArrayList();
		editRecordFieldList.add(ViewEditFieldResource.createHiddenField("id"));
		editRecordFieldList.add(ViewEditFieldResource.createHiddenField("wbcId"));
		editRecordFieldList.add(ViewEditFieldResource.createHiddenField("wblcId"));
		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("busName"));
		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("locnNbr"));
		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("module"));
		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("key"));
		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("value"));
		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("busOverride"));
		editRecordFieldList.add(ViewEditFieldResource.createTextField("busLocnOverride"));
		

		pickDataResource.setFieldList(fieldList);
//		pickDataResource.setSearchFields("busName,locnNbr,locnBrcd,itemBrcd");
		pickDataResource.setListFields("module,key,value,busOverride,busLocnOverride");
		pickDataResource.setEditResourceFieldList(editRecordFieldList);
//		pickDataResource.setRecordActionList(Arrays.asList(pickConfirmPickingAction));
		screen.setDataResource(pickDataResource);
		return screen;
	}

}
