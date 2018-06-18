package org.quanlitaichinhcanhan.android.tests;

import android.content.Context;

import com.vanluom.group11.quanlytaichinhcanhan.BuildConfig;
import com.vanluom.group11.quanlytaichinhcanhan.account.AccountStatuses;
import com.vanluom.group11.quanlytaichinhcanhan.account.AccountTypes;
import com.vanluom.group11.quanlytaichinhcanhan.datalayer.AccountRepository;
import com.vanluom.group11.quanlytaichinhcanhan.domainmodel.Account;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quanlitaichinhcanhan.android.testhelpers.UnitTestHelper;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test Account model.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class AccountTests {

    private Account account;

    @Before
    public void setup() {
        this.account = new Account();
    }

    @Test
    public void testInstantiation() {
        Account account = new Account();

        assertTrue(account != null);
    }

    @Test
    public void testPropertySetting() {
        final int id = 3;

        this.account.setId(id);

        int actual = this.account.getId();

        assertEquals(id, actual);
    }

    @Test
    public void testThrowException() {
        Integer actual = this.account.getId();

        assertNull(actual);
    }

    @Test
    public void canUpdateValueInDb() {
        // Given

        UnitTestHelper.setupContentProvider();
        Context context = UnitTestHelper.getContext();
        AccountRepository repo = new AccountRepository(context);
        Account account = Account.create("first", AccountTypes.CHECKING, AccountStatuses.OPEN,
            true, 1);
        String accountNumber = "blah blah";

        // When

        repo.save(account);
        Integer id = account.getId();

        Account loaded = repo.load(id);
        loaded.setAccountNumber(accountNumber);
        repo.save(loaded);

        loaded = repo.load(id);
        String actual = loaded.getAccountNumber();

        // Then
        assertThat(id).isEqualTo(1);
        assertThat(actual).isEqualTo(accountNumber);
    }
}
