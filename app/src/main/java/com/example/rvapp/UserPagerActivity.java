package com.example.rvapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.rvapp.database.UserRepository;

public class UserPagerActivity extends AppCompatActivity {
    private ViewPager viewPager;
    UserRepository users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_pager);
        viewPager = findViewById(R.id.userViewPager);
        FragmentManager fragmentManager = getSupportFragmentManager();
        users = new UserRepository(UserPagerActivity.this);
        int pos = getIntent().getIntExtra("adapterPosition",0);
        viewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                User user = users.getUserList().get(position);
                Fragment fragment = new UserInfoFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("user", user);
                fragment.setArguments(bundle);
                return fragment;
            }

            @Override
            public int getCount() {
                return users.getUserList().size();
            }
        });
        viewPager.setCurrentItem(pos);
    }
}