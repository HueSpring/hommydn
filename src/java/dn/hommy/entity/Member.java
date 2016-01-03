package dn.hommy.entity;

import java.util.Date;

public class Member {
    
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String city;
    private String avatar;
    private String gender;
    private String phone;
    private String email;
    private Date time_create_acc;
    private int role_login;

    public Member() {
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public Date getTime_create_acc() {
        return time_create_acc;
    }

    public void setTime_create_acc(Date time_create_acc) {
        this.time_create_acc = time_create_acc;
    }

    public int getRole_login() {
        return role_login;
    }

    public void setRole_login(int role_login) {
        this.role_login = role_login;
    }
    
}
