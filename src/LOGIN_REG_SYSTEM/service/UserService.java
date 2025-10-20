package LOGIN_REG_SYSTEM.service;

import LOGIN_REG_SYSTEM.model.User;

import java.util.ArrayList;

public class UserService {

    private ArrayList<User> userArrayList = new ArrayList<>();
    private User LoggedInUser = null;




    //register
    public boolean register(String username, String password){
        for(User user: userArrayList){
            if(user.getUsername().equals(username)){
                return false; // may ka name na username
            }
        }

        userArrayList.add(new User(username,password));
        return true;
    }

    //login
    public boolean login(String username, String password){
        for(User user: userArrayList){
            if(user.getUsername().equals(username) && user.getPassword().equals(password)){
                LoggedInUser = user;//dito papasok ung user ang mag hohold
                return true;//kung ung credential ay parehas
            }
        }
        return false; //walang tugma sa username at password
    }

    //logout
    public void loggedOut(){
         LoggedInUser = null;//ibig sabihin wala ng naka login
    }

    //may naka login ba?
    public boolean isLoggedIn(){
        return LoggedInUser != null; // may user na naka log-in
    }

    public String getCurrentUserName(){
        return LoggedInUser != null ? LoggedInUser.getUsername() : "none";
    }




}
