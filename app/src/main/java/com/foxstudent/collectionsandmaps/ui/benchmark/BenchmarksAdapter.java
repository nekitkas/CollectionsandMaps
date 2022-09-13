package com.foxstudent.collectionsandmaps.ui.benchmark;

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
        private final TextView operation;
        private final ProgressBar progressBar;

        ItemHolder(View itemView) {
            super(itemView);
            operation = itemView.findViewById(R.id.operation);
            progressBar = itemView.findViewById(R.id.progressBar);
        }

        public void bind(Cell cell) {
            String placeHolderName = itemView.getContext().getString(cell.name);
            String placeHolderOperation = itemView.getContext().getString(cell.operation);
            String placeHolderResult = cell.result;
            String title = operation.getContext().getString(R.string.title, placeHolderOperation, placeHolderName, placeHolderResult);
            operation.setText(title);

            progressBarAnimate(cell);
        }

        private void progressBarAnimate(Cell cell) {
            float expected = cell.isInProgress ? 1f : 0f;
            if (progressBar.getAlpha() == expected) {
                return;
            }
            progressBar.animate()
                    .alpha(expected)
                    .setDuration(600);
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
