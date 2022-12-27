package com.example.mybirthandroid;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class Adapter_Fragment extends FragmentStateAdapter {
    public final ArrayList<BackInterface> fragments;
    public Adapter_Fragment(@NonNull FragmentActivity fragmentActivity,ArrayList<BackInterface> fragments) {
        super(fragmentActivity);
        this.fragments =fragments;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragments.get(position).back();
    }

    @Override
    public int getItemCount() {
        return fragments.size();
    }
}
