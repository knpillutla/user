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

public class InventoryScreen {
	public static ScreenResource createInventoryScreen(String busName, Integer locnNbr) {
		ScreenResource inventoryScreen = new ScreenResource("InventoryMaintenance", "Inventory Maintenance",
				"Item Inventory", "RW", UserConstants.MAINTENANCE_SCREEN, null);
		String searchUrl = "https://the3dsoft.com/inventory/v1/" + busName + "/" + locnNbr + "/inventory/search";
		String invnListUrl = "https://the3dsoft.com/inventory/v1/" + busName + "/" + locnNbr + "/inventory";
		String invnGetRecordUrl = "https://the3dsoft.com/inventory/v1/" + busName + "/" + locnNbr + "/inventory/{id}";
		String invnAddRecordUrl = "https://the3dsoft.com/inventory/v1/" + busName + "/" + locnNbr + "/inventory";
		String updateRecordUrl = "https://the3dsoft.com/inventory/v1/" + busName + "/" + locnNbr + "/inventory"
				+ "/{id}";
		String deleteRecordUrl = "https://the3dsoft.com/inventory/v1/" + busName + "/" + locnNbr + "/inventory"
				+ "/{id}";
		DataResource inventoryDataResource = new DataResource("Inventory", "Inventory", searchUrl, invnListUrl,
				invnAddRecordUrl, updateRecordUrl, deleteRecordUrl);

		List<FieldResource> fieldList = new ArrayList();
		fieldList.add(FieldResource.createPrimaryField("id", "Inventory Id", "int", "10"));
		fieldList.add(FieldResource.createPrimaryField("busName","Bus Name", "string", "25"));
		fieldList.add(FieldResource.createPrimaryField("locnNbr", "Locn Nbr", "int", "10"));
		fieldList.add(FieldResource.createField("company","Company", "string", "15"));
		fieldList.add(FieldResource.createField("division","Division", "string", "10"));
		fieldList.add(FieldResource.createMandotoryField("busUnit","Bus Unit", "string", "2"));
		fieldList.add(FieldResource.createMandotoryField("locnBrcd", "Locn Brcd","string", "20"));
		fieldList.add(FieldResource.createMandotoryField("itemBrcd", "Item Brcd","string", "20"));
		fieldList.add(FieldResource.createMandotoryField("qty", "Qty", "int", "2"));
		fieldList.add(FieldResource.createDropDownField("locked", "Locked", "string", "1","Y,N","N"));
		fieldList.add(FieldResource.createDropDownField("trackByIlpn", "Track By LPN?", "string", "1","Y,N","N"));

		List<SearchFieldResource> searchFieldList = new ArrayList();
		searchFieldList.add(SearchFieldResource.createHiddenFieldWithDefaultValue("busName",busName));
		searchFieldList.add(SearchFieldResource.createHiddenFieldWithDefaultValue("locnNbr",locnNbr.toString()));
		searchFieldList.add(SearchFieldResource.createSearchField("locnBrcd"));
		searchFieldList.add(SearchFieldResource.createSearchField("itemBrcd"));
				
		List<AddFieldResource> addRecordFieldList = new ArrayList();
		addRecordFieldList.add(AddFieldResource.createHiddenFieldWithDefaultValue("busName",busName));
		addRecordFieldList.add(AddFieldResource.createHiddenFieldWithDefaultValue("locnNbr",locnNbr));
		addRecordFieldList.add(AddFieldResource.createTextField("company"));
		addRecordFieldList.add(AddFieldResource.createTextField("division"));
		addRecordFieldList.add(AddFieldResource.createTextField("busUnit"));
		addRecordFieldList.add(AddFieldResource.createTextField("qty"));
		addRecordFieldList.add(AddFieldResource.createTextField("locked"));
		addRecordFieldList.add(AddFieldResource.createTextField("trackByIlpn"));

		List<ViewEditFieldResource> editRecordFieldList = new ArrayList();
		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("id"));
		editRecordFieldList.add(ViewEditFieldResource.createHiddenField("busName"));
		editRecordFieldList.add(ViewEditFieldResource.createHiddenField("locnNbr"));
		editRecordFieldList.add(ViewEditFieldResource.createHiddenField("company"));
		editRecordFieldList.add(ViewEditFieldResource.createHiddenField("division"));
		editRecordFieldList.add(ViewEditFieldResource.createHiddenField("busUnit"));
		editRecordFieldList.add(ViewEditFieldResource.createTextField("qty"));
		editRecordFieldList.add(ViewEditFieldResource.createTextField("locked"));

		List<ViewEditFieldResource> viewRecordFieldList = new ArrayList();
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("id"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("busName"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("locnNbr"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("company"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("division"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("busUnit"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("qty"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("locked"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("trackByIlpn"));

		inventoryDataResource.setFieldList(fieldList);
		inventoryDataResource.setSearchFieldList(searchFieldList);
		inventoryDataResource.setListFields("id,locnBrcd,itemBrcd,qty,locked,trackByIlpn");
		inventoryDataResource.setAddResourceFieldList(addRecordFieldList);
		inventoryDataResource.setViewResourceFieldList(viewRecordFieldList);
		inventoryDataResource.setEditResourceFieldList(editRecordFieldList);
		inventoryScreen.setDataResource(inventoryDataResource);
		return inventoryScreen;
	}

}
