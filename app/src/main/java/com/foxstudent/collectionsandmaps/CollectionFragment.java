package com.foxstudent.collectionsandmaps;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.foxstudent.collectionsandmaps.databinding.FragmentCollectionBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        LiveData<Map<Integer,String>> dataModel = model.getCollectionData();

        dataModel.observe(requireActivity(), stringStringMap -> {
            for (int i = 0; i < data.size(); i++) {
                    data.get(i).setResult(stringStringMap.get(i));
                    mRecyclerViewAdapter.notifyItemChanged(i);
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