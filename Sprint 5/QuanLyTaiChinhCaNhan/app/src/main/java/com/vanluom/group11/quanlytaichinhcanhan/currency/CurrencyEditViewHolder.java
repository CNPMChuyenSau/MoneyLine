package com.vanluom.group11.quanlytaichinhcanhan.currency;

import android.app.Activity;
import android.widget.EditText;
import android.widget.Spinner;

import com.vanluom.group11.quanlytaichinhcanhan.R;

/**
 * View Holder pattern for the currencies list.
 */
public class CurrencyEditViewHolder {
    public static CurrencyEditViewHolder initialize(Activity activity) {
        CurrencyEditViewHolder holder = new CurrencyEditViewHolder();

        holder.edtCurrencyName = (EditText) activity.findViewById(R.id.editTextCurrencyName);
        holder.spinCurrencySymbol = (Spinner) activity.findViewById(R.id.spinCurrencySymbol);
        holder.edtUnitName = (EditText) activity.findViewById(R.id.editTextUnitName);
        holder.edtCentsName = (EditText) activity.findViewById(R.id.editTextCentsName);
        holder.edtPrefix = (EditText) activity.findViewById(R.id.editTextPrefixSymbol);
        holder.edtSuffix = (EditText) activity.findViewById(R.id.editTextSuffixSymbol);
        holder.edtDecimal = (EditText) activity.findViewById(R.id.editTextDecimalChar);
        holder.edtGroup = (EditText) activity.findViewById(R.id.editTextGroupChar);
        holder.edtScale = (EditText) activity.findViewById(R.id.editTextScale);
        holder.edtConversion = (EditText) activity.findViewById(R.id.editTextConversion);

        return holder;
    }

    public EditText edtCurrencyName, edtUnitName, edtCentsName, edtPrefix, edtSuffix,
        edtDecimal, edtGroup, edtScale, edtConversion;
    public Spinner spinCurrencySymbol;

}
