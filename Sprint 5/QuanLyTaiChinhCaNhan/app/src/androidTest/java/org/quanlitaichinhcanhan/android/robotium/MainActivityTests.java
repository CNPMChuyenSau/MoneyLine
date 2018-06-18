package org.quanlitaichinhcanhan.android.robotium;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.widget.DrawerLayout;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;

import com.vanluom.group11.quanlytaichinhcanhan.R;
import com.vanluom.group11.quanlytaichinhcanhan.assetallocation.editor.AssetAllocationEditorActivity;
import com.vanluom.group11.quanlytaichinhcanhan.budget.BudgetsActivity;
import com.vanluom.group11.quanlytaichinhcanhan.currency.list.CurrencyListActivity;
import com.vanluom.group11.quanlytaichinhcanhan.home.MainActivity;
import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Robotium tests for the Main Activity.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTests
        extends ActivityInstrumentationTestCase2<MainActivity> {

    private Solo solo;

    public MainActivityTests() {
        super(MainActivity.class);

    }

    @Before
    public void setUp() throws Exception {
        super.setUp();

        injectInstrumentation(InstrumentationRegistry.getInstrumentation());

        solo = new Solo(getInstrumentation(), getActivity());
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();

        if (solo != null) {
            solo.finishOpenedActivities();
        }
    }

    @Test
    public void testCreation() {
        assertNotNull(getActivity());
    }

    public void welcomeViewDisplayed() {
        solo.waitForText("Welcome to MoneyManagerEx");

        //assert
    }

    @Test
    public void testDrawerOpenClose() {
//        solo.waitForActivity(MainActivity.class.getSimpleName());

        View view = getActivity().findViewById(R.id.drawerLayout);
        DrawerLayout drawer = (DrawerLayout) view;
        assertThat(drawer).isNotNull();

//        assertThat(drawer.isDrawerOpen(view)).isFalse();
        assertThat(solo.searchText("Budgets", true)).isFalse();

        solo.clickOnActionBarHomeButton();
//        assertThat(drawer.isDrawerOpen(drawer)).isFalse();
        assertThat(solo.searchText("Budgets", true)).isTrue();

        solo.clickOnActionBarHomeButton();
//        assertThat(drawer.isDrawerOpen(drawer)).isFalse();
        assertThat(solo.searchText("Budgets", true)).isFalse();
    }

    @Test
    public void testBudgetsOpen() {
        solo.clickOnActionBarHomeButton();
        assertThat(solo.searchText("Budgets", true)).as("Budgets menu item not visible.").isTrue();

        solo.clickOnText("Budgets");
        solo.waitForActivity(BudgetsActivity.class.getSimpleName());

        assertThat(solo.searchText("Budget list")).isTrue();
    }

    @Test
    public void testEntitiesSubmenu() {
        solo.clickOnActionBarHomeButton();

        assertThat(solo.searchText("Currencies")).isFalse();

        solo.clickOnText("Entities");

        assertThat(solo.searchText("Accounts")).isTrue();
        assertThat(solo.searchText("Categories")).isTrue();
        assertThat(solo.searchText("Currencies")).isTrue();
        assertThat(solo.searchText("Payees")).isTrue();
    }

    @Test
    public void testCurrenciesOpen() {
        solo.clickOnActionBarHomeButton();
        solo.clickOnText("Entities");
        solo.clickOnText("Currencies");

        assertThat(solo.waitForActivity(CurrencyListActivity.class.getSimpleName())).isTrue();
        assertThat(solo.searchText("Bosnia and Herzegovina")).isTrue();
    }

    @Test
    public void openAssetAllocation() {
        solo.clickOnActionBarHomeButton();
        solo.clickOnText("Asset Allocation");

        assertThat(solo.waitForActivity(AssetAllocationEditorActivity.class.getSimpleName()))
            .as("Asset Allocation not started")
            .isTrue();
    }
}
