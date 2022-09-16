package com.foxstudent.collectionsandmaps.models;

import com.foxstudent.collectionsandmaps.ui.benchmark.BenchmarksVMFactory;

import dagger.Component;

@Component(modules = BenchmarkModule.class)
public interface BenchmarkComponent {
    void inject(BenchmarksVMFactory factory);
}
