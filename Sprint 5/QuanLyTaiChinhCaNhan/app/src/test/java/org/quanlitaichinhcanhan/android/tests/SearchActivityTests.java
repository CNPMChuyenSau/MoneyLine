package org.quanlitaichinhcanhan.android.tests;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vanluom.group11.quanlytaichinhcanhan.BuildConfig;
import com.vanluom.group11.quanlytaichinhcanhan.R;
import com.vanluom.group11.quanlytaichinhcanhan.common.AllDataListFragment;
import com.vanluom.group11.quanlytaichinhcanhan.common.CategoryListActivity;
import com.vanluom.group11.quanlytaichinhcanhan.core.RequestCodes;
import com.vanluom.group11.quanlytaichinhcanhan.core.TransactionTypes;
import com.vanluom.group11.quanlytaichinhcanhan.search.SearchActivity;
import com.vanluom.group11.quanlytaichinhcanhan.search.SearchParametersFragment;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quanlitaichinhcanhan.android.testhelpers.DataHelpers;
import org.quanlitaichinhcanhan.android.testhelpers.UnitTestHelper;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.util.ActivityController;

import info.javaperformance.money.MoneyFactory;

import static junit.framework.Assert.fail;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test Search activity.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class SearchActivityTests {

    private ActivityController<SearchActivity> controller;
    private SearchActivity activity;

    @Before
    public void setUp() {
        this.controller = UnitTestHelper.getController(SearchActivity.class);
        this.activity = UnitTestHelper.getActivity(this.controller);

        UnitTestHelper.setupContentProvider();
        UnitTestHelper.setupLog();
    }

    @After
    public void tearDown() {
        this.controller.destroy();
        UnitTestHelper.teardownDatabase();
    }

    @Test
    public void activityOpens() {
        assertThat(this.activity).isNotNull();
    }

    /**
     * Add two transaction for Dining Out. Search for this sub/category and confirm that the
     * results are shown and the total & currency match.
     */
    @Test
    public void searchForSubCategoryWorksWithData() {
        // arrange

        UnitTestHelper.setDefaultCurrency("BAM");
        DataHelpers.insertData();
        // add expected transaction
        DataHelpers.createTransaction(1, -1, TransactionTypes.Withdrawal, 2, 9, MoneyFactory.fromString("-14.68"));
        // add one more for testing the total calculation
        DataHelpers.createTransaction(1, -1, TransactionTypes.Withdrawal, 2, 9, MoneyFactory.fromString("-25.37"));

        //*******************************************
        // act

        // Click Select Category
        TextView selectCategory = (TextView) activity.findViewById(R.id.textViewSelectCategory);
        assertThat(selectCategory).isNotNull();
        selectCategory.performClick();

        // confirm that clicking the Select Category text view opens category selector
        ShadowActivity shadowActivity = Shadows.shadowOf(this.activity);
        Intent expectedIntent = shadowActivity.peekNextStartedActivityForResult().intent;
        assertThat(expectedIntent.getComponent()).isEqualTo(new ComponentName(this.activity,
                CategoryListActivity.class));
        assertThat(shadowActivity.getNextStartedActivity()).isEqualTo(expectedIntent);

        // Now simulate that we received the category.

        Fragment searchFragment = UnitTestHelper.getFragment(activity, SearchParametersFragment.class.getSimpleName());
//        assertThat(searchFragment).isNotNull();

        // We "selected" Food:Dining out.
        Intent categoryData = UnitTestHelper.getSelectCategoryResult(2, "Food", 9, "Dining out");
        searchFragment.onActivityResult(RequestCodes.CATEGORY, Activity.RESULT_OK,
                categoryData);
        assertThat(selectCategory.getText()).containsSequence("Food : Dining out");

        // Run search

        LinearLayout searchButton = (LinearLayout) activity.findViewById(R.id.action_search);
        assertThat(searchButton).isNotNull();
        searchButton.performClick();

        //**************************************
        // assert

        // confirm the Total is shown and the sum is 0.

        Fragment resultsFragment = UnitTestHelper.getFragment(activity, AllDataListFragment.class.getSimpleName());
        assertThat(resultsFragment).isNotNull();

        View totalView = resultsFragment.getView().findViewById(R.id.textViewColumn1);
        assertThat(totalView).isNotNull();
        assertThat(totalView).isInstanceOf(TextView.class);
        TextView totalTextView = (TextView) totalView;
        assertThat(totalTextView.getText()).isEqualTo("Total");

        // total amount
        View totalNumberView = resultsFragment.getView().findViewById(R.id.textViewColumn2);
        assertThat(totalNumberView).isNotNull();
        assertThat(totalNumberView).isInstanceOf(TextView.class);
        TextView totalNumberTextView = (TextView) totalNumberView;
        assertThat(totalNumberTextView.getText()).isEqualTo("KM 40.05");
    }

    @Test
    public void searchForSubCategoryWorksWithNoData() {
        //*******************************************
        // arrange

        UnitTestHelper.setDefaultCurrency("BAM");
        DataHelpers.insertData();

        //*******************************************
        // act

        // Click Select Category
        TextView selectCategory = (TextView) activity.findViewById(R.id.textViewSelectCategory);
        selectCategory.performClick();

        // confirm that clicking the Select Category text view opens category selector
        ShadowActivity shadowActivity = Shadows.shadowOf(this.activity);
        Intent expectedIntent = shadowActivity.peekNextStartedActivityForResult().intent;
        assertThat(expectedIntent.getComponent()).isEqualTo(new ComponentName(this.activity,
            CategoryListActivity.class));
        assertThat(shadowActivity.getNextStartedActivity()).isEqualTo(expectedIntent);

        // Now simulate that we received the category.

        Fragment searchFragment = UnitTestHelper.getFragment(activity, SearchParametersFragment.class.getSimpleName());

        // We "selected" Food:Dining out.
        Intent categoryData = UnitTestHelper.getSelectCategoryResult(2, "Food", 9, "Dining out");
        searchFragment.onActivityResult(RequestCodes.CATEGORY, Activity.RESULT_OK,
            categoryData);
        assertThat(selectCategory.getText()).containsSequence("Food : Dining out");

        // Run search

        LinearLayout searchButton = (LinearLayout) activity.findViewById(R.id.action_search);
        searchButton.performClick();

        //**************************************
        // assert

        // confirm the Total is shown and the sum is 0.

        Fragment resultsFragment = UnitTestHelper.getFragment(activity, AllDataListFragment.class.getSimpleName());

        View totalView = resultsFragment.getView().findViewById(R.id.textViewColumn1);
        assertThat(totalView).isInstanceOf(TextView.class);
        TextView totalTextView = (TextView) totalView;
        assertThat(totalTextView.getText()).isEqualTo("Total");

        // total amount
        View totalNumberView = resultsFragment.getView().findViewById(R.id.textViewColumn2);
        assertThat(totalNumberView).isInstanceOf(TextView.class);
        TextView totalNumberTextView = (TextView) totalNumberView;
        assertThat(totalNumberTextView.getText()).isEqualTo("KM 0.00");
    }

}
