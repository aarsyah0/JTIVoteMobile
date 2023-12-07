package com.votee.voteee;

public class ObjectElection {
    String election_topic;
    int electionId;
    public ObjectElection(String election_topic) {
        this.election_topic = election_topic;
    }

    public String getElection_topic() {
        return election_topic;
    }

    public void setElection_topic(String election_topic) {
        this.election_topic = election_topic;
    }

    public int getElectionId() {
        return electionId;
    }

    public void setElectionId(int electionId) {
        this.electionId = electionId;
    }
}
