package com.vanluom.group11.quanlytaichinhcanhan.assetallocation;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;

import com.vanluom.group11.quanlytaichinhcanhan.R;
import com.vanluom.group11.quanlytaichinhcanhan.adapter.MoneySimpleCursorAdapter;
import com.vanluom.group11.quanlytaichinhcanhan.common.BaseListFragment;
import com.vanluom.group11.quanlytaichinhcanhan.common.MmxCursorLoader;
import com.vanluom.group11.quanlytaichinhcanhan.datalayer.AssetClassStockRepository;
import com.vanluom.group11.quanlytaichinhcanhan.datalayer.Select;
import com.vanluom.group11.quanlytaichinhcanhan.datalayer.StockFields;
import com.vanluom.group11.quanlytaichinhcanhan.datalayer.StockRepository;
import com.vanluom.group11.quanlytaichinhcanhan.domainmodel.AssetClassStock;
import com.vanluom.group11.quanlytaichinhcanhan.domainmodel.Stock;

/**
 * A placeholder fragment containing a simple view.
 */
public class SecurityListFragment
    extends BaseListFragment
    implements LoaderManager.LoaderCallbacks<Cursor> {

    public static final String INTENT_RESULT_STOCK_SYMBOL = "SecurityListFragment:StockSymbol";

    private static final int LOADER_SYMBOLS = 0;
    private static final String PARAM_ASSET_CLASS_ID = "assetClassId";

    public static SecurityListFragment create(Integer assetClassId) {
        SecurityListFragment instance = new SecurityListFragment();

        Bundle params = new Bundle();
        params.putInt(PARAM_ASSET_CLASS_ID, assetClassId);
        instance.setArguments(params);

        return instance;
    }

    public SecurityListFragment() {
    }

    public String action = Intent.ACTION_PICK;

    private String mCurFilter;
    private String selectedStockSymbol;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // set show search
        setSearchMenuVisible(true);
        // set default value
        setEmptyText(getActivity().getResources().getString(R.string.no_records_found_create));
        setHasOptionsMenu(true);

//        int layout = Intent.ACTION_PICK.equals(this.action)
//            ? android.R.layout.simple_list_item_multiple_choice
//            : android.R.layout.simple_list_item_1;
        int layout = android.R.layout.simple_list_item_1;

        // create adapter
        MoneySimpleCursorAdapter adapter = new MoneySimpleCursorAdapter(getActivity(),
            layout, null,
            new String[]{ StockFields.SYMBOL },
            new int[]{ android.R.id.text1 }, 0);
        setListAdapter(adapter);

        registerForContextMenu(getListView());

        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        setListShown(false);
        // start loader
        getLoaderManager().initLoader(LOADER_SYMBOLS, null, this);

        // set icon searched
//        setMenuItemSearchIconified(!Intent.ACTION_PICK.equals(this.action));
//        setFloatingActionButtonVisible(true);
//        attachFloatingActionButtonToListView(true);
    }

    @Override
    public String getSubTitle() {
        return null;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        switch (id) {
            case LOADER_SYMBOLS:
                String whereClause;
                String selectionArgs[] = null;

                // ignore all the symbols already linked
                whereClause = StockFields.SYMBOL + " NOT IN (SELECT " + AssetClassStock.STOCKSYMBOL +
                    " FROM " + new AssetClassStockRepository(getActivity()).getSource() + ")";


                if (!TextUtils.isEmpty(mCurFilter)) {
                    whereClause += " AND " + StockFields.SYMBOL + " LIKE ?";
                    selectionArgs = new String[]{ mCurFilter + "%" };
                }

                StockRepository repo = new StockRepository(getActivity());
                Select query = new Select(new String[] { "STOCKID AS _id", StockFields.STOCKID, StockFields.SYMBOL })
                    .where(whereClause, selectionArgs)
                    .orderBy("upper(" + StockFields.SYMBOL + ")");

                return new MmxCursorLoader(getActivity(), repo.getUri(), query);
        }

        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        switch (loader.getId()) {
            case LOADER_SYMBOLS:
                MoneySimpleCursorAdapter adapter = (MoneySimpleCursorAdapter) getListAdapter();
                adapter.setHighlightFilter(mCurFilter != null ? mCurFilter.replace("%", "") : "");
//                adapter.swapCursor(data);
                adapter.changeCursor(data);

                if (isResumed()) {
                    setListShown(true);
                    if (data != null && data.getCount() <= 0 && getFloatingActionButton() != null) {
                        getFloatingActionButton().show(true);
                    }
                } else {
                    setListShownNoAnimation(true);
                }
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        switch (loader.getId()) {
            case LOADER_SYMBOLS:
                MoneySimpleCursorAdapter adapter = (MoneySimpleCursorAdapter) getListAdapter();
//                adapter.swapCursor(null);
                adapter.changeCursor(null);
        }
    }

    // Other

    @Override
    public boolean onQueryTextChange(String newText) {
        mCurFilter = !TextUtils.isEmpty(newText) ? newText : null;
        getLoaderManager().restartLoader(LOADER_SYMBOLS, null, this);
        return true;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        if (this.action.equals(Intent.ACTION_PICK)) {
            // select the current item and return.
            Cursor c = (Cursor) l.getItemAtPosition(position);
            Stock stock = Stock.from(c);
            selectedStockSymbol = stock.getSymbol();

            setResultAndFinish();
        }
    }

    @Override
    protected void setResult() {
        Intent result;
        switch (this.action) {
            case Intent.ACTION_PICK:
                result = new Intent();
                result.putExtra(INTENT_RESULT_STOCK_SYMBOL, selectedStockSymbol);
                if (TextUtils.isEmpty(selectedStockSymbol)) {
                    getActivity().setResult(Activity.RESULT_CANCELED, result);
                } else {
                    getActivity().setResult(Activity.RESULT_OK, result);
                }
                break;

            default:
                // otherwise return cancel
                getActivity().setResult(Activity.RESULT_CANCELED);
                break;
        }
    }

}
