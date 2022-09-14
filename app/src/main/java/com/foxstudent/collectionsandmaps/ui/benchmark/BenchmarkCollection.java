package com.foxstudent.collectionsandmaps.ui.benchmark;

import com.foxstudent.collectionsandmaps.R;
import com.foxstudent.collectionsandmaps.models.Benchmark;

import java.util.ArrayList;
import java.util.List;

public class BenchmarkCollection implements Benchmark {


    @Override
    public int getSpanCount() {
        return 3;
    }

    @Override
    public List<Integer> getNames() {
        final List<Integer> names = new ArrayList<>();
        names.add(R.string.arrayList);
        names.add(R.string.linkedList);
        names.add(R.string.copyOnWrite);
        return names;
    }

    @Override
    public List<Integer> getOperations() {
        final List<Integer> operations = new ArrayList<>();
        operations.add(R.string.addToStart);
        operations.add(R.string.addToMiddle);
        operations.add(R.string.addToEnd);
        operations.add(R.string.removeFromStart);
        operations.add(R.string.removeFromMiddle);
        operations.add(R.string.removeFromEnd);
        operations.add(R.string.searchIn);
        return operations;
    }
}
