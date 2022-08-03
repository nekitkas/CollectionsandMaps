package com.foxstudent.collectionsandmaps;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.foxstudent.collectionsandmaps.databinding.FragmentCollectionBinding;

import java.util.ArrayList;
import java.util.List;

public class CollectionFragment extends Fragment {

    private static final String TAG = "CollectionFragment";
    private CollectionFragmentRecyclerViewAdapter mRecyclerViewAdapter;
    private FragmentCollectionBinding fragmentCollectionBinding;
    private List<Cell> data;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentCollectionBinding = FragmentCollectionBinding.inflate(inflater, container, false);

        MainViewModel model = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        RecyclerView recyclerView = fragmentCollectionBinding.rvGrid;


        data = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            data.add(new Cell()); // header
            data.add(new Cell("ArrayList"));
            data.add(new Cell("LinkedList"));
            data.add(new Cell("CopyOnWrite"));
        }

        int numberOfColumns = 3;
        GridLayoutManager manager = new GridLayoutManager(getContext(),numberOfColumns);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup(){
            @Override
            public int getSpanSize(int position) {
                switch(mRecyclerViewAdapter.getItemViewType(position)){
                    case CollectionFragmentRecyclerViewAdapter.TYPE_HEADER:
                        return 3;
                    case CollectionFragmentRecyclerViewAdapter.TYPE_ITEM:
                        return 1;
                    default:
                        return -1;
                }
            }
        });
        recyclerView.setLayoutManager(manager);
        mRecyclerViewAdapter = new CollectionFragmentRecyclerViewAdapter(getContext(),data);
        recyclerView.setAdapter(mRecyclerViewAdapter);


        /*LiveData<Map<Integer,String>> dataModel = model.getData();

        dataModel.observe(requireActivity(), new Observer<Map<Integer,String>>() {
            @Override
            public void onChanged(Map<Integer, String> stringStringMap) {
                for (int i = 0; i < data.size(); i++) {
                    switch (i){
                        case 1:
                            data.get(i).setResult(stringStringMap.get(i));
                            mRecyclerViewAdapter.setProgressBarVisibility(false);
                            mRecyclerViewAdapter.notifyItemChanged(i);
                            Log.d(TAG, "FIRST CELL " + " " + stringStringMap.get(i));
                        case 2:
                            data.get(i).setResult(stringStringMap.get(i));
                            mRecyclerViewAdapter.setProgressBarVisibility(false);
                            mRecyclerViewAdapter.notifyItemChanged(i);
                            Log.d(TAG, "SECOND CELL: " + " " + stringStringMap.get(i));
                        case 3:
                            data.get(i).setResult(stringStringMap.get(i));
                            mRecyclerViewAdapter.setProgressBarVisibility(false);
                            mRecyclerViewAdapter.notifyItemChanged(i);
                            Log.d(TAG, "THIRD CELL: " + " " + stringStringMap.get(i));
                    }
                }
            }
        });*/

        model.getCollectionData().observe(getViewLifecycleOwner(),result ->{
            data.get(1).setResult(result.get(1));
            mRecyclerViewAdapter.setProgressBarVisibility(false);
            mRecyclerViewAdapter.notifyItemChanged(1);
            Log.d(TAG, "FIRST CELL " + " " + result.get(1));
                });
        model.getCollectionData().observe(getViewLifecycleOwner(),result -> {
            data.get(2).setResult(result.get(2));
            mRecyclerViewAdapter.setProgressBarVisibility(false);
            mRecyclerViewAdapter.notifyItemChanged(2);
            Log.d(TAG, "SECOND CELL: " + " " + result.get(2));
                });
        model.getCollectionData().observe(getViewLifecycleOwner(),result -> {
            data.get(3).setResult(result.get(3));
            mRecyclerViewAdapter.setProgressBarVisibility(false);
            mRecyclerViewAdapter.notifyItemChanged(3);
            Log.d(TAG, "THIRD CELL: " + " " + result.get(3));
                });

        return fragmentCollectionBinding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fragmentCollectionBinding = null;
    }
}