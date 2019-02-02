package com.threedsoft.user.menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.threedsoft.user.dto.responses.ActionResource;
import com.threedsoft.user.dto.responses.DataResource;
import com.threedsoft.user.dto.responses.FieldResource;
import com.threedsoft.user.dto.responses.ScreenResource;
import com.threedsoft.user.dto.responses.SearchFieldResource;
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
		fieldList.add(FieldResource.createPrimaryField("id", "pick id", "int", "10"));
		fieldList.add(FieldResource.createPrimaryField("busName","Bus Name", "string", "25"));
		fieldList.add(FieldResource.createPrimaryField("locnNbr", "Locn Nbr", "int", "10"));
		fieldList.add(FieldResource.createPrimaryField("batchNbr","Batch Nbr", "string", "25"));
		fieldList.add(FieldResource.createField("company","Company", "string", "15"));
		fieldList.add(FieldResource.createField("division","Division", "string", "10"));
		fieldList.add(FieldResource.createField("busUnit","Bus Unit", "string", "2"));
		fieldList.add(FieldResource.createField("locnBrcd", "Locn Brcd","string", "20"));
		fieldList.add(FieldResource.createField("itemBrcd", "Item Brcd","string", "20"));
		fieldList.add(FieldResource.createField("qty", "Qty", "int", "2"));
		fieldList.add(FieldResource.createField("qtyPicked", "Picked Qty", "int", "2"));
		fieldList.add(FieldResource.createDropDownField("status", "Pick Status", "string", "10","None,Released,Assigned,Picked","None"));
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
		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("status"));
		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("qty"));
		editRecordFieldList.add(ViewEditFieldResource.createMandatoryField("qtyPicked"));
		editRecordFieldList.add(ViewEditFieldResource.createField("locked"));
		
		List<ViewEditFieldResource> viewRecordFieldList = new ArrayList();
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("id"));
		viewRecordFieldList.add(ViewEditFieldResource.createHiddenField("busName"));
		viewRecordFieldList.add(ViewEditFieldResource.createHiddenField("locnNbr"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("company"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("division"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("locnBrcd"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("itemBrcd"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("status"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("qty"));
		viewRecordFieldList.add(ViewEditFieldResource.createMandatoryField("qtyPicked"));
		viewRecordFieldList.add(ViewEditFieldResource.createField("locked"));
		
		List<SearchFieldResource> searchFieldList = new ArrayList();
		searchFieldList.add(SearchFieldResource.createHiddenFieldWithDefaultValue("busName",busName));
		searchFieldList.add(SearchFieldResource.createHiddenFieldWithDefaultValue("locnNbr",locnNbr.toString()));
		searchFieldList.add(SearchFieldResource.createSearchField("locnBrcd"));
		searchFieldList.add(SearchFieldResource.createSearchField("itemBrcd"));
		searchFieldList.add(SearchFieldResource.createSearchField("status"));

		pickDataResource.setFieldList(fieldList);
		pickDataResource.setViewResourceFieldList(viewRecordFieldList);
		pickDataResource.setSearchFieldList(searchFieldList);
		pickDataResource.setListFields("id,batchNbr,locnBrcd,itemBrcd,status,qty,qtyPicked,locked");
		pickDataResource.setEditResourceFieldList(editRecordFieldList);
		pickDataResource.setRecordActionList(Arrays.asList(pickConfirmPickingAction));
		screen.setDataResource(pickDataResource);
		return screen;
	}

}
