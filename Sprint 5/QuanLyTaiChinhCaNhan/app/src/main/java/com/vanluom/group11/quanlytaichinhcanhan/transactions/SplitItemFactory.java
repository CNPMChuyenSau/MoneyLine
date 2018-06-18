package com.vanluom.group11.quanlytaichinhcanhan.transactions;

import com.vanluom.group11.quanlytaichinhcanhan.Constants;
import com.vanluom.group11.quanlytaichinhcanhan.core.TransactionTypes;
import com.vanluom.group11.quanlytaichinhcanhan.database.ISplitTransaction;
import com.vanluom.group11.quanlytaichinhcanhan.domainmodel.SplitCategory;
import com.vanluom.group11.quanlytaichinhcanhan.domainmodel.SplitRecurringCategory;

import info.javaperformance.money.MoneyFactory;

/**
 * The factory that creates the Split Category entities
 */
public class SplitItemFactory {

    public static ISplitTransaction create(String entityClassName, TransactionTypes parentTransactionType) {
        ISplitTransaction entity;
        String recurringSplitName = SplitRecurringCategory.class.getSimpleName();

        if (entityClassName != null && entityClassName.contains(recurringSplitName)) {
            entity = SplitRecurringCategory.create(Constants.NOT_SET, Constants.NOT_SET,
                    Constants.NOT_SET, parentTransactionType, MoneyFactory.fromDouble(0));
        } else {
            entity = SplitCategory.create(Constants.NOT_SET, Constants.NOT_SET,
                    Constants.NOT_SET, parentTransactionType, MoneyFactory.fromDouble(0));
        }

        return entity;
    }
}
