package com.threedsoft.user.menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.threedsoft.user.dto.responses.ActionResource;
import com.threedsoft.user.dto.responses.AddFieldResource;
import com.threedsoft.user.dto.responses.DataResource;
import com.threedsoft.user.dto.responses.FieldResource;
import com.threedsoft.user.dto.responses.ScreenResource;
import com.threedsoft.user.dto.responses.SearchFieldResource;
import com.threedsoft.user.dto.responses.ViewEditFieldResource;
import com.threedsoft.user.util.UserConstants;

public class OrderPlannerScreenNew {
	public static ScreenResource createOrderPlannerScreen(String busName, String facilityNbr) {
		ScreenResource wmsOrdersScreen = new ScreenResource("OrderPlanner", "Order Planner", "Order Planner", "RW",
				UserConstants.MAINTENANCE_SCREEN, null);
		String searchUrl = "https://the3dsoft.com/orderplanner/api/wms-orders";
		// even if we donot specify size=20, default size is 20.no page is specified
		// here
		String custOrdersListUrl = "https://the3dsoft.com/orderplanner/api/wms-orders?busName.equals=" + busName
				+ "&facilityNbr.equals=" + facilityNbr + "&size=100";
		String custOrdersAddRecordUrl = "https://the3dsoft.com/orderplanner/api/wms-orders";
		String custOrdersUpdateRecordUrl = "https://the3dsoft.com/orderplanner/api/wms-orders";
		String custOrdersDeleteRecordUrl = "https://the3dsoft.com/orderplanner/api/wms-orders/{id}";
		String packAndPrintActionUrl = "";
		String printPickListActionUrl = "";
		String reprintLabelActionUrl = "";
		DataResource customerOrderDataResource = new DataResource("Wms Orders", "Wms Orders", searchUrl,
				custOrdersListUrl, custOrdersAddRecordUrl, custOrdersUpdateRecordUrl, custOrdersDeleteRecordUrl);

		List<FieldResource> fieldList = new ArrayList();
		fieldList.add(FieldResource.createPrimaryField("id", "Id", "int", "10"));
		fieldList.add(FieldResource.createPrimaryField("orderNbr", "Order Nbr", "string", "10"));
		fieldList.add(FieldResource.createPrimaryField("busName", "Bus Name", "string", "25"));
		fieldList.add(FieldResource.createPrimaryField("facilityNbr", "Facility Nbr", "int", "10"));
		fieldList.add(FieldResource.createField("company", "Company", "string", "15"));
		fieldList.add(FieldResource.createField("division", "Division", "string", "10"));
		/*
		 * if(UserApplicationProperties.isHomeStore.equalsIgnoreCase("Y")) {
		 * fieldList.add(FieldResource.createField("batchNbr","Picklist Nbr", "string",
		 * "25")); }else {
		 * fieldList.add(FieldResource.createField("batchNbr","Batch Nbr", "string",
		 * "25")); }
		 */ fieldList.add(FieldResource.createField("batchNbr", "Batch Nbr", "string", "25"));
		fieldList.add(FieldResource.createField("orderDttm", "Order Date/Time", "datetime", "20"));
		fieldList.add(FieldResource.createField("expectedDeliveryDttm", "Delivery Date", "date", "20"));
		fieldList.add(FieldResource.createField("shipByDttm", "Ship By Date", "date", "20"));
		fieldList.add(FieldResource.createField("deliveryType", "Delivery Type", "string", "1"));
		fieldList.add(FieldResource.createDropDownField("statCode", "Order Status", "int", "10",
				"100,200,300,400,500,600,700", "None"));

		ActionResource packAndPrintAction = new ActionResource();
		packAndPrintAction.setActionName("packAndPrint");
		packAndPrintAction.setActionDisplayName("PackAndPrint");
		packAndPrintAction.setActionUrl(packAndPrintActionUrl);
		packAndPrintAction.setRequestFields("busName,facilityNbr,id");

		ActionResource printPickListAction = new ActionResource();
		printPickListAction.setActionName("printPickList");
		printPickListAction.setActionDisplayName("Print Picklist");
		printPickListAction.setActionUrl(printPickListActionUrl);
		printPickListAction.setRequestFields("busName,facilityNbr,id");

		ActionResource reprintLabelAction = new ActionResource();
		reprintLabelAction.setActionName("reprintLabels");
		reprintLabelAction.setActionDisplayName("Reprint Packing/Shipping Labels");
		reprintLabelAction.setActionUrl(reprintLabelActionUrl);
		reprintLabelAction.setRequestFields("busName,facilityNbr,id");
		/*
		 * List<SearchFieldResource> searchFieldList = new ArrayList();
		 * searchFieldList.add(new SearchFieldResource("busName", "Y", "N", busName));
		 * searchFieldList.add(new SearchFieldResource("facilityNbr", "Y", "N",
		 * facilityNbr.toString())); searchFieldList.add(new
		 * SearchFieldResource("orderNbr", "N", "N", "")); searchFieldList.add(new
		 * SearchFieldResource("batchNbr", "N", "N", ""));
		 */
		/*
		 * List<ListFieldResource> listRecordFieldList = new ArrayList();
		 * listRecordFieldList.add(new ListFieldResource("id"));
		 * listRecordFieldList.add(new ListFieldResource("orderNbr"));
		 * listRecordFieldList.add(new ListFieldResource("batchNbr"));
		 * listRecordFieldList.add(new ListFieldResource("busName"));
		 * listRecordFieldList.add(new ListFieldResource("facilityNbr"));
		 * listRecordFieldList.add(new ListFieldResource("company"));
		 * listRecordFieldList.add(new ListFieldResource("division"));
		 */
		List<AddFieldResource> addRecordFieldList = new ArrayList();
		addRecordFieldList.add(AddFieldResource.createHiddenFieldWithDefaultValue("busName", busName));
		addRecordFieldList.add(AddFieldResource.createHiddenFieldWithDefaultValue("facilityNbr", facilityNbr));
		addRecordFieldList.add(AddFieldResource.createMandatoryField("orderNbr"));
		addRecordFieldList.add(AddFieldResource.createField("company"));
		addRecordFieldList.add(AddFieldResource.createField("division"));
		addRecordFieldList.add(AddFieldResource.createMandatoryField("orderDttm"));
		addRecordFieldList.add(AddFieldResource.createMandatoryField("expectedDeliveryDttm"));
		addRecordFieldList.add(AddFieldResource.createMandatoryField("shipByDttm"));

		List<ViewEditFieldResource> editRecordFieldList = new ArrayList();
		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("id"));
		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("busName"));
		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("facilityNbr"));
		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("orderNbr"));
		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("company"));
		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("division"));
		editRecordFieldList.add(ViewEditFieldResource.createField("statCode"));
		editRecordFieldList.add(ViewEditFieldResource.createField("orderDttm"));
		editRecordFieldList.add(ViewEditFieldResource.createField("expectedDeliveryDttm"));
		editRecordFieldList.add(ViewEditFieldResource.createField("shipByDttm"));

		List<ViewEditFieldResource> viewRecordFieldList = new ArrayList();
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("id"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("busName"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("facilityNbr"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("orderNbr"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("company"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("division"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("orderDttm"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("expectedDeliveryDttm"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("shipByDttm"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("statCode"));

		List<SearchFieldResource> searchFieldList = new ArrayList();
		searchFieldList.add(SearchFieldResource.createHiddenFieldWithDefaultValue("busName", ".equals", busName));
		searchFieldList
				.add(SearchFieldResource.createHiddenFieldWithDefaultValue("facilityNbr", ".equals", facilityNbr));
		searchFieldList.add(SearchFieldResource.createSearchField("orderNbr", ".contains"));
		searchFieldList.add(SearchFieldResource.createSearchField("batchNbr", ".contains"));
		searchFieldList.add(SearchFieldResource.createSearchField("statCode", ".equals"));

		customerOrderDataResource.setFieldList(fieldList);
		customerOrderDataResource.setSearchFieldList(searchFieldList);
		customerOrderDataResource.setListFields("id,orderNbr,statCode,batchNbr,busName,facilityNbr,company,division");
		// customerOrderDataResource.setAddRecordFields("busName,facilityNbr,orderNbr,company,division");
		// customerOrderDataResource.setEditRecordFields("id,busName,facilityNbr,orderNbr,company,division");
		// customerOrderDataResource.setViewFields("id,busName,facilityNbr,orderNbr,company,division");
		customerOrderDataResource.setAddResourceFieldList(addRecordFieldList);
		customerOrderDataResource.setViewResourceFieldList(viewRecordFieldList);
		customerOrderDataResource.setEditResourceFieldList(editRecordFieldList);

		List actionList = new ArrayList();
//		if(UserApplicationProperties.isHomeStore.equalsIgnoreCase("Y")) {
		actionList.add(printPickListAction);
		actionList.add(packAndPrintAction);
		actionList.add(reprintLabelAction);
//		}
		customerOrderDataResource.setRecordActionList(actionList);
		customerOrderDataResource.setDtlResources(Arrays.asList(getCustomerOrderDetailResource(busName, facilityNbr)));
		wmsOrdersScreen.setDataResource(customerOrderDataResource);
		customerOrderDataResource.setPrimaryDtlResource(getCustomerOrderDetailResource(busName, facilityNbr));
		return wmsOrdersScreen;

	}

	private static DataResource getCustomerOrderDetailResource(String busName, String facilityNbr) {
		String searchUrl = "";
		String custOrderDtlListUrl = "https://the3dsoft.com/orderplanner/api/wms-order-lines?busName.equals=" + busName
				+ "&facilityNbr.equals=" + facilityNbr + "&wmsOrderId.equals={wmsOrderId}";
		String custOrderDtlAddRecordUrl = "https://the3dsoft.com/orderplanner/api/wms-order-lines";
		String custOrderDtlupdateRecordUrl = "https://the3dsoft.com/orderplanner/api/wms-order-lines";
		String custOrderDtldeleteRecordUrl = "https://the3dsoft.com/orderplanner/api/wms-order-lines/{id}";
		DataResource orderDtlResource = new DataResource("CustomerOrderDtl", "Customer Order Details", searchUrl,
				custOrderDtlListUrl, custOrderDtlAddRecordUrl, custOrderDtlupdateRecordUrl,
				custOrderDtldeleteRecordUrl);

		List<FieldResource> fieldList = new ArrayList();
		fieldList.add(FieldResource.createPrimaryField("id", "id", "int", "10"));
		fieldList.add(FieldResource.createField("wmsOrderId", "Customer Order Id", "int", "10"));
		fieldList.add(FieldResource.createField("lineNbr", "Line Nbr", "int", "3"));
		fieldList.add(FieldResource.createField("company", "Company", "string", "15"));
		fieldList.add(FieldResource.createField("division", "Division", "string", "10"));
		fieldList.add(FieldResource.createField("statCode", "Stat Code", "int", "10"));
		fieldList.add(FieldResource.createField("itemBrcd", "UPC", "string", "20"));
		fieldList.add(FieldResource.createField("orderNbr", "Order Nbr", "string", "10"));
		fieldList.add(FieldResource.createField("origQty", "Orig Order Qty", "int", "4"));
		fieldList.add(FieldResource.createField("qty", "Order Qty", "int", "4"));
		fieldList.add(FieldResource.createField("allocatedQty", "Allocated Qty", "int", "4"));
		fieldList.add(FieldResource.createField("pickedQty", "Picked Qty", "int", "4"));
		fieldList.add(FieldResource.createField("packedQty", "Packed Qty", "int", "4"));
		fieldList.add(FieldResource.createField("shippedQty", "Shipped Qty", "int", "4"));

		List<AddFieldResource> addRecordFieldList = new ArrayList();
		addRecordFieldList.add(AddFieldResource.createHiddenFieldWithDefaultValue("busName", busName));
		addRecordFieldList.add(AddFieldResource.createHiddenFieldWithDefaultValue("facilityNbr", facilityNbr));
		addRecordFieldList.add(AddFieldResource.createHdrReferenceField("wmsOrderId", "id"));
		addRecordFieldList.add(AddFieldResource.createHdrReferenceField("orderNbr", "orderNbr"));
		addRecordFieldList.add(AddFieldResource.createMandatoryField("lineNbr"));
		addRecordFieldList.add(AddFieldResource.createMandatoryField("itemBrcd"));
		addRecordFieldList.add(AddFieldResource.createMandatoryField("origQty"));
		addRecordFieldList.add(AddFieldResource.createMandatoryField("qty"));
//		addRecordFieldList.add(AddFieldResource.createTextField("allocatedQty"));
//		addRecordFieldList.add(AddFieldResource.createTextField("pickedQty"));
//		addRecordFieldList.add(AddFieldResource.createTextField("packedQty"));
//		addRecordFieldList.add(AddFieldResource.createTextField("shippedQty"));

		List<ViewEditFieldResource> editRecordFieldList = new ArrayList();
		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("id"));
		editRecordFieldList.add(ViewEditFieldResource.createHiddenField("busName"));
		editRecordFieldList.add(ViewEditFieldResource.createHiddenField("facilityNbr"));
		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("wmsOrderId"));
		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("orderNbr"));
		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("lineNbr"));
		editRecordFieldList.add(ViewEditFieldResource.createMandatoryField("itemBrcd"));
		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("origQty"));
		editRecordFieldList.add(ViewEditFieldResource.createMandatoryField("qty"));

		List<ViewEditFieldResource> viewRecordFieldList = new ArrayList();
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("id"));
		viewRecordFieldList.add(ViewEditFieldResource.createHiddenField("busName"));
		viewRecordFieldList.add(ViewEditFieldResource.createHiddenField("facilityNbr"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("orderNbr"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("wmsOrderId"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("lineNbr"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("itemBrcd"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("qty"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("allocatedQty"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("pickedQty"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("packedQty"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("shippedQty"));

		orderDtlResource.setFieldList(fieldList);
		// orderDtlResource.setSearchFieldList(searchFieldList);
		orderDtlResource.setListFields(
				"id,wmsOrderId,orderNbr,lineNbr,itemBrcd,origQty, qty, allocatedQty, pickedQty, packedQty, shippedQty");
		// orderDtlResource.setAddRecordFields("origQty,qty,allocatedQty,pickedQty,packedQty,shippedQty");
		// orderDtlResource.setEditRecordFields("id,busName,facilityNbr,orderNbr,wmsOrderId,lineNbr,orderQy");
		// orderDtlResource.setViewFields("id,wmsOrderId,orderNbr,lineNbr,origQty,
		// qty, allocatedQty, pickedQty, packedQty, shippedQty");
		orderDtlResource.setAddResourceFieldList(addRecordFieldList);
		orderDtlResource.setEditResourceFieldList(editRecordFieldList);
		orderDtlResource.setViewResourceFieldList(viewRecordFieldList);
		orderDtlResource.setHdrDisplayFields("id,orderNbr,company,division,orderDttm,shipByDttm");
		return orderDtlResource;
	}

}
