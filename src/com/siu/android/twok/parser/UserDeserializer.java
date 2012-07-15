package com.siu.android.twok.parser;

import com.google.gson.*;
import com.siu.android.twok.DataAccessLayer;
import com.siu.android.twok.model.User;

import java.lang.reflect.Type;

/**
 * Created with IntelliJ IDEA.
 * User: dieux
 * Date: 09/07/12
 * Time: 14:31
 * To change this template use File | Settings | File Templates.
 */
public class UserDeserializer implements JsonDeserializer<User> {

    @Override
    public User deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        User user = new User();
        user.setToken(CommonDeserializer.getString(jsonObject.get("access_token")));

        return user;
    }
}
