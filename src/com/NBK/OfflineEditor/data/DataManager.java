package com.NBK.OfflineEditor.data;

import com.NBK.OfflineEditor.util.VersionUtils;

public class DataManager{

	private static final DataManager DATAMANAGER;
	private IData manager;
	
	static {
		DATAMANAGER = new DataManager();
	}
	
	private DataManager() {
		switch (VersionUtils.getVersion()) {
		case "v1_8_R1":
			this.manager = new com.NBK.OfflineEditor.data.versions.v1_8_R1.Manager();
			break;
		case "v1_8_R2":
			this.manager = new com.NBK.OfflineEditor.data.versions.v1_8_R2.Manager();
			break;
		case "v1_8_R3":
			this.manager = new com.NBK.OfflineEditor.data.versions.v1_8_R3.Manager();
			break;
		case "v1_9_R1":
			this.manager = new com.NBK.OfflineEditor.data.versions.v1_9_R1.Manager();
			break;
		case "v1_9_R2":
			this.manager = new com.NBK.OfflineEditor.data.versions.v1_9_R2.Manager();
			break;
		case "v1_10_R1":
			this.manager = new com.NBK.OfflineEditor.data.versions.v1_10_R1.Manager();
			break;
		case "v1_11_R1":
			this.manager = new com.NBK.OfflineEditor.data.versions.v1_11_R1.Manager();
			break;
		case "v1_12_R1":
			this.manager = new com.NBK.OfflineEditor.data.versions.v1_12_R1.Manager();
			break;
		case "v1_13_R1":
			this.manager = new com.NBK.OfflineEditor.data.versions.v1_13_R1.Manager();
			break;
		case "v1_13_R2":
			this.manager = new com.NBK.OfflineEditor.data.versions.v1_13_R2.Manager();
			break;
		case "v1_14_R1":
			this.manager = new com.NBK.OfflineEditor.data.versions.v1_14_R1.Manager();
			break;
		case "v1_15_R1":
			this.manager = new com.NBK.OfflineEditor.data.versions.v1_15_R1.Manager();
			break;
		case "v1_16_R1":
			this.manager = new com.NBK.OfflineEditor.data.versions.v1_16_R1.Manager();
			break;
		case "v1_16_R2":
			this.manager = new com.NBK.OfflineEditor.data.versions.v1_16_R2.Manager();
			break;
		case "v1_16_R3":
			this.manager = new com.NBK.OfflineEditor.data.versions.v1_16_R3.Manager();
			break;
		default:
			this.manager = null;
		}
	}
	
	public static IData getManager() {
		return DataManager.DATAMANAGER.manager;
	}
	
}
