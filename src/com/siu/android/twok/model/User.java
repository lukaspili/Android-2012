package com.siu.android.twok.model;

/**
 * Created with IntelliJ IDEA.
 * User: dieux
 * Date: 09/07/12
 * Time: 14:30
 * To change this template use File | Settings | File Templates.
 */
public class User {

    private String password;
    private String login;
    private String token;
    private String email;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
