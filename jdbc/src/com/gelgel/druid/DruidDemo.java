package com.gelgel.druid;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

/**
 * druid数据库连接池
 */
public class DruidDemo {
    public static void main(String[] args) throws Exception {
        //导入jar包
        //定义配置文件
        //加载配置文件
        Properties prop = new Properties();
        prop.load(new FileInputStream("D:/4/IdeaProjects/jdbc_project/jdbc/src/druid.properties"));
        //获取连接池对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
        //获取数据库连接池
        Connection connection = dataSource.getConnection();

        //定义sql
        String sql = "select * from userim";

        //
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()){
            int Id = rs.getInt(1);
            String Name = rs.getString(2);
            String Gender = rs.getString(3);
            System.out.println(Id);
            System.out.println(Name);
            System.out.println(Gender);
            System.out.println();
        }

        stmt.close();
        connection.close();

        //System.out.println(connection);

        /*寻找路径
        System.out.println(System.getProperty("user.dir"));*/
    }
}
