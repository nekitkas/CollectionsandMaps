package com.foxstudent.collectionsandmaps.models;

import android.annotation.SuppressLint;

import com.foxstudent.collectionsandmaps.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class BenchmarkCollection implements Benchmark {


    @Override
    public int getSpanCount() {
        return 3;
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

    @SuppressLint("NonConstantResourceId")
    @Override
    public float measureTime(Cell cell, int operations) {
        final List<Integer> list;
        switch (cell.name) {
            case R.string.arrayList:
                list = new ArrayList<>(Collections.nCopies(operations, 0));
                break;
            case R.string.linkedList:
                list = new LinkedList<>(Collections.nCopies(operations, 0));
                break;
            case R.string.copyOnWrite:
                list = new CopyOnWriteArrayList<>(Collections.nCopies(operations, 0));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + cell.name);
        }
        final float result;
        switch (cell.operation) {
            case R.string.addToStart:
                result = listAddInTheBeginning(list, operations);
                break;
            case R.string.addToMiddle:
                result = listAddInTheMiddle(list, operations);
                break;
            case R.string.addToEnd:
                result = listAddInTheEnd(list, operations);
                break;
            case R.string.removeFromStart:
                result = listRemoveInTheBeginning(list, operations);
                break;
            case R.string.removeFromMiddle:
                result = listRemoveInTheMiddle(list, operations);
                break;
            case R.string.removeFromEnd:
                result = listRemoveInTheEnd(list, operations);
                break;
            case R.string.searchIn:
                result = listSearchByValue(list, operations);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + cell.operation);
        }
        return result;
    }

    private List<Integer> getNames() {
        final List<Integer> names = new ArrayList<>();
        names.add(R.string.arrayList);
        names.add(R.string.linkedList);
        names.add(R.string.copyOnWrite);
        return names;
    }

    private List<Integer> getOperations() {
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

    private float track(Runnable runnable) {
        long startTime = System.currentTimeMillis();
        runnable.run();
        long endTime = System.currentTimeMillis();
        return (endTime - startTime) / 1000F;
    }

    private float listAddInTheBeginning(List<Integer> list, int value) {
        return track(() -> {
            for (int i = 0; i < value; i++) {
                list.add(0, 0);
            }
        });
    }

    private float listAddInTheMiddle(List<Integer> list, int value) {
        return track(() -> {
            for (int i = 0; i < value; i++) {
                list.add(list.size() / 2, 0);
            }
        });
    }

    private float listAddInTheEnd(List<Integer> list, int value) {
        return track(() -> {
            for (int i = 0; i < value; i++) {
                list.add(list.size(), 0);
            }
        });
    }

    private float listRemoveInTheBeginning(List<Integer> list, int value) {
        return track(() -> {
            for (int i = 0; i < list.size(); i++) {
                list.remove(0);
            }
        });
    }

    private float listRemoveInTheMiddle(List<Integer> list, int value) {
        return track(() -> {
            for (int i = 0; i < list.size(); i++) {
                list.remove(list.size() / 2);
            }
        });
    }

    private float listRemoveInTheEnd(List<Integer> list, int value) {
        return track(() -> {
            for (int i = 0; i < list.size(); i++) {
                list.remove(list.size() - 1);
            }
        });
    }

    private float listSearchByValue(List<Integer> list, int value) {
        return track(() -> {
            for (int i = 0; i < list.size(); i++) {
                list.get(i);
            }
        });
    }
}
