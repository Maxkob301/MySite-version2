package com.example.buysell.models;

import java.util.ArrayList;

public class InfoOfUsers {
    private static ArrayList<User> usernames;

    public static ArrayList<User> getInfo(){
        if(usernames == null){
            usernames = new ArrayList<>();
        }
        return usernames;
    }

    private InfoOfUsers(){}


}
