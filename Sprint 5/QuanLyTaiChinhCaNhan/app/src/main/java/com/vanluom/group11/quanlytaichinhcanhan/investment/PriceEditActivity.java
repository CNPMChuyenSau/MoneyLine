
package com.vanluom.group11.quanlytaichinhcanhan.investment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.codetroopers.betterpickers.calendardatepicker.CalendarDatePickerDialogFragment;
import com.vanluom.group11.quanlytaichinhcanhan.Constants;
import com.vanluom.group11.quanlytaichinhcanhan.MoneyManagerApplication;
import com.vanluom.group11.quanlytaichinhcanhan.R;
import com.vanluom.group11.quanlytaichinhcanhan.common.Calculator;
import com.vanluom.group11.quanlytaichinhcanhan.common.CalculatorActivity;
import com.vanluom.group11.quanlytaichinhcanhan.common.MmxBaseFragmentActivity;
import com.vanluom.group11.quanlytaichinhcanhan.core.MenuHelper;
import com.vanluom.group11.quanlytaichinhcanhan.core.RequestCodes;
import com.vanluom.group11.quanlytaichinhcanhan.core.UIHelper;
import com.vanluom.group11.quanlytaichinhcanhan.datalayer.StockHistoryRepository;
import com.vanluom.group11.quanlytaichinhcanhan.datalayer.StockRepository;
import com.vanluom.group11.quanlytaichinhcanhan.sync.SyncManager;
import com.vanluom.group11.quanlytaichinhcanhan.utils.MmxDate;
import com.vanluom.group11.quanlytaichinhcanhan.utils.MmxDateTimeUtils;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.Lazy;
import icepick.Icepick;
import info.javaperformance.money.MoneyFactory;
import timber.log.Timber;

public class PriceEditActivity
    extends MmxBaseFragmentActivity {

    public static final String ARG_CURRENCY_ID = "PriceEditActivity:CurrencyId";

    @Inject Lazy<MmxDateTimeUtils> dateTimeUtilsLazy;

    //@State
    protected PriceEditModel model;
    private EditPriceViewHolder viewHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price_edit);

        MoneyManagerApplication.getApp().iocComponent.inject(this);

        ButterKnife.bind(this);

        initializeToolbar();

        if (savedInstanceState != null) {
            Icepick.restoreInstanceState(this, savedInstanceState);
        }  else {
            initializeModel();
        }

        viewHolder = new EditPriceViewHolder();
        viewHolder.bind(this);

        model.display(this, viewHolder);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ((resultCode == Activity.RESULT_CANCELED) || data == null) return;

        String stringExtra;

        switch (requestCode) {
            case RequestCodes.AMOUNT:
                stringExtra = data.getStringExtra(CalculatorActivity.RESULT_AMOUNT);
                model.price = MoneyFactory.fromString(stringExtra);
                model.display(this, viewHolder);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        MenuHelper menuHelper = new MenuHelper(this, menu);
        menuHelper.addSaveToolbarIcon();

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // cancel clicked. Prompt to confirm?
                Timber.d("going back");
                break;
            case MenuHelper.save:
                // save & close
                save();
                setResult(Activity.RESULT_OK);
                finish();
                return onActionDoneClick();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        Icepick.saveInstanceState(this, savedInstanceState);
    }

    @OnClick(R.id.amountTextView)
    protected void onPriceClick() {
        Calculator.forActivity(this)
            .amount(model.price)
            .roundToCurrency(false)
            .show(RequestCodes.AMOUNT);
    }

    @OnClick(R.id.dateTextView)
    protected void onDateClick() {
        MmxDate priceDate = model.date;

        CalendarDatePickerDialogFragment.OnDateSetListener listener = new CalendarDatePickerDialogFragment.OnDateSetListener() {
            @Override
            public void onDateSet(CalendarDatePickerDialogFragment dialog, int year, int monthOfYear, int dayOfMonth) {
                model.date = new MmxDate(year, monthOfYear, dayOfMonth);

                model.display(PriceEditActivity.this, viewHolder);
            }
        };

        CalendarDatePickerDialogFragment datePicker = new CalendarDatePickerDialogFragment()
                .setFirstDayOfWeek(dateTimeUtilsLazy.get().getFirstDayOfWeek())
                .setOnDateSetListener(listener)
                .setPreselectedDate(priceDate.getYear(), priceDate.getMonthOfYear(), priceDate.getDayOfMonth());
        if (new UIHelper(this).isUsingDarkTheme()) {
            datePicker.setThemeDark();
        }
        datePicker.show(getSupportFragmentManager(), datePicker.getClass().getSimpleName());
    }

    @OnClick(R.id.previousDayButton)
    protected void onPreviousDayClick() {
        model.date = model.date.minusDays(1);

        model.display(this, viewHolder);
    }

    @OnClick(R.id.nextDayButton)
    protected void onNextDayClick() {
        model.date = model.date.plusDays(1);

        model.display(this, viewHolder);
    }

    private void initializeModel() {
        model = new PriceEditModel();

        // get parameters
        readParameters();
    }

    private void initializeToolbar() {
        // Title
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(getString(R.string.edit_price));

        // Back arrow / cancel.
        setDisplayHomeAsUpEnabled(true);
    }

    private void readParameters() {
        Intent intent = getIntent();
        if (intent == null) return;

        model.accountId = intent.getIntExtra(EditPriceDialog.ARG_ACCOUNT, Constants.NOT_SET);
        model.symbol = intent.getStringExtra(EditPriceDialog.ARG_SYMBOL);

        String priceString = intent.getStringExtra(EditPriceDialog.ARG_PRICE);
        model.price = MoneyFactory.fromString(priceString);

        String dateString = intent.getStringExtra(EditPriceDialog.ARG_DATE);
        model.date = new MmxDate(dateString);

        // currency!
        model.currencyId = intent.getIntExtra(ARG_CURRENCY_ID, Constants.NOT_SET);
    }

    private void save() {
        //update price
        StockRepository repo = new StockRepository(this);
        repo.updateCurrentPrice(model.symbol, model.price);

        StockHistoryRepository historyRepository = new StockHistoryRepository(this);
        boolean result = historyRepository.addStockHistoryRecord(model);
        if (!result) {
            Toast.makeText(this, getString(R.string.error_update_currency_exchange_rate),
                    Toast.LENGTH_SHORT).show();
        }

        new SyncManager(this).dataChanged();
    }
}
