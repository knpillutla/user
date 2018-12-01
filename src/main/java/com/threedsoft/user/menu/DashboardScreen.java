package com.threedsoft.user.menu;

import com.threedsoft.user.dto.responses.DashboardScreenResource;
import com.threedsoft.user.dto.responses.DashboardTileResource;
import com.threedsoft.user.dto.responses.ScreenResource;

public class DashboardScreen {
	public static DashboardScreenResource createDashboardScreen(String busName, Integer locnNbr) {
		DashboardScreenResource screen = new DashboardScreenResource("Dashboard", "WMS Dashboard", "WMS Dashboard",
				"R");
		screen.add(new DashboardTileResource("pickerPerformance24hr", "Picker Performance 24 hours",
				DashboardTileResource.TYPE_LIST, "https://the3dsoft.com/eventmonitor/v1" + busName + "/" + locnNbr + "/picks/picker/stats/1/10"));
		screen.add(new DashboardTileResource("pickerPerformanceRealtime", "Realtime Picker Performance ",
				DashboardTileResource.TYPE_LIST, "https://the3dsoft.com/eventmonitor/v1/" + busName + "/" + locnNbr + "/picks/picker/stats"));
		screen.add(new DashboardTileResource("picksHeatMap", "Realtime Picks HeatMap",
				DashboardTileResource.TYPE_HEATMAP, "https://the3dsoft.com/eventmonitor/v1/" + busName + "/" + locnNbr + "/picks/heatmap"));

		
		screen.add(new DashboardTileResource("packerPerformance", "Packer Performance 24 hours",
				DashboardTileResource.TYPE_LIST, "https://the3dsoft.com/eventmonitor/v1" + busName + "/" + locnNbr + "/packs/packer/stats/1/10"));
		screen.add(new DashboardTileResource("packerPerformanceRealtime", "Realtime Packer Performance",
				DashboardTileResource.TYPE_LIST, "https://the3dsoft.com/eventmonitor/v1/" + busName + "/" + locnNbr + "/packs/packer/stats"));
		
		screen.add(new DashboardTileResource("eventMonitoring", "Realtime Events", DashboardTileResource.TYPE_LINEGRAPH,
				"https://the3dsoft.com/eventmonitor/v1/" + busName + "/" + locnNbr + "/eventcounters/1"));
		screen.add(new DashboardTileResource("orderbacklog", "Realtime Order Backlog",
				DashboardTileResource.TYPE_PIECHART, "https://the3dsoft.com/eventmonitor/v1/" + busName + "/" + locnNbr + "/orders/backlog"));
		screen.add(new DashboardTileResource("hourlyOrderStats", "Realtime Order Events",
				DashboardTileResource.TYPE_LINEGRAPH, "https://the3dsoft.com/eventmonitor/v1/" + busName + "/" + locnNbr + "/orders/stats/1"));
		screen.add(new DashboardTileResource("pickingStats", "Picking Statistics 24 Hours",
				DashboardTileResource.TYPE_LIST, "https://the3dsoft.com/eventmonitor/v1/" + busName + "/" + locnNbr + "/orders/picking/stats/1/3"));
		return screen;
	}

}
