package com.foxstudent.collectionsandmaps;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BenchmarksViewModel extends AndroidViewModel {

    private MutableLiveData<List<Cell>> collectionCell;
    private MutableLiveData<List<Cell>> mapCell;
    private MutableLiveData<String> inputValue;
    private MutableLiveData<String> threadValue;
    private final List<Cell> mapCells = new ArrayList<>();
    private final List<Cell> collectionCells = new ArrayList<>();
    private final List<String> mapNames = new ArrayList<>();
    private final List<String> collectionNames = new ArrayList<>();
    private final static String EMPTY_VALUE = "N/A";
    private final ConcurrentHashMap<Integer, String> mapResult = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Integer, String> collectionResult = new ConcurrentHashMap<>();
    private ExecutorService service;

    public BenchmarksViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Cell>> getMapCell() {
        if (mapCell == null) {
            mapCell = new MutableLiveData<>();
            setMapCell();
            mapCell.setValue(mapCells);
        }
        return mapCell;
    }

    public LiveData<List<Cell>> getCollectionCell() {
        if (collectionCell == null) {
            collectionCell = new MutableLiveData<>();
            setCollectionCell();
            collectionCell.setValue(collectionCells);
        }
        return collectionCell;
    }

    public void setMapCell() {
        if (mapNames.isEmpty()) {
            setMapNames();
        }
        for (int i = 0; i < 6; i++) {
            mapCells.add(new Cell(mapNames.get(i) + EMPTY_VALUE));
        }
    }

    public void setCollectionCell() {
        if (collectionNames.isEmpty()) {
            setCollectionName();
        }
        for (int i = 0; i < 21; i++) {
            collectionCells.add(new Cell(collectionNames.get(i) + EMPTY_VALUE));
        }
    }

    public void updateMapCell(int key) {
        mapCells.remove(key);
        Cell newCell = new Cell(mapNames.get(key) + mapResult.get(key));
        mapCells.add(key, newCell);
        mapCell.postValue(mapCells);
    }

    public void updateCollectionCell(int key) {
        collectionCells.remove(key);
        Cell newCell = new Cell(collectionNames.get(key) + collectionResult.get(key));
        collectionCells.add(key, newCell);
        collectionCell.postValue(collectionCells);
    }

    public LiveData<String> getOperationInput() {
        if (inputValue == null) {
            inputValue = new MutableLiveData<>();
            inputValue.setValue("");
        }
        return inputValue;
    }

    public LiveData<String> getThreadInput() {
        if (threadValue == null) {
            threadValue = new MutableLiveData<>();
            threadValue.setValue("");
        }
        return threadValue;
    }

    public void setInputValue(String input) {
        inputValue.setValue(input);
    }

    public void setThreadValue(String input) {
        threadValue.setValue(input);
    }


    public void setCollectionData() {
        int value = Integer.parseInt(Objects.requireNonNull(inputValue.getValue()));
        service = Executors.newFixedThreadPool(Integer.parseInt(Objects.requireNonNull(threadValue.getValue())));
        service.execute(() -> {
            collectionResult.put(0, Collections.listAddInTheBeginning(new ArrayList<>(), value));
            updateCollectionCell(0);
        });
        service.execute(() -> {
            collectionResult.put(1, Collections.listAddInTheBeginning(new LinkedList<>(), value));
            updateCollectionCell(1);
        });
        service.execute(() -> {
            collectionResult.put(2, Collections.listAddInTheBeginning(new CopyOnWriteArrayList<>(), value));
            updateCollectionCell(2);
        });
        service.execute(() -> {
            collectionResult.put(3, Collections.listAddInTheMiddle(new ArrayList<>(), value));
            updateCollectionCell(3);
        });
        service.execute(() -> {
            collectionResult.put(4, Collections.listAddInTheMiddle(new LinkedList<>(), value));
            updateCollectionCell(4);
        });
        service.execute(() -> {
            collectionResult.put(5, Collections.listAddInTheMiddle(new CopyOnWriteArrayList<>(), value));
            updateCollectionCell(5);
        });
        service.execute(() -> {
            collectionResult.put(6, Collections.listAddInTheEnd(new ArrayList<>(), value));
            updateCollectionCell(6);
        });
        service.execute(() -> {
            collectionResult.put(7, Collections.listAddInTheEnd(new LinkedList<>(), value));
            updateCollectionCell(7);
        });
        service.execute(() -> {
            collectionResult.put(8, Collections.listAddInTheEnd(new CopyOnWriteArrayList<>(), value));
            updateCollectionCell(8);
        });
        service.execute(() -> {
            collectionResult.put(9, Collections.listRemoveInTheBeginning(new ArrayList<>(), value));
            updateCollectionCell(9);
        });
        service.execute(() -> {
            collectionResult.put(10, Collections.listRemoveInTheBeginning(new LinkedList<>(), value));
            updateCollectionCell(10);
        });
        service.execute(() -> {
            collectionResult.put(11, Collections.listRemoveInTheBeginning(new CopyOnWriteArrayList<>(), value));
            updateCollectionCell(11);
        });
        service.execute(() -> {
            collectionResult.put(12, Collections.listRemoveInTheMiddle(new ArrayList<>(), value));
            updateCollectionCell(12);
        });
        service.execute(() -> {
            collectionResult.put(13, Collections.listRemoveInTheMiddle(new LinkedList<>(), value));
            updateCollectionCell(13);
        });
        service.execute(() -> {
            collectionResult.put(14, Collections.listRemoveInTheMiddle(new CopyOnWriteArrayList<>(), value));
            updateCollectionCell(14);
        });
        service.execute(() -> {
            collectionResult.put(15, Collections.listRemoveInTheEnd(new ArrayList<>(), value));
            updateCollectionCell(15);
        });
        service.execute(() -> {
            collectionResult.put(16, Collections.listRemoveInTheEnd(new LinkedList<>(), value));
            updateCollectionCell(16);
        });
        service.execute(() -> {
            collectionResult.put(17, Collections.listRemoveInTheEnd(new CopyOnWriteArrayList<>(), value));
            updateCollectionCell(17);
        });
        service.execute(() -> {
            collectionResult.put(18, Collections.listSearchByValue(new ArrayList<>(), value));
            updateCollectionCell(18);
        });
        service.execute(() -> {
            collectionResult.put(19, Collections.listSearchByValue(new LinkedList<>(), value));
            updateCollectionCell(19);
        });
        service.execute(() -> {
            collectionResult.put(20, Collections.listSearchByValue(new CopyOnWriteArrayList<>(), value));
            updateCollectionCell(20);
        });
    }


    public void setMapData() {
        int value = Integer.parseInt(Objects.requireNonNull(inputValue.getValue()));
        service = Executors.newFixedThreadPool(Integer.parseInt(Objects.requireNonNull(threadValue.getValue())));
        service.execute(() -> {
            mapResult.put(0, Collections.mapAddingNew(new TreeMap<>(), value));
            updateMapCell(0);
        });
        service.execute(() -> {
            mapResult.put(1, Collections.mapAddingNew(new HashMap<>(), value));
            updateMapCell(1);
        });
        service.execute(() -> {
            mapResult.put(2, Collections.mapSearchByKey(new TreeMap<>(), value));
            updateMapCell(2);
        });
        service.execute(() -> {
            mapResult.put(3, Collections.mapSearchByKey(new HashMap<>(), value));
            updateMapCell(3);
        });
        service.execute(() -> {
            mapResult.put(4, Collections.mapRemoving(new TreeMap<>(), value));
            updateMapCell(4);
        });
        service.execute(() -> {
            mapResult.put(5, Collections.mapRemoving(new HashMap<>(), value));
            updateMapCell(5);
        });
    }

    public void shutDown() {
        service.shutdown();
        try {
            if (!service.awaitTermination(0, TimeUnit.SECONDS)) {
                service.shutdownNow();
                if (!service.awaitTermination(0, TimeUnit.SECONDS))
                    System.err.println(getApplication().getString(R.string.service_fail));
            }
        } catch (InterruptedException ie) {
            service.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    private void setMapNames() {
        mapNames.add(getApplication().getString(R.string.treeMapAddToStart));
        mapNames.add(getApplication().getString(R.string.hashMapAddToStart));
        mapNames.add(getApplication().getString(R.string.searchInTreeMap));
        mapNames.add(getApplication().getString(R.string.searchInHashMap));
        mapNames.add(getApplication().getString(R.string.removeFromThreeMap));
        mapNames.add(getApplication().getString(R.string.removeFromHashMap));
    }

    private void setCollectionName() {
        collectionNames.add(getApplication().getString(R.string.arrayAddToStart));
        collectionNames.add(getApplication().getString(R.string.linkedListAddToStart));
        collectionNames.add(getApplication().getString(R.string.copyOnWriteAddToStart));
        collectionNames.add(getApplication().getString(R.string.arrayAddToMiddle));
        collectionNames.add(getApplication().getString(R.string.linkedListAddToMiddle));
        collectionNames.add(getApplication().getString(R.string.copyOnWriteAddToMiddle));
        collectionNames.add(getApplication().getString(R.string.arrayAddToEnd));
        collectionNames.add(getApplication().getString(R.string.linkedListAddToEnd));
        collectionNames.add(getApplication().getString(R.string.copyOnWriteAddToEnd));
        collectionNames.add(getApplication().getString(R.string.arrayRemoveFromStart));
        collectionNames.add(getApplication().getString(R.string.linkedListRemoveFromStart));
        collectionNames.add(getApplication().getString(R.string.copyOnWriteRemoveFromStart));
        collectionNames.add(getApplication().getString(R.string.arrayRemoveFromMiddle));
        collectionNames.add(getApplication().getString(R.string.linkedListRemoveFromMiddle));
        collectionNames.add(getApplication().getString(R.string.copyOnWriteRemoveFromMiddle));
        collectionNames.add(getApplication().getString(R.string.arrayRemoveFromEnd));
        collectionNames.add(getApplication().getString(R.string.linkedListRemoveFromEnd));
        collectionNames.add(getApplication().getString(R.string.copyOnWriteRemoveFromEnd));
        collectionNames.add(getApplication().getString(R.string.searchInArray));
        collectionNames.add(getApplication().getString(R.string.searchInLinkedList));
        collectionNames.add(getApplication().getString(R.string.searchInCopyOnWrite));
    }
}