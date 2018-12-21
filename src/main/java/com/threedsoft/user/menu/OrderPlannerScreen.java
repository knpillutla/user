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

public class OrderPlannerScreen {
	public static ScreenResource createOrdersScreen(String busName, Integer locnNbr) {
		ScreenResource OrdersScreen = new ScreenResource("OrderPlaner", "OrderPlanner Screen", "Customer Orders", "R",
				UserConstants.MAINTENANCE_SCREEN, null);
		String searchUrl = "https://the3dsoft.com/orderplanner/v1/" + busName + "/" + locnNbr + "/order/search";
		String ordersListUrl = "https://the3dsoft.com/orderplanner/v1/" + busName + "/" + locnNbr + "/order";
//	//	String ordersRecordUrl = "https://the3dsoft.com/orderplanner/v1/" + busName + "/" + locnNbr
		// + "/order/{id}";
		String ordersAddRecordUrl = "https://the3dsoft.com/orderplanner/v1/" + busName + "/" + locnNbr + "/order";
		String ordersUpdateRecordUrl = "https://the3dsoft.com/orderplanner/v1/" + busName + "/" + locnNbr + "/order"
				+ "/{id}";
		String ordersDeleteRecordUrl = "https://the3dsoft.com/orderplanner/v1/" + busName + "/" + locnNbr + "/order"
				+ "/{id}";
		DataResource OrderDataResource = new DataResource("Orders", "Orders", searchUrl, ordersListUrl,
				ordersAddRecordUrl, ordersUpdateRecordUrl, ordersDeleteRecordUrl);

		List<FieldResource> fieldList = new ArrayList();
		fieldList.add(FieldResource.createPrimaryField("id", "id", "int", "10"));
		fieldList.add(FieldResource.createPrimaryField("orderNbr", "Order Nbr", "string", "10"));
		fieldList.add(FieldResource.createPrimaryField("busName", "Bus Name", "string", "25"));
		fieldList.add(FieldResource.createPrimaryField("locnNbr", "Locn Nbr", "int", "10"));
		fieldList.add(FieldResource.createField("company", "Company", "string", "15"));
		fieldList.add(FieldResource.createField("division", "Division", "string", "10"));
		fieldList.add(FieldResource.createField("busUnit","Bus Unit", "string", "2"));
		fieldList.add(FieldResource.createField("batchNbr", "Batch Nbr", "string", "25"));
		fieldList.add(FieldResource.createField("orderDttm","Order Date/Time", "datetime", "20"));
		fieldList.add(FieldResource.createField("expectedDeliveryDttm","Delivery Date", "date", "20"));
		fieldList.add(FieldResource.createField("shipByDttm","Ship By Date", "date", "20"));
		fieldList.add(FieldResource.createField("deliveryType", "Delivery Type", "string", "1"));
		String startFullfillmentActionUrl = "https://the3dsoft.com/orderplanner/v1/" + busName + "/" + locnNbr
				+ "/order" + "/{id}";

		ActionResource startFullfillmentAction = new ActionResource();
		startFullfillmentAction.setActionName("startfullfillment");
		startFullfillmentAction.setActionDisplayName("Start Fulfillment");
		startFullfillmentAction.setActionUrl(startFullfillmentActionUrl);
		/*
		 * List<RequestFieldResource> requestFieldList = new ArrayList();
		 * requestFieldList.add(new RequestFieldResource("busName","","N"));
		 * requestFieldList.add(new RequestFieldResource("locnNbr","","N"));
		 * requestFieldList.add(new RequestFieldResource("id","List","N"));
		 */ startFullfillmentAction.setRequestFields("busName,locnNbr,id");

		/*
		 * List<SearchFieldResource> searchFieldList = new ArrayList();
		 * searchFieldList.add(new SearchFieldResource("busName", "Y", "N", busName));
		 * searchFieldList.add(new SearchFieldResource("locnNbr", "Y", "N",
		 * locnNbr.toString())); searchFieldList.add(new SearchFieldResource("orderNbr",
		 * "N", "N", "")); searchFieldList.add(new SearchFieldResource("batchNbr", "N",
		 * "N", ""));
		 */
		/*
		 * List<ListFieldResource> listRecordFieldList = new ArrayList();
		 * listRecordFieldList.add(new ListFieldResource("id"));
		 * listRecordFieldList.add(new ListFieldResource("orderNbr"));
		 * listRecordFieldList.add(new ListFieldResource("batchNbr"));
		 * listRecordFieldList.add(new ListFieldResource("busName"));
		 * listRecordFieldList.add(new ListFieldResource("locnNbr"));
		 * listRecordFieldList.add(new ListFieldResource("company"));
		 * listRecordFieldList.add(new ListFieldResource("division"));
		 */
		/*
		 * List<AddFieldResource> addRecordFieldList = new ArrayList();
		 * addRecordFieldList.add(new AddFieldResource("busName", "Y", "N", busName));
		 * addRecordFieldList.add(new AddFieldResource("locnNbr", "Y", "N",
		 * locnNbr.toString())); addRecordFieldList.add(new AddFieldResource("orderNbr",
		 * "N", "N", "")); addRecordFieldList.add(new AddFieldResource("company", "N",
		 * "N", "")); addRecordFieldList.add(new AddFieldResource("division", "N", "N",
		 * ""));
		 * 
		 * List<EditFieldResource> editRecordFieldList = new ArrayList();
		 * editRecordFieldList.add(new EditFieldResource("id", "Y", "N"));
		 * editRecordFieldList.add(new EditFieldResource("busName", "Y", "N"));
		 * editRecordFieldList.add(new EditFieldResource("locnNbr", "Y", "N"));
		 * editRecordFieldList.add(new EditFieldResource("orderNbr", "Y", "N"));
		 * editRecordFieldList.add(new EditFieldResource("company", "N", "N"));
		 * editRecordFieldList.add(new EditFieldResource("division", "N", "N"));
		 */
		/*
		 * List<ViewFieldResource> viewRecordFieldList = new ArrayList();
		 * viewRecordFieldList.add(new ViewFieldResource("id"));
		 * viewRecordFieldList.add(new ViewFieldResource("busName"));
		 * viewRecordFieldList.add(new ViewFieldResource("locnNbr"));
		 * viewRecordFieldList.add(new ViewFieldResource("orderNbr"));
		 * viewRecordFieldList.add(new ViewFieldResource("company"));
		 * viewRecordFieldList.add(new ViewFieldResource("division"));
		 */
		List<ViewEditFieldResource> viewRecordFieldList = new ArrayList();
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("id"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("busName"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("locnNbr"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("orderNbr"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("company"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("division"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("busUnit"));

		List<SearchFieldResource> searchFieldList = new ArrayList();
		searchFieldList.add(SearchFieldResource.createHiddenFieldWithDefaultValue("busName",busName));
		searchFieldList.add(SearchFieldResource.createHiddenFieldWithDefaultValue("locnNbr",locnNbr.toString()));
		searchFieldList.add(SearchFieldResource.createSearchField("orderNbr"));
		searchFieldList.add(SearchFieldResource.createSearchField("batchNbr"));
		
		OrderDataResource.setFieldList(fieldList);
		OrderDataResource.setSearchFieldList(searchFieldList);
		OrderDataResource.setListFields("id,orderNbr,batchNbr,busName,locnNbr,company,division,busUnit");
		// OrderDataResource.setAddRecordFields("busName,locnNbr,orderNbr,company,division");
		// OrderDataResource.setEditRecordFields("id,busName,locnNbr,orderNbr,company,division");
		// OrderDataResource.setViewFields("id,busName,locnNbr,orderNbr,company,division");
		OrderDataResource.setViewResourceFieldList(viewRecordFieldList);
		OrderDataResource.setRecordActionList(Arrays.asList(startFullfillmentAction));
		OrdersScreen.setDataResource(OrderDataResource);
		OrderDataResource.setDtlResources(Arrays.asList(getOrderDetailResource(busName, locnNbr)));
		OrderDataResource.setPrimaryDtlResource(getOrderDetailResource(busName, locnNbr));
		return OrdersScreen;

	}

	private static DataResource getOrderDetailResource(String busName, Integer locnNbr) {
		String searchUrl = "";
		String custOrderDtlListUrl = "https://the3dsoft.com/orderplanner/v1/" + busName + "/" + locnNbr
				+ "/order/{orderId}/dtl";
		String custOrderDtlRecordUrl = "https://the3dsoft.com/orderplanner/v1/" + busName + "/" + locnNbr
				+ "/order/{orderId}/dtl/{dtlId}";
		String custOrderDtlAddRecordUrl = "https://the3dsoft.com/orderplanner/v1/" + busName + "/" + locnNbr
				+ "/order/{orderId}/dtl";
		String custOrderDtlupdateRecordUrl = "https://the3dsoft.com/orderplanner/v1/" + busName + "/" + locnNbr
				+ "/order/{orderId}/dtl/{dtlId}";
		String custOrderDtldeleteRecordUrl = "https://the3dsoft.com/orderplanner/v1/" + busName + "/" + locnNbr
				+ "/order/{orderId}/dtl/{dtlId}";
		DataResource orderDtlResource = new DataResource("OrderDtl", "Order Details", searchUrl, custOrderDtlListUrl,
				custOrderDtlAddRecordUrl, custOrderDtlupdateRecordUrl, custOrderDtldeleteRecordUrl);

		List<FieldResource> fieldList = new ArrayList();
		fieldList.add(FieldResource.createPrimaryField("id", "id", "int", "10"));
		fieldList.add(FieldResource.createField("orderId", "Order Id", "int", "10"));
		fieldList.add(FieldResource.createField("orderLineNbr", "Line Nbr", "int", "2"));
		fieldList.add(FieldResource.createField("company", "Company", "string", "15"));
		fieldList.add(FieldResource.createField("division", "Division", "string", "10"));
		fieldList.add(FieldResource.createField("orderNbr", "Order Nbr", "string", "10"));
		fieldList.add(FieldResource.createField("itemBrcd", "UPC", "string", "20"));
		fieldList.add(FieldResource.createField("origOrderQty", "Orig Order Qty", "int", "4"));
		fieldList.add(FieldResource.createField("orderQty", "Order Qty", "int", "4"));
		fieldList.add(FieldResource.createField("allocatedQty", "Allocated Qty", "int", "4"));
		fieldList.add(FieldResource.createField("pickedQty", "Picked Qty", "int", "4"));
		fieldList.add(FieldResource.createField("packedQty", "Packed Qty", "int", "4"));
		fieldList.add(FieldResource.createField("shippedQty", "Shipped Qty", "int", "4"));

		/*
		 * List<AddFieldResource> addRecordFieldList = new ArrayList();
		 * addRecordFieldList.add(new AddFieldResource("origOrderQty", "N", "N", ""));
		 * addRecordFieldList.add(new AddFieldResource("orderQty", "N", "N", ""));
		 * addRecordFieldList.add(new AddFieldResource("allocatedQty", "N", "N", ""));
		 * addRecordFieldList.add(new AddFieldResource("pickedQty", "N", "N", ""));
		 * addRecordFieldList.add(new AddFieldResource("packedQty", "N", "N", ""));
		 * addRecordFieldList.add(new AddFieldResource("shippedQty", "N", "N", ""));
		 * 
		 * List<EditFieldResource> editRecordFieldList = new ArrayList();
		 * editRecordFieldList.add(new EditFieldResource("id", "Y", "N"));
		 * editRecordFieldList.add(new EditFieldResource("busName", "Y", "N"));
		 * editRecordFieldList.add(new EditFieldResource("locnNbr", "Y", "N"));
		 * editRecordFieldList.add(new EditFieldResource("orderNbr", "Y", "N"));
		 * editRecordFieldList.add(new EditFieldResource("orderId", "Y", "N"));
		 * editRecordFieldList.add(new EditFieldResource("orderLineNbr", "Y", "N"));
		 * editRecordFieldList.add(new EditFieldResource("orderQty", "N", "N"));
		 */
		List<ViewEditFieldResource> viewRecordFieldList = new ArrayList();
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("id"));
		viewRecordFieldList.add(ViewEditFieldResource.createHiddenField("busName"));
		viewRecordFieldList.add(ViewEditFieldResource.createHiddenField("locnNbr"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("orderNbr"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("orderId"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("orderLineNbr"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("itemBrcd"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("orderQty"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("allocatedQty"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("pickedQty"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("packedQty"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("shippedQty"));
		orderDtlResource.setFieldList(fieldList);
		// orderDtlResource.setSearchFieldList(searchFieldList);
		orderDtlResource.setListFields(
				"id,orderId,orderNbr,orderLineNbr,origOrderQty, orderQty, allocatedQty, pickedQty, packedQty, shippedQty");
/*		orderDtlResource.setViewFields(
				"id,orderId,orderNbr,orderLineNbr,origOrderQty, orderQty, allocatedQty, pickedQty, packedQty, shippedQty");
*/		
		orderDtlResource.setViewResourceFieldList(viewRecordFieldList);
		orderDtlResource.setHdrDisplayFields("id,orderNbr,company,division,busUnit,orderDttm,shipByDttm");
		return orderDtlResource;
	}

}
