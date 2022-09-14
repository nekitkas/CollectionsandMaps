package com.foxstudent.collectionsandmaps.ui.benchmark;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.foxstudent.collectionsandmaps.models.BenchmarkCollection;
import com.foxstudent.collectionsandmaps.models.BenchmarkMap;


public class BenchmarksVMFactory implements ViewModelProvider.Factory {

    private final int type;

    BenchmarksVMFactory(int type) {
        this.type = type;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(BenchmarksViewModel.class)) {
            if (type == 0)
                return (T) new BenchmarksViewModel(new BenchmarkCollection());
            else
                return (T) new BenchmarksViewModel(new BenchmarkMap());
        }
        throw new IllegalArgumentException("Failed to create ViewModel");
    }
}
