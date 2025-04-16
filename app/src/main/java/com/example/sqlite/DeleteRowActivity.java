package com.example.sqlite;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sqlite.Adapter.CustomListAdapterDeleteRows;
import com.example.sqlite.Adapter.UsersDatabaseAdapter;
import com.example.sqlite.models.User;

import java.util.ArrayList;

public class DeleteRowActivity extends AppCompatActivity implements CustomListAdapterDeleteRows.OnItemClickListener {
    private ListView listView;
    private ArrayList<User> users;
    private CustomListAdapterDeleteRows adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_row);

        listView = findViewById(R.id.listviewdeleteID);
        UsersDatabaseAdapter dbAdapter = new UsersDatabaseAdapter(this);
        users = dbAdapter.getRows();
        adapter = new CustomListAdapterDeleteRows(this, users, this);
        listView.setAdapter(adapter);
    }

    @Override
    public void onDeleteClick(User user) {
        UsersDatabaseAdapter dbAdapter = new UsersDatabaseAdapter(this);
        dbAdapter.deleteEntry(String.valueOf(user.getId()));
        users.remove(user);
        adapter.notifyDataSetChanged();
    }
}