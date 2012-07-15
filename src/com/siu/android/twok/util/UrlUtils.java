package com.siu.android.twok.util;

import android.util.Log;
import com.actionbarsherlock.app.SherlockActivity;
import com.siu.android.twok.R;
import com.siu.android.twok.fragment.main.ArticlesFragment1;
import com.siu.android.twok.model.User;
import com.siu.android.twok.parser.GsonFormatter;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dieux
 * Date: 29/06/12
 * Time: 17:44
 * To change this template use File | Settings | File Templates.
 */
public final class UrlUtils {

    private UrlUtils() {
    }

    public static URL getUrl(String urlAsString) {
        URL url;
        try {
            url = new URL(urlAsString);
        } catch (MalformedURLException e) {
            Log.w(UrlUtils.class.getName(), "Invalid format for url : " + urlAsString, e);
            return null;
        }

        return url;
    }

    public static String downloadData(String url) {
        Log.d(UrlUtils.class.getName(), "Connection opened to : " + url);
        long time = System.currentTimeMillis();

        HttpClient client = new DefaultHttpClient();

        try {
            HttpResponse response = client.execute(new HttpGet(url));
            InputStream in = response.getEntity().getContent();

            try {
                return IOUtils.toString(in);
            } finally {
                in.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(UrlUtils.class.getName(), "Error during downloading : " + e.getMessage());
            return null;
        } finally {
            Log.d(UrlUtils.class.getName(), "Finish in " + (System.currentTimeMillis() - time) + " ms");
        }

    }

    public static boolean post(String url) {
        Log.d(UrlUtils.class.getName(), "Connection opened to : " + url);
        long time = System.currentTimeMillis();

        HttpClient client = new DefaultHttpClient();
        HttpResponse response;

        HttpPost post = new HttpPost(url);

        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("value", "want"));

        try {
            post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            response = client.execute(post);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(UrlUtils.class.getName(), "Error during downloading : " + e.getMessage());
            return false;
        } finally {
            Log.d(UrlUtils.class.getName(), "Finish in " + (System.currentTimeMillis() - time) + " ms");
        }

        if (response.getStatusLine().getStatusCode() == 200) {
            return true;
        }

        return false;
    }
}