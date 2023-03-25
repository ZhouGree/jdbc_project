package com.gelgel;

import com.gelgel.Example.Users;
import com.gelgel.Utils.JDBCUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {

        JDBCUtils ju = JDBCUtils.getInstance();
        Object[] data = { };
        String sql = "select name,gender from userim " ;
        //测试
        //ju.Updata("insert into userim(id,name,gender) values(3205,'张三','男')",null);
        //ju.Updata("update userim set name = '章章'where id=3205",null);
        //ju.Updata("delete from userim where id=3205", null);
        //ju.SelectAll("select * from userim");
        ju.Query(sql,null);
    }
}
