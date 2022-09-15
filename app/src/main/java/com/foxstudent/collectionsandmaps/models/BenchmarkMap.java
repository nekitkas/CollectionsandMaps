package com.foxstudent.collectionsandmaps.models;

import android.annotation.SuppressLint;

import com.foxstudent.collectionsandmaps.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class BenchmarkMap implements Benchmark {

    final Random random = new Random();


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
        final Map<Integer, Integer> map;
        switch (cell.name) {
            case R.string.treeMap:
                map = new TreeMap<>();
                break;
            case R.string.hashMap:
                map = new HashMap<>();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + cell.name);
        }
        fillMap(map, operations);
        final float result;
        switch (cell.operation) {
            case R.string.addTo:
                result = mapAddingNew(map);
                break;
            case R.string.removeFrom:
                result = mapRemoving(map);
                break;
            case R.string.searchIn:
                result = mapSearchByKey(map);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + cell.operation);
        }
        return result;
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

    private float track(Runnable runnable) {
        long startTime = System.currentTimeMillis();
        runnable.run();
        long endTime = System.currentTimeMillis();
        return (endTime - startTime) / 1000F;
    }

    private float mapAddingNew(Map<Integer, Integer> map) {
        return track(() -> map.put(0, 0));
    }

    private float mapSearchByKey(Map<Integer, Integer> map) {
        return track(() -> map.get(random.nextInt(map.size() - 1)));
    }

    private float mapRemoving(Map<Integer, Integer> map) {
        return track(() -> map.remove(0));
    }

    private void fillMap(Map<Integer, Integer> map, int value) {
        for (int i = 0; i < value; i++) {
            map.put(i, 0);
        }
    }
}
