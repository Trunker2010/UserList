package com.example.rvapp;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

class UserHolder extends RecyclerView.ViewHolder implements View.OnClickListener { // Создаем класс, который будет создавать элементы RecyclerView
    private final TextView userNameTV;
    private final TextView lastNameTV;
    private final TextView phoneTV;


    private User user;
    private int pos;

    public UserHolder(LayoutInflater inflater, ViewGroup viewGroup) { //конструктор для создания холдера
        super(inflater.inflate(R.layout.user_lauout, viewGroup, false)); //создаем элемент для RecyclerView
        userNameTV = itemView.findViewById(R.id.userNameTV); //инициализируем TextView
        lastNameTV = itemView.findViewById(R.id.userLastNameTV);
        phoneTV = itemView.findViewById(R.id.userPhoneTV);
        itemView.setOnClickListener(this);
    }

    void bind(User user,int itemCount) { //Функция для привязки данных из адаптера
        this.user = user;
        userNameTV.setText(user.getUserName()); //устанавливаем значение в userNameTV
        lastNameTV.setText(user.getUserLastName());
        phoneTV.setText(user.getPhone());
        pos = itemCount;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(view.getContext(), UserPagerActivity.class);
        intent.putExtra("adapterPosition", pos);
        intent.putExtra(Constants.USER_KEY, user);
        view.getContext().startActivity(intent);
    }
}
