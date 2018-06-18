
package com.vanluom.group11.quanlytaichinhcanhan.assetallocation.overview;

import com.vanluom.group11.quanlytaichinhcanhan.domainmodel.AssetClass;

/**
 * A view model for asset class row.
 */
public class AssetClassViewModel {
    public AssetClassViewModel(AssetClass assetClass, int level) {
        this.assetClass = assetClass;
        this.level = level;
    }

    public AssetClass assetClass;
    public int level;
}
