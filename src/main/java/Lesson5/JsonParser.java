package Lesson5;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

//import java.lang.reflect.Type;

public class JsonParser {
    public static List<Supply> getSuppliesList() throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get("src/main/resources/supplies.json"));
        return new Gson().fromJson(new String(bytes, "UTF-8"), new TypeToken<List<Supply>>() {}.getType());
    }

//    public static List<Repository> getRepositoryList() throws IOException {
//        byte[] bytes = Files.readAllBytes(Paths.get("src/main/resources/supplies.json"));
//        Gson gson = new Gson();
//        Type type = new TypeToken<List<Repository>>() {
//        }.getType();
//        return gson.fromJson(new String(bytes, "UTF-8"), type);
//    }
}
