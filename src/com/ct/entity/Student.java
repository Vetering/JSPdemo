package com.ct.entity;

/**
 * 功能
 * 作者:Veter
 * 日期: 2023/12/4 21:09
 **/
public class Student {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private String StudentID;
    private String sex;
    private String _faculty;
    private String address;
    private Integer Age;
    private String _class;
    private String AdmissionTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentID() {
        return StudentID;
    }

    public void setStudentID(String studentID) {
        StudentID = studentID;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String get_faculty() {
        return _faculty;
    }

    public void set_faculty(String _faculty) {
        this._faculty = _faculty;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAge() {
        return Age;
    }

    public void setAge(Integer age) {
        Age = age;
    }

    public String get_class() {
        return _class;
    }

    public void set_class(String _class) {
        this._class = _class;
    }

    public String getAdmissionTime() {
        return AdmissionTime;
    }

    public void setAdmissionTime(String admissionTime) {
        AdmissionTime = admissionTime;
    }
}