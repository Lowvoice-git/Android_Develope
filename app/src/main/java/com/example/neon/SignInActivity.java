package com.example.neon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class SignInActivity extends Activity {
    private EditText editTextId;
    private EditText editTextPw;
    private Button btn_SignIn, btn_SignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        editTextId = (EditText)findViewById(R.id.editId);
        editTextPw = (EditText)findViewById(R.id.editPw);

        btn_SignIn = findViewById(R.id.SignIn);
        btn_SignUp = findViewById(R.id.ToSignUp);

        btn_SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        btn_SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String Id = editTextId.getText().toString();
                String Pw = editTextPw.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");

                            if (success) {
                                // TODO json file's context 'UserName' and 'UserPassword'
                                String username = jsonObject.getString("UserName");
                                String password = jsonObject.getString("UserPassword");

                                Toast.makeText(getApplicationContext(), "Log In", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                                intent.putExtra("log", "User");
                                intent.putExtra("username", username);

                                startActivity(intent);
                            } else {
                                Toast.makeText(getApplicationContext(), "Log In Failed", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                SignInRequest signInRequest = new SignInRequest(Id, Pw, responseListener);
                RequestQueue queue = Volley.newRequestQueue(SignInActivity.this);
                queue.add(signInRequest);
            }
        });
    }
}