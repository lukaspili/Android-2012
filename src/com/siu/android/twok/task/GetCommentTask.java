package com.siu.android.twok.task;

import android.os.AsyncTask;
import android.util.Log;
import com.google.gson.reflect.TypeToken;
import com.siu.android.twok.activity.DetailIdeaActivity;
import com.siu.android.twok.model.Comment;
import com.siu.android.twok.model.Idea;
import com.siu.android.twok.parser.GsonFormatter;
import com.siu.android.twok.util.NetworkUtils;
import com.siu.android.twok.util.UrlUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dieux
 * Date: 15/07/12
 * Time: 18:41
 * To change this template use File | Settings | File Templates.
 */
public class GetCommentTask extends AsyncTask<Void, String, List<Comment>> {

    private DetailIdeaActivity fragment;
    private String url;

    public GetCommentTask(DetailIdeaActivity fragment) {
        this.fragment = fragment;
    }

    public GetCommentTask(String url, DetailIdeaActivity fragment) {
        this.url = url;
        this.fragment = fragment;
    }

    public void setFragment(DetailIdeaActivity articlesFragment) {
        fragment = articlesFragment;
    }

    @Override
    protected List<Comment> doInBackground(Void... aVoid) {
        // if no network available, then stop
        if (!NetworkUtils.isOnline()) {
            return null;
        }

        String data = UrlUtils.downloadData(url);

        if (StringUtils.isEmpty(data)) {
            return null;
        }
        Log.d(getClass().getName(), "response pour le comment: "+ data);

        return GsonFormatter.getGson().fromJson(data, new TypeToken<List<Idea>>() {
        }.getType());
    }

    @Override
    protected void onPostExecute(List<Comment> comment) {
        if (null != fragment) {
           // fragment.onFinishGetCommentTask(comment);
        }
    }  
    
}
