
package com.vanluom.group11.quanlytaichinhcanhan.database;
/**
 * Class to parse in content provider for generate rawQuery
 */
public class SQLDataSet extends Dataset {
	public SQLDataSet() {
		super(null, DatasetType.SQL, "sql");
	}
	
	@Override
	public String[] getAllColumns() {
		return null;
	}
}
