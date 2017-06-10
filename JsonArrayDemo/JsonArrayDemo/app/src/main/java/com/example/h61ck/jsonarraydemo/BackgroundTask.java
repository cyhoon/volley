package com.example.h61ck.jsonarraydemo;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by h61ck on 2017-05-03.
 */

// 배열 스코프 문제인거같음.

public class BackgroundTask {

    Context context;
    ArrayList<Contact> arrayList = new ArrayList<>(); // 객체 생성
    String json_url = "http://172.30.1.37/contactinfo.php";

    public BackgroundTask(Context context)
    {
        this.context = context;
    }

    public ArrayList<Contact> getList()
    {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, json_url, (String) null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        int count = 0;
                        while (count<response.length())
                        {
                            try {
                                JSONObject jsonObject = response.getJSONObject(count);
                                Contact contact = new Contact(jsonObject.getString("Name"),jsonObject.getString("Email"));
                                arrayList.add(contact);
                                count++;
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(context, "Error...", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
                
            }
        });

        MySingleton.getInstance(context).addToRequestQue(jsonArrayRequest);
        return arrayList;
    }

}
