package com.gelgel.Utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class JDBCUtils {

    private static Connection connection;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    private static JDBCUtils jdbcUtils;

    private JDBCUtils() {
    }

    public static JDBCUtils getInstance(){
        if(jdbcUtils==null){
            jdbcUtils = new JDBCUtils();
        }
        return jdbcUtils;
    }

    /**
     * 获取连接
     * @return
     */
    public static Connection getConnection() {
        try {
            //驱动加载
            Properties properties = new Properties();
            properties.load(new FileInputStream("D:/4/IdeaProjects/jdbc_project/jdbc/src/druid.properties"));
            DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
           //获取连接
            connection = dataSource.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(">>>>>>>数据库连接成功！");
        return connection;
        //return DriverManager.getConnection(url,name,password);
    }

    /**
     * 获取Preparedment对象
     * @param sql
     * @return
     * @throws SQLException
     */
    public PreparedStatement getPreparedStatement(String sql,Object...args) throws SQLException {

        connection = getConnection();
        //获取prepareStatement对象
        preparedStatement=connection.prepareStatement(sql);
        //sql语句存在问号时，将对应的数据传进去
        if(args!=null && args.length>0){
            for(int i=0;i<args.length;i++){
                preparedStatement.setObject(i+1,args[i]);
            }
        }
        return preparedStatement;
    }

    /**
     * 查询所有信息
     * @param sql
     * @return
     * @throws Exception
     */
    public ResultSet SelectAll(String sql) throws Exception {
        try{
            connection = getConnection();
            preparedStatement = getPreparedStatement(sql);
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){

                int Id = resultSet.getInt(1);
                String Name = resultSet.getString(2);
                String Gender = resultSet.getString(3);
                System.out.println(Id);
                System.out.println(Name);
                System.out.println(Gender);
                System.out.println();

            }

            } catch (SQLException throwables) {
            throwables.printStackTrace();
            }
        return resultSet;
    }

    /**
     * 查询
     * @param sql
     * @param data
     */
    public ArrayList<Object> Query(String sql,Object[] data){
        //用来存储字段个数
        int cct = 0;
        // //定义ResultSetMetaData对象 ResultSetMetaData为 描述数据的类
        ResultSetMetaData resultSetMetaData = null;
        try {
            //获取PreparedStatement对象
            preparedStatement = getPreparedStatement(sql, data);
            //PreparedStatement已经进行过预处理 获取ResultSet对象
            resultSet = preparedStatement.executeQuery();
            //PreparedStatement已经进行过预处理
            resultSetMetaData = resultSet.getMetaData();
            //返回所有字段的数目
            cct = resultSetMetaData.getColumnCount();
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //用链表来存放每一行的结果
        ArrayList<Object> list = new ArrayList<>();
        try {
            while (resultSet.next()){
                //根据字段数获取信息
                for(int i=1; i<=cct; i++){
                    Object o = resultSet.getObject(i);
                    list.add(o);
                }
            }
            //输出每一行的结果
            System.out.println(list);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }
    /**
     * 增删改
     * @param sql
     * @param data
     * @return
     * @throws SQLException
     */
    public int Updata(String sql, Object[] data) throws SQLException {

        //preparedStatement = getPreparedStatement(sql);
        int count = 0;
        //System.out.println(count > 0);

        try {
            preparedStatement = getPreparedStatement(sql);
            if (data != null && data.length > 0) {
                for (int i = 0; i < data.length; i++) {
                    preparedStatement.setObject((i + 1), data[i]);
                }
            }
            count = preparedStatement.executeUpdate();
            return count;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }

    /**
     * 关闭资源
     * @param resultSet
     * @param preparedStatement
     * @param connection
     */
    private static void close(ResultSet resultSet, PreparedStatement preparedStatement, Connection connection) {
        try {
            if(resultSet!=null)
                resultSet.close();
            if(preparedStatement!=null)
                preparedStatement.close();
            if(connection!=null)
                connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    /*
    //DML关闭资源
    public  static void closeResource(Connection conn, Statement stat){
        try{
            if(stat!=null){
                stat.close();
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }

    //DQL关闭资源
    public static void closeResource(Connection conn, Statement stat, ResultSet set){
        try{
            if(set!=null){
                set.close();
            }
            if(stat!=null){
                stat.close();
            }
            if(conn!=null){
                conn.close();
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }*/

}
