package com.vanluom.group11.quanlytaichinhcanhan;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.preference.PreferenceManager;
import android.support.multidex.MultiDexApplication;
import android.text.TextUtils;
import android.widget.TextView;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.core.CrashlyticsCore;
import com.mikepenz.iconics.Iconics;
import com.mikepenz.mmex_icon_font_typeface_library.MMXIconFont;
import com.vanluom.group11.quanlytaichinhcanhan.common.MoneyParcelConverter;
import com.vanluom.group11.quanlytaichinhcanhan.core.InfoKeys;
import com.vanluom.group11.quanlytaichinhcanhan.core.UIHelper;
import com.vanluom.group11.quanlytaichinhcanhan.core.ioc.DaggerMmxComponent;
import com.vanluom.group11.quanlytaichinhcanhan.core.ioc.MmxComponent;
import com.vanluom.group11.quanlytaichinhcanhan.core.ioc.MmxModule;
import com.vanluom.group11.quanlytaichinhcanhan.database.MmxOpenHelper;
import com.vanluom.group11.quanlytaichinhcanhan.log.CrashReportingTree;
import com.vanluom.group11.quanlytaichinhcanhan.database.QueryAccountBills;
import com.vanluom.group11.quanlytaichinhcanhan.log.DebugTree;
import com.vanluom.group11.quanlytaichinhcanhan.servicelayer.InfoService;
import com.vanluom.group11.quanlytaichinhcanhan.settings.AppSettings;
import com.vanluom.group11.quanlytaichinhcanhan.settings.DatabaseSettings;
import com.vanluom.group11.quanlytaichinhcanhan.settings.LookAndFeelSettings;
import com.vanluom.group11.quanlytaichinhcanhan.settings.PreferenceConstants;
import com.vanluom.group11.quanlytaichinhcanhan.utils.MmxDatabaseUtils;
import com.vanluom.group11.quanlytaichinhcanhan.view.RobotoView;
import com.shamanland.fonticon.FontIconTypefaceHolder;

import io.fabric.sdk.android.Fabric;

import org.parceler.Parcel;
import org.parceler.ParcelClass;
import org.parceler.ParcelClasses;

import java.io.File;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicReference;

import info.javaperformance.money.Money;
import timber.log.Timber;

/**
 * Here we define the parcel converter for Money type.
 */
@ParcelClasses(
    @ParcelClass(
        value = Money.class,
        annotation = @Parcel(converter = MoneyParcelConverter.class))
)
public class MoneyManagerApplication
    extends MultiDexApplication {

    private static MoneyManagerApplication appInstance;
    private static float mTextSize;
    private static String userName = "";

    public static MoneyManagerApplication getApp() {
        return appInstance;
    }

    /**
     * Reads the current database path from the preferences and checks for the existence of the
     * database file.
     * Creates a default database file if the one from preferences is not found. Sets this file as
     * the default database.
     * @param context Executing context.
     * @return Full path to the current database file.
     */
    public static String getDatabasePath(Context context) {
        // todo: move this to the recent db provider

        DatabaseSettings dbSettings = new AppSettings(context).getDatabaseSettings();
        String databasePath = dbSettings.getDatabasePath();

        if (!TextUtils.isEmpty(databasePath)) {
            // Use the db path stored in the preferences.
            File databaseFile = new File(databasePath);
            if (databaseFile.getAbsoluteFile().exists())  {
                return databaseFile.getPath();
            }
        }

        // otherwise try other paths or create the default database.

        String defaultPath = new MmxDatabaseUtils(context).getDefaultDatabasePath();

        // Save db path to preferences.
        dbSettings.setDatabasePath(defaultPath);

        // Show notification
        if (databasePath.equals(defaultPath)) {
            new UIHelper(context).showToast("Default database file will be created at " + defaultPath);
        } else {
            new UIHelper(context).showToast("Database " + databasePath + " not found. Using default:" + defaultPath);
        }

        return defaultPath;
    }

    public static float getTextSize() {
        return MoneyManagerApplication.mTextSize;
    }

    public static void setTextSize(float textSize) {
        MoneyManagerApplication.mTextSize = textSize;
    }

    /**
     * close process application
     */
    public static void killApplication() {
        // close application
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    // Instance fields.

    public MmxComponent iocComponent;
    public AtomicReference<MmxOpenHelper> openHelperAtomicReference;

    // Overrides.

    @Override
    public void onCreate() {
        super.onCreate();

        // update instance of application
        appInstance = this;

        // set default text size.
        setTextSize(new TextView(getApplicationContext()).getTextSize());

        // Font
        SharedPreferences appPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        RobotoView.setUserFont(Integer.parseInt(
            appPreferences.getString(getString(PreferenceConstants.PREF_APPLICATION_FONT), "-1")));
        RobotoView.setUserFontSize(getApplicationContext(),
            appPreferences.getString(getString(PreferenceConstants.PREF_APPLICATION_FONT_SIZE), "default"));

        registerCustomFonts();

        // Initialize Joda Time
//        JodaTimeAndroid.init(this);

        // Exception reporting. Disabled for debug builds.
        Crashlytics crashlyticsKit = new Crashlytics.Builder()
                .core(new CrashlyticsCore.Builder().disabled(BuildConfig.DEBUG).build())
                .build();
        Fabric.with(this, crashlyticsKit); // new Crashlytics()

        // Loggers
        if (BuildConfig.DEBUG) {
            Timber.plant(new DebugTree());
        } else {
            Timber.plant(new CrashReportingTree());
        }

        initializeDependencyInjection();
    }

    /**
     * Initialize Dagger 2 module(s).
     */
    private void initializeDependencyInjection() {
        // Dependency Injection. IoC
        iocComponent = DaggerMmxComponent.builder()
                .mmxModule(new MmxModule(appInstance))
                .build();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();

        // on terminate is never called
        // ref: http://stackoverflow.com/questions/15162562/application-lifecycle
        Timber.d("Application terminated");
    }

    @Override
    public void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        // Trying to mitigate issues on some 4.2.2 devices
        // https://code.google.com/p/android/issues/detail?id=78377
        // ref: https://developer.android.com/tools/building/multidex.html
//        MultiDex.install(this);
    }

    // dynamic

    public void initDb(String path) {
        MmxOpenHelper db = createDbInstance(path);

        if (openHelperAtomicReference == null) {
            openHelperAtomicReference = new AtomicReference<>(db);
        } else {
            // close existing db
            openHelperAtomicReference.get().close();
            openHelperAtomicReference.set(db);
        }
    }

    private MmxOpenHelper createDbInstance(String path) {
        if (TextUtils.isEmpty(path)) {
//            path = new MmxDatabaseUtils(this).getDefaultDatabasePath();
            path = getDatabasePath(this);
        }
        return new MmxOpenHelper(this, path);
    }

    public Locale getAppLocale() {
        Locale locale = null;
        Context context = getApplicationContext();

        String language = new AppSettings(context).getGeneralSettings().getApplicationLanguage();

        if(!TextUtils.isEmpty(language)) {
            try {
                locale = new Locale(language);
            } catch (Exception e) {
                Timber.e(e, "parsing locale: %s", language);
            }
        }

        // in case the above failed
        if (locale == null) {
            // use the default locale.
            locale = context.getResources().getConfiguration().locale;
        }
        if (locale == null) {
            locale = Locale.getDefault();
        }

        return locale;
    }

    public boolean setUserName(String userName) {
        return this.setUserName(userName, false);
    }

    /**
     * @param userName the userName to set
     * @param save     update into database
     * @deprecated Use Info Service directly to read and write this value as it is used only in
     * the main activity.
     */
    @Deprecated
    public boolean setUserName(String userName, boolean save) {
        MoneyManagerApplication.userName = userName;

        if (save) {
            InfoService service = new InfoService(this.getApplicationContext());
            boolean updateSuccessful = service.setInfoValue(InfoKeys.USERNAME, userName);

            if (!updateSuccessful) {
                return false;
            }
        }

        return true;
    }

    public String loadUserNameFromDatabase(Context context) {
        InfoService service = new InfoService(context);
        String username = service.getInfoValue(InfoKeys.USERNAME);

        String result = TextUtils.isEmpty(username) ? "" : username;

        return result;
    }

    /**
     * Compute account balance and returns balance
     *
     * @param context Executing context
     * @return total
     */
    public double getSummaryAccounts(Context context) {
        try {
            return getSummaryAccountsInternal(context);
        } catch (Exception e) {
            Timber.e(e, "getting summary accounts");
        }
        return 0;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

//    public boolean isUriAvailable(Context context, Intent intent) {
//        return context.getPackageManager().resolveActivity(intent, 0) != null;
//    }

    /*
        Private
    */

    private double getSummaryAccountsInternal(Context context) {
        double curTotal = 0;

        LookAndFeelSettings settings = new AppSettings(context)
                .getLookAndFeelSettings();
        // compose whereClause
        String where = "";
        // check if show only open accounts
        if (settings.getViewOpenAccounts()) {
            where = "LOWER(STATUS)='open'";
        }
        // check if show fav accounts
        if (settings.getViewFavouriteAccounts()) {
            where = "LOWER(FAVORITEACCT)='true'";
        }
        QueryAccountBills accountBills = new QueryAccountBills(context);
        Cursor cursor = context.getContentResolver().query(accountBills.getUri(),
                null,
                where,
                null,
                null);
        if (cursor == null) return 0;

        // calculate summary
        while (cursor.moveToNext()) {
            curTotal = curTotal + cursor.getDouble(cursor.getColumnIndex(QueryAccountBills.TOTALBASECONVRATE));
        }
        cursor.close();

        return curTotal;
    }

    private void registerCustomFonts() {
        String iconFontPath = "fonts/mmex.ttf";

        // Font icons
        Iconics.registerFont(new MMXIconFont());

        // Initialize font icons support.
        FontIconTypefaceHolder.init(getAssets(), iconFontPath);
    }

}
