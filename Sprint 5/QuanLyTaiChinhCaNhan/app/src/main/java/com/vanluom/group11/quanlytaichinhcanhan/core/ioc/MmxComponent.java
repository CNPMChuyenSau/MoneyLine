
package com.vanluom.group11.quanlytaichinhcanhan.core.ioc;

import com.vanluom.group11.quanlytaichinhcanhan.MmxContentProvider;
import com.vanluom.group11.quanlytaichinhcanhan.budget.BudgetAdapter;
import com.vanluom.group11.quanlytaichinhcanhan.common.CalculatorActivity;
import com.vanluom.group11.quanlytaichinhcanhan.core.Core;
import com.vanluom.group11.quanlytaichinhcanhan.core.FormatUtilities;
import com.vanluom.group11.quanlytaichinhcanhan.core.Passcode;
import com.vanluom.group11.quanlytaichinhcanhan.core.UIHelper;
import com.vanluom.group11.quanlytaichinhcanhan.currency.CurrencyService;
import com.vanluom.group11.quanlytaichinhcanhan.currency.list.CurrencyListFragment;
import com.vanluom.group11.quanlytaichinhcanhan.datalayer.StockHistoryRepositorySql;
import com.vanluom.group11.quanlytaichinhcanhan.datalayer.StockRepositorySql;
import com.vanluom.group11.quanlytaichinhcanhan.home.HomeFragment;
import com.vanluom.group11.quanlytaichinhcanhan.home.MainActivity;
import com.vanluom.group11.quanlytaichinhcanhan.home.SelectDatabaseActivity;
import com.vanluom.group11.quanlytaichinhcanhan.investment.EditPriceDialog;
import com.vanluom.group11.quanlytaichinhcanhan.investment.ISecurityPriceUpdater;
import com.vanluom.group11.quanlytaichinhcanhan.investment.InvestmentTransactionEditActivity;
import com.vanluom.group11.quanlytaichinhcanhan.investment.PriceEditActivity;
import com.vanluom.group11.quanlytaichinhcanhan.investment.PriceEditModel;
import com.vanluom.group11.quanlytaichinhcanhan.investment.morningstar.MorningstarPriceUpdater;
import com.vanluom.group11.quanlytaichinhcanhan.recurring.transactions.RecurringTransactionEditActivity;
import com.vanluom.group11.quanlytaichinhcanhan.recurring.transactions.RecurringTransactionListFragment;
import com.vanluom.group11.quanlytaichinhcanhan.reports.BaseReportFragment;
import com.vanluom.group11.quanlytaichinhcanhan.search.SearchParametersFragment;
import com.vanluom.group11.quanlytaichinhcanhan.servicelayer.InfoService;
import com.vanluom.group11.quanlytaichinhcanhan.settings.AppSettings;
import com.vanluom.group11.quanlytaichinhcanhan.settings.DatabaseSettingsFragment;
import com.vanluom.group11.quanlytaichinhcanhan.settings.SettingsActivity;
import com.vanluom.group11.quanlytaichinhcanhan.sync.SyncManager;
import com.vanluom.group11.quanlytaichinhcanhan.sync.SyncPreferenceFragment;
import com.vanluom.group11.quanlytaichinhcanhan.sync.SyncService;
import com.vanluom.group11.quanlytaichinhcanhan.sync.SyncServiceMessageHandler;
import com.vanluom.group11.quanlytaichinhcanhan.transactions.CheckingTransactionEditActivity;
import com.vanluom.group11.quanlytaichinhcanhan.transactions.EditTransactionCommonFunctions;
import com.vanluom.group11.quanlytaichinhcanhan.utils.MmxDatabaseUtils;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Main Dagger 2 Component. Represents the link between the Modules and Injections.
 * Component consumes functionality.
 */
@Singleton
@Component(modules = MmxModule.class)
public interface MmxComponent {
    // Example on how to expose a provision method.
//    MmxOpenHelper getHelper();

    // Activities
    void inject(CalculatorActivity activity);
    void inject(CheckingTransactionEditActivity activity);
    void inject(InvestmentTransactionEditActivity activity);
    void inject(MainActivity activity);
    void inject(PriceEditActivity activity);
    void inject(RecurringTransactionEditActivity activity);
    void inject(SelectDatabaseActivity activity);
    void inject(SettingsActivity activity);

    // Fragments
    void inject(BaseReportFragment fragment);
    void inject(CurrencyListFragment fragment);
    void inject(DatabaseSettingsFragment fragment);
    void inject(HomeFragment fragment);
    void inject(RecurringTransactionListFragment fragment);
    void inject(SearchParametersFragment fragment);
    void inject(SyncPreferenceFragment fragment);

    // Dialogs
    void inject(EditPriceDialog dialog);

    // Models
    void inject(PriceEditModel model);

    // Custom objects
    void inject(ISecurityPriceUpdater updater);
    void inject(MorningstarPriceUpdater updater);
    void inject(AppSettings settings);
    void inject(Core core);
    void inject(MmxContentProvider provider);
    void inject(MmxDatabaseUtils utils);
    void inject(SyncManager sync);
    void inject(SyncServiceMessageHandler handler);
    void inject(Passcode object);
    void inject(EditTransactionCommonFunctions object);

    // Helpers
    void inject(UIHelper helper);
    void inject(FormatUtilities utils);

    // Business Services
    void inject(CurrencyService service);
    void inject(InfoService service);

    // Intent Services
    void inject(SyncService service);

    // Repositories
    void inject(StockRepositorySql repository);
    void inject(StockHistoryRepositorySql repository);

    // Adapters
    void inject(BudgetAdapter adapter);
}
