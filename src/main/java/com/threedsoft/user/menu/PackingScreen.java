package com.threedsoft.user.menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.threedsoft.user.dto.responses.ActionResource;
import com.threedsoft.user.dto.responses.DataResource;
import com.threedsoft.user.dto.responses.FieldResource;
import com.threedsoft.user.dto.responses.ScreenResource;
import com.threedsoft.user.dto.responses.SearchFieldResource;
import com.threedsoft.user.dto.responses.ViewEditFieldResource;
import com.threedsoft.user.util.UserConstants;

public class PackingScreen {
	public static ScreenResource createPackingScreen(String busName, Integer locnNbr) {
		ScreenResource screen = new ScreenResource("PackingMaintenance", "Packing Maintenance", "Packing Maintenance",
				"R", UserConstants.MAINTENANCE_SCREEN, null);
		String searchUrl = "https://the3dsoft.com/packing/v1/" + busName + "/" + locnNbr + "/packs/search";
		String listUrl = "https://the3dsoft.com/packing/v1/" + busName + "/" + locnNbr + "/packs";
		String getRecordUrl = "https://the3dsoft.com/packing/v1/" + busName + "/" + locnNbr + "/packs/{id}";
		String addRecordUrl = "";
		String updateRecordUrl = "https://the3dsoft.com/packing/v1/" + busName + "/" + locnNbr + "/packs" + "/{id}";
		String deleteRecordUrl = "";
		String packConfirmActionUrl =  "https://the3dsoft.com/picking/v1/" + busName + "/" + locnNbr + "/picks/{id}";
		
		ActionResource packConfirmPickingAction = new ActionResource();
		packConfirmPickingAction.setActionName("packconfirm");
		packConfirmPickingAction.setActionDisplayName("Pack Confirm");
		packConfirmPickingAction.setActionUrl(packConfirmActionUrl);
/*		List<RequestFieldResource> requestFieldList = new ArrayList();
		requestFieldList.add(new RequestFieldResource("busName","","N"));
		requestFieldList.add(new RequestFieldResource("locnNbr","","N"));
		requestFieldList.add(new RequestFieldResource("id","","N"));
		requestFieldList.add(new RequestFieldResource("orderId","","N"));
		requestFieldList.add(new RequestFieldResource("batchNbr","","N"));
		requestFieldList.add(new RequestFieldResource("qtyPacked","Qty Packed","","Y"));
		requestFieldList.add(new RequestFieldResource("fromContainer","From Container","","Y"));
		requestFieldList.add(new RequestFieldResource("toContainer","To Container","","Y"));
*/		packConfirmPickingAction.setRequestFields("id,busName,locnNbr,packedQty,toContainer");
		DataResource packDataResource = new DataResource("Packs", "Packs", searchUrl, listUrl, 
				addRecordUrl, updateRecordUrl, deleteRecordUrl);

		List<FieldResource> fieldList = new ArrayList();
		fieldList.add(FieldResource.createPrimaryField("id", "pack id", "int", "10"));
		fieldList.add(FieldResource.createPrimaryField("busName","Bus Name", "string", "25"));
		fieldList.add(FieldResource.createPrimaryField("locnNbr", "Locn Nbr", "int", "10"));
		fieldList.add(FieldResource.createField("batchNbr","Batch Nbr", "string", "15"));
		fieldList.add(FieldResource.createField("orderNbr","Order Nbr", "string", "15"));
		fieldList.add(FieldResource.createField("orderId","Order Id", "string", "15"));
		fieldList.add(FieldResource.createField("company","Company", "string", "15"));
		fieldList.add(FieldResource.createField("division","Division", "string", "10"));
		fieldList.add(FieldResource.createField("busUnit","Bus Unit", "string", "2"));
		fieldList.add(FieldResource.createField("itemBrcd", "Item Brcd","string", "20"));
		fieldList.add(FieldResource.createField("qty", "Qty", "int", "2"));
		fieldList.add(FieldResource.createField("packedQty", "Packed Qty", "int", "2"));
		fieldList.add(FieldResource.createField("fromContainer", "From Container", "string", "20"));
		fieldList.add(FieldResource.createField("toContainer", "To Container", "string", "20"));
		fieldList.add(FieldResource.createDropDownField("locked", "Locked", "string", "1","Y,N","N"));

		List<ViewEditFieldResource> editRecordFieldList = new ArrayList();
		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("id"));
		editRecordFieldList.add(ViewEditFieldResource.createHiddenField("busName"));
		editRecordFieldList.add(ViewEditFieldResource.createHiddenField("locnNbr"));
		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("company"));
		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("division"));
		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("batchNbr"));
		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("orderNbr"));
		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("orderId"));
		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("itemBrcd"));
		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("qty"));
		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("fromContainer"));
		editRecordFieldList.add(ViewEditFieldResource.createMandatoryField("packedQty"));
		editRecordFieldList.add(ViewEditFieldResource.createMandatoryField("toContainer"));
		editRecordFieldList.add(ViewEditFieldResource.createMandatoryField("locked"));

		List<SearchFieldResource> searchFieldList = new ArrayList();
		searchFieldList.add(SearchFieldResource.createHiddenFieldWithDefaultValue("busName",busName));
		searchFieldList.add(SearchFieldResource.createHiddenFieldWithDefaultValue("locnNbr",locnNbr.toString()));
		searchFieldList.add(SearchFieldResource.createSearchField("locnBrcd"));
		searchFieldList.add(SearchFieldResource.createSearchField("itemBrcd"));

		packDataResource.setFieldList(fieldList);
		packDataResource.setSearchFieldList(searchFieldList);
		packDataResource.setListFields("id, batchNbr,itemBrcd,qty,fromContainer,toContainer,locked, ordeId, orderNbr");
		packDataResource.setRecordActionList(Arrays.asList(packConfirmPickingAction));
		screen.setDataResource(packDataResource);
		return screen;
	}
}
