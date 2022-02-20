package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ReceptionistListview extends AppCompatActivity {
    private static final String url = "http://192.168.1.11:80/mobileProject/getReceptionistList.php";
    private ListView receptionistList;
    private ArrayList<RoomReceptionist> rooms = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receptionist_recycler);

        receptionistList = findViewById(R.id.receptionistList);

        loadRooms();
    }

    public  void loadRooms(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {

                    try {
                        JSONArray array = new JSONArray(response);
                        System.out.println(array.toString());
                        for (int i = 0; i<array.length(); i++){
                            JSONObject roomObject = array.getJSONObject(i);

                            int id = roomObject.getInt("roomID");
                            int capacity = roomObject.getInt("capacity");
                            int priceByDay = roomObject.getInt("priceByDay");
                            String userName = roomObject.getString("userName");
                            RoomReceptionist room = new RoomReceptionist(id, capacity, priceByDay, userName);
                            rooms.add(room);

                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    ListAdapter listAdapter = new ListAdapter(rooms,ReceptionistListview.this,R.layout.activity_list_adapter);
                    receptionistList.setAdapter(listAdapter);
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(ReceptionistListview.this, error.toString(),Toast.LENGTH_LONG).show();
            }
        });

        Volley.newRequestQueue(ReceptionistListview.this).add(stringRequest);
    }
}