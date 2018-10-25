package com.threedsoft.user.dto.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.threedsoft.user.db.User;
import com.threedsoft.user.dto.requests.UserCreationRequestDTO;
import com.threedsoft.user.dto.responses.MenuResource;
import com.threedsoft.user.dto.responses.ScreenResource;
import com.threedsoft.user.dto.responses.SearchFieldResource;
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
			userResourceDTO.getMenuResourceList().add(createSampleOrderMenu(userResourceDTO.getBusName(), userResourceDTO.getDefLocnNbr()));
			userResourceDTO.getMenuResourceList().add(createSampleInventoryMenu(userResourceDTO.getBusName(), userResourceDTO.getDefLocnNbr()));
			return userResourceDTO;
		}
		return null;
	}

	private MenuResource createSampleOrderMenu(String busName, Integer locnNbr) {
		MenuResource orderMenu = new MenuResource();
		orderMenu.setMenuName("Orders");
		List<ScreenResource> orderScreenResourceList = new ArrayList();
		List<SearchFieldResource> orderPlannerSearchFieldList = new ArrayList(); 
		orderPlannerSearchFieldList.add(new SearchFieldResource("orderNbr", ""));
		orderPlannerSearchFieldList.add(new SearchFieldResource("id",""));
		orderPlannerSearchFieldList.add(new SearchFieldResource("statCode",""));
		
		List<String> displayFieldList = new ArrayList();
		displayFieldList.add("id");
		displayFieldList.add("orderNbr");
		displayFieldList.add("statCode");
		List<String> updateFieldList = new ArrayList();
		updateFieldList.add("statCode");
		ScreenResource orderPlannerScreenResource= new ScreenResource("Order Planner", "RW", "https://orderplanner.the3dsoft.com:8080/orderplanner/v1/"+busName+"/" + locnNbr + "/order", "", "",
				"", orderPlannerSearchFieldList, displayFieldList, displayFieldList, displayFieldList, updateFieldList);
		orderMenu.setScreenResourceList(orderScreenResourceList);
		orderScreenResourceList.add(orderPlannerScreenResource);
		return orderMenu;
	}
	
	private MenuResource createSampleInventoryMenu(String busName, Integer locnNbr) {
		MenuResource inventoryMenu = new MenuResource();
		inventoryMenu.setMenuName("Inventory");
		List<ScreenResource> screenResourceList = new ArrayList();
		List<SearchFieldResource> searchFieldList = new ArrayList(); 
		searchFieldList.add(new SearchFieldResource("locnBrcd", ""));
		searchFieldList.add(new SearchFieldResource("itemBrcd",""));
		
		List<String> displayFieldList = new ArrayList();
		displayFieldList.add("id");
		displayFieldList.add("locnBrcd");
		displayFieldList.add("itemBrcd");
		displayFieldList.add("qty");
		List<String> updateFieldList = new ArrayList();
		updateFieldList.add("qty");
		ScreenResource screenResource= new ScreenResource("Inventory Availability", "RW", "https://inventory.the3dsoft.com:8080/inventory/v1/"+busName+"/" + locnNbr + "/inventory", "", "",
				"", searchFieldList, displayFieldList, displayFieldList, displayFieldList, updateFieldList);
		inventoryMenu.setScreenResourceList(screenResourceList);
		screenResourceList.add(screenResource);
		return inventoryMenu;
	}
	

}
