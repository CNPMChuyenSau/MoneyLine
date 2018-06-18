package com.vanluom.group11.quanlytaichinhcanhan.investment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.SpinnerAdapter;

import com.codetroopers.betterpickers.calendardatepicker.CalendarDatePickerDialogFragment;
import com.vanluom.group11.quanlytaichinhcanhan.Constants;
import com.vanluom.group11.quanlytaichinhcanhan.MoneyManagerApplication;
import com.vanluom.group11.quanlytaichinhcanhan.R;
import com.vanluom.group11.quanlytaichinhcanhan.common.Calculator;
import com.vanluom.group11.quanlytaichinhcanhan.common.MmxBaseFragmentActivity;
import com.vanluom.group11.quanlytaichinhcanhan.core.MenuHelper;
import com.vanluom.group11.quanlytaichinhcanhan.core.UIHelper;
import com.vanluom.group11.quanlytaichinhcanhan.datalayer.AccountRepository;
import com.vanluom.group11.quanlytaichinhcanhan.datalayer.StockRepository;
import com.vanluom.group11.quanlytaichinhcanhan.domainmodel.Account;
import com.vanluom.group11.quanlytaichinhcanhan.domainmodel.Stock;
import com.vanluom.group11.quanlytaichinhcanhan.servicelayer.AccountService;
import com.vanluom.group11.quanlytaichinhcanhan.utils.MmxDate;
import com.vanluom.group11.quanlytaichinhcanhan.utils.MmxDateTimeUtils;
import com.vanluom.group11.quanlytaichinhcanhan.utils.SpinnerHelper;
import com.vanluom.group11.quanlytaichinhcanhan.view.RobotoTextView;
import com.vanluom.group11.quanlytaichinhcanhan.view.RobotoTextViewFontIcon;

import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.Lazy;
import info.javaperformance.money.Money;
import info.javaperformance.money.MoneyFactory;

/**
 * Edit investment transaction (stock purchase).
 */
public class InvestmentTransactionEditActivity
    extends MmxBaseFragmentActivity {

    public static final String ARG_ACCOUNT_ID = "InvestmentTransactionEditActivity:AccountId";
    public static final String ARG_STOCK_ID = "InvestmentTransactionEditActivity:StockId";
    public static final String DATEPICKER_TAG = "datepicker";

    public static final int REQUEST_NUM_SHARES = 1;
    public static final int REQUEST_PURCHASE_PRICE = 2;
    public static final int REQUEST_COMMISSION = 3;
    public static final int REQUEST_CURRENT_PRICE = 4;

    @Inject Lazy<MmxDateTimeUtils> dateTimeUtilsLazy;

    private boolean mDirty = false;
    private Account mAccount;
    private Stock mStock;
    private InvestmentTransactionViewHolder mViewHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investment_transaction_edit);

        MoneyManagerApplication.getApp().iocComponent.inject(this);
        ButterKnife.bind(this);

        setDisplayHomeAsUpEnabled(true);

        // load account & currency
        Intent intent = getIntent();
        if (intent != null) {
            int accountId = intent.getIntExtra(ARG_ACCOUNT_ID, Constants.NOT_SET);
            if (accountId != Constants.NOT_SET) {
                AccountRepository repository = new AccountRepository(this);
                mAccount = repository.load(accountId);
            }

            int stockId = intent.getIntExtra(ARG_STOCK_ID, Constants.NOT_SET);
            if (stockId != Constants.NOT_SET) {
                StockRepository repo = new StockRepository(this);
                mStock = repo.load(stockId);
            } else {
                mStock = Stock.create();
                if (mAccount != null) {
                    mStock.setHeldAt(mAccount.getId());
                }
            }
        }

        initializeForm();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_CANCELED || data == null) return;

        Money amount = Calculator.getAmountFromResult(data);

        switch (requestCode) {
            case REQUEST_NUM_SHARES:
                mStock.setNumberOfShares(amount.toDouble());
                showNumberOfShares();
                showValue();
                break;

            case REQUEST_PURCHASE_PRICE:
                mStock.setPurchasePrice(amount);
                showPurchasePrice();

                if (mStock.getCurrentPrice().isZero()) {
                    mStock.setCurrentPrice(amount);
                    showCurrentPrice();
                    // recalculate value
                    showValue();
                }
                break;

            case REQUEST_COMMISSION:
                mStock.setCommission(amount);
                showCommission();
                break;

            case REQUEST_CURRENT_PRICE:
                mStock.setCurrentPrice(amount);
                showCurrentPrice();
                showValue();
                break;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        new MenuHelper(this, menu).addSaveToolbarIcon();

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically e clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case MenuHelper.save:
                return onActionDoneClick();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        onActionCancelClick();
    }

    @Override
    public boolean onActionCancelClick() {
        setResult(Activity.RESULT_CANCELED);
        finish();

        return true;
    }

    @Override
    public boolean onActionDoneClick() {
        if (save()) {
            // set result ok and finish activity
            setResult(RESULT_OK);
            finish();
            return true;
        } else {
            return false;
        }
    }

    public void setDirty(boolean dirty) {
        mDirty = dirty;
    }

    @OnClick(R.id.numSharesView)
    public void onNumSharesClick() {
        Money amount = MoneyFactory.fromDouble(mStock.getNumberOfShares());

        Calculator.forActivity(this)
                .amount(amount)
                .roundToCurrency(false)
                .show(REQUEST_NUM_SHARES);
    }

    @OnClick(R.id.purchasePriceView)
    public void onPurchasePriceClick() {
        if (mAccount == null) return;

        Calculator.forActivity(this)
                .roundToCurrency(false)
                .amount(mStock.getPurchasePrice())
                .currency(mAccount.getCurrencyId())
                .show(REQUEST_PURCHASE_PRICE);
    }

    @OnClick(R.id.commissionView)
    public void onCommissionClick() {
        Calculator.forActivity(this)
                .amount(mStock.getCommission())
                .currency(mAccount.getCurrencyId())
                .show(REQUEST_COMMISSION);
    }

    @OnClick(R.id.currentPriceView)
    public void onCurrentPriceClick() {
        Calculator.forActivity(this)
                .currency(mAccount.getCurrencyId())
                .amount(mStock.getCurrentPrice())
                .show(REQUEST_CURRENT_PRICE);
    }

    /*
        Private
     */

    private void collectData() {
        String stockName = mViewHolder.stockNameEdit.getText().toString().trim();
        mStock.setName(stockName);

        // Symbols are always uppercase.
        String symbol = mViewHolder.symbolEdit.getText().toString()
            .trim().replace(" ", "").toUpperCase();
        mStock.setSymbol(symbol);

        mStock.setNotes(mViewHolder.notesEdit.getText().toString());
    }

    private void displayStock(Stock stock, InvestmentTransactionViewHolder viewHolder) {
        if (mAccount == null) return;

        // Date
        String dateDisplay = new MmxDate(stock.getPurchaseDate()).toString(Constants.LONG_DATE_PATTERN);
        viewHolder.dateView.setText(dateDisplay);

        // Account.
        Cursor cursor = ((CursorAdapter) viewHolder.accountSpinner.getAdapter()).getCursor();
        int accountIndex = SpinnerHelper.getPosition(mAccount.getName(), Account.ACCOUNTNAME, cursor);
        if (accountIndex >= 0) {
            viewHolder.accountSpinner.setSelection(accountIndex, true);
        }

        viewHolder.stockNameEdit.setText(stock.getName());
        viewHolder.symbolEdit.setText(stock.getSymbol());

        showNumberOfShares();
        showPurchasePrice();
        viewHolder.notesEdit.setText(stock.getNotes());
        showCommission();
        showCurrentPrice();
        showValue();
    }

    private void initializeForm() {
        View rootView = this.findViewById(R.id.content);
        mViewHolder = new InvestmentTransactionViewHolder(rootView);

        initDateControl(mViewHolder);
        initAccountSelectors(mViewHolder);

        displayStock(mStock, mViewHolder);
    }

    /**
     * Initialize account selectors.
     */
    private void initAccountSelectors(final InvestmentTransactionViewHolder viewHolder) {
        Context context = this;

        // Account list as the data source to populate the drop-downs.

        AccountService accountService = new AccountService(context);
        accountService.loadInvestmentAccountsToSpinner(viewHolder.accountSpinner, false);

//        AccountRepository accountRepository = new AccountRepository(context);
        final Integer accountId = mStock.getHeldAt();
//        if (accountId != null) {
//            addMissingAccountToSelectors(accountRepository, accountId);
//        }

        AdapterView.OnItemSelectedListener listener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SpinnerAdapter adapter = viewHolder.accountSpinner.getAdapter();
                Cursor cursor = (Cursor) adapter.getItem(position);
                Account account = Account.from(cursor);

                if (!account.getId().equals(accountId)) {
                    setDirty(true);
                    mStock.setHeldAt(account.getId());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        viewHolder.accountSpinner.setOnItemSelectedListener(listener);
    }

    private void initDateControl(final InvestmentTransactionViewHolder viewHolder) {
        // Purchase Date

        viewHolder.dateView.setOnClickListener(new View.OnClickListener() {
            CalendarDatePickerDialogFragment.OnDateSetListener listener = new CalendarDatePickerDialogFragment.OnDateSetListener() {
                @Override
                public void onDateSet(CalendarDatePickerDialogFragment dialog, int year, int monthOfYear, int dayOfMonth) {
                    setDirty(true);

                    MmxDate dateTime = new MmxDate(year, monthOfYear, dayOfMonth);
                    viewHolder.dateView.setText(dateTime.toString(Constants.LONG_DATE_PATTERN));
                }
            };

            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(mStock.getPurchaseDate());

                CalendarDatePickerDialogFragment datePicker = new CalendarDatePickerDialogFragment()
                        .setFirstDayOfWeek(dateTimeUtilsLazy.get().getFirstDayOfWeek())
                        .setOnDateSetListener(listener)
                        .setPreselectedDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                if (new UIHelper(InvestmentTransactionEditActivity.this).isUsingDarkTheme()) {
                    datePicker.setThemeDark();
                }
                datePicker.show(getSupportFragmentManager(), DATEPICKER_TAG);
            }
        });
        // prev/next day
        viewHolder.previousDayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MmxDate dateTime = new MmxDate(mStock.getPurchaseDate()).minusDays(1);
                setDate(dateTime.toDate());
            }
        });
        viewHolder.nextDayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MmxDate dateTime = new MmxDate(mStock.getPurchaseDate()).plusDays(1);
                setDate(dateTime.toDate());
            }
        });
    }

    private void showCommission() {
        RobotoTextView view = (RobotoTextView) this.findViewById(R.id.commissionView);
        if (view == null) return;

        // todo: format the number of shares based on selected locale.
        view.setText(mStock.getCommission().toString());
    }

    private void showCurrentPrice() {
        RobotoTextView view = (RobotoTextView) this.findViewById(R.id.currentPriceView);
        // todo: format the number of shares based on selected locale.
        view.setText(mStock.getCurrentPrice().toString());
    }

    private void showNumberOfShares() {
        RobotoTextViewFontIcon view = (RobotoTextViewFontIcon) this.findViewById(R.id.numSharesView);
        if (view == null) return;

        // todo: format the number of shares based on selected locale?

        view.setText(mStock.getNumberOfShares().toString());
    }

    private void showPurchasePrice() {
        RobotoTextView view = (RobotoTextView) this.findViewById(R.id.purchasePriceView);
        // todo: format the number of shares based on selected locale.
        view.setText(mStock.getPurchasePrice().toString());
    }

    private void showValue() {
        RobotoTextView view = (RobotoTextView) this.findViewById(R.id.valueView);
        //mViewHolder.
        view.setText(mStock.getValue().toString());
    }

    private boolean save() {
        collectData();

        if (!validate()) return false;

        // update
        StockRepository repository = new StockRepository(getApplicationContext());
        if (mStock.getId() != null) {
            repository.save(mStock);
        } else {
            repository.insert(mStock);
        }

        return true;
    }

    private void setDate(Date dateTime) {
        setDirty(true);

        mStock.setPurchaseDate(dateTime);

        showDate(dateTime);
    }

    private void showDate(Date date) {
        String display = new MmxDate(date).toString(Constants.LONG_DATE_PATTERN);
        mViewHolder.dateView.setText(display);
    }

    private boolean validate() {
        // symbol must not be empty.
        if (TextUtils.isEmpty(mStock.getSymbol())) {
            new UIHelper(this).showToast(getString(R.string.symbol_required));
            return false;
        }

        // number of shares, price?

        return true;
    }
}
