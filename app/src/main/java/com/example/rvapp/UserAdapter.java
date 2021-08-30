package com.example.rvapp;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

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
        holder.bind(userList.get(position), position);
    }

    @Override
    public int getItemCount() {//указываем количество элементов в списке
        return userList.size();
    }
}
