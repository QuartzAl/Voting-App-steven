package com.example.votingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class VoteListRecyclerAdapter extends RecyclerView.Adapter<VoteListRecyclerAdapter.ViewHolder>{

//    make an array list to store all the dta
//    make a constructor to pass the data to the adapter
    ArrayList<Vote> votes;
    public VoteListRecyclerAdapter(ArrayList<Vote> votes){
        this.votes = votes;
    }

    @NonNull
    @Override
    public VoteListRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = View.inflate(context, R.layout.vote_item, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull VoteListRecyclerAdapter.ViewHolder holder, int position) {
        holder.txVoterName.setText(votes.get(position).getName());
        holder.txVotedFor.setText("Amount of votes: " + String.valueOf(votes.get(position).getCandidate_ID()));
    }

    @Override
    public int getItemCount() {
        if (votes == null){
            return 0;
        }
        return votes.size();
    }

//    add a viewholder for the recyclerview
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txVoterName;
        TextView txVotedFor;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txVoterName = itemView.findViewById(R.id.tx_voter_name);
            txVotedFor = itemView.findViewById(R.id.tx_voted_for);
        }
    }
}
