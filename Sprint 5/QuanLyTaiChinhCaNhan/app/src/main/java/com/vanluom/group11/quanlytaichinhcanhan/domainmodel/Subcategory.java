package com.vanluom.group11.quanlytaichinhcanhan.domainmodel;

import org.parceler.Parcel;

/**
 * Category
 */
@Parcel
public class Subcategory
    extends EntityBase {

    public static final String SUBCATEGID = "SUBCATEGID";
    public static final String SUBCATEGNAME = "SUBCATEGNAME";
    public static final String CATEGID = "CATEGID";

    public Subcategory() {}

    public String getName() {
        return getString(SUBCATEGNAME);
    }
}
