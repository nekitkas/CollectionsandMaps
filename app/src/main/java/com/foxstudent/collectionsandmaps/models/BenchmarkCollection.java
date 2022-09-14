package com.foxstudent.collectionsandmaps.models;

import android.annotation.SuppressLint;

import com.foxstudent.collectionsandmaps.R;
import com.foxstudent.collectionsandmaps.models.Benchmark;
import com.foxstudent.collectionsandmaps.models.Cell;
import com.foxstudent.collectionsandmaps.models.Collections;

import java.util.ArrayList;
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
        switch (cell.name) {
            case R.string.arrayList:
                switch (cell.operation) {
                    case R.string.addToStart:
                        return Collections.listAddInTheBeginning(new ArrayList<>(), operations);
                    case R.string.addToMiddle:
                        return Collections.listAddInTheMiddle(new ArrayList<>(), operations);
                    case R.string.addToEnd:
                        return Collections.listAddInTheEnd(new ArrayList<>(), operations);
                    case R.string.searchIn:
                        return Collections.listSearchByValue(new ArrayList<>(), operations);
                    case R.string.removeFromStart:
                        return Collections.listRemoveInTheBeginning(new ArrayList<>(), operations);
                    case R.string.removeFromMiddle:
                        return Collections.listRemoveInTheMiddle(new ArrayList<>(), operations);
                    case R.string.removeFromEnd:
                        return Collections.listRemoveInTheEnd(new ArrayList<>(), operations);
                }
            case R.string.linkedList:
                switch (cell.operation) {
                    case R.string.addToStart:
                        return Collections.listAddInTheBeginning(new LinkedList<>(), operations);
                    case R.string.addToMiddle:
                        return Collections.listAddInTheMiddle(new LinkedList<>(), operations);
                    case R.string.addToEnd:
                        return Collections.listAddInTheEnd(new LinkedList<>(), operations);
                    case R.string.searchIn:
                        return Collections.listSearchByValue(new LinkedList<>(), operations);
                    case R.string.removeFromStart:
                        return Collections.listRemoveInTheBeginning(new LinkedList<>(), operations);
                    case R.string.removeFromMiddle:
                        return Collections.listRemoveInTheMiddle(new LinkedList<>(), operations);
                    case R.string.removeFromEnd:
                        return Collections.listRemoveInTheEnd(new LinkedList<>(), operations);
                }
            case R.string.copyOnWrite:
                switch (cell.operation) {
                    case R.string.addToStart:
                        return Collections.listAddInTheBeginning(new CopyOnWriteArrayList<>(), operations);
                    case R.string.addToMiddle:
                        return Collections.listAddInTheMiddle(new CopyOnWriteArrayList<>(), operations);
                    case R.string.addToEnd:
                        return Collections.listAddInTheEnd(new CopyOnWriteArrayList<>(), operations);
                    case R.string.searchIn:
                        return Collections.listSearchByValue(new CopyOnWriteArrayList<>(), operations);
                    case R.string.removeFromStart:
                        return Collections.listRemoveInTheBeginning(new CopyOnWriteArrayList<>(), operations);
                    case R.string.removeFromMiddle:
                        return Collections.listRemoveInTheMiddle(new CopyOnWriteArrayList<>(), operations);
                    case R.string.removeFromEnd:
                        return Collections.listRemoveInTheEnd(new CopyOnWriteArrayList<>(), operations);
                }
            default:
                return 0f;
        }
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
}
