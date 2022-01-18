package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class coustomerHome extends AppCompatActivity {
    Button checkIn;
    Button checkOut;
    String url;
    String userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coustomer_home);
        checkIn = findViewById(R.id.checkIn);
        checkOut = findViewById(R.id.checkOut);
        Intent intent = getIntent();
        userName = intent.getStringExtra("userName");
        System.out.println(userName);
        url = "http://192.168.1.115:80/mobileProject/checkOut.php?userName="+userName;

    }

    public void viewRoom(View view){
    Intent intent =new Intent(coustomerHome.this, CustomerRecycler.class);
    intent.putExtra("userName",userName);
    startActivity(intent);
        }
    public void checkOutclick(View view){
    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
            response -> {
                if(response.equalsIgnoreCase("Error, cannot check out from this room")){

                    Toast.makeText(coustomerHome.this, response,Toast.LENGTH_LONG).show();

                }else if(response.equalsIgnoreCase("Check out successfully")){

                    Toast.makeText(coustomerHome.this, response,Toast.LENGTH_LONG).show();

                }
            }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {

            Toast.makeText(coustomerHome.this, error.toString(),Toast.LENGTH_LONG).show();

        }


        });

    Volley.newRequestQueue(coustomerHome.this).add(stringRequest);
    }
    }
