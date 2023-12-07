package com.votee.voteee;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

public class Votee extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_votee);

        // Inisialisasi UI components
        Button voteButton = findViewById(R.id.voteButton);

        // Menambahkan listener untuk tombol vote
        voteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Panggil metode untuk melakukan vote
                CastVote("1", "2", "SESSION_USER_ID"); // Ganti dengan nilai yang sesuai
            }
        });
    }

    private void CastVote(String electionId, String candidateId, String voterId) {
        RequestQueue queue = Volley.newRequestQueue(this);

        VoteRequest voteRequest = new VoteRequest(electionId, candidateId, voterId,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        handleVoteResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Tangani kesalahan, misalnya, tampilkan pesan kesalahan
                        Toast.makeText(Votee.this, "Error occurred during vote request", Toast.LENGTH_SHORT).show();
                    }
                });

        queue.add(voteRequest);
    }

    private void handleVoteResponse(String response) {
        switch (response) {
            case "Success":
                // Suara berhasil
                Toast.makeText(this, "Vote success", Toast.LENGTH_SHORT).show();
                break;
            case "Failed":
                // Suara gagal
                Toast.makeText(this, "Vote failed", Toast.LENGTH_SHORT).show();
                break;
            case "AlreadyVoted":
                // Pengguna sudah memberikan suara
                Toast.makeText(this, "You have already voted", Toast.LENGTH_SHORT).show();
                break;
            case "InvalidMethod":
                // Metode HTTP tidak didukung
                Toast.makeText(this, "Invalid HTTP method", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}