package org.quanlitaichinhcanhan.android.tests;

import android.content.Context;

import com.vanluom.group11.quanlytaichinhcanhan.BuildConfig;
import com.vanluom.group11.quanlytaichinhcanhan.account.AccountStatuses;
import com.vanluom.group11.quanlytaichinhcanhan.account.AccountTypes;
import com.vanluom.group11.quanlytaichinhcanhan.currency.CurrencyService;
import com.vanluom.group11.quanlytaichinhcanhan.datalayer.AccountRepository;
import com.vanluom.group11.quanlytaichinhcanhan.domainmodel.Account;
import com.vanluom.group11.quanlytaichinhcanhan.domainmodel.Currency;
import com.vanluom.group11.quanlytaichinhcanhan.servicelayer.AccountService;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quanlitaichinhcanhan.android.testhelpers.UnitTestHelper;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Account Service tests.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class AccountServiceTests {

    private AccountService testObject;

    @Before
    public void setup() {
        // initialize support for activities (UI)
//        this.controller = UnitTestHelper.getController(MainActivity.class);
//        this.activity = UnitTestHelper.getActivity(this.controller);

        // initialize database
        // UnitTestHelper.setupContentProvider();

        Context context = UnitTestHelper.getContext();
        testObject = new AccountService(context);
    }

    @After
    public void tearDown() {
        // Reset database instance between tests.
        UnitTestHelper.teardownDatabase();

        // Destroy the activity controller.
//        this.controller.destroy();

        testObject = null;
    }

    @Test
    public void instantiation() {
        assertThat(testObject).isNotNull();
    }

    @Test
    public void getAccountCurrency() {
        // Given
        UnitTestHelper.setupContentProvider();
        String expectedCode = "ISK";
        Context context = UnitTestHelper.getContext();
        CurrencyService currencyService = new CurrencyService(context);
        Currency currency = currencyService.getCurrency(expectedCode);
        AccountRepository repo = new AccountRepository(context);
        Account account = Account.create("bank account", AccountTypes.CHECKING, AccountStatuses.OPEN,
            false, currency.getCurrencyId());
        repo.save(account);
        int accountId = account.getId();

        // When
        String actual = testObject.getAccountCurrencyCode(accountId);

        // Then
        assertThat(actual).isEqualTo(expectedCode);
    }
}
