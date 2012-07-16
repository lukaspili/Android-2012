package com.siu.android.twok.parser;

import android.util.Log;
import com.google.gson.*;
import com.siu.android.twok.model.Comment;
import com.siu.android.twok.model.Idea;

import java.lang.reflect.Type;

/**
 * Created with IntelliJ IDEA.
 * User: dieux
 * Date: 15/07/12
 * Time: 20:22
 * To change this template use File | Settings | File Templates.
 */
public class CommentDeserializer implements JsonDeserializer<Comment> {

    private static final String CONTENT = "content";
    private static final String USERNAME = "username";
    private static final String DATE = "created_at";

    @Override
    public Comment deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        Comment comment = new Comment();
        comment.setContent(CommonDeserializer.getString(jsonObject.get(CONTENT)));
        comment.setAuteur(CommonDeserializer.getString(jsonObject.get(USERNAME)));
        comment.setDate(CommonDeserializer.getString(jsonObject.get(DATE)));

        return comment;
    }

}
