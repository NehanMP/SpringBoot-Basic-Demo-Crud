package com.example.Application.models;

import jakarta.validation.constraints.*;

public class employeeDTO {
    @NotEmpty(message = "First Name is required")
    private String name;

    @NotEmpty(message = "Last Name is required")
    private String surname;

    @Min(value = 0, message = "Age must be a positive number")
    private int age;

    @NotEmpty(message = "Mobile number is required")
    @Pattern(regexp = "\\d{10}", message = "Mobile number should be 10 digits")
    private String phone;

    @NotEmpty(message = "E-mail is required")
    @Email(message = "E-mail should be valid")
    private String email;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
