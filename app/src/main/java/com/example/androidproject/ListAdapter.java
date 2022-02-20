package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {
    private ArrayList<RoomReceptionist> rooms;
    private Context context;
    private int layout;

    public ListAdapter(ArrayList<RoomReceptionist> rooms,Context context ,int layout){
        this.rooms = rooms;
        this.context = context ;
        this.layout = layout;

    }

    @Override
    public int getCount() {
        return rooms.size();
    }

    @Override
    public Object getItem(int position) {
        return rooms.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }
    private class ViewHolder  {
        TextView roomId,roomCapacity,roomPriceByDay,username;
    }
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = new ViewHolder();
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(layout,null);
        viewHolder.roomId = view.findViewById(R.id.roomId);
        viewHolder.roomCapacity = view.findViewById(R.id.roomCapacity);
        viewHolder.roomPriceByDay = view.findViewById(R.id.roomPriceByDay);
        viewHolder.username = view.findViewById(R.id.username);

        RoomReceptionist roomReceptionist = rooms.get(position);
        viewHolder.roomId.setText("Room id : "+roomReceptionist.getId()+"\n");
        viewHolder.roomCapacity.setText("Room Capacity: "+roomReceptionist.getCapacity()+"\n");
        viewHolder.roomPriceByDay.setText("Price By Day : "+roomReceptionist.getPriceByDay()+"\n");
        String userName = roomReceptionist.getUserName();
        if(userName.trim().equals(""))
            viewHolder.username.setText("Username : none \n");
        else
            viewHolder.username.setText("Username :  "+userName+"\n");

        return view;
    }



}