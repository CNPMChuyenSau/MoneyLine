package com.vanluom.group11.quanlytaichinhcanhan.widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

public class AccountBillsWidgetService
    extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new AllAccountBillsViewFactory(this.getApplicationContext(), intent);
    }
}
