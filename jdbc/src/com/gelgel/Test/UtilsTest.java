package com.gelgel.Test;

import com.gelgel.Example.Users;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class UtilsTest {
    @Test
    public void insert() throws Exception {

        /**
         * 查询所有
         * 不需要参数
         */
        //1.获取Connection
        //
        Properties properties = new Properties();
        //加载配置文件
        properties.load(new FileInputStream("jdbc/src/druid.properties"));
        //获取连接池对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        //获取数据库连接Connection
        Connection conn = dataSource.getConnection();
        //定义SQL
        String sql = "select * from user";
        //获取pstmt对象
        PreparedStatement pstmt = conn.prepareStatement(sql);
        //设置参数

        //执行SQL
        ResultSet rs = pstmt.executeQuery();
        Users users = null;
        List<Users> User = new ArrayList<>();
        //处理结果 List<Brand>封装Brand对象，装在List集合
        while(rs.next()){
            //获取数据
            int id  = rs.getInt("id");
            String name = rs.getString("name");
            //String password = rs.getString("password");
            String gender = rs.getString("gender");
            int status = rs.getInt("status");
            //封装
            users = new Users();
            users.setId(id);
            users.setName(name);
            users.setGender(gender);
            users.setStatus(status);
            //装载集合
            User.add(users);
        }
        //释放资源
        rs.close();
        pstmt.close();
        conn.close();
    }
}
