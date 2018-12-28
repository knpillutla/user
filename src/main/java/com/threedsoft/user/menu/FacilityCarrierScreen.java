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

public class FacilityCarrierScreen {
	public static ScreenResource createFacilityCarrierScreen(String busName, Integer locnNbr) {
		ScreenResource facilityCarrierScreen = new ScreenResource("facilityShippingCarrierMaintenance", "Shipping Carrier Maintenance",
				"Shipping Carrier Maintenance", "RW", UserConstants.MAINTENANCE_SCREEN, null);
		String searchUrl = "";
		String listUrl = "https://the3dsoft.com/shipping/v1/facility-carriers";
		String getRecordUrl = "";
		String addRecordUrl = "https://the3dsoft.com/shipping/v1/facility-carriers";
		String updateRecordUrl = "https://the3dsoft.com/shipping/v1/facility-carriers/{id}";
				
		String deleteRecordUrl = "";
		DataResource facilityCarrierDataResource = new DataResource("carrier", "Carrier", searchUrl, listUrl,
				addRecordUrl, updateRecordUrl, deleteRecordUrl);

		List<FieldResource> fieldList = new ArrayList();
		fieldList.add(FieldResource.createPrimaryField("id", "facility carrier Id", "int", "10"));
		fieldList.add(FieldResource.createPrimaryField("busName","Bus Name", "string", "25"));
		fieldList.add(FieldResource.createPrimaryField("locnNbr", "Locn Nbr", "int", "10"));
		//fieldList.add(FieldResource.createDropDownField("carrierCode","Carrier Code", "string", "15","stamps_com,usps,fedex","stamps_com"));
		fieldList.add(FieldResource.createField("carrierCode","Carrier Code", "string", "15"));
		fieldList.add(FieldResource.createField("accountNbr","Account Number", "string", "10"));
		fieldList.add(FieldResource.createField("isEnabled","Active?", "string", "1"));
		fieldList.add(FieldResource.createField("updatedBy", "User", "int", "25"));

		//List<SearchFieldResource> searchFieldList = new ArrayList();
				
		List<AddFieldResource> addRecordFieldList = new ArrayList();
		addRecordFieldList.add(AddFieldResource.createHiddenFieldWithDefaultValue("busName",busName));
		addRecordFieldList.add(AddFieldResource.createHiddenFieldWithDefaultValue("locnNbr",locnNbr));
		addRecordFieldList.add(AddFieldResource.createField("carrierCode"));
		addRecordFieldList.add(AddFieldResource.createField("accountNbr"));
		addRecordFieldList.add(AddFieldResource.createField("isEnabled"));

		List<ViewEditFieldResource> editRecordFieldList = new ArrayList();
		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("id"));
		editRecordFieldList.add(ViewEditFieldResource.createHiddenField("busName"));
		editRecordFieldList.add(ViewEditFieldResource.createHiddenField("locnNbr"));
		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("carrierCode"));
		editRecordFieldList.add(ViewEditFieldResource.createField("accountNbr"));
		editRecordFieldList.add(ViewEditFieldResource.createField("isEnabled"));

		List<ViewEditFieldResource> viewRecordFieldList = new ArrayList();
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("id"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("busName"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("locnNbr"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("carrierCode"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("accountNbr"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("isEnabled"));

		facilityCarrierDataResource.setFieldList(fieldList);
		//facilityCarrierDataResource.setSearchFieldList(searchFieldList);
		facilityCarrierDataResource.setListFields("id,carrierCode,accountNbr,isEnabled,updatedBy");
		facilityCarrierDataResource.setAddResourceFieldList(addRecordFieldList);
		facilityCarrierDataResource.setViewResourceFieldList(viewRecordFieldList);
		facilityCarrierDataResource.setEditResourceFieldList(editRecordFieldList);
		facilityCarrierScreen.setDataResource(facilityCarrierDataResource);
		return facilityCarrierScreen;
	}

}
