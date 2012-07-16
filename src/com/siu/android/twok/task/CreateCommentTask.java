package com.siu.android.twok.task;

import android.os.AsyncTask;
import android.util.Log;
import com.siu.android.andutils.Application;
import com.siu.android.andutils.util.HttpUtils;
import com.siu.android.twok.DataAccessLayer;
import com.siu.android.twok.R;
import com.siu.android.twok.fragment.formulaire.NewCommentDialogFragment;
import com.siu.android.twok.model.Idea;
import com.siu.android.twok.model.User;
import com.siu.android.twok.parser.GsonFormatter;
import com.siu.android.twok.task.mother.NewIdeaTaskCallback;
import com.siu.android.twok.util.NetworkUtils;

/**
 * Created with IntelliJ IDEA.
 * User: dieux
 * Date: 10/07/12
 * Time: 13:18
 * To change this template use File | Settings | File Templates.
 */
public class CreateCommentTask extends AsyncTask<Void, String, Idea> {


        private NewCommentDialogFragment fragment;
        private String titre;
        private String description;
        private String idNumber;

        public CreateCommentTask(NewCommentDialogFragment fragment,  String description, String idNumber) {
        this.fragment = fragment;
        this.idNumber= idNumber;
        this.description = description;
    }

    public void setFragment(NewCommentDialogFragment fragment) {
        this.fragment = fragment;
    }

    @Override
    protected Idea doInBackground(Void... unused) {
        if (!NetworkUtils.isOnline()) {
            return null;
        }
        User user = DataAccessLayer.getInstance().getUser();

        String url = Application.getContext().getString(R.string.url_base) + Application.getContext().getString(R.string.url_idea_opinion, idNumber);
        Log.e( getClass().getName(), "création du commentaire à : " + url);
        String response = HttpUtils.getResponseAsString(HttpUtils.request(url, HttpUtils.HttpMethod.POST, "access_token", user.getToken() ,
                "idea[category_ids][]", "873570203659140", "comment[name]", titre, "comment[content]", description));

        Log.e(getClass().getName(), "Liste de commentaire 'la reponse': "+ response);

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