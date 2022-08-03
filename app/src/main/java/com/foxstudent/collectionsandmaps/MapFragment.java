package com.foxstudent.collectionsandmaps;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.foxstudent.collectionsandmaps.databinding.FragmentMapBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import androidx.lifecycle.ViewModelProvider;



public class MapFragment extends Fragment {

    private MapFragmentRecyclerViewAdapter mRecyclerViewAdapter;
    private FragmentMapBinding fragmentMapBinding;
    private List<Cell> data;
    private final String  TAG = "MapFragment";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentMapBinding = FragmentMapBinding.inflate(inflater, container, false);

        MainViewModel model = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        RecyclerView recyclerView = fragmentMapBinding.rvGrid;


        data = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            data.add(new Cell()); // header
            data.add(new Cell("TreeMap"));
            data.add(new Cell("HashMap"));
        }
        int numberOfColumns = 2;
        GridLayoutManager manager = new GridLayoutManager(getContext(),numberOfColumns);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup(){
            @Override
            public int getSpanSize(int position) {
                switch(mRecyclerViewAdapter.getItemViewType(position)){
                    case MapFragmentRecyclerViewAdapter.TYPE_HEADER:
                        return 2;
                    case MapFragmentRecyclerViewAdapter.TYPE_ITEM:
                        return 1;
                    default:
                        return -1;
                }
            }
        });
        recyclerView.setLayoutManager(manager);
        mRecyclerViewAdapter = new MapFragmentRecyclerViewAdapter(getContext(),data);
        recyclerView.setAdapter(mRecyclerViewAdapter);

        LiveData<Map<Integer,String>> dataModel = model.getCollectionData();

        dataModel.observe(requireActivity(), stringStringMap -> {
            for (int i = 0; i < data.size(); i++) {
                data.get(i).setResult(stringStringMap.get(i));
                mRecyclerViewAdapter.notifyItemChanged(i);
            }
        });


        return fragmentMapBinding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fragmentMapBinding = null;
    }
}