package com.threedsoft.user.menu;

import java.util.ArrayList;
import java.util.List;

import com.threedsoft.user.dto.responses.AddFieldResource;
import com.threedsoft.user.dto.responses.DataResource;
import com.threedsoft.user.dto.responses.FieldResource;
import com.threedsoft.user.dto.responses.ScreenResource;
import com.threedsoft.user.dto.responses.SearchFieldResource;
import com.threedsoft.user.dto.responses.ViewEditFieldResource;
import com.threedsoft.user.util.UserConstants;

public class InventoryScreenNew {
	public static ScreenResource createInventoryScreen(String busName, String facilityNbr) {
		ScreenResource inventoryScreen = new ScreenResource("InventoryMaintenance", "Inventory Maintenance",
				"Item Inventory", "RW", UserConstants.MAINTENANCE_SCREEN, null);
		String searchUrl = "https://the3dsoft.com/inventory/api/inventories";
		String invnListUrl = "https://the3dsoft.com/inventory/api/inventories?busName.equals=" + busName
				+ "&facilityNbr.equals=" + facilityNbr + "&size=100";
		String invnAddRecordUrl = "https://the3dsoft.com/inventory/api/inventories";
		String updateRecordUrl = "https://the3dsoft.com/inventory/api/inventories";
		String deleteRecordUrl = "https://the3dsoft.com/inventory/api/inventories/{id}";
		DataResource inventoryDataResource = new DataResource("Inventory", "Inventory", searchUrl, invnListUrl,
				invnAddRecordUrl, updateRecordUrl, deleteRecordUrl);

		List<FieldResource> fieldList = new ArrayList();
		fieldList.add(FieldResource.createPrimaryField("id", "Inventory Id", "int", "10"));
		fieldList.add(FieldResource.createPrimaryField("busName", "Bus Name", "string", "25"));
		fieldList.add(FieldResource.createPrimaryField("facilityNbr", "Facility Nbr", "int", "10"));
		fieldList.add(FieldResource.createField("company", "Company", "string", "15"));
		fieldList.add(FieldResource.createField("division", "Division", "string", "10"));
		fieldList.add(FieldResource.createField("locnBrcd", "Locn Brcd", "string", "20"));
		fieldList.add(FieldResource.createField("itemBrcd", "Item Brcd", "string", "20"));
		fieldList.add(FieldResource.createField("qty", "Qty", "int", "2"));
		fieldList.add(FieldResource.createDropDownField("locked", "Locked", "string", "1", "Y,N", "N"));
		fieldList.add(FieldResource.createDropDownField("trackByLPN", "Track By LPN?", "string", "1", "Y,N", "N"));
		fieldList.add(FieldResource.createField("ilpn", "Inbound LPN", "string", "20"));
		fieldList.add(FieldResource.createField("userId", "userId", "int", "25"));

		List<SearchFieldResource> searchFieldList = new ArrayList();
		searchFieldList.add(SearchFieldResource.createHiddenFieldWithDefaultValue("busName", ".equals", busName));
		searchFieldList
				.add(SearchFieldResource.createHiddenFieldWithDefaultValue("facilityNbr", ".equals", facilityNbr));
		searchFieldList.add(SearchFieldResource.createSearchField("locnBrcd", ".contains"));
		searchFieldList.add(SearchFieldResource.createSearchField("itemBrcd", ".contains"));

		List<AddFieldResource> addRecordFieldList = new ArrayList();
		addRecordFieldList.add(AddFieldResource.createHiddenFieldWithDefaultValue("busName", busName));
		addRecordFieldList.add(AddFieldResource.createHiddenFieldWithDefaultValue("facilityNbr", facilityNbr));
		addRecordFieldList.add(AddFieldResource.createField("company"));
		addRecordFieldList.add(AddFieldResource.createField("division"));
		addRecordFieldList.add(AddFieldResource.createMandatoryField("busUnit"));
		addRecordFieldList.add(AddFieldResource.createMandatoryField("locnBrcd"));
		addRecordFieldList.add(AddFieldResource.createMandatoryField("itemBrcd"));
		addRecordFieldList.add(AddFieldResource.createMandatoryField("qty"));
		addRecordFieldList.add(AddFieldResource.createField("locked"));
		addRecordFieldList.add(AddFieldResource.createField("trackByLPN"));
		addRecordFieldList.add(AddFieldResource.createField("ilpn"));

		List<ViewEditFieldResource> editRecordFieldList = new ArrayList();
		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("id"));
		editRecordFieldList.add(ViewEditFieldResource.createHiddenField("busName"));
		editRecordFieldList.add(ViewEditFieldResource.createHiddenField("facilityNbr"));
		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("company"));
		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("division"));
		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("locnBrcd"));
		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("itemBrcd"));
		editRecordFieldList.add(ViewEditFieldResource.createMandatoryField("qty"));
		editRecordFieldList.add(ViewEditFieldResource.createField("locked"));
		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("ilpn"));
		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("trackByLPN"));

		List<ViewEditFieldResource> viewRecordFieldList = new ArrayList();
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("id"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("busName"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("facilityNbr"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("company"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("division"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("locnBrcd"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("itemBrcd"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("ilpn"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("qty"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("locked"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("trackByLPN"));

		inventoryDataResource.setFieldList(fieldList);
		inventoryDataResource.setSearchFieldList(searchFieldList);
		inventoryDataResource.setListFields("id,locnBrcd,itemBrcd,qty,locked,trackByLPN,ilpn,userId");
		inventoryDataResource.setAddResourceFieldList(addRecordFieldList);
		inventoryDataResource.setViewResourceFieldList(viewRecordFieldList);
		inventoryDataResource.setEditResourceFieldList(editRecordFieldList);
		inventoryScreen.setDataResource(inventoryDataResource);
		return inventoryScreen;
	}

}
