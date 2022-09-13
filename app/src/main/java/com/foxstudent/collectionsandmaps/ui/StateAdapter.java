package com.foxstudent.collectionsandmaps.ui;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.foxstudent.collectionsandmaps.ui.benchmark.BenchmarksFragment;

public class StateAdapter extends FragmentStateAdapter {


    public StateAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return BenchmarksFragment.newInstance(position);
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
