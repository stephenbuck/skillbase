package com.headspin.skillbase.common.app;

import com.gruelbox.transactionoutbox.Dialect;
import com.gruelbox.transactionoutbox.Persistor;
import com.gruelbox.transactionoutbox.TransactionManager;
import com.gruelbox.transactionoutbox.TransactionOutbox;

public abstract class AppOutbox {

    private final TransactionManager txmanager;
    private final TransactionOutbox txoutbox;

    public AppOutbox() {
        this.txmanager = null;
        this.txoutbox = TransactionOutbox.builder()
                .transactionManager(this.txmanager)
                .persistor(Persistor.forDialect(Dialect.POSTGRESQL_9))
                .build();
    }

    public <T> T schedule(Class<T> clazz) {
        return this.txoutbox.schedule(clazz);
    }
}
