package com.example.sqlite;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.sqlite.Adapter.UsersDatabaseAdapter;
import com.example.sqlite.models.User;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

public class EditUserActivity extends AppCompatActivity {
    private TextInputEditText nameEditText, phoneEditText, emailEditText;
    private User user;
    private UsersDatabaseAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        // Thiết lập toolbar
        Toolbar toolbar = findViewById(R.id.topAppBar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());

        nameEditText = findViewById(R.id.nameEditText);
        phoneEditText = findViewById(R.id.phoneEditText);
        emailEditText = findViewById(R.id.emailEditText);
        MaterialButton saveButton = findViewById(R.id.saveButton);
        MaterialButton cancelButton = findViewById(R.id.cancelButton);

        user = (User) getIntent().getSerializableExtra("user");
        nameEditText.setText(user.getUsername());
        phoneEditText.setText(user.getPhone());
        emailEditText.setText(user.getEmail());

        dbAdapter = new UsersDatabaseAdapter(this);

        saveButton.setOnClickListener(v -> {
            String username = nameEditText.getText().toString().trim();
            String phone = phoneEditText.getText().toString().trim();
            String email = emailEditText.getText().toString().trim();

            if (TextUtils.isEmpty(username)) {
                nameEditText.setError(getString(R.string.required_field));
                return;
            }

            user.setUsername(username);
            user.setPhone(phone);
            user.setEmail(email);
            dbAdapter.updateEntry(user);

            Snackbar.make(v, R.string.user_updated, Snackbar.LENGTH_SHORT).show();
            finish();
        });

        cancelButton.setOnClickListener(v -> finish());
    }
}