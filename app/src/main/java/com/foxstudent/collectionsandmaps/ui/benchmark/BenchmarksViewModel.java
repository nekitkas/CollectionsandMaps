package com.foxstudent.collectionsandmaps.ui.benchmark;


import android.annotation.SuppressLint;
import android.os.Handler;

import androidx.annotation.StringRes;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.foxstudent.collectionsandmaps.R;
import com.foxstudent.collectionsandmaps.models.Cell;
import com.foxstudent.collectionsandmaps.models.Collections;
import com.foxstudent.collectionsandmaps.models.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class BenchmarksViewModel extends ViewModel {

    private final static String EMPTY_VALUE = "N/A";
    private final String type;
    private final MutableLiveData<List<Cell>> cells = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isCalculating = new MutableLiveData<>(false);
    private ExecutorService service;

    public BenchmarksViewModel(String args) {
        this.type = args;
    }

    public void onCreate() {
        cells.setValue(createCells(EMPTY_VALUE, false));
    }

    public int getSpanCount() {
        if (type.equals(Constants.COLLECTION.toString())) {
            return 3;
        } else {
            return 2;
        }
    }

    public List<Cell> createCells(String result, boolean isInProgress) {
        final List<Cell> cells = new ArrayList<>();
        final int listSize = type.equals(Constants.COLLECTION.toString()) ? 21 : 6;
        int nameIndex = 0;
        int operationIndex = 0;

        for (int i = 0; i < listSize; i++) {
            cells.add(new Cell(
                    getNames().get(nameIndex),
                    result,
                    getOperations().get(operationIndex),
                    isInProgress));
            nameIndex++;
            if (nameIndex == listSize / getOperations().size()) {
                nameIndex = 0;
                operationIndex++;
            }
        }
        return cells;
    }

    public List<Integer> getNames() {
        final List<Integer> names = new ArrayList<>();
        if (type.equals(Constants.COLLECTION.toString())) {
            names.add(R.string.arrayList);
            names.add(R.string.linkedList);
            names.add(R.string.copyOnWrite);
        } else {
            names.add(R.string.treeMap);
            names.add(R.string.hashMap);
        }
        return names;
    }

    public List<Integer> getOperations() {
        final List<Integer> operations = new ArrayList<>();
        if (type.equals(Constants.COLLECTION.toString())) {
            operations.add(R.string.addToStart);
            operations.add(R.string.addToMiddle);
            operations.add(R.string.addToEnd);
            operations.add(R.string.removeFromStart);
            operations.add(R.string.removeFromMiddle);
            operations.add(R.string.removeFromEnd);
            operations.add(R.string.searchIn);
        } else {
            operations.add(R.string.addTo);
            operations.add(R.string.searchIn);
            operations.add(R.string.removeFrom);
        }
        return operations;
    }

    public void updateCell(int position, float result, boolean isInProgress) {
        final List<Cell> cellList = cells.getValue();
        cellList.set(position, new Cell(
                cellList.get(position).name,
                Float.toString(result),
                cellList.get(position).operation,
                isInProgress));
        cells.postValue(cellList);
    }

    @SuppressLint("NonConstantResourceId")
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

    public void executeBenchmarks(String operation, String threadPool) {
        if (inputIsNumeric(operation) && inputIsNumeric(threadPool)) {
            int operationInput = Integer.parseInt(operation);
            int tasks = type.equals(Constants.COLLECTION.toString()) ? 20 : 5;
            service = Executors.newFixedThreadPool(Integer.parseInt(threadPool));
            AtomicInteger tasksCompleted = new AtomicInteger();

            final Handler handler = new Handler(message -> {
                if (message.what == tasks) {
                    setIsCalculating(false);
                    shutDown();
                }
                return true;
            });
            List<Cell> cellList = cells.getValue();
            for (int i = 0; i < cellList.size(); i++) {
                final int position = i;
                service.submit(() -> {
                    updateCell(position, measureTime(cellList.get(position), operationInput), false);
                    handler.sendEmptyMessage(tasksCompleted.getAndIncrement());
                });
            }
        }
    }

    private boolean inputIsNumeric(String input) {
        if (input == null) {
            return false;
        }
        try {
             Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            return false;
        }
        return true;
    }

    public void shutDown() {
        service.shutdownNow();
    }

    @StringRes
    public int run(String operation, String thread) {
        if (operation.isEmpty() && thread.isEmpty()) {
            return R.string.empty_field;
        } else if (isCalculating.getValue()) {
            setIsCalculating(false);
            shutDown();
            return R.string.calc_stop;
        } else {
            cells.setValue(createCells(null, true));
            setIsCalculating(true);
            executeBenchmarks(operation, thread);
            return R.string.calc_start;
        }
    }

    public LiveData<Boolean> getIsCalculating() {
        return isCalculating;
    }

    public void setIsCalculating(boolean calculating) {
        isCalculating.setValue(calculating);
    }

    public LiveData<List<Cell>> getCells() {
        return cells;
    }
}
