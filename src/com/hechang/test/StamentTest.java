package com.hechang.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class StamentTest {
    public static void main(String[] args) {
        //创建jdbc链接共分为5步
        // 1.通过反射来加载数据库驱动 需要抛出异常
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException ce){
            ce.printStackTrace();
        }

        //2.创建数据库链接
        try{
            String url = "jdbc:mysql://localhost:3306/newsdb_7?autoReconnection=true&useSSL=false";
            String userName = "root";
            String password = "123456";
            Connection connection = DriverManager.getConnection(url, userName, password);
            //3.创建会话对象 Statement prepareStatement
            Statement smt = connection.createStatement();

            //4.执行会话操作 增加 删除 修改 查找 crud:操作
            //执行添加操作
            String insertSql = "insert into admin(adminId, adminName, adminPwd) values (1, '何畅', '2011' )";
            int result = smt.executeUpdate(insertSql);
            if (result > 0){
                System.out.println("添加成功！！");
            }else{
                System.out.println("添加失败！！");
            }

            //5.关闭流
            smt.close();
            connection.close();

        }catch (SQLException ce){
            ce.printStackTrace();
        }




    }
    
}
