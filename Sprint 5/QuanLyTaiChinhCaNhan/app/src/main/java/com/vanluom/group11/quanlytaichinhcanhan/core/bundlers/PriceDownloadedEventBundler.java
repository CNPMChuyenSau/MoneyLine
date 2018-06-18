
package com.vanluom.group11.quanlytaichinhcanhan.core.bundlers;

import android.os.Bundle;
import android.os.Parcelable;

import com.vanluom.group11.quanlytaichinhcanhan.investment.events.PriceDownloadedEvent;

import org.parceler.Parcels;

import icepick.Bundler;

/**
 * Bundler PriceDownloadedEvent with IcePick.
 */

public class PriceDownloadedEventBundler
        implements Bundler<PriceDownloadedEvent> {
    @Override
    public void put(String key, PriceDownloadedEvent event, Bundle bundle) {
        bundle.putParcelable(key, Parcels.wrap(event));
    }

    @Override
    public PriceDownloadedEvent get(String key, Bundle bundle) {
        Parcelable value = bundle.getParcelable(key);
        return Parcels.unwrap(value);
    }
}
