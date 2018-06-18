package com.vanluom.group11.quanlytaichinhcanhan.servicelayer.qif;

import android.content.Context;
import android.database.Cursor;

import com.vanluom.group11.quanlytaichinhcanhan.adapter.AllDataAdapter;
import com.vanluom.group11.quanlytaichinhcanhan.core.TransactionTypes;
import com.vanluom.group11.quanlytaichinhcanhan.viewmodels.AccountTransactionDisplay;

import java.text.ParseException;

/**
 * Generator of Qif file contents.
 * Parses the transactions and creates qif output.
 * References:
 * http://www.respmech.com/mym2qifw/qif_new.htm
 */
public class QifGenerator implements IQifGenerator {
    public QifGenerator(Context context) {
        mContext = context;
    }

    private Context mContext;

    public String createFromAdapter(AllDataAdapter adapter)
            throws ParseException {
        StringBuilder builder = new StringBuilder();

        Cursor cursor = adapter.getCursor();
        int originalCursorPosition = cursor.getPosition();
        cursor.moveToFirst();

        int previousAccountId = 0;
        QifHeader header = new QifHeader(mContext);
        QifRecord record = new QifRecord(mContext);
        AccountTransactionDisplay transaction = new AccountTransactionDisplay();

        while (!cursor.isAfterLast()) {
            // get data from cursor.
            transaction.loadFromCursor(cursor);

            int accountId;
            if (transaction.getTransactionType() == TransactionTypes.Transfer) {
                accountId = transaction.getToAccountId();
            } else {
                accountId = transaction.getAccountId();
            }
            if (accountId != previousAccountId) {
                previousAccountId = accountId;
                // add header record
                String headerRecord = header.parse(cursor);
                builder.append(headerRecord);
            }

            // add transaction record
            String row = record.parse(transaction);

            builder.append(row);
            cursor.moveToNext();
        }
        // No need to close the cursor here because it is used in the parent fragment.
//        cursor.close();
        cursor.moveToPosition(originalCursorPosition);

        return builder.toString();
    }

}
