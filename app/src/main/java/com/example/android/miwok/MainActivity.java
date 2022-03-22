/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
// import android.support.v7.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);

        // Find the view pager that will allow the user to swipe between fragments
        ViewPager2 viewPager = (ViewPager2) findViewById(R.id.viewpager);

        // Create an adapter that knows which fragment should be shown on each page
        viewPager.setAdapter(new FixedTabsFragmentStateAdapter(getSupportFragmentManager(), getLifecycle()));

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        List<String> tabTitles = new ArrayList<>();
        tabTitles.add(getString(R.string.category_numbers));
        tabTitles.add(getString(R.string.category_family));
        tabTitles.add(getString(R.string.category_colors));
        tabTitles.add(getString(R.string.category_phrases));

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            tab.setText(tabTitles.get(position));
        }).attach();
    }

    /**
     *  This method sets onClickListener to view and starts respective Activity
     */
    private void setListenerStartActivity(View view, final Class activity) {
        view.setOnClickListener(v -> {
            startActivity(new Intent(this, activity));
        });
    }
}
