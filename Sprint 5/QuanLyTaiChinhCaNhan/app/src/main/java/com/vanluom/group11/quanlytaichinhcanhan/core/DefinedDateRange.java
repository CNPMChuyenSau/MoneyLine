package com.vanluom.group11.quanlytaichinhcanhan.core;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Represent one selectable date range, i.e. Last 7 Days, Current Month, etc.
 */
public class DefinedDateRange
    implements Parcelable {

    public DefinedDateRange() {
//        this.context = context;
    }

    public DefinedDateRangeName key;
    /**
     * Used for display sorting.
     */
    public int order;
    /**
     * Stores the id of the string for the name/title. i.e. R.string.last7days
     */
    public int nameResourceId;
    /**
     * Id of the menu resource used to display this date range in toolbars.
     */
    public int menuResourceId;

//    private Context context;

    protected DefinedDateRange(Parcel in) {
        this.key = DefinedDateRangeName.valueOf(in.readString());
        order = in.readInt();
        nameResourceId = in.readInt();
        menuResourceId = in.readInt();
    }

    public static final Creator<DefinedDateRange> CREATOR = new Creator<DefinedDateRange>() {
        @Override
        public DefinedDateRange createFromParcel(Parcel in) {
            return new DefinedDateRange(in);
        }

        @Override
        public DefinedDateRange[] newArray(int size) {
            return new DefinedDateRange[size];
        }
    };

    public String getLocalizedName(Context context) {
        return context.getString(this.nameResourceId);
    }

    public String getName() {
        return this.key.toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.key.name());
        dest.writeInt(this.order);
        dest.writeInt(this.nameResourceId);
        dest.writeInt(this.menuResourceId);
    }
}
