package com.example.votingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        setup edittext fields and move to voting activity if credentials are correct
//        setup button to move to vote list activity
        EditText etUsername = findViewById(R.id.et_username);
        EditText etPassword = findViewById(R.id.et_password);
        Button btnLogin = findViewById(R.id.btn_login);
        Button btnVote = findViewById(R.id.btn_vote);

        dbHandler dbHandler = new dbHandler(this, Constants.DB_NAME, null, Constants.DB_VERSION);

        btnLogin.setOnClickListener(view -> {
            if(etPassword.getText().toString().equals("admin")){
                if (dbHandler.getVote(etUsername.getText().toString()) == null){

                    Intent myIntent = new Intent(this, VotingActivity.class);
                    myIntent.putExtra("name", etUsername.getText().toString());
                    etUsername.setText("");
                    etPassword.setText("");
                    startActivity(myIntent);
                } else {
                    etUsername.setText("");
                    etPassword.setText("");
                    Toast.makeText(this,
                            "You have already voted, redirecting to vote list screen",
                            Toast.LENGTH_SHORT).show();
                    Intent myIntent = new Intent(this, VoteListActivity.class);
                    myIntent.putExtra("name", etUsername.getText().toString());
                    startActivity(myIntent);
                }

            } else {
                etUsername.setText("");
                etPassword.setText("");
                Toast.makeText(this, "Wrong password", Toast.LENGTH_SHORT).show();
            }
        });

        btnVote.setOnClickListener(view -> {
            Intent myIntent = new Intent(this, VoteListActivity.class);
            myIntent.putExtra("name", etUsername.getText().toString());
            startActivity(myIntent);
        });
    }
}