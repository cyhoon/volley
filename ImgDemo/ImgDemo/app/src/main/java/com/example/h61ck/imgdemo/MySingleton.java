package com.example.h61ck.imgdemo;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by h61ck on 2017-05-03.
 */

public class MySingleton {
    private static MySingleton mInstance;
    private RequestQueue requestQueue;
    private static Context mCtx;


    private MySingleton(Context context)
    {
        mCtx = context;
        requestQueue = getRequestQueue();
    }

    private RequestQueue getRequestQueue()
    {
        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return requestQueue;
    }

    public static synchronized MySingleton getInstance(Context mCtx)
    {
        if(mInstance == null){
            mInstance = new MySingleton(mCtx);
        }
        return mInstance;
    }

    public<T> void addToRequestQue(Request<T> request)
    {
        requestQueue.add(request);
    }

}
