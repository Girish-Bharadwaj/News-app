package com.android.application.newsapp.adpaters;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.android.application.newsapp.views.TabFragment;

import java.util.ArrayList;

public class viewPagerAdapter extends FragmentStateAdapter {
    private ArrayList<String> arrayList;

    public void setArrayList(ArrayList<String> arrayList) {
        this.arrayList = arrayList;
    }

    public viewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        TabFragment fragment=new TabFragment();
        Bundle bundle=new Bundle();
        bundle.putString("query",arrayList.get(position).toLowerCase());
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getItemCount() {
            return arrayList.size();
    }
}
