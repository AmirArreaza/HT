package com.hometask.model;

import com.google.gson.*;

import java.lang.reflect.Type;

public class AccountAdapter implements JsonSerializer<Account>, JsonDeserializer<Account> {
    @Override
    public Account deserialize(JsonElement jsonElement, Type typeOfT, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        String type = jsonObject.get("type").getAsString();
        try {
            if(type.equals("METAL") || type.equals("PREMIUM") || type.equals("STANDARD")){
                return jsonDeserializationContext.deserialize(jsonObject, AccPersonal.class);
            }else{
                return jsonDeserializationContext.deserialize(jsonObject, AccBusiness.class);
            }
        } catch (Exception cnfe) {
            throw new JsonParseException("Unknown element type: Account", cnfe);
        }
    }

    @Override
    public JsonElement serialize(Account account, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject result = new JsonObject();
        result.add("type", new JsonPrimitive(account.getClass().getSimpleName()));
        result.add("properties", jsonSerializationContext.serialize(account, account.getClass()));

        return result;
    }
}
