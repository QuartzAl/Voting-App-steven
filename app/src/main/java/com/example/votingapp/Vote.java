package com.example.votingapp;

public class Vote {
    public Vote(int id, String name, Integer candidate_ID) {
        this.id = id;
        Name = name;
        Candidate_ID = candidate_ID;
    }

    public Vote(String name, Integer candidate_ID) {
        Name = name;
        Candidate_ID = candidate_ID;
    }

    int id;
    String Name;
    Integer Candidate_ID;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getCandidate_ID() {
        return Candidate_ID;
    }

    public void setCandidate_ID(Integer candidate_ID) {
        Candidate_ID = candidate_ID;
    }
}
