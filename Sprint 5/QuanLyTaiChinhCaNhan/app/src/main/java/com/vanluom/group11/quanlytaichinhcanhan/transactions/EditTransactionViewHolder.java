
package com.vanluom.group11.quanlytaichinhcanhan.transactions;

import android.app.Activity;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.view.IconicsImageView;
import com.vanluom.group11.quanlytaichinhcanhan.R;
import com.vanluom.group11.quanlytaichinhcanhan.core.UIHelper;
import com.shamanland.fonticon.FontIconView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * View holder for transaction editing (checking & recurring).
 */
public class EditTransactionViewHolder {

    public EditTransactionViewHolder(Activity view) {
        ButterKnife.bind(this, view);

        // add custom icons
        UIHelper uiHelper = new UIHelper(view);
        removePayeeButton.setImageDrawable(uiHelper.getIcon(GoogleMaterial.Icon.gmd_backspace));
    }

    @BindView(R.id.textViewDate) public TextView dateTextView;
    @BindView(R.id.previousDayButton) public IconicsImageView previousDayButton;
    @BindView(R.id.nextDayButton) public IconicsImageView nextDayButton;

    @BindView(R.id.textViewCategory) TextView categoryTextView;
    @BindView(R.id.textViewPayee) TextView txtSelectPayee;
    @BindView(R.id.spinnerStatus) Spinner spinStatus;
    @BindView(R.id.spinnerAccount) Spinner spinAccount;
    @BindView(R.id.spinnerToAccount) Spinner spinAccountTo;
    @BindView(R.id.textViewToAmount) TextView txtAmountTo;
    @BindView(R.id.textViewAmount) TextView txtAmount;

    @BindView(R.id.tableRowPayee) ViewGroup tableRowPayee;
    @BindView(R.id.tableRowAmountTo) ViewGroup tableRowAmountTo;
    @BindView(R.id.tableRowAccountTo) ViewGroup tableRowAccountTo;
    @BindView(R.id.accountFromLabel) TextView accountFromLabel;
    @BindView(R.id.textViewToAccount) TextView txtToAccount;
    @BindView(R.id.textViewHeaderAmount) TextView amountHeaderTextView;
    @BindView(R.id.textViewHeaderAmountTo) TextView amountToHeaderTextView;
    @BindView(R.id.removePayeeButton) ImageButton removePayeeButton;
    @BindView(R.id.splitButton) FontIconView splitButton;
    // Transaction types
    @BindView(R.id.withdrawalButton) RelativeLayout withdrawalButton;
    @BindView(R.id.depositButton) RelativeLayout depositButton;
    @BindView(R.id.transferButton) RelativeLayout transferButton;
    @BindView(R.id.buttonTransNumber) ImageButton btnTransNumber;
    @BindView(R.id.editTextTransNumber) public EditText edtTransNumber;
    @BindView(R.id.editTextNotes) public EditText edtNotes;
}
