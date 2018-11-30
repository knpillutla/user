package com.threedsoft.user.menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.threedsoft.user.UserApplicationProperties;
import com.threedsoft.user.dto.responses.ActionResource;
import com.threedsoft.user.dto.responses.DataResource;
import com.threedsoft.user.dto.responses.FieldResource;
import com.threedsoft.user.dto.responses.MenuResource;
import com.threedsoft.user.dto.responses.RequestFieldResource;
import com.threedsoft.user.dto.responses.ScreenResource;
import com.threedsoft.user.util.UserConstants;

public class MenuCreator {

	public List<MenuResource> createWMSMenus(String busName, Integer locnNbr) {
		List<MenuResource> menuList = new ArrayList<MenuResource>();
		menuList.add(createFulfillmentMenu(busName, locnNbr));
		// menuList.add(createCustomerOrdersMenu(busName, locnNbr));
		// menuList.add(createInventoryMenu(busName, locnNbr));
		menuList.add(RFMenu.createRFMenu(busName, locnNbr));
		return menuList;
	}

	private MenuResource createFulfillmentMenu(String busName, Integer locnNbr) {
		MenuResource inventoryMenu = new MenuResource();
		inventoryMenu.setMenuName("Fullfillment Maintenance");
		List<ScreenResource> screenResourceList = new ArrayList();
		screenResourceList.add(CustomerOrderScreen.createCustomerOrdersScreen(busName, locnNbr));
		if(UserApplicationProperties.isHomeStore.equalsIgnoreCase("N")) {
			screenResourceList.add(OrderPlannerScreen.createOrdersScreen(busName, locnNbr));
			// screenResourceList.add(orderPlannerScreen);
			screenResourceList.add(InventoryScreen.createInventoryScreen(busName, locnNbr));
			screenResourceList.add(PickingScreen.createPickingScreen(busName, locnNbr));
			screenResourceList.add(PackingScreen.createPackingScreen(busName, locnNbr));
			inventoryMenu.setScreenResourceList(screenResourceList);
		}
		return inventoryMenu;
	}

/*	private MenuResource createCustomerOrdersMenu(String busName, Integer locnNbr) {
		MenuResource customerOrdersMenu = new MenuResource();
		customerOrdersMenu.setMenuName("Customer Orders");
		List<ScreenResource> screenResourceList = new ArrayList();
		screenResourceList.add(createCustomerOrdersScreen(busName, locnNbr));
		customerOrdersMenu.setScreenResourceList(screenResourceList);
		return customerOrdersMenu;
	}

	private MenuResource createInventoryMenu(String busName, Integer locnNbr) {
		MenuResource inventoryMenu = new MenuResource();
		inventoryMenu.setMenuName("Inventory");
		List<ScreenResource> screenResourceList = new ArrayList();
		screenResourceList.add(createInventoryScreen(busName, locnNbr));
		inventoryMenu.setScreenResourceList(screenResourceList);
		return inventoryMenu;
	}
*/
}
