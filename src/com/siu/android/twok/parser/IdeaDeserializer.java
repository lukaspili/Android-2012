package com.siu.android.twok.parser;

import com.google.gson.*;
import com.siu.android.twok.model.Idea;

import java.lang.reflect.Type;

/**
 * Created with IntelliJ IDEA.
 * User: dieux
 * Date: 23/05/12
 * Time: 14:21
 * To change this template use File | Settings | File Templates.
 */

public class IdeaDeserializer implements JsonDeserializer<Idea> {
    private static final String CATEGORY = "category";
    private static final String NICKNAME = "username";
    private static final String NAME = "name";
    private static final String WANT = "want";
    private static final String DONTWANT = "dont_want";
    private static final String ALREADY = "already";
    private static final String DATE = "created_at";
    private static final String CONTENT = "content";
    private static final String ID = "id";
 // les mots cléfs en vert sont les mots-clef de l'api !!!  ces mots-clef doivent être les meme !!!
    // c'est donc ici qu'on rajoute des donnée supplémentaire à récupérer !
    @Override
    public Idea deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        Idea idea = new Idea();
        idea.setName(CommonDeserializer.getString(jsonObject.get(NAME)));
        idea.setNickname(CommonDeserializer.getString(jsonObject.get(NICKNAME)));
        idea.setCategory(CommonDeserializer.getString(jsonObject.get(CATEGORY)));
        idea.setContent(CommonDeserializer.getString(jsonObject.get(CONTENT)));
        idea.setWant(CommonDeserializer.getLong(jsonObject.get(WANT)));
        idea.setDontwant(CommonDeserializer.getLong(jsonObject.get(DONTWANT)));
        idea.setAlready(CommonDeserializer.getLong(jsonObject.get(ALREADY)));
        idea.setDate(CommonDeserializer.getString(jsonObject.get(DATE)));
        idea.setId(CommonDeserializer.getString(jsonObject.get(ID)));

        return idea;
    }
}