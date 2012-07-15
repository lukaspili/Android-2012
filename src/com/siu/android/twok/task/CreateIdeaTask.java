package com.siu.android.twok.task;

import android.os.AsyncTask;
import com.siu.android.andutils.Application;
import com.siu.android.andutils.util.HttpUtils;
import com.siu.android.twok.DataAccessLayer;
import com.siu.android.twok.model.Idea;
import com.siu.android.twok.parser.GsonFormatter;
import com.siu.android.twok.task.mother.NewIdeaTaskCallback;
import com.siu.android.twok.util.NetworkUtils;


import android.util.Log;
import com.siu.android.twok.R;
import com.siu.android.twok.model.User;

/**
 * Created with IntelliJ IDEA.
 * User: dieux
 * Date: 04/07/12
 * Time: 12:11
 * To change this template use File | Settings | File Templates.
 */
public class CreateIdeaTask extends AsyncTask<Void, String, Idea> {
    private NewIdeaTaskCallback fragment;
    private String titre;
    private String description;

    public CreateIdeaTask(NewIdeaTaskCallback fragment, String titre, String description) {
        this.fragment = fragment;
        this.titre = titre;
        this.description = description;
    }

    public void setFragment(NewIdeaTaskCallback fragment) {
        this.fragment = fragment;
    }

    @Override
    protected Idea doInBackground(Void... unused) {
        if (!NetworkUtils.isOnline()) {
            return null;
        }
        User user = DataAccessLayer.getInstance().getUser();
        if (null == user) {
            Log.e(getClass().getName(), "User null");
        }

        String url = Application.getContext().getString(R.string.url_base) + Application.getContext().getString(R.string.url_ideas_create);
        Log.e( getClass().getName(), "création d'idée à l'addresse : " + url);
        String response = HttpUtils.getResponseAsString(HttpUtils.request(url, HttpUtils.HttpMethod.POST, "access_token", user.getToken(),
                "idea[category_ids][]", "873570203659140", "idea[name]", titre, "idea[content]", description));

        Log.e("-siu---------------------------", "reponse :"+ response);

        try {

            return GsonFormatter.getGson().fromJson(response, Idea.class);
        } catch (Exception e) {
            Log.e(getClass().getName(), "Error in parsing", e);
            return null;
        }
    }

    @Override
    protected void onPostExecute(Idea idea) {
        if (null != fragment)
            fragment.onIdeaTaskFinished();
    }


}