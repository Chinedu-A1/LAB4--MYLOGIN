package ca.sait.lab4login.services;

import ca.sait.lab4login.models.User;

/**
 *
 * @author Chinedu Alake
 */
public class AccountService {

     public User login(String username, String password) {
     if ((username.equals("abe") || username.equals("barb")) && password.equals("password")) {
         return new User(username, null);
     } else {
         return null;
     }
  }
}



