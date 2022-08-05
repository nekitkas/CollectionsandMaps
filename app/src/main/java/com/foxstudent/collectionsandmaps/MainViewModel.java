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
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainViewModel extends ViewModel {

    private MutableLiveData<Boolean> isVisible;
    private MutableLiveData<Map<Integer,String>> collectionData;
    private MutableLiveData<Map<Integer,String>> mapData;
    private MutableLiveData<List<Cell>> collectionCell;
    private MutableLiveData<List<Cell>> mapCell;
    private MutableLiveData<String> inputValue;
    private final ExecutorService service = Executors.newFixedThreadPool(30);

    public void shutDown(){
        service.shutdownNow();
    }

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
        Map<Integer,String> time = new HashMap<>();
        int value = Integer.parseInt(Objects.requireNonNull(inputValue.getValue()));
        service.execute(() -> {
            Collections collections = new Collections(new ArrayList<>(),value);
            collections.listAddInTheBeginning();
            time.put(1, collections.getTime());
            collectionData.postValue(time);
        });
        service.execute(()->{
            Collections collections = new Collections(new LinkedList<>(),value);
            collections.listAddInTheBeginning();
            time.put(2, collections.getTime());
            collectionData.postValue(time);
        });
        service.execute(()->{
            Collections collections = new Collections(new CopyOnWriteArrayList<>(),value);
            collections.listAddInTheBeginning();
            time.put(3, collections.getTime());
            collectionData.postValue(time);
        });
        service.execute(() -> {
            Collections collections = new Collections(new ArrayList<>(),value);
            collections.listAddInTheMiddle();
            time.put(5, collections.getTime());
            collectionData.postValue(time);
        });
        service.execute(()->{
            Collections collections = new Collections(new LinkedList<>(),value);
            collections.listAddInTheMiddle();
            time.put(6, collections.getTime());
            collectionData.postValue(time);
        });
        service.execute(()->{
            Collections collections = new Collections(new CopyOnWriteArrayList<>(),value);
            collections.listAddInTheMiddle();
            time.put(7, collections.getTime());
            collectionData.postValue(time);
        });
        service.execute(() -> {
            Collections collections = new Collections(new ArrayList<>(),value);
            collections.listAddInTheEnd();
            time.put(9, collections.getTime());
            collectionData.postValue(time);
        });
        service.execute(()->{
            Collections collections = new Collections(new LinkedList<>(),value);
            collections.listAddInTheEnd();
            time.put(10, collections.getTime());
            collectionData.postValue(time);
        });
        service.execute(()->{
            Collections collections = new Collections(new CopyOnWriteArrayList<>(),value);
            collections.listAddInTheEnd();
            time.put(11, collections.getTime());
            collectionData.postValue(time);
        });
        service.execute(() -> {
            Collections collections = new Collections(new ArrayList<>(),value);
            collections.listRemoveInTheBeginning();
            time.put(13, collections.getTime());
            collectionData.postValue(time);
        });
        service.execute(()->{
            Collections collections = new Collections(new LinkedList<>(),value);
            collections.listRemoveInTheBeginning();
            time.put(14, collections.getTime());
            collectionData.postValue(time);
        });
        service.execute(()->{
            Collections collections = new Collections(new CopyOnWriteArrayList<>(),value);
            collections.listRemoveInTheBeginning();
            time.put(15, collections.getTime());
            collectionData.postValue(time);
        });
        service.execute(() -> {
            Collections collections = new Collections(new ArrayList<>(),value);
            collections.listRemoveInTheMiddle();
            time.put(17, collections.getTime());
            collectionData.postValue(time);
        });
        service.execute(()->{
            Collections collections = new Collections(new LinkedList<>(),value);
            collections.listRemoveInTheMiddle();
            time.put(18, collections.getTime());
            collectionData.postValue(time);
        });
        service.execute(()->{
            Collections collections = new Collections(new CopyOnWriteArrayList<>(),value);
            collections.listRemoveInTheMiddle();
            time.put(19, collections.getTime());
            collectionData.postValue(time);
        });
        service.execute(() -> {
            Collections collections = new Collections(new ArrayList<>(),value);
            collections.listRemoveInTheEnd();
            time.put(21, collections.getTime());
            collectionData.postValue(time);
        });
        service.execute(()->{
            Collections collections = new Collections(new LinkedList<>(),value);
            collections.listRemoveInTheEnd();
            time.put(22, collections.getTime());
            collectionData.postValue(time);
        });
        service.execute(()->{
            Collections collections = new Collections(new CopyOnWriteArrayList<>(),value);
            collections.listRemoveInTheEnd();
            time.put(23, collections.getTime());
            collectionData.postValue(time);
        });
        service.execute(() -> {
            Collections collections = new Collections(new ArrayList<>(),value);
            collections.listSearchByValue();
            time.put(25, collections.getTime());
            collectionData.postValue(time);
        });
        service.execute(()->{
            Collections collections = new Collections(new LinkedList<>(),value);
            collections.listSearchByValue();
            time.put(26, collections.getTime());
            collectionData.postValue(time);
        });
        service.execute(()->{
            Collections collections = new Collections(new CopyOnWriteArrayList<>(),value);
            collections.listSearchByValue();
            time.put(27, collections.getTime());
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
        Map<Integer,String> time = new HashMap<>();
        int value = Integer.parseInt(Objects.requireNonNull(inputValue.getValue()));
        service.execute(() -> {
            Collections collections = new Collections(new TreeMap<>(),value);
            collections.mapAddingNew();
            time.put(1, collections.getTime());
            mapData.postValue(time);
        });
        service.execute(()->{
            Collections collections = new Collections(new HashMap<>(),value);
            collections.mapAddingNew();
            time.put(2, collections.getTime());
            mapData.postValue(time);
        });
        service.execute(()->{
            Collections collections = new Collections(new TreeMap<>(),value);
            collections.mapSearchByKey();
            time.put(4, collections.getTime());
            mapData.postValue(time);
        });
        service.execute(() -> {
            Collections collections = new Collections(new HashMap<>(),value);
            collections.mapSearchByKey();
            time.put(5, collections.getTime());
            mapData.postValue(time);
        });
        service.execute(()->{
            Collections collections = new Collections(new TreeMap<>(),value);
            collections.mapRemoving();
            time.put(7, collections.getTime());
            mapData.postValue(time);
        });
        service.execute(()->{
            Collections collections = new Collections(new HashMap<>(),value);
            collections.mapRemoving();
            time.put(8, collections.getTime());
            mapData.postValue(time);
        });
    }
}
