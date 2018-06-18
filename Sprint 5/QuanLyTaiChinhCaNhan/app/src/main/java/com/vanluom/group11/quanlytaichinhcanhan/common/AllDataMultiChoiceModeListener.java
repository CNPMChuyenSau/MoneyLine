package com.vanluom.group11.quanlytaichinhcanhan.common;

import android.view.ActionMode;
import android.widget.AbsListView;

import com.vanluom.group11.quanlytaichinhcanhan.R;

/**
 * class to manage multi choice mode
 */
public class AllDataMultiChoiceModeListener
        implements AbsListView.MultiChoiceModeListener {

    public void setListener(IAllDataMultiChoiceModeListenerCallbacks callbacks) {
        mCallbacks = callbacks;
    }

    private IAllDataMultiChoiceModeListenerCallbacks mCallbacks;

    @Override
    public boolean onPrepareActionMode(ActionMode mode, android.view.Menu menu) {

        return false;
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {
        mCallbacks.onDestroyActionMode();
    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, android.view.Menu menu) {
        mCallbacks.onMultiChoiceCreated(menu);
        return true;
    }

    /**
     * Handle the toolbar icon click (delete, copy, etc.)
     * @param mode
     * @param item
     * @return
     */
    @Override
    public boolean onActionItemClicked(ActionMode mode, android.view.MenuItem item) {
        boolean result;

        switch (item.getItemId()) {
            case R.id.menu_select_all:
                mCallbacks.onSelectAllRecordsClicked();
                // do not finish the selection mode after this!
                result = false;
                break;
            case R.id.menu_change_status:
                mCallbacks.onChangeTransactionStatusClicked();
                result = true;
                break;
            case R.id.menu_duplicate_transactions:
                mCallbacks.onDuplicateTransactionsClicked();
                result = true;
                break;
            case R.id.menu_delete:
                mCallbacks.onDeleteClicked();
                result = true;
                break;
            case R.id.menu_none:
            case R.id.menu_reconciled:
            case R.id.menu_follow_up:
            case R.id.menu_duplicate:
            case R.id.menu_void:
                String status = Character.toString(item.getAlphabeticShortcut());
                mCallbacks.onTransactionStatusClicked(status);
                result = true;
                break;
            default:
                // nothing
                result = false;
        }

        if (result) {
            mode.finish();
        }

        return result;
    }

    @Override
    public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
        mCallbacks.onItemCheckedStateChanged(position, checked);
    }
}
