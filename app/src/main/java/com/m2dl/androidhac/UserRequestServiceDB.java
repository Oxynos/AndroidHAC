package com.m2dl.androidhac;

/**
 * Created by manantsoa on 21/01/16.
 */
public class UserRequestServiceDB {

    public static String createUser(User user) {
        return "INSERT INTO USERS (PSEUDO) "
                + "VALUES ('" + user.getPseudo() + "');";
    }

    public static String deleteUser(User user) {
        return "DELETE from USERS where PSEUDO=" + user.getPseudo() +";";
    }

    public static String deleteAllUser() {
        return "DELETE from USERS;";
    }

}
