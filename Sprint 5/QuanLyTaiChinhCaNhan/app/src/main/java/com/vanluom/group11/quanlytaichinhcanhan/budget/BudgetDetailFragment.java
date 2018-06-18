package com.vanluom.group11.quanlytaichinhcanhan.budget;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.vanluom.group11.quanlytaichinhcanhan.Constants;
import com.vanluom.group11.quanlytaichinhcanhan.R;
import com.vanluom.group11.quanlytaichinhcanhan.common.BaseListFragment;
import com.vanluom.group11.quanlytaichinhcanhan.common.MmxCursorLoader;
import com.vanluom.group11.quanlytaichinhcanhan.database.QueryCategorySubCategory;
import com.vanluom.group11.quanlytaichinhcanhan.datalayer.Select;
import com.vanluom.group11.quanlytaichinhcanhan.settings.AppSettings;

/**
 * Use the {@link BudgetDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BudgetDetailFragment
    extends BaseListFragment {

    private static final String ARG_BUDGET_YEAR_ID = "budgetYearId";
    private static final String ARG_BUDGET_NAME_ID = "budgetName";

    private final int LOADER_BUDGET = 1;
    private long mBudgetYearId = Constants.NOT_SET;
    private String mBudgetName;
    private View mHeader;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment BudgetDetailFragment.
     */
    public static BudgetDetailFragment newInstance(long budgetYearId, String budgetName) {
        BudgetDetailFragment fragment = new BudgetDetailFragment();
        Bundle args = new Bundle();
        args.putLong(ARG_BUDGET_YEAR_ID, budgetYearId);
        args.putString(ARG_BUDGET_NAME_ID, budgetName);
        fragment.setArguments(args);

        return fragment;
    }

    public BudgetDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public String getSubTitle() {
        return mBudgetName;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mBudgetYearId = getArguments().getLong(ARG_BUDGET_YEAR_ID);
            mBudgetName = getArguments().getString(ARG_BUDGET_NAME_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_budget_detail, container, false);
        View view = super.onCreateView(inflater, container, savedInstanceState);

        ListView list = (ListView) view.findViewById(android.R.id.list);

        // Add the column header.
        // switch to simple layout if the showSimpleView is set
        AppSettings settings = new AppSettings(getContext());
        int layout = (settings.getBudgetSettings().getShowSimpleView()) ? R.layout.item_budget_simple_header : R.layout.item_budget_header;

        mHeader = View.inflate(getActivity(), layout, null);
        list.addHeaderView(mHeader);
        // Header has to be added before the adapter is set on the list.

        setUpAdapter();

        return view;
    }

    // Got random IndexOutOfBoundsException during loading the new fragment
    // reference: http://stackoverflow.com/a/28463811
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (getListView().getHeaderViewsCount() > 0) {
            getListView().removeHeaderView(mHeader);
        }
    }

    @Override
    public void onViewCreated (View view, Bundle savedInstanceState) {
        displayBudget();
    }

    // Private

    private void displayBudget() {
        setListShown(false);

        LoaderManager.LoaderCallbacks<Cursor> callbacks = setUpLoaderCallbacks();

        getLoaderManager().initLoader(LOADER_BUDGET, null, callbacks);
    }

    private void setUpAdapter() {
        BudgetAdapter adapter = new BudgetAdapter(getActivity(),
                null,
                new String[]{ BudgetQuery.CATEGNAME },
                new int[]{ R.id.categoryTextView },
                0);

        adapter.setBudgetName(mBudgetName);
        adapter.setBudgetYearId(mBudgetYearId);

        setListAdapter(adapter);
    }

    private LoaderManager.LoaderCallbacks<Cursor> setUpLoaderCallbacks() {
        return new LoaderManager.LoaderCallbacks<Cursor>() {
            @Override
            public Loader<Cursor> onCreateLoader(int id, Bundle args) {
                Loader<Cursor> result = null;

                switch (id) {
                    case LOADER_BUDGET:
                        QueryCategorySubCategory categories = new QueryCategorySubCategory(getActivity());
                        Select query = new Select(categories.getAllColumns())
                            .orderBy(QueryCategorySubCategory.CATEGSUBNAME);

                        result = new MmxCursorLoader(getActivity(), categories.getUri(), query);
                        break;
                }
                return result;
            }

            @Override
            public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
                switch (loader.getId()) {
                    case LOADER_BUDGET:
                        BudgetAdapter adapter = (BudgetAdapter) getListAdapter();
//                        adapter.swapCursor(data);
                        adapter.changeCursor(data);

                        if (isResumed()) {
                            setListShown(true);
                        } else {
                            setListShownNoAnimation(true);
                        }
                        break;
                }
            }

            @Override
            public void onLoaderReset(Loader<Cursor> loader) {
                switch (loader.getId()) {
                    case LOADER_BUDGET:
                        BudgetAdapter adapter = (BudgetAdapter) getListAdapter();
//                        adapter.swapCursor(null);
                        adapter.changeCursor(null);
                        break;
                }
            }
        };
    }
}
