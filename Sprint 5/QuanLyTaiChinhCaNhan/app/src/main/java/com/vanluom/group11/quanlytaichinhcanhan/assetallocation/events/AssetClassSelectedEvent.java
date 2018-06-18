
package com.vanluom.group11.quanlytaichinhcanhan.assetallocation.events;

/**
 * Raised when an Asset Class has been selected from the list.
 */
public class AssetClassSelectedEvent {

    public AssetClassSelectedEvent(int assetClassId) {
        this.assetClassId = assetClassId;
    }

    public int assetClassId;
}
