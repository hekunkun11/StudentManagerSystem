package com.hechang.model;

public class XuanxiuLesson {
    private String class_number;
    private String name;

    public XuanxiuLesson() {
    }

    public XuanxiuLesson(String number, String name) {
        this.class_number = number;
        this.name = name;
    }

    public String getNumber() {
        return class_number;
    }

    public void setNumber(String number) {
        this.class_number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
