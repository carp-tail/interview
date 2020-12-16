package com.guaniu.mybatis.jdbc;

import java.sql.*;

/**
 * @Author: guaniu
 * @Description:
 * @Date: Create in 23:52 2020/12/15
 * @Modified
 */
public class JdbcDemo {

    static String URL = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC"; // TCP协议
    static String USER = "root";
    static String PASSWORD = "123456";

    public static void main(String[] args) {
        try {
            //com.mysql.jdbc.Driver(mysql 8.0之前从此包下加载驱动)
            //第一步：加载数据库驱动
//            Class clazz =
            Class.forName("com.mysql.cj.jdbc.Driver");

            //或者使用以下方式注册
//            DriverManager.registerDriver(com.mysql.cj.jdbc.Driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        try {
            // 第二步：获取数据库连接
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            // 第三步：创建sql运行操作
            stat = conn.createStatement();

            // 第四步：运行sql语句
            rs = stat.executeQuery("select * from user");

            // 第五步： 处理sql运行结果
            // 有数据时rs.next() 返回true
            while (rs.next()){
                // 数据通过resultSet的映射获取
                System.out.println("id：" + rs.getInt("id") + "  name：" + rs.getString("name") + " age："
                + rs.getInt("age"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            // 第六步、 释放资源，关闭结果集、操作、数据库连接
            if (rs != null){
                try {
                    rs.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (stat != null){
                try {
                    stat.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (conn != null){
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }
}
/**
 * 其中加载数据库驱动、获取连接的过程都属于样板式代码，每次操作都重写一遍太麻烦了，mybatis解决了这个问题
 * 从resultSet中获取属性需要一列一列的获取，mybatis使数据库列属性与Bean属性映射
 */
