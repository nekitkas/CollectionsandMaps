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
import com.foxstudent.collectionsandmaps.databinding.FragmentBenchmarkBinding;

import java.util.ArrayList;

public class BenchmarksFragment extends Fragment implements View.OnClickListener {


    private static final String KEY = "KEY";
    private static final String DEFAULT = "DEFAULT";
    private final BenchmarksAdapter adapter = new BenchmarksAdapter();
    private FragmentBenchmarkBinding binding;
    private BenchmarksViewModel model;
    private boolean running;

    public static BenchmarksFragment newInstance(String value) {
        final BenchmarksFragment fragment = new BenchmarksFragment();
        final Bundle args = new Bundle();
        args.putString(KEY, value);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String arg = getArguments() == null ? DEFAULT : getArguments().getString(KEY);
        BenchmarksVMFactory factory = new BenchmarksVMFactory(requireActivity().getApplication(), arg);
        model = new ViewModelProvider(getViewModelStore(), factory).get(BenchmarksViewModel.class);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState
    ) {
        binding = FragmentBenchmarkBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        model.getOperationInput().observe(requireActivity(), input -> binding.etOperations.setText(input));
        model.getThreadInput().observe(requireActivity(), input -> binding.etThreads.setText(input));
        model.getCells().observe(requireActivity(), cells -> adapter.submitList(new ArrayList<>(cells)));
        model.getState().observe(requireActivity(), state -> {
            running = state;
            if (running) {
                binding.button.setText(R.string.stop);
            } else {
                binding.button.setText(R.string.start);
            }
        });

        GridLayoutManager manager = new GridLayoutManager(getContext(), 3);
        binding.rvGrid.setLayoutManager(manager);
        binding.rvGrid.setAdapter(adapter);

        binding.button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String operationInput = binding.etOperations.getText().toString().trim();
        String threadInput = binding.etThreads.getText().toString().trim();
        if (operationInput.isEmpty() || threadInput.isEmpty()) {
            Toast.makeText(requireActivity(), R.string.empty_field, Toast.LENGTH_SHORT).show();
        } else if (!running) {
            Toast.makeText(requireActivity(), R.string.calc_start, Toast.LENGTH_SHORT).show();
            model.setInputValue(operationInput);
            model.setThreadValue(threadInput);
            model.setState(true);
            model.run();
        } else {
            Toast.makeText(requireActivity(), R.string.calc_stopping, Toast.LENGTH_SHORT).show();
            model.setState(false);
            Toast.makeText(requireActivity(), model.shutDown(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
