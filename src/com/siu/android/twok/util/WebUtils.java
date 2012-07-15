package com.siu.android.twok.util;

import android.util.Log;

import java.net.URLEncoder;

/**
 * Created with IntelliJ IDEA.
 * User: dieux
 * Date: 29/06/12
 * Time: 17:45
 * To change this template use File | Settings | File Templates.
 */
public class WebUtils {

    public static final String UTF8 = "utf-8";
    public static final String HTML = "text/html";

    public static String encodeHtml(String content) {
        try {
            return URLEncoder.encode(content, UTF8).replaceAll("\\+", " ");
        } catch (Exception e) {
            Log.e(WebUtils.class.getName(), "Error encoding html in UTF8 : " + e.getMessage());
            return content;
        }
    }

    public static String wrapWithHtml(String content) {
        return "<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" /></head><body>" +
                content +
                "</body></html>";
    }
}