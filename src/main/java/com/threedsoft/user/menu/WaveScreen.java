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

public class WaveScreen {
	public static ScreenResource createWaveScreen(String busName, String facilityNbr) {
		ScreenResource inventoryScreen = new ScreenResource("WaveMaintenance", "Wave Maintenance", "Wave", "RW",
				UserConstants.MAINTENANCE_SCREEN, null);
		String searchUrl = "https://the3dsoft.com/inventory/api/waves";
		String invnListUrl = "https://the3dsoft.com/inventory/api/waves?busName.equals=" + busName
				+ "&facilityNbr.equals=" + facilityNbr + "&size=100";
		String invnAddRecordUrl = "https://the3dsoft.com/inventory/api/waves";
		String updateRecordUrl = "https://the3dsoft.com/inventory/api/waves";
		String deleteRecordUrl = "https://the3dsoft.com/inventory/api/waves/{id}";
		DataResource inventoryDataResource = new DataResource("Wave", "Wave", searchUrl, invnListUrl, invnAddRecordUrl,
				updateRecordUrl, deleteRecordUrl);

		List<FieldResource> fieldList = new ArrayList();
		fieldList.add(FieldResource.createPrimaryField("id", "Wave Id", "int", "10"));
		fieldList.add(FieldResource.createPrimaryField("busName", "Bus Name", "string", "25"));
		fieldList.add(FieldResource.createPrimaryField("facilityNbr", "Facility Nbr", "int", "10"));
		fieldList.add(FieldResource.createField("company", "Company", "string", "15"));
		fieldList.add(FieldResource.createField("division", "Division", "string", "10"));
		fieldList.add(FieldResource.createField("waveNbr", "Wave Nbr", "string", "20"));
		fieldList.add(FieldResource.createField("totalOrders", "Total Orders", "int", "4"));
		fieldList.add(FieldResource.createField("totalShipments", "Total Shipments", "int", "4"));
		fieldList.add(FieldResource.createField("totalShipmentsReleased", "Shipments Released", "int", "4"));
		fieldList.add(FieldResource.createField("totalShipmentsAllocated", "Shipments Allocated", "int", "4"));
		fieldList.add(FieldResource.createField("totalShipmentsPicked", "Shipments Picked", "int", "4"));
		fieldList.add(FieldResource.createField("totalShipmentsPacked", "Shipmentsm Packed", "int", "4"));
		fieldList.add(FieldResource.createField("totalShipmentsShipped", "Shipments Shipped", "int", "4"));
		fieldList.add(FieldResource.createDropDownField("waveStatCode", "Wave Stat Code", "Integer", "4",
				"100,200,300,400", "None"));
		fieldList.add(FieldResource.createField("waveStatCodeDesc", "Wave Status Description", "string", "20"));
		fieldList.add(
				FieldResource.createDropDownField("statCode", "Stat Code", "Integer", "4", "100,200,300,400", "None"));
		fieldList.add(FieldResource.createField("statCodeDesc", "Status Description", "string", "20"));
		fieldList.add(FieldResource.createField("createdDttm", "Created Dttm", "date", "20"));
		fieldList.add(FieldResource.createField("updatedDttm", "Updated Dttm", "date", "20"));
		fieldList.add(FieldResource.createField("createdBy", "Created By", "string", "25"));
		fieldList.add(FieldResource.createField("updatedBy", "Updated By", "string", "25"));

		List<SearchFieldResource> searchFieldList = new ArrayList();
		searchFieldList.add(SearchFieldResource.createHiddenFieldWithDefaultValue("busName", ".equals", busName));
		searchFieldList
				.add(SearchFieldResource.createHiddenFieldWithDefaultValue("facilityNbr", ".equals", facilityNbr));
		searchFieldList.add(SearchFieldResource.createSearchField("waveNbr", ".contains"));
		searchFieldList.add(SearchFieldResource.createSearchField("statCode", ".equals"));

		List<AddFieldResource> addRecordFieldList = new ArrayList();
//		addRecordFieldList.add(AddFieldResource.createHiddenFieldWithDefaultValue("busName", busName));
//		addRecordFieldList.add(AddFieldResource.createHiddenFieldWithDefaultValue("facilityNbr", facilityNbr));
//		addRecordFieldList.add(AddFieldResource.createField("company"));
//		addRecordFieldList.add(AddFieldResource.createField("division"));
//		addRecordFieldList.add(AddFieldResource.createMandatoryField("busUnit"));
//		addRecordFieldList.add(AddFieldResource.createMandatoryField("locnBrcd"));
//		addRecordFieldList.add(AddFieldResource.createMandatoryField("itemBrcd"));
//		addRecordFieldList.add(AddFieldResource.createMandatoryField("qty"));
//		addRecordFieldList.add(AddFieldResource.createField("locked"));
//		addRecordFieldList.add(AddFieldResource.createField("trackByLPN"));
//		addRecordFieldList.add(AddFieldResource.createField("ilpn"));

		List<ViewEditFieldResource> editRecordFieldList = new ArrayList();
//		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("id"));
//		editRecordFieldList.add(ViewEditFieldResource.createHiddenField("busName"));
//		editRecordFieldList.add(ViewEditFieldResource.createHiddenField("facilityNbr"));
//		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("company"));
//		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("division"));
//		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("locnBrcd"));
//		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("itemBrcd"));
//		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("statCode"));
//		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("statCodeDesc"));
//		editRecordFieldList.add(ViewEditFieldResource.createMandatoryField("qty"));
//		editRecordFieldList.add(ViewEditFieldResource.createField("locked"));
//		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("ilpn"));
//		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("trackByLPN"));

		List<ViewEditFieldResource> viewRecordFieldList = new ArrayList();
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("id"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("busName"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("facilityNbr"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("company"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("division"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("waveStatCode"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("waveStatCodeDesc"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("statCode"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("statCodeDesc"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("totalOrders"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("totalShipments"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("totalShipmentsReleased"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("totalShipmentsAllocated"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("totalShipmentsPicked"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("totalShipmentsPacked"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("totalShipmentsShipped"));

		inventoryDataResource.setFieldList(fieldList);
		inventoryDataResource.setSearchFieldList(searchFieldList);
		inventoryDataResource.setListFields(
				"id,busName,facilityNbr,company,division,waveNbr,waveStatCode,waveStatCodeDesc,statCode,statCodeDesc,totalOrders, totalShipments,totalShipmentsReleased,totalShipmentsAllocated, totalShipmentsPicked, totalShipmentsPacked,totalShipmentsShipped,createdDttm,updatedDttm,createdBy,updatedBy");
		inventoryDataResource.setAddResourceFieldList(addRecordFieldList);
		inventoryDataResource.setViewResourceFieldList(viewRecordFieldList);
		inventoryDataResource.setEditResourceFieldList(editRecordFieldList);
		inventoryScreen.setDataResource(inventoryDataResource);
		return inventoryScreen;
	}

}
