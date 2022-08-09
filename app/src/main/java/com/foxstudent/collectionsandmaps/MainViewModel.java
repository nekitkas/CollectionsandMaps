package com.foxstudent.collectionsandmaps;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MainViewModel extends ViewModel {

    private MutableLiveData<Boolean> isVisible;
    private MutableLiveData<Map<Integer,String>> collectionData;
    private MutableLiveData<Map<Integer,String>> mapData;
    private MutableLiveData<List<Cell>> collectionCell;
    private MutableLiveData<List<Cell>> mapCell;
    private MutableLiveData<String> inputValue;
    private final ExecutorService service = Executors.newFixedThreadPool(30);
    private String result;


    public LiveData<List<Cell>> getMapCell(){
        if(mapCell == null){
            mapCell = new MutableLiveData<>();
            setMapCell();
        }
        return mapCell;
    }

    public void setMapCell(){
        List<Cell> cells = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            cells.add(new Cell()); // header
            cells.add(new Cell("TreeMap"));
            cells.add(new Cell("HashMap"));
        }
        mapCell.setValue(cells);
    }

    public LiveData<List<Cell>> getCollectionCell(){
        if(collectionCell == null){
            collectionCell = new MutableLiveData<>();
            setCollectionCell();
        }
        return collectionCell;
    }

    public void setCollectionCell(){
        List<Cell> cells = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            cells.add(new Cell()); // header
            cells.add(new Cell("ArrayList"));
            cells.add(new Cell("LinkedList"));
            cells.add(new Cell("CopyOnWrite"));
        }
        collectionCell.setValue(cells);
    }

    public LiveData<String> getInputValue(){
        if(inputValue == null){
            inputValue = new MutableLiveData<>();
            inputValue.setValue("");
        }
        return inputValue;
    }

    public void setInputValue(String input){
        inputValue.setValue(input);
    }

    public LiveData<Boolean> getIsVisible(){
        if(isVisible == null){
            isVisible = new MutableLiveData<>();
            setIsVisible(false);
        }
        return isVisible;
    }

    public void setIsVisible(boolean visible){
        isVisible.setValue(visible);
    }

    public LiveData<Map<Integer,String>> getCollectionData(){
        if(collectionData == null){
            collectionData = new MutableLiveData<>();
            setCollectionData();
        }
        return collectionData;
    }

    public void setCollectionData(){
        ConcurrentHashMap<Integer,String> time = new ConcurrentHashMap<>();
        int value = Integer.parseInt(Objects.requireNonNull(inputValue.getValue()));
            service.execute(() -> {
                result = Collections.listAddInTheBeginning(new ArrayList<>(), value);
                time.put(1, result);
                collectionData.postValue(time);
            });
            service.execute(()->{
                result = Collections.listAddInTheBeginning(new LinkedList<>(),value);
                time.put(2, result);
                collectionData.postValue(time);
            });
            service.execute(()->{
                result = Collections.listAddInTheBeginning(new CopyOnWriteArrayList<>(),value);
                time.put(3, result);
                collectionData.postValue(time);
            });
            service.execute(() -> {
                result = Collections.listAddInTheMiddle(new ArrayList<>(),value);
                time.put(5, result);
                collectionData.postValue(time);
            });
            service.execute(()->{
                result = Collections.listAddInTheMiddle(new LinkedList<>(),value);
                time.put(6, result);
                collectionData.postValue(time);
            });
            service.execute(()->{
                result = Collections.listAddInTheMiddle(new CopyOnWriteArrayList<>(),value);
                time.put(7, result);
                collectionData.postValue(time);
            });
            service.execute(() -> {
                result = Collections.listAddInTheEnd(new ArrayList<>(),value);
                time.put(9, result);
                collectionData.postValue(time);
            });
            service.execute(()->{
                result = Collections.listAddInTheEnd(new LinkedList<>(),value);
                time.put(10, result);
                collectionData.postValue(time);
            });
            service.execute(()->{
                result = Collections.listAddInTheEnd(new CopyOnWriteArrayList<>(),value);
                time.put(11, result);
                collectionData.postValue(time);
            });
            service.execute(() -> {
                result = Collections.listRemoveInTheBeginning(new ArrayList<>(),value);
                time.put(13, result);
                collectionData.postValue(time);
            });
            service.execute(()->{
                result = Collections.listRemoveInTheBeginning(new LinkedList<>(),value);
                time.put(14, result);
                collectionData.postValue(time);
            });
            service.execute(()->{
                result = Collections.listRemoveInTheBeginning(new CopyOnWriteArrayList<>(),value);
                time.put(15, result);
                collectionData.postValue(time);
            });
            service.execute(() -> {
                result = Collections.listRemoveInTheMiddle(new ArrayList<>(),value);
                time.put(17, result);
                collectionData.postValue(time);
            });
            service.execute(()->{
                result = Collections.listRemoveInTheMiddle(new LinkedList<>(),value);
                time.put(18, result);
                collectionData.postValue(time);
            });
            service.execute(()->{
                result = Collections.listRemoveInTheMiddle(new CopyOnWriteArrayList<>(),value);
                time.put(19, result);
                collectionData.postValue(time);
            });
            service.execute(() -> {
                result = Collections.listRemoveInTheEnd(new ArrayList<>(),value);
                time.put(21, result);
                collectionData.postValue(time);
            });
            service.execute(()->{
                result = Collections.listRemoveInTheEnd(new LinkedList<>(),value);
                time.put(22, result);
                collectionData.postValue(time);
            });
            service.execute(()->{
                result = Collections.listRemoveInTheEnd(new CopyOnWriteArrayList<>(),value);
                time.put(23, result);
                collectionData.postValue(time);
            });
            service.execute(() -> {
                result = Collections.listSearchByValue(new ArrayList<>(),value);
                time.put(25, result);
                collectionData.postValue(time);
            });
            service.execute(()->{
                result = Collections.listSearchByValue(new LinkedList<>(),value);
                time.put(26, result);
                collectionData.postValue(time);
            });
            service.execute(()->{
                result = Collections.listSearchByValue(new CopyOnWriteArrayList<>(),value);
                time.put(27, result);
                collectionData.postValue(time);
            });
        }

    public LiveData<Map<Integer,String>> getMapData(){
        if(mapData == null){
            mapData = new MutableLiveData<>();
            setMapData();
        }
        return mapData;
    }

    public void setMapData(){
        ConcurrentHashMap<Integer,String> time = new ConcurrentHashMap<>();
        int value = Integer.parseInt(Objects.requireNonNull(inputValue.getValue()));
            service.execute(() -> {
                result = Collections.mapAddingNew(new TreeMap<>(),value);
                time.put(1, result);
                mapData.postValue(time);
            });
            service.execute(()->{
                result = Collections.mapAddingNew(new HashMap<>(),value);
                time.put(2, result);
                mapData.postValue(time);
            });
            service.execute(()->{
                result = Collections.mapSearchByKey(new TreeMap<>(),value);
                time.put(4, result);
                mapData.postValue(time);
            });
            service.execute(() -> {
                result = Collections.mapSearchByKey(new HashMap<>(),value);
                time.put(5, result);
                mapData.postValue(time);
            });
            service.execute(()->{
                result = Collections.mapRemoving(new TreeMap<>(),value);
                time.put(7, result);
                mapData.postValue(time);
            });
            service.execute(()->{
                result = Collections.mapRemoving(new HashMap<>(),value);
                time.put(8, result);
                mapData.postValue(time);
            });
        }

    public void shutDown(){
        service.shutdown();
        try {
            if (!service.awaitTermination(5, TimeUnit.SECONDS)) {
                service.shutdownNow();
                if (!service.awaitTermination(5, TimeUnit.SECONDS))
                    System.err.println("Pool did not terminate");
            }
        } catch (InterruptedException ie) {
            service.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}