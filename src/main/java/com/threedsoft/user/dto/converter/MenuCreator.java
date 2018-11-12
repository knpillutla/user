package com.threedsoft.user.dto.converter;

import java.util.ArrayList;
import java.util.List;

import com.threedsoft.user.dto.responses.FieldResource;
import com.threedsoft.user.dto.responses.MenuResource;
import com.threedsoft.user.dto.responses.RecordResource;
import com.threedsoft.user.dto.responses.ScreenResource;
import com.threedsoft.user.util.UserConstants;

public class MenuCreator {
	
	public List<MenuResource> createWMSMenus(String busName, Integer locnNbr) {
		List<MenuResource> menuList = new ArrayList<MenuResource>();
		menuList.add(createFulfillmentMenu(busName, locnNbr));
		//menuList.add(createCustomerOrdersMenu(busName, locnNbr));
		//menuList.add(createInventoryMenu(busName, locnNbr));
		menuList.add(createRFMenu(busName, locnNbr));
		return menuList;
	}
	
	private MenuResource createFulfillmentMenu(String busName, Integer locnNbr) {
		MenuResource inventoryMenu = new MenuResource();
		inventoryMenu.setMenuName("Fullfillment Maintenance");
		List<ScreenResource> screenResourceList = new ArrayList();
		screenResourceList.add(createCustomerOrdersScreen(busName, locnNbr));
		//screenResourceList.add(orderPlannerScreen);
		screenResourceList.add(createInventoryScreen(busName, locnNbr));
		screenResourceList.add(createPickingScreen(busName, locnNbr));
		screenResourceList.add(createPackingScreen(busName, locnNbr));
		inventoryMenu.setScreenResourceList(screenResourceList);
		return inventoryMenu;
	}
	
	private MenuResource createCustomerOrdersMenu(String busName, Integer locnNbr) {
		MenuResource inventoryMenu = new MenuResource();
		inventoryMenu.setMenuName("Customer Orders");
		List<ScreenResource> screenResourceList = new ArrayList();
		screenResourceList.add(createCustomerOrdersScreen(busName, locnNbr));
		inventoryMenu.setScreenResourceList(screenResourceList);
		return inventoryMenu;
	}
	
	private MenuResource createInventoryMenu(String busName, Integer locnNbr) {
		MenuResource inventoryMenu = new MenuResource();
		inventoryMenu.setMenuName("Inventory");
		List<ScreenResource> screenResourceList = new ArrayList();
		screenResourceList.add(createInventoryScreen(busName, locnNbr));
		inventoryMenu.setScreenResourceList(screenResourceList);
		return inventoryMenu;
	}
	
	private MenuResource createRFMenu(String busName, Integer locnNbr) {
		MenuResource rfenu = new MenuResource();
		rfenu.setMenuName("RF Menu");
		List<ScreenResource> screenResourceList = new ArrayList();
		screenResourceList.add(createSinglesRFPickScreen(busName, locnNbr));
		screenResourceList.add(createRFPackScreen(busName, locnNbr));
		rfenu.setScreenResourceList(screenResourceList);
		return rfenu;
	}

	private ScreenResource createSinglesRFPickScreen(String busName, Integer locnNbr) {
		ScreenResource rfScreen = new ScreenResource("SinglesRFPicking", "Singles RFPicking","Singles RFPicking", "RW",UserConstants.RF_SCREEN, null, null);
		String searchUrl = "";
		String listUrl = "";
		String getRecordUrl = "https://the3dsoft.com/picking/v1/"+busName+"/" + locnNbr + "/picks/next/{userId}";
		String addRecordUrl = "";
		String updateRecordUrl = "https://the3dsoft.com/picking/v1/"+busName+"/" + locnNbr + "/picks"+"/{id}";
		String deleteRecordUrl = "";
		RecordResource hdrResource = new RecordResource("RFPicking", "Pick To Tote (Singles)", searchUrl, listUrl, getRecordUrl, addRecordUrl, updateRecordUrl, deleteRecordUrl, null, null, null, null, null, null);
		
		List<FieldResource> fieldList = new ArrayList();
		fieldList.add(new FieldResource("id","Pick Id","N","N","int","10","","update"));
		fieldList.add(new FieldResource("busName","Bus Name","Y","Y","string","25","","update"));
		fieldList.add(new FieldResource("locnNbr","Locn Nbr","Y","Y","string","10","","update"));
		fieldList.add(new FieldResource("company","Company","N","N","string","15","","update"));
		fieldList.add(new FieldResource("division","Division","N","N","string","10","","update"));
		fieldList.add(new FieldResource("locnBrcd","Locn Brcd","N","N","string","20","","update"));
		fieldList.add(new FieldResource("itemBrcd","Item Brcd","N","N","string","20","","update"));
		fieldList.add(new FieldResource("qty","Qty","N","N","int","2","","update"));
		fieldList.add(new FieldResource("toteNbr","To Tote","N","N","string","20","","update"));
		
		List<String> searchFieldList = new ArrayList(); 
		hdrResource.setFieldList(fieldList);
		hdrResource.setSearchFieldList(searchFieldList);
		rfScreen.setHdrResource(hdrResource);
		return rfScreen;
	}

	private ScreenResource createRFPackScreen(String busName, Integer locnNbr) {
		ScreenResource rfScreen = new ScreenResource("RFPacking", "RFPackinge","RFPacking", "RW",UserConstants.RF_SCREEN, null, null);
		String searchUrl = "";
		String listUrl = "";
		String getRecordUrl = "https://the3dsoft.com/packing/v1/"+busName+"/" + locnNbr + "/packs"+"/{id}";
		String addRecordUrl = "";
		String updateRecordUrl = "https://the3dsoft.com/packing/v1/"+busName+"/" + locnNbr + "/packs"+"/{id}";
		String deleteRecordUrl = "";
		RecordResource hdrResource = new RecordResource("RFPacking", "Pack from Tote", searchUrl, listUrl, getRecordUrl, addRecordUrl, updateRecordUrl, deleteRecordUrl, null, null, null, null, null, null);
		
		List<FieldResource> fieldList = new ArrayList();
		fieldList.add(new FieldResource("toteNbr","From Tote","N","N","string","20","","update"));
		fieldList.add(new FieldResource("id","Pack Id","N","N","int","10","","update"));
		fieldList.add(new FieldResource("orderId","Order Id","N","N","int","10","","update"));
		fieldList.add(new FieldResource("busName","Bus Name","Y","Y","string","25","","update"));
		fieldList.add(new FieldResource("locnNbr","Locn Nbr","Y","Y","string","10","","update"));
		fieldList.add(new FieldResource("company","Company","N","N","string","15","","update"));
		fieldList.add(new FieldResource("division","Division","N","N","string","10","","update"));
		fieldList.add(new FieldResource("itemBrcd","Item Brcd","N","N","string","20","","update"));
		fieldList.add(new FieldResource("qty","Qty","N","N","int","2","","update"));
		
		List<String> searchFieldList = new ArrayList(); 
		hdrResource.setFieldList(fieldList);
		hdrResource.setSearchFieldList(searchFieldList);
		rfScreen.setHdrResource(hdrResource);
		return rfScreen;
	}

	private ScreenResource createInventoryScreen(String busName, Integer locnNbr) {
		ScreenResource inventoryScreen = new ScreenResource("InventoryMaintenance", "Inventory Maintenance","Item Inventory", "RW",UserConstants.MAINTENANCE_SCREEN, null, null);
		String searchUrl = "https://the3dsoft.com/inventory/v1/"+busName+"/" + locnNbr + "/inventory/search";
		String invnListUrl = "https://the3dsoft.com/inventory/v1/"+busName+"/" + locnNbr + "/inventory";
		String invnGetRecordUrl = "https://the3dsoft.com/inventory/v1/"+busName+"/" + locnNbr + "/inventory/{id}";
		String invnAddRecordUrl = "https://the3dsoft.com/inventory/v1/"+busName+"/" + locnNbr + "/inventory";
		String updateRecordUrl = "https://the3dsoft.com/inventory/v1/"+busName+"/" + locnNbr + "/inventory"+"/{id}";
		String deleteRecordUrl = "https://the3dsoft.com/inventory/v1/"+busName+"/" + locnNbr + "/inventory"+"/{id}";
		RecordResource inventoryHdrResource = new RecordResource("Inventory", "Inventory", searchUrl,invnListUrl, invnGetRecordUrl, invnAddRecordUrl, updateRecordUrl, deleteRecordUrl, null, null, null, null, null, null);
		
		List<FieldResource> fieldList = new ArrayList();
		fieldList.add(new FieldResource("id","id","N","N","int","10","","list,detail,add,update"));
		fieldList.add(new FieldResource("busName","Bus Name","N","N","string","25","","list,detail,add,update"));
		fieldList.add(new FieldResource("locnNbr","Locn Nbr","N","N","string","10","","list,detail,add,update"));
		fieldList.add(new FieldResource("company","Company","N","N","string","15","","list,detail,add,update"));
		fieldList.add(new FieldResource("division","Division","N","N","string","10","","list,detail,add,update"));
		fieldList.add(new FieldResource("locnBrcd","Locn Brcd","N","N","string","20","","list,detail,add,update"));
		fieldList.add(new FieldResource("itemBrcd","Item Brcd","N","N","string","20","","list,detail,add,update"));
		fieldList.add(new FieldResource("qty","Qty","N","N","int","4","","list,detail,add,update"));
		fieldList.add(new FieldResource("locked","Locked","N","N","string","1","Y,N","list,detail,add,update","dropdown"));
		fieldList.add(new FieldResource("trackByIlpn","Track By LPN?","N","N","string","1","Y,N","detail,add,update","dropdown"));
		
		List<String> searchFieldList = new ArrayList(); 
		searchFieldList.add("locnBrcd");
		searchFieldList.add("itemBrcd");
		inventoryHdrResource.setFieldList(fieldList);
		inventoryHdrResource.setSearchFieldList(searchFieldList);
		inventoryScreen.setHdrResource(inventoryHdrResource);
		return inventoryScreen;
	}

	private ScreenResource createPickingScreen(String busName, Integer locnNbr) {
		ScreenResource screen = new ScreenResource("PickingMaintenance", "Picking Maintenance","Picking Maintenance", "R",UserConstants.MAINTENANCE_SCREEN, null, null);
		String searchUrl = "https://the3dsoft.com/picking/v1/"+busName+"/" + locnNbr + "/picks/search";
		String listUrl = "https://the3dsoft.com/picking/v1/"+busName+"/" + locnNbr + "/picks";
		String getRecordUrl = "https://the3dsoft.com/picking/v1/"+busName+"/" + locnNbr + "/picks/{id}";
		String addRecordUrl = "";
		String updateRecordUrl = "https://the3dsoft.com/picking/v1/"+busName+"/" + locnNbr + "/picks"+"/{id}";
		String deleteRecordUrl = "";
		RecordResource hdrResource = new RecordResource("Picks", "Picks", searchUrl,listUrl, getRecordUrl, addRecordUrl, updateRecordUrl, deleteRecordUrl, null, null, null, null, null, null);
		
		List<FieldResource> fieldList = new ArrayList();
		fieldList.add(new FieldResource("id","id","N","N","int","10","","list,detail,add,update"));
		fieldList.add(new FieldResource("busName","Bus Name","N","N","string","25","","list,detail,add,update"));
		fieldList.add(new FieldResource("locnNbr","Locn Nbr","N","N","string","10","","list,detail,add,update"));
		fieldList.add(new FieldResource("company","Company","N","N","string","15","","list,detail,add,update"));
		fieldList.add(new FieldResource("division","Division","N","N","string","10","","list,detail,add,update"));
		fieldList.add(new FieldResource("locnBrcd","Locn Brcd","N","N","string","20","","list,detail,add,update"));
		fieldList.add(new FieldResource("itemBrcd","Item Brcd","N","N","string","20","","list,detail,add,update"));
		fieldList.add(new FieldResource("qty","Qty","N","N","int","4","","list,detail,add,update"));
		fieldList.add(new FieldResource("locked","Locked","N","N","string","1","Y,N","list,detail,add,update","dropdown"));
		fieldList.add(new FieldResource("trackByIlpn","Track By LPN?","N","N","string","1","Y,N","detail,add,update","dropdown"));
		
		List<String> searchFieldList = new ArrayList(); 
		searchFieldList.add("locnBrcd");
		searchFieldList.add("itemBrcd");
		hdrResource.setFieldList(fieldList);
		hdrResource.setSearchFieldList(searchFieldList);
		screen.setHdrResource(hdrResource);
		return screen;
	}

	private ScreenResource createPackingScreen(String busName, Integer locnNbr) {
		ScreenResource screen = new ScreenResource("PackingMaintenance", "Packing Maintenance","Packing Maintenance", "R",UserConstants.MAINTENANCE_SCREEN, null, null);
		String searchUrl = "https://the3dsoft.com/packing/v1/"+busName+"/" + locnNbr + "/packs/search";
		String listUrl = "https://the3dsoft.com/packing/v1/"+busName+"/" + locnNbr + "/packs";
		String getRecordUrl = "https://the3dsoft.com/packing/v1/"+busName+"/" + locnNbr + "/packs/{id}";
		String addRecordUrl = "";
		String updateRecordUrl = "https://the3dsoft.com/packing/v1/"+busName+"/" + locnNbr + "/packs"+"/{id}";
		String deleteRecordUrl = "";
		RecordResource hdrResource = new RecordResource("Packs", "Packs", searchUrl, listUrl, getRecordUrl, addRecordUrl, updateRecordUrl, deleteRecordUrl, null, null, null, null, null, null);
		
		List<FieldResource> fieldList = new ArrayList();
		fieldList.add(new FieldResource("id","id","N","N","int","10","","list,detail,add,update"));
		fieldList.add(new FieldResource("busName","Bus Name","N","N","string","25","","list,detail,add,update"));
		fieldList.add(new FieldResource("locnNbr","Locn Nbr","N","N","string","10","","list,detail,add,update"));
		fieldList.add(new FieldResource("company","Company","N","N","string","15","","list,detail,add,update"));
		fieldList.add(new FieldResource("division","Division","N","N","string","10","","list,detail,add,update"));
		fieldList.add(new FieldResource("locnBrcd","Locn Brcd","N","N","string","20","","list,detail,add,update"));
		fieldList.add(new FieldResource("itemBrcd","Item Brcd","N","N","string","20","","list,detail,add,update"));
		fieldList.add(new FieldResource("qty","Qty","N","N","int","4","","list,detail,add,update"));
		fieldList.add(new FieldResource("locked","Locked","N","N","string","1","Y,N","list,detail,add,update","dropdown"));
		fieldList.add(new FieldResource("trackByIlpn","Track By LPN?","N","N","string","1","Y,N","detail,add,update","dropdown"));
		
		List<String> searchFieldList = new ArrayList(); 
		searchFieldList.add("locnBrcd");
		searchFieldList.add("itemBrcd");
		hdrResource.setFieldList(fieldList);
		hdrResource.setSearchFieldList(searchFieldList);
		screen.setHdrResource(hdrResource);
		return screen;
	}
	private ScreenResource createCustomerOrdersScreen(String busName, Integer locnNbr) {
		ScreenResource customerOrdersScreen = new ScreenResource("CustomerOrderMaintenance", "Customer Order Maintenance","Customer Orders", "RW",UserConstants.MAINTENANCE_SCREEN, null, null);
		String searchUrl = "https://the3dsoft.com/customer-orders/v1/"+busName+"/" + locnNbr + "/order/search";
		String custOrdersListUrl = "https://the3dsoft.com/customer-orders/v1/"+busName+"/" + locnNbr + "/order";
		String custOrdersRecordUrl = "https://the3dsoft.com/customer-orders/v1/"+busName+"/" + locnNbr + "/order/{id}";
		String custOrdersAddRecordUrl = "https://the3dsoft.com/customer-orders/v1/"+busName+"/" + locnNbr + "/order";
		String custOrdersUpdateRecordUrl = "https://the3dsoft.com/customer-orders/v1/"+busName+"/" + locnNbr + "/order"+"/{id}";
		String custOrdersDeleteRecordUrl = "https://the3dsoft.com/customer-orders/v1/"+busName+"/" + locnNbr + "/order"+"/{id}";
		RecordResource customerOrderHdrResource = new RecordResource("Customer Orders", "Customer Orders", searchUrl, custOrdersListUrl, custOrdersRecordUrl, custOrdersAddRecordUrl, custOrdersUpdateRecordUrl, custOrdersDeleteRecordUrl, null, null, null, null, null, null);
		
		List<FieldResource> fieldList = new ArrayList();
		fieldList.add(new FieldResource("id","id","Y","N","int","10","","list,detail,add,update"));
		fieldList.add(new FieldResource("busName","Bus Name","Y","N","string","25","","list,detail,add,update"));
		fieldList.add(new FieldResource("locnNbr","Locn Nbr","Y","N","int","10","","list,detail,add,update"));
		fieldList.add(new FieldResource("company","Company","Y","N","string","15","","list,detail,add,update"));
		fieldList.add(new FieldResource("division","Division","Y","N","string","10","","list,detail,add,update"));
		fieldList.add(new FieldResource("orderNbr","Order Nbr","Y","N","string","10","","list,detail,add,update"));
		fieldList.add(new FieldResource("batchNbr","Batch Nbr","N","N","string","25","","detail,add,update"));
		fieldList.add(new FieldResource("orderDttm","Order Date/Time","Y","N","datetime","20","","detail,add,update"));
		fieldList.add(new FieldResource("deliveryDttm","Delivery Dttm","N","N","date","20","","detail,add,update"));
		fieldList.add(new FieldResource("deliveryType","Delivery Type","N","N","string","1","","detail,add,update"));

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
	public RecordResource getCustomerOrderDetailResource(String busName, Integer locnNbr) {
		String searchUrl = "";
		String custOrderDtlListUrl = "https://the3dsoft.com/customer-orders/v1/"+busName+"/" + locnNbr + "/order/{id}/dtl";
		String custOrderDtlRecordUrl = "https://the3dsoft.com/customer-orders/v1/"+busName+"/" + locnNbr + "/order/{id}/dtl/{dtlId}";
		String custOrderDtlAddRecordUrl = "https://the3dsoft.com/customer-orders/v1/"+busName+"/" + locnNbr + "/order/{id}/dtl";
		String custOrderDtlupdateRecordUrl = "https://the3dsoft.com/customer-orders/v1/"+busName+"/" + locnNbr + "/order/{id}/dtl/{dtlId}";
		String custOrderDtldeleteRecordUrl = "https://the3dsoft.com/customer-orders/v1/"+busName+"/" + locnNbr + "/order/{id}/dtl/{dtlId}";
		RecordResource orderDtlResource = new RecordResource("CustomerOrderDtl", "Customer Order Details", searchUrl,custOrderDtlListUrl, custOrderDtlRecordUrl, custOrderDtlAddRecordUrl,custOrderDtlupdateRecordUrl, custOrderDtldeleteRecordUrl, null, null, null, null, null, null);
		
		List<FieldResource> fieldList = new ArrayList();
		fieldList.add(new FieldResource("id","id","Y","N","int","10","","list,detail,add,update"));
		fieldList.add(new FieldResource("orderId","Order Id","Y","N","int","10","","list,detail,add,update"));
		fieldList.add(new FieldResource("orderLineNbr","Line Nbr","Y","N","int","2","","list,detail,add,update"));
		fieldList.add(new FieldResource("company","Company","Y","N","string","15","","list,detail,add,update"));
		fieldList.add(new FieldResource("division","Division","Y","N","string","10","","list,detail,add,update"));
		fieldList.add(new FieldResource("orderNbr","Order Nbr","Y","N","string","10","","list,detail,add,update"));
		fieldList.add(new FieldResource("origOrderQty","Orig Order Qty","N","N","int","4","","list,detail,add,update"));
		fieldList.add(new FieldResource("orderQty","Qty","Y","N","int","4","","list,detail,add,update"));

		List<String> searchFieldList = new ArrayList(); 
		orderDtlResource.setFieldList(fieldList);
		orderDtlResource.setSearchFieldList(searchFieldList);
		return orderDtlResource;
	}
	
}
