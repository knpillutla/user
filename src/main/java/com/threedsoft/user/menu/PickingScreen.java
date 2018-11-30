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

public class PickingScreen {
	public static ScreenResource createPickingScreen(String busName, Integer locnNbr) {
		ScreenResource screen = new ScreenResource("PickingMaintenance", "Picking Maintenance", "Picking Maintenance",
				"R", UserConstants.MAINTENANCE_SCREEN, null);
		String searchUrl = "https://the3dsoft.com/picking/v1/" + busName + "/" + locnNbr + "/picks/search";
		String listUrl = "https://the3dsoft.com/picking/v1/" + busName + "/" + locnNbr + "/picks";
		String getRecordUrl = "https://the3dsoft.com/picking/v1/" + busName + "/" + locnNbr + "/picks/{id}";
		String addRecordUrl = "";
		String updateRecordUrl = "https://the3dsoft.com/picking/v1/" + busName + "/" + locnNbr + "/picks/{id}";
		String deleteRecordUrl = "";
		String pickConfirmActionUrl =  "https://the3dsoft.com/picking/v1/" + busName + "/" + locnNbr + "/picks/{id}";
		
		ActionResource pickConfirmPickingAction = new ActionResource();
		pickConfirmPickingAction.setActionName("pickconfirm");
		pickConfirmPickingAction.setActionDisplayName("Pick Confirm");
		pickConfirmPickingAction.setActionUrl(pickConfirmActionUrl);
		pickConfirmPickingAction.setRequestFields("id,busName,locnNbr,batchNbr,qtyPicked,toContainer");


		DataResource pickDataResource = new DataResource("Picks", "Picks", searchUrl, listUrl, 
				addRecordUrl, updateRecordUrl, deleteRecordUrl);

		List<FieldResource> fieldList = new ArrayList();
		fieldList.add(FieldResource.createPrimaryField("id", "pickId","pick id", "int", "10"));
		fieldList.add(FieldResource.createPrimaryField("busName","busName","Bus Name", "string", "25"));
		fieldList.add(FieldResource.createPrimaryField("locnNbr", "locnNbr", "Locn Nbr", "int", "10"));
		fieldList.add(FieldResource.createPrimaryField("batchNbr","batchNbr","Batch Nbr", "string", "25"));
		fieldList.add(FieldResource.createField("company","Company", "string", "15"));
		fieldList.add(FieldResource.createField("division","Division", "string", "10"));
		fieldList.add(FieldResource.createField("locnBrcd", "Locn Brcd","string", "20"));
		fieldList.add(FieldResource.createField("itemBrcd", "Item Brcd","string", "20"));
		fieldList.add(FieldResource.createField("qty", "Qty", "int", "2"));
		fieldList.add(FieldResource.createField("qtyPicked", "Picked Qty", "int", "2"));
		fieldList.add(FieldResource.createDropDownField("locked", "Locked", "string", "1","Y,N","N"));
		fieldList.add(FieldResource.createField("userId", "userId", "int", "2"));

		List<ViewEditFieldResource> editRecordFieldList = new ArrayList();
		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("id"));
		editRecordFieldList.add(ViewEditFieldResource.createHiddenField("busName"));
		editRecordFieldList.add(ViewEditFieldResource.createHiddenField("locnNbr"));
		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("company"));
		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("division"));
		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("locnBrcd"));
		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("itemBrcd"));
		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("qty"));
		editRecordFieldList.add(ViewEditFieldResource.createTextField("qtyPicked"));
		editRecordFieldList.add(ViewEditFieldResource.createTextField("locked"));
		

		pickDataResource.setFieldList(fieldList);
		pickDataResource.setSearchFields("busName,locnNbr,locnBrcd,itemBrcd");
		pickDataResource.setListFields("id,batchNbr,locnBrcd,itemBrcd,qty,qtyPicked,locked");
		pickDataResource.setEditResourceFieldList(editRecordFieldList);
		pickDataResource.setRecordActionList(Arrays.asList(pickConfirmPickingAction));
		screen.setDataResource(pickDataResource);
		return screen;
	}

}
