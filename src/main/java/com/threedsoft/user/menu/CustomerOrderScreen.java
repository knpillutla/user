package com.threedsoft.user.menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.threedsoft.user.UserApplicationProperties;
import com.threedsoft.user.dto.responses.ActionResource;
import com.threedsoft.user.dto.responses.AddFieldResource;
import com.threedsoft.user.dto.responses.DataResource;
import com.threedsoft.user.dto.responses.FieldResource;
import com.threedsoft.user.dto.responses.ScreenResource;
import com.threedsoft.user.dto.responses.ViewEditFieldResource;
import com.threedsoft.user.util.UserConstants;

public class CustomerOrderScreen {
	public static ScreenResource createCustomerOrdersScreen(String busName, Integer locnNbr) {
		ScreenResource customerOrdersScreen = new ScreenResource("CustomerOrderMaintenance",
				"Customer Order Maintenance", "Customer Orders", "RW", UserConstants.MAINTENANCE_SCREEN, null);
		String searchUrl = "https://the3dsoft.com/customer-orders/v1/" + busName + "/" + locnNbr + "/order/search";
		String custOrdersListUrl = "https://the3dsoft.com/customer-orders/v1/" + busName + "/" + locnNbr + "/order";
//	//	String custOrdersRecordUrl = "https://the3dsoft.com/customer-orders/v1/" + busName + "/" + locnNbr
		// + "/order/{id}";
		String custOrdersAddRecordUrl = "https://the3dsoft.com/customer-orders/v1/" + busName + "/" + locnNbr
				+ "/order";
		String custOrdersUpdateRecordUrl = "https://the3dsoft.com/customer-orders/v1/" + busName + "/" + locnNbr
				+ "/order" + "/{id}";
		String custOrdersDeleteRecordUrl = "https://the3dsoft.com/customer-orders/v1/" + busName + "/" + locnNbr
				+ "/order" + "/{id}";
		String packAndPrintActionUrl = "";
		String printPickListActionUrl="";
		String reprintLabelActionUrl="";
		DataResource customerOrderDataResource = new DataResource("Customer Orders", "Customer Orders", searchUrl,
				custOrdersListUrl, custOrdersAddRecordUrl, custOrdersUpdateRecordUrl, custOrdersDeleteRecordUrl);

		List<FieldResource> fieldList = new ArrayList();
		fieldList.add(FieldResource.createPrimaryField("id", "orderId","id", "int", "10"));
		fieldList.add(FieldResource.createPrimaryField("orderNbr", "orderNbr","Order Nbr", "string", "10"));
		fieldList.add(FieldResource.createPrimaryField("busName","busName","Bus Name", "string", "25"));
		fieldList.add(FieldResource.createPrimaryField("locnNbr", "locnNbr", "Locn Nbr", "int", "10"));
		fieldList.add(FieldResource.createField("company","Company", "string", "15"));
		fieldList.add(FieldResource.createField("division","Division", "string", "10"));
/*		if(UserApplicationProperties.isHomeStore.equalsIgnoreCase("Y")) {
			fieldList.add(FieldResource.createField("batchNbr","Picklist Nbr", "string", "25"));
		}else {
			fieldList.add(FieldResource.createField("batchNbr","Batch Nbr", "string", "25"));
		}
*/		fieldList.add(FieldResource.createField("batchNbr","Batch Nbr", "string", "25"));
		fieldList.add(FieldResource.createField("orderDttm","Order Date/Time", "datetime", "20"));
		fieldList.add(FieldResource.createField("deliveryDttm","Delivery Dttm", "date", "20"));
		fieldList.add(FieldResource.createField("deliveryType","Delivery Type", "string", "1"));
		ActionResource packAndPrintAction = new ActionResource();
		packAndPrintAction.setActionName("packAndPrint");
		packAndPrintAction.setActionDisplayName("PackAndPrint");
		packAndPrintAction.setActionUrl(packAndPrintActionUrl);
		packAndPrintAction.setRequestFields("busName,locnNbr,id");
		
		ActionResource printPickListAction = new ActionResource();
		printPickListAction.setActionName("printPickList");
		printPickListAction.setActionDisplayName("Print Picklist");
		printPickListAction.setActionUrl(printPickListActionUrl);
		printPickListAction.setRequestFields("busName,locnNbr,id");

		ActionResource reprintLabelAction = new ActionResource();
		reprintLabelAction.setActionName("reprintLabels");
		reprintLabelAction.setActionDisplayName("Reprint Packing/Shipping Labels");
		reprintLabelAction.setActionUrl(reprintLabelActionUrl);
		reprintLabelAction.setRequestFields("busName,locnNbr,id");
/*		List<SearchFieldResource> searchFieldList = new ArrayList();
		searchFieldList.add(new SearchFieldResource("busName", "Y", "N", busName));
		searchFieldList.add(new SearchFieldResource("locnNbr", "Y", "N", locnNbr.toString()));
		searchFieldList.add(new SearchFieldResource("orderNbr", "N", "N", ""));
		searchFieldList.add(new SearchFieldResource("batchNbr", "N", "N", ""));
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
		List<AddFieldResource> addRecordFieldList = new ArrayList();
		addRecordFieldList.add(AddFieldResource.createHiddenFieldWithDefaultValue("busName",busName));
		addRecordFieldList.add(AddFieldResource.createHiddenFieldWithDefaultValue("locnNbr",locnNbr));
		addRecordFieldList.add(AddFieldResource.createTextField("orderNbr"));
		addRecordFieldList.add(AddFieldResource.createTextField("company"));
		addRecordFieldList.add(AddFieldResource.createTextField("division"));
		
		List<ViewEditFieldResource> editRecordFieldList = new ArrayList();
		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("id"));
		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("busName"));
		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("locnNbr"));
		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("orderNbr"));
		editRecordFieldList.add(ViewEditFieldResource.createTextField("company"));
		editRecordFieldList.add(ViewEditFieldResource.createTextField("division"));

		List<ViewEditFieldResource> viewRecordFieldList = new ArrayList();
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("id"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("busName"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("locnNbr"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("orderNbr"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("company"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("division"));

		customerOrderDataResource.setFieldList(fieldList);
		customerOrderDataResource.setSearchFields("busName,locnNbr,orderNbr,batchNbr");
		customerOrderDataResource.setListFields("id,orderNbr,batchNbr,busName,locnNbr,company,division");
		//customerOrderDataResource.setAddRecordFields("busName,locnNbr,orderNbr,company,division");
		//customerOrderDataResource.setEditRecordFields("id,busName,locnNbr,orderNbr,company,division");
		//customerOrderDataResource.setViewFields("id,busName,locnNbr,orderNbr,company,division");
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
		customerOrderDataResource.setDtlResources(Arrays.asList(getCustomerOrderDetailResource(busName, locnNbr)));
		customerOrdersScreen.setDataResource(customerOrderDataResource);
		customerOrderDataResource.setPrimaryDtlResource(getCustomerOrderDetailResource(busName, locnNbr));
		return customerOrdersScreen;

	}

	private static DataResource getCustomerOrderDetailResource(String busName, Integer locnNbr) {
		String searchUrl = "";
		String custOrderDtlListUrl = "https://the3dsoft.com/customer-orders/v1/" + busName + "/" + locnNbr
				+ "/order/{orderId}/dtl";
		String custOrderDtlRecordUrl = "https://the3dsoft.com/customer-orders/v1/" + busName + "/" + locnNbr
				+ "/order/{orderId}/dtl/{id}";
		String custOrderDtlAddRecordUrl = "https://the3dsoft.com/customer-orders/v1/" + busName + "/" + locnNbr
				+ "/order/{orderId}/dtl";
		String custOrderDtlupdateRecordUrl = "https://the3dsoft.com/customer-orders/v1/" + busName + "/" + locnNbr
				+ "/order/{orderId}/dtl/{id}";
		String custOrderDtldeleteRecordUrl = "https://the3dsoft.com/customer-orders/v1/" + busName + "/" + locnNbr
				+ "/order/{orderId}/dtl/{idd}";
		DataResource orderDtlResource = new DataResource("CustomerOrderDtl", "Customer Order Details", searchUrl,
				custOrderDtlListUrl, custOrderDtlAddRecordUrl, custOrderDtlupdateRecordUrl, custOrderDtldeleteRecordUrl);

		List<FieldResource> fieldList = new ArrayList();
		fieldList.add(FieldResource.createPrimaryField("id", "orderDtlId","id", "int", "10"));
		fieldList.add(FieldResource.createField("orderId", "Order Id", "int", "10"));
		fieldList.add(FieldResource.createField("orderLineNbr", "Line Nbr", "int", "2"));
		fieldList.add(FieldResource.createField("company", "Company", "string", "15"));
		fieldList.add(FieldResource.createField("division", "Division", "string", "10"));
		fieldList.add(FieldResource.createField("orderNbr", "Order Nbr", "string", "10"));
		fieldList.add(FieldResource.createField("origOrderQty", "Orig Order Qty", "int", "4"));
		fieldList.add(FieldResource.createField("orderQty", "Order Qty", "int", "4"));
		fieldList.add(FieldResource.createField("allocatedQty", "Allocated Qty", "int", "4"));
		fieldList.add(FieldResource.createField("pickedQty", "Picked Qty", "int", "4"));
		fieldList.add(FieldResource.createField("packedQty", "Packed Qty", "int", "4"));
		fieldList.add(FieldResource.createField("shippedQty", "Shipped Qty", "int", "4"));

		List<AddFieldResource> addRecordFieldList = new ArrayList();
		addRecordFieldList.add(AddFieldResource.createHiddenFieldWithDefaultValue("busName",busName));
		addRecordFieldList.add(AddFieldResource.createHiddenFieldWithDefaultValue("locnNbr",locnNbr));
		addRecordFieldList.add(AddFieldResource.createDisabledFieldWithDefaultValue("orderId", "{orderId}"));
		addRecordFieldList.add(AddFieldResource.createTextField("origOrderQty"));
		addRecordFieldList.add(AddFieldResource.createTextField("orderQty"));
		addRecordFieldList.add(AddFieldResource.createTextField("allocatedQty"));
		addRecordFieldList.add(AddFieldResource.createTextField("pickedQty"));
		addRecordFieldList.add(AddFieldResource.createTextField("packedQty"));
		addRecordFieldList.add(AddFieldResource.createTextField("shippedQty"));

		List<ViewEditFieldResource> editRecordFieldList = new ArrayList();
		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("id"));
		editRecordFieldList.add(ViewEditFieldResource.createHiddenField("busName"));
		editRecordFieldList.add(ViewEditFieldResource.createHiddenField("locnNbr"));
		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("orderNbr"));
		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("orderId"));
		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("orderLineNbr"));
		editRecordFieldList.add(ViewEditFieldResource.createTextField("orderQty"));

		List<ViewEditFieldResource> viewRecordFieldList = new ArrayList();
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("id"));
		viewRecordFieldList.add(ViewEditFieldResource.createHiddenField("busName"));
		viewRecordFieldList.add(ViewEditFieldResource.createHiddenField("locnNbr"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("orderNbr"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("orderId"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("orderLineNbr"));
		viewRecordFieldList.add(ViewEditFieldResource.createDisabledField("orderQty"));

		orderDtlResource.setFieldList(fieldList);
		//orderDtlResource.setSearchFieldList(searchFieldList);
		orderDtlResource.setListFields("id,orderId,orderNbr,orderLineNbr,origOrderQty, orderQty, allocatedQty, pickedQty, packedQty, shippedQty");
		//orderDtlResource.setAddRecordFields("origOrderQty,orderQty,allocatedQty,pickedQty,packedQty,shippedQty");
		//orderDtlResource.setEditRecordFields("id,busName,locnNbr,orderNbr,orderId,orderLineNbr,orderQy");
		//orderDtlResource.setViewFields("id,orderId,orderNbr,orderLineNbr,origOrderQty, orderQty, allocatedQty, pickedQty, packedQty, shippedQty");
		orderDtlResource.setAddResourceFieldList(addRecordFieldList);
		orderDtlResource.setEditResourceFieldList(editRecordFieldList);
		orderDtlResource.setViewResourceFieldList(viewRecordFieldList);
		return orderDtlResource;
	}

}
