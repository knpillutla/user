package com.threedsoft.user.menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.threedsoft.user.dto.responses.ActionResource;
import com.threedsoft.user.dto.responses.DataResource;
import com.threedsoft.user.dto.responses.FieldResource;
import com.threedsoft.user.dto.responses.ScreenResource;
import com.threedsoft.user.dto.responses.ViewEditFieldResource;
import com.threedsoft.user.util.UserConstants;

public class UserProfileScreen {
	public static ScreenResource createUserProfileScreen(Long id) {
		ScreenResource screen = new ScreenResource("userProfile", "User Profile", "User Profile",
				"RW", UserConstants.MAINTENANCE_SCREEN, null);
		String updateRecordUrl = "https://the3dsoft.com/users/v1/user/id/" + id;
		
		DataResource userDataResource = new DataResource("User", "User", "", "", 
				"", updateRecordUrl, "");

		List<FieldResource> fieldList = new ArrayList();
		fieldList.add(FieldResource.createField("id","id", "string", "15"));
		fieldList.add(FieldResource.createField("theme", "Theme", "string", "15"));

		List<ViewEditFieldResource> editRecordFieldList = new ArrayList();
		editRecordFieldList.add(ViewEditFieldResource.createDisabledField("id"));
		editRecordFieldList.add(ViewEditFieldResource.createTextField("theme"));
		
		userDataResource.setFieldList(fieldList);
		userDataResource.setEditResourceFieldList(editRecordFieldList);
		screen.setDataResource(userDataResource);
		return screen;
	}
}
