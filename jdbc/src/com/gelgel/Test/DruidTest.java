package com.gelgel.Test;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DruidTest {
    public static void main(String[] args) {
        //加载属性文件
        Properties properties = new Properties();
        //
        String propertyFile=DruidTest.class.getResource("/src/druid.properties").getPath();

        try{
           //propertyFile = new URLDecoder().decode(propertyFile. "UTF-8");

            properties.load(new FileInputStream(propertyFile));
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);

            for(int i=0; i<20;i++){
                conn = dataSource.getConnection();
            }
            String sql="selsct * from demo";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){

                //System.out.println(rs.getInt(id));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(conn!=null){
              try{
                  conn.close();
              }catch (SQLException e){
                  e.printStackTrace();
              }
            }
            if(ps!=null){
                try{
                    ps.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(rs!=null){
                try{
                    rs.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }
}

