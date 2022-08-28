package com.foxstudent.collectionsandmaps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.foxstudent.collectionsandmaps.databinding.ActivityMainBinding;
import com.google.android.material.tabs.TabLayoutMediator;


public class MainActivity extends AppCompatActivity {

    private StateAdapter stateAdapter;
    private ActivityMainBinding binding;
    private TabLayoutMediator tabLayoutMediator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setFragments();
    }

    private void setFragments() {
        CollectionFragment collectionFragment = new CollectionFragment();
        MapFragment mapFragment = new MapFragment();

        stateAdapter = new StateAdapter(getSupportFragmentManager(), getLifecycle());

        stateAdapter.addFragment(collectionFragment, "Collection");
        stateAdapter.addFragment(mapFragment, "Map");

        binding.viewPager2.setAdapter(stateAdapter);
        tabLayoutMediator = new TabLayoutMediator(binding.tabLayout, binding.viewPager2, (tab, position) -> {
            tab.setText(stateAdapter.getName(position));
        });
        tabLayoutMediator.attach();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
        tabLayoutMediator.detach();
    }
}