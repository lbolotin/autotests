package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUtil {
    static {
        GsonBuilder builder = new GsonBuilder();
        GSON_CONVERTER = builder.create();
    }

    public static final Gson GSON_CONVERTER;
}
