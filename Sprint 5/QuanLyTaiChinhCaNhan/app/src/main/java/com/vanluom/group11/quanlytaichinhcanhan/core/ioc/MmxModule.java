
package com.vanluom.group11.quanlytaichinhcanhan.core.ioc;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.vanluom.group11.quanlytaichinhcanhan.MoneyManagerApplication;
import com.vanluom.group11.quanlytaichinhcanhan.settings.AppSettings;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Main IoC module.
 * Modules provide functionality.
 */

@Module(
    includes = {
        DbModule.class,
        RepositoryModule.class
    }
)
public final class MmxModule {
    private final MoneyManagerApplication application;

    public MmxModule(MoneyManagerApplication application) {
        this.application = application;
    }

    @Provides @Singleton MoneyManagerApplication provideApplication() {
        return application;
    }

    @Provides @Singleton
    Context provideAppContext() {
        return application;
    }

    @Provides @Singleton
    SharedPreferences provideSharedPreferences() {
//        return application.getSharedPreferences(PREFERENCES_FILE_NAME, 0);
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    @Provides @Singleton
    AppSettings provideAppSettings(MoneyManagerApplication application) {
        return new AppSettings(application);
    }
}
