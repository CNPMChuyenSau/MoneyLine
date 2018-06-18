package com.vanluom.group11.quanlytaichinhcanhan.common;

/**
 * Callbacks from All Data multi-choice mode listener.
 */
public interface IAllDataMultiChoiceModeListenerCallbacks {
    void onMultiChoiceCreated(android.view.Menu menu);
    void onDestroyActionMode();
    void onDeleteClicked();
    void onChangeTransactionStatusClicked();
    void onTransactionStatusClicked(String status);
    void onSelectAllRecordsClicked();
    void onDuplicateTransactionsClicked();
    void onItemCheckedStateChanged(int position, boolean checked);
}
