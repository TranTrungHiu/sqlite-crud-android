package com.example.sqlite;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sqlite.Adapter.UsersDatabaseAdapter;

public class InsertRowActivity extends AppCompatActivity {
    private EditText nameEditText, phoneEditText, emailEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_row);

        nameEditText = findViewById(R.id.nameEditText);
        phoneEditText = findViewById(R.id.phoneEditText);
        emailEditText = findViewById(R.id.emailEditText);
        Button saveButton = findViewById(R.id.saveButton);

        saveButton.setOnClickListener(v -> {
            UsersDatabaseAdapter dbAdapter = new UsersDatabaseAdapter(this);
            String name = nameEditText.getText().toString();
            String phone = phoneEditText.getText().toString();
            String email = emailEditText.getText().toString();
            dbAdapter.insertEntry(name, phone, email);
            finish();
        });
    }
}