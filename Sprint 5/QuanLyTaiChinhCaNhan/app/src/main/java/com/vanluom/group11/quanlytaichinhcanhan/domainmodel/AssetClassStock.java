package com.vanluom.group11.quanlytaichinhcanhan.domainmodel;

import org.parceler.Parcel;

/**
 * Mapping between Asset Classes and Stocks.
 */
@Parcel
public class AssetClassStock
    extends EntityBase {

    public static final String ID = "ID";
    public static final String ASSETCLASSID = "ASSETCLASSID";
    public static final String STOCKSYMBOL = "STOCKSYMBOL";

    public static AssetClassStock create(int assetClassId, String stockSymbol) {
        AssetClassStock entity = new AssetClassStock();

        entity.setAssetClassId(assetClassId);
        entity.setStockSymbol(stockSymbol);

        return entity;
    }

    public AssetClassStock() {
        super();
    }

    public void setId(int value) {
        setInt(ID, value);
    }

    public Integer getAssetClassId() {
        return getInt(ASSETCLASSID);
    }

    public void setAssetClassId(int value) {
        setInt(ASSETCLASSID, value);
    }

    public String getStockSymbol() {
        return getString(STOCKSYMBOL);
    }

    public void setStockSymbol(String value) {
        setString(STOCKSYMBOL, value);
    }
}
