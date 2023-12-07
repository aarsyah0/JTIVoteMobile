package com.votee.voteee;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class CandidateActivity extends AppCompatActivity {

    private RecyclerView recyclerViewCandidates;
    private CandidateAdapter candidateAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate);

        // Inisialisasi RecyclerView
        recyclerViewCandidates = findViewById(R.id.recyclerViewCandidates);
        recyclerViewCandidates.setLayoutManager(new LinearLayoutManager(this));

        // Ambil data kandidat dari Volley
        fetchDataFromVolley();
    }

    // Metode untuk mengambil data kandidat dari Volley
    private void fetchDataFromVolley() {
        String url = "https://jti-vote.tifint.myhost.id/jtivote/vote/candidate.php"; // Ganti dengan URL endpoint API Anda

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Tangani respons JSON
                        List<Candidate> candidateList = parseJsonResponse(response);

                        // Inisialisasi dan set adapter ke RecyclerView
                        candidateAdapter = new CandidateAdapter(candidateList, CandidateActivity.this);
                        recyclerViewCandidates.setAdapter(candidateAdapter);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Tangani kesalahan, misalnya, tampilkan pesan kesalahan
                    }
                });

        // Tambahkan permintaan ke antrian Volley
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }

    // Metode untuk mengurai respons JSON dan membuat daftar kandidat
    private List<Candidate> parseJsonResponse(JSONArray response) {
        List<Candidate> candidates = new ArrayList<>();

        try {
            for (int i = 0; i < response.length(); i++) {
                JSONObject candidateObject = response.getJSONObject(i);

                int id = candidateObject.getInt("id");
                int photo = candidateObject.getInt("photo"); // Ganti dengan sumber gambar yang sesuai
                String name = candidateObject.getString("name");
                String details = candidateObject.getString("details");
                int votes = candidateObject.getInt("votes");

                candidates.add(new Candidate(id, photo, name, details, votes));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return candidates;
    }
}

