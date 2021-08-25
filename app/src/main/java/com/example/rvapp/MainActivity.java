package com.example.rvapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rvapp.database.UserRepository;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    ArrayList<User> userList = new ArrayList<>();
    Button addBtn;
    private UserAdapter userAdapter;
    UserRepository mUserRepository;
    int tempSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUserRepository = new UserRepository(MainActivity.this);
        mRecyclerView = findViewById(R.id.rv);//находим наш RecyclerView
        addBtn = findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddUserActivity.class);
                tempSize = userList.size();
                startActivity(intent);
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this)); //устанавливаем LayoutManager - способ отображения списка
        recyclerViewInit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!userList.equals(mUserRepository.getUserList())) {
            userList.clear();
            userList.addAll(mUserRepository.getUserList());
            userAdapter.notifyDataSetChanged();
        }
    }

    private void recyclerViewInit() {
        userList = mUserRepository.getUserList();
        userAdapter = new UserAdapter(userList);
        mRecyclerView.setAdapter(userAdapter);
    }
}

