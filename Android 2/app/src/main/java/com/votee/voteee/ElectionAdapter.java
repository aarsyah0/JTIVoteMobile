package com.votee.voteee;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ElectionAdapter extends ArrayAdapter<ObjectElection> {
    private List<ObjectElection> electionList;

    public ElectionAdapter(Context context, List<ObjectElection> electionList) {
        super(context, 0, electionList);
        this.electionList = electionList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ObjectElection election = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_election, parent, false);
        }

        TextView electionTopicTextView = convertView.findViewById(R.id.election_topic_text_view);
        electionTopicTextView.setText(election.getElection_topic());

        return convertView;
    }
}

