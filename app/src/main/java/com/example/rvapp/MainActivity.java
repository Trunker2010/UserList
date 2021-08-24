package com.example.rvapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    ArrayList<User> userList = new ArrayList<>();
    Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (int i = 0; i < 100; i++) {
            User user = new User();
            user.setUserName("Имя " + i);
            user.setUserLastName("Фамилия " + i);
            user.setPhone("Телефон");

            userList.add(user);
        }
        mRecyclerView = findViewById(R.id.rv);//находим наш RecyclerView
        addBtn = findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddUserActivity.class);
                startActivity(intent);
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this)); //устанавливаем LayoutManager - способ отображения списка
        mRecyclerView.setAdapter(new UserAdapter(userList)); // устанавливаем адапетер
    }
}

class UserHolder extends RecyclerView.ViewHolder implements View.OnClickListener { // Создаем класс, который будет создавать элементы RecyclerView
    private final TextView userNameTV;
    private final TextView lastNameTV;
    private final TextView phoneTV;


    private User user;

    public UserHolder(LayoutInflater inflater, ViewGroup viewGroup) { //конструктор для создания холдера
        super(inflater.inflate(R.layout.user_lauout, viewGroup, false)); //создаем элемент для RecyclerView
        userNameTV = itemView.findViewById(R.id.userNameTV); //инициализируем TextView
        lastNameTV = itemView.findViewById(R.id.userLastNameTV);
        phoneTV = itemView.findViewById(R.id.userPhoneTV);

        itemView.setOnClickListener(this);
    }

    void bind(User user) { //Функция для привязки данных из адаптера
        this.user = user;
        userNameTV.setText(user.getUserName()); //устанавливаем значение в userNameTV
        lastNameTV.setText(user.getUserLastName());
        phoneTV.setText(user.getPhone());
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(view.getContext(), UserInfoActivity.class);
        intent.putExtra(Constants.USER_KEY, user);
        view.getContext().startActivity(intent);
    }
}

class UserAdapter extends RecyclerView.Adapter<UserHolder> { // класс адаптер, для связи данных и их отображения
    ArrayList<User> userList;

    public UserAdapter(ArrayList<User> userList) { //конструктор для создания адаптера, принимает список для отображения
        this.userList = userList;//устанавливаем значение списку, который будет использоваться адаптером
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { //метод создающий холдер для дальнейшего его заполнения
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());// Получаем инфлейтер для использования в холдере
        return new UserHolder(inflater, parent);//создаем холдер
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int position) { //передаем данные в холдер для заполнения
        holder.bind(userList.get(position));
    }

    @Override
    public int getItemCount() {//указываем количество элементов в списке
        return userList.size();
    }
}