package com.foxstudent.collectionsandmaps.ui.benchmark;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.foxstudent.collectionsandmaps.R;
import com.foxstudent.collectionsandmaps.databinding.FragmentBinding;

import java.util.ArrayList;

public class BenchmarksFragment extends Fragment implements View.OnClickListener {

    private static String fragment;
    private final BenchmarksAdapter adapter = new BenchmarksAdapter();
    private FragmentBinding binding;
    private BenchmarksViewModel model;
    private boolean running;

    public static BenchmarksFragment newInstance(String value) {
        final BenchmarksFragment fragment = new BenchmarksFragment();

        final Bundle args = new Bundle();
        args.putString("KEY", value);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragment = getArguments() != null ? getArguments().getString("KEY") : "default";
        model = new ViewModelProvider(requireActivity()).get(BenchmarksViewModel.class);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState
    ) {
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

        GridLayoutManager manager = new GridLayoutManager(getContext(), 3);
        binding.rvGrid.setLayoutManager(manager);
        binding.rvGrid.setAdapter(adapter);

        binding.button.setOnClickListener(this);

        if (fragment.equals("collections")) {
            model.getCollectionCell().observe(requireActivity(), cellList -> {
                adapter.submitList(new ArrayList<>(cellList));
            });
        } else if (fragment.equals("maps")) {
            model.getMapCell().observe(requireActivity(), cellList -> {
                adapter.submitList(new ArrayList<>(cellList));
            });
        }
    }

    @Override
    public void onClick(View v) {
        String operationInput = binding.etOperations.getText().toString().trim();
        String threadInput = binding.etThreads.getText().toString().trim();
        if (operationInput.isEmpty() || threadInput.isEmpty()) {
            Toast.makeText(requireActivity(), R.string.empty_field, Toast.LENGTH_LONG).show();
        } else if (running) {
            Toast.makeText(requireActivity(), R.string.calc_stop, Toast.LENGTH_LONG).show();
            binding.button.setText(R.string.start);
            running = false;
            adapter.running(false);
            model.shutDown();
        } else {
            running = true;
            adapter.running(true);
            Toast.makeText(requireActivity(), R.string.calc_start, Toast.LENGTH_LONG).show();
            binding.button.setText(R.string.stop);
            model.setThreadValue(threadInput);
            model.setInputValue(operationInput);
            if (fragment.equals("collections")) {
                model.setCollectionData();
            } else if (fragment.equals("maps")) {
                model.setMapData();
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}