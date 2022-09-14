package com.foxstudent.collectionsandmaps.ui.benchmark;

import com.foxstudent.collectionsandmaps.R;
import com.foxstudent.collectionsandmaps.models.Benchmark;

import java.util.ArrayList;
import java.util.List;

public class BenchmarkMap implements Benchmark {


    @Override
    public int getSpanCount() {
        return 2;
    }

    @Override
    public List<Integer> getNames() {
        final List<Integer> names = new ArrayList<>();
        names.add(R.string.treeMap);
        names.add(R.string.hashMap);
        return names;
    }

    @Override
    public List<Integer> getOperations() {
        final List<Integer> operations = new ArrayList<>();
        operations.add(R.string.addTo);
        operations.add(R.string.searchIn);
        operations.add(R.string.removeFrom);
        return operations;
    }
}
