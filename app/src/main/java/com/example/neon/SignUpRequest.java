package com.example.neon;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class SignUpRequest extends StringRequest {
    // TODO php wed page's URL
    final static private String URL = "http://gun4930.dothome.co.kr/SignUp.php";
    private Map<String, String> map;

    public SignUpRequest(String Id, String Pw, Response.Listener<String> listener) {
        super(Method.POST,URL,listener,null);

        map = new HashMap<>();
        map.put("UserName", Id);
        map.put("UserPassword", Pw);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
