package com.foxstudent.collectionsandmaps;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.foxstudent.collectionsandmaps.databinding.FragmentCollectionBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CollectionFragment extends Fragment {

    private CollectionFragmentRecyclerViewAdapter recyclerViewAdapter;
    private FragmentCollectionBinding fragmentCollectionBinding;
    private List<Cell> data;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentCollectionBinding = FragmentCollectionBinding.inflate(inflater, container, false);

        MainViewModel model = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        LiveData<List<Cell>> dataCell = model.getCollectionCell();
        data = new ArrayList<>();

        dataCell.observe(requireActivity(),cells -> {
            data.addAll(cells);
        });

        GridLayoutManager manager = new GridLayoutManager(getContext(),3);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup(){
            @Override
            public int getSpanSize(int position) {
                switch(recyclerViewAdapter.getItemViewType(position)){
                    case CollectionFragmentRecyclerViewAdapter.TYPE_HEADER:
                        return 3;
                    case CollectionFragmentRecyclerViewAdapter.TYPE_ITEM:
                        return 1;
                    default:
                        return -1;
                }
            }
        });

        fragmentCollectionBinding.rvGrid.setLayoutManager(manager);
        recyclerViewAdapter = new CollectionFragmentRecyclerViewAdapter(getContext(),data);
        fragmentCollectionBinding.rvGrid.setAdapter(recyclerViewAdapter);

        LiveData<Map<Integer,String>> dataModel = model.getCollectionData();

        dataModel.observe(requireActivity(), stringStringMap -> {
            for (int i = 0; i < data.size(); i++) {
                    data.get(i).setResult(stringStringMap.get(i));
                    recyclerViewAdapter.notifyItemChanged(i);
            }
        });

        return fragmentCollectionBinding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fragmentCollectionBinding = null;
    }
}