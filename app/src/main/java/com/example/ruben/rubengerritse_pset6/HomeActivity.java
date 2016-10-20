package com.example.ruben.rubengerritse_pset6;

import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.FirebaseDatabase;

public class HomeActivity extends MenuActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }
}
