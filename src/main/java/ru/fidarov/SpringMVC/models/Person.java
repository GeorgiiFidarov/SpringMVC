package ru.fidarov.SpringMVC.models;

import javax.validation.constraints.*;

public class Person {

    private int id;
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2,max = 30,message = "Name should be between 2 and 30 characters")
    private String name;
    @Min(value = 0,message = "Age should be greater than zero")
    private int age;
    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Wrong email format!")
    private String email;
    @Pattern(regexp = "[A-Z]\\w+, [A-Z]\\w+, \\d{6}", message = "Wrong Address format:Country, City, Postal Code{6 digits}")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private String address;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Person(){

    }
    public Person(int id, String name,String email,int age,String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
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


}
