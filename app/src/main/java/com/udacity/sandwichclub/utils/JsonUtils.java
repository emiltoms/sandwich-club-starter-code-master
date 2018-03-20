package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        if (json != null) {
            String mainName;
            List<String> alsoKnowAsList = new ArrayList<>();
            String placeOfOrigin;
            String description;
            String image;
            List<String> ingredients = new ArrayList<>();

            try {
                JSONObject jsonObject = new JSONObject(json);

                JSONObject name = jsonObject.getJSONObject("name");
                mainName = name.getString("mainName");

                JSONArray alsoKnowAsArray = name.getJSONArray("alsoKnownAs");
                if (alsoKnowAsArray != null) {

                    for (int i = 0; i < alsoKnowAsArray.length(); i++) {
                        alsoKnowAsList.add(alsoKnowAsArray.getString(i));
                    }
                }

                placeOfOrigin = jsonObject.getString("placeOfOrigin");

                description = jsonObject.getString("description");

                image = jsonObject.getString("image");

                JSONArray ingredientsArray = jsonObject.getJSONArray("ingredients");
                if (ingredientsArray != null) {
                    for (int i = 0; i < ingredientsArray.length(); i++) {
                        ingredients.add(ingredientsArray.getString(i));
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
            return new Sandwich(mainName, alsoKnowAsList, placeOfOrigin, description, image, ingredients);
        }

        return null;
    }
}
