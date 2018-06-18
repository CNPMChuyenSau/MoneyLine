
package com.vanluom.group11.quanlytaichinhcanhan.datalayer;

import android.database.sqlite.SQLiteQueryBuilder;

import java.util.Arrays;

/**
 * Select object for easier querying through repositories.
 */
public class Select {
    public String[] projection = null;
    public String from = null;
    public String selection = null;
    public String[] selectionArgs = null;
    public String sort = null;

    /**
     * Query generator. The constructor is also the projection definition. Empty arguments mean *.
     */
    public Select() {}

    /**
     * The constructor with projection.
     * @param projection The fields to fetch.
     */
    public Select(String... projection) {
        this.projection = projection;
    }

    public Select from(String table) {
        from = table;
        return this;
    }

    /**
     * With this method the arguments can be passed directly to the database query.
     * @param selection Selection statement with placeholders for arguments.
     * @return Select object.
     */
    public Select where(String selection) {
        this.selection = selection;
        return this;
    }

    /**
     * When using this method, make sure to pass Select.selectionArgs to the .query or other action
     * methods.
     * @param selection WHERE statement
     * @param args arguments
     * @return Select object for chaining methods.
     */
    public Select where(String selection, String... args) {
        this.selection = selection;
        this.selectionArgs = args;
        return this;
    }

    public Select where(String selection, long... args) {
        this.selection = selection;

        // convert array values from Integer to String
        String[] stringArgs = Arrays.toString(args)
                .split("[\\[\\]]")[1]
                .split(", ");

        this.selectionArgs = stringArgs;
        return this;
    }

    public Select orderBy(String sort) {
        // sort
        this.sort = sort;
        return this;
    }

    public String toString() {
        // compose select query.
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        builder.setTables(from);
        String sql = builder.buildQuery(projection, selection, null, null, sort, null);
        return sql;
    }
}
