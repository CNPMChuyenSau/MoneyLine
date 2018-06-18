package com.vanluom.group11.quanlytaichinhcanhan.servicelayer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;

import com.vanluom.group11.quanlytaichinhcanhan.Constants;
import com.vanluom.group11.quanlytaichinhcanhan.database.ITransactionEntity;
import com.vanluom.group11.quanlytaichinhcanhan.datalayer.AccountTransactionRepository;
import com.vanluom.group11.quanlytaichinhcanhan.datalayer.PayeeRepository;
import com.vanluom.group11.quanlytaichinhcanhan.domainmodel.Payee;

/**
 */
public class PayeeService
    extends ServiceBase {

    public PayeeService(Context context) {
        super(context);

        this.payeeRepository = new PayeeRepository(context);
    }

    private PayeeRepository payeeRepository;

    public Payee loadByName(String name) {
        Payee payee = null;
        String selection = Payee.PAYEENAME + "='" + name + "'";

        Cursor cursor = getContext().getContentResolver().query(
                this.payeeRepository.getUri(),
                this.payeeRepository.getAllColumns(),
                selection,
                null,
                null);
        if (cursor == null) return null;

        if(cursor.moveToFirst()) {
            payee = new Payee();
            payee.loadFromCursor(cursor);
        }

        cursor.close();

        return payee;
    }

    public int loadIdByName(String name) {
        int result = Constants.NOT_SET;

        if(TextUtils.isEmpty(name)) return result;

        String selection = Payee.PAYEENAME + "=?";

        Cursor cursor = getContext().getContentResolver().query(
                payeeRepository.getUri(),
                new String[]{ Payee.PAYEEID },
                selection,
                new String[] { name },
                null);
        if (cursor == null) return Constants.NOT_SET;

        if(cursor.moveToFirst()) {
            result = cursor.getInt(cursor.getColumnIndex(Payee.PAYEEID));
        }

        cursor.close();

        return result;
    }

    public Payee createNew(String name) {
        if (TextUtils.isEmpty(name)) return null;

        name = name.trim();

        Payee payee = new Payee();
        payee.setName(name);

        int id = this.payeeRepository.add(payee);

        payee.setId(id);

        return payee;
    }

    public boolean exists(String name) {
        name = name.trim();

        Payee payee = loadByName(name);
        return (payee != null);
    }

    public boolean isPayeeUsed(int payeeId) {
        AccountTransactionRepository repo = new AccountTransactionRepository(getContext());
        int links = repo.count(ITransactionEntity.PAYEEID + "=?", new String[]{Integer.toString(payeeId)});
        return links > 0;
    }

    public int update(int id, String name) {
        if(TextUtils.isEmpty(name)) return Constants.NOT_SET;

        name = name.trim();

        ContentValues values = new ContentValues();
        values.put(Payee.PAYEENAME, name);

        return getContext().getContentResolver().update(payeeRepository.getUri(),
                values,
                Payee.PAYEEID + "=" + id,
                null);
    }
}
