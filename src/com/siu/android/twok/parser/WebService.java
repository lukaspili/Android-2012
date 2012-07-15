package com.siu.android.twok.parser;

import android.util.Log;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: dieux
 * Date: 23/05/12
 * Time: 13:25
 * To change this template use File | Settings | File Templates.
 */
public class WebService {
                      /*
    public List<Idea> load() {
        String data = getData();
        Log.d(getClass().getName(), "DOWNLOADED = " + data);
        return ideas;
    }                   */

    private String getData(String urlAsString) {
        URL url = UrlUtils.getUrl(urlAsString);

        if (null == url) {
            Log.e(getClass().getName(), "Invalid URL : " + urlAsString);
            return null;
        }
        InputStream is = null;
        String data;
        try {
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setUseCaches(false);
            urlConnection.setConnectTimeout(15 * 1000);
            Log.d(getClass().getName(), "Connection opened to : " + urlAsString);
            long time = System.currentTimeMillis();
            urlConnection.connect();
            is = urlConnection.getInputStream();
            data = IOUtils.toString(is);
            Log.d(getClass().getName(), "Content downloaded in " + (System.currentTimeMillis() - time) + " ms");
        } catch (IOException e) {
            Log.e(getClass().getName(), "Error during reading connection stream : " + e.getMessage());
            return null;
        } finally {
            IOUtils.closeQuietly(is);
        }
        return data;
    }
}




