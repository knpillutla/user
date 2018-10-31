package com.threedsoft.user.dto.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.threedsoft.user.db.User;
import com.threedsoft.user.dto.requests.UserCreationRequestDTO;
import com.threedsoft.user.dto.responses.FieldResource;
import com.threedsoft.user.dto.responses.MenuResource;
import com.threedsoft.user.dto.responses.RecordResource;
import com.threedsoft.user.dto.responses.ScreenResource;
import com.threedsoft.user.dto.responses.UserResourceDTO;
import com.threedsoft.user.util.UserConstants;

@Component
public class UserDTOConverter {

	public User getUserEntity(UserCreationRequestDTO userCreationReq) {
		User userEntity = new User();
		userEntity.setBusName(userCreationReq.getBusName());
		userEntity.setFirstName(userCreationReq.getFirstName());
		userEntity.setMiddleName(userCreationReq.getMiddleName());
		userEntity.setLastName(userCreationReq.getLastName());
		userEntity.setUserName(userCreationReq.getUserName());
		userEntity.setCreatedBy(userCreationReq.getUserId());
		userEntity.setUpdatedBy(userCreationReq.getUserId());
		if(userCreationReq.getAuthType()== null || userCreationReq.getAuthType().trim().equals("")) {
			userEntity.setAuthType(UserConstants.USER_AUTH_TYPE);
		}
		else
		if(userCreationReq.getAuthType().toLowerCase().contains("google")) {
			userEntity.setAuthType(UserConstants.GOOGLE_AUTH_TYPE);	
		}
		else
		if(userCreationReq.getAuthType().toLowerCase().contains("face")) {
			userEntity.setAuthType(UserConstants.FACEBOOK_AUTH_TYPE);	
		}
		else {
			userEntity.setAuthType(UserConstants.USER_AUTH_TYPE);
		}
		userEntity.setAuthToken(userCreationReq.getAuthToken());
		userEntity.setAddr1(userCreationReq.getAddr1());
		userEntity.setAddr2(userCreationReq.getAddr2());
		userEntity.setAddr3(userCreationReq.getAddr3());
		userEntity.setCity(userCreationReq.getCity());
		userEntity.setState(userCreationReq.getState());
		userEntity.setCountry(userCreationReq.getCountry());
		userEntity.setLocale(userCreationReq.getLocale());
		userEntity.setZipCode(userCreationReq.getZipCode());
		userEntity.setDefLocnNbr(userCreationReq.getDefLocnNbr());

		userEntity.setCreatedBy(userCreationReq.getUserId());
		userEntity.setUpdatedBy(userCreationReq.getUserId());
		return userEntity;
	}

	public UserResourceDTO getUserResourceDTO(User userEntity) {
		if (userEntity != null) {
			UserResourceDTO userResourceDTO = new UserResourceDTO(userEntity.getId(), userEntity.getBusName(),
					userEntity.getDefLocnNbr(), userEntity.getUserName(), userEntity.getAuthType(),
					userEntity.getFirstName(), userEntity.getLastName(), userEntity.getMiddleName(),
					userEntity.getRoleName(), userEntity.getAddr1(), userEntity.getAddr2(), userEntity.getAddr3(),
					userEntity.getCity(), userEntity.getState(), userEntity.getCountry(), userEntity.getZipCode(),
					userEntity.getLocale(), userEntity.getCreatedBy(), null);
			userResourceDTO.setMenuResourceList(new ArrayList<MenuResource>());
			userResourceDTO.getMenuResourceList().add(createCustomerOrdersMenu(userResourceDTO.getBusName(), userResourceDTO.getDefLocnNbr()));
			userResourceDTO.getMenuResourceList().add(createInventoryMenu(userResourceDTO.getBusName(), userResourceDTO.getDefLocnNbr()));
			userResourceDTO.getMenuResourceList().add(createFulfillmentMenu(userResourceDTO.getBusName(), userResourceDTO.getDefLocnNbr()));
			return userResourceDTO;
		}
		return null;
	}

	private MenuResource createFulfillmentMenu(String busName, Integer locnNbr) {
		MenuResource inventoryMenu = new MenuResource();
		inventoryMenu.setMenuName("Inventory");
		List<ScreenResource> screenResourceList = new ArrayList();
		screenResourceList.add(createCustomerOrdersScreen(busName, locnNbr));
		//screenResourceList.add(orderPlannerScreen);
		screenResourceList.add(createInventoryScreen(busName, locnNbr));
		inventoryMenu.setScreenResourceList(screenResourceList);
		return inventoryMenu;
	}

	
/*	private MenuResource createSampleOrderMenu(String busName, Integer locnNbr) {
		MenuResource orderMenu = new MenuResource();
		orderMenu.setMenuName("Orders");
		List<ScreenResource> screenResourceList = new ArrayList();

		ScreenResource orderPlannerScreen = new ScreenResource("InventoryMaintenance", "Inventory Maintenance","Item Inventory", "RW", null, null);
		String invnListUrl = "https://orderplanner.the3dsoft.com/orderplanner/v1/"+busName+"/" + locnNbr + "/order";
		String invnGetRecordUrl = "https://inventory.the3dsoft.com/orderplanner/v1/"+busName+"/" + locnNbr + "/order/{id}";
		String invnAddRecordUrl = "https://inventory.the3dsoft.com/orderplanner/v1/"+busName+"/" + locnNbr + "/order";
		String updateRecordUrl = "https://inventory.the3dsoft.com/orderplanner/v1/"+busName+"/" + locnNbr + "/order"+"/{id}";
		String deleteRecordUrl = "https://inventory.the3dsoft.com/orderplanner/v1/"+busName+"/" + locnNbr + "/order"+"/{id}";
		RecordResource orderHdrResource = new RecordResource("Orderplanner", "Planned Orders", invnListUrl, invnGetRecordUrl, invnAddRecordUrl, updateRecordUrl, deleteRecordUrl, null, null, null, null, null);
		
		List<FieldResource> searchFieldList = new ArrayList(); 
		searchFieldList.add(new FieldResource("orderNbr", "Order Nbr", "N","N","string","10",""));
		searchFieldList.add(new FieldResource("batchNbr","Batch Nbr", "N","N","string","10", ""));
		
		List<FieldResource> listDisplayFieldList = new ArrayList();
		listDisplayFieldList.add(new FieldResource("id","id","Y","N","int","0",""));
		listDisplayFieldList.add(new FieldResource("busName","Company","Y","N","int","0",""));
		listDisplayFieldList.add(new FieldResource("locnNbr","Division","Y","N","int","0",""));
		listDisplayFieldList.add(new FieldResource("company","Company","Y","N","int","0",""));
		listDisplayFieldList.add(new FieldResource("division","Division","Y","N","int","0",""));
		listDisplayFieldList.add(new FieldResource("orderNbr","Company","Y","N","int","0",""));
		listDisplayFieldList.add(new FieldResource("batchNbr","Division","N","N","int","0",""));
		listDisplayFieldList.add(new FieldResource("orderDttm","Order Date/Time","Y","N","string","20",""));
		listDisplayFieldList.add(new FieldResource("deliveryDttm","deliveryDttm","N","N","string","20",""));
		listDisplayFieldList.add(new FieldResource("deliveryType","Delivery Type","N","N","int","0",""));
		
		orderHdrResource.setSearchFieldList(searchFieldList);
		orderHdrResource.setListDisplayFieldList(listDisplayFieldList);
		orderHdrResource.setAddRecordFieldList(listDisplayFieldList);
		orderHdrResource.setUpdateRecordFieldList(listDisplayFieldList);
		orderPlannerScreen.setHdrResource(orderHdrResource);
		List<RecordResource> dtlResources = new ArrayList();
		dtlResources.add(getOrderDetailResource(busName, locnNbr));
		orderPlannerScreen.setDtlResources(dtlResources);
		screenResourceList.add(orderPlannerScreen);
		orderMenu.setScreenResourceList(screenResourceList);
		return orderMenu;
	
	}*/
	
	public MenuResource createCustomerOrdersMenu(String busName, Integer locnNbr) {
		MenuResource inventoryMenu = new MenuResource();
		inventoryMenu.setMenuName("Customer Orders");
		List<ScreenResource> screenResourceList = new ArrayList();
		screenResourceList.add(createCustomerOrdersScreen(busName, locnNbr));
		inventoryMenu.setScreenResourceList(screenResourceList);
		return inventoryMenu;
	}
	
	public MenuResource createInventoryMenu(String busName, Integer locnNbr) {
		MenuResource inventoryMenu = new MenuResource();
		inventoryMenu.setMenuName("Inventory");
		List<ScreenResource> screenResourceList = new ArrayList();
		screenResourceList.add(createInventoryScreen(busName, locnNbr));
		inventoryMenu.setScreenResourceList(screenResourceList);
		return inventoryMenu;
	}
	
	
	private MenuResource createSampleOrderMenu(String busName, Integer locnNbr) {
		MenuResource orderMenu = new MenuResource();
		orderMenu.setMenuName("Orders");
		List<ScreenResource> screenResourceList = new ArrayList();

		ScreenResource orderPlannerScreen = new ScreenResource("OrderPlannerMaintenance", "Order Planner Maintenance","Order Planning", "RW", null, null);
		String invnListUrl = "https://orderplanner.the3dsoft.com/orderplanner/v1/"+busName+"/" + locnNbr + "/order";
		String invnGetRecordUrl = "https://orderplanner.the3dsoft.com/orderplanner/v1/"+busName+"/" + locnNbr + "/order/{id}";
		String invnAddRecordUrl = "https://orderplanner.the3dsoft.com/orderplanner/v1/"+busName+"/" + locnNbr + "/order";
		String updateRecordUrl = "https://orderplanner.the3dsoft.com/orderplanner/v1/"+busName+"/" + locnNbr + "/order"+"/{id}";
		String deleteRecordUrl = "https://orderplanner.the3dsoft.com/orderplanner/v1/"+busName+"/" + locnNbr + "/order"+"/{id}";
		RecordResource orderHdrResource = new RecordResource("Orderplanner", "Planned Orders", invnListUrl, invnGetRecordUrl, invnAddRecordUrl, updateRecordUrl, deleteRecordUrl, null, null, null, null, null, null);
		
		List<FieldResource> fieldList = new ArrayList();
		fieldList.add(new FieldResource("id","id","Y","N","int","0","","list,detail,add,update"));
		fieldList.add(new FieldResource("busName","Company","Y","N","int","0","","list,detail,add,update"));
		fieldList.add(new FieldResource("locnNbr","Division","Y","N","int","0","","list,detail,add,update"));
		fieldList.add(new FieldResource("company","Company","Y","N","int","0","","list,detail,add,update"));
		fieldList.add(new FieldResource("division","Division","Y","N","int","0","","list,detail,add,update"));
		fieldList.add(new FieldResource("orderNbr","Company","Y","N","int","0","","list,detail,add,update"));
		fieldList.add(new FieldResource("batchNbr","Division","N","N","int","0","","list,detail,add,update"));
		fieldList.add(new FieldResource("orderDttm","Order Date/Time","Y","N","string","20","","list,detail,add,update"));
		fieldList.add(new FieldResource("deliveryDttm","deliveryDttm","N","N","string","20","","list,detail,add,update"));
		fieldList.add(new FieldResource("deliveryType","Delivery Type","N","N","int","0","","list,detail,add,update"));

		List<String> searchFieldList = new ArrayList(); 
		searchFieldList.add("orderNbr");
		searchFieldList.add("batchNbr");
		
/*		List<String> listDisplayFieldList = new ArrayList();
		listDisplayFieldList.add("id");
		listDisplayFieldList.add("busName");
		listDisplayFieldList.add("locnNbr");
		listDisplayFieldList.add("company");
		listDisplayFieldList.add("division");
		listDisplayFieldList.add("orderNbr");
		listDisplayFieldList.add("batchNbr");
		listDisplayFieldList.add("orderDttm");
		listDisplayFieldList.add("deliveryDttm");
		listDisplayFieldList.add("deliveryType");
*/		
		orderHdrResource.setFieldList(fieldList);
		orderHdrResource.setSearchFieldList(searchFieldList);
		//orderHdrResource.setListDisplayFieldList(listDisplayFieldList);
		//orderHdrResource.setAddRecordFieldList(listDisplayFieldList);
		//orderHdrResource.setUpdateRecordFieldList(listDisplayFieldList);
		orderPlannerScreen.setHdrResource(orderHdrResource);
		List<RecordResource> dtlResources = new ArrayList();
		dtlResources.add(getOrderDetailResource(busName, locnNbr));
		orderPlannerScreen.setDtlResources(dtlResources);
		screenResourceList.add(orderPlannerScreen);
		orderMenu.setScreenResourceList(screenResourceList);
		return orderMenu;
	
	}	
	public RecordResource getOrderDetailResource(String busName, Integer locnNbr) {
		String invnListUrl = "https://orderplanner.the3dsoft.com/orderplanner/dtl/v1/"+busName+"/" + locnNbr + "/order";
		String invnGetRecordUrl = "https://orderplanner.the3dsoft.com/orderplanner/dtl/v1/"+busName+"/" + locnNbr + "/order/{id}";
		String invnAddRecordUrl = "https://orderplanner.the3dsoft.com/orderplanner/dtl/v1/"+busName+"/" + locnNbr + "/order";
		String updateRecordUrl = "https://orderplanner.the3dsoft.com/orderplanner/dtl/v1/"+busName+"/" + locnNbr + "/order"+"/{id}";
		String deleteRecordUrl = "https://orderplanner.the3dsoft.com/orderplanner/dtl/v1/"+busName+"/" + locnNbr + "/order"+"/{id}";
		RecordResource orderDtlResource = new RecordResource("OrderDtl", "Planned Order Details", invnListUrl, invnGetRecordUrl, invnAddRecordUrl, updateRecordUrl, deleteRecordUrl, null, null, null, null, null, null);
		
		List<FieldResource> fieldList = new ArrayList();
		fieldList.add(new FieldResource("id","id","Y","N","int","0","","list,detail,add,update"));
		fieldList.add(new FieldResource("busName","Company","Y","N","int","0","","list,detail,add,update"));
		fieldList.add(new FieldResource("locnNbr","Division","Y","N","int","0","","list,detail,add,update"));
		fieldList.add(new FieldResource("company","Company","Y","N","int","0","","list,detail,add,update"));
		fieldList.add(new FieldResource("division","Division","Y","N","int","0","","list,detail,add,update"));
		fieldList.add(new FieldResource("orderNbr","Company","Y","N","int","0","","list,detail,add,update"));
		fieldList.add(new FieldResource("batchNbr","Division","N","N","int","0","","list,detail,add,update"));
		fieldList.add(new FieldResource("orderDttm","Order Date/Time","Y","N","string","20","","list,detail,add,update"));
		fieldList.add(new FieldResource("deliveryDttm","deliveryDttm","N","N","string","20","","list,detail,add,update"));
		fieldList.add(new FieldResource("deliveryType","Delivery Type","N","N","int","0","","list,detail,add,update"));

		List<String> searchFieldList = new ArrayList(); 
		searchFieldList.add("orderNbr");
		searchFieldList.add("batchNbr");
		
/*		List<String> listDisplayFieldList = new ArrayList();
		listDisplayFieldList.add("id");
		listDisplayFieldList.add("busName");
		listDisplayFieldList.add("locnNbr");
		listDisplayFieldList.add("company");
		listDisplayFieldList.add("division");
		listDisplayFieldList.add("orderNbr");
		listDisplayFieldList.add("batchNbr");
		listDisplayFieldList.add("orderDttm");
		listDisplayFieldList.add("deliveryDttm");
		listDisplayFieldList.add("deliveryType");
*/		
		orderDtlResource.setFieldList(fieldList);
		orderDtlResource.setSearchFieldList(searchFieldList);
		//orderDtlResource.setListDisplayFieldList(listDisplayFieldList);
		//orderDtlResource.setAddRecordFieldList(listDisplayFieldList);
		//orderDtlResource.setUpdateRecordFieldList(listDisplayFieldList);
		return orderDtlResource;

	}
	
	private ScreenResource createInventoryScreen(String busName, Integer locnNbr) {
		ScreenResource inventoryScreen = new ScreenResource("InventoryMaintenance", "Inventory Maintenance","Item Inventory", "RW", null, null);
		String invnListUrl = "https://inventory.the3dsoft.com/inventory/v1/"+busName+"/" + locnNbr + "/inventory";
		String invnGetRecordUrl = "https://inventory.the3dsoft.com/inventory/v1/"+busName+"/" + locnNbr + "/inventory/{id}";
		String invnAddRecordUrl = "https://inventory.the3dsoft.com/inventory/v1/"+busName+"/" + locnNbr + "/inventory";
		String updateRecordUrl = "https://inventory.the3dsoft.com/inventory/v1/"+busName+"/" + locnNbr + "/inventory"+"/{id}";
		String deleteRecordUrl = "https://inventory.the3dsoft.com/inventory/v1/"+busName+"/" + locnNbr + "/inventory"+"/{id}";
		RecordResource inventoryHdrResource = new RecordResource("Inventory", "Inventory", invnListUrl, invnGetRecordUrl, invnAddRecordUrl, updateRecordUrl, deleteRecordUrl, null, null, null, null, null, null);
		
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

	private ScreenResource createPicksScreen(String busName, Integer locnNbr) {
		ScreenResource screen = new ScreenResource("PickingMaintenance", "Picking Maintenance","Picking Maintenance", "R", null, null);
		String listUrl = "https://picking.the3dsoft.com/picking/v1/"+busName+"/" + locnNbr + "/picks";
		String getRecordUrl = "https://picking.the3dsoft.com/picking/v1/"+busName+"/" + locnNbr + "/picks/{id}";
		String addRecordUrl = "";
		String updateRecordUrl = "https://picking.the3dsoft.com/picking/v1/"+busName+"/" + locnNbr + "/picks"+"/{id}";
		String deleteRecordUrl = "";
		RecordResource hdrResource = new RecordResource("Picks", "Picks", listUrl, getRecordUrl, addRecordUrl, updateRecordUrl, deleteRecordUrl, null, null, null, null, null, null);
		
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
		ScreenResource screen = new ScreenResource("PackingMaintenance", "Packing Maintenance","Packing Maintenance", "R", null, null);
		String listUrl = "https://packing.the3dsoft.com/packing/v1/"+busName+"/" + locnNbr + "/packs";
		String getRecordUrl = "https://packing.the3dsoft.com/packing/v1/"+busName+"/" + locnNbr + "/packs/{id}";
		String addRecordUrl = "";
		String updateRecordUrl = "https://packing.the3dsoft.com/packing/v1/"+busName+"/" + locnNbr + "/packs"+"/{id}";
		String deleteRecordUrl = "";
		RecordResource hdrResource = new RecordResource("Packs", "Packs", listUrl, getRecordUrl, addRecordUrl, updateRecordUrl, deleteRecordUrl, null, null, null, null, null, null);
		
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
	
	/*	private MenuResource createSampleInventoryMenu(String busName, Integer locnNbr) {
		MenuResource inventoryMenu = new MenuResource();
		inventoryMenu.setMenuName("Inventory");
		List<ScreenResource> screenResourceList = new ArrayList();

		ScreenResource inventoryScreen = new ScreenResource("InventoryMaintenance", "Inventory Maintenance","Item Inventory", "RW", null, null);
		String invnListUrl = "https://inventory.the3dsoft.com/inventory/v1/"+busName+"/" + locnNbr + "/inventory";
		String invnGetRecordUrl = "https://inventory.the3dsoft.com/inventory/v1/"+busName+"/" + locnNbr + "/inventory/{id}";
		String invnAddRecordUrl = "https://inventory.the3dsoft.com/inventory/v1/"+busName+"/" + locnNbr + "/inventory";
		String updateRecordUrl = "https://inventory.the3dsoft.com/inventory/v1/"+busName+"/" + locnNbr + "/inventory"+"/{id}";
		String deleteRecordUrl = "https://inventory.the3dsoft.com/inventory/v1/"+busName+"/" + locnNbr + "/inventory"+"/{id}";
		RecordResource inventoryHdrResource = new RecordResource("Inventory", "Inventory", invnListUrl, invnGetRecordUrl, invnAddRecordUrl, updateRecordUrl, deleteRecordUrl, null, null, null, null, null);
		
		List<FieldResource> searchFieldList = new ArrayList(); 
		searchFieldList.add(new FieldResource("locnBrcd", "Locn Brcd","N","N","int","20",""));
		searchFieldList.add(new FieldResource("itemBrcd","","Locn Brcd","N","int","20",""));
		
		List<FieldResource> listDisplayFieldList = new ArrayList();
		listDisplayFieldList.add(new FieldResource("id","id","N","N","int","0",""));
		listDisplayFieldList.add(new FieldResource("busName","Company","N","N","int","0",""));
		listDisplayFieldList.add(new FieldResource("locnNbr","Division","N","N","int","0",""));
		listDisplayFieldList.add(new FieldResource("company","Company","N","N","int","0",""));
		listDisplayFieldList.add(new FieldResource("division","Division","N","N","int","0",""));
		listDisplayFieldList.add(new FieldResource("locnBrcd","Locn Brcd","N","N","string","20",""));
		listDisplayFieldList.add(new FieldResource("itemBrcd","Item Brcd","N","N","string","20",""));
		listDisplayFieldList.add(new FieldResource("qty","Qty","N","N","int","0",""));
		listDisplayFieldList.add(new FieldResource("locked","Locked","N","N","int","0",""));
		listDisplayFieldList.add(new FieldResource("trackByIlpn","Track By LPN?","N","N","string","1","Y,N"));
		
		List<FieldResource> recordDetailFieldList = new ArrayList();
		recordDetailFieldList.add(new FieldResource("id","id","N","N","int","0",""));
		recordDetailFieldList.add(new FieldResource("company","Company","Y","N","int","0",""));
		recordDetailFieldList.add(new FieldResource("division","Division","Y","N","int","0",""));
		recordDetailFieldList.add(new FieldResource("locnBrcd","Locn Brcd","N","N","string","20",""));
		recordDetailFieldList.add(new FieldResource("itemBrcd","Item Brcd","N","N","string","20",""));
		recordDetailFieldList.add(new FieldResource("qty","Qty","N","N","int","0",""));
		recordDetailFieldList.add(new FieldResource("ilpn","ilpn","Ilpn","N","int","0",""));
		recordDetailFieldList.add(new FieldResource("locked","Locked","N","N","int","0",""));
		recordDetailFieldList.add(new FieldResource("trackByIlpn","Track By LPN?","N","N","string","1","Y,N"));
		
		List<FieldResource> updateRecordFieldList = new ArrayList();
		updateRecordFieldList.add(new FieldResource("qty","id","Qty","N","int","0",""));
		updateRecordFieldList.add(new FieldResource("ilpn","id","Qty","N","int","0",""));
		updateRecordFieldList.add(new FieldResource("locked","id","Qty","N","int","0",""));
		updateRecordFieldList.add(new FieldResource("trackByIlpn","id","Qty","N","int","0",""));
		inventoryHdrResource.setSearchFieldList(searchFieldList);
		inventoryHdrResource.setListDisplayFieldList(listDisplayFieldList);
		inventoryHdrResource.setAddRecordFieldList(recordDetailFieldList);
		inventoryHdrResource.setUpdateRecordFieldList(updateRecordFieldList);
		inventoryScreen.setHdrResource(inventoryHdrResource);
		screenResourceList.add(inventoryScreen);
		inventoryMenu.setScreenResourceList(screenResourceList);
		return inventoryMenu;
	}
*/	
	
	private ScreenResource createCustomerOrdersScreen(String busName, Integer locnNbr) {
		ScreenResource customerOrdersScreen = new ScreenResource("CustomerOrderMaintenance", "Customer Order Maintenance","Customer Orders", "RW", null, null);
		String custOrdersListUrl = "https://customer-orders.the3dsoft.com/customer-orders/v1/"+busName+"/" + locnNbr + "/order";
		String custOrdersRecordUrl = "https://customer-orders.the3dsoft.com/customer-orders/v1/"+busName+"/" + locnNbr + "/order/{id}";
		String custOrdersAddRecordUrl = "https://customer-orders.the3dsoft.com/customer-orders/v1/"+busName+"/" + locnNbr + "/order";
		String custOrdersUpdateRecordUrl = "https://customer-orders.the3dsoft.com/customer-orders/v1/"+busName+"/" + locnNbr + "/order"+"/{id}";
		String custOrdersDeleteRecordUrl = "https://customer-orders.the3dsoft.com/customer-orders/v1/"+busName+"/" + locnNbr + "/order"+"/{id}";
		RecordResource customerOrderHdrResource = new RecordResource("Customer Orders", "Customer Orders", custOrdersListUrl, custOrdersRecordUrl, custOrdersAddRecordUrl, custOrdersUpdateRecordUrl, custOrdersDeleteRecordUrl, null, null, null, null, null, null);
		
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
		
/*		List<String> listDisplayFieldList = new ArrayList();
		listDisplayFieldList.add("id");
		listDisplayFieldList.add("busName");
		listDisplayFieldList.add("locnNbr");
		listDisplayFieldList.add("company");
		listDisplayFieldList.add("division");
		listDisplayFieldList.add("orderNbr");
		listDisplayFieldList.add("batchNbr");
		listDisplayFieldList.add("orderDttm");
		listDisplayFieldList.add("deliveryDttm");
		listDisplayFieldList.add("deliveryType");
*/		
		customerOrderHdrResource.setFieldList(fieldList);
		customerOrderHdrResource.setSearchFieldList(searchFieldList);
		//customerOrderHdrResource.setListDisplayFieldList(listDisplayFieldList);
		//customerOrderHdrResource.setAddRecordFieldList(listDisplayFieldList);
		//customerOrderHdrResource.setUpdateRecordFieldList(listDisplayFieldList);
		customerOrdersScreen.setHdrResource(customerOrderHdrResource);
		List<RecordResource> dtlResources = new ArrayList();
		dtlResources.add(getCustomerOrderDetailResource(busName, locnNbr));
		customerOrdersScreen.setDtlResources(dtlResources);
		return customerOrdersScreen;
	
	}	
	public RecordResource getCustomerOrderDetailResource(String busName, Integer locnNbr) {
		String custOrderDtlListUrl = "https://customer-orders.the3dsoft.com/customer-orders/v1/"+busName+"/" + locnNbr + "/order/{id}/dtl";
		String custOrderDtlRecordUrl = "https://customer-orders.the3dsoft.com/customer-orders/v1/"+busName+"/" + locnNbr + "/order/{id}/dtl/{dtlId}";
		String custOrderDtlAddRecordUrl = "https://customer-orders.the3dsoft.com/customer-orders/v1/"+busName+"/" + locnNbr + "/order/{id}/dtl";
		String custOrderDtlupdateRecordUrl = "https://customer-orders.the3dsoft.com/customer-orders/v1/"+busName+"/" + locnNbr + "/order/{id}/dtl/{dtlId}";
		String custOrderDtldeleteRecordUrl = "https://customer-orders.the3dsoft.com/customer-orders/v1/"+busName+"/" + locnNbr + "/order/{id}/dtl/{dtlId}";
		RecordResource orderDtlResource = new RecordResource("CustomerOrderDtl", "Customer Order Details", custOrderDtlListUrl, custOrderDtlRecordUrl, custOrderDtlAddRecordUrl,custOrderDtlupdateRecordUrl, custOrderDtldeleteRecordUrl, null, null, null, null, null, null);
		
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
/*		
		List<String> listDisplayFieldList = new ArrayList();
		listDisplayFieldList.add("id");
		listDisplayFieldList.add("busName");
		listDisplayFieldList.add("locnNbr");
		listDisplayFieldList.add("company");
		listDisplayFieldList.add("division");
		listDisplayFieldList.add("orderNbr");
		listDisplayFieldList.add("origOrderQty");
		listDisplayFieldList.add("orderQty");
*/		
		orderDtlResource.setFieldList(fieldList);
		orderDtlResource.setSearchFieldList(searchFieldList);
		//orderDtlResource.setListDisplayFieldList(listDisplayFieldList);
		//orderDtlResource.setAddRecordFieldList(listDisplayFieldList);
		//orderDtlResource.setUpdateRecordFieldList(listDisplayFieldList);
		return orderDtlResource;
	}
}
