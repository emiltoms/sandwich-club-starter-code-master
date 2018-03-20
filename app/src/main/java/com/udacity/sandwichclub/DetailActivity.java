package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        TextView alsoKnownTv = findViewById(R.id.also_known_tv);
        TextView ingredientsTv = findViewById(R.id.ingredients_tv);
        TextView originTv = findViewById(R.id.origin_tv);
        TextView descriptionTv = findViewById(R.id.description_tv);

        List<String> list = sandwich.getAlsoKnownAs();
        if (list.isEmpty()) {
            alsoKnownTv.setText(R.string.no_data);
        } else {
            StringBuilder alsoSBuilder = new StringBuilder();
            for (int i = 0; i < list.size(); i++) {
                if (i > 0) {
                    alsoSBuilder.append(System.getProperty("line.separator"));
                }
                alsoSBuilder.append(list.get(i));
            }
            alsoKnownTv.setText(alsoSBuilder.toString());
        }

        List<String> list2 = sandwich.getIngredients();
        if (list2.isEmpty()) {
            alsoKnownTv.setText(R.string.no_data);
        } else {
            StringBuilder ingredientsSBuilder = new StringBuilder();
            for (int j = 0; j < list2.size(); j++) {
                if (j > 0) {
                    ingredientsSBuilder.append(System.getProperty("line.separator"));
                }
                ingredientsSBuilder.append(list2.get(j));
            }
            ingredientsTv.setText(ingredientsSBuilder.toString());
        }
        if (sandwich.getPlaceOfOrigin().isEmpty()) {
            originTv.setText(R.string.no_data);
        } else {
            originTv.setText(sandwich.getPlaceOfOrigin());
        }
        if (sandwich.getDescription().isEmpty()) {
            descriptionTv.setText(R.string.no_data);
        } else {
            descriptionTv.setText(sandwich.getDescription());
        }
    }

}
