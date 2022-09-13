package com.foxstudent.collectionsandmaps.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.foxstudent.collectionsandmaps.R;
import com.foxstudent.collectionsandmaps.databinding.ActivityMainBinding;
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
        StateAdapter stateAdapter = new StateAdapter(getSupportFragmentManager(), getLifecycle());
        binding.viewPager2.setAdapter(stateAdapter);
        tabLayoutMediator = new TabLayoutMediator(binding.tabLayout, binding.viewPager2, (tab, position) ->
                tab.setText(getResources().getStringArray(R.array.fragmentNames)[position]));
        tabLayoutMediator.attach();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
        tabLayoutMediator.detach();
    }
}
