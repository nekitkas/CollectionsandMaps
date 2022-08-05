package com.foxstudent.collectionsandmaps;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


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

    private MapFragmentRecyclerViewAdapter recyclerViewAdapter;
    private FragmentMapBinding fragmentMapBinding;
    private List<Cell> data;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentMapBinding = FragmentMapBinding.inflate(inflater, container, false);

        MainViewModel model = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        LiveData<List<Cell>> dataCell = model.getMapCell();
        data = new ArrayList<>();

        dataCell.observe(requireActivity(),cells -> {
            data.addAll(cells);
        });


        GridLayoutManager manager = new GridLayoutManager(getContext(),2);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup(){
            @Override
            public int getSpanSize(int position) {
                switch(recyclerViewAdapter.getItemViewType(position)){
                    case MapFragmentRecyclerViewAdapter.TYPE_HEADER:
                        return 2;
                    case MapFragmentRecyclerViewAdapter.TYPE_ITEM:
                        return 1;
                    default:
                        return -1;
                }
            }
        });

        fragmentMapBinding.rvGrid.setLayoutManager(manager);
        recyclerViewAdapter = new MapFragmentRecyclerViewAdapter(getContext(),data);
        fragmentMapBinding.rvGrid.setAdapter(recyclerViewAdapter);

        LiveData<Map<Integer,String>> dataModel = model.getMapData();

        dataModel.observe(requireActivity(), stringStringMap -> {
            for (int i = 0; i < data.size(); i++) {
                data.get(i).setResult(stringStringMap.get(i));
                recyclerViewAdapter.notifyItemChanged(i);
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