package com.example.studyforce;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class Login_Request extends StringRequest {

    final static private String URL ="http://118.33.132.221/php/Login.php";
    private Map<String, String> map;

    public Login_Request(String Uid, String Upw, Response.Listener<String> listener){
        super(Request.Method.POST, URL, listener, null);

        map =new HashMap<>();
        map.put("Uid", Uid);
        map.put("Upw", Upw);
    }

    @Override
    protected Map<String, String>getParams() throws AuthFailureError {
        return map;
    }

    //값을 넘겨줄 때
    /*public static Context context_login;
    public int userid1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__request);

        context_login = this;*/
    }
}