package com.hechang.method;

import com.hechang.model.*;
import utils.DButils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class StudentDao extends DButils<Student> {
    int choice;
    List<Student> students = getdataList("select * from student_info");
    Scanner sc = new Scanner(System.in);
    LessonDao lessonDao = new LessonDao();
    XuanXiuDao xuanXiuDao = new XuanXiuDao();
    @Override
    public Student getEntity(ResultSet rs) {
        Student student = new Student();
        try{
            student.setId(rs.getInt(1));
            student.setName(rs.getString("name"));
            student.setPassword(rs.getString("password"));
            student.setSex(rs.getString("sex"));
            student.setAge(rs.getString("age"));
            student.setNumber(rs.getString("number"));
            student.setAcade(rs.getString("acade"));
            student.setLevel(rs.getInt("level"));
        }catch (SQLException ce){
            ce.printStackTrace();
        }

        return student;

    }

    //学生登录界面
    public void studentMenu(User user){
        while (true){
            System.out.println("--------------------欢迎进入学生选课系统-------------------------");
            System.out.println("-------------您当前的身份是：学生 ; 请选择您接下来的操作------------");
            System.out.println("---------------------1.查看选课信息-----------------------------");
            System.out.println("---------------------2.学生选课-----------------------------");
            System.out.println("---------------------3.退出系统--------------------------------");
            choice = sc.nextInt();
            switch (choice){
                case 1:
                    showXuanxiuClass(user);
                    break;
                case 2:
                    studentChooseLesson(user);
                    break;
                case 3:
                    break;
            }
        }
    }
    //定义学生登录方法
    public Student studentLogin(Student student){
        List<Student> students = getdataList("select * from student_info where name = ? and number = ?",student.getName(),student.getNumber());
        return student != null && students.size() > 0 ? students.get(0) : null;
    }

    //定义查看选课情况
    public void showXuanxiuClass(User user){
        if(xuanXiuDao.judgeChoose(user) != null){
            String number = user.getPassword();
            String name = xuanXiuDao.getdataList("select * from xuanxiuclass where class_number = ?",number).get(0).getName();
            Lesson lesson = lessonDao.getdataList("select * from lesson where name = ?", name).get(0);
            System.out.println("课程名称:" + lesson.getName() + " 课程讲师:" + lesson.getTeacher() + " 上课地点:" + lesson.getPot() + " 已选人数:" + lesson.getStuNum() + "人");
        }else{
            System.out.println("您还未选课,请先去选课");
        }
//        System.out.println("您的选课为:");
//        String number = user.getPassword();
//        String name = xuanXiuDao.getdataList("select xuanxiu_name from xuanxiuclass where class_number = ? ", number).get(0).getName();
//        Lesson lesson = lessonDao.getdataList("select * from lesson where name = ?", name).get(0);
//        System.out.println("课程名称:" + lesson.getName() + " 课程讲师:" + lesson.getTeacher() + " 上课地点:" + lesson.getPot() + "已选人数: " + lesson.getStuNum());
    }

    //定义学生选课方法
    public void studentChooseLesson(User user){
        if (xuanXiuDao.judgeChoose(user) != null){
            System.out.println("您已经选过课了，请退课后重新选择");
            return;
        }
        //选课 学生需要看到课程表 四门必修课 两门选修课
        System.out.println("以下是选修课课程表");
        lessonDao.showAllLesson();
        while(true){
            System.out.println("输入课程id来选择课程");
            int id = sc.nextInt();
            if (id > 0 && id <= lessonDao.getdataList("select * from lesson").size()){
                String name = lessonDao.getdataList("select * from lesson where id = ?", id).get(0).getName();
                preparesmtSQL("insert into xuanxiuclass values (?, ?)", user.getPassword(), name);
                preparesmtSQL("update lesson set stuNum = (stuNum + 1)");
                System.out.println("选课成功");
                break;
            }else{
                System.out.println("您输入的指令有误，请重新输入");
            }
        }
    }


    //显示全部学生信息
    public void showAllStudents(){
        System.out.println("以下是全部学生信息:");
        //遍历查询
        for (int i = 0; i < students.size(); i++) {
            System.out.println("学生id:" + students.get(i).getId() + " 学生姓名:" + students.get(i).getName()
                    + " 学生性别:" + students.get(i).getSex() + " 学社年龄:" + students.get(i).getAge() +
                    " 学生学号:" + students.get(i).getNumber() + " 学生所属学院:"+ students.get(i).getAcade());
        }
    }

    //修改学生信息
    public void updateStudentInfo(){
        System.out.println("请输入要修改学生的id 或输入 -1 来取消修改");
        int id = sc.nextInt();
        if (id == -1) return;
        while (true){
            if (id > 0 && id <= students.size()){
                System.out.println("请选择您要修改的位置:1--姓名 2--性别 3--年龄 4--学号 5--所属学院");
                System.out.println("可输入-1取消修改操作");
                int choice = sc.nextInt();
                switch (choice){
                    case -1:
                        return;
                    case 1:
                        System.out.println("当前该学生的姓名为" + this.getdataList("select * from student_info where id = ?",id).get(0).getName()
                                + " 请输入你要修改的姓名:");
                        String name = sc.next();
                        DButils.preparesmtSQL("update student_info set name = ? where id = ?",name,id);
                        System.out.println("修改成功！！");
                        break;
                    case 2:
                        System.out.println("当前该学生的性别为" + this.getdataList("select * from student_info where id = ?",id).get(0).getSex()
                                + " 请输入你要修改的性别:");
                        String sex = sc.next();
                        DButils.preparesmtSQL("update student_info set sex = ? where id = ?",sex,id);
                        System.out.println("修改成功！！");
                        break;
                    case 3:
                        System.out.println("当前该学生的年龄为" + this.getdataList("select * from student_info where id = ?",id).get(0).getAge()
                                + " 请输入你要修改的年龄:");
                        String age = sc.next();
                        DButils.preparesmtSQL("update student_info set age = ? where id = ?",age,id);
                        System.out.println("修改成功！！");
                        break;
                    case 4:
                        System.out.println("当前该学生的学号为" + this.getdataList("select * from student_info where id = ?",id).get(0).getNumber()
                                + " 请输入你要修改的学号:");
                        String number = sc.next();
                        DButils.preparesmtSQL("update student_info set number = ? where id = ?",number,id);
                        System.out.println("修改成功！！");
                        break;
                    case 5:
                        System.out.println("当前该学生的所属学院为" + this.getdataList("select * from student_info where id = ?",id).get(0).getAcade()
                                + " 请输入你要修改的学院:");
                        String acade = sc.next();
                        DButils.preparesmtSQL("update student_info set acade = ? where id = ?",acade,id);
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

    //删除学生信息
    public void deleteStudentInfo(){
        System.out.println("请输入要删除的学生编号");
        int id = sc.nextInt();
        List<Student> studentss = getdataList("select * from student_info where id = ?", id);
        if (id >0 && id <= students.size()){
            System.out.println("要删除的教师信息如下:您确定要删除吗?  输入1确认删除 输入2取消操作");
            System.out.println("学生id:" + studentss.get(0).getId() + " 学生姓名:" + studentss.get(0).getName()
                    + " 学生性别:" + studentss.get(0).getSex() + " 学社年龄:" + studentss.get(0).getAge() +
                    " 学生学号:" + studentss.get(0).getNumber() + " 学生所属学院:"+ studentss.get(0).getAcade());
            while(true){
                choice = sc.nextInt();
                if (choice == 1){
                    DButils.preparesmtSQL("delete from student_info where id = ?", id);
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

    //增加学生信息
    public void addStudentInfo(){
        System.out.println("请输入学生姓名");
        String name = sc.next();
        System.out.println("请设置登录密码");
        String password = sc.next();
        System.out.println("请输入学生性别");
        String sex = sc.next();
        System.out.println("请输入学生年龄");
        String age = sc.next();
        System.out.println("请输入学生学号");
        String position = sc.next();
        System.out.println("请输入学生所属学院");
        String field = sc.next();
        int i = DButils.preparesmtSQL("insert into student_info values (?, ?, ?, ?, ?, ?, ?, ?)",
                students.size()+1,name,password,sex,age,position,field,3);
        if (i > 0){
            System.out.println("添加成功");
        }else{
            System.out.println("添加失败");
        }
    }
}

