package com.hechang;

import com.hechang.method.AdminDao;
import com.hechang.method.StudentDao;
import com.hechang.method.TeacherDao;
import com.hechang.model.Admin;
import com.hechang.model.Student;
import com.hechang.model.Teacher;
import com.hechang.model.User;
import utils.DButils;
import java.util.List;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {

        LoginShow();

    }

    //写一个登录界面
    public static void LoginShow(){
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.println("--------------------欢迎进入学生选课系统-------------------------");
            System.out.println("--------------------请选择您的身份------------------------------");
            System.out.println("--------------------1.管理员 2.教师 3.学生----------------------");
            int choice = sc.nextInt();
            switch (choice){
                case 1:
                    adminLoginIsTrue();
                    break;
                case 2:
                    teacherLoginIsTrue();
                    break;
                case 3:
                    studentLoginIsTrue();
                    break;
                default:
                    System.out.println("您输入的指令不对，请重新输入");

            }
        }
    }

    //管理员登陆验证
    public static void adminLoginIsTrue(){
        Scanner sc = new Scanner(System.in);
        AdminDao adminDao = new AdminDao();
        while (true){
            System.out.println("请输入管理员用户名");
            String name = sc.next();
            System.out.println("请输入管理员密码");
            String password = sc.next();
            User user = new User(name,password);
            Admin admin = new Admin();
            admin.setName(name);
            admin.setPassword(password);
            if (adminDao.adminLogin(admin) != null){
                System.out.println("登录成功");
                adminDao.adminMenu(user);
                break;
            }else{
                System.out.println("您输入的用户名或密码错误请重新输入");
            }
        }
    }



    //教师登陆验证
    public static void teacherLoginIsTrue(){
        Scanner sc = new Scanner(System.in);
        TeacherDao teacherDao = new TeacherDao();
        while (true){
            System.out.println("请输入教师用户名");
            String name = sc.next();
            System.out.println("请输入教师密码");
            String password = sc.next();
            User user = new User(name, password);
            Teacher teacher = new Teacher();
            teacher.setName(name);
            teacher.setPassword(password);
            if (teacherDao.teacherLogin(teacher) != null){
                System.out.println("登录成功");
                teacherDao.teacherMenu(user);
                break;
            }else{
                System.out.println("您输入的用户名或密码错误请重新输入");
            }
        }
    }

    //学生登陆验证
    public static void studentLoginIsTrue(){
        Scanner sc = new Scanner(System.in);
        StudentDao studentDao = new StudentDao();
        while (true){
            System.out.println("请输入学生用户名");
            String name = sc.next();
            System.out.println("请输入学生学号");
            String number = sc.next();
            User user = new User(name, number);
            Student student = new Student();
            student.setName(name);
            student.setNumber(number);
            if (studentDao.studentLogin(student) != null){
                System.out.println("登录成功");
                studentDao.studentMenu(user);
                break;
            }else{
                System.out.println("您输入的用户名或学号错误请重新输入");
            }
        }
    }




}
