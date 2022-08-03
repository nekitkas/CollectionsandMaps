package com.foxstudent.collectionsandmaps;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainViewModel extends ViewModel {

    private final String TAG = "MainViewModel";
    private MutableLiveData<Boolean> isVisible;
    private MutableLiveData<Map<Integer,String>> collectionData;
    private MutableLiveData<List<String>> mapData;
    private MutableLiveData<String> inputValue;

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
        ExecutorService service = Executors.newFixedThreadPool(15);
        int value = Integer.parseInt(Objects.requireNonNull(inputValue.getValue()));
        service.execute(() -> {
            Collections collections = new Collections(new ArrayList<>(),value);
            collections.arrayListAddInTheBeginning();
            time.put(1, collections.getTime());
            Log.d(TAG, "setData: arrayListAddInTheBeginning " + " " + collections.getTime());
            collectionData.postValue(time);
        });
        service.execute(()->{
            Collections collections = new Collections(new LinkedList<>(),value);
            collections.linkedListAddInTheBeginning();
            time.put(2, collections.getTime());
            Log.d(TAG, "setData: linkedListAddInTheBeginning " + " " + collections.getTime());
            collectionData.postValue(time);
        });
        service.execute(()->{
            Collections collections = new Collections(new CopyOnWriteArrayList<>(),value);
            collections.copyOnWriteListAddInTheBeginning();
            time.put(3, collections.getTime());
            Log.d(TAG, "setData: copyOnWriteListAddInTheBeginning " + " " + collections.getTime());
            collectionData.postValue(time);
        });
        service.shutdown();
    }

    public LiveData<List<String>> getMapData(){
        if(mapData == null){
            mapData = new MutableLiveData<>();
            setMapData();
        }
        return mapData;
    }

    public void setMapData(){
        List<String> time = new ArrayList<>();
        ExecutorService service = Executors.newFixedThreadPool(15);
        int value = 10000;
        service.execute(() -> {

        });
    }
}
