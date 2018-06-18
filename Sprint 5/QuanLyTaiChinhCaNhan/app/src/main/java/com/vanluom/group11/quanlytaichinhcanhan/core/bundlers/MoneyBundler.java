
package com.vanluom.group11.quanlytaichinhcanhan.core.bundlers;

import android.os.Bundle;

import icepick.Bundler;
import info.javaperformance.money.Money;
import info.javaperformance.money.MoneyFactory;

/**
 * Custom bundler used to bundle the Money type and save instance state with IcePick.
 */

public class MoneyBundler
    implements Bundler<Money> {
    @Override
    public void put(String key, Money money, Bundle bundle) {
        bundle.putString(key, money.toString());
    }

    @Override
    public Money get(String key, Bundle bundle) {
        String value = bundle.getString(key);
        return MoneyFactory.fromString(value);
    }
}
