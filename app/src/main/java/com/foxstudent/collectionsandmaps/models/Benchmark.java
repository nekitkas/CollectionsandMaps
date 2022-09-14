package com.foxstudent.collectionsandmaps.models;

import java.util.List;

public interface Benchmark {

    int getSpanCount();

    List<Integer> getNames();

    List<Integer> getOperations();
}
