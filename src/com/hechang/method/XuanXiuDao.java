package com.hechang.method;

import com.hechang.model.Admin;
import com.hechang.model.Student;
import com.hechang.model.User;
import com.hechang.model.XuanxiuLesson;
import utils.DButils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class XuanXiuDao extends DButils<XuanxiuLesson> {
    @Override
    public XuanxiuLesson getEntity(ResultSet rs) {
        XuanxiuLesson xuanxiuLesson = new XuanxiuLesson();
        try{
         xuanxiuLesson.setNumber(rs.getString("class_number"));
          xuanxiuLesson.setName(rs.getString("xuanxiu_name"));
        }catch (SQLException ce){
            ce.printStackTrace();
        }
        return xuanxiuLesson;
    }

    //定义判断学生是否选过课
    public XuanxiuLesson judgeChoose(User user){
        List<XuanxiuLesson> xuanxiuLessons = getdataList("select * from xuanxiuclass where  class_number = ?",user.getPassword());
        return xuanxiuLessons != null && xuanxiuLessons.size() > 0 ? xuanxiuLessons.get(0) : null;
    }
}
