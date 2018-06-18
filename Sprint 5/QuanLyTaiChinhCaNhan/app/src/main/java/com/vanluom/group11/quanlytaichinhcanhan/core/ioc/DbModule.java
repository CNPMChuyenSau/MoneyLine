
package com.vanluom.group11.quanlytaichinhcanhan.core.ioc;

import com.vanluom.group11.quanlytaichinhcanhan.MoneyManagerApplication;
import com.vanluom.group11.quanlytaichinhcanhan.database.MmxOpenHelper;
import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;

import dagger.Module;
import dagger.Provides;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Module for database access.
 */
@Module
public final class DbModule {
//    private MmxOpenHelper instance;

//    @Provides SQLiteOpenHelper provideOpenHelper(Application application, AppSettings appSettings) {
//        return MmxOpenHelper.createNewInstance(application);
        // Use the existing singleton instance.
        //return MmxOpenHelper.getInstance(application);
//    }

//    @Provides MmxOpenHelper provideOpenHelper(Application application) {
//        if (instance == null) {
//            instance = createInstance(application);
//        } else {
//            // See whether to reinitialize
//            String currentPath = instance.getDatabaseName();
//            String newPath = MoneyManagerApplication.getDatabasePath(application);
//
//            if (!currentPath.equals(newPath)) {
//                instance.close();
//                instance = createInstance(application);
//            }
//        }
//        return instance;
//    }

    /**
     * Keeping the open helper reference in the application instance.
     * @param app Instance of application object (context).
     * @return Open Helper (Database) instance.
     */
    @Provides
//    @Named("instance")
    MmxOpenHelper provideOpenHelper(MoneyManagerApplication app) {
//        MoneyManagerApplication app = MoneyManagerApplication.getInstance();
        if (app.openHelperAtomicReference == null) {
            app.initDb(null);
        }
        return app.openHelperAtomicReference.get();
    }

//    private MmxOpenHelper createInstance(Application application) {
//        String dbPath = MoneyManagerApplication.getDatabasePath(application);
//        return new MmxOpenHelper(application, dbPath);
//    }

    @Provides SqlBrite provideSqlBrite() {
        return SqlBrite.create(new SqlBrite.Logger() {
            @Override public void log(String message) {
                Timber.tag("Database").v(message);
            }
        });
    }

    @Provides BriteDatabase provideDatabase(SqlBrite sqlBrite, MmxOpenHelper helper) {
        BriteDatabase db = sqlBrite.wrapDatabaseHelper(helper, Schedulers.io());
        db.setLoggingEnabled(true);
        return db;
    }
}
