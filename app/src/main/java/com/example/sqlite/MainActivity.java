package com.example.sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sqlite.Adapter.UsersDatabaseAdapter;

public class MainActivity extends AppCompatActivity {
    private TextView countTextView;
    private UsersDatabaseAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbAdapter = new UsersDatabaseAdapter(this);
        countTextView = findViewById(R.id.countTextView);

        Button insertButton = findViewById(R.id.insertButton);
        Button updateButton = findViewById(R.id.updateButton);
        Button deleteButton = findViewById(R.id.deleteButton);
        Button countButton = findViewById(R.id.countButton);
        Button deleteAllButton = findViewById(R.id.deleteAllButton);
        insertButton.setOnClickListener(v -> startActivity(new Intent(this, InsertRowActivity.class)));
        updateButton.setOnClickListener(v -> startActivity(new Intent(this, UpdateRowView.class)));
        deleteButton.setOnClickListener(v -> startActivity(new Intent(this, DeleteRowActivity.class)));
        countButton.setOnClickListener(v -> updateCount());
        deleteAllButton.setOnClickListener(v -> showDeleteAllConfirmation());

        updateCount();
    }

    private void updateCount() {
        int count = dbAdapter.getCount();
        countTextView.setText(getString(R.string.user_count, count));
    }

    private void showDeleteAllConfirmation() {
        new androidx.appcompat.app.AlertDialog.Builder(this)
                .setTitle(R.string.delete_all)
                .setMessage(R.string.confirm_delete_all)
                .setPositiveButton(R.string.yes, (dialog, which) -> {
                    dbAdapter.deleteAll();
                    updateCount();
                    com.google.android.material.snackbar.Snackbar.make(
                            findViewById(android.R.id.content),
                            R.string.all_users_deleted,
                            com.google.android.material.snackbar.Snackbar.LENGTH_LONG).show();
                })
                .setNegativeButton(R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}