package com.siu.android.twok.parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.siu.android.twok.model.Comment;
import com.siu.android.twok.model.Idea;
import com.siu.android.twok.model.User;

/**
 * Created with IntelliJ IDEA.
 * User: dieux
 * Date: 23/05/12
 * Time: 11:47
 * To change this template use File | Settings | File Templates.
 */
public class GsonFormatter {

    private static Gson gson = new GsonBuilder()
            .registerTypeAdapter(Idea.class, new IdeaDeserializer())
            .registerTypeAdapter(User.class, new UserDeserializer())
            .registerTypeAdapter(Comment.class, new CommentDeserializer())
            .create();

    public static Gson getGson() {
        return gson;
    }
}

