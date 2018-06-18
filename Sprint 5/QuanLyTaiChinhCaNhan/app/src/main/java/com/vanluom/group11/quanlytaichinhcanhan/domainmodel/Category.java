package com.vanluom.group11.quanlytaichinhcanhan.domainmodel;

import android.content.ContentValues;

import org.parceler.Parcel;

/**
 * Category
 */
@Parcel
public class Category
    extends EntityBase {

    public static final String CATEGID = "CATEGID";
    public static final String CATEGNAME = "CATEGNAME";

    public Category() {
        super();
    }

    public Category(ContentValues contentValues) {
        super(contentValues);
    }

    public int getId() {
        return getInt(CATEGID);
    }

    public void setId(Integer value) {
        setInt(CATEGID, value);
    }

    public String getName() {
        return getString(CATEGNAME);
    }

    public void setName(String value) {
        setString(CATEGNAME, value);
    }
}
