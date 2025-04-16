package com.example.sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sqlite.Adapter.CustomListAdapterUpdateRows;
import com.example.sqlite.Adapter.UsersDatabaseAdapter;
import com.example.sqlite.models.User;

import java.util.ArrayList;

public class UpdateRowView extends AppCompatActivity implements CustomListAdapterUpdateRows.OnItemClickListener {
    private ListView listView;
    private ArrayList<User> users;
    private CustomListAdapterUpdateRows adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_row);

        listView = findViewById(R.id.listupdateviewID);
        UsersDatabaseAdapter dbAdapter = new UsersDatabaseAdapter(this);
        users = dbAdapter.getRows();
        adapter = new CustomListAdapterUpdateRows(this, users, this);
        listView.setAdapter(adapter);
    }

    @Override
    public void onUpdateClick(User user) {
        Intent intent = new Intent(this, EditUserActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        users.clear();
        users.addAll(new UsersDatabaseAdapter(this).getRows());
        adapter.notifyDataSetChanged();
    }
}