package com.siu.android.twok;

import com.siu.android.twok.model.User;

/**
 * Created with IntelliJ IDEA.
 * User: dieux
 * Date: 09/07/12
 * Time: 16:54
 * To change this template use File | Settings | File Templates.
 */
public class DataAccessLayer {

    private static DataAccessLayer instance;

    private User user;

    private DataAccessLayer(){
    }

    public static DataAccessLayer getInstance(){
        if(null == instance){
            instance = new DataAccessLayer();
        }
        return instance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
