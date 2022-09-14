package com.foxstudent.collectionsandmaps.models;

import android.annotation.SuppressLint;

import com.foxstudent.collectionsandmaps.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public class BenchmarkMap implements Benchmark {


    @Override
    public int getSpanCount() {
        return 2;
    }

    @Override
    public List<Cell> createCells(float result, boolean isInProgress) {
        final List<Cell> cells = new ArrayList<>();
        final List<Integer> names = getNames();
        final List<Integer> operations = getOperations();

        for (int operation : operations) {
            for (int name : names) {
                cells.add(new Cell(name, String.valueOf(result), operation, isInProgress));
            }
        }
        return cells;
    }

    @Override
    @SuppressLint("NonConstantResourceId")
    public float measureTime(Cell cell, int operations) {
        switch (cell.name) {
            case R.string.treeMap:
                switch (cell.operation) {
                    case R.string.addTo:
                        return Collections.mapAddingNew(new TreeMap<>(), operations);
                    case R.string.removeFrom:
                        return Collections.mapRemoving(new TreeMap<>(), operations);
                    case R.string.searchIn:
                        return Collections.mapSearchByKey(new TreeMap<>(), operations);
                }
            case R.string.hashMap:
                switch (cell.operation) {
                    case R.string.addTo:
                        return Collections.mapAddingNew(new HashMap<>(), operations);
                    case R.string.removeFrom:
                        return Collections.mapRemoving(new HashMap<>(), operations);
                    case R.string.searchIn:
                        return Collections.mapSearchByKey(new HashMap<>(), operations);
                }
            default:
                return 0f;
        }
    }

    private List<Integer> getNames() {
        final List<Integer> names = new ArrayList<>();
        names.add(R.string.treeMap);
        names.add(R.string.hashMap);
        return names;
    }

    private List<Integer> getOperations() {
        final List<Integer> operations = new ArrayList<>();
        operations.add(R.string.addTo);
        operations.add(R.string.searchIn);
        operations.add(R.string.removeFrom);
        return operations;
    }
}
