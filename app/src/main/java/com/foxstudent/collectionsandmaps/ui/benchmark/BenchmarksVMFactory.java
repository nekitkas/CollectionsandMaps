package com.foxstudent.collectionsandmaps.ui.benchmark;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;

public class BenchmarksVMFactory implements ViewModelProvider.Factory {

    private final Application application;
    private final ArrayList<String> arg;

    BenchmarksVMFactory(Application application, ArrayList<String> arg){
        this.arg = arg;
        this.application = application;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(BenchmarksViewModel.class)){
            return (T) new BenchmarksViewModel(application,arg);
        }
        throw new IllegalArgumentException("Failed to create ViewModel");
    }
}
