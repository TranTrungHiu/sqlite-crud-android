package com.example.sqlite;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlite.Adapter.CustomListAdapterUpdateRows;
import com.example.sqlite.Adapter.UsersDatabaseAdapter;
import com.example.sqlite.models.User;

import java.util.ArrayList;

public class UpdateRowView extends AppCompatActivity implements CustomListAdapterUpdateRows.OnItemClickListener {
    private RecyclerView recyclerView;
    private ArrayList<User> users;
    private CustomListAdapterUpdateRows adapter;
    private UsersDatabaseAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_row);

        // Thiết lập toolbar
        Toolbar toolbar = findViewById(R.id.topAppBar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());

        recyclerView = findViewById(R.id.listupdateviewID);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dbAdapter = new UsersDatabaseAdapter(this);
        users = dbAdapter.getRows();
        adapter = new CustomListAdapterUpdateRows(this, users, this);
        recyclerView.setAdapter(adapter);
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