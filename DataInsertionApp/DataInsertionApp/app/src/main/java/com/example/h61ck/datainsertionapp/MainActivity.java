package com.example.h61ck.datainsertionapp;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Button button; // 버튼 객체
    EditText Name,Email; // 이름 , 이메일 객체
    String server_url = "http://172.30.1.37/update_info.php";// 서버 주소
    AlertDialog.Builder builder; // 알림창 객체

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.btn);
        Name = (EditText) findViewById(R.id.name);
        Email = (EditText) findViewById(R.id.email);
        builder = new AlertDialog.Builder(MainActivity.this); // MainActivity.this 에서 객체 생성

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name,email;
                name = Name.getText().toString(); // 이름가져옴
                email = Email.getText().toString(); // 이메일 가져옴

                StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // 경고창 셋팅
                                builder.setTitle("Server Response");
                                builder.setMessage("Response :"+response);
                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() { // 경고창에 OK를 누르면
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Name.setText("");
                                        Email.setText("");
                                    }
                                });
                                AlertDialog alertDialog  = builder.create(); // 경고창 생성
                                alertDialog.show(); // 경고창 보여줌

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Error...", Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params = new HashMap<String, String>();
                        params.put("name",name);
                        params.put("email",email);
                        return params;
                    }
                };

                MySingleton.getInstance(MainActivity.this).addToRequest(stringRequest);

            }
        });
    }
}
