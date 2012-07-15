package com.siu.android.twok.parser;

/**
 * Created with IntelliJ IDEA.
 * User: dieux
 * Date: 23/05/12
 * Time: 15:22
 * To change this template use File | Settings | File Templates.
 */

import android.util.Log;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Lukasz Piliszczuk <lukasz.pili AT gmail.com>
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
}

