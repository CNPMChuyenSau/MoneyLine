
package com.vanluom.group11.quanlytaichinhcanhan.domainmodel;

import android.database.Cursor;
import android.database.DatabaseUtils;

import com.vanluom.group11.quanlytaichinhcanhan.Constants;
import com.vanluom.group11.quanlytaichinhcanhan.common.CommonSplitCategoryLogic;
import com.vanluom.group11.quanlytaichinhcanhan.core.TransactionTypes;
import com.vanluom.group11.quanlytaichinhcanhan.database.ISplitTransaction;
import com.vanluom.group11.quanlytaichinhcanhan.database.ITransactionEntity;
import com.vanluom.group11.quanlytaichinhcanhan.datalayer.IEntity;

import org.parceler.Parcel;

import info.javaperformance.money.Money;
import info.javaperformance.money.MoneyFactory;

/**
 * Split Category for checking account transaction.
 */
@Parcel
public class SplitCategory
    extends EntityBase
    implements ISplitTransaction, IEntity {

    public static String TABLE_NAME = "SPLITTRANSACTIONS_V1";

    public static final String SPLITTRANSID = "SPLITTRANSID";
    public static final String TRANSID = "TRANSID";
    public static final String CATEGID = "CATEGID";
    public static final String SUBCATEGID = "SUBCATEGID";
    public static final String SPLITTRANSAMOUNT = "SPLITTRANSAMOUNT";

    public static SplitCategory create(int transactionId, int categoryId, int subcategoryId,
                                       TransactionTypes parentTransactionType, Money amount) {
        SplitCategory entity = new SplitCategory();

        entity.setId(Constants.NOT_SET);
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

    public Integer getId() {
        return getInt(SPLITTRANSID);
    }

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
    public void setCategoryId(int categoryId) {
        setInt(CATEGID, categoryId);
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

    public void setTransId(Integer value) {
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
