package com.hechang.model;

public class Lesson{
    private int id;
    private String name;
    private String teacher;
    private String pot; //上课地点
    private int stuNum; //上课人数

    public Lesson() {
    }

    public Lesson(int id, String name, String teacher, String pot, int stuNum) {
        this.id = id;
        this.name = name;
        this.teacher = teacher;
        this.pot = pot;
        this.stuNum = stuNum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getPot() {
        return pot;
    }

    public void setPot(String pot) {
        this.pot = pot;
    }

    public int getStuNum() {
        return stuNum;
    }

    public void setStuNum(int stuNum) {
        this.stuNum = stuNum;
    }

    @Override
    public String toString() {
        return "lesson{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", teacher='" + teacher + '\'' +
                ", pot='" + pot + '\'' +
                ", stuNum=" + stuNum +
                '}';
    }
}
