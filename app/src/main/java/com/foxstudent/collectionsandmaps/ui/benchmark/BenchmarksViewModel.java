package com.foxstudent.collectionsandmaps.ui.benchmark;

import android.annotation.SuppressLint;
import android.os.Handler;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.foxstudent.collectionsandmaps.R;
import com.foxstudent.collectionsandmaps.models.Benchmark;
import com.foxstudent.collectionsandmaps.models.Cell;
import com.foxstudent.collectionsandmaps.models.Collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;


public class BenchmarksViewModel extends ViewModel {

    private final MutableLiveData<List<Cell>> cells = new MutableLiveData<>();
    private final MutableLiveData<Integer> toastMessage = new MutableLiveData<>();
    private final Handler handler = new Handler();
    private final Benchmark benchmark;
    private ThreadPoolExecutor service;

    public BenchmarksViewModel(Benchmark benchmark) {
        this.benchmark = benchmark;
    }

    public void onCreate() {
        cells.setValue(createCells(0, false));
        setToastMessage(0);
    }

    public int getSpanCount() {
        return benchmark.getSpanCount();
    }

    public List<Cell> createCells(float result, boolean isInProgress) {
        final List<Cell> cells = new ArrayList<>();
        final List<Integer> names = benchmark.getNames();
        final List<Integer> operations = benchmark.getOperations();

        for (int operation : operations) {
            for (int name : names) {
                cells.add(new Cell(name, String.valueOf(result), operation, isInProgress));
            }
        }
        return cells;
    }

    public void updateCell(int position, float result, boolean isInProgress) {
        final List<Cell> cellList = cells.getValue();
        cellList.set(position, new Cell(
                cellList.get(position).name,
                String.valueOf(result),
                cellList.get(position).operation,
                isInProgress));
        cells.setValue(cellList);
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

    public void executeBenchmarks(int operation, int threadPool) {
        service = (ThreadPoolExecutor) Executors.newFixedThreadPool(threadPool);

        final List<Cell> cellList = cells.getValue();
        for (Cell cell : cellList) {
            service.submit(() -> {
                final float result = measureTime(cell, operation);
                handler.post(() -> updateCell(cellList.indexOf(cell), result, false));
                if (service.getCompletedTaskCount() == cellList.size() - 1) {
                    handler.post(() -> setToastMessage(R.string.calc_complete));
                }
            });
        }
        service.shutdown();
    }

    public void hideProgressBar() {
        final List<Cell> cellList = cells.getValue();
        for (int i = 0; i < cellList.size(); i++) {
            cellList.set(i, new Cell(cellList.get(i).name, cellList.get(i).result, cellList.get(i).operation, false));
        }
        cells.setValue(cellList);
    }

    public void shutDown() {
        service.shutdownNow();
        hideProgressBar();
        setToastMessage(R.string.calc_stop);
    }

    public void onButtonPressed(String operation, String threadPool) {
        if (service != null && service.getActiveCount() > 0) {
            shutDown();
            return;
        }
        if (operation.isEmpty() || threadPool.isEmpty()) {
            setToastMessage(R.string.empty_field);
        } else {
            int operationToInt, threadPoolToInt;
            try {
                operationToInt = Integer.parseInt(operation);
                threadPoolToInt = Integer.parseInt(threadPool);
            } catch (NumberFormatException exception) {
                setToastMessage(R.string.must_be_numeric);
                return;
            }
            if (operationToInt <= 0 || threadPoolToInt <= 0) {
                setToastMessage(R.string.must_be_positive);
            } else {
                executeBenchmarks(operationToInt, threadPoolToInt);
                cells.setValue(createCells(0, true));
                setToastMessage(R.string.calc_start);
            }
        }
    }

    public LiveData<List<Cell>> getCells() {
        return cells;
    }

    public LiveData<Integer> getToastMessage() {
        return toastMessage;
    }

    public void setToastMessage(int message) {
        toastMessage.setValue(message);
    }
}
