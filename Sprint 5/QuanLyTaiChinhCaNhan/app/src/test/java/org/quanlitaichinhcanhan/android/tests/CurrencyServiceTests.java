package org.quanlitaichinhcanhan.android.tests;

import android.content.Context;

import com.vanluom.group11.quanlytaichinhcanhan.BuildConfig;
import com.vanluom.group11.quanlytaichinhcanhan.currency.CurrencyService;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quanlitaichinhcanhan.android.testhelpers.UnitTestHelper;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test Currency Service
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class CurrencyServiceTests {

    private CurrencyService testObject;

    @Before
    public void setup() {
        // initialize database
        UnitTestHelper.setupContentProvider();

        Context context = UnitTestHelper.getContext();
        testObject = new CurrencyService(context);
    }

    @After
    public void tearDown() {
        testObject = null;

        // Reset database instance between tests.
        UnitTestHelper.teardownDatabase();
    }

    @Test
    public void instantiation() {
        assertThat(testObject).isNotNull();
    }

    @Test
    public void fetchingIdsBySymbol() {
        // Given

        // When
        Integer id = testObject.getIdForCode("EUR");

        // Then
        assertThat(id).isNotNull();
        assertThat(id).isEqualTo(2);
    }

    @Test
    public void fetchingSymbolById() {
        String symbol = testObject.getSymbolFor(2);

        assertThat(symbol).isNotNull();
        assertThat(symbol).isEqualTo("EUR");
    }

    @Test
    public void gettingDefaultCurrency() {
        // Given

        // When
        String actual = this.testObject.getBaseCurrencyCode();

        // Then
        //assertThat(actual).isEqualTo("EUR");
        assertThat(actual).isNotEmpty();
    }
}
