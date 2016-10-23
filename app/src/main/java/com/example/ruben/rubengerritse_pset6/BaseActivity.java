package com.example.ruben.rubengerritse_pset6;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.home_mi:
                intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                return true;
            case R.id.lists_mi:
                intent = new Intent(this, ListsActivity.class);
                startActivity(intent);
                return true;
            case R.id.search_mi:
                intent = new Intent(this, SearchActivity.class);
                startActivity(intent);
                return true;
            case R.id.sign_out_mi:
                intent = new Intent(this, SignInActivity.class);
                intent.putExtra("sign_out", true);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public String getUid() {
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }
}
