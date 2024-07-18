package com.hechang.method;

import com.hechang.Test;
import com.hechang.model.Admin;
import com.hechang.model.Teacher;
import com.hechang.model.User;
import utils.DButils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class AdminDao extends DButils<Admin> {
    Scanner sc = new Scanner(System.in);
    int choice;
    @Override
    public Admin getEntity(ResultSet rs) {
       Admin admin = new Admin();
       try{
           admin.setId(rs.getInt(1));
           admin.setName(rs.getString("name"));
           admin.setPassword(rs.getString("password"));
           admin.setSex(rs.getString("sex"));
           admin.setAge(rs.getString("age"));
           admin.setPhone(rs.getString("phone"));
           admin.setLevel(rs.getInt("level"));
       }catch (SQLException ce){
           ce.printStackTrace();
       }

       return admin;

    }
    //定义管理员登录方法
    public Admin adminLogin(Admin admin){
        List<Admin> admins = getdataList("select * from admin_info where name = ? and password = ?", admin.getName(), admin.getPassword());
        return admins != null && admins.size() > 0 ? admins.get(0) : null;
    }


    //创建管理员登录后的系统页面
    public void adminMenu(User user){
        while(true){
            System.out.println("--------------------欢迎进入学生选课系统-------------------------");
            System.out.println("-------------您当前的身份是：管理员; 请选择您接下来的操作------------");
            System.out.println("---------------------1.教师信息管理-----------------------------");
            System.out.println("---------------------2.学生信息管理-----------------------------");
            System.out.println("---------------------3.课程信息管理-----------------------------");
            System.out.println("---------------------4.修改密码--------------------------------");
            System.out.println("---------------------5.退出系统--------------------------------");
            choice = sc.nextInt();
            switch (choice){
                case 1:
                    teacherManager();
                    break;
                case 2:
                    studentManager();
                    break;
                case 3:
                    lessonManager();
                    break;
                case 4:
                    chargeAdminPassword(user);
                case 5:
                    return;
            }
        }

    }

    //教师信息管理界面
    public void teacherManager(){
        here:
        while(true){
            System.out.println("--------------------请选择接下来的操作-------------------------");
            System.out.println("--------------------1.展示全部教师信息-------------------------");
            System.out.println("--------------------2.修改教师信息----------------------------");
            System.out.println("--------------------3.删除教师信息----------------------------");
            System.out.println("--------------------4.增加教师信息----------------------------");
            System.out.println("--------------------5.返回上一界面----------------------------");
            choice = sc.nextInt();
            TeacherDao teacherDao = new TeacherDao();
            switch (choice){
                case 1:
                    teacherDao.showAllTeachers();
                    break;
                case 2:
                    teacherDao.updateTeacherInfo();
                    break;
                case 3:
                    teacherDao.deleteTeacherInfo();
                    break;
                case 4:
                    teacherDao.addTeacherInfo();
                    break;
                case 5:
                    break here;
                default:
                    System.out.println("您输入的指令有误，请重新输入");
            }
        }
    }


    //学生信息管理界面
    public void studentManager(){
        here:
        while(true){
            System.out.println("--------------------请选择接下来的操作-------------------------");
            System.out.println("--------------------1.展示全部学生信息-------------------------");
            System.out.println("--------------------2.修改学生信息----------------------------");
            System.out.println("--------------------3.删除学生信息----------------------------");
            System.out.println("--------------------4.增加学生信息----------------------------");
            System.out.println("--------------------5.返回上一界面----------------------------");
            choice = sc.nextInt();
           StudentDao studentDao = new StudentDao();
            switch (choice){
                case 1:
                    studentDao.showAllStudents();
                    break;
                case 2:
                    studentDao.updateStudentInfo();
                    break;
                case 3:
                    studentDao.deleteStudentInfo();
                    break;
                case 4:
                    studentDao.addStudentInfo();
                    break;
                case 5:
                    break here;
                default:
                    System.out.println("您输入的指令有误，请重新输入");
            }
        }
    }

    //课程信息管理界面
    public void lessonManager(){
        here:
        while(true){
            System.out.println("--------------------请选择接下来的操作-------------------------");
            System.out.println("--------------------1.展示全部课程信息-------------------------");
            System.out.println("--------------------2.修改课程信息----------------------------");
            System.out.println("--------------------3.删除课程信息----------------------------");
            System.out.println("--------------------4.增加课程信息----------------------------");
            System.out.println("--------------------5.返回上一界面----------------------------");
            choice = sc.nextInt();
            LessonDao lessonDao = new LessonDao();
            switch (choice){
                case 1:
                    lessonDao.showAllLesson();
                    break;
                case 2:
                    lessonDao.updateLessonInfo();
                    break;
                case 3:
                    lessonDao.deleteLessonInfo();
                    break;
                case 4:
                    lessonDao.addLessonDaoInfo();
                    break;
                case 5:
                    break here;
                default:
                    System.out.println("您输入的指令有误，请重新输入");
            }
        }
    }

    //修改管理员密码
    public void chargeAdminPassword(User user){
        while (true){
            System.out.println("请输入原密码:");
            String password = sc.next();
            if (password.equals(user.getPassword())){
                System.out.println("请输入新密码");
                String npassword = sc.next();
                DButils.preparesmtSQL("update admin_info set password = ? where name = ? ", npassword,user.getUsername());
                System.out.println("修改密码成功,请重新登录");
                break;
            }else{
                System.out.println("您的密码输入错误请重新输入");
            }
        }
//        Admin admin = new Admin();
//        admin.setPassword(user.getUsername());
//        admin.setPassword(password);
//        admin = adminLogin(admin);
//        if (admin != null){
//            System.out.println("请输入新的密码");
//            String npassword = sc.next();
//            DButils.preparesmtSQL("update admin-info set password = ? where name = ?", npassword,user.getUsername());
//        }else {
//            System.out.println("查无此人，修改失败");
//        }
    }
}
