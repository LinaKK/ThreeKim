package com.example.studyforce;

import com.android.volley.AuthFailureError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.Response;

import java.util.HashMap;
import java.util.Map;

public class Sign_Request extends StringRequest {

    //받아올 값 목록 String Uname, String Uid, String Upw, String Uemail
    final static private String URL ="http://118.33.132.221/php/Sign.php";
    private Map<String, String> map;
    //private Map<String, String> parameters;

    public Sign_Request(String Uname, String Uid, String Upw, String Uemail, Response.Listener<String> listener, Response.ErrorListener errorListener){
        super(Method.POST, URL, listener, errorListener);

        map =new HashMap<>();
        map.put("Uname", Uname);
        map.put("Uid", Uid);
        map.put("Upw", Upw);
        map.put("Uemail", Uemail);
    }

    @Override
    protected Map<String, String>getParams() throws AuthFailureError {
        return map;
    }

}