package com.foxstudent.collectionsandmaps.models;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class BenchmarkModule {

    @Provides
    @Named("collection")
    public Benchmark providesBenchmarkCollection() {
        return new BenchmarkCollection();
    }

    @Provides
    @Named("map")
    public Benchmark providesBenchmarkMap() {
        return new BenchmarkMap();
    }
}
