package com.hechang;

import com.hechang.method.LessonDao;
import com.hechang.method.StudentDao;
import com.hechang.method.XuanXiuDao;
import com.hechang.model.Lesson;
import com.hechang.model.Student;
import com.hechang.model.User;

public class MothidTest {
    public static void main(String[] args) {

       LessonDao lessonDao = new LessonDao();
       lessonDao.showLessonByName("母猪产后护理");

    }

}
