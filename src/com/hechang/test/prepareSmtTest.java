package com.hechang.test;

import java.sql.*;
import java.util.Map;

public class prepareSmtTest {
    public static void main(String[] args) {
        //1.通过反射加载数据库驱动
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException ce){
            ce.printStackTrace();
        }

        //2.创建数据库链接
        try {
            String url = "jdbc:mysql://localhost:3306/newsdb_7?autoReconnection=true&useSSL=false";
            String userName = "root";
            String password = "123456";
            Connection connection = DriverManager.getConnection(url, userName, password);

            //3.创建回话对象
//            //添加操作
//            String insertSql = "insert into admin(adminId, adminName, adminPwd) values (?, ?, ?)";
//            PreparedStatement pst = connection.prepareStatement(insertSql);
//            pst.setObject(1,2);
//            pst.setObject(2,"黄小鸭");
//            pst.setObject(3,"1024");
//            //执行会话
//            int result = pst.executeUpdate();
//            if (result > 0){
//                System.out.println("添加成功！！");
//            }else{
//                System.out.println("添加失败！！");
//            }


            //删除操作
//            String deleteSql = "delete from admin where adminName = ?";
//            PreparedStatement pst = connection.prepareStatement(deleteSql);
//            pst.setObject(1,"黄小鸭");
//
//            //执行会话
//            int result = pst.executeUpdate();
//            if (result > 0){
//                System.out.println("删除成功！！");
//            }else{
//                System.out.println("删除失败！！");
//            }

//            //修改操作
//            String changeSql = "update admin set adminName = ?, adminPwd = ? where adminId = ?";
//            PreparedStatement pst = connection.prepareStatement(changeSql);
//            pst.setObject(1,"韩帅坤");
//            pst.setObject(2,"2024");
//            pst.setObject(3,2);
//
//            //执行会话
//            int result = pst.executeUpdate();
//            if (result > 0){
//                System.out.println("修改成功！！");
//            }else{
//                System.out.println("修改失败！！");
//            }

            //查询操作
            String selectSql = "select * from admin where adminName = ? ";
            PreparedStatement pst = connection.prepareStatement(selectSql);
            pst.setObject(1,"何畅");
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                // rs.getString()可以为列的序号或者列名
                int id = rs.getInt(1);    //通过列的序号来获取列值
                String adminName = rs.getString("adminName");   //通过列名来获取列值
                String adminPwd = rs.getString("adminPwd");

                System.out.println("序号: " + id + " 姓名: " + adminName + " 密码: " + adminPwd);
            }

            rs.close();

            //5.关闭流
            pst.close();
            connection.close();


        }catch (SQLException ce){
            ce.printStackTrace();
        }


    }
}
