package com.example.rvapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class UserInfoActivity extends AppCompatActivity {
    TextView mTextView;
    User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        mUser = (User) getIntent().getSerializableExtra(Constants.USER_KEY);
        mTextView = findViewById(R.id.userNameTV);
        mTextView.setText(mUser.getUserName());
    }
}