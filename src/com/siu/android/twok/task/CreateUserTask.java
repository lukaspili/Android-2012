package com.siu.android.twok.task;

import android.os.AsyncTask;
import android.util.Log;
import com.siu.android.andutils.Application;
import com.siu.android.andutils.util.HttpUtils;
import com.siu.android.twok.DataAccessLayer;
import com.siu.android.twok.R;
import com.siu.android.twok.model.User;
import com.siu.android.twok.parser.GsonFormatter;
import com.siu.android.twok.task.mother.LoginTaskCallback;
import com.siu.android.twok.util.NetworkUtils;

/**
 * Created with IntelliJ IDEA.
 * User: dieux
 * Date: 04/07/12
 * Time: 12:11
 * To change this template use File | Settings | File Templates.
 */
public class CreateUserTask extends AsyncTask<Void, String, User> {
    private LoginTaskCallback callback;
    private String login;
    private String password;
    private String mail;

    public CreateUserTask(LoginTaskCallback callback, String login, String password, String mail) {
        this.callback = callback;
        this.login = login;
        this.password = password;
        this.mail = mail;
    }

    public void setCallback(LoginTaskCallback callback) {
        this.callback = callback;
    }

    @Override
    protected User doInBackground(Void... aVoid) {
        if (!NetworkUtils.isOnline()) {
            return null;
        }

        String url = Application.getContext().getString(R.string.url_base) + Application.getContext().getString(R.string.url_users_create);
        String response = HttpUtils.getResponseAsString(HttpUtils.request(url, HttpUtils.HttpMethod.POST,
                "user[name]", login, "user[password]", password, "user[email]", mail));


        Log.d(getClass().getName(), "tokken  :" + response);

        try {
            return GsonFormatter.getGson().fromJson(response, User.class);
        } catch (Exception e) {
            Log.e(getClass().getName(), "Error in parsing", e);
            return null;
        }
    }

    @Override
    protected void onPostExecute(User user) {
        DataAccessLayer.getInstance().setUser(user);// ajout pierre

        if (null != callback)
            callback.onLoginTaskFinished(user);
        else
            Log.d(getClass().getName(), " Fragment null (CreatUserTask)");
    }
}