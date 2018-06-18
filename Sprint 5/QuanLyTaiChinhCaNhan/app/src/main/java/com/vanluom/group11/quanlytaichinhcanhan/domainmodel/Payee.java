package com.vanluom.group11.quanlytaichinhcanhan.domainmodel;

import android.content.ContentValues;

import com.vanluom.group11.quanlytaichinhcanhan.Constants;

/**
 * Payee model.
 */
public class Payee
    extends EntityBase {

    public static final String PAYEEID = "PAYEEID";
    public static final String PAYEENAME = "PAYEENAME";
    public static final String CATEGID = "CATEGID";
    public static final String SUBCATEGID = "SUBCATEGID";

    public Payee() {
        super();
    }

    public Payee(ContentValues contentValues) {
        super(contentValues);
    }

    public Integer getId() {
        return getInt(PAYEEID);
    }

    public void setId(Integer value) {
        setInt(Payee.PAYEEID, value);
    }

    public String getName() {
        return getString(Payee.PAYEENAME);
    }

    public void setName(String value) {
        setString(Payee.PAYEENAME, value);
    }

    public Integer getCategoryId() {
        return getInt(Payee.CATEGID);
    }

    public void setCategoryId(Integer value) {
        setInt(Payee.CATEGID, value);
    }

    public boolean hasCategory() {
        return this.getCategoryId() != null && this.getCategoryId() != Constants.NOT_SET;
    }

    public Integer getSubcategoryId() {
        return getInt(Payee.SUBCATEGID);
    }

    public void setSubcategoryId(Integer value) {
        setInt(SUBCATEGID, value);
    }
}
