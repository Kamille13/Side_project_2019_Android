package com.example.sideproject2019;

import android.content.Context;

public class SingletonVolley {

    private final static String REQUEST_URL = "http://192.168.1.17:8080";
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
