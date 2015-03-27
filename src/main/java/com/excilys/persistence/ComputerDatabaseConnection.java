package com.excilys.persistence;

import com.excilys.exception.PersistenceException;
import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public enum ComputerDatabaseConnection {
    INSTANCE;

    private BoneCP connectionPool;
    private Properties properties;
    private String url;

    ComputerDatabaseConnection() {
        try {
            loadConfigFile();
        } catch (Exception e) {
            throw new PersistenceException(e);
        }
    }

    public Connection getInstance() {
        try {
            if (connectionPool == null) {
                Class.forName(properties.getProperty("driver")).newInstance();
                BoneCPConfig config = new BoneCPConfig();
                config.setJdbcUrl(properties.getProperty("url"));
                config.setUsername(properties.getProperty("user"));
                config.setPassword(properties.getProperty("password"));
                config.setMinConnectionsPerPartition(Integer.valueOf(properties.getProperty("minConnectionsPerPartition")));
                config.setMaxConnectionsPerPartition(Integer.valueOf(properties.getProperty("maxConnectionsPerPartition")));
                config.setPartitionCount(Integer.valueOf(properties.getProperty("partitionCount")));
                connectionPool = new BoneCP(config);
            }
        } catch (SQLException | InstantiationException | IllegalAccessException
                | ClassNotFoundException e) {
            throw new PersistenceException(e);
        }

        final Connection connection;
        try {
            connection = connectionPool.getConnection();
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }

        return connection;
    }

    /*
     * Load config.properties.
     */
    private void loadConfigFile() throws IOException {
        properties = new Properties();
        try (final InputStream is = ComputerDatabaseConnection.class
                .getClassLoader().getResourceAsStream("config.properties")) {
            properties.load(is);
            url = properties.getProperty("url");
        }
    }
}
