package com.bshashi.jsonbin;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class PathUtil {

    private static Gson gson = new Gson();

    public static JsonElement get(JsonObject obj, String path) {
        String[] keys = path.split("/");
        JsonElement current = obj;
        for (int i = 0; i < keys.length; i++) {
            if (current == null || !current.isJsonObject()) return null;
            current = current.getAsJsonObject().get(keys[i]);
        }
        return current;
    }

    public static void set(JsonObject obj, String path, Object value) {
        String[] keys = path.split("/");
        JsonObject current = obj;
        for (int i = 0; i < keys.length - 1; i++) {
            if (!current.has(keys[i]) || !current.get(keys[i]).isJsonObject()) {
                current.add(keys[i], new JsonObject());
            }
            current = current.getAsJsonObject(keys[i]);
        }
        JsonElement json = gson.toJsonTree(value);
        current.add(keys[keys.length - 1], json);
    }

    public static void remove(JsonObject obj, String path) {
        String[] keys = path.split("/");
        JsonObject current = obj;
        for (int i = 0; i < keys.length - 1; i++) {
            current = current.getAsJsonObject(keys[i]);
        }
        current.remove(keys[keys.length - 1]);
    }
}