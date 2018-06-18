
package com.vanluom.group11.quanlytaichinhcanhan.database;

import com.vanluom.group11.quanlytaichinhcanhan.core.TransactionTypes;

import java.util.Date;

import info.javaperformance.money.Money;

/**
 * Interface for Recurring/Checking transactions.
 * This is a common transaction (the common fields in Account & Recurring transactions).
 */
public interface ITransactionEntity {

    String ACCOUNTID = "ACCOUNTID";
    String CATEGID = "CATEGID";
    String NOTES = "NOTES";
    String PAYEEID = "PAYEEID";
    String STATUS = "STATUS";
    String SUBCATEGID = "SUBCATEGID";
    String TOACCOUNTID = "TOACCOUNTID";
    String TOTRANSAMOUNT = "TOTRANSAMOUNT";
    String TRANSAMOUNT = "TRANSAMOUNT";
    String TRANSCODE = "TRANSCODE";
    String TRANSACTIONNUMBER = "TRANSACTIONNUMBER";
    String TRANSDATE = "TRANSDATE";
    String FOLLOWUPID = "FOLLOWUPID";

    Integer getId();
    void setId(Integer value);
    boolean hasId();

    Integer getAccountId();
    void setAccountId(Integer value);

    Integer getAccountToId();
    void setAccountToId(Integer value);
    boolean hasAccountTo();

    Integer getCategoryId();
    void setCategoryId(Integer value);
    boolean hasCategory();

    Integer getSubcategoryId();
    void setSubcategoryId(Integer value);

    /**
     * @return the splitTransAmount
     */
    Money getAmount();
    /**
     * @param value the splitTransAmount to set
     */
    void setAmount(Money value);

    Money getAmountTo();
    void setAmountTo(Money value);

    Date getDate();
    String getDateString();
    void setDate(Date value);

    String getNotes();
    void setNotes(String value);

    Integer getPayeeId();
    void setPayeeId(Integer value);
    boolean hasPayee();

    String getStatus();
    void setStatus(String value);

    String getTransactionNumber();
    void setTransactionNumber(String value);

    TransactionTypes getTransactionType();
    void setTransactionType(TransactionTypes value);
}
