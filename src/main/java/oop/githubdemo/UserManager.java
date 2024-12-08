package oop.githubdemo;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private static final List<User> userList = new ArrayList<>();
    private static User loggedInUser = null;

    static {
//        userList.add(new User("asif", "1234", LocalDate.now().minusYears(30)));
//        userList.add(new User("test", "test", LocalDate.now().minusYears(20).minusMonths(2)));
//        userList.add(new User("admin", "0987", LocalDate.now().minusYears(50).minusMonths(6).minusDays(20)));
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("user.bin"));
            while (true) {
                User u = (User) ois.readObject();
                userList.add(u);
                System.out.println("Added User: " + u.toString());
            }
        }catch (Exception e) {
//            e.printStackTrace();
        }
        finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

    public static List<User> getUsers() {
        return userList;
    }

    public static User checkLogIn(String username, String password) throws IOException {
        ObjectInputStream ois = null;
        User u = null;

        try {
            ois = new ObjectInputStream(new FileInputStream("user.bin"));
            while (true) {
                u = (User) ois.readObject();
                if (u.getUsername().equals(username) && u.getPassword().equals(password))
                    break;
            }
        }
        catch (ClassNotFoundException e) {
            System.out.println("Invalid user file format!");
        }
        catch (IOException e){
            return null;
        }
        finally {
            if (ois != null)
                ois.close();
        }
        return u;
    }

    public static void deleteUser(User user) {
        userList.remove(user);
        System.out.println("User deleted: " + user.getUsername());
        System.out.println("Current number of registered users: " + userList.size());
        System.out.println();
    }

    public static void addUser(User user) {
        userList.add(user);
        System.out.println("User added: " + user.getUsername());
        System.out.println("Current number of registered users: " + userList.size());
        System.out.println();
    }

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(User loggedInUser) {
        UserManager.loggedInUser = loggedInUser;
    }
}
