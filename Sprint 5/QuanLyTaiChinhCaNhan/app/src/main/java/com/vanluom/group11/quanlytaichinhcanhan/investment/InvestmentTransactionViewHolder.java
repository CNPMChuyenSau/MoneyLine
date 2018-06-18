
package com.vanluom.group11.quanlytaichinhcanhan.investment;

import android.view.View;
import android.widget.Spinner;

import com.vanluom.group11.quanlytaichinhcanhan.R;
import com.vanluom.group11.quanlytaichinhcanhan.view.RobotoEditTextFontIcon;
import com.vanluom.group11.quanlytaichinhcanhan.view.RobotoTextViewFontIcon;
import com.shamanland.fonticon.FontIconView;

/**
 * View Holder for Investment transaction editing.
 */
public class InvestmentTransactionViewHolder {
    public InvestmentTransactionViewHolder(View view) {
        accountSpinner = (Spinner) view.findViewById(R.id.spinnerAccount);
        dateView = (RobotoTextViewFontIcon) view.findViewById(R.id.textViewDate);
        numSharesView = (RobotoTextViewFontIcon) view.findViewById(R.id.numSharesView);
        stockNameEdit = (RobotoEditTextFontIcon) view.findViewById(R.id.stockNameEdit);
        symbolEdit = (RobotoEditTextFontIcon) view.findViewById(R.id.symbolEdit);
        notesEdit = (RobotoEditTextFontIcon) view.findViewById(R.id.notesEdit);
        previousDayButton = (FontIconView) view.findViewById(R.id.previousDayButton);
        nextDayButton = (FontIconView) view.findViewById(R.id.nextDayButton);
    }

    public Spinner accountSpinner;
    public RobotoTextViewFontIcon dateView;
    public RobotoTextViewFontIcon numSharesView;
    public RobotoEditTextFontIcon stockNameEdit;
    public RobotoEditTextFontIcon symbolEdit;
    public RobotoEditTextFontIcon notesEdit;
    public FontIconView previousDayButton;
    public FontIconView nextDayButton;
}
