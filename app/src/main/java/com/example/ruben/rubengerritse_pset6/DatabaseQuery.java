package com.example.ruben.rubengerritse_pset6;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by ruben on 18-10-16.
 */

public class DatabaseQuery extends AsyncTask<URL,Integer,String> {
    @Override
    protected String doInBackground(URL... params) {
        URL url = params[0];

        BufferedReader in;
        String inputLine;
        String jsonString = "";

        try {
            in = new BufferedReader(new InputStreamReader(url.openStream()));
            while ((inputLine = in.readLine()) != null) {
                jsonString += inputLine;
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonString;
    }
}
