package com.example.mybirthandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import java.util.ArrayList;

public class Activity_Fragment_ViewPager extends AppCompatActivity {
    public ViewPager2 vp2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_view_pager);
        vp2 = findViewById(R.id.f_vp_text);
        ArrayList<BackInterface> fragments= new ArrayList<>();
        fragments.add(new BackInterface() {
            @Override
            public Fragment back() {
                return new Fragment1();
            }
        });
        fragments.add(() -> new Fragment2());
        Adapter_Fragment adapter_fragment = new Adapter_Fragment(this,fragments);
        vp2.setAdapter(adapter_fragment);
    }
}