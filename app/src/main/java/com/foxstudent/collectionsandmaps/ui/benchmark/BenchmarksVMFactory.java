package com.foxstudent.collectionsandmaps.ui.benchmark;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.foxstudent.collectionsandmaps.models.Benchmark;
import com.foxstudent.collectionsandmaps.models.BenchmarkComponent;
import com.foxstudent.collectionsandmaps.models.DaggerBenchmarkComponent;

import javax.inject.Inject;
import javax.inject.Named;


public class BenchmarksVMFactory implements ViewModelProvider.Factory {

    private final int type;
    private final BenchmarkComponent component = DaggerBenchmarkComponent.create();

    @Inject
    @Named("collection")
    Benchmark collectionBenchmark;
    @Inject
    @Named("map")
    Benchmark mapBenchmark;

    BenchmarksVMFactory(int type) {
        this.type = type;
        component.inject(this);
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(BenchmarksViewModel.class)) {
            if (type == 0)
                return (T) new BenchmarksViewModel(collectionBenchmark);
            else
                return (T) new BenchmarksViewModel(mapBenchmark);
        }
        throw new IllegalArgumentException("Failed to create ViewModel");
    }
}
