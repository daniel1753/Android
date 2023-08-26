package com.movies;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginButton = (Button) findViewById(R.id.LoginButton);
        if (loginButton != null) {
            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openMainActivity();
                }
            });
        }
        else
        {
            Toast.makeText(this,"Error", Toast.LENGTH_SHORT).show();

        }
    }

        public void openMainActivity()
    {
        startActivity(new Intent(this, MainFeedActivity.class));
    }


}