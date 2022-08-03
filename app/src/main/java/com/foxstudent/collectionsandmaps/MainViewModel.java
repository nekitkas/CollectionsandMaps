package com.foxstudent.collectionsandmaps;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainViewModel extends ViewModel {

    private final String TAG = "MainViewModel";
    private MutableLiveData<Boolean> isVisible;
    private MutableLiveData<Map<Integer,String>> collectionData;
    private MutableLiveData<Map<Integer,String>> mapData;
    private MutableLiveData<String> inputValue;
    private final ExecutorService service = Executors.newFixedThreadPool(30);

    public void shutDown(){
        service.shutdownNow();
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
            collections.arrayListAddInTheBeginning();
            time.put(1, collections.getTime());
            collectionData.postValue(time);
        });
        service.execute(()->{
            Collections collections = new Collections(new LinkedList<>(),value);
            collections.linkedListAddInTheBeginning();
            time.put(2, collections.getTime());
            collectionData.postValue(time);
        });
        service.execute(()->{
            Collections collections = new Collections(new CopyOnWriteArrayList<>(),value);
            collections.copyOnWriteListAddInTheBeginning();
            time.put(3, collections.getTime());
            collectionData.postValue(time);
        });
        service.execute(() -> {
            Collections collections = new Collections(new ArrayList<>(),value);
            collections.arrayListAddInTheMiddle();
            time.put(5, collections.getTime());
            collectionData.postValue(time);
        });
        service.execute(()->{
            Collections collections = new Collections(new LinkedList<>(),value);
            collections.linkedListAddInTheMiddle();
            time.put(6, collections.getTime());
            collectionData.postValue(time);
        });
        service.execute(()->{
            Collections collections = new Collections(new CopyOnWriteArrayList<>(),value);
            collections.copyOnWriteListAddInTheMiddle();
            time.put(7, collections.getTime());
            collectionData.postValue(time);
        });
        service.execute(() -> {
            Collections collections = new Collections(new ArrayList<>(),value);
            collections.arrayListAddInTheEnd();
            time.put(9, collections.getTime());
            collectionData.postValue(time);
        });
        service.execute(()->{
            Collections collections = new Collections(new LinkedList<>(),value);
            collections.linkedListAddInTheEnd();
            time.put(10, collections.getTime());
            collectionData.postValue(time);
        });
        service.execute(()->{
            Collections collections = new Collections(new CopyOnWriteArrayList<>(),value);
            collections.copyOnWriteListAddInTheEnd();
            time.put(11, collections.getTime());
            collectionData.postValue(time);
        });
        service.execute(() -> {
            Collections collections = new Collections(new ArrayList<>(),value);
            collections.arrayListRemoveInTheBeginning();
            time.put(13, collections.getTime());
            collectionData.postValue(time);
        });
        service.execute(()->{
            Collections collections = new Collections(new LinkedList<>(),value);
            collections.linkedListRemoveInTheBeginning();
            time.put(14, collections.getTime());
            collectionData.postValue(time);
        });
        service.execute(()->{
            Collections collections = new Collections(new CopyOnWriteArrayList<>(),value);
            collections.copyOnWriteListRemoveInTheBeginning();
            time.put(15, collections.getTime());
            collectionData.postValue(time);
        });
        service.execute(() -> {
            Collections collections = new Collections(new ArrayList<>(),value);
            collections.arrayListRemoveInTheMiddle();
            time.put(17, collections.getTime());
            collectionData.postValue(time);
        });
        service.execute(()->{
            Collections collections = new Collections(new LinkedList<>(),value);
            collections.linkedListRemoveInTheMiddle();
            time.put(18, collections.getTime());
            collectionData.postValue(time);
        });
        service.execute(()->{
            Collections collections = new Collections(new CopyOnWriteArrayList<>(),value);
            collections.copyOnWriteListRemoveInTheMiddle();
            time.put(19, collections.getTime());
            collectionData.postValue(time);
        });
        service.execute(() -> {
            Collections collections = new Collections(new ArrayList<>(),value);
            collections.arrayListRemoveInTheEnd();
            time.put(21, collections.getTime());
            collectionData.postValue(time);
        });
        service.execute(()->{
            Collections collections = new Collections(new LinkedList<>(),value);
            collections.linkedListRemoveInTheEnd();
            time.put(22, collections.getTime());
            collectionData.postValue(time);
        });
        service.execute(()->{
            Collections collections = new Collections(new CopyOnWriteArrayList<>(),value);
            collections.copyOnWriteListRemoveInTheEnd();
            time.put(23, collections.getTime());
            collectionData.postValue(time);
        });
        service.execute(() -> {
            Collections collections = new Collections(new ArrayList<>(),value);
            collections.arrayListSearchByValue();
            time.put(25, collections.getTime());
            collectionData.postValue(time);
        });
        service.execute(()->{
            Collections collections = new Collections(new LinkedList<>(),value);
            collections.linkedListSearchByValue();
            time.put(26, collections.getTime());
            collectionData.postValue(time);
        });
        service.execute(()->{
            Collections collections = new Collections(new CopyOnWriteArrayList<>(),value);
            collections.copyOnWriteListSearchByValue();
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
            collections.treeMapAddingNew();
            time.put(1, collections.getTime());
            mapData.postValue(time);
        });
        service.execute(()->{
            Collections collections = new Collections(new HashMap<>(),value);
            collections.hashMapAddingNew();
            time.put(2, collections.getTime());
            mapData.postValue(time);
        });
        service.execute(()->{
            Collections collections = new Collections(new TreeMap<>(),value);
            collections.treeMapSearchByKey();
            time.put(4, collections.getTime());
            mapData.postValue(time);
        });
        service.execute(() -> {
            Collections collections = new Collections(new HashMap<>(),value);
            collections.hashMapSearchByKey();
            time.put(5, collections.getTime());
            mapData.postValue(time);
        });
        service.execute(()->{
            Collections collections = new Collections(new TreeMap<>(),value);
            collections.treeMapRemoving();
            time.put(7, collections.getTime());
            mapData.postValue(time);
        });
        service.execute(()->{
            Collections collections = new Collections(new HashMap<>(),value);
            collections.hashMapRemoving();
            time.put(8, collections.getTime());
            mapData.postValue(time);
        });
    }
}
