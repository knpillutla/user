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

public class ShipmentScreenNew {
	public static ScreenResource createShippingScreen(String busName, String facilityNbr) {
		ScreenResource shippingScreen = new ScreenResource("newShipmentMaintenance", "New Shipment Maintenance",
				"New Shipment Maintenance", "R", UserConstants.MAINTENANCE_SCREEN, null);
		String searchUrl = "https://the3dsoft.com/shipment/api/shipments";
		// even if we donot specify size=20, default size is 20.no page is specified
		// here
		String shipListUrl = "https://the3dsoft.com/shipment/api/shipments?busName.equals=" + busName
				+ "&facilityNbr.equals=" + facilityNbr + "&statCode.equals=100&size=20";
		String shipAddRecordUrl = "https://the3dsoft.com/shipment/api/shipments";
		String updateRecordUrl = "https://the3dsoft.com/shipment/api/shipments";
		String deleteRecordUrl = "https://the3dsoft.com/shipment/api/shipments/{id}";
		DataResource shippingDataResource = new DataResource("shipping", "shipping", searchUrl, shipListUrl,
				shipAddRecordUrl, updateRecordUrl, deleteRecordUrl);

		List<FieldResource> fieldList = new ArrayList();
		fieldList.add(FieldResource.createPrimaryField("id", "Id", "int", "10"));
		fieldList.add(FieldResource.createPrimaryField("busName", "Bus Name", "string", "25"));
		fieldList.add(FieldResource.createPrimaryField("facilityNbr", "Facility Nbr", "int", "10"));
		fieldList.add(FieldResource.createField("company", "Company", "string", "15"));
		fieldList.add(FieldResource.createField("division", "Division", "string", "10"));
		fieldList.add(FieldResource.createField("batchNbr", "Batch Nbr", "string", "20"));
		fieldList.add(FieldResource.createField("orderId", "Order Id", "string", "20"));
		fieldList.add(FieldResource.createField("orderNbr", "Order Nbr", "string", "15"));
		fieldList.add(FieldResource.createField("statCode", "Stat Code", "Integer", "10"));
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
		fieldList.add(FieldResource.createField("shippingLabelContentType", "Shipping Label Format", "string", "3"));
		fieldList.add(FieldResource.createField("shippingLabel", "Shipping Label", "byte[]", "10000"));
		fieldList.add(FieldResource.createField("invoiceLabel", "Shipping Label", "byte[]", "10000"));
		fieldList.add(FieldResource.createField("invoiceLabelContentType", "Invoice Label Format", "string", "3"));
		fieldList.add(FieldResource.createField("updatedBy", "User Id", "String", "25"));

		List<SearchFieldResource> searchFieldList = new ArrayList();
		searchFieldList.add(SearchFieldResource.createHiddenFieldWithDefaultValue("busName", ".equals", busName));
		searchFieldList
				.add(SearchFieldResource.createHiddenFieldWithDefaultValue("facilityNbr", ".equals", facilityNbr));
		searchFieldList.add(SearchFieldResource.createSearchField("orderNbr", ".contains"));
		searchFieldList.add(SearchFieldResource.createSearchField("trackingNbr", ".contains"));
		searchFieldList.add(SearchFieldResource.createSearchField("statCode", ".equals"));
		List<ViewEditFieldResource> viewRecordFieldList = new ArrayList();
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("id"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("busName"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("facilityNbr"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("company"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("division"));
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
		shippingDataResource
				.setListFields("id,orderNbr,deliveryType,shipCarrier,shipCarrierService,shipCost,zipcode,userId");
		// shippingDataResource.setAddResourceFieldList(addRecordFieldList);
		shippingDataResource.setViewResourceFieldList(viewRecordFieldList);
		BrowserViewerResource shippingLabelViewer = new BrowserViewerResource("pdfViewer", "View/Print Shipping Label",
				"pdf", "shipLabel");
		shippingDataResource.setBrowserViewerResource(shippingLabelViewer);
		// shippingDataResource.setEditResourceFieldList(editRecordFieldList);
		shippingScreen.setDataResource(shippingDataResource);
		return shippingScreen;
	}

}
