package com.foxstudent.collectionsandmaps;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.HashMap;
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
                time.put(1, Collections.listAddInTheBeginning(new ArrayList<>(), value));
                collectionData.postValue(time);
            });
            service.execute(()->{
                time.put(2, Collections.listAddInTheBeginning(new LinkedList<>(),value));
                collectionData.postValue(time);
            });
            service.execute(()->{
                time.put(3, Collections.listAddInTheBeginning(new CopyOnWriteArrayList<>(),value));
                collectionData.postValue(time);
            });
            service.execute(() -> {
                time.put(5, Collections.listAddInTheMiddle(new ArrayList<>(),value));
                collectionData.postValue(time);
            });
            service.execute(()->{
                time.put(6, Collections.listAddInTheMiddle(new LinkedList<>(),value));
                collectionData.postValue(time);
            });
            service.execute(()->{
                time.put(7, Collections.listAddInTheMiddle(new CopyOnWriteArrayList<>(),value));
                collectionData.postValue(time);
            });
            service.execute(() -> {
                time.put(9, Collections.listAddInTheEnd(new ArrayList<>(),value));
                collectionData.postValue(time);
            });
            service.execute(()->{
                time.put(10, Collections.listAddInTheEnd(new LinkedList<>(),value));
                collectionData.postValue(time);
            });
            service.execute(()->{
                time.put(11, Collections.listAddInTheEnd(new CopyOnWriteArrayList<>(),value));
                collectionData.postValue(time);
            });
            service.execute(() -> {
                time.put(13, Collections.listRemoveInTheBeginning(new ArrayList<>(),value));
                collectionData.postValue(time);
            });
            service.execute(()->{
                time.put(14, Collections.listRemoveInTheBeginning(new LinkedList<>(),value));
                collectionData.postValue(time);
            });
            service.execute(()->{
                time.put(15, Collections.listRemoveInTheBeginning(new CopyOnWriteArrayList<>(),value));
                collectionData.postValue(time);
            });
            service.execute(() -> {
                time.put(17, Collections.listRemoveInTheMiddle(new ArrayList<>(),value));
                collectionData.postValue(time);
            });
            service.execute(()->{
                time.put(18, Collections.listRemoveInTheMiddle(new LinkedList<>(),value));
                collectionData.postValue(time);
            });
            service.execute(()->{
                time.put(19, Collections.listRemoveInTheMiddle(new CopyOnWriteArrayList<>(),value));
                collectionData.postValue(time);
            });
            service.execute(() -> {
                time.put(21, Collections.listRemoveInTheEnd(new ArrayList<>(),value));
                collectionData.postValue(time);
            });
            service.execute(()->{
                time.put(22, Collections.listRemoveInTheEnd(new LinkedList<>(),value));
                collectionData.postValue(time);
            });
            service.execute(()->{
                time.put(23, Collections.listRemoveInTheEnd(new CopyOnWriteArrayList<>(),value));
                collectionData.postValue(time);
            });
            service.execute(() -> {
                time.put(25, Collections.listSearchByValue(new ArrayList<>(),value));
                collectionData.postValue(time);
            });
            service.execute(()->{
                time.put(26, Collections.listSearchByValue(new LinkedList<>(),value));
                collectionData.postValue(time);
            });
            service.execute(()->{
                time.put(27, Collections.listSearchByValue(new CopyOnWriteArrayList<>(),value));
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
                time.put(1, Collections.mapAddingNew(new TreeMap<>(),value));
                mapData.postValue(time);
            });
            service.execute(()->{
                time.put(2, Collections.mapAddingNew(new HashMap<>(),value));
                mapData.postValue(time);
            });
            service.execute(()->{
                time.put(4, Collections.mapSearchByKey(new TreeMap<>(),value));
                mapData.postValue(time);
            });
            service.execute(() -> {
                time.put(5, Collections.mapSearchByKey(new HashMap<>(),value));
                mapData.postValue(time);
            });
            service.execute(()->{
                time.put(7, Collections.mapRemoving(new TreeMap<>(),value));
                mapData.postValue(time);
            });
            service.execute(()->{
                time.put(8, Collections.mapRemoving(new HashMap<>(),value));
                mapData.postValue(time);
            });
        }

    public void shutDown(){
        service.shutdown();
        try {
            if (!service.awaitTermination(2, TimeUnit.SECONDS)) {
                service.shutdownNow();
                if (!service.awaitTermination(2, TimeUnit.SECONDS))
                    System.err.println("Pool did not terminate");
            }
        } catch (InterruptedException ie) {
            service.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}