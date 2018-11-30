package com.threedsoft.user.menu;

import java.util.ArrayList;
import java.util.List;

import com.threedsoft.user.dto.responses.DataResource;
import com.threedsoft.user.dto.responses.FieldResource;
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
		rfMenu.setRfScreenResourceList(rfScreenResourceList);
		return rfMenu;
	}

	private static RFScreenResource createSinglesRFPickScreen(String busName, Integer locnNbr) {
		RFScreenResource rfScreen = new RFScreenResource("pickToTote", "Pick To Tote", "RW",0,30,15,null,null);
		String nextPickUrl = "https://the3dsoft.com/picking/v1/" + busName + "/" + locnNbr + "/picks/next/{userId}";
		String updateRecordUrl = "https://the3dsoft.com/picking/v1/" + busName + "/" + locnNbr + "/picks" + "/{id}";
		List<RFFieldResource> fieldList = new ArrayList();
		fieldList.add(RFFieldResource.createHiddenField("busName", busName));
		fieldList.add(RFFieldResource.createHiddenField("locnNbr", locnNbr));
		fieldList.add(RFFieldResource.createDataTriggerHiddenField("id", "Pick Id","int", "10",nextPickUrl,"No Picks Found, try again..."));
		fieldList.add(RFFieldResource.createTextField("toteNbr", "Enter Tote","string", "20"));
		fieldList.add(RFFieldResource.createLabelField("locnBrcd", "Location Brcd","string", "7"));
		fieldList.add(RFFieldResource.createTextFieldWithValidation("scanLocnBrcd", "Enter Location Barcode:","int", "7","locnBrcd","invalid location barcode"));
		fieldList.add(RFFieldResource.createLabelField("upc", "UPC","string", "20"));
		fieldList.add(RFFieldResource.createTextFieldWithValidation("scanUpc", "Enter UPC:","string", "20","upc","invalid upc"));
		fieldList.add(RFFieldResource.createLabelField("qty", "Qty:","int", "3"));
		fieldList.add(RFFieldResource.createActionTextFieldWithValidation("scanQty", "Enter Qty:","string", "3","Y","qty","",updateRecordUrl,"id:id,toteNbr:toteNbr,locnBrcd:locnBrcd,upc:upc,scanQty:qty"));

		rfScreen.setRfFieldResourceList(fieldList);
		RFButtonResource buttonResource = new RFButtonResource("endTote","End Tote","Are you sure you want to end tote?","", "","toteNumber","N");
		rfScreen.setButtonResource(buttonResource);
		return rfScreen;
	}

	private static RFScreenResource createRFPackScreen(String busName, Integer locnNbr) {
		RFScreenResource rfScreen = new RFScreenResource("packFromTote", "Pack From Tote", "RW",0,30,15,null,null);
		String toteDetailsUrl = "https://the3dsoft.com/packing/v1/" + busName + "/" + locnNbr + "/tote" + "/{tote}";
		String updateRecordUrl = "https://the3dsoft.com/packing/v1/" + busName + "/" + locnNbr + "/packs" + "/{id}";
		List<RFFieldResource> fieldList = new ArrayList();
		fieldList.add(RFFieldResource.createHiddenField("busName", busName));
		fieldList.add(RFFieldResource.createHiddenField("locnNbr", locnNbr));
		fieldList.add(RFFieldResource.createDataTriggerTextField("toteNumber", "Enter Tote","string", "20",toteDetailsUrl,"No Tote Found, try again"));
		fieldList.add(RFFieldResource.createTextFieldWithValidation("scanUpc", "Enter UPC:","string", "20","upc","invalid upc"));
		fieldList.add(RFFieldResource.createLabelField("id", "Pack Id:","int", "20"));
		fieldList.add(RFFieldResource.createLabelField("qty", "Qty:","int", "3"));
		fieldList.add(RFFieldResource.createActionTextFieldWithValidation("scanQty", "Enter Qty:","string", "3","Y","qty","",updateRecordUrl,"id:id,toteNbr:toteNbr,locnBrcd:locnBrcd,upc:upc,scanQty:qty"));
		fieldList.add(RFFieldResource.createLabelField("packageNbr", "Package Number:","string", "20"));
		fieldList.add(RFFieldResource.createActionTextFieldWithValidation("scanPackageNbr", "Enter Package Nbr:","string", "20","Y","packageNbr","",updateRecordUrl,"id:id,toteNbr:toteNbr,upc:upc,scanQty:qty,packageNbr:packageNbr"));

		rfScreen.setRfFieldResourceList(fieldList);
		RFButtonResource buttonResource = new RFButtonResource("exit","Exit","Are you sure you want to Exit?","", "","","Y");
		rfScreen.setButtonResource(buttonResource);
		return rfScreen;
	}

	private static RFScreenResource createRFShipScreen(String busName, Integer locnNbr) {
		RFScreenResource rfScreen = new RFScreenResource("shipping", "Ship Package", "RW",0,30,15,null,null);
		String packageDetailsUrl = "https://the3dsoft.com/shipping/v1/" + busName + "/" + locnNbr + "/package" + "/{packageNbr}";
		String shipPackageUrl = "https://the3dsoft.com/shipping/v1/" + busName + "/" + locnNbr + "/package" + "/{packageNbr}";
		List<RFFieldResource> fieldList = new ArrayList();
		fieldList.add(RFFieldResource.createHiddenField("busName", busName));
		fieldList.add(RFFieldResource.createHiddenField("locnNbr", locnNbr));
		fieldList.add(RFFieldResource.createDataTriggerTextField("packageNbr", "Enter Package Nbr:","string", "20",packageDetailsUrl,"No Package Found, try again"));
		fieldList.add(RFFieldResource.createLabelField("estimatedWt", "EstimatedWt:","numeric", "3,2"));
		fieldList.add(RFFieldResource.createTextField("actualWt", "ActualWt:","numeric", "3,2"));
		rfScreen.setRfFieldResourceList(fieldList);
		RFButtonResource buttonResource = new RFButtonResource("ship","Ship Package","",shipPackageUrl, "packageNbr,estimatedWt,actualWt","packageNbr","N");
		rfScreen.setButtonResource(buttonResource);
		return rfScreen;
	}
}
