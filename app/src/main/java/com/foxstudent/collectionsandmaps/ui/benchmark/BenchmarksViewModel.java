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
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class BenchmarksViewModel extends ViewModel {

    private final static String EMPTY_VALUE = "N/A";
    private final String type;
    private final MutableLiveData<List<Cell>> cells = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isCalculating = new MutableLiveData<>(false);
    private ThreadPoolExecutor service;

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
        final List<Integer> names = getNames();
        final List<Integer> operations = getOperations();

        for (int operation : operations) {
            for (int name : names) {
                cells.add(new Cell(
                        name,
                        result,
                        operation,
                        isInProgress
                ));
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

    public void updateCell(int position, String result, boolean isInProgress) {
        final List<Cell> cellList = cells.getValue();
        cellList.set(position, new Cell(
                cellList.get(position).name,
                result,
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
        setIsCalculating(true);
        service = (ThreadPoolExecutor) Executors.newFixedThreadPool(Integer.parseInt(threadPool));
        final Handler handler = new Handler();

        List<Cell> cellList = cells.getValue();
        for (Cell cell : cellList) {
            service.submit(() -> {
                updateCell(cellList.indexOf(cell), String.valueOf(measureTime(cell, Integer.parseInt(operation))), false);
                if (service.getCompletedTaskCount() == cellList.size() - 1) {
                    handler.post(this::shutDown);
                }
            });
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

    public void hideProgressBar() {
        final List<Cell> cellList = cells.getValue();
        for (int i = 0; i < cellList.size(); i++) {
            String result = cellList.get(i).result;
            if (result == null) {
                result = EMPTY_VALUE;
            }
            updateCell(i, result, false);
        }
    }

    public void shutDown() {
        setIsCalculating(false);
        service.shutdownNow();
    }

    @StringRes
    private int validateInputs(String operation, String threadPool) {
        if (operation.isEmpty() || threadPool.isEmpty()) {
            return R.string.empty_field;
        } else if (!inputIsNumeric(operation) || !inputIsNumeric(threadPool)) {
            return R.string.must_be_numeric;
        } else if (Integer.parseInt(operation) == 0 || Integer.parseInt(threadPool) == 0) {
            return R.string.must_be_more_than_zero;
        } else if (Integer.parseInt(operation) < 0 || Integer.parseInt(threadPool) < 0) {
            return R.string.must_be_positive;
        }
        return R.string.inputIsValidated;
    }

    @StringRes
    public int onButtonPressed(String operation, String threadPool) {
        int message = validateInputs(operation, threadPool);
        if (message == R.string.inputIsValidated) {
            if (service != null && service.getActiveCount() > 0) {
                shutDown();
                hideProgressBar();
                return R.string.calc_stop;
            } else {
                executeBenchmarks(operation, threadPool);
                cells.setValue(createCells(null, true));
                return R.string.calc_start;
            }
        }
        return message;
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
