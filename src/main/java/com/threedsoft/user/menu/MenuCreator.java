package com.threedsoft.user.menu;

import java.util.ArrayList;
import java.util.List;

import com.threedsoft.user.dto.responses.MenuResource;
import com.threedsoft.user.dto.responses.ScreenResource;

public class MenuCreator {

	public List<MenuResource> createWMSMenus(String busName, Integer locnNbr) {
		List<MenuResource> menuList = new ArrayList<MenuResource>();
		menuList.add(createConfigurationMenu(busName, locnNbr));
		menuList.add(createFulfillmentMenu(busName, locnNbr));
		// menuList.add(createCustomerOrdersMenu(busName, locnNbr));
		// menuList.add(createInventoryMenu(busName, locnNbr));
		menuList.add(RFMenu.createRFMenu(busName, locnNbr));
		return menuList;
	}

	private MenuResource createFulfillmentMenu(String busName, Integer locnNbr) {
		MenuResource orderFulfillmentMenu = new MenuResource();
		orderFulfillmentMenu.setMenuName("Fullfillment Maintenance");
		List<ScreenResource> screenResourceList = new ArrayList();
//		screenResourceList.add(CustomerOrderScreen.createCustomerOrdersScreen(busName, locnNbr));
//		screenResourceList.add(OrderPlannerScreen.createOrdersScreen(busName, locnNbr));
//		screenResourceList.add(InventoryScreen.createInventoryScreen(busName, locnNbr));
//		screenResourceList.add(PickingScreen.createPickingScreen(busName, locnNbr));
//		screenResourceList.add(PackingScreen.createPackingScreen(busName, locnNbr));
//		screenResourceList.add(ShippingScreen.createShippingScreen(busName, locnNbr));
		screenResourceList.add(WaveScreen.createWaveScreen(busName, locnNbr.toString()));
		screenResourceList.add(CustomerOrderScreenNew.createCustomerOrdersScreen(busName, locnNbr.toString()));
		screenResourceList.add(OrderPlannerScreenNew.createOrderPlannerScreen(busName, locnNbr.toString()));
		screenResourceList.add(InventoryScreenNew.createInventoryScreen(busName, locnNbr.toString()));
		screenResourceList.add(PickScreenNew.createPickScreen(busName, locnNbr.toString()));
		screenResourceList.add(PackScreenNew.createPackScreen(busName, locnNbr.toString()));
		screenResourceList.add(ShipmentScreenNew.createShippingScreen(busName, locnNbr.toString()));
		orderFulfillmentMenu.setScreenResourceList(screenResourceList);
		// }
		return orderFulfillmentMenu;
	}

	private MenuResource createConfigurationMenu(String busName, Integer locnNbr) {
		MenuResource configScreen = new MenuResource();
		configScreen.setMenuName("System Configuration");
		List<ScreenResource> screenResourceList = new ArrayList();
		screenResourceList.add(ConfigurationScreen.createConfigScreen(busName, locnNbr));
		screenResourceList.add(FacilityCarrierScreen.createFacilityCarrierScreen(busName, locnNbr));
		configScreen.setScreenResourceList(screenResourceList);
		return configScreen;
	}

	/*
	 * private MenuResource createCustomerOrdersMenu(String busName, Integer
	 * locnNbr) { MenuResource customerOrdersMenu = new MenuResource();
	 * customerOrdersMenu.setMenuName("Customer Orders"); List<ScreenResource>
	 * screenResourceList = new ArrayList();
	 * screenResourceList.add(createCustomerOrdersScreen(busName, locnNbr));
	 * customerOrdersMenu.setScreenResourceList(screenResourceList); return
	 * customerOrdersMenu; }
	 * 
	 * private MenuResource createInventoryMenu(String busName, Integer locnNbr) {
	 * MenuResource inventoryMenu = new MenuResource();
	 * inventoryMenu.setMenuName("Inventory"); List<ScreenResource>
	 * screenResourceList = new ArrayList();
	 * screenResourceList.add(createInventoryScreen(busName, locnNbr));
	 * inventoryMenu.setScreenResourceList(screenResourceList); return
	 * inventoryMenu; }
	 */
}
