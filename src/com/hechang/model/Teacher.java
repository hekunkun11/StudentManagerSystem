package com.hechang.model;

public class Teacher extends Account{
    private String password;    //密码
    private String position;    //职位
    private String field;   //所属专业
    private int level;      //管理权限
    private String phone;   //电话号码
    private String lessonName; //所授课程

    public Teacher() {
    }

    public Teacher(long id, String name, String age, String sex) {
        super(id, name, age, sex);
    }

    public Teacher(String password, String position, String field, int level, String phone, String lessonName) {
        this.password = password;
        this.position = position;
        this.field = field;
        this.level = level;
        this.phone = phone;
        this.lessonName = lessonName;
    }

    public Teacher(long id, String name, String age, String sex, String password, String position, String field, int level, String phone, String lessonName) {
        super(id, name, age, sex);
        this.password = password;
        this.position = position;
        this.field = field;
        this.level = level;
        this.phone = phone;
        this.lessonName = lessonName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    @Override
    public String toString() {
        return super.toString() + "Teacher{" +
                "password='" + password + '\'' +
                ", position='" + position + '\'' +
                ", field='" + field + '\'' +
                ", level=" + level +
                ", phone='" + phone + '\'' +
                ", lessonName='" + lessonName + '\'' +
                '}';
    }
}
