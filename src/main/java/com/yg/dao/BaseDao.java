package com.yg.dao;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class BaseDao {
    private static String url;
    private static String driver;
    private static String username;
    private static String password;

    //静态代码块自动加载  类加载的时候就自动加载了
    static {
        Properties properties = new Properties();
        try {

            InputStream ras = BaseDao.class.getClassLoader().getResourceAsStream("db.properties");
            properties.load(ras);
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver = properties.getProperty("driver");
        url = properties.getProperty("url");
        username = properties.getProperty("username");
        password = properties.getProperty("password");
        System.out.println(driver + url + username + password);
    }

    //获取数据库的连接
    public static Connection getConnection() {
        Connection con = null;

        try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, username, password);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    //编写查询公共类
    public static ResultSet excute(Connection connection, ResultSet resultSet, PreparedStatement preparedStatement, String sql, Object[] parms) throws SQLException {
        //预处理sql语句
        preparedStatement = connection.prepareStatement(sql);
        for (int i = 0; i < parms.length; i++) {
            preparedStatement.setObject(i + 1, parms[i]);
        }
        //执行的结果集
        resultSet = preparedStatement.executeQuery();
        return resultSet;
    }

    //编写增删改类
    public static int update(Connection connection, String sql, Object[] parms) throws SQLException {

        int update = 0;
        try {
            connection.setAutoCommit(false);
            PreparedStatement presta = connection.prepareStatement(sql);
            for (int i = 0; i < parms.length; i++) {
                presta.setObject(i + 1, parms[i]);
            }
            update = presta.executeUpdate();
        } catch (SQLException e) {
            connection.rollback();
        }
        connection.commit();
        return update;
    }

    //关闭连接
    public static boolean close(Connection connection, PreparedStatement presta, ResultSet resultSet) {
        boolean flag = true;
        try {
            presta.close();
            resultSet.close();
            connection.close();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }
}
