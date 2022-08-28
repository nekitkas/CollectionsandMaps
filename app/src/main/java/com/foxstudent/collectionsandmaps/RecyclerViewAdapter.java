package com.foxstudent.collectionsandmaps;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;


public class RecyclerViewAdapter extends ListAdapter<Cell, RecyclerViewAdapter.ItemHolder> {

    private boolean running;

    RecyclerViewAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Cell> DIFF_CALLBACK = new DiffUtil.ItemCallback<Cell>() {

        @Override
        public boolean areItemsTheSame(Cell oldItem, Cell newItem) {
            return oldItem.equals(newItem);
        }

        @Override
        public boolean areContentsTheSame(Cell oldItem, Cell newItem) {
            return oldItem.getName().equals(newItem.getName()) &&
                    oldItem.getResult().equals(newItem.getResult());
        }
    };

    @Override
    @NonNull
    public RecyclerViewAdapter.ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ItemHolder holder, int position) {
        holder.bind(position);
    }

    public void running(boolean running) {
        this.running = running;
    }


    public class ItemHolder extends RecyclerView.ViewHolder {
        private final TextView operation;
        private final ProgressBar progressBar;

        ItemHolder(View itemView) {
            super(itemView);
            operation = itemView.findViewById(R.id.operation);
            progressBar = itemView.findViewById(R.id.progressBar);
        }

        public void bind(int position) {
            operation.setText(getItem(position).getName());
            if (running) {
                progressBar.setVisibility(View.VISIBLE);
            } else {
                progressBar.setVisibility(View.GONE);
            }
        }
    }
}
