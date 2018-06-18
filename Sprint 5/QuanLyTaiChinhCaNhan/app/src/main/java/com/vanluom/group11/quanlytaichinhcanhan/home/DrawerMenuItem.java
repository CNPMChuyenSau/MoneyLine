
package com.vanluom.group11.quanlytaichinhcanhan.home;

import android.graphics.drawable.Drawable;

public class DrawerMenuItem {
    private Integer mId;
    private String mText;
    private Integer mIcon;
    private Drawable mIconDrawable;
    private String mShortcut;
    private Boolean mDivider = Boolean.FALSE;

    private Object mTag;

    public Integer getId() {
        return mId;
    }

    public DrawerMenuItem withId(Integer id) {
        this.mId = id;
        return this;
    }

    public String getText() {
        return mText;
    }

    public DrawerMenuItem withText(String text) {
        this.mText = text;
        return this;
    }

    public Integer getIcon() {
        return mIcon;
    }

    public Drawable getIconDrawable() {
        return mIconDrawable;
    }

    public DrawerMenuItem withIcon(Integer icon) {
        this.mIcon = icon;
        return this;
    }

    public DrawerMenuItem withIconDrawable(Drawable iconDrawable) {
        this.mIconDrawable = iconDrawable;
        return this;
    }

    public String getShortcut() {
        return mShortcut;
    }

    public DrawerMenuItem withShortcut(String shortcut) {
        this.mShortcut = shortcut;
        return this;
    }

    public Boolean hasDivider() {
        return mDivider;
    }

    public DrawerMenuItem withDivider(Boolean divider) {
        this.mDivider = divider;
        return this;
    }

    public void setTag(Object tag) {
        this.mTag = tag;
    }

    public Object getTag() {
        return this.mTag;
    }
}
