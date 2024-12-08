package oop.githubdemo;

import java.io.Serializable;
import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.YEARS;

public class User implements Serializable {
    String username, password;
    LocalDate dob;

    public User(String username, String password, LocalDate dob) {
        this.username = username;
        this.password = password;
        this.dob = dob;
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

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", dob=" + dob +
                '}';
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public int getAge() {
        return (int) this.dob.until(LocalDate.now(),YEARS);
    }
}
