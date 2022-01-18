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

public class ReceptionistListview extends AppCompatActivity {
    private static final String url = "http://192.168.1.115:80/mobileProject/getReceptionistList.php";
    private ListView receptionistList;

    String tutorials[]
            = { "Algorithms", "Data Structures",
            "Languages", "Interview Corner",
            "GATE", "ISRO CS",
            "UGC NET CS", "CS Subjects",
            "Web Technologies" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receptionist_recycler);

        receptionistList = findViewById(R.id.receptionistList);

        Intent intent = getIntent();

        ArrayAdapter<String> arr;
        arr = new ArrayAdapter<String>(
                this,
                R.layout.support_simple_spinner_dropdown_item,
                tutorials);
        receptionistList.setAdapter(arr);
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

                        }
                    }catch (Exception e){

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(ReceptionistListview.this, error.toString(),Toast.LENGTH_LONG).show();
            }
        });

        Volley.newRequestQueue(ReceptionistListview.this).add(stringRequest);
    }
}