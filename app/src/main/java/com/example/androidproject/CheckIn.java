package com.example.androidproject;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class CheckIn extends AppCompatActivity {
    public static final String DEFAULT = "N/A";
    TextView id;
    TextView cap;
    TextView pric;
    EditText checkindate;
    EditText checkoutdate;
    Button checkin;
    String price;
    String userName;
    int roomID;
    String url;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in);
        Intent intent = getIntent();

        userName = intent.getStringExtra("userName");

        SharedPreferences sharedPreferences = getSharedPreferences("MyData", MODE_PRIVATE);
        String rid= sharedPreferences.getString("RoomID",DEFAULT);
        String capacity =  sharedPreferences.getString("Capacity",DEFAULT);
         price =  sharedPreferences.getString("Price",DEFAULT);
        id = findViewById(R.id.roomId);
        cap = findViewById(R.id.roomCapacity);
        pric = findViewById(R.id.roomPriceByDay);
        id.setText(rid);
        cap.setText(capacity);
        pric.setText(price);
        checkindate = findViewById(R.id.checkindate);
        checkoutdate = findViewById(R.id.chekoutdate);
        checkin = findViewById(R.id.checkinbutton);
         roomID = Integer.parseInt(rid);
        System.out.println(userName+" "+roomID);
        ///////////////////////////////////////////////////////////////////////////////////////////



    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void calculateDate(View view) {
        String startday = String.valueOf(checkindate.getText());
        String endDay = String.valueOf(checkoutdate.getText());
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d/M/u");
        LocalDate startDateValue = LocalDate.parse(startday, dateFormatter);
        LocalDate endDateValue = LocalDate.parse(endDay, dateFormatter);
        long days = ChronoUnit.DAYS.between(startDateValue, endDateValue) + 1;
        System.out.println(days * Integer.parseInt(price));
        double totalPrice = days * Integer.parseInt(price);
        url = "http://192.168.1.115:80/mobileProject/checkIn.php?roomID="+roomID+"&userName="+userName+"&totalPrice="+totalPrice;
        checkInVolly();
    }
    public void checkInVolly(){

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    if(response.equalsIgnoreCase("Check in successfully")){
                        Toast.makeText(CheckIn.this, response,Toast.LENGTH_LONG).show();
                        Intent intent =new Intent(CheckIn.this, CustomerRecycler.class);
                        startActivity(intent);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(CheckIn.this, error.toString(),Toast.LENGTH_LONG).show();

            }


        });

        Volley.newRequestQueue(CheckIn.this).add(stringRequest);

    }


}