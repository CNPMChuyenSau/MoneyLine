
package com.vanluom.group11.quanlytaichinhcanhan.database;

import com.vanluom.group11.quanlytaichinhcanhan.core.TransactionTypes;
import com.vanluom.group11.quanlytaichinhcanhan.datalayer.IEntity;

import info.javaperformance.money.Money;

/**
 * Common interface for split transactions and recurring splits.
 */
public interface ISplitTransaction
    extends IEntity {

    Integer getId();
    void setId(int splitTransId);
    boolean hasId();

    Integer getAccountId();
    void setAccountId(int value);

    Money getAmount();
    void setAmount(Money splitTransAmount);

    Integer getCategoryId();
    void setCategoryId(int categoryId);

    Integer getSubcategoryId();
    void setSubcategoryId(Integer subCategoryId);

    TransactionTypes getTransactionType(TransactionTypes parentTransactionType);
    void setTransactionType(TransactionTypes value, TransactionTypes parentTransactionType);
}
