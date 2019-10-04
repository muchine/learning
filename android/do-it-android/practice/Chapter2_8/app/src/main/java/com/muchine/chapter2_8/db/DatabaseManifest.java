package com.muchine.chapter2_8.db;

import com.muchine.chapter2_8.db.logger.DatabaseLoggable;

/**
 * Created by sejoonlim on 9/13/16.
 */
public class DatabaseManifest {

    private final String databaseName;

    private final String tableName;

    private final int version;

    private DatabaseLoggable logger;

    public DatabaseManifest(String databaseName, String tableName, int version) {
        this.databaseName = databaseName;
        this.tableName = tableName;
        this.version = version;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public String getTableName() {
        return tableName;
    }

    public int getVersion() {
        return version;
    }

    public DatabaseLoggable getLogger() {
        return logger;
    }

    public void setLogger(DatabaseLoggable logger) {
        this.logger = logger;
    }
}
