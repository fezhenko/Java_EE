package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println(getUser("name", "password"));
    }
    public static AppUser getUser(String name, String password) {
        List<AppUser> users = new ArrayList<>();
        users.add(new AppUser(1L,"name","role","password"));
        users.add(new AppUser(2L,"Data Communications and Networking","Forouzan","Mc Graw Hill"));
        users.add(new AppUser(3L,"Operating System","Galvin","Wiley"));
        return users.stream()
                .filter(user -> user.getName().equals(name) && user.getPassword().equals(password))
                .findFirst()
                .orElse(null);

    }
}
