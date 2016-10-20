package com.example.ruben.rubengerritse_pset6;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.concurrent.ExecutionException;

public class SearchActivity extends BaseActivity {

    private static final String PATH = "http://www.giantbomb.com/api/search";
    private static final String KEY = "5496ad3bff8caf2cbadfe3dbbd0bc63c2d41ff34";
    private static final String FORMAT = "json";
    private static final String FIELD_LIST = "id,name,image,original_release_date";
    private static final String RESOURCES = "game";
//    api/search/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }

    public void searchQuery(View view) {

        String jsonString = "";
        EditText editText = (EditText) findViewById(R.id.search_et);
        String query = editText.getText().toString().trim();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.search_rv);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        try {
            String urlString = PATH;
            urlString += String.format("?query=%s", URLEncoder.encode(query, "UTF-8"));
            urlString += String.format("&api_key=%s", KEY);
            urlString += String.format("&format=%s", FORMAT);
            urlString += String.format("&field_list=%s", FIELD_LIST);
            urlString += String.format("&resources=%s", RESOURCES);
            URL url = new URL(urlString);

            jsonString = new DatabaseQuery().execute(url).get();
            JSONObject json = new JSONObject(jsonString);
            if (json.getString("status_code").equals("1")) {
                String resultsString = json.getString("results");
                JSONArray resultsArray = new JSONArray(resultsString);
                RecyclerView.Adapter adapter = new SearchAdapter(resultsArray);
                recyclerView.setAdapter(adapter);
            }
        } catch (UnsupportedEncodingException | MalformedURLException | InterruptedException |
                ExecutionException | JSONException e) {
            e.printStackTrace();
        }

    }
}
