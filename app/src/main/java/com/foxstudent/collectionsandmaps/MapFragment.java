package com.foxstudent.collectionsandmaps;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.foxstudent.collectionsandmaps.databinding.FragmentBinding;

import java.util.ArrayList;

public class MapFragment extends Fragment {

    private RecyclerViewAdapter adapter;
    private FragmentBinding binding;
    private MainViewModel model;
    private String operationInput, threadInput;
    private boolean running;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        model.getOperationInput().observe(requireActivity(), input -> {
            binding.etOperations.setText(input);
        });

        model.getThreadInput().observe(requireActivity(), input -> {
            binding.etThreads.setText(input);
        });

        GridLayoutManager manager = new GridLayoutManager(getContext(), 2);
        binding.rvGrid.setLayoutManager(manager);
        adapter = new RecyclerViewAdapter();
        binding.rvGrid.setAdapter(adapter);

        binding.button.setOnClickListener(v -> {
            operationInput = binding.etOperations.getText().toString().trim();
            threadInput = binding.etThreads.getText().toString().trim();
            if (operationInput.isEmpty() || threadInput.isEmpty()) {
                Toast.makeText(requireActivity(), R.string.empty_field, Toast.LENGTH_LONG).show();
            } else if (running) {
                Toast.makeText(requireActivity(), R.string.calc_stop, Toast.LENGTH_LONG).show();
                binding.button.setText(R.string.start);
                adapter.running(running);
                model.shutDown();
                running = false;
            } else {
                running = true;
                adapter.running(running);
                Toast.makeText(requireActivity(), R.string.calc_start, Toast.LENGTH_LONG).show();
                binding.button.setText(R.string.stop);
                model.setThreadValue(threadInput);
                model.setInputValue(operationInput);
                model.setMapData();
            }
        });

        model.getMapCell().observe(requireActivity(), cellList -> {
            adapter.submitList(new ArrayList<>(cellList));
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}