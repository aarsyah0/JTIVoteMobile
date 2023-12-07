package com.votee.voteee;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class VoteRequest extends StringRequest {

    private static final String VOTE_URL = "http://jti-vote.tifint.myhost.id/jtivote/process_vote.php";  // Ganti dengan URL server dan lokasi skrip PHP Anda

    private Map<String, String> params;

    public VoteRequest(String electionId, String candidateId, String voterId, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(Method.POST, VOTE_URL, listener, errorListener);
        params = new HashMap<>();
        params.put("e_id", electionId);
        params.put("c_id", candidateId);
        params.put("v_id", voterId);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return params;
    }
}