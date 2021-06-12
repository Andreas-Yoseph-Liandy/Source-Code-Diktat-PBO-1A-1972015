package com.andreas.AdvancedClass;

public class Employee {
    protected String firstname;
    protected String lastname;
    protected final String gender;

    public Employee(){

    }

    public Employee(String firstname, String lastname, String gender) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getGender() {
        return gender;
    }
}
