package com.example.rvapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.rvapp.database.UserRepository;

public class UserInfoFragment extends Fragment {
    private User user;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        user = (User) bundle.getSerializable("user");
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.activity_user_info, viewGroup, false);
        TextView userNameTextView = view.findViewById(R.id.userNameTV);
        TextView phoneTextView = view.findViewById(R.id.userPhoneTV);
        TextView userLasName = view.findViewById(R.id.userLNameTV);
        userNameTextView.setText(user.getUserName());
        phoneTextView.setText(user.getPhone());
        userLasName.setText(user.getUserLastName());
        Button editUserDataBtn = view.findViewById(R.id.editBtn);
        Button deleteUserBtn = view.findViewById(R.id.dellBtn);
        editUserDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AddUserActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
                // Открываем активность для редактирования пользователя
                // На активность нужно передать данные пользователя (Используйте сериализацию)
                // На активности реализуем возможность изменить данные пользователя
            }
        });
        deleteUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserRepository users = new UserRepository(getActivity());
                users.deleteUser(user.getUuid());
                getActivity().onBackPressed();
            }
        });
        return view;
    }
}
