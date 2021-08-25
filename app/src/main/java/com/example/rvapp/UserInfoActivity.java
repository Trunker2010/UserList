package com.example.rvapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.rvapp.database.UserRepository;

public class UserInfoActivity extends AppCompatActivity implements View.OnClickListener {
    TextView nameTextView, lNameTextView, phoneTexView;
    Button deleteUserBrn, editUserBtn;
    User mUser;
    UserRepository mUserRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        mUser = (User) getIntent().getSerializableExtra(Constants.USER_KEY);
        nameTextView = findViewById(R.id.userNameTV);
        lNameTextView = findViewById(R.id.userLNameTV);
        phoneTexView = findViewById(R.id.userPhoneTV);
        deleteUserBrn = findViewById(R.id.dellBtn);
        editUserBtn = findViewById(R.id.editBtn);
        nameTextView.setText(mUser.getUserName());
        phoneTexView.setText(mUser.getPhone());
        lNameTextView.setText(mUser.getUserLastName());
        mUserRepository = new UserRepository(this);
        deleteUserBrn.setOnClickListener(this);
        editUserBtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.editBtn) {
            Intent intent = new Intent(UserInfoActivity.this, AddUserActivity.class);
            intent.putExtra(Constants.USER_KEY, mUser);
            startActivity(intent);
        } else if (view.getId() == R.id.dellBtn) {
            mUserRepository.deleteUser(mUser.getUuid());
            onBackPressed();
        }

    }
}