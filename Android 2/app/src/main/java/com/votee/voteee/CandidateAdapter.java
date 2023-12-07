package com.votee.voteee;

// CandidateAdapter.java
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CandidateAdapter extends RecyclerView.Adapter<CandidateAdapter.ViewHolder> {

    private List<Candidate> candidates;
    private Context context;

    public CandidateAdapter(List<Candidate> candidates, Context context) {
        this.candidates = candidates;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_candidate, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Candidate candidate = candidates.get(position);

        // Set data kandidat ke elemen UI
        holder.imageViewCandidate.setImageResource(candidate.getPhoto()); // Ganti dengan sumber gambar yang sesuai
        holder.textViewCandidateName.setText(candidate.getName());
        holder.textViewCandidateDetails.setText(candidate.getDetails());
        holder.textViewCandidateVotes.setText(String.valueOf(candidate.getVotes()));

        // Tambahkan logika untuk menangani klik tombol vote di sini
        holder.buttonVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle klik tombol vote
                // Panggil metode untuk melakukan vote
                CastVote(candidate.getId(), candidate.getId(), "SESSION_USER_ID"); // Ganti dengan nilai yang sesuai
            }
        });
    }

    @Override
    public int getItemCount() {
        return candidates.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewCandidate;
        TextView textViewCandidateName;
        TextView textViewCandidateDetails;
        TextView textViewCandidateVotes;
        Button buttonVote;
        CardView cardViewCandidate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageViewCandidate = itemView.findViewById(R.id.imageViewCandidate);
            textViewCandidateName = itemView.findViewById(R.id.textViewCandidateName);
            textViewCandidateDetails = itemView.findViewById(R.id.textViewCandidateDetails);
            textViewCandidateVotes = itemView.findViewById(R.id.textViewCandidateVotes);
            buttonVote = itemView.findViewById(R.id.buttonVote);
            cardViewCandidate = itemView.findViewById(R.id.cardViewCandidate);
        }
    }

    private void CastVote(int electionId, int candidateId, String voterId) {
        // Implementasi metode untuk melakukan vote (seperti yang telah dijelaskan sebelumnya)
        // ...
    }
}

