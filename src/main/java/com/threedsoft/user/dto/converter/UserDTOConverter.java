package com.threedsoft.user.dto.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.threedsoft.user.db.User;
import com.threedsoft.user.dto.requests.UserCreationRequestDTO;
import com.threedsoft.user.dto.responses.FieldResource;
import com.threedsoft.user.dto.responses.MenuResource;
import com.threedsoft.user.dto.responses.DataResource;
import com.threedsoft.user.dto.responses.ScreenResource;
import com.threedsoft.user.dto.responses.UserResourceDTO;
import com.threedsoft.user.menu.MenuCreator;
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
		userEntity.setTheme(userCreationReq.getTheme());
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
			MenuCreator menuCreator = new MenuCreator();
			userResourceDTO.setMenuResourceList(menuCreator.createWMSMenus(userResourceDTO.getBusName(), userResourceDTO.getDefLocnNbr()));
			return userResourceDTO;
		}
		return null;
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
	
/*	private MenuResource createSampleOrderMenu(String busName, Integer locnNbr) {
		MenuResource orderMenu = new MenuResource();
		orderMenu.setMenuName("Orders");
		List<ScreenResource> screenResourceList = new ArrayList();

		ScreenResource orderPlannerScreen = new ScreenResource("OrderPlannerMaintenance", "Order Planner Maintenance","Order Planning", "RW", "Maintenance",null, null);
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
		
		List<String> listDisplayFieldList = new ArrayList();
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
		
		List<String> listDisplayFieldList = new ArrayList();
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
		
		orderDtlResource.setFieldList(fieldList);
		orderDtlResource.setSearchFieldList(searchFieldList);
		//orderDtlResource.setListDisplayFieldList(listDisplayFieldList);
		//orderDtlResource.setAddRecordFieldList(listDisplayFieldList);
		//orderDtlResource.setUpdateRecordFieldList(listDisplayFieldList);
		return orderDtlResource;

	}
	
*/}
