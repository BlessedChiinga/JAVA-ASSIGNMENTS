package com.example;

public class Student {

    private String studentId;
    private String fullName;
    private String gender;
    private String dateOfBirth;
    private String programme;
    private String year;
    private String email;
    private String phone;

    public Student(String studentId, String fullName, String gender,
                   String dateOfBirth, String programme, String year,
                   String email, String phone) {

        this.studentId = studentId;
        this.fullName = fullName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.programme = programme;
        this.year = year;
        this.email = email;
        this.phone = phone;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getGender() {
        return gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getProgramme() {
        return programme;
    }

    public String getYear() {
        return year;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
}