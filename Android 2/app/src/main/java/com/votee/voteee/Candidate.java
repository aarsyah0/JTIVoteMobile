package com.votee.voteee;

// Candidate.java
public class Candidate {
    private int id;
    private String name;
    private String details;
    private int votes;

    private int photo;

    public Candidate(int id, String name, String details, int votes, int photo) {
        this.id = id;
        this.name = name;
        this.details = details;
        this.votes = votes;
        this.photo = photo;
    }

    public Candidate(int id, int photo, String name, String details, int votes) {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDetails() {
        return details;
    }

    public int getVotes() {
        return votes;
    }
    public int getPhoto() {
        return photo;
    }
}

