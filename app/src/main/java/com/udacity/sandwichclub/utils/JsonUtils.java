package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static final String TAG = JsonUtils.class.getSimpleName();

    public static Sandwich parseSandwichJson(String json) {

        Log.wtf(TAG, "parseSandwichJson just Started");

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

                Log.wtf(TAG, "WE HAVE GOT NAME: " + mainName);

                    JSONArray alsoKnowAsArray = name.getJSONArray("alsoKnownAs");
                    if (alsoKnowAsArray != null) {

                        Log.wtf(TAG, "alsoKnowAsArray != null");


                        for (int i = 0; i < alsoKnowAsArray.length(); i++) {
                            alsoKnowAsList.add(alsoKnowAsArray.getString(i));
                            Log.wtf(TAG, "i < alsoKnowAsArray.length() LOOP x " + i);
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
