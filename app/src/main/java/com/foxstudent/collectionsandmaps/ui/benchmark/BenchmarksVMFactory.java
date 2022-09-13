package com.foxstudent.collectionsandmaps.ui.benchmark;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;


public class BenchmarksVMFactory implements ViewModelProvider.Factory {

    private final int arg;

    BenchmarksVMFactory(int arg) {
        this.arg = arg;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(BenchmarksViewModel.class)) {
            return (T) new BenchmarksViewModel(arg);
        }
        throw new IllegalArgumentException("Failed to create ViewModel");
    }
}
