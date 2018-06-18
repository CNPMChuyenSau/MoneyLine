
package com.vanluom.group11.quanlytaichinhcanhan.investment.watchlist;

import android.content.Context;

import com.vanluom.group11.quanlytaichinhcanhan.R;
import com.vanluom.group11.quanlytaichinhcanhan.datalayer.StockFields;
import com.vanluom.group11.quanlytaichinhcanhan.domainmodel.StockHistory;
import com.vanluom.group11.quanlytaichinhcanhan.database.Dataset;
import com.vanluom.group11.quanlytaichinhcanhan.database.DatasetType;
import com.vanluom.group11.quanlytaichinhcanhan.utils.MmxFileUtils;

/**
 * Watchlist query.
 */
public class WatchlistDataset
    extends Dataset {

    public WatchlistDataset(Context context) {
        super(MmxFileUtils.getRawAsString(context, R.raw.query_watchlist), DatasetType.QUERY,
                "watchlist");

        mContext = context;
    }

    private Context mContext;

    @Override
    public String[] getAllColumns() {
        return new String[] {
                StockFields.STOCKID + " AS _id",
                StockFields.STOCKID,
                StockFields.HELDAT,
                StockFields.STOCKNAME,
                StockFields.SYMBOL,
                StockHistory.DATE,
                StockHistory.VALUE
        };
    }

    public String getWatchlistSqlQuery() {
        String result = MmxFileUtils.getRawAsString(mContext, R.raw.query_watchlist);
        return result;
    }

}
