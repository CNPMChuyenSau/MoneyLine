package com.vanluom.group11.quanlytaichinhcanhan.database;

import android.content.Context;
import android.database.Cursor;

import com.vanluom.group11.quanlytaichinhcanhan.R;
import com.vanluom.group11.quanlytaichinhcanhan.utils.MmxFileUtils;

public class QueryCategorySubCategory extends Dataset {
    //definizione dei nomi dei campi
    public static final String ID = "_id";
    public static final String CATEGID = "CATEGID";
    public static final String CATEGNAME = "CATEGNAME";
    public static final String SUBCATEGID = "SUBCATEGID";
    public static final String SUBCATEGNAME = "SUBCATEGNAME";
    public static final String CATEGSUBNAME = "CATEGSUBNAME";
    //definizione dei campi
    private int categId;
    private CharSequence categName;
    private int subCategId;
    private CharSequence mSubcategoryName;
    private CharSequence categSubName;

    // definizione del costruttore
    public QueryCategorySubCategory(Context context) {
        super(MmxFileUtils.getRawAsString(context, R.raw.query_categorysubcategory), DatasetType.QUERY, "categorysubcategory");
    }

    @Override
    public String[] getAllColumns() {
        return new String[]{ID, CATEGID, CATEGNAME, SUBCATEGID, SUBCATEGNAME, CATEGSUBNAME};
    }

    public int getCategId() {
        return categId;
    }

    public void setCategId(int categId) {
        this.categId = categId;
    }

    public CharSequence getCategName() {
        return categName;
    }

    public void setCategName(CharSequence categName) {
        this.categName = categName;
    }

    public CharSequence getCategSubName() {
        return categSubName;
    }

    public void setCategSubName(CharSequence categSubName) {
        this.categSubName = categSubName;
    }

    public int getSubCategId() {
        return subCategId;
    }

    public void setSubCategId(int subCategId) {
        this.subCategId = subCategId;
    }

    public CharSequence getSubcategoryName() {
        return mSubcategoryName;
    }

    public void setSubcategoryName(CharSequence mSubcategoryName) {
        this.mSubcategoryName = mSubcategoryName;
    }

    @Override
    public void setValueFromCursor(Cursor c) {
        // controllo che non sia null il cursore
        if (c == null) {
            return;
        }
        // controllo che il numero di colonne siano le stesse
        if (!(c.getColumnCount() == this.getAllColumns().length)) {
            return;
        }
        // set dei valori
        this.setCategId(c.getInt(c.getColumnIndex(CATEGID)));
        this.setCategName(c.getString(c.getColumnIndex(CATEGNAME)));
        this.setSubCategId(c.getInt(c.getColumnIndex(SUBCATEGID)));
        this.setSubcategoryName(c.getString(c.getColumnIndex(SUBCATEGNAME)));
        this.setCategSubName(c.getString(c.getColumnIndex(CATEGSUBNAME)));
    }
}
