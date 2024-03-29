package ohtu.services;

import ohtu.domain.User;
import java.util.ArrayList;
import java.util.List;
import ohtu.data_access.UserDao;

public class AuthenticationService {

    private UserDao userDao;

    public AuthenticationService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean logIn(String username, String password) {
        for (User user : userDao.listAll()) {
            if (user.getUsername().equals(username)
                    && user.getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    }

    public boolean createUser(String username, String password) {
        if (userDao.findByName(username) != null) {
            return false;
        }

        if (invalid(username, password)) {
            return false;
        }

        userDao.add(new User(username, password));

        return true;
    }

    private boolean invalid(String username, String password) {
        // validity check of username and password
        if (username.length() < 3) {
            return true;
        }

        //check that all characters are in the range [a-z]
        for (int i = 0; i < username.length(); i++) {
            int charval = username.charAt(i);
            if (charval < 97 || charval > 122) {
                return true;
            }
        }

        if (password.length() < 8) {
            return true;
        }

        //check that password contains characters outside of [aA-zZ]
        for (int i = 0; i < password.length(); i++) {
            int charval = password.charAt(i);
            if (charval < 65 || charval > 122) {
                return false;
            }
            //char is in the range [65-122]
            if (charval > 90 && charval < 97) {
                return false;
            }
        }

        return true;
    }
}
