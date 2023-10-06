package com.example.votingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;

public class VoteListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote_list);

//        setup recyclerview
        dbHandler dbHandler = new dbHandler(this, Constants.DB_NAME, null, Constants.DB_VERSION);

        ArrayList<Vote> votes = new ArrayList<Vote>();
        votes.add(new Vote("Alyssa", dbHandler.getVoteAmount(1)));
        votes.add(new Vote("Karin", dbHandler.getVoteAmount(2)));
        votes.add(new Vote("Roger", dbHandler.getVoteAmount(3)));
        votes.add(new Vote("Potato man", dbHandler.getVoteAmount(4)));

        RecyclerView recyclerView = findViewById(R.id.rv_vote_list);
        VoteListRecyclerAdapter recyclerViewAdapter = new VoteListRecyclerAdapter(votes);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        ImageButton btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(view -> {
            finish();
        });
    }
}