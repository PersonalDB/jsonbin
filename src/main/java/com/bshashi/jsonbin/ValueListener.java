package com.bshashi.jsonbin;

import com.google.gson.JsonElement;

public interface ValueListener {
    void onDataChange(JsonElement data);
}