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

public class PickScreenNew {
	public static ScreenResource createPickScreen(String busName, String facilityNbr) {
		ScreenResource screen = new ScreenResource("NewPickScreen", "New Pick Screen", "New Pick Screen", "RW",
				UserConstants.MAINTENANCE_SCREEN, null);
		String searchUrl = "https://the3dsoft.com/pick/api/picks";
		// even if we donot specify size=20, default size is 20.no page is specified
		// here
		String listUrl = "https://the3dsoft.com/pick/api/picks?busName.equals=" + busName + "&facilityNbr.equals="
				+ facilityNbr + "&size=100";
		String addRecordUrl = "";// https://the3dsoft.com/pick/api/picks";
		String updateRecordUrl = "";// "https://the3dsoft.com/pick/api/picks";
		String deleteRecordUrl = "";// "https://the3dsoft.com/pick/api/picks/{id}";
		String pickConfirmActionUrl = "https://the3dsoft.com/pick/api/picks/confirm";

		ActionResource pickConfirmPickingAction = new ActionResource();
		pickConfirmPickingAction.setActionName("pickconfirm");
		pickConfirmPickingAction.setActionDisplayName("Pick Confirm");
		pickConfirmPickingAction.setActionUrl(pickConfirmActionUrl);
		pickConfirmPickingAction.setRequestFields("id,busName,facilityNbr,batchNbr,pickedQty,toContainer,updatedBy");

		DataResource pickDataResource = new DataResource("Picks", "Picks", searchUrl, listUrl, addRecordUrl,
				updateRecordUrl, deleteRecordUrl);

		List<FieldResource> fieldList = new ArrayList();
		fieldList.add(FieldResource.createPrimaryField("id", "pick id", "int", "10"));
		fieldList.add(FieldResource.createPrimaryField("busName", "Bus Name", "string", "25"));
		fieldList.add(FieldResource.createPrimaryField("facilityNbr", "Facility Nbr", "int", "10"));
		fieldList.add(FieldResource.createPrimaryField("orderNbr", "Order Nbr", "string", "25"));
		fieldList.add(FieldResource.createPrimaryField("batchNbr", "Batch Nbr", "string", "25"));
		fieldList.add(FieldResource.createField("company", "Company", "string", "15"));
		fieldList.add(FieldResource.createField("division", "Division", "string", "10"));
		fieldList.add(FieldResource.createField("locnBrcd", "Locn Brcd", "string", "20"));
		fieldList.add(FieldResource.createField("itemBrcd", "Item Brcd", "string", "20"));
		fieldList.add(FieldResource.createField("qty", "Qty", "int", "2"));
		fieldList.add(FieldResource.createField("pickedQty", "Picked Qty", "int", "2"));
		fieldList.add(FieldResource.createDropDownField("statCode", "Pick Status", "string", "10",
				"100,200,300,400,500", "None"));
		fieldList.add(FieldResource.createDropDownField("locked", "Locked", "string", "1", "Y,N", "N"));
		fieldList.add(FieldResource.createField("updatedBy", "User Id", "String", "20"));

		List<ViewEditFieldResource> editRecordFieldList = new ArrayList();
		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("id"));
		editRecordFieldList.add(ViewEditFieldResource.createHiddenField("busName"));
		editRecordFieldList.add(ViewEditFieldResource.createHiddenField("facilityNbr"));
		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("company"));
		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("division"));
		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("orderNbr"));
		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("locnBrcd"));
		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("itemBrcd"));
		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("statCode"));
		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("qty"));
		editRecordFieldList.add(ViewEditFieldResource.createMandatoryField("pickedQty"));
		editRecordFieldList.add(ViewEditFieldResource.createField("locked"));

		List<ViewEditFieldResource> viewRecordFieldList = new ArrayList();
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("id"));
		viewRecordFieldList.add(ViewEditFieldResource.createHiddenField("busName"));
		viewRecordFieldList.add(ViewEditFieldResource.createHiddenField("facilityNbr"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("company"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("division"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("orderNbr"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("locnBrcd"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("itemBrcd"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("statCode"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("qty"));
		viewRecordFieldList.add(ViewEditFieldResource.createMandatoryField("pickedQty"));
		viewRecordFieldList.add(ViewEditFieldResource.createField("locked"));

		List<SearchFieldResource> searchFieldList = new ArrayList();
		searchFieldList.add(SearchFieldResource.createHiddenFieldWithDefaultValue("busName", ".equals", busName));
		searchFieldList.add(SearchFieldResource.createHiddenFieldWithDefaultValue("facilityNbr", ".equals",
				facilityNbr.toString()));
		searchFieldList.add(SearchFieldResource.createSearchField("locnBrcd", ".contains"));
		searchFieldList.add(SearchFieldResource.createSearchField("itemBrcd", ".contains"));
		searchFieldList.add(SearchFieldResource.createSearchField("statCode", ".equals"));

		pickDataResource.setFieldList(fieldList);
		pickDataResource.setViewResourceFieldList(viewRecordFieldList);
		pickDataResource.setSearchFieldList(searchFieldList);
		pickDataResource.setListFields("id,orderNbr,batchNbr,locnBrcd,itemBrcd,status,qty,pickedQty,statCode,locked");
		pickDataResource.setEditResourceFieldList(editRecordFieldList);
		pickDataResource.setRecordActionList(Arrays.asList(pickConfirmPickingAction));
		screen.setDataResource(pickDataResource);
		return screen;
	}
}
