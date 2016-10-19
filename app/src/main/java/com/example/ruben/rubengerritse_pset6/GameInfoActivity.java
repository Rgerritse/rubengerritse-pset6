package com.example.ruben.rubengerritse_pset6;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class GameInfoActivity extends AppCompatActivity {
    private static final String PATH = "http://www.giantbomb.com/api/game";
    private static final String KEY = "5496ad3bff8caf2cbadfe3dbbd0bc63c2d41ff34";
    private static final String FORMAT = "json";
    private static final String FIELD_LIST = "id,name,image";

    private JSONObject gameJson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_info);

        int id = getIntent().getExtras().getInt("id");


        String urlString = PATH;
        urlString += String.format("/%s", String.valueOf(id));
        urlString += String.format("?api_key=%s", KEY);
        urlString += String.format("&format=%s", FORMAT);
        urlString += String.format("&field_list=%s", FIELD_LIST);

        try {
            URL url = new URL(urlString);
            String jsonString = new DatabaseQuery().execute(url).get();
            JSONObject json = new JSONObject(jsonString);
            if (json.getString("status_code").equals("1")) {
                String resultsString = json.getString("results");
                gameJson = new JSONObject(resultsString);
            }
        } catch (MalformedURLException | InterruptedException | ExecutionException |
                JSONException e) {
            e.printStackTrace();
        }

        updateUI();
    }

    public void updateUI () {
        TextView gameName = (TextView) findViewById(R.id.game_name_tv);

        try {
            gameName.setText(gameJson.getString("name"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
