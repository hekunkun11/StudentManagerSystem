package com.hechang.method;

import com.hechang.model.*;
import utils.DButils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TeacherDao extends DButils<Teacher> {
    int choice;
    List<Teacher> teachers = getdataList("select * from teacher_info");
    LessonDao lessonDao = new LessonDao();
    XuanXiuDao xuanXiuDao = new XuanXiuDao();
    StudentDao studentDao = new StudentDao();
    Scanner sc = new Scanner(System.in);
    @Override
    public Teacher getEntity(ResultSet rs) {
        Teacher teacher = new Teacher();
        try{
            teacher.setId(rs.getInt(1));
            teacher.setName(rs.getString("name"));
            teacher.setPassword(rs.getString("password"));
            teacher.setSex(rs.getString("sex"));
            teacher.setAge(rs.getString("age"));
            teacher.setPhone(rs.getString("phone"));
            teacher.setLevel(rs.getInt("level"));
            teacher.setPosition(rs.getString("position"));
            teacher.setField(rs.getString("feild"));
            teacher.setLessonName(rs.getString("lesson_name"));
        }catch (SQLException ce){
            ce.printStackTrace();
        }

        return teacher;
    }

    //显示全部教师信息
    public void showAllTeachers(){
        System.out.println("以下是全部教师信息:");
        //遍历查询
        for (int i = 0; i < teachers.size(); i++) {
            System.out.println("教师id:" + teachers.get(i).getId() + " 教师姓名:" + teachers.get(i).getName()
            + " 教师性别:" + teachers.get(i).getSex() + " 教师年龄:" + teachers.get(i).getAge() + " 教师职位:" + teachers.get(i).getPosition()
            + " 教师所属专业:" + teachers.get(i).getField() + " 教师联系方式:" + teachers.get(i).getPhone() + " 教师所授课程:" + teachers.get(i).getLessonName());
        }
    }

    //修改教师信息
    public void updateTeacherInfo(){
        System.out.println("请输入要修改教师的id 或输入 -1 来取消修改");
        int id = sc.nextInt();
        if (id == -1) return;
        while (true){
            if (id > 0 && id <= teachers.size()){
                System.out.println("请选择您要修改的位置:1--姓名 2--性别 3--年龄 4--职位 5--所属专业 6--手机号码 7--密码 8--所授课程");
                System.out.println("可输入-1取消修改操作");
                int choice = sc.nextInt();
                switch (choice){
                    case -1:
                        return;
                    case 1:
                        System.out.println("当前该教师的姓名为" + this.getdataList("select * from teacher_info where id = ?",id).get(0).getName()
                        + " 请输入你要修改的姓名:");
                        String name = sc.next();
                        DButils.preparesmtSQL("update teacher_info set name = ? where id = ?",name,id);
                        System.out.println("修改成功！！");
                        break;
                    case 2:
                        System.out.println("当前该教师的性别为" + this.getdataList("select * from teacher_info where id = ?",id).get(0).getSex()
                                + " 请输入你要修改的性别:");
                        String sex = sc.next();
                        DButils.preparesmtSQL("update teacher_info set sex = ? where id = ?",sex,id);
                        System.out.println("修改成功！！");
                        break;
                    case 3:
                        System.out.println("当前该教师的年龄为" + this.getdataList("select * from teacher_info where id = ?",id).get(0).getAge()
                                + " 请输入你要修改的年龄:");
                        String age = sc.next();
                        DButils.preparesmtSQL("update teacher_info set age = ? where id = ?",age,id);
                        System.out.println("修改成功！！");
                        break;
                    case 4:
                        System.out.println("当前该教师的职位为" + this.getdataList("select * from teacher_info where id = ?",id).get(0).getPosition()
                                + " 请输入你要修改的职位:");
                        String pos = sc.next();
                        DButils.preparesmtSQL("update teacher_info set position = ? where id = ?",pos,id);
                        System.out.println("修改成功！！");
                        break;
                    case 5:
                        System.out.println("当前该教师的所属专业为" + this.getdataList("select * from teacher_info where id = ?",id).get(0).getField()
                                + " 请输入你要修改的所属专业:");
                        String fie = sc.next();
                        DButils.preparesmtSQL("update teacher_info set feild = ? where id = ?",fie,id);
                        System.out.println("修改成功！！");
                        break;
                    case 6:
                        System.out.println("当前该教师的手机号码为" + this.getdataList("select * from teacher_info where id = ?",id).get(0).getPhone()
                                + " 请输入你要修改的手机号码:");
                        String phone = sc.next();
                        DButils.preparesmtSQL("update teacher_info set phone = ? where id = ?",phone,id);
                        System.out.println("修改成功！！");
                        break;
                    case 7:
                        System.out.println("当前该教师的密码为" + this.getdataList("select * from teacher_info where id = ?",id).get(0).getPassword()
                                + " 请输入你要修改的密码:");
                        String password = sc.next();
                        DButils.preparesmtSQL("update teacher_info set phone = ? where id = ?",password,id);
                        System.out.println("修改成功！！");
                        break;
                    case 8:
                        System.out.println("当前该教师的所授课程为" + this.getdataList("select * from teacher_info where id = ?",id).get(0).getLessonName()
                                + " 请输入你要修改的课程名称:");
                        String lessonname = sc.next();
                        DButils.preparesmtSQL("update teacher_info set lesson_name = ? where id = ?",lessonname,id);
                        System.out.println("修改成功！！");
                        break;
                    default:
                        System.out.println("您输入的指令有误请重新输入");
                        continue;
                }
                break;

            }else if (id == -1){
                break;
            }else{
                System.out.println("查无此人,请重新输入或者退出");
            }
        }

    }

    //删除教师信息
    public void deleteTeacherInfo(){
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要删除的教师编号");
        int id = sc.nextInt();
        List<Teacher> teacherss = getdataList("select * from teacher_info where id = ?", id);
        if (id >0 && id <= teachers.size()){
            System.out.println("要删除的教师信息如下:您确定要删除吗?  输入1确认删除 输入2取消操作");
            System.out.println("教师id:" + teacherss.get(0).getId() + " 教师姓名:" + teacherss.get(0).getName()
                    + " 教师性别:" + teacherss.get(0).getSex() + " 教师年龄:" + teacherss.get(0).getAge() + " 教师职位:" + teacherss.get(0).getPosition()
                    + " 教师所属专业:" + teacherss.get(0).getField());
            while(true){
                choice = sc.nextInt();
                if (choice == 1){
                    DButils.preparesmtSQL("delete from teacher_info where id = ?", id);
                    System.out.println("删除成功");
                    break;
                }else if (choice == 2){
                    System.out.println("已取消操作");
                    break;
                }else{
                    System.out.println("指令有误，请重新输入");
                }
            }
        }else{
            System.out.println("查无此人");
        }
    }

    //增加教师信息
    public void addTeacherInfo(){
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入老师姓名");
        String name = sc.next();
        System.out.println("请设置登录密码");
        String password = sc.next();
        System.out.println("请输入老师性别");
        String sex = sc.next();
        System.out.println("请输入老师年龄");
        String age = sc.next();
        System.out.println("请输入老师职位");
        String position = sc.next();
        System.out.println("请输入老师所属专业");
        String field = sc.next();
        System.out.println("请输入老师手机号码");
        String phone = sc.next();
        System.out.println("请输入老师的所授课程");
        String lessonName = sc.next();
        int i = DButils.preparesmtSQL("insert into teacher_info values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
        teachers.size()+1,name,password,sex,age,position,field,2,phone,lessonName);
        if (i > 0){
            System.out.println("添加成功");
        }else{
            System.out.println("添加失败");
        }
    }


    //老师登录界面设计
    public void teacherMenu(User user){
        while (true){
            System.out.println("--------------------欢迎进入学生选课系统-------------------------");
            System.out.println("-------------您当前的身份是：教师 ; 请选择您接下来的操作------------");
            System.out.println("---------------------1.查看选课学生-----------------------------");
            System.out.println("---------------------2.查看课程信息-----------------------------");
            System.out.println("---------------------3.修改密码--------------------------------");
            System.out.println("---------------------4.退出系统--------------------------------");
            choice = sc.nextInt();
            switch (choice){
                case 1:
                    showYourStudent(user);
                    break;
                case 2:
                    showYourClass(user);
                    break;
                case 3:
                    updatePassword(user);
                    break;
                case 4:
                    return;
            }
        }
    }

    //定义教师登录方法
  public Teacher teacherLogin(Teacher teacher){
        List<Teacher> teachers = getdataList("select * from teacher_info where name = ? and password = ?",teacher.getName(),teacher.getPassword());
        return teachers != null && teachers.size() > 0 ? teachers.get(0) : null;
  }

  //查看选课学生
    public void showYourStudent(User user){
        System.out.println("选择您的课程的学生有:");
        String classname = lessonDao.getdataList("select * from lesson where teacher = ?", user.getUsername()).get(0).getName();
        List<XuanxiuLesson> xuanxiuLessons = xuanXiuDao.getdataList("select * from xuanxiuclass where xuanxiu_name = ?",classname);
        if (xuanxiuLessons.size() > 0){
           for (int i = 0; i < xuanxiuLessons.size(); i++) {
               Student student = studentDao.getdataList("select * from student_info where number = ?", xuanxiuLessons.get(i).getNumber()).get(0);
               System.out.println("学生姓名:" + student.getName() + " 学生学号:" + student.getNumber() + " 学生所属专业:" + student.getAcade());
           }
        }else{
           System.out.println("还没学生选择您的课");
        }

;     }

    //查看课程信息
    public void showYourClass(User user){
        System.out.println("您所授的课程信息:");
        Lesson lesson = lessonDao.getdataList("select * from lesson where teacher = ?",user.getUsername()).get(0);
        System.out.println("课程信息:" + lesson.getName() + " 上课地点:" + lesson.getPot() + " 已选人数:" + lesson.getStuNum());
    }

    //修改密码
    public void updatePassword(User user){
        System.out.println("请输入您当前原密码:");
        while (true){
            String password = sc.next();
            if (password.equals(user.getPassword())){
                System.out.println("请输入新密码:");
                String npassword = sc.next();
                preparesmtSQL("update teacher_info set password = ?",npassword);
                System.out.println("密码修改成功！");
            }else if (password.equals("-1")){
                return;
            }else{
                System.out.println("您输入的密码有误,请重新输入");
            }
        }
    }




}
