package com.hechang.method;

import com.hechang.model.Lesson;
import com.hechang.model.Student;
import com.hechang.model.Teacher;
import com.sun.xml.internal.ws.addressing.WsaActionUtil;
import utils.DButils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class LessonDao extends DButils<Lesson> {
    int choice;
    List<Lesson> lessons = getdataList("select * from lesson");
    Scanner sc = new Scanner(System.in);

    @Override
    public Lesson getEntity(ResultSet rs) {
        Lesson lesson = new Lesson();
        try {
            lesson.setId(rs.getInt(1));
            lesson.setName(rs.getString("name"));
            lesson.setTeacher(rs.getString("teacher"));
            lesson.setPot(rs.getString("pot"));
            lesson.setStuNum(rs.getInt("stuNum"));
        } catch (SQLException ce) {
            ce.printStackTrace();
        }
        return lesson;
    }

    //根据名称查找课程信息
    public void showLessonByName(String name){
        Lesson lesson = getdataList("select * from xuanxiuclass where name = ?", name).get(0);
        lesson.toString();
    }


    //显示全部课程信息
    public void showAllLesson(){
        System.out.println("以下是全部课程信息:");
        //遍历查询
        for (int i = 0; i < lessons.size(); i++) {
            System.out.println("课程id:" + lessons.get(i).getId() + " 课程名称:" + lessons.get(i).getName() +
            " 课程讲师" + lessons.get(i).getTeacher() + " 上课地点" + lessons.get(i).getPot() + " 选课人数" + lessons.get(i).getStuNum());
        }
    }

    //修改课程信息
    public void updateLessonInfo(){
        System.out.println("请输入要修改课程的id 或输入 -1 来取消修改");
        int id = sc.nextInt();
        if (id == -1) return;
        while (true){
            if (id > 0 && id <= lessons.size()){
                System.out.println("请选择您要修改的位置:1--课程名称 2--课程讲师 3--上课地点 4--选课人数");
                System.out.println("可输入-1取消修改操作");
                int choice = sc.nextInt();
                switch (choice){
                    case -1:
                        return;
                    case 1:
                        System.out.println("当前该课程的名称为" + getdataList("select * from lesson where id = ?",id).get(0).getName() +
                                " 请输入你要修改的名称");
                        String name = sc.next();
                        DButils.preparesmtSQL("update lesson set name = ? where id = ?",name,id);
                        System.out.println("修改成功！！");
                        break;
                    case 2:
                        System.out.println("当前该课程的讲师为" + getdataList("select * from lesson where id = ?",id).get(0).getTeacher() +
                                " 请输入你要修改的讲师");
                        String teacher = sc.next();
                        DButils.preparesmtSQL("update lesson set teacher = ? where id = ?",teacher,id);
                        System.out.println("修改成功！！");
                        break;
                    case 3:
                        System.out.println("当前该课程的上课地点为" + getdataList("select * from lesson where id = ?",id).get(0).getPot() +
                                " 请输入你要修改的上课地点");
                        String pot = sc.next();
                        DButils.preparesmtSQL("update lesson set teacher = ? where id = ?",pot,id);
                        System.out.println("修改成功！！");
                        break;
                    case 4:
                        System.out.println("当前该课程的选课人数为" + getdataList("select * from lesson where id = ?",id).get(0).getStuNum() +
                                "人 请输入你要修改的选课人数");
                        String stuNum = sc.next();
                        DButils.preparesmtSQL("update lesson set teacher = ? where id = ?",stuNum,id);
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
                System.out.println("查无此课程,请重新输入或者退出");
            }
        }

    }

    //删除课程信息
    public void deleteLessonInfo(){
        System.out.println("请输入要删除的课程编号");
        int id = sc.nextInt();
        List<Lesson> lessonss = getdataList("select * from lesson where id = ?", id);
        if (id >0 && id <= lessons.size()){
            System.out.println("要删除的教师信息如下:您确定要删除吗?  输入1确认删除 输入2取消操作");
            System.out.println("课程id:" + lessonss.get(0).getId() + " 课程名称:" + lessonss.get(0).getName()
                    + " 上课地点:" + lessonss.get(0).getPot() + " 选课人数:" + lessonss.get(0).getStuNum());
            while(true){
                choice = sc.nextInt();
                if (choice == 1){
                    DButils.preparesmtSQL("delete from lesson where id = ?", id);
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
            System.out.println("查无此课程");
        }
    }

    //增加课程信息
    public void addLessonDaoInfo(){
        System.out.println("请输入课程名称");
        String name = sc.next();
        System.out.println("请输入上课讲师");
        String teacher = sc.next();
        System.out.println("请输入上课地点");
        String pot = sc.next();
        System.out.println("请输入选课人数");
        String stuNum = sc.next();
        int i = DButils.preparesmtSQL("insert into student_info values (?, ?, ?, ?, ?)",
                lessons.size()+1,name,teacher,pot,stuNum);
        if (i > 0){
            System.out.println("添加成功");
        }else{
            System.out.println("添加失败");
        }
    }

}
