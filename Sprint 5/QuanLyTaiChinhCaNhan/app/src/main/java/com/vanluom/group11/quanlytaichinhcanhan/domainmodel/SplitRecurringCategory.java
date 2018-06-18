
package com.vanluom.group11.quanlytaichinhcanhan.domainmodel;

import android.database.Cursor;
import android.database.DatabaseUtils;

import com.vanluom.group11.quanlytaichinhcanhan.Constants;
import com.vanluom.group11.quanlytaichinhcanhan.common.CommonSplitCategoryLogic;
import com.vanluom.group11.quanlytaichinhcanhan.core.TransactionTypes;
import com.vanluom.group11.quanlytaichinhcanhan.database.ISplitTransaction;
import com.vanluom.group11.quanlytaichinhcanhan.database.ITransactionEntity;

import org.parceler.Parcel;

import info.javaperformance.money.Money;
import info.javaperformance.money.MoneyFactory;

/**
 * Split Category item for recurring transaction item.
 */
@Parcel
public class SplitRecurringCategory
        extends EntityBase
        implements ISplitTransaction {

    public static String TABLE_NAME = "budgetsplittransactions_v1";

    public static final String SPLITTRANSID = "SPLITTRANSID";
    public static final String TRANSID = "TRANSID";
    public static final String CATEGID = "CATEGID";
    public static final String SUBCATEGID = "SUBCATEGID";
    public static final String SPLITTRANSAMOUNT = "SPLITTRANSAMOUNT";

    public static SplitRecurringCategory create(int transactionId, int categoryId, int subcategoryId,
                                                TransactionTypes parentTransactionType, Money amount) {
        SplitRecurringCategory entity = new SplitRecurringCategory();

        entity.setCategoryId(categoryId);
        entity.setSubcategoryId(subcategoryId);
        entity.setAmount(amount);
        entity.setTransId(transactionId);

        TransactionTypes splitType;
        if (amount.isZero() || amount.compareTo(MoneyFactory.fromDouble(0)) == -1) {
            splitType = TransactionTypes.Withdrawal;
        } else {
            splitType = TransactionTypes.Deposit;
        }
        entity.setTransactionType(splitType, parentTransactionType);

        return entity;
    }

    TransactionTypes transactionType;

    @Override
    public Integer getId() {
        return getInt(SPLITTRANSID);
    }

    @Override
    public void setId(int value) {
        setInt(SPLITTRANSID, value);
    }

    @Override
    public boolean hasId() {
        return getId() != null && getId() != Constants.NOT_SET;
    }

    @Override
    public Integer getAccountId() {
        return getInt(ITransactionEntity.ACCOUNTID);
    }

    @Override
    public void setAccountId(int value) {
        setInt(ITransactionEntity.ACCOUNTID, value);
    }

    @Override
    public Integer getCategoryId() {
        return getInt(CATEGID);
    }

    @Override
    public Money getAmount() {
        return getMoney(SPLITTRANSAMOUNT);
    }

    @Override
    public Integer getSubcategoryId() {
        return getInt(SUBCATEGID);
    }

    @Override
    public void setCategoryId(int categId) {
        setInt(CATEGID, categId);
    }

    @Override
    public void setAmount(Money splitTransAmount) {
        setMoney(SPLITTRANSAMOUNT, splitTransAmount);
    }

    @Override
    public void setSubcategoryId(Integer subCategoryId) {
        setInt(SUBCATEGID, subCategoryId);
    }

    @Override
    public void loadFromCursor(Cursor c) {
        super.loadFromCursor(c);

        DatabaseUtils.cursorDoubleToContentValuesIfPresent(c, contentValues, SPLITTRANSAMOUNT);
    }

    public Integer getTransId() {
        return getInt(TRANSID);
    }

    public void setTransId(int value) {
        setInt(TRANSID, value);
    }

    public TransactionTypes getTransactionType(TransactionTypes parentTransactionType) {
        if (transactionType == null) {
            transactionType = CommonSplitCategoryLogic.getTransactionType(parentTransactionType, this.getAmount());
        }
        return transactionType;
    }

    public void setTransactionType(TransactionTypes value, TransactionTypes parentTransactionType) {
        TransactionTypes currentType = getTransactionType(parentTransactionType);

        this.transactionType = value;

        // If the type is being changed, just revert the sign.
        if (value != currentType) {
            this.setAmount(this.getAmount().negate());
        }
    }
}
