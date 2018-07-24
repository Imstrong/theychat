package com.bruce.theychat.pool;

import javax.sql.DataSource;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * 自定义连接池
 */
public class PooledDataSource implements DataSource {
    private static final LinkedList<Connection> connectionPool = new LinkedList<>();

    static {
        InputStream inputStream = PooledDataSource.class.getResourceAsStream("db.properties");
        Properties props = new Properties();
        try {
            props.load(inputStream);

            String driver = props.getProperty("driver");
            String url = props.getProperty("url");
            String username = props.getProperty("userName");
            String password = props.getProperty("password");
            int initialSize = Integer.parseInt(props.getProperty("initialSize"));
            Class.forName(driver);
            for (int i = 0; i < initialSize; i++) {
                Connection conn = DriverManager.getConnection(url, username, password);
                connectionPool.add(conn);
            }
        } catch (java.io.IOException e) {
            System.err.println("打开属性文件失败" + e.getMessage());
        } catch (java.lang.ClassNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (java.sql.SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        if (connectionPool.size() > 0) {
            Connection conn = connectionPool.removeFirst();
//            return (Connection) Proxy.newProxyInstance(PooledDataSource.class.getClassLoader(), conn.getClass().getInterfaces(), (Object proxy, Method method, Object[] args) -> {
//                return null;
//            });
        }
        return null;
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}
