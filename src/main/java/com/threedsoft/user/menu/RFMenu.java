package com.threedsoft.user.menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.threedsoft.user.dto.responses.MenuResource;
import com.threedsoft.user.dto.responses.RFButtonResource;
import com.threedsoft.user.dto.responses.RFFieldResource;
import com.threedsoft.user.dto.responses.RFScreenResource;

public class RFMenu {

	public static MenuResource createRFMenu(String busName, Integer locnNbr) {
		MenuResource rfMenu = new MenuResource();
		rfMenu.setMenuName("RF Menu");
		rfMenu.setMenuType("RF");
		List<RFScreenResource> rfScreenResourceList = new ArrayList();
		rfScreenResourceList.add(createSinglesRFPickScreen(busName, locnNbr));
		rfScreenResourceList.add(createRFPackScreen(busName, locnNbr));
		rfScreenResourceList.add(createRFShipScreen(busName, locnNbr));
		rfScreenResourceList.add(createNewRFPickScreen(busName, locnNbr.toString()));
		rfScreenResourceList.add(createNewRFPackScreen(busName, locnNbr.toString()));
		rfMenu.setRfScreenResourceList(rfScreenResourceList);
		return rfMenu;
	}

	private static RFScreenResource createSinglesRFPickScreen(String busName, Integer locnNbr) {
		RFScreenResource rfScreen = new RFScreenResource("pickToTote", "Pick To Tote", "Pick To Tote", "RW", 0, 30, 15);
		// new RFScreenResource("pickToTote", "Pick To Tote", "RW",0,30,15,null,null);
		String nextPickUrl = "https://the3dsoft.com/picking/v1/" + busName + "/" + locnNbr + "/picks/next/{userId}";
		String updateRecordUrl = "https://the3dsoft.com/picking/v1/" + busName + "/" + locnNbr + "/picks" + "/{id}";
		String endToteUrl = "https://the3dsoft.com/picking/v1/" + busName + "/" + locnNbr + "/picks" + "/{id}/endTote";
		List<RFFieldResource> fieldList = new ArrayList();
		fieldList.add(RFFieldResource.createHiddenField("busName", busName));
		fieldList.add(RFFieldResource.createHiddenField("locnNbr", locnNbr));
		fieldList.add(RFFieldResource.createDataTriggerLabelField("id", "Pick Id", "int", "10", nextPickUrl, "POST",
				"No Picks Found, try again..."));
		fieldList.add(RFFieldResource.createStickyTextField("toteNbr", "Enter Tote", "string", "20"));
		fieldList.add(RFFieldResource.createLabelField("locnBrcd", "Location Brcd", "string", "7"));
		fieldList.add(RFFieldResource.createTextFieldWithValidation("scanLocnBrcd", "Enter Location Barcode:", "int",
				"7", "locnBrcd", "invalid location barcode"));
		fieldList.add(RFFieldResource.createLabelField("itemBrcd", "UPC", "string", "20"));
		fieldList.add(RFFieldResource.createTextFieldWithValidation("scanUpc", "Enter UPC:", "string", "20", "itemBrcd",
				"invalid upc"));
		fieldList.add(RFFieldResource.createLabelField("qty", "Qty:", "int", "3"));
		fieldList.add(RFFieldResource.createActionTextFieldWithValidation("scanQty", "Enter Qty:", "string", "3", "qty",
				"invalid qty", updateRecordUrl,
				"id:id,busName:busName,locnNbr:locnNbr,toContainer:toteNbr,locnBrcd:locnBrcd,scanUpc:itemBrcd,qtyPicked:scanQty,userId:userId",
				"Y"));

		rfScreen.setRfFieldResourceList(fieldList);
		RFButtonResource buttonResource = new RFButtonResource("endTote", "End Tote",
				"Are you sure you want to end tote?", endToteUrl, "busName:busName,locnNbr:locnNbr,toContainer:toteNbr",
				"toteNbr", "N");
		rfScreen.setButtonResources(Arrays.asList(buttonResource));
		return rfScreen;
	}

	private static RFScreenResource createRFPackScreen(String busName, Integer locnNbr) {
		RFScreenResource rfScreen = new RFScreenResource("packFromTote", "Pack From Tote", "Pack From Tote", "RW", 0,
				30, 15);
		String toteDetailsUrl = "https://the3dsoft.com/packing/v1/" + busName + "/" + locnNbr + "/packs/container"
				+ "/{tote}";
		String updateRecordUrl = "https://the3dsoft.com/packing/v1/" + busName + "/" + locnNbr + "/packs" + "/{id}";
		List<RFFieldResource> fieldList = new ArrayList();
		fieldList.add(RFFieldResource.createHiddenField("busName", busName));
		fieldList.add(RFFieldResource.createHiddenField("locnNbr", locnNbr));
		fieldList.add(RFFieldResource.createDataTriggerStickyTextField("toteNbr", "Enter Tote", "string", "20",
				toteDetailsUrl, "GET", "No Tote Found, try again"));
		fieldList.add(RFFieldResource.createTextFieldWithValidation("scanUpc", "Enter UPC:", "string", "20", "upc",
				"invalid upc"));
		fieldList.add(RFFieldResource.createLabelField("id", "Pack Id:", "int", "20"));
		fieldList.add(RFFieldResource.createLabelField("qty", "Qty:", "int", "3"));
		fieldList.add(RFFieldResource.createTextFieldWithValidation("scanQty", "Enter Qty:", "string", "3", "qty",
				"invalid qty"));
		fieldList.add(RFFieldResource.createLabelField("toContainer", "PackageNbr:", "string", "20"));
//		fieldList.add(RFFieldResource.createActionTextFieldWithValidation("scanPackageNbr", "Enter Package Nbr:",
//				"string", "20", "toContainer", "invalid package", updateRecordUrl,
//				"id:id,busName:busName,locnNbr:locnNbr,fromContainer:toteNbr,toContainer:scanPackageNbr,itemBrcd:scanUpc,qtyPacked:scanQty,userId:userId", "Y"));
		fieldList.add(RFFieldResource.createActionTextField("scanPackageNbr", "Enter Package Nbr:", "string", "20",
				updateRecordUrl,
				"id:id,busName:busName,locnNbr:locnNbr,fromContainer:toteNbr,toContainer:scanPackageNbr,itemBrcd:scanUpc,qtyPacked:scanQty,userId:userId",
				"Y"));

		rfScreen.setRfFieldResourceList(fieldList);
		RFButtonResource buttonResource = new RFButtonResource("exit", "Exit", "Are you sure you want to Exit?", "", "",
				"", "Y");
		rfScreen.setButtonResources(Arrays.asList(buttonResource));
		return rfScreen;
	}

	private static RFScreenResource createOldRFPackScreen(String busName, Integer locnNbr) {
		RFScreenResource rfScreen = new RFScreenResource("packFromTote", "Pack From Tote", "Pack From Tote", "RW", 0,
				30, 15);
		String toteDetailsUrl = "https://the3dsoft.com/packing/v1/" + busName + "/" + locnNbr + "/packs/container"
				+ "/{tote}";
		String updateRecordUrl = "https://the3dsoft.com/packing/v1/" + busName + "/" + locnNbr + "/packs" + "/{id}";
		List<RFFieldResource> fieldList = new ArrayList();
		fieldList.add(RFFieldResource.createHiddenField("busName", busName));
		fieldList.add(RFFieldResource.createHiddenField("locnNbr", locnNbr));
		fieldList.add(RFFieldResource.createDataTriggerStickyTextField("toteNbr", "Enter Tote", "string", "20",
				toteDetailsUrl, "GET", "No Tote Found, try again"));
		fieldList.add(RFFieldResource.createTextFieldWithValidation("scanUpc", "Enter UPC:", "string", "20", "upc",
				"invalid upc"));
		fieldList.add(RFFieldResource.createLabelField("id", "Pack Id:", "int", "20"));
		fieldList.add(RFFieldResource.createLabelField("qty", "Qty:", "int", "3"));
		fieldList.add(RFFieldResource.createTextFieldWithValidation("scanQty", "Enter Qty:", "string", "3", "qty",
				"invalid qty"));
		fieldList.add(RFFieldResource.createLabelField("toContainer", "PackageNbr:", "string", "20"));
//		fieldList.add(RFFieldResource.createActionTextFieldWithValidation("scanPackageNbr", "Enter Package Nbr:",
//				"string", "20", "toContainer", "invalid package", updateRecordUrl,
//				"id:id,busName:busName,locnNbr:locnNbr,fromContainer:toteNbr,toContainer:scanPackageNbr,itemBrcd:scanUpc,qtyPacked:scanQty,userId:userId", "Y"));
		fieldList.add(RFFieldResource.createActionTextField("scanPackageNbr", "Enter Package Nbr:", "string", "20",
				updateRecordUrl,
				"id:id,busName:busName,locnNbr:locnNbr,fromContainer:toteNbr,toContainer:scanPackageNbr,itemBrcd:scanUpc,qtyPacked:scanQty,userId:userId",
				"Y"));

		rfScreen.setRfFieldResourceList(fieldList);
		RFButtonResource buttonResource = new RFButtonResource("exit", "Exit", "Are you sure you want to Exit?", "", "",
				"", "Y");
		rfScreen.setButtonResources(Arrays.asList(buttonResource));
		return rfScreen;
	}

	private static RFScreenResource createRFShipScreen(String busName, Integer locnNbr) {
		RFScreenResource rfScreen = new RFScreenResource("shipping", "Ship Package", "Ship Package", "RW", 0, 30, 15);
		String packageDetailsUrl = "https://the3dsoft.com/shipping/v1/" + busName + "/" + locnNbr + "/package"
				+ "/{packageNbr}";
		String shipPackageUrl = "https://the3dsoft.com/shipping/v1/" + busName + "/" + locnNbr + "/package"
				+ "/{packageNbr}";
		List<RFFieldResource> fieldList = new ArrayList();
		fieldList.add(RFFieldResource.createHiddenField("busName", busName));
		fieldList.add(RFFieldResource.createHiddenField("locnNbr", locnNbr));
		fieldList.add(RFFieldResource.createDataTriggerTextField("packageNbr", "Enter Package Nbr:", "string", "20",
				packageDetailsUrl, "GET", "No Package Found, try again"));
		fieldList.add(RFFieldResource.createLabelField("estimatedWt", "EstimatedWt:", "numeric", "3,2"));
		fieldList.add(RFFieldResource.createTextField("actualWt", "ActualWt:", "numeric", "3,2"));
		rfScreen.setRfFieldResourceList(fieldList);
		RFButtonResource buttonResource = new RFButtonResource("ship", "Ship Package", "", shipPackageUrl,
				"packageNbr:packageNbr,estimatedWt:estimatedWt,actualWt:actualWt", "packageNbr", "N");
		rfScreen.setButtonResources(Arrays.asList(buttonResource));
		return rfScreen;
	}

	private static RFScreenResource createNewRFPickScreen(String busName, String facilityNbr) {
		RFScreenResource rfScreen = new RFScreenResource("newpickToTote", "New Pick To Tote", "New Pick To Tote", "RW",
				0, 30, 15);
		// new RFScreenResource("pickToTote", "Pick To Tote", "RW",0,30,15,null,null);
		String nextPickUrl = "https://the3dsoft.com/pick/api/picks/assign";
		String updateRecordUrl = "https://the3dsoft.com/pick/api/picks/confirm";
		String endToteUrl = "https://the3dsoft.com/pick/api/picks/endTote";
		List<RFFieldResource> fieldList = new ArrayList();
		fieldList.add(RFFieldResource.createHiddenField("busName", busName));
		fieldList.add(RFFieldResource.createHiddenField("facilityNbr", facilityNbr));
		fieldList.add(RFFieldResource.createDataTriggerLabelField("id", "Pick Id", "int", "10", nextPickUrl, "POST",
				"No Picks Found, try again..."));
		fieldList.add(RFFieldResource.createStickyTextField("toteNbr", "Enter Tote", "string", "20"));
		fieldList.add(RFFieldResource.createLabelField("locnBrcd", "Location Brcd", "string", "7"));
		fieldList.add(RFFieldResource.createTextFieldWithValidation("scanLocnBrcd", "Enter Location Barcode:", "int",
				"7", "locnBrcd", "invalid location barcode"));
		fieldList.add(RFFieldResource.createLabelField("itemBrcd", "UPC", "string", "20"));
		fieldList.add(RFFieldResource.createTextFieldWithValidation("scanUpc", "Enter UPC:", "string", "20", "upc",
				"invalid upc"));
		fieldList.add(RFFieldResource.createLabelField("qty", "Qty:", "int", "3"));
		fieldList.add(RFFieldResource.createActionTextFieldWithValidation("scanQty", "Enter Qty:", "string", "3", "qty",
				"invalid qty", updateRecordUrl,
				"id:id,busName:busName,facilityNbr:facilityNbr,toContainer:toteNbr,locnBrcd:locnBrcd,scanUpc:itemBrcd,pickedQty:scanQty,updatedBy:userId",
				"Y"));

		rfScreen.setRfFieldResourceList(fieldList);
		RFButtonResource buttonResource = new RFButtonResource("endTote", "End Tote",
				"Are you sure you want to end tote?", endToteUrl,
				"busName:busName,facilityNbr:facilityNbr,toContainer:toteNbr", "toteNbr", "N");
		rfScreen.setButtonResources(Arrays.asList(buttonResource));
		return rfScreen;
	}

	private static RFScreenResource createNewRFPackScreen(String busName, String facilityNbr) {
		RFScreenResource rfScreen = new RFScreenResource("newpackFromTote", "New Pack From Tote", "New Pack From Tote",
				"RW", 0, 30, 15);
		String toteDetailsUrl = "https://the3dsoft.com/pack/api/packs/container?busName=" + busName + "&facilityNbr="
				+ facilityNbr + "&fromContainer={containerNbr}";
		String updateRecordUrl = "https://the3dsoft.com/pack/api/packs/confirmPack";
		List<RFFieldResource> fieldList = new ArrayList();
		fieldList.add(RFFieldResource.createHiddenField("busName", busName));
		fieldList.add(RFFieldResource.createHiddenField("facilityNbr", facilityNbr));
//		fieldList.add(RFFieldResource.createDataTriggerStickyTextField("containerNbr", "Enter Tote", "string", "20",
//				toteDetailsUrl, "GET", "No Tote Found, try again"));
		fieldList.add(RFFieldResource.createStickyTextField("containerNbr", "Enter Tote:", "string", "20"));

//		fieldList.add(RFFieldResource.createTextFieldWithValidation("scanUpc", "Enter UPC:", "string", "20", "upc",
//				"invalid upc"));
		// fieldList.add(RFFieldResource.createLabelField("id", "Pack Id:", "int",
		// "20"));
		// fieldList.add(RFFieldResource.createLabelField("qty", "Qty:", "int", "3"));
		// fieldList.add(RFFieldResource.createTextField("scanQty", "Enter Qty:",
		// "string", "3"));
		// fieldList.add(RFFieldResource.createLabelField("toContainer", "PackageNbr:",
		// "string", "20"));
//		fieldList.add(RFFieldResource.createActionTextFieldWithValidation("scanPackageNbr", "Enter Package Nbr:",
//				"string", "20", "toContainer", "invalid package", updateRecordUrl,
//				"id:id,busName:busName,locnNbr:locnNbr,fromContainer:toteNbr,toContainer:scanPackageNbr,itemBrcd:scanUpc,qtyPacked:scanQty,userId:userId", "Y"));
		fieldList.add(RFFieldResource.createActionTextField("scanUpc", "Enter UPC:", "string", "20", updateRecordUrl,
				"busName:busName,facilityNbr:facilityNbr,fromContainer:containerNbr,itemBrcd:scanUpc,packedQty:1,updatedBy:userId",
				"Y"));

		rfScreen.setRfFieldResourceList(fieldList);
		RFButtonResource buttonResource = new RFButtonResource("exit", "Exit", "Are you sure you want to Exit?", "", "",
				"", "Y");
		rfScreen.setButtonResources(Arrays.asList(buttonResource));
		return rfScreen;
	}

}
