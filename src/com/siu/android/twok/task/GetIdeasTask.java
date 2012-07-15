package com.siu.android.twok.task;

import android.os.AsyncTask;
import android.util.Log;
import com.google.gson.reflect.TypeToken;
import com.siu.android.twok.R;
import com.siu.android.twok.fragment.main.ArticlesFragment1;
import com.siu.android.twok.model.Idea;
import com.siu.android.twok.model.User;
import com.siu.android.twok.parser.GsonFormatter;
import com.siu.android.twok.util.NetworkUtils;
import com.siu.android.twok.util.UrlUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dieux
 * Date: 04/07/12
 * Time: 12:11
 * To change this template use File | Settings | File Templates.
 */
public class GetIdeasTask extends AsyncTask<Void, String, List<Idea>> {
    private ArticlesFragment1 fragment;
    private String url;

    public GetIdeasTask(ArticlesFragment1 fragment) {
        this.fragment = fragment;
    }

    public GetIdeasTask(String url, ArticlesFragment1 fragment) {
        this.url = url;
        this.fragment = fragment;
    }

    public void setFragment(ArticlesFragment1 articlesFragment) {
        fragment = articlesFragment;
    }

    @Override
    protected List<Idea> doInBackground(Void... aVoid) {
        // if no network available, then stop
        if (!NetworkUtils.isOnline()) {
            return null;
        }

        String data = UrlUtils.downloadData(url);

        if (StringUtils.isEmpty(data)) {
            return null;
        }

        return GsonFormatter.getGson().fromJson(data, new TypeToken<List<Idea>>() {
        }.getType());
    }

    @Override
    protected void onPostExecute(List<Idea> ideas) {
        if (null != fragment) {
            fragment.onFinishGetIdeasTask(ideas);
        }
    }
}