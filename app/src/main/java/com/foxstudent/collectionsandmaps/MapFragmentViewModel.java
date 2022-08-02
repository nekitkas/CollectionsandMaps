package com.foxstudent.collectionsandmaps;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MapFragmentViewModel extends ViewModel {

    private static final String TAG = "MapFragmentViewModel";
    private MutableLiveData<List<String>> data;

    public LiveData<List<String>> getData(){
        if(data == null){
            data = new MutableLiveData<>();
            setData();
        }
        return data;
    }

    public void setData(){
        List<String> time = new ArrayList<>();
        ExecutorService service = Executors.newFixedThreadPool(15);
        int value = 10000;
        service.execute(() -> {

        });
    }
}
