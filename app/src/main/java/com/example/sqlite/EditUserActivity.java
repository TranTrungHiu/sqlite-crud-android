package com.example.sqlite;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sqlite.Adapter.UsersDatabaseAdapter;
import com.example.sqlite.models.User;

public class EditUserActivity extends AppCompatActivity {
    private EditText nameEditText, phoneEditText, emailEditText;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        nameEditText = findViewById(R.id.nameEditText);
        phoneEditText = findViewById(R.id.phoneEditText);
        emailEditText = findViewById(R.id.emailEditText);
        Button saveButton = findViewById(R.id.saveButton);

        user = (User) getIntent().getSerializableExtra("user");
        nameEditText.setText(user.getUsername());
        phoneEditText.setText(user.getPhone());
        emailEditText.setText(user.getEmail());

        saveButton.setOnClickListener(v -> {
            user.setUsername(nameEditText.getText().toString());
            user.setPhone(phoneEditText.getText().toString());
            user.setEmail(emailEditText.getText().toString());
            UsersDatabaseAdapter dbAdapter = new UsersDatabaseAdapter(this);
            dbAdapter.updateEntry(user);
            finish();
        });
    }
}