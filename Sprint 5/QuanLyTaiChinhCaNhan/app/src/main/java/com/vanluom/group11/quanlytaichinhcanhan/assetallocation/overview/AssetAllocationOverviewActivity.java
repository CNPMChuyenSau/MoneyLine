
package com.vanluom.group11.quanlytaichinhcanhan.assetallocation.overview;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.CustomEvent;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.vanluom.group11.quanlytaichinhcanhan.R;
import com.vanluom.group11.quanlytaichinhcanhan.assetallocation.editor.AssetAllocationEditorActivity;
import com.vanluom.group11.quanlytaichinhcanhan.assetallocation.ItemType;
import com.vanluom.group11.quanlytaichinhcanhan.common.MmxBaseFragmentActivity;
import com.vanluom.group11.quanlytaichinhcanhan.core.AnswersEvents;
import com.vanluom.group11.quanlytaichinhcanhan.core.FormatUtilities;
import com.vanluom.group11.quanlytaichinhcanhan.core.MenuHelper;
import com.vanluom.group11.quanlytaichinhcanhan.core.UIHelper;
import com.vanluom.group11.quanlytaichinhcanhan.currency.list.CurrencyListActivity;
import com.vanluom.group11.quanlytaichinhcanhan.domainmodel.AssetClass;
import com.vanluom.group11.quanlytaichinhcanhan.servicelayer.AssetAllocationService;
import com.vanluom.group11.quanlytaichinhcanhan.settings.AppSettings;
import com.vanluom.group11.quanlytaichinhcanhan.view.RobotoTextView;

import java.util.ArrayList;
import java.util.List;

import info.javaperformance.money.Money;

public class AssetAllocationOverviewActivity
    extends MmxBaseFragmentActivity {

    private FormatUtilities formatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_asset_allocation_overview);

        // Toolbar
        setUpToolbar();

        Answers.getInstance().logCustom(new CustomEvent(AnswersEvents.AssetAllocationOverview.name()));
    }

    @Override
    public void onResume() {
        super.onResume();

        displayAssetAllocation();
    }

    // Menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.menu_asset_allocation_editor, menu);

        UIHelper ui = new UIHelper(this);

        // Currencies
        MenuItem currenciesMenu = menu.findItem(R.id.menu_currencies);
        if (currenciesMenu != null) {
            IconicsDrawable icon = ui.getIcon(GoogleMaterial.Icon.gmd_euro_symbol);
            currenciesMenu.setIcon(icon);
        }

        MenuHelper helper = new MenuHelper(this, menu);

        // Edit Asset Allocation.
        helper.add(MenuHelper.edit, R.string.edit, GoogleMaterial.Icon.gmd_edit)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;

            case R.id.menu_currencies:
                // open the Currencies activity.
                intent = new Intent(this, CurrencyListActivity.class);
                intent.setAction(Intent.ACTION_EDIT);
                startActivity(intent);
                break;

            case MenuHelper.edit:
                intent = new Intent(this, AssetAllocationEditorActivity.class);
                startActivity(intent);
                break;

            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    /*
     * Private
     */

    private List<AssetClassViewModel> createViewModel(AssetClass assetAllocation) {
        if (assetAllocation == null) {
            // get asset allocation
            AssetAllocationService service = new AssetAllocationService(this);
            assetAllocation = service.loadAssetAllocation();
        }
        // This happens.
        if (assetAllocation == null) {
            return null;
        }

        // linearize for display
        List<AssetClassViewModel> modelList = new ArrayList<>();
        for (AssetClass child : assetAllocation.getChildren()) {
            addModelToList(child, modelList, 0);
        }

        // add the totals at the end
        AssetClassViewModel totalModel = new AssetClassViewModel(assetAllocation, 0);
        totalModel.assetClass.setType(ItemType.Footer);
        modelList.add(totalModel);

        return modelList;
    }

    private void addModelToList(AssetClass assetClass, List<AssetClassViewModel> modelList, int level) {
        // add the asset class first.
        AssetClassViewModel model = new AssetClassViewModel(assetClass, level);
        modelList.add(model);

        List<AssetClass> children = assetClass.getChildren();
        if (children.size() == 0) return;

        // then add the children.
        for (AssetClass child : children) {
            addModelToList(child, modelList, level + 1);
        }
    }

    private void displayAssetAllocation() {
        AssetAllocationService service = new AssetAllocationService(this);
        AssetClass assetAllocation = service.loadAssetAllocation();

        List<AssetClassViewModel> model = createViewModel(assetAllocation);

        Money threshold = new AppSettings(this).getInvestmentSettings().getAssetAllocationDifferenceThreshold();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        FullAssetAllocationAdapter adapter = new FullAssetAllocationAdapter(model, threshold, getFormatter());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        showTotal(assetAllocation);
    }

    private FormatUtilities getFormatter() {
        if (this.formatter == null) {
            formatter = new FormatUtilities(this);
        }
        return this.formatter;
    }

    private void setUpToolbar() {
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar == null) return;

//        actionBar.setSubtitle(R.string.asset_allocation);
//        actionBar.setTitle(R.string.asset_allocation);
        actionBar.setDisplayHomeAsUpEnabled(true);

        // Title.
        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(getString(R.string.asset_allocation));
    }

    private void showTotal(AssetClass assetAllocation) {
        if (assetAllocation == null) return;

        RobotoTextView totalView = (RobotoTextView) findViewById(R.id.totalAmountTextView);
        if (totalView == null) return;

        totalView.setText(getFormatter().getValueFormattedInBaseCurrency(assetAllocation.getValue()));
    }
}
