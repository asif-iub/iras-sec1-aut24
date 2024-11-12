package oop.githubdemo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserManager {
    List<User> getUsers() {
        List<User> userList = new ArrayList<>();
        userList.add(new User("asif", "1234", LocalDate.now().minusYears(30)));
        userList.add(new User("test", "test", LocalDate.now().minusYears(20).minusMonths(2)));
        userList.add(new User("admin", "0987", LocalDate.now().minusYears(50).minusMonths(6).minusDays(20)));

        return userList;
    }
}
