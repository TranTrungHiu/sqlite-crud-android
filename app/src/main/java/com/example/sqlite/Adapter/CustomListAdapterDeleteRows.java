package com.example.sqlite.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.sqlite.R;
import com.example.sqlite.models.User;

import java.util.ArrayList;

public class CustomListAdapterDeleteRows extends BaseAdapter {
    private Context context;
    private ArrayList<User> userList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onDeleteClick(User user);
    }

    public CustomListAdapterDeleteRows(Context context, ArrayList<User> userList, OnItemClickListener listener) {
        this.context = context;
        this.userList = userList;
        this.listener = listener;
    }

    @Override
    public int getCount() { return userList.size(); }
    @Override
    public Object getItem(int position) { return userList.get(position); }
    @Override
    public long getItemId(int position) { return position; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_delete, parent, false);
        }
        TextView userName = convertView.findViewById(R.id.userName);
        TextView userPhone = convertView.findViewById(R.id.userPhone);
        TextView userEmail = convertView.findViewById(R.id.userEmail);
        Button deleteButton = convertView.findViewById(R.id.deleteButton);

        User user = userList.get(position);
        userName.setText(user.getUsername());
        userPhone.setText(user.getPhone());
        userEmail.setText(user.getEmail());
        deleteButton.setOnClickListener(v -> listener.onDeleteClick(user));

        return convertView;
    }
}