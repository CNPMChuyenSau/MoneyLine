package com.vanluom.group11.quanlytaichinhcanhan.investment;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.vanluom.group11.quanlytaichinhcanhan.R;
import com.vanluom.group11.quanlytaichinhcanhan.common.BaseListFragment;
import com.vanluom.group11.quanlytaichinhcanhan.common.MmxCursorLoader;
import com.vanluom.group11.quanlytaichinhcanhan.datalayer.Select;
import com.vanluom.group11.quanlytaichinhcanhan.datalayer.StockFields;
import com.vanluom.group11.quanlytaichinhcanhan.datalayer.StockRepository;
import com.vanluom.group11.quanlytaichinhcanhan.domainmodel.Stock;

/**
 * Use the {@link PortfolioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PortfolioFragment
    extends BaseListFragment {

    private static final String ARG_ACCOUNT_ID = "accountId";
    public static final int ID_LOADER = 1;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param accountId Investment account Id
     * @return A new instance of fragment PortfolioFragment.
     */
    public static PortfolioFragment newInstance(Integer accountId) {
        PortfolioFragment fragment = new PortfolioFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_ACCOUNT_ID, accountId);
        fragment.setArguments(args);
        return fragment;
    }

    public PortfolioFragment() {
        // Required empty public constructor
    }

    private Integer mAccountId;

    @Override
    public String getSubTitle() {
        return getString(R.string.portfolio);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null && savedInstanceState.containsKey(ARG_ACCOUNT_ID)) {
            // get data from saved instance state
            mAccountId = savedInstanceState.getInt(ARG_ACCOUNT_ID);
        } else {
            //if (getArguments() != null) {
            mAccountId = getArguments().getInt(ARG_ACCOUNT_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (container == null) return null;

        View view = inflater.inflate(R.layout.fragment_portfolio, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setEmptyText(getString(R.string.no_stock_data));
        setListShown(false);

        // create adapter
        PortfolioCursorAdapter adapter = new PortfolioCursorAdapter(getActivity(), null);

        initializeList();

        setListAdapter(adapter);

        initializeLoader();

        // hide the title
        // todo: uncomment this after setting the correct fragment type.
//        ActionBar actionBar = getActionBar();
//        if (actionBar != null) {
//            actionBar.setDisplayShowTitleEnabled(false);
//        }

        setFloatingActionButtonVisible(true);
        attachFloatingActionButtonToListView();
    }

    @Override
    public void onFloatingActionButtonClicked() {
        openEditInvestmentActivity(null);
    }

    @Override
    public void onSaveInstanceState(Bundle saveInstanceState) {
        super.onSaveInstanceState(saveInstanceState);

        saveInstanceState.putInt(ARG_ACCOUNT_ID, mAccountId);
    }

    // Private

    private void initializeList() {
        // e list item click.
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Ignore the header row.
                if (getListView().getHeaderViewsCount() > 0 && position == 0) return;

                if (getListAdapter() != null && getListAdapter() instanceof PortfolioCursorAdapter) {
                    Cursor cursor = (Cursor) getListAdapter().getItem(position);
                    Stock stock = Stock.from(cursor);
                    openEditInvestmentActivity(stock.getId());
                }
            }
        });

    }

    private void initializeLoader() {
        // initialize loader
        getLoaderManager().initLoader(ID_LOADER, getArguments(), new LoaderManager.LoaderCallbacks<Cursor>() {
            @Override
            public Loader<Cursor> onCreateLoader(int id, Bundle args) {
                //animation
                setListShown(false);

                StockRepository repo = new StockRepository(getActivity());
                Select query = new Select(repo.getAllColumns())
                    .where(StockFields.HELDAT + " = " + args.getInt(ARG_ACCOUNT_ID))
                    .orderBy(StockFields.SYMBOL);
                //.orderBy(sort);

                return new MmxCursorLoader(getActivity(), repo.getUri(), query);
            }

            @Override
            public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
                CursorAdapter adapter = (CursorAdapter) getListAdapter();
                adapter.changeCursor(data);

                if (isResumed()) {
                    setListShown(true);

                    if (getFloatingActionButton() != null) {
                        getFloatingActionButton().show(true);
                    }
                } else {
                    setListShownNoAnimation(true);
                }
                // update the header
//   todo     displayHeaderData();
            }

            @Override
            public void onLoaderReset(Loader<Cursor> loader) {
                ((CursorAdapter) getListAdapter()).changeCursor(null);
            }
        });
    }

    private void openEditInvestmentActivity(Integer stockId) {
        Intent intent = new Intent(getActivity(), InvestmentTransactionEditActivity.class);
        intent.putExtra(InvestmentTransactionEditActivity.ARG_ACCOUNT_ID, mAccountId);
        intent.putExtra(InvestmentTransactionEditActivity.ARG_STOCK_ID, stockId);
        intent.setAction(Intent.ACTION_INSERT);
        startActivity(intent);
    }

}
