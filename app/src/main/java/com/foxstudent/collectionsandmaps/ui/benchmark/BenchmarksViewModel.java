package com.foxstudent.collectionsandmaps.ui.benchmark;


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
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class BenchmarksViewModel extends ViewModel {

    private final static String EMPTY_VALUE = "N/A";
    private final String type;
    private final ConcurrentHashMap<Integer, Float> results = new ConcurrentHashMap<>();
    private final MutableLiveData<List<Cell>> cells = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isCalculating = new MutableLiveData<>(false);
    private ExecutorService service;


    public BenchmarksViewModel(String args) {
        this.type = args;
    }

    public LiveData<List<Cell>> getCells() {
        return cells;
    }

    public List<Cell> createCells(String result, boolean isInProgress) {
        final List<Cell> cells = new ArrayList<>();
        if (type.equals(Constants.COLLECTION.toString())) {
            for (int i = 0; i < 21; i++) {
                cells.add(new Cell(getNames().get(i), result, isInProgress));
            }
        } else {
            for (int i = 0; i < 6; i++) {
                cells.add(new Cell(getNames().get(i), result, isInProgress));
            }
        }
        return cells;
    }

    public void updateCell(int position, boolean isInProgress) {
        List<Cell> cellList = cells.getValue();
        cellList.set(position, new Cell(getNames().get(position), results.get(position).toString(), isInProgress));
        cells.postValue(cellList);
    }

    public void executeBenchmarks(String operation, String thread) {
        /*List<Cell> cellList = cells.getValue();
        for (Cell cell : cellList) {
            service.submit(() ->{

            });
        }*/

        int value = Integer.parseInt((operation));
        service = Executors.newFixedThreadPool(Integer.parseInt((thread)));
        AtomicInteger tasksCompleted = new AtomicInteger();

        if (type.equals(Constants.COLLECTION.toString())) {
            Handler handler = new Handler(message -> {
                if (message.what == 20) {
                    setIsCalculating(false);
                    shutDown();
                }
                return true;
            });

            service.execute(() -> {
                results.put(0, Collections.listAddInTheBeginning(new ArrayList<>(), value));
                updateCell(0,false);
                handler.sendEmptyMessage(tasksCompleted.getAndIncrement());
            });
            service.execute(() -> {
                results.put(1, Collections.listAddInTheBeginning(new LinkedList<>(), value));
                updateCell(1,false);
                handler.sendEmptyMessage(tasksCompleted.getAndIncrement());
            });
            service.execute(() -> {
                results.put(2, Collections.listAddInTheBeginning(new CopyOnWriteArrayList<>(), value));
                updateCell(2,false);
                handler.sendEmptyMessage(tasksCompleted.getAndIncrement());
            });
            service.execute(() -> {
                results.put(3, Collections.listAddInTheMiddle(new ArrayList<>(), value));
                updateCell(3,false);
                handler.sendEmptyMessage(tasksCompleted.getAndIncrement());
            });
            service.execute(() -> {
                results.put(4, Collections.listAddInTheMiddle(new LinkedList<>(), value));
                updateCell(4,false);
                handler.sendEmptyMessage(tasksCompleted.getAndIncrement());
            });
            service.execute(() -> {
                results.put(5, Collections.listAddInTheMiddle(new CopyOnWriteArrayList<>(), value));
                updateCell(5,false);
                handler.sendEmptyMessage(tasksCompleted.getAndIncrement());
            });
            service.execute(() -> {
                results.put(6, Collections.listAddInTheEnd(new ArrayList<>(), value));
                updateCell(6,false);
                handler.sendEmptyMessage(tasksCompleted.getAndIncrement());
            });
            service.execute(() -> {
                results.put(7, Collections.listAddInTheEnd(new LinkedList<>(), value));
                updateCell(7,false);
                handler.sendEmptyMessage(tasksCompleted.getAndIncrement());
            });
            service.execute(() -> {
                results.put(8, Collections.listAddInTheEnd(new CopyOnWriteArrayList<>(), value));
                updateCell(8,false);
                handler.sendEmptyMessage(tasksCompleted.getAndIncrement());
            });
            service.execute(() -> {
                results.put(9, Collections.listRemoveInTheBeginning(new ArrayList<>(), value));
                updateCell(9,false);
                handler.sendEmptyMessage(tasksCompleted.getAndIncrement());
            });
            service.execute(() -> {
                results.put(10, Collections.listRemoveInTheBeginning(new LinkedList<>(), value));
                updateCell(10,false);
                handler.sendEmptyMessage(tasksCompleted.getAndIncrement());
            });
            service.execute(() -> {
                results.put(11, Collections.listRemoveInTheBeginning(new CopyOnWriteArrayList<>(), value));
                updateCell(11,false);
                handler.sendEmptyMessage(tasksCompleted.getAndIncrement());
            });
            service.execute(() -> {
                results.put(12, Collections.listRemoveInTheMiddle(new ArrayList<>(), value));
                updateCell(12,false);
                handler.sendEmptyMessage(tasksCompleted.getAndIncrement());
            });
            service.execute(() -> {
                results.put(13, Collections.listRemoveInTheMiddle(new LinkedList<>(), value));
                updateCell(13,false);
                handler.sendEmptyMessage(tasksCompleted.getAndIncrement());
            });
            service.execute(() -> {
                results.put(14, Collections.listRemoveInTheMiddle(new CopyOnWriteArrayList<>(), value));
                updateCell(14,false);
                handler.sendEmptyMessage(tasksCompleted.getAndIncrement());
            });
            service.execute(() -> {
                results.put(15, Collections.listRemoveInTheEnd(new ArrayList<>(), value));
                updateCell(15,false);
                handler.sendEmptyMessage(tasksCompleted.getAndIncrement());
            });
            service.execute(() -> {
                results.put(16, Collections.listRemoveInTheEnd(new LinkedList<>(), value));
                updateCell(16,false);
                handler.sendEmptyMessage(tasksCompleted.getAndIncrement());
            });
            service.execute(() -> {
                results.put(17, Collections.listRemoveInTheEnd(new CopyOnWriteArrayList<>(), value));
                updateCell(17,false);
                handler.sendEmptyMessage(tasksCompleted.getAndIncrement());
            });
            service.execute(() -> {
                results.put(18, Collections.listSearchByValue(new ArrayList<>(), value));
                updateCell(18,false);
                handler.sendEmptyMessage(tasksCompleted.getAndIncrement());
            });
            service.execute(() -> {
                results.put(19, Collections.listSearchByValue(new LinkedList<>(), value));
                updateCell(19,false);
                handler.sendEmptyMessage(tasksCompleted.getAndIncrement());
            });
            service.execute(() -> {
                results.put(20, Collections.listSearchByValue(new CopyOnWriteArrayList<>(), value));
                updateCell(20,false);
                handler.sendEmptyMessage(tasksCompleted.getAndIncrement());
            });
        } else {
            Handler handler = new Handler(message -> {
                if (message.what == 5) {
                    setIsCalculating(false);
                    shutDown();
                }
                return true;
            });
            service.execute(() -> {
                results.put(0, Collections.mapAddingNew(new TreeMap<>(), value));
                updateCell(0,false);
                handler.sendEmptyMessage(tasksCompleted.getAndIncrement());
            });
            service.execute(() -> {
                results.put(1, Collections.mapAddingNew(new HashMap<>(), value));
                updateCell(1,false);
                handler.sendEmptyMessage(tasksCompleted.getAndIncrement());
            });
            service.execute(() -> {
                results.put(2, Collections.mapSearchByKey(new TreeMap<>(), value));
                updateCell(2,false);
                handler.sendEmptyMessage(tasksCompleted.getAndIncrement());
            });
            service.execute(() -> {
                results.put(3, Collections.mapSearchByKey(new HashMap<>(), value));
                updateCell(3,false);
                handler.sendEmptyMessage(tasksCompleted.getAndIncrement());
            });
            service.execute(() -> {
                results.put(4, Collections.mapRemoving(new TreeMap<>(), value));
                updateCell(4,false);
                handler.sendEmptyMessage(tasksCompleted.getAndIncrement());
            });
            service.execute(() -> {
                results.put(5, Collections.mapRemoving(new HashMap<>(), value));
                updateCell(5,false);
                handler.sendEmptyMessage(tasksCompleted.getAndIncrement());
            });
        }
    }

    public void shutDown() {
        service.shutdown();
        try {
            if (!service.awaitTermination(1, TimeUnit.SECONDS)) {
                service.shutdownNow();
                if (!service.awaitTermination(1, TimeUnit.SECONDS)) {
                    System.err.println(R.string.service_fail);
                }
            }
        } catch (InterruptedException ie) {
            service.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    public ArrayList<Integer> getNames() {
        final ArrayList<Integer> names = new ArrayList<>();
        if (type.equals(Constants.COLLECTION.toString())) {
            names.add(R.string.addToStart);
            names.add(R.string.addToStart);
            names.add(R.string.addToStart);
            names.add(R.string.addToMiddle);
            names.add(R.string.addToMiddle);
            names.add(R.string.addToMiddle);
            names.add(R.string.addToEnd);
            names.add(R.string.addToEnd);
            names.add(R.string.addToEnd);
            names.add(R.string.removeFromStart);
            names.add(R.string.removeFromStart);
            names.add(R.string.removeFromStart);
            names.add(R.string.removeFromMiddle);
            names.add(R.string.removeFromMiddle);
            names.add(R.string.removeFromMiddle);
            names.add(R.string.removeFromEnd);
            names.add(R.string.removeFromEnd);
            names.add(R.string.removeFromEnd);
            names.add(R.string.searchIn);
            names.add(R.string.searchIn);
            names.add(R.string.searchIn);
        } else {
            names.add(R.string.addTo);
            names.add(R.string.addTo);
            names.add(R.string.searchIn);
            names.add(R.string.searchIn);
            names.add(R.string.removeFrom);
            names.add(R.string.removeFrom);
        }
        return names;
    }

    public LiveData<Boolean> getIsCalculating() {
        return isCalculating;
    }

    public void setIsCalculating(boolean calculating) {
        isCalculating.setValue(calculating);
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

    public int getSpanCount() {
        if (type.equals(Constants.COLLECTION.toString())) {
            return 3;
        } else {
            return 2;
        }
    }

    public void onCreate() {
        cells.setValue(createCells(EMPTY_VALUE, false));
    }
}
