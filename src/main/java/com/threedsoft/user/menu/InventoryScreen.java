package com.threedsoft.user.menu;

import java.util.ArrayList;
import java.util.List;

import com.threedsoft.user.dto.responses.DataResource;
import com.threedsoft.user.dto.responses.FieldResource;
import com.threedsoft.user.dto.responses.ScreenResource;
import com.threedsoft.user.dto.responses.SearchFieldResource;
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
		fieldList.add(FieldResource.createPrimaryField("id", "pickId","pick id", "int", "10"));
		fieldList.add(FieldResource.createPrimaryField("busName","busName","Bus Name", "string", "25"));
		fieldList.add(FieldResource.createPrimaryField("locnNbr", "locnNbr", "Locn Nbr", "int", "10"));
		fieldList.add(FieldResource.createField("company","Company", "string", "15"));
		fieldList.add(FieldResource.createField("division","Division", "string", "10"));
		fieldList.add(FieldResource.createField("locnBrcd", "Locn Brcd","string", "20"));
		fieldList.add(FieldResource.createField("itemBrcd", "Item Brcd","string", "20"));
		fieldList.add(FieldResource.createField("qty", "Qty", "int", "2"));
		fieldList.add(FieldResource.createDropDownField("locked", "Locked", "string", "1","Y,N","N"));
		fieldList.add(FieldResource.createDropDownField("trackByIlpn", "Track By LPN?", "string", "1","Y,N","N"));

		List<SearchFieldResource> searchFieldList = new ArrayList();
		searchFieldList.add(SearchFieldResource.createHiddenFieldWithDefaultValue("busName",busName));
		searchFieldList.add(SearchFieldResource.createHiddenFieldWithDefaultValue("locnNbr",locnNbr.toString()));
		searchFieldList.add(SearchFieldResource.createSearchField("locnBrcd"));
		searchFieldList.add(SearchFieldResource.createSearchField("itemBrcd"));
				
		inventoryDataResource.setFieldList(fieldList);
		inventoryDataResource.setSearchFieldList(searchFieldList);
		inventoryDataResource.setListFields("id,locnBrcd,itemBrcd,qty,locked,trackByIlpn");
		inventoryScreen.setDataResource(inventoryDataResource);
		return inventoryScreen;
	}

}
