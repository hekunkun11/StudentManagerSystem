package com.hechang.model;

public class Student extends Account{
    private String password; //密码
    private String number;  //学号
    private String acade;   //所属学院
    private int level;      //权限等级

    public Student() {
    }

    public Student(long id, String name,  String age, String sex) {
        super(id, name, age, sex);
    }

    public Student(String number, String acade, int level) {
        this.number = number;
        this.acade = acade;
        this.level = level;
    }

    public Student(long id, String name, String age, String sex, String number, String acade, int level) {
        super(id, name, age, sex);
        this.number = number;
        this.acade = acade;
        this.level = level;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAcade() {
        return acade;
    }

    public void setAcade(String adade) {
        this.acade = adade;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return super.toString() + "Student{" +
                "number='" + number + '\'' +
                ", adade='" + acade + '\'' +
                ", level=" + level +
                '}';
    }
}
