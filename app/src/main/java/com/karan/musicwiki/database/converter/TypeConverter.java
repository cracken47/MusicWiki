package com.karan.musicwiki.database.converter;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.karan.musicwiki.database.entity.Tags;

import java.lang.reflect.Type;

public class TypeConverter {

    @androidx.room.TypeConverter
    public static Tags fromStringLToptags(String value) {
        Type listType = new TypeToken<Tags>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @androidx.room.TypeConverter
    public static String fromToptagsToString(Tags list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }


}
