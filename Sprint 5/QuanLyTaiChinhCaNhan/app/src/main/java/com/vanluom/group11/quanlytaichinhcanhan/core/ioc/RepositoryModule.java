
package com.vanluom.group11.quanlytaichinhcanhan.core.ioc;

import com.vanluom.group11.quanlytaichinhcanhan.datalayer.StockHistoryRepositorySql;
import com.vanluom.group11.quanlytaichinhcanhan.datalayer.StockRepositorySql;
import com.squareup.sqlbrite.BriteDatabase;

import dagger.Module;
import dagger.Provides;

/**
 * Module for repositories
 */
@Module
public class RepositoryModule {
    @Provides
    StockRepositorySql provideStockRepository(BriteDatabase db) {
        return new StockRepositorySql(db);
    }

    @Provides
    StockHistoryRepositorySql provideStockHistoryRepository(BriteDatabase db) {
        return new StockHistoryRepositorySql(db);
    }
}
