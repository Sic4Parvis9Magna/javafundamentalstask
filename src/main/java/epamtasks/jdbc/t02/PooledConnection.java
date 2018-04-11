package epamtasks.jdbc.t02;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.function.Consumer;

public class PooledConnection implements Connection {
    private static final Logger log = LogManager.getLogger(PooledConnection.class);
    private Consumer<PooledConnection> closer;
    private Connection connection;
    private static final String ERROR_MSG;

    static {
        ERROR_MSG = "Error {} apears from {}";
    }


    public PooledConnection(Consumer<PooledConnection> closer, Connection connection) {
        this.closer = closer;
        this.connection = connection;
    }


    @Override
    public void close() {
        closer.accept(this);
    }


    public void reallyClose() {
        try {
            connection.close();
        } catch (SQLException e) {
            log.error(ERROR_MSG,e.getMessage(),e.getStackTrace());
            throw new ConnectionPoolException("Can't close Connection error!",new SQLException());
        }
    }

    public void commit() throws java.sql.SQLException {
        this.connection.commit();
    }

    public void setTypeMap(Map<String, Class<?>> map) throws java.sql.SQLException {
        this.connection.setTypeMap(map);
    }

    public Clob createClob() throws java.sql.SQLException {
        return this.connection.createClob();
    }

    public boolean getAutoCommit() throws java.sql.SQLException {
        return this.connection.getAutoCommit();
    }

    public void beginRequest() throws java.sql.SQLException {
        this.connection.beginRequest();
    }

    public void setReadOnly(boolean readOnly) throws java.sql.SQLException {
        this.connection.setReadOnly(readOnly);
    }

    public void setAutoCommit(boolean autoCommit) throws java.sql.SQLException {
        this.connection.setAutoCommit(autoCommit);
    }

    public Properties getClientInfo() throws java.sql.SQLException {
        return this.connection.getClientInfo();
    }

    public Map<String, Class<?>> getTypeMap() throws java.sql.SQLException {
        return this.connection.getTypeMap();
    }

    public void setCatalog(String catalog) throws java.sql.SQLException {
        this.connection.setCatalog(catalog);
    }

    public void rollback() throws java.sql.SQLException {
        this.connection.rollback();
    }

    public void clearWarnings() throws java.sql.SQLException {
        this.connection.clearWarnings();
    }

    public void setShardingKey(ShardingKey shardingKey) throws java.sql.SQLException {
        this.connection.setShardingKey(shardingKey);
    }

    public CallableStatement prepareCall(String sql) throws java.sql.SQLException {
        return this.connection.prepareCall(sql);
    }

    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws java.sql.SQLException {
        return this.connection.prepareCall(sql, resultSetType, resultSetConcurrency);
    }

    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return this.connection.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
    }

    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        return this.connection.prepareStatement(sql, resultSetType, resultSetConcurrency);
    }

    public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
        return this.connection.createStruct(typeName, attributes);
    }

    public boolean isValid(int timeout) throws SQLException {
        return this.connection.isValid(timeout);
    }

    public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
        this.connection.setNetworkTimeout(executor, milliseconds);
    }

    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return this.connection.isWrapperFor(iface);
    }

    public String nativeSQL(String sql) throws SQLException {
        return this.connection.nativeSQL(sql);
    }

    public <T> T unwrap(Class<T> iface) throws SQLException {
        return this.connection.unwrap(iface);
    }

    public Statement createStatement() throws SQLException {
        return this.connection.createStatement();
    }

    public int getNetworkTimeout() throws SQLException {
        return this.connection.getNetworkTimeout();
    }

    public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
        return this.connection.createStatement(resultSetType, resultSetConcurrency);
    }

    public void rollback(Savepoint savepoint) throws SQLException {
        this.connection.rollback(savepoint);
    }

    public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
        return this.connection.prepareStatement(sql, columnIndexes);
    }

    public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
        return this.connection.prepareStatement(sql, columnNames);
    }

    public SQLWarning getWarnings() throws SQLException {
        return this.connection.getWarnings();
    }

    public boolean isReadOnly() throws SQLException {
        return this.connection.isReadOnly();
    }

    public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return this.connection.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
    }

    public void abort(Executor executor) throws SQLException {
        this.connection.abort(executor);
    }

    public void setShardingKey(ShardingKey shardingKey, ShardingKey superShardingKey) throws SQLException {
        this.connection.setShardingKey(shardingKey, superShardingKey);
    }

    public Savepoint setSavepoint() throws SQLException {
        return this.connection.setSavepoint();
    }

    public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
        return this.connection.prepareStatement(sql, autoGeneratedKeys);
    }

    public String getSchema() throws SQLException {
        return this.connection.getSchema();
    }

    public void setHoldability(int holdability) throws SQLException {
        this.connection.setHoldability(holdability);
    }

    public DatabaseMetaData getMetaData() throws SQLException {
        return this.connection.getMetaData();
    }

    public Savepoint setSavepoint(String name) throws SQLException {
        return this.connection.setSavepoint(name);
    }

    public void endRequest() throws SQLException {
        this.connection.endRequest();
    }

    public void setClientInfo(String name, String value) throws SQLClientInfoException {
        this.connection.setClientInfo(name, value);
    }

    public boolean setShardingKeyIfValid(ShardingKey shardingKey, int timeout) throws SQLException {
        return this.connection.setShardingKeyIfValid(shardingKey, timeout);
    }

    public boolean isClosed() throws SQLException {
        return this.connection.isClosed();
    }

    public int getTransactionIsolation() throws SQLException {
        return this.connection.getTransactionIsolation();
    }

    public SQLXML createSQLXML() throws SQLException {
        return this.connection.createSQLXML();
    }

    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return this.connection.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
    }

    public int getHoldability() throws SQLException {
        return this.connection.getHoldability();
    }

    public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
        return this.connection.createArrayOf(typeName, elements);
    }

    public Blob createBlob() throws SQLException {
        return this.connection.createBlob();
    }

    public void setTransactionIsolation(int level) throws SQLException {
        this.connection.setTransactionIsolation(level);
    }

    public boolean setShardingKeyIfValid(ShardingKey shardingKey, ShardingKey superShardingKey, int timeout) throws SQLException {
        return this.connection.setShardingKeyIfValid(shardingKey, superShardingKey, timeout);
    }

    public void releaseSavepoint(Savepoint savepoint) throws SQLException {
        this.connection.releaseSavepoint(savepoint);
    }

    public void setSchema(String schema) throws SQLException {
        this.connection.setSchema(schema);
    }

    public NClob createNClob() throws SQLException {
        return this.connection.createNClob();
    }

    public String getClientInfo(String name) throws SQLException {
        return this.connection.getClientInfo(name);
    }

    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return this.connection.prepareStatement(sql);
    }

    public void setClientInfo(Properties properties) throws SQLClientInfoException {
        this.connection.setClientInfo(properties);
    }

    public String getCatalog() throws SQLException {
        return this.connection.getCatalog();
    }
}
