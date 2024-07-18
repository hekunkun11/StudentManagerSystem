package utils;

import java.lang.annotation.Target;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class DButils<T> {

    public DButils() {
    }

    //1.使用静态代码块来加载数据库驱动
    static {
        //静态代码块优先级最高，且只加载一次
        //1.通过反射加载数据库驱动
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException ce){
            ce.printStackTrace();
        }
    }

    public static Connection getConnection() {
        Connection connection = null;
        //2.创建数据库链接
        try {
            String url = "jdbc:mysql://localhost:3306/mypra?autoReconnection=true&useSSL=false";
            String userName = "root";
            String password = "123456";
            connection = DriverManager.getConnection(url, userName, password);
        } catch (SQLException ce) {
            ce.printStackTrace();
        }
            return connection;
    }

    //使用 statement 来实现增删改
    public static int smtExcuteSQL(String dbsql){
        int result = 0;
        try {
            Connection connection = getConnection();
            Statement smt = connection.createStatement();
            result = smt.executeUpdate(dbsql);
            if (result > 0){
                System.out.println("执行成功！！");
            }else{
                System.out.println("执行失败！！");
            }
            dbClose(connection,smt,null,null);
        }catch (SQLException ce){
            ce.printStackTrace();
        }
       return result;

    }

    //使用 prepareStatement 来实现正删改            Object...? 表示包含一个或者多个参数的Object对象数组
    public static int preparesmtSQL(String sql, Object...obj){
        int result = 0;
        try{
            //获取数据库链接
            Connection connection = getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);
            for (int i = 0; i < obj.length; i++) {
                pst.setObject((i+1),obj[i]);
            }
            result = pst.executeUpdate();
            if (result > 0){
                System.out.println("执行成功！！");
            }else{
                System.out.println("执行失败！！");
            }
            dbClose(connection,null,pst,null);

        }catch (SQLException ce){
            ce.printStackTrace();
        }

        return result;
        }

        //关闭流
    public static void dbClose(Connection connection, Statement smt, PreparedStatement psmt, ResultSet rs){
        try {
            if(rs != null) rs.close();
            if(psmt != null) psmt.close();
            if(smt != null) smt.close();
            if(connection != null) connection.close();
        }catch (SQLException ce){
            ce.printStackTrace();
        }
    }

    // 查询
    // T表示泛化成不指定的子类

    public List<T> getdataList(String sql, Object...obj){
        List<T> dataList = new ArrayList<T>();
        try {
            Connection connection = getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);
            for (int i = 0; i < obj.length; i++) {
                pst.setObject((i+1),obj[i]);
            }

            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                T t = getEntity(rs);
                dataList.add(t);
        }
            //关闭流
            dbClose(connection,null,pst,rs);
        }catch (SQLException ce){
            ce.printStackTrace();
        }

        return dataList;

    }

    //获取某个子类的抽象方法
    public abstract T getEntity(ResultSet rs);


}


