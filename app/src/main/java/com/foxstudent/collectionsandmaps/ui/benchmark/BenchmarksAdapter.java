package com.foxstudent.collectionsandmaps.ui.benchmark;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.foxstudent.collectionsandmaps.R;
import com.foxstudent.collectionsandmaps.models.Cell;


public class BenchmarksAdapter extends ListAdapter<Cell, BenchmarksAdapter.ItemHolder> {


    BenchmarksAdapter() {
        super(DIFF_CALLBACK);
    }

    @Override
    @NonNull
    public BenchmarksAdapter.ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_benchmark, parent, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BenchmarksAdapter.ItemHolder holder, int position) {
        holder.bind(getItem(position));
    }


    public static class ItemHolder extends RecyclerView.ViewHolder {
        private final TextView operation, result;
        private final ProgressBar progressBar;

        ItemHolder(View itemView) {
            super(itemView);
            operation = itemView.findViewById(R.id.operation);
            progressBar = itemView.findViewById(R.id.progressBar);
            result = itemView.findViewById(R.id.result);
        }

        public void bind(Cell cell) {
            operation.setText(cell.name);
            result.setText(cell.result);

            if (cell.result == null) {
                progressBar.animate()
                        .setListener(null)
                        .setDuration(60000)
                        .setStartDelay(0);
                progressBar.setVisibility(View.VISIBLE);
            } else {
                progressBar.animate()
                        .setListener(null)
                        .setDuration(60000)
                        .setStartDelay(0);
                progressBar.setVisibility(View.INVISIBLE);
            }
        }
    }


    private static final DiffUtil.ItemCallback<Cell> DIFF_CALLBACK = new DiffUtil.ItemCallback<Cell>() {

        @Override
        public boolean areItemsTheSame(@NonNull Cell oldItem, @NonNull Cell newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(Cell oldItem, @NonNull Cell newItem) {
            return oldItem.equals(newItem);
        }
    };
}
