package com.threedsoft.user.menu;

import java.util.ArrayList;
import java.util.List;

import com.threedsoft.user.dto.responses.BrowserViewerResource;
import com.threedsoft.user.dto.responses.DataResource;
import com.threedsoft.user.dto.responses.FieldResource;
import com.threedsoft.user.dto.responses.ScreenResource;
import com.threedsoft.user.dto.responses.SearchFieldResource;
import com.threedsoft.user.dto.responses.ViewEditFieldResource;
import com.threedsoft.user.util.UserConstants;

public class ShippingScreen {
	public static ScreenResource createShippingScreen(String busName, Integer locnNbr) {
		ScreenResource shippingScreen = new ScreenResource("shippingMaintenance", "Shipping Maintenance",
				"Item shipping", "R", UserConstants.MAINTENANCE_SCREEN, null);
		String searchUrl = "https://the3dsoft.com/shipping/v1/" + busName + "/" + locnNbr + "/ships/search";
		String shipListUrl = "https://the3dsoft.com/shipping/v1/" + busName + "/" + locnNbr + "/ships";
		String shipGetRecordUrl = "https://the3dsoft.com/shipping/v1/" + busName + "/" + locnNbr + "/ships/{id}";
		String shipAddRecordUrl = "";
		String updateRecordUrl = "https://the3dsoft.com/shipping/v1/" + busName + "/" + locnNbr + "/ships"
				+ "/{id}";
		String deleteRecordUrl = "";
		DataResource shippingDataResource = new DataResource("shipping", "shipping", searchUrl, shipListUrl,
				shipAddRecordUrl, updateRecordUrl, deleteRecordUrl);

		List<FieldResource> fieldList = new ArrayList();
		fieldList.add(FieldResource.createPrimaryField("id", "shipping Id", "int", "10"));
		fieldList.add(FieldResource.createPrimaryField("busName","Bus Name", "string", "25"));
		fieldList.add(FieldResource.createPrimaryField("locnNbr", "Locn Nbr", "int", "10"));
		fieldList.add(FieldResource.createField("company","Company", "string", "15"));
		fieldList.add(FieldResource.createField("division","Division", "string", "10"));
		fieldList.add(FieldResource.createField("busUnit","Bus Unit", "string", "2"));
		fieldList.add(FieldResource.createField("batchNbr", "Batch Nbr","string", "20"));
		fieldList.add(FieldResource.createField("orderId", "Order Id","string", "20"));
		fieldList.add(FieldResource.createField("orderNbr", "Order Nbr", "string", "15"));
		fieldList.add(FieldResource.createField("statCode", "Status", "string", "10"));
		fieldList.add(FieldResource.createField("orderDttm", "Order Date/Time", "datetime", "20"));
		fieldList.add(FieldResource.createField("shipByDttm", "ShipBy Date", "date", "20"));
		fieldList.add(FieldResource.createField("expectedDeliveryDttm", "Expected Delivery Date", "date", "20"));
		fieldList.add(FieldResource.createField("deliveryType", "Delivery Type", "string", "20"));
		fieldList.add(FieldResource.createField("shipCarrier", "Ship Carrier", "string", "20"));
		fieldList.add(FieldResource.createField("shipCarrierService", "Ship Carrier Service", "string", "20"));
		fieldList.add(FieldResource.createField("packageNbr", "Package Nbr", "string", "20"));
		fieldList.add(FieldResource.createField("trackingNbr", "Tracking Nbr", "string", "20"));
		fieldList.add(FieldResource.createField("firstName", "Delivery First Name", "string", "20"));
		fieldList.add(FieldResource.createField("lastName", "Delivery Last Name", "string", "20"));
		fieldList.add(FieldResource.createField("middleName", "Delivery Middle Name", "string", "20"));
		fieldList.add(FieldResource.createField("addr1", "Delivery Address 1", "string", "20"));
		fieldList.add(FieldResource.createField("addr2", "Delivery Address 2", "string", "20"));
		fieldList.add(FieldResource.createField("addr3", "Delivery Address 3", "string", "20"));
		fieldList.add(FieldResource.createField("city", "Delivery City", "string", "20"));
		fieldList.add(FieldResource.createField("state", "Delivery State", "string", "20"));
		fieldList.add(FieldResource.createField("country", "Delivery Country", "string", "20"));
		fieldList.add(FieldResource.createField("zipcode", "Delivery ZipCode", "string", "20"));
		fieldList.add(FieldResource.createField("phoneNbr", "Delivery Phone", "string", "20"));
		fieldList.add(FieldResource.createField("shipLabelFormat", "Shipping Label Format", "string", "3"));
		fieldList.add(FieldResource.createField("shipLabel", "Shipping Label", "byte[]", "10000"));
		fieldList.add(FieldResource.createField("userId", "userId", "int", "25"));

		List<SearchFieldResource> searchFieldList = new ArrayList();
		searchFieldList.add(SearchFieldResource.createHiddenFieldWithDefaultValue("busName",busName));
		searchFieldList.add(SearchFieldResource.createHiddenFieldWithDefaultValue("locnNbr",locnNbr.toString()));
		searchFieldList.add(SearchFieldResource.createSearchField("orderNbr"));
		searchFieldList.add(SearchFieldResource.createSearchField("trackingNbr"));
				
/*		List<AddFieldResource> addRecordFieldList = new ArrayList();
		addRecordFieldList.add(AddFieldResource.createHiddenFieldWithDefaultValue("busName",busName));
		addRecordFieldList.add(AddFieldResource.createHiddenFieldWithDefaultValue("locnNbr",locnNbr));
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
		editRecordFieldList.add(ViewEditFieldResource.createHiddenField("locnNbr"));
		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("company"));
		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("division"));
		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("busUnit"));
		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("locnBrcd"));
		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("itemBrcd"));
		editRecordFieldList.add(ViewEditFieldResource.createMandatoryField("qty"));
		editRecordFieldList.add(ViewEditFieldResource.createField("locked"));
		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("ilpn"));
		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("trackByLPN"));
*/
		List<ViewEditFieldResource> viewRecordFieldList = new ArrayList();
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("id"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("busName"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("locnNbr"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("company"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("division"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("busUnit"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("orderNbr"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("trackingNbr"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("shipCarrier"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("shipCarrierService"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("shipCost"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("firstName"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("lastName"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("middleName"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("addr1"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("addr2"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("addr3"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("city"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("state"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("country"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("zipcode"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("phoneNbr"));
		
		

		shippingDataResource.setFieldList(fieldList);
		shippingDataResource.setSearchFieldList(searchFieldList);
		shippingDataResource.setListFields("id,orderNbr,deliveryType,shipCarrier,shipCarrierService,shipCost,zipcode,userId");
		//shippingDataResource.setAddResourceFieldList(addRecordFieldList);
		shippingDataResource.setViewResourceFieldList(viewRecordFieldList);
		BrowserViewerResource shippingLabelViewer = new BrowserViewerResource("pdfViewer","View/Print Shipping Label", "pdf","shipLabel");
		shippingDataResource.setBrowserViewerResource(shippingLabelViewer);
		//shippingDataResource.setEditResourceFieldList(editRecordFieldList);
		shippingScreen.setDataResource(shippingDataResource);
		return shippingScreen;
	}

}
