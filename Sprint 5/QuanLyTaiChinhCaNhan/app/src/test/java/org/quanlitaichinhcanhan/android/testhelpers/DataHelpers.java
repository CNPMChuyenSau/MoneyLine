package org.quanlitaichinhcanhan.android.testhelpers;

import android.content.Context;

import com.vanluom.group11.quanlytaichinhcanhan.Constants;
import com.vanluom.group11.quanlytaichinhcanhan.account.AccountStatuses;
import com.vanluom.group11.quanlytaichinhcanhan.account.AccountTypes;
import com.vanluom.group11.quanlytaichinhcanhan.core.TransactionTypes;
import com.vanluom.group11.quanlytaichinhcanhan.currency.CurrencyService;
import com.vanluom.group11.quanlytaichinhcanhan.datalayer.AccountRepository;
import com.vanluom.group11.quanlytaichinhcanhan.datalayer.AccountTransactionRepository;
import com.vanluom.group11.quanlytaichinhcanhan.datalayer.AssetClassRepository;
import com.vanluom.group11.quanlytaichinhcanhan.datalayer.AssetClassStockRepository;
import com.vanluom.group11.quanlytaichinhcanhan.datalayer.PayeeRepository;
import com.vanluom.group11.quanlytaichinhcanhan.datalayer.SplitCategoriesRepository;
import com.vanluom.group11.quanlytaichinhcanhan.datalayer.StockRepository;
import com.vanluom.group11.quanlytaichinhcanhan.domainmodel.Account;
import com.vanluom.group11.quanlytaichinhcanhan.domainmodel.AccountTransaction;
import com.vanluom.group11.quanlytaichinhcanhan.domainmodel.AssetClass;
import com.vanluom.group11.quanlytaichinhcanhan.domainmodel.AssetClassStock;
import com.vanluom.group11.quanlytaichinhcanhan.domainmodel.Currency;
import com.vanluom.group11.quanlytaichinhcanhan.domainmodel.Payee;
import com.vanluom.group11.quanlytaichinhcanhan.domainmodel.SplitCategory;
import com.vanluom.group11.quanlytaichinhcanhan.domainmodel.Stock;
import com.vanluom.group11.quanlytaichinhcanhan.servicelayer.AccountService;
import com.vanluom.group11.quanlytaichinhcanhan.servicelayer.PayeeService;

import info.javaperformance.money.Money;
import info.javaperformance.money.MoneyFactory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.robolectric.Shadows.shadowOf;

/**
 * Database manipulation. Used for test preparation.
 */
public class DataHelpers {
    public static void insertData() {
        Context context = UnitTestHelper.getContext();

        // add account

        AccountRepository accountRepository = new AccountRepository(context);
        // Bahraini dinar
        Account account = Account.create("cash", AccountTypes.CHECKING, AccountStatuses.OPEN,
                true, 17);
        int accountId = accountRepository.add(account);
        account.setId(accountId);
        assertThat(accountId).isNotEqualTo(Constants.NOT_SET);

        // add payees

        PayeeRepository repo = new PayeeRepository(context);
        for (int i = 0; i < 3; i++) {
            Payee payee = new Payee();
            payee.setName("payee" + i);
            int payeeId = repo.add(payee);
            assertThat(payeeId).isNotEqualTo(Constants.NOT_SET);
        }

        // add transactions

        for (int i = 0; i < 3; i++) {
            Money amount = MoneyFactory.fromString("-" + i);
            // this is semantically wrong as there is no category & subcategory!
            createTransaction(accountId, 1, TransactionTypes.Withdrawal, -1, -1, amount);
        }
    }

    public static void createTransaction(int accountId, int payeeId, TransactionTypes type,
                                         int categoryId, int subCategoryId, Money amount) {
        AccountTransactionRepository txRepo = new AccountTransactionRepository(UnitTestHelper.getContext());

        AccountTransaction tx = AccountTransaction.create(accountId, payeeId, type,
                categoryId, subCategoryId, amount);
        int txId = txRepo.add(tx);
        assertThat(txId).isNotEqualTo(Constants.NOT_SET);

    }

    public static void createSplitTransaction() {
        Context context = UnitTestHelper.getContext();

        // currency
        CurrencyService currencyService = new CurrencyService(context);
        Currency euro = currencyService.getCurrency("EUR");
        // account
        AccountService accountService = new AccountService(context);
        Account account = accountService.createAccount("only", AccountTypes.CHECKING, AccountStatuses.OPEN,
            true, euro.getCurrencyId());
        // payee
        PayeeService payeeService = new PayeeService(context);
        Payee payee = payeeService.createNew("zdravko colic");
        // transaction
        Money amount = MoneyFactory.fromDouble(100);
        AccountTransactionRepository txRepo = new AccountTransactionRepository(context);
        AccountTransaction tx = AccountTransaction.create(account.getId(), payee.getId(),
            TransactionTypes.Withdrawal, 1, -1, amount);
        txRepo.insert(tx);
        // split categories
        SplitCategoriesRepository splitRepo = new SplitCategoriesRepository(context);
        SplitCategory split1 = SplitCategory.create(tx.getId(), 1, -1,
                tx.getTransactionType(), MoneyFactory.fromDouble(25));
        splitRepo.insert(split1);
        SplitCategory split2 = SplitCategory.create(tx.getId(), 1, -1,
                tx.getTransactionType(), MoneyFactory.fromDouble(25));
        splitRepo.insert(split2);
    }

//    private static void setFakeCursor() {
//        ContentResolver resolver = UnitTestHelper.getContext().getContentResolver();
//        ShadowContentResolver shadow = shadowOf(resolver);
//
//        BaseCursor cursor = new AccountCursor();
//        shadow.setCursor(cursor);
//    }

    public static void createAllocation() {
        Context context = UnitTestHelper.getContext();
        AssetClassRepository repo = new AssetClassRepository(context);
        AssetClassStockRepository linkRepo = new AssetClassStockRepository(context);
        StockRepository stockRepo = new StockRepository(context);
        AccountRepository accountRepo = new AccountRepository(context);

        // Currency
        CurrencyService currencyService = new CurrencyService(context);
        Currency eur = currencyService.getCurrency("EUR");

        // Investment account
        Account account = Account.create("investment", AccountTypes.INVESTMENT, AccountStatuses.OPEN,
            true, eur.getCurrencyId());
        accountRepo.save(account);
        int accountId = account.getId();

        // Stock symbols
        Stock stock = Stock.create();
        stock.setSymbol("VHY.ax");
        stock.setHeldAt(accountId);
        stockRepo.insert(stock);

        AssetClass stocks = AssetClass.create("stocks");
        stocks.setAllocation(MoneyFactory.fromString("70"));
        repo.insert(stocks);

        AssetClassStock link = AssetClassStock.create(stocks.getId(), stock.getSymbol());
        linkRepo.insert(link);
    }
}
