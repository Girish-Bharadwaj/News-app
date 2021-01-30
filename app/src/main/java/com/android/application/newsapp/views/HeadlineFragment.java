package com.android.application.newsapp.views;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.android.application.newsapp.R;
import com.android.application.newsapp.adpaters.onItemClicked;
import com.android.application.newsapp.adpaters.viewPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class HeadlineFragment extends Fragment implements onItemClicked{
TabLayout tabLayout;
ViewPager2 viewPager;
ArrayList<String> titlesList=new ArrayList<>();

    public HeadlineFragment() {
        super(R.layout.fragment_headline);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tabLayout=view.findViewById(R.id.tab_layout);
        viewPager=view.findViewById(R.id.view_pager);
        String[] titles={"India","Business","Entertainment","Health","Sports","Technology"};
        for(int i=0;i<titles.length;i++){
            titlesList.add(titles[i]);

        }
        viewPagerAdapter adapter=new viewPagerAdapter(getActivity());
        adapter.setArrayList(titlesList);
        viewPager.setAdapter(adapter);
        new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(titlesList.get(position));
            }
        }).attach();
    }

}