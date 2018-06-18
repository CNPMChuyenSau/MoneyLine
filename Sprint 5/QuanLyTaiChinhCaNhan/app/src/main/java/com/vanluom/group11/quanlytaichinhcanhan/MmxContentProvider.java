package com.vanluom.group11.quanlytaichinhcanhan;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
//import net.sqlcipher.database.SQLiteDatabase;
//import net.sqlcipher.database.SQLiteQueryBuilder;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.util.SparseArrayCompat;
import android.text.TextUtils;

import com.vanluom.group11.quanlytaichinhcanhan.budget.BudgetQuery;
import com.vanluom.group11.quanlytaichinhcanhan.currency.CurrencyRepository;
import com.vanluom.group11.quanlytaichinhcanhan.database.Dataset;
import com.vanluom.group11.quanlytaichinhcanhan.database.DatasetType;
import com.vanluom.group11.quanlytaichinhcanhan.database.MmxOpenHelper;
import com.vanluom.group11.quanlytaichinhcanhan.database.QueryAccountBills;
import com.vanluom.group11.quanlytaichinhcanhan.database.QueryAllData;
import com.vanluom.group11.quanlytaichinhcanhan.database.QueryBillDeposits;
import com.vanluom.group11.quanlytaichinhcanhan.database.QueryCategorySubCategory;
import com.vanluom.group11.quanlytaichinhcanhan.database.QueryReportIncomeVsExpenses;
import com.vanluom.group11.quanlytaichinhcanhan.database.SQLDataSet;
import com.vanluom.group11.quanlytaichinhcanhan.database.ViewMobileData;
import com.vanluom.group11.quanlytaichinhcanhan.datalayer.AccountRepository;
import com.vanluom.group11.quanlytaichinhcanhan.datalayer.AccountTransactionRepository;
import com.vanluom.group11.quanlytaichinhcanhan.datalayer.AssetClassRepository;
import com.vanluom.group11.quanlytaichinhcanhan.datalayer.AssetClassStockRepository;
import com.vanluom.group11.quanlytaichinhcanhan.datalayer.BudgetEntryRepository;
import com.vanluom.group11.quanlytaichinhcanhan.datalayer.BudgetRepository;
import com.vanluom.group11.quanlytaichinhcanhan.datalayer.CategoryRepository;
import com.vanluom.group11.quanlytaichinhcanhan.datalayer.PayeeRepository;
import com.vanluom.group11.quanlytaichinhcanhan.datalayer.RecurringTransactionRepository;
import com.vanluom.group11.quanlytaichinhcanhan.datalayer.SplitCategoriesRepository;
import com.vanluom.group11.quanlytaichinhcanhan.datalayer.SplitRecurringCategoriesRepository;
import com.vanluom.group11.quanlytaichinhcanhan.datalayer.StockRepository;
import com.vanluom.group11.quanlytaichinhcanhan.datalayer.SubcategoryRepository;
import com.vanluom.group11.quanlytaichinhcanhan.datalayer.StockHistoryRepository;
import com.vanluom.group11.quanlytaichinhcanhan.sync.SyncManager;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import dagger.Lazy;
import timber.log.Timber;

/**
 * MmxContentProvider is the extension of the base class of Android
 * ContentProvider. Its purpose is to implement the read access and modify the
 * application data
 */
public class MmxContentProvider
    extends ContentProvider {

    // object definition for the call to check the content
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    // object map for the definition of the objects referenced in the URI
    private static SparseArrayCompat<Object> mapContent = new SparseArrayCompat<>();
    private static String mAuthority;

    public MmxContentProvider() {
        super();

    }

    @Inject Lazy<MmxOpenHelper> openHelper;

    public static String getAuthority() {
        return mAuthority;
    }

    public static void setAuthority(String mAuthority) {
        MmxContentProvider.mAuthority = mAuthority;
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        if (context == null) return false;

        setAuthority(context.getApplicationContext().getPackageName() + ".provider");

        List<Dataset> objMoneyManager = Arrays.asList(
            new AccountRepository(context),
            new AccountTransactionRepository(context),
            new AssetClassRepository(context),
            new AssetClassStockRepository(context),
            new BudgetEntryRepository(context),
            new BudgetRepository(context),
            new CategoryRepository(context),
            new CurrencyRepository(context),
//            new InfoRepositorySql(context),
            new PayeeRepository(context),
            new RecurringTransactionRepository(context),
            new SplitCategoriesRepository(context),
            new SplitRecurringCategoriesRepository(context),
            new StockRepository(context),
            new StockHistoryRepository(context),
            new SubcategoryRepository(context),
                new QueryAccountBills(context),
                new QueryCategorySubCategory(context),
                new QueryAllData(context),
                new QueryBillDeposits(context),
                new QueryReportIncomeVsExpenses(context),
                new BudgetQuery(context),
                new ViewMobileData(context),
                new SQLDataSet()
        );

        // Cycle all data sets for the composition of UriMatcher
        for (int i = 0; i < objMoneyManager.size(); i++) {
            // add URI
            sUriMatcher.addURI(getAuthority(), objMoneyManager.get(i).getBasepath(), i);
            // put map in the object being added in UriMatcher
            mapContent.put(i, objMoneyManager.get(i));
        }
        return false;
    }

    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        try {
            return query_internal(uri, projection, selection, selectionArgs, sortOrder);
        } catch (Exception e) {
            Timber.e(e, "content provider.query %s", uri);
        }
        return null;
    }

    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {
        Timber.d("Insert Uri: %s", uri);

        // find object from uri
        Object ret = getObjectFromUri(uri);
        long id = Constants.NOT_SET;
        String parse;

        if (Dataset.class.isInstance(ret)) {
            Dataset dataset = ((Dataset) ret);
            switch (dataset.getType()) {
                case TABLE:
                    logTableInsert(dataset, values);

                    //database.beginTransaction();
                    try {
                        initializeDependencies();

                        id = openHelper.get().getWritableDatabase()
                                .insertOrThrow(dataset.getSource(), null, values);
                        //database.setTransactionSuccessful();
                    } catch (Exception e) {
                        Timber.e(e, "inserting: %s", "insert");
                    }
                    parse = dataset.getBasepath() + "/" + id;
                    break;
                default:
                    throw new IllegalArgumentException("Type of dataset not supported for update");
            }
        } else {
            throw new IllegalArgumentException("Object ret of mapContent is not instance of dataset");
        }

        if (id > 0) {
            notifyChange(uri);
        }

        // return Uri with the primary key of the inserted record.
        return Uri.parse(parse);
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues values, String whereClause, String[] whereArgs) {
        Timber.d("Update Uri: %s", uri);

        Object ret = getObjectFromUri(uri);

        initializeDependencies();

        SQLiteDatabase database = openHelper.get().getWritableDatabase();

        int rowsUpdate = 0;

        if (Dataset.class.isInstance(ret)) {
            Dataset dataset = ((Dataset) ret);
            switch (dataset.getType()) {
                case TABLE:
                    logUpdate(dataset, values, whereClause, whereArgs);

                    try {
                        rowsUpdate = database.update(dataset.getSource(), values, whereClause, whereArgs);
                    } catch (Exception ex) {
                        Timber.e(ex, "updating: %s", "update");
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Type of dataset not supported for update");
            }
        } else {
            throw new IllegalArgumentException("Object ret of mapContent is not istance of dataset");
        }

        if (rowsUpdate > 0) {
            notifyChange(uri);
        }

        // return rows modified
        return rowsUpdate;
    }

    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
        Timber.d("Delete URI: %s", uri);

        // find object from uri
        Object ret = getObjectFromUri(uri);
        // safety control of having the where if not clean the table
        if (TextUtils.isEmpty(selection)) {
            throw new IllegalArgumentException("Delete not permitted because not define where clause");
//            Log.e(LOGCAT, "Delete not permitted because not define where clause");
//            return 0;
        }
        // take a database reference
        int rowsDelete = 0;

        if (Dataset.class.isInstance(ret)) {
            Dataset dataset = ((Dataset) ret);
            switch (dataset.getType()) {
                case TABLE:
                    logDelete(dataset, selection, selectionArgs);
                    try {
                        initializeDependencies();

                        rowsDelete = openHelper.get().getWritableDatabase()
                                .delete(dataset.getSource(), selection, selectionArgs);

                        // committed
                        //if (BuildConfig.DEBUG) Log.d(LOGCAT, "database set transaction successful");
                        //database.setTransactionSuccessful();
                    } catch (Exception e) {
                        Timber.e(e, "insert");
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Type of dataset not supported for delete");
            }
        } else {
            throw new IllegalArgumentException("Object ret of mapContent is not istance of dataset");
        }

        if (rowsDelete > 0) notifyChange(uri);

        return rowsDelete;
    }

    /**
     * Prepare statement SQL from data set object
     *
     * @param query SQL query
     * @param projection ?
     * @param selection ?
     * @param sortOrder field name for sort order
     * @return statement
     */
    public String prepareQuery(String query, String[] projection, String selection, String sortOrder) {
        String selectList, from, where = "", sort = "";

        // todo: use builder?
//        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
//        SQLiteQueryBuilder.buildQueryString(false, )

        // compose select list
        if (projection == null) {
            selectList = "SELECT *";
        } else {
            selectList = "SELECT ";

            for (int i = 0; i < projection.length; i++) {
                if (i > 0) {
                    selectList += ", ";
                }
                selectList += projection[i];
            }
        }
        // FROM
        from = "FROM (" + query + ") T";
        // WHERE
        if (!TextUtils.isEmpty(selection)) {
//            if (!selection.contains("WHERE")) {
            if (!selection.startsWith("WHERE")) {
                where += "WHERE";
            }
            where += " " + selection;
        }
        // compose sort
        if (!TextUtils.isEmpty(sortOrder)) {
            if (!sortOrder.contains("ORDER BY")) {
                sort += "ORDER BY ";
            }
            sort += " " + sortOrder;
        }
        // compose statement to return
        query = selectList + " " + from;
        // check where or sort not empty
        if (!TextUtils.isEmpty(where)) {
            query += " " + where;
        }
        if (!TextUtils.isEmpty(sort)) {
            query += " " + sort;
        }

        return query;
    }

    public Object getObjectFromUri(Uri uri) {
        int uriMatch = sUriMatcher.match(uri);
//        Timber.d("Uri Match Result: %s", Integer.toString(uriMatch));

        // find key into hash map
        Object objectRet = mapContent.get(uriMatch);
        if (objectRet == null) {
            throw new IllegalArgumentException("Unknown URI for Update: " + uri);
        }

        return objectRet;
    }

    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    public void resetDatabase() {
        if (openHelper != null) {
            openHelper.get().close();
        }

        openHelper = null;
        initializeDependencies();
    }

    // Private

    private void initializeDependencies() {
        if (openHelper != null) return;

        MoneyManagerApplication.getApp().iocComponent.inject(this);
    }

    private void logTableInsert(Dataset dataset, ContentValues values) {
        String log = "INSERT INTO " + dataset.getSource();
        if (values != null) {
            log += " VALUES ( " + values.toString() + ")";
        }
        Timber.d(log);
    }

    private Cursor query_internal(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder){
        Timber.d("Querying URI: %s", uri);

        // find object from uri
        Object sourceObject = getObjectFromUri(uri);

        initializeDependencies();

        SQLiteDatabase database = openHelper.get().getReadableDatabase();
        if (database == null) {
            Timber.e("Database could not be opened");
            return null;
        }

        Cursor cursor;

        // check type of instance data set
        if (Dataset.class.isInstance(sourceObject)) {
            Dataset dataset = ((Dataset) sourceObject);

//            logQuery(dataset, projection, selection, selectionArgs, sortOrder);

            switch (dataset.getType()) {
                case QUERY:
                    String query = prepareQuery(dataset.getSource(), projection, selection, sortOrder);
                    cursor = database.rawQuery(query, selectionArgs);
                    break;
                case SQL:
                    cursor = database.rawQuery(selection, selectionArgs);
                    break;
                case TABLE:
                case VIEW:
                    SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
                    queryBuilder.setTables(dataset.getSource());
                    cursor = queryBuilder.query(database, projection, selection, selectionArgs, null, null, sortOrder);
                    break;
                default:
                    throw new IllegalArgumentException("Type of dataset not defined");
            }
        } else {
            throw new IllegalArgumentException("Object sourceObject of mapContent is not instance of dataset");
        }

        // notify listeners waiting for the data is ready
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        if (!cursor.isClosed()) {
            Timber.d("Rows returned: %d", cursor.getCount());
        }

        return cursor;
    }

    private void logQuery(Dataset dataset, String[] projection, String selection,
                          String[] selectionArgs, String sortOrder) {
        // compose log verbose instruction
        String log;
        // compose log
        if (dataset.getType() == DatasetType.SQL) {
            log = selection;
        } else {
            if (projection != null) {
                log = "SELECT " + Arrays.asList(projection).toString();
            } else {
                log = "SELECT *";
            }
            log += " FROM " + dataset.getSource();
            if (!TextUtils.isEmpty(selection)) {
                log += " WHERE " + selection;
            }
            if (!TextUtils.isEmpty(sortOrder)) {
                log += " ORDER BY " + sortOrder;
            }
            if (selectionArgs != null) {
                log += "; ARGS=" + Arrays.asList(selectionArgs).toString();
            }
        }
        // log
        Timber.d(log);
    }

    private void logUpdate(Dataset dataset, ContentValues values, String whereClause, String[] whereArgs) {
        String log = "UPDATE " + dataset.getSource();
        // compose log verbose
        if (values != null) {
            log += " SET " + values.toString();
        }
        if (!TextUtils.isEmpty(whereClause)) {
            log += " WHERE " + whereClause;
        }
        if (whereArgs != null) {
            log += "; ARGS=" + Arrays.asList(whereArgs).toString();
        }

        // open transaction
        //database.beginTransaction();
//        if (BuildConfig.DEBUG) Log.d(LOGCAT, "database begin transaction");

        Timber.d(log);
    }

    private void logDelete(Dataset dataset, String selection, String[] selectionArgs) {
        String log = "DELETE FROM " + dataset.getSource();
        // compose log verbose
        if (!TextUtils.isEmpty(selection)) {
            log += " WHERE " + selection;
        }
        if (selectionArgs != null) {
            log += "; ARGS=" + Arrays.asList(selectionArgs).toString();
        }
        // open transaction
        //database.beginTransaction();
//                    if (BuildConfig.DEBUG) Log.d(LOGCAT, "database begin transaction");
        Timber.d(log);
    }

    private void notifyChange(Uri uri) {
        if (getContext() == null) return;

        // notify update. todo Do this also after changes via sqlite.
        getContext().getContentResolver().notifyChange(uri, null);
        // notify the sync that database has changed.
        new SyncManager(getContext()).dataChanged();
    }
}
