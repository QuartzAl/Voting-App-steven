package com.example.votingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import java.util.concurrent.atomic.AtomicReference;

public class VotingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voting);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        Log.d("name", name);

        Button btnVote = findViewById(R.id.btn_confirm);

        Button btn_candidate1 = findViewById(R.id.btn_candidate1);
        Button btn_candidate2 = findViewById(R.id.btn_candidate2);
        Button btn_candidate3 = findViewById(R.id.btn_candidate3);
        Button btn_candidate4 = findViewById(R.id.btn_candidate4);

        AtomicReference<Integer> selectedCandidate = new AtomicReference<>(0);

        btn_candidate1.setOnClickListener(view -> {
            selectedCandidate.set(1);
            btn_candidate1.setText("Voted");
            btn_candidate2.setText("Vote");
            btn_candidate3.setText("Vote");
            btn_candidate4.setText("Vote");
        });

        btn_candidate2.setOnClickListener(view -> {
            selectedCandidate.set(2);
            btn_candidate1.setText("Vote");
            btn_candidate2.setText("Voted");
            btn_candidate3.setText("Vote");
            btn_candidate4.setText("Vote");
        });

        btn_candidate3.setOnClickListener(view -> {
            selectedCandidate.set(3);
            btn_candidate1.setText("Vote");
            btn_candidate2.setText("Vote");
            btn_candidate3.setText("Voted");
            btn_candidate4.setText("Vote");
        });

        btn_candidate4.setOnClickListener(view -> {
            selectedCandidate.set(4);
            btn_candidate1.setText("Vote");
            btn_candidate2.setText("Vote");
            btn_candidate3.setText("Vote");
            btn_candidate4.setText("Voted");
        });

        btnVote.setOnClickListener(view -> {
            if (selectedCandidate.get() == 0) {
//                make a toast telling them to select a candidate first
                CharSequence text = "Select a candidate to vote first!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(this /* MyActivity */, text, duration);
                toast.show();
            } else {
                dbHandler dbHandler = new dbHandler(this, Constants.DB_NAME, null, Constants.DB_VERSION);
                Vote vote = new Vote(name, selectedCandidate.get());
                dbHandler.addVote(vote);
                Toast.makeText(this, "Vote submitted", Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(this, VoteListActivity.class);
                startActivity(myIntent);
                finish();
            }
        });

    }
}