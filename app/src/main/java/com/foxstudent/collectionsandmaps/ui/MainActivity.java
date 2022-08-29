package com.foxstudent.collectionsandmaps.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.foxstudent.collectionsandmaps.R;
import com.foxstudent.collectionsandmaps.databinding.ActivityMainBinding;
import com.foxstudent.collectionsandmaps.ui.benchmark.BenchmarksFragment;
import com.google.android.material.tabs.TabLayoutMediator;


public class MainActivity extends AppCompatActivity {

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
        BenchmarksFragment collectionFragment = BenchmarksFragment.newInstance("collections");
        BenchmarksFragment mapFragment = BenchmarksFragment.newInstance("maps");

        StateAdapter stateAdapter = new StateAdapter(getSupportFragmentManager(), getLifecycle());
        stateAdapter.addFragment(collectionFragment, getString(R.string.collection));
        stateAdapter.addFragment(mapFragment, getString(R.string.map));

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
