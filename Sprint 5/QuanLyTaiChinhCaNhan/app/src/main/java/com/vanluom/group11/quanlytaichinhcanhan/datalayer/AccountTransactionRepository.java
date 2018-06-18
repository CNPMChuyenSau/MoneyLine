package com.vanluom.group11.quanlytaichinhcanhan.datalayer;

import android.content.Context;

import com.vanluom.group11.quanlytaichinhcanhan.Constants;
import com.vanluom.group11.quanlytaichinhcanhan.database.DatasetType;
import com.vanluom.group11.quanlytaichinhcanhan.database.ITransactionEntity;
import com.vanluom.group11.quanlytaichinhcanhan.database.WhereStatementGenerator;
import com.vanluom.group11.quanlytaichinhcanhan.domainmodel.AccountTransaction;
import com.vanluom.group11.quanlytaichinhcanhan.utils.MmxDatabaseUtils;

/**
 * Repository for Checking Account records.
 * Source: Table Checking Account.
 */
public class AccountTransactionRepository
    extends RepositoryBase {

    public AccountTransactionRepository(Context context) {
        super(context, "checkingaccount_v1", DatasetType.TABLE, "checkingaccount");
    }

    @Override
    public String[] getAllColumns() {
        return new String[] {
                "TRANSID AS _id", AccountTransaction.TRANSID,
                ITransactionEntity.ACCOUNTID,
                ITransactionEntity.TOACCOUNTID,
                ITransactionEntity.PAYEEID,
                ITransactionEntity.TRANSCODE,
                ITransactionEntity.TRANSAMOUNT,
                ITransactionEntity.STATUS,
                ITransactionEntity.TRANSACTIONNUMBER,
                ITransactionEntity.NOTES,
                ITransactionEntity.CATEGID,
                ITransactionEntity.SUBCATEGID,
                ITransactionEntity.TRANSDATE,
                ITransactionEntity.FOLLOWUPID,
                ITransactionEntity.TOTRANSAMOUNT};
    }

    public AccountTransaction load(int id) {
        if (id == Constants.NOT_SET) return null;

        AccountTransaction tx = (AccountTransaction) first(AccountTransaction.class,
                getAllColumns(),
                AccountTransaction.TRANSID + "=?",
                MmxDatabaseUtils.getArgsForId(id),
                null);

        return tx;
    }

    public AccountTransaction insert(AccountTransaction entity) {
        entity.contentValues.remove(AccountTransaction.TRANSID);

        int id = insert(entity.contentValues);

        entity.setId(id);

        return entity;
    }

    public boolean update(AccountTransaction item) {
        WhereStatementGenerator where = new WhereStatementGenerator();
        where.addStatement(AccountTransaction.TRANSID, "=", item.getId());

        boolean saved = super.update(item, where.getWhere());
        return saved;
    }
}
