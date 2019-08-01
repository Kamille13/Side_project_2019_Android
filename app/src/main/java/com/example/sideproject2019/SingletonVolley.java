package com.example.sideproject2019;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class SingletonVolley {

    private final static String REQUEST_URL = "http://10.0.2.2:8080/";
    private static SingletonVolley instance;
    private static Context ctx;
    private RequestQueue requestQueue;

    private SingletonVolley(Context context) {
        ctx = context;
        requestQueue = getRequestQueue();
    }

    public static synchronized SingletonVolley getInstance(Context context) {
        if (instance == null) {
            instance = new SingletonVolley(context);
        }
        return instance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
        }
        return requestQueue;
    }
}
