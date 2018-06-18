package com.vanluom.group11.quanlytaichinhcanhan.database;

public class TableAssets extends Dataset {
	//FIELDS
	public static final String ASSETID = "ASSETID";
	public static final String STARTDATE = "STARTDATE";
	public static final String ASSETNAME = "ASSETNAME";
	public static final String VALUE = "VALUE";
	public static final String VALUECHANGE = "VALUECHANGE";
	public static final String NOTES = "NOTES";
	public static final String VALUECHANGERATE = "VALUECHANGERATE";
	public static final String ASSETTYPE = "ASSETTYPE";
	
	public TableAssets() {
		super("assets_v1", DatasetType.TABLE, "assets");
	}

	public String[] getAllColumns() {
		// todo: implement!
		return null;
	}

}
