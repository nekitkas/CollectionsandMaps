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
import com.foxstudent.collectionsandmaps.models.Constants;

import java.util.ArrayList;

public class BenchmarksFragment extends Fragment implements View.OnClickListener {


    private static final String KEY = "KEY";
    private final BenchmarksAdapter adapter = new BenchmarksAdapter();
    private FragmentBenchmarkBinding binding;
    private BenchmarksViewModel model;

    public static BenchmarksFragment newInstance(int type) {
        final BenchmarksFragment fragment = new BenchmarksFragment();
        final Bundle args = new Bundle();
        args.putInt(KEY, type);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int type = getArguments() == null ? Constants.DEFAULT : getArguments().getInt(KEY);
        BenchmarksVMFactory factory = new BenchmarksVMFactory(type);
        model = new ViewModelProvider(getViewModelStore(), factory).get(BenchmarksViewModel.class);
        model.onCreate();
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
        model.getCells().observe(requireActivity(), cells -> adapter.submitList(new ArrayList<>(cells)));
        model.getToastMessage().observe(requireActivity(), message -> {
            if (message != 0) {
                Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show();
                if (message == R.string.calc_start)
                    binding.button.setText(R.string.stop);
                if (message == R.string.calc_stop) {
                    binding.button.setText(R.string.start);
                    binding.button.setClickable(true);
                }
                if (message == R.string.calc_complete) {
                    binding.button.setText(R.string.start);
                    binding.button.setClickable(true);
                }
                if (message == R.string.calc_stopping) {
                    binding.button.setText(R.string.wait);
                    binding.button.setClickable(false);
                }
            }
        });

        GridLayoutManager manager = new GridLayoutManager(getContext(), model.getSpanCount());
        binding.rvGrid.setLayoutManager(manager);
        binding.rvGrid.setAdapter(adapter);

        binding.button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String operationInput = binding.etOperations.getText().toString().trim();
        String threadInput = binding.etThreads.getText().toString().trim();
        model.onButtonPressed(operationInput, threadInput);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
