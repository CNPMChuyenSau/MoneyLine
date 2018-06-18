
package com.vanluom.group11.quanlytaichinhcanhan.domainmodel;

import org.parceler.Parcel;

/**
 * Represents a Budget.
 * Table: budgetyear_v1
 */
@Parcel
public class Budget
    extends EntityBase {

    public static final String BUDGETYEARID = "BUDGETYEARID";
    public static final String BUDGETYEARNAME = "BUDGETYEARNAME";

    public Budget() { }

    public Integer getId() {
        return getInt(BUDGETYEARID);
    }

    public void setId(Integer value) {
        setInt(BUDGETYEARID, value);
    }

    public String getName() {
        return getString(BUDGETYEARNAME);
    }

    public void setName(String value) {
        setString(BUDGETYEARNAME, value);
    }
}
