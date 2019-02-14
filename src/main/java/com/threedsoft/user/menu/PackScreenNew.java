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

public class PackScreenNew {
	public static ScreenResource createPackScreen(String busName, String facilityNbr) {
		ScreenResource screen = new ScreenResource("NewPackingMaintenance", "New Packing Maintenance",
				"New Packing Maintenance", "R", UserConstants.MAINTENANCE_SCREEN, null);
		String searchUrl = "https://the3dsoft.com/pack/api/packs";
		// even if we donot specify size=20, default size is 20.no page is specified
		// here
		String listUrl = "https://the3dsoft.com/pack/api/packs?busName.equals=" + busName + "&facilityNbr.equals="
				+ facilityNbr + "&size=100";
		String addRecordUrl = "";// https://the3dsoft.com/pack/api/packs";
		String updateRecordUrl = "";// "https://the3dsoft.com/pack/api/packs";
		String deleteRecordUrl = "";// "https://the3dsoft.com/pack/api/packs/{id}";
		String packConfirmActionUrl = "https://the3dsoft.com/pack/api/packs/confirmPack";

		ActionResource packConfirmPickingAction = new ActionResource();
		packConfirmPickingAction.setActionName("packconfirm");
		packConfirmPickingAction.setActionDisplayName("Pack Confirm");
		packConfirmPickingAction.setActionUrl(packConfirmActionUrl);
		/*
		 * List<RequestFieldResource> requestFieldList = new ArrayList();
		 * requestFieldList.add(new RequestFieldResource("busName","","N"));
		 * requestFieldList.add(new RequestFieldResource("facilityNbr","","N"));
		 * requestFieldList.add(new RequestFieldResource("id","","N"));
		 * requestFieldList.add(new RequestFieldResource("orderId","","N"));
		 * requestFieldList.add(new RequestFieldResource("batchNbr","","N"));
		 * requestFieldList.add(new
		 * RequestFieldResource("qtyPacked","Qty Packed","","Y"));
		 * requestFieldList.add(new
		 * RequestFieldResource("fromContainer","From Container","","Y"));
		 * requestFieldList.add(new
		 * RequestFieldResource("toContainer","To Container","","Y"));
		 */ packConfirmPickingAction.setRequestFields("id,busName,facilityNbr,packedQty,toContainer,updatedBy");
		DataResource packDataResource = new DataResource("Packs", "Packs", searchUrl, listUrl, addRecordUrl,
				updateRecordUrl, deleteRecordUrl);

		List<FieldResource> fieldList = new ArrayList();
		fieldList.add(FieldResource.createPrimaryField("id", "pack id", "int", "10"));
		fieldList.add(FieldResource.createPrimaryField("busName", "Bus Name", "string", "25"));
		fieldList.add(FieldResource.createPrimaryField("facilityNbr", "Locn Nbr", "int", "10"));
		fieldList.add(FieldResource.createField("batchNbr", "Batch Nbr", "string", "15"));
		fieldList.add(FieldResource.createField("orderNbr", "Order Nbr", "string", "15"));
		fieldList.add(FieldResource.createField("orderId", "Order Id", "string", "15"));
		fieldList.add(FieldResource.createField("company", "Company", "string", "15"));
		fieldList.add(FieldResource.createField("division", "Division", "string", "10"));
		fieldList.add(FieldResource.createField("itemBrcd", "Item Brcd", "string", "20"));
		fieldList.add(FieldResource.createField("qty", "Qty", "int", "2"));
		fieldList.add(FieldResource.createField("packedQty", "Packed Qty", "int", "2"));
		fieldList.add(FieldResource.createField("fromContainer", "From Container", "string", "20"));
		fieldList.add(FieldResource.createField("toContainer", "To Container", "string", "20"));
		fieldList.add(FieldResource.createField("updatedBy", "User Id", "String", "20"));
		fieldList.add(FieldResource.createDropDownField("locked", "Locked", "string", "1", "Y,N", "N"));
		fieldList.add(FieldResource.createDropDownField("statCode", "Pack Status", "string", "10",
				"100,200,300,400,500", "None"));

		List<ViewEditFieldResource> viewResourceFieldList = new ArrayList();
		viewResourceFieldList.add(ViewEditFieldResource.createDisabledField("id"));
		viewResourceFieldList.add(ViewEditFieldResource.createHiddenField("busName"));
		viewResourceFieldList.add(ViewEditFieldResource.createHiddenField("facilityNbr"));
		viewResourceFieldList.add(ViewEditFieldResource.createDisabledField("company"));
		viewResourceFieldList.add(ViewEditFieldResource.createDisabledField("division"));
		viewResourceFieldList.add(ViewEditFieldResource.createDisabledField("batchNbr"));
		viewResourceFieldList.add(ViewEditFieldResource.createDisabledField("orderNbr"));
		viewResourceFieldList.add(ViewEditFieldResource.createDisabledField("orderId"));
		viewResourceFieldList.add(ViewEditFieldResource.createDisabledField("itemBrcd"));
		viewResourceFieldList.add(ViewEditFieldResource.createDisabledField("qty"));
		viewResourceFieldList.add(ViewEditFieldResource.createDisabledField("fromContainer"));
		viewResourceFieldList.add(ViewEditFieldResource.createDisabledField("packedQty"));
		viewResourceFieldList.add(ViewEditFieldResource.createDisabledField("toContainer"));
		viewResourceFieldList.add(ViewEditFieldResource.createDisabledField("statCode"));
		viewResourceFieldList.add(ViewEditFieldResource.createDisabledField("locked"));

		List<ViewEditFieldResource> editRecordFieldList = new ArrayList();
		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("id"));
		editRecordFieldList.add(ViewEditFieldResource.createHiddenField("busName"));
		editRecordFieldList.add(ViewEditFieldResource.createHiddenField("facilityNbr"));
		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("company"));
		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("division"));
		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("batchNbr"));
		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("orderNbr"));
		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("orderId"));
		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("itemBrcd"));
		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("qty"));
		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("fromContainer"));
		editRecordFieldList.add(ViewEditFieldResource.createMandatoryField("packedQty"));
		editRecordFieldList.add(ViewEditFieldResource.createMandatoryField("toContainer"));
		editRecordFieldList.add(ViewEditFieldResource.createMandatoryField("locked"));
		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("statCode"));
		List<SearchFieldResource> searchFieldList = new ArrayList();
		searchFieldList.add(SearchFieldResource.createHiddenFieldWithDefaultValue("busName", ".equals", busName));
		searchFieldList.add(SearchFieldResource.createHiddenFieldWithDefaultValue("facilityNbr", ".equals",
				facilityNbr.toString()));
		searchFieldList.add(SearchFieldResource.createSearchField("locnBrcd", ".contains"));
		searchFieldList.add(SearchFieldResource.createSearchField("itemBrcd", ".contains"));
		searchFieldList.add(SearchFieldResource.createSearchField("statCode", ".equals"));

		packDataResource.setFieldList(fieldList);
		packDataResource.setSearchFieldList(searchFieldList);
		packDataResource.setViewResourceFieldList(viewResourceFieldList);
		packDataResource.setEditResourceFieldList(editRecordFieldList);
		packDataResource.setListFields(
				"id, batchNbr,itemBrcd,qty,packedQty,fromContainer,toContainer,statCode,locked, orderNbr,updatedBy");
		packDataResource.setRecordActionList(Arrays.asList(packConfirmPickingAction));
		screen.setDataResource(packDataResource);
		return screen;
	}
}
