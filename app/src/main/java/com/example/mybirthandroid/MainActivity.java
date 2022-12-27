package com.example.mybirthandroid;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private ViewPager2 vpText;
    private TabLayout tabLayout;
    private ArrayList<Character> data = new ArrayList<>();
    {
        data.add('红');
        data.add('岩');
        data.add('网');
        data.add('校');
        data.add('移');
        data.add('动');
        data.add('开');
        data.add('发');
        data.add('部');
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vpText =  findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tl_center);
        vpText.setAdapter(new Adapter_ViewPager(data));
        new TabLayoutMediator(tabLayout, vpText, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
//在这里给Tab设置Text
                tab.setText(data.get(position).toString());
            }
        }).attach();

    }
}