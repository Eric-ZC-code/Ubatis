package com.middleware.ubatis.session;

import java.sql.Connection;

public enum TransactionIsolatedLevel {

    None(Connection.TRANSACTION_NONE),
    READ_UNCOMMITTED(Connection.TRANSACTION_READ_UNCOMMITTED),
    READ_COMMITTED(Connection.TRANSACTION_READ_COMMITTED),
    REPEATABLE_READ(Connection.TRANSACTION_REPEATABLE_READ),
    TRANSACTION_SERIALIZABLE(Connection.TRANSACTION_SERIALIZABLE);

    private final int level;

    TransactionIsolatedLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
