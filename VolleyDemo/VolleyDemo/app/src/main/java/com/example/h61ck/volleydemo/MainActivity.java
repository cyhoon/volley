package com.example.h61ck.volleydemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    Button btn;
    TextView txt;
    String server_url = "http://172.30.1.37/honjar/test";
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.btn);
        txt = (TextView) findViewById(R.id.txt);

        /*Cache cache = new DiskBasedCache(getCacheDir(), 1024*1024);
        Network network = new BasicNetwork(new HurlStack());
        requestQueue = new RequestQueue(cache,network);
        requestQueue.start();*/

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final RequestQueue requestQueue =
                        Volley.newRequestQueue(MainActivity.this);

                StringRequest stringRequest =
                        new StringRequest(Request.Method.POST, server_url,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {

                                        txt.setText(response);
//                                        requestQueue.stop();
                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                txt.setText("Something went wrong...");
                                error.printStackTrace();
//                                requestQueue.stop();
                            }
                        });

                Mysingleton.getInstance(getApplicationContext()).addRequestQue(stringRequest);
//                requestQueue.add(stringRequest);
            }
        });

    }
}
