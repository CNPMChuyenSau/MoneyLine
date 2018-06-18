
package com.vanluom.group11.quanlytaichinhcanhan.recurring.transactions;

import android.widget.EditText;
import android.widget.TextView;

import com.shamanland.fonticon.FontIconView;

/**
 * View Holder pattern for Recurring Transaction editing
 */
public class RecurringTransactionViewHolder {
//    public TextView dueDateTextView;
    public TextView paymentDateTextView;
    public FontIconView paymentPreviousDayButton, paymentNextDayButton;
    public TextView recurrenceLabel;
    public TextView paymentsLeftTextView;
    public EditText paymentsLeftEditText;
}
