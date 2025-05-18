package com.example.sqlite;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlite.Adapter.CustomListAdapterDeleteRows;
import com.example.sqlite.Adapter.UsersDatabaseAdapter;
import com.example.sqlite.models.User;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class DeleteRowActivity extends AppCompatActivity implements CustomListAdapterDeleteRows.OnItemClickListener {
    private RecyclerView recyclerView;
    private ArrayList<User> users;
    private CustomListAdapterDeleteRows adapter;
    private UsersDatabaseAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_row);

        // Thiết lập toolbar
        Toolbar toolbar = findViewById(R.id.topAppBar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());

        recyclerView = findViewById(R.id.listviewdeleteID);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dbAdapter = new UsersDatabaseAdapter(this);
        users = dbAdapter.getRows();
        adapter = new CustomListAdapterDeleteRows(this, users, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onDeleteClick(User user) {
        new MaterialAlertDialogBuilder(this)
                .setTitle(R.string.delete_user)
                .setMessage(R.string.confirm_delete)
                .setPositiveButton(R.string.yes, (dialog, which) -> {
                    dbAdapter.deleteEntry(String.valueOf(user.getId()));
                    users.remove(user);
                    adapter.notifyDataSetChanged();
                    Snackbar.make(recyclerView, R.string.user_deleted, Snackbar.LENGTH_SHORT).show();
                })
                .setNegativeButton(R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}