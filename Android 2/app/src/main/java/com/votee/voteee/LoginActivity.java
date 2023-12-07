package com.votee.voteee;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    EditText username, password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernamee = username.getText().toString();
                String passwordd = password.getText().toString();

                if (! (usernamee.isEmpty()) || (passwordd.isEmpty())) {
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

                    String login = "http://jti-vote.tifint.myhost.id/jtivote/vote/api_login.php";
                    StringRequest stringRequest = new StringRequest(Request.Method.GET, login + "?username=" + usernamee + "&password=" + passwordd, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.equals("0")) {
                                Toast.makeText(getApplicationContext(), "salah", Toast.LENGTH_SHORT).show();
                            } else {
                                try {
                                    JSONObject jsonResponse = new JSONObject(response);
                                    String idVote = jsonResponse.getString("id");

                                    SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString("id_vote", idVote);
                                    editor.apply();

                                    startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            if (error instanceof NoConnectionError) {
                                Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    requestQueue.add(stringRequest);
                } else {
                    Toast.makeText(getApplicationContext(), "salah", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}