package com.example.ruben.rubengerritse_pset6;

import android.os.Bundle;
import android.view.View;

public class HomeActivity extends MenuActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

//  For test purposes
    public void onClick(View view) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();

    }
}