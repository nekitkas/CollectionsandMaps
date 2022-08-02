package com.foxstudent.collectionsandmaps;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CollectionFragmentViewModel extends ViewModel {

    private static final String TAG = "CollectionViewModel";
    private MutableLiveData<Map<Integer,String>> data;

    public LiveData<Map<Integer,String>> getData(){
        if(data == null){
            data = new MutableLiveData<>();
            setData();
        }
        return data;
    }

    public void setData(){
        Map<Integer,String> time = new HashMap<>();
        ExecutorService service = Executors.newFixedThreadPool(15);
        int value = 100000;
        service.execute(() -> {
            Collections collections = new Collections(new ArrayList<>(),value);
            collections.arrayListAddInTheBeginning();
            time.put(1, collections.getTime());
            Log.d(TAG, "setData: arrayListAddInTheBeginning " + " " + collections.getTime());
            data.postValue(time);
        });
        service.execute(()->{
            Collections collections = new Collections(new LinkedList<>(),value);
            collections.linkedListAddInTheBeginning();
            time.put(2, collections.getTime());
            Log.d(TAG, "setData: linkedListAddInTheBeginning " + " " + collections.getTime());
            data.postValue(time);
        });
        service.execute(()->{
            Collections collections = new Collections(new CopyOnWriteArrayList<>(),value);
            collections.copyOnWriteListAddInTheBeginning();
            time.put(3, collections.getTime());
            Log.d(TAG, "setData: copyOnWriteListAddInTheBeginning " + " " + collections.getTime());
            data.postValue(time);
        });
        service.shutdown();
    }

}
