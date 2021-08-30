package com.example.rvapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rvapp.database.UserRepository;

import java.util.ArrayList;

public class UserListFragment extends Fragment {
    RecyclerView mRecyclerView;
    ArrayList<User> userList = new ArrayList<>();
    Button addBtn;
    private UserAdapter userAdapter;
    UserRepository mUserRepository;
    int tempSize;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = layoutInflater.inflate(R.layout.fragment_user_list, viewGroup, false);
        mUserRepository = new UserRepository(getActivity());
        addBtn = view.findViewById(R.id.addBtn);
        mRecyclerView = view.findViewById(R.id.rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity())); //устанавливаем LayoutManager - способ отображения списка
        recyclerViewInit();
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddUserActivity.class);
                tempSize = userList.size();
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!userList.equals(mUserRepository.getUserList())) {
            userList.clear();
            userList.addAll(mUserRepository.getUserList());
            userAdapter.notifyDataSetChanged();
            getActivity();
        }
    }

    private void recyclerViewInit() {
        userList = mUserRepository.getUserList();
        userAdapter = new UserAdapter(userList);
        mRecyclerView.setAdapter(userAdapter);
    }
}