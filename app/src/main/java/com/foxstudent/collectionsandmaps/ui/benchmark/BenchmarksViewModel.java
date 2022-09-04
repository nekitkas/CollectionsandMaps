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
    private final ConcurrentHashMap<Integer, String> mapResult = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Integer, String> collectionResult = new ConcurrentHashMap<>();
    private final String fragmentArgs;
    private final MutableLiveData<List<Cell>> collectionCell = new MutableLiveData<>();
    private final MutableLiveData<List<Cell>> mapCell = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isCalculating = new MutableLiveData<>();
    private String inputValue, threadValue;
    private ExecutorService service;


    public BenchmarksViewModel(String args) {
        this.fragmentArgs = args;
    }

    public LiveData<List<Cell>> getCells() {
        if (fragmentArgs.equals(Constants.COLLECTION.toString())) {
            collectionCell.setValue(setCells());
            return collectionCell;
        } else {
            mapCell.setValue(setCells());
            return mapCell;
        }
    }

    public ArrayList<Cell> setCells() {
        ArrayList<Cell> cells = new ArrayList<>();
        if(fragmentArgs.equals(Constants.COLLECTION.toString())){
            for (int i = 0; i < 21; i++) {
                cells.add(new Cell(getNames().get(i), EMPTY_VALUE));
            }
        }else {
            for (int i = 0; i < 6; i++) {
                cells.add(new Cell(getNames().get(i), EMPTY_VALUE));
            }
        }
        return cells;
    }


    public void updateCell(int position) {
        if (fragmentArgs.equals(Constants.COLLECTION.toString())) {
            List<Cell> list = collectionCell.getValue();
            list.remove(position);
            list.add(position, new Cell(getNames().get(position), collectionResult.get(position)));
            collectionCell.postValue(list);
        } else {
            List<Cell> list = mapCell.getValue();
            list.remove(position);
            list.add(position, new Cell(getNames().get(position), mapResult.get(position)));
            mapCell.postValue(list);
        }
    }

    public void setInputs(String operation, String thread) {
        inputValue = operation;
        threadValue = thread;
    }


    public void setData(){
        int value = Integer.parseInt((inputValue));
        service = Executors.newFixedThreadPool(Integer.parseInt((threadValue)));
        AtomicInteger tasksCompleted = new AtomicInteger();

        if(fragmentArgs.equals(Constants.COLLECTION.toString())){
            android.os.Handler handler = new Handler(message -> {
                if (message.what == 20) {
                    setIsCalculating(false);
                    shutDown();
                }
                return true;
            });

            service.execute(() -> {
                collectionResult.put(0, Collections.listAddInTheBeginning(new ArrayList<>(), value));
                updateCell(0);
                handler.sendEmptyMessage(tasksCompleted.getAndIncrement());
            });
            service.execute(() -> {
                collectionResult.put(1, Collections.listAddInTheBeginning(new LinkedList<>(), value));
                updateCell(1);
                handler.sendEmptyMessage(tasksCompleted.getAndIncrement());
            });
            service.execute(() -> {
                collectionResult.put(2, Collections.listAddInTheBeginning(new CopyOnWriteArrayList<>(), value));
                updateCell(2);
                handler.sendEmptyMessage(tasksCompleted.getAndIncrement());
            });
            service.execute(() -> {
                collectionResult.put(3, Collections.listAddInTheMiddle(new ArrayList<>(), value));
                updateCell(3);
                handler.sendEmptyMessage(tasksCompleted.getAndIncrement());
            });
            service.execute(() -> {
                collectionResult.put(4, Collections.listAddInTheMiddle(new LinkedList<>(), value));
                updateCell(4);
                handler.sendEmptyMessage(tasksCompleted.getAndIncrement());
            });
            service.execute(() -> {
                collectionResult.put(5, Collections.listAddInTheMiddle(new CopyOnWriteArrayList<>(), value));
                updateCell(5);
                handler.sendEmptyMessage(tasksCompleted.getAndIncrement());
            });
            service.execute(() -> {
                collectionResult.put(6, Collections.listAddInTheEnd(new ArrayList<>(), value));
                updateCell(6);
                handler.sendEmptyMessage(tasksCompleted.getAndIncrement());
            });
            service.execute(() -> {
                collectionResult.put(7, Collections.listAddInTheEnd(new LinkedList<>(), value));
                updateCell(7);
                handler.sendEmptyMessage(tasksCompleted.getAndIncrement());
            });
            service.execute(() -> {
                collectionResult.put(8, Collections.listAddInTheEnd(new CopyOnWriteArrayList<>(), value));
                updateCell(8);
                handler.sendEmptyMessage(tasksCompleted.getAndIncrement());
            });
            service.execute(() -> {
                collectionResult.put(9, Collections.listRemoveInTheBeginning(new ArrayList<>(), value));
                updateCell(9);
                handler.sendEmptyMessage(tasksCompleted.getAndIncrement());
            });
            service.execute(() -> {
                collectionResult.put(10, Collections.listRemoveInTheBeginning(new LinkedList<>(), value));
                updateCell(10);
                handler.sendEmptyMessage(tasksCompleted.getAndIncrement());
            });
            service.execute(() -> {
                collectionResult.put(11, Collections.listRemoveInTheBeginning(new CopyOnWriteArrayList<>(), value));
                updateCell(11);
                handler.sendEmptyMessage(tasksCompleted.getAndIncrement());
            });
            service.execute(() -> {
                collectionResult.put(12, Collections.listRemoveInTheMiddle(new ArrayList<>(), value));
                updateCell(12);
                handler.sendEmptyMessage(tasksCompleted.getAndIncrement());
            });
            service.execute(() -> {
                collectionResult.put(13, Collections.listRemoveInTheMiddle(new LinkedList<>(), value));
                updateCell(13);
                handler.sendEmptyMessage(tasksCompleted.getAndIncrement());
            });
            service.execute(() -> {
                collectionResult.put(14, Collections.listRemoveInTheMiddle(new CopyOnWriteArrayList<>(), value));
                updateCell(14);
                handler.sendEmptyMessage(tasksCompleted.getAndIncrement());
            });
            service.execute(() -> {
                collectionResult.put(15, Collections.listRemoveInTheEnd(new ArrayList<>(), value));
                updateCell(15);
                handler.sendEmptyMessage(tasksCompleted.getAndIncrement());
            });
            service.execute(() -> {
                collectionResult.put(16, Collections.listRemoveInTheEnd(new LinkedList<>(), value));
                updateCell(16);
                handler.sendEmptyMessage(tasksCompleted.getAndIncrement());
            });
            service.execute(() -> {
                collectionResult.put(17, Collections.listRemoveInTheEnd(new CopyOnWriteArrayList<>(), value));
                updateCell(17);
                handler.sendEmptyMessage(tasksCompleted.getAndIncrement());
            });
            service.execute(() -> {
                collectionResult.put(18, Collections.listSearchByValue(new ArrayList<>(), value));
                updateCell(18);
                handler.sendEmptyMessage(tasksCompleted.getAndIncrement());
            });
            service.execute(() -> {
                collectionResult.put(19, Collections.listSearchByValue(new LinkedList<>(), value));
                updateCell(19);
                handler.sendEmptyMessage(tasksCompleted.getAndIncrement());
            });
            service.execute(() -> {
                collectionResult.put(20, Collections.listSearchByValue(new CopyOnWriteArrayList<>(), value));
                updateCell(20);
                handler.sendEmptyMessage(tasksCompleted.getAndIncrement());
            });
        } else {
            android.os.Handler handler = new Handler(message -> {
                if (message.what == 5) {
                    setIsCalculating(false);
                    shutDown();
                }
                return true;
            });
            service.execute(() -> {
                mapResult.put(0, Collections.mapAddingNew(new TreeMap<>(), value));
                updateCell(0);
                handler.sendEmptyMessage(tasksCompleted.getAndIncrement());
            });
            service.execute(() -> {
                mapResult.put(1, Collections.mapAddingNew(new HashMap<>(), value));
                updateCell(1);
                handler.sendEmptyMessage(tasksCompleted.getAndIncrement());
            });
            service.execute(() -> {
                mapResult.put(2, Collections.mapSearchByKey(new TreeMap<>(), value));
                updateCell(2);
                handler.sendEmptyMessage(tasksCompleted.getAndIncrement());
            });
            service.execute(() -> {
                mapResult.put(3, Collections.mapSearchByKey(new HashMap<>(), value));
                updateCell(3);
                handler.sendEmptyMessage(tasksCompleted.getAndIncrement());
            });
            service.execute(() -> {
                mapResult.put(4, Collections.mapRemoving(new TreeMap<>(), value));
                updateCell(4);
                handler.sendEmptyMessage(tasksCompleted.getAndIncrement());
            });
            service.execute(() -> {
                mapResult.put(5, Collections.mapRemoving(new HashMap<>(), value));
                updateCell(5);
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
        ArrayList<Integer> names = new ArrayList<>();
        if (fragmentArgs.equals(Constants.COLLECTION.toString())) {
            names.add(R.string.arrayAddToStart);
            names.add(R.string.linkedListAddToStart);
            names.add(R.string.copyOnWriteAddToStart);
            names.add(R.string.arrayAddToMiddle);
            names.add(R.string.linkedListAddToMiddle);
            names.add(R.string.copyOnWriteAddToMiddle);
            names.add(R.string.arrayAddToEnd);
            names.add(R.string.linkedListAddToEnd);
            names.add(R.string.copyOnWriteAddToEnd);
            names.add(R.string.arrayRemoveFromStart);
            names.add(R.string.linkedListRemoveFromStart);
            names.add(R.string.copyOnWriteRemoveFromStart);
            names.add(R.string.arrayRemoveFromMiddle);
            names.add(R.string.linkedListRemoveFromMiddle);
            names.add(R.string.copyOnWriteRemoveFromMiddle);
            names.add(R.string.arrayRemoveFromEnd);
            names.add(R.string.linkedListRemoveFromEnd);
            names.add(R.string.copyOnWriteRemoveFromEnd);
            names.add(R.string.searchInArray);
            names.add(R.string.searchInLinkedList);
            names.add(R.string.searchInCopyOnWrite);
        } else {
            names.add(R.string.treeMapAddToStart);
            names.add(R.string.hashMapAddToStart);
            names.add(R.string.searchInTreeMap);
            names.add(R.string.searchInHashMap);
            names.add(R.string.removeFromThreeMap);
            names.add(R.string.removeFromHashMap);
        }
        return names;
    }

    public LiveData<Boolean> getIsCalculating() {
        isCalculating.setValue(false);
        return isCalculating;
    }

    public void setIsCalculating(boolean calculating) {
        isCalculating.setValue(calculating);
    }

    @StringRes
    public int run() {
        if (threadValue.isEmpty() && inputValue.isEmpty()) {
            return R.string.empty_field;
        } else if(isCalculating.getValue()){
            setIsCalculating(false);
            shutDown();
            return R.string.calc_stop;
        }else{
            setIsCalculating(true);
            setData();
            return R.string.calc_start;
        }
    }
}
