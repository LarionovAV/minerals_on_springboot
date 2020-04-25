package com.minerals.minerals_on_springboot.Config;

public class Authentication {
    private String pwd = "AdminPWD";
    private boolean isAuth  = false;
    private static Authentication auth = null;


    private Authentication(){}

    public static Authentication getAuth(){
        if (auth == null)
            auth = new Authentication();

        return auth;
    }

    public boolean checkAuth(String insertedPWD){
        return (isAuth = insertedPWD.equals(pwd));
    }

    public boolean isAuth() {
        return isAuth;
    }

    public void setAuth(boolean auth) {
        isAuth = auth;
    }
}
