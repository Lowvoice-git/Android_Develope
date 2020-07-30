package com.example.neon;

import android.app.Activity;
import android.app.AlertDialog;
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

public class SignUpActivity extends Activity {
    private EditText editTextId;
    private EditText editTextPw;
    private Button btn_SignUp, btn_Valid;
    private AlertDialog dialog;
    private boolean isValid = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        editTextId = (EditText)findViewById(R.id.editSignUpId);
        editTextPw = (EditText)findViewById(R.id.editSignUpPw);

        btn_Valid = findViewById(R.id.ValidId);
        btn_SignUp = findViewById(R.id.SignUp);

        btn_Valid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Id = editTextId.getText().toString();
                if(isValid) {
                    return;
                }

                if(Id.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
                    dialog = builder.setMessage("Username cannot be blank").setPositiveButton("Confirm",null).create();
                    dialog.show();
                    return;
                }
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
                                dialog = builder.setMessage("You can use this username").setPositiveButton("Confirm",null).create();
                                dialog.show();
                                editTextId.setEnabled(false);
                                isValid = true;
                                btn_Valid.setText("OK");
                            }
                            else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
                                dialog = builder.setMessage("Cannot use this Username").setPositiveButton("Confirm",null).create();
                                dialog.show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                ValidRequest validRequest = new ValidRequest(Id,responseListener);
                RequestQueue queue = Volley.newRequestQueue(SignUpActivity.this);
                queue.add(validRequest);
            }
        });

        btn_SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Id = editTextId.getText().toString();
                final String Pw = editTextPw.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if(success) {
                                Toast.makeText(getApplicationContext(),"Sign Up Success!",Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(getApplicationContext(),"Sign Up Fail!",Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                SignUpRequest signUpRequest = new SignUpRequest(Id, Pw, responseListener);
                RequestQueue queue = Volley.newRequestQueue(SignUpActivity.this);
                queue.add(signUpRequest);
            }
        });
    }
}