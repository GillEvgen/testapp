package com.gillevgenii.testapp.model;

import java.util.Objects;

public class Employee {

    private Integer id;
    private String first_name;
    private String last_name;
    private Integer department_id;
    private String job_title;
    private String gender;
    private String date_of_birth;

    public Employee() {
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public Employee(Integer id, String first_name, String last_name,
                    Integer department_id, String job_title, String gender,
                    String date_of_birth) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.department_id = department_id;
        this.job_title = job_title;
        this.gender = gender;
        this.date_of_birth = date_of_birth;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Integer getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(Integer department_id) {
        this.department_id = department_id;
    }

    public String getJob_title() {
        return job_title;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) &&
                Objects.equals(first_name, employee.first_name) &&
                Objects.equals(last_name, employee.last_name) &&
                Objects.equals(department_id, employee.department_id) &&
                Objects.equals(job_title, employee.job_title) &&
                Objects.equals(gender, employee.gender) &&
                Objects.equals(date_of_birth, employee.date_of_birth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, first_name, last_name, department_id, job_title, gender, date_of_birth);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", department_id=" + department_id +
                ", job_title='" + job_title + '\'' +
                ", gender='" + gender + '\'' +
                ", date_of_birth=" + date_of_birth +
                '}';
    }
}
