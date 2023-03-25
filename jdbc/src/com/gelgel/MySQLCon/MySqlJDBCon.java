package com.gelgel.MySQLCon;

import java.sql.*;

public class MySqlJDBCon {



    public static void main(String[] args) throws Exception {
        //1.注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");

        //2.获取链接
        String url = "jdbc:mysql://localhost:3306/demo" + "?useUnicode=true&characterEncoding=UTF-8&userSSL=false&serverTimezone=GMT%2B8";
        String user = "root";
        String password = "555666";
        Connection conn = DriverManager.getConnection(url, user, password);

        //接受输入用户输入 用户名、用户密码
        String username = "周周";
        String pwd = "3200";

        //3.定义sql
        String sql = "update user set name='周周' where id = 3200";
        //String sql1 = "create database user1";
        String sql2 = "select * from user";
        String sql3 = "select * from user where name=? and password=?";

        //4.获取执行sql的对象 Statement
        Statement stmt = conn.createStatement();
        PreparedStatement pstmt = conn.prepareStatement(sql3);
        //设置问号的值
        pstmt.setString(2,username);
        pstmt.setString(1, pwd);
        try{
            //开启事务
            conn.setAutoCommit(false);
            //5.执行sql
            //  受影响的行数
            int count = stmt.executeUpdate(sql);
            //int count1 = stmt.executeUpdate(sql1);
            ResultSet rs = stmt.executeQuery(sql2);
            ResultSet RS = pstmt.executeQuery();
            //6.处理结果
            System.out.println(count);
            //System.out.println(count1);
            //光标向下移动一行，并判断当前行是否有数据
            while (rs.next() ){
                //获取数据
                int id = rs.getInt(1);
                String name = rs.getNString(2);
                String gender = rs.getNString(4);

                System.out.println(id);
                System.out.println(name);
                System.out.println(gender);
                System.out.println();
            }
            if(RS.next()){
                System.out.println("登录成功");
            }else {
                System.out.println("登录失败");
            }
            //提交事务
            conn.commit();
        }catch (Exception throwables){
            conn.rollback();
            throwables.printStackTrace();
        }

        //7.释放资源
        pstmt.close();
        //rs.close();
        stmt.close();
        conn.close();

    }
}
