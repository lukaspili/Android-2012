package com.siu.android.twok.task;

import android.os.AsyncTask;
import android.util.Log;
import com.siu.android.andutils.Application;
import com.siu.android.andutils.util.HttpUtils;
import com.siu.android.twok.DataAccessLayer;
import com.siu.android.twok.R;
import com.siu.android.twok.activity.DetailIdeaActivity;
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
public class LoginUserTask extends AsyncTask<Void, Void, User> {
    private LoginTaskCallback callbackFormulaire;
    private DetailIdeaActivity callbackDetail;

    private String login;
    private String password;


    public LoginUserTask(LoginTaskCallback callback, String login, String password) {
        this.callbackFormulaire = callback;
        this.login = login;
        this.password = password;
    }

    public LoginUserTask(DetailIdeaActivity callbackDetail, String login, String password) {
        this.callbackDetail = callbackDetail;
        this.login = login;
        this.password = password;
    }

    public void setCallbackFormulaire(LoginTaskCallback callbackFormulaire) {
        this.callbackFormulaire = callbackFormulaire;
    }

    @Override
    protected User doInBackground(Void... aVoid) {
        if (!NetworkUtils.isOnline()) {
            return null;
        }

        String url = Application.getContext().getString(R.string.url_base) + Application.getContext().getString(R.string.url_users_login);
        String response = HttpUtils.getResponseAsString(HttpUtils.request(url, HttpUtils.HttpMethod.POST,
                "user[name]", login, "user[password]", password));

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

        if (null != callbackFormulaire)
            callbackFormulaire.onLoginTaskFinished(user);
        else
            Log.d(getClass().getName(), " Fragment null (LoginUserTask)");

        if (null != callbackDetail)
            callbackDetail.onLoginTaskFinished(user);
        else
            Log.d(getClass().getName(), " Fragment null (LoginUserTask)");
    }
}