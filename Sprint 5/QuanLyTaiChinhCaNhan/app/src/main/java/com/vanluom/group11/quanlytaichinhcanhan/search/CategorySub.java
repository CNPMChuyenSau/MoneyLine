package com.vanluom.group11.quanlytaichinhcanhan.search;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Used for search criteria.
 * Created by Alen on 13/07/2015.
 */
public class CategorySub implements Parcelable {

    public CategorySub() {
        // default constructor
    }

    public int categId;
    public String categName;
    public int subCategId;
    public String subCategName;

    protected CategorySub(Parcel in) {
        categId = in.readInt();
        categName = in.readString();
        subCategId = in.readInt();
        subCategName = in.readString();
    }

    public static final Creator<CategorySub> CREATOR = new Creator<CategorySub>() {
        @Override
        public CategorySub createFromParcel(Parcel in) {
            return new CategorySub(in);
        }

        @Override
        public CategorySub[] newArray(int size) {
            return new CategorySub[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(categId);
        parcel.writeString(categName);

        parcel.writeInt(subCategId);
        parcel.writeString(subCategName);
    }
}
