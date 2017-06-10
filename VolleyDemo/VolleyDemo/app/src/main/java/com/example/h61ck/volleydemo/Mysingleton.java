package com.example.h61ck.volleydemo;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by h61ck on 2017-05-03.
 */

// 디자인 패턴 : 싱글리턴

public class Mysingleton {

    private static Mysingleton mInstance;
    private RequestQueue requestQueue;
    private static Context mCtx;

    private Mysingleton(Context context)
    {
        mCtx = context;
        requestQueue = getRequestQueue();
    }

    public RequestQueue getRequestQueue()
    {
        if(requestQueue==null){
            requestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return requestQueue;
    }

    public static synchronized Mysingleton getInstance(Context context)
    {
        // mInstacne 가 null 이라면
        if( mInstance == null )
        {
            // 객체를 생성한다.
            mInstance = new Mysingleton(context);
        }

        return mInstance;
    }

    public<T> void addRequestQue(Request<T> request){
        requestQueue.add(request);
    }

}
