package com.example.rvapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rvapp.database.UserRepository;

public class AddUserActivity extends AppCompatActivity {
    EditText editTextUserName, editTextUserLastName, editTextPhone;
    Button formAddUserBtn;
    UserRepository mUserRepository;
    User mUser = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        editTextPhone = findViewById(R.id.phoneET);
        editTextUserLastName = findViewById(R.id.lNameET);
        editTextUserName = findViewById(R.id.nameET);
        formAddUserBtn = findViewById(R.id.addNewUserBtn);
        mUserRepository = new UserRepository(AddUserActivity.this);

        mUser = (User) getIntent().getSerializableExtra(Constants.USER_KEY);

        if (mUser != null) {
            editTextPhone.setText(mUser.getPhone());
            editTextUserName.setText(mUser.getUserName());
            editTextUserLastName.setText(mUser.getUserLastName());
        }

        formAddUserBtn.setOnClickListener(view -> {
            User user = new User();
            user.setUserName(editTextUserName.getText().toString());
            user.setUserLastName(editTextUserLastName.getText().toString());
            user.setPhone(editTextPhone.getText().toString());
            if (mUser != null) {
                mUser.setUserLastName(editTextUserLastName.getText().toString());
                mUser.setPhone(editTextPhone.getText().toString());
                mUser.setUserName(editTextUserName.getText().toString());
                mUserRepository.updateUser(mUser);
                Intent intent = new Intent(AddUserActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            } else {
                mUserRepository.addUser(user);
                onBackPressed();
            }
        });
    }
}