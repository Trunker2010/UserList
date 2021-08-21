package com.example.rvapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    ArrayList<String> userList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (int i = 0; i < 100; i++) {
            userList.add("Пользователь " + i);
        }
        mRecyclerView = findViewById(R.id.rv);//находим наш RecyclerView
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this)); //устанавливаем LayoutManager - способ отображения списка
        mRecyclerView.setAdapter(new UserAdapter(userList)); // устанавливаем адапетер
    }
}

class UserHolder extends RecyclerView.ViewHolder implements View.OnClickListener { // Создаем класс, который будет создавать элементы RecyclerView
    private TextView userNameTV;
    private String name;

    public UserHolder(LayoutInflater inflater, ViewGroup viewGroup) { //конструктор для создания холдера
        super(inflater.inflate(R.layout.user_lauout, viewGroup, false)); //создаем элемент для RecyclerView
        userNameTV = itemView.findViewById(R.id.userNameTV); //инициализируем TextView
        itemView.setOnClickListener(this);
    }

    void bind(String useName) { //Функция для привязки данных из адаптера
        name = useName;
        userNameTV.setText(name); //устанавливаем значение в userNameTV

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(view.getContext(), UserInfoActivity.class);
        intent.putExtra(Constants.USER_NAME_KEY, name);
        view.getContext().startActivity(intent);
    }
}

class UserAdapter extends RecyclerView.Adapter<UserHolder> { // класс адаптер, для связи данных и их отображения
    ArrayList<String> userList;

    public UserAdapter(ArrayList<String> userList) { //конструктор для создания адаптера, принимает список для отображения
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