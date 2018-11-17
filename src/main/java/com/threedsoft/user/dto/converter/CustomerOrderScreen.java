package com.threedsoft.user.dto.converter;

import java.util.ArrayList;
import java.util.List;

import com.threedsoft.user.dto.responses.FieldResource;
import com.threedsoft.user.dto.responses.RecordResource;
import com.threedsoft.user.dto.responses.ScreenResource;
import com.threedsoft.user.util.UserConstants;

public class CustomerOrderScreen {
	public static ScreenResource createCustomerOrdersScreen(String busName, Integer locnNbr) {
		ScreenResource customerOrdersScreen = new ScreenResource("CustomerOrderMaintenance",
				"Customer Order Maintenance", "Customer Orders", "RW", UserConstants.MAINTENANCE_SCREEN, null, null);
		String searchUrl = "https://the3dsoft.com/customer-orders/v1/" + busName + "/" + locnNbr + "/order/search";
		String custOrdersListUrl = "https://the3dsoft.com/customer-orders/v1/" + busName + "/" + locnNbr + "/order";
		String custOrdersRecordUrl = "https://the3dsoft.com/customer-orders/v1/" + busName + "/" + locnNbr
				+ "/order/{id}";
		String custOrdersAddRecordUrl = "https://the3dsoft.com/customer-orders/v1/" + busName + "/" + locnNbr
				+ "/order";
		String custOrdersUpdateRecordUrl = "https://the3dsoft.com/customer-orders/v1/" + busName + "/" + locnNbr
				+ "/order" + "/{id}";
		String custOrdersDeleteRecordUrl = "https://the3dsoft.com/customer-orders/v1/" + busName + "/" + locnNbr
				+ "/order" + "/{id}";
		RecordResource customerOrderHdrResource = new RecordResource("Customer Orders", "Customer Orders", searchUrl,
				custOrdersListUrl, custOrdersRecordUrl, custOrdersAddRecordUrl, custOrdersUpdateRecordUrl,
				custOrdersDeleteRecordUrl, null, null, null, null, null, null,null);

		List<FieldResource> fieldList = new ArrayList();
		fieldList.add(new FieldResource("id", "id", "Y", "N", "int", "10", "", "list,detail,add,update"));
		fieldList.add(new FieldResource("busName", "Bus Name", "Y", "N", "string", "25", "", "list,detail,add,update","",busName));
		fieldList.add(new FieldResource("locnNbr", "Locn Nbr", "Y", "N", "int", "10", "", "list,detail,add,update","",String.valueOf(locnNbr)));
		fieldList.add(new FieldResource("company", "Company", "Y", "N", "string", "15", "", "list,detail,add,update"));
		fieldList
				.add(new FieldResource("division", "Division", "Y", "N", "string", "10", "", "list,detail,add,update"));
		fieldList.add(
				new FieldResource("orderNbr", "Order Nbr", "Y", "N", "string", "10", "", "list,detail,add,update"));
		fieldList.add(new FieldResource("batchNbr", "Batch Nbr", "N", "N", "string", "25", "", "detail,add,update"));
		fieldList.add(
				new FieldResource("orderDttm", "Order Date/Time", "Y", "N", "datetime", "20", "", "detail,add,update"));
		fieldList.add(
				new FieldResource("deliveryDttm", "Delivery Dttm", "N", "N", "date", "20", "", "detail,add,update"));
		fieldList.add(
				new FieldResource("deliveryType", "Delivery Type", "N", "N", "string", "1", "", "detail,add,update"));

		List<String> searchFieldList = new ArrayList();
		searchFieldList.add("orderNbr");
		searchFieldList.add("batchNbr");
		customerOrderHdrResource.setFieldList(fieldList);
		customerOrderHdrResource.setSearchFieldList(searchFieldList);
		customerOrdersScreen.setHdrResource(customerOrderHdrResource);
		List<RecordResource> dtlResources = new ArrayList();
		dtlResources.add(getCustomerOrderDetailResource(busName, locnNbr));
		customerOrdersScreen.setDtlResources(dtlResources);
		return customerOrdersScreen;

	}

	private static RecordResource getCustomerOrderDetailResource(String busName, Integer locnNbr) {
		String searchUrl = "";
		String custOrderDtlListUrl = "https://the3dsoft.com/customer-orders/v1/" + busName + "/" + locnNbr
				+ "/order/{id}/dtl";
		String custOrderDtlRecordUrl = "https://the3dsoft.com/customer-orders/v1/" + busName + "/" + locnNbr
				+ "/order/{id}/dtl/{dtlId}";
		String custOrderDtlAddRecordUrl = "https://the3dsoft.com/customer-orders/v1/" + busName + "/" + locnNbr
				+ "/order/{id}/dtl";
		String custOrderDtlupdateRecordUrl = "https://the3dsoft.com/customer-orders/v1/" + busName + "/" + locnNbr
				+ "/order/{id}/dtl/{dtlId}";
		String custOrderDtldeleteRecordUrl = "https://the3dsoft.com/customer-orders/v1/" + busName + "/" + locnNbr
				+ "/order/{id}/dtl/{dtlId}";
		RecordResource orderDtlResource = new RecordResource("CustomerOrderDtl", "Customer Order Details", searchUrl,
				custOrderDtlListUrl, custOrderDtlRecordUrl, custOrderDtlAddRecordUrl, custOrderDtlupdateRecordUrl,
				custOrderDtldeleteRecordUrl, null, null, null, null, null, null,null);

		List<FieldResource> fieldList = new ArrayList();
		fieldList.add(new FieldResource("id", "id", "Y", "N", "int", "10", "", "list,detail,add,update"));
		fieldList.add(new FieldResource("orderId", "Order Id", "Y", "N", "int", "10", "", "list,detail,add,update"));
		fieldList
				.add(new FieldResource("orderLineNbr", "Line Nbr", "Y", "N", "int", "2", "", "list,detail,add,update"));
		fieldList.add(new FieldResource("company", "Company", "Y", "N", "string", "15", "", "list,detail,add,update"));
		fieldList
				.add(new FieldResource("division", "Division", "Y", "N", "string", "10", "", "list,detail,add,update"));
		fieldList.add(
				new FieldResource("orderNbr", "Order Nbr", "Y", "N", "string", "10", "", "list,detail,add,update"));
		fieldList.add(new FieldResource("origOrderQty", "Orig Order Qty", "N", "N", "int", "4", "",
				"list,detail,add,update"));
		fieldList.add(new FieldResource("orderQty", "Qty", "Y", "N", "int", "4", "", "list,detail,add,update"));

		List<String> searchFieldList = new ArrayList();
		orderDtlResource.setFieldList(fieldList);
		orderDtlResource.setSearchFieldList(searchFieldList);
		return orderDtlResource;
	}


}
