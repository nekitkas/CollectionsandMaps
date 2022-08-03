package com.foxstudent.collectionsandmaps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.foxstudent.collectionsandmaps.databinding.ActivityMainBinding;
import com.google.android.material.tabs.TabLayoutMediator;


public class MainActivity extends AppCompatActivity {

    private StateAdapter mStateAdapter;
    private ActivityMainBinding activityMainBinding;
    private String inputValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

        MainViewModel model = new ViewModelProvider(this).get(MainViewModel.class);

        model.getInputValue().observe(this,input->{
            activityMainBinding.etInput.setText(input);
        });

        model.getIsVisible().observe(this,isVisible->{
            if(isVisible) {
                activityMainBinding.startLayout.setVisibility(View.GONE);
                activityMainBinding.viewPager2.setVisibility(View.VISIBLE);
                activityMainBinding.buttonPanel.setVisibility(View.VISIBLE);
            }
        });

        CollectionFragment collectionFragment = new CollectionFragment();
        MapFragment mapFragment = new MapFragment();

        mStateAdapter = new StateAdapter(getSupportFragmentManager(),getLifecycle());

        mStateAdapter.addFragment(collectionFragment,"Collection");
        mStateAdapter.addFragment(mapFragment,"Map");

        activityMainBinding.viewPager2.setAdapter(mStateAdapter);
        new TabLayoutMediator(activityMainBinding.tabLayout,activityMainBinding.viewPager2,(tab, position) -> {
            tab.setText(mStateAdapter.getName(position));
        }).attach();

        activityMainBinding.btnCalculate.setOnClickListener(view -> {

            inputValue = activityMainBinding.etInput.getText().toString().trim();
            if(inputValue.isEmpty()){
                Toast.makeText(this,"Enter an integer value",Toast.LENGTH_LONG).show();
                return;
            }else{
                model.setInputValue(inputValue);
            }

            activityMainBinding.startLayout.setVisibility(View.GONE);
            activityMainBinding.viewPager2.setVisibility(View.VISIBLE);
            activityMainBinding.buttonPanel.setVisibility(View.VISIBLE);
            model.setIsVisible(true);
        });

        activityMainBinding.btnClear.setOnClickListener(v -> {
            model.shutDown();
            finish();
            startActivity(getIntent());
            });
        }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activityMainBinding = null;
    }
}