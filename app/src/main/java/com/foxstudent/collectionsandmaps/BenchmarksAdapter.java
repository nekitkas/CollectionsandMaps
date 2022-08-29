package com.foxstudent.collectionsandmaps;

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


public class BenchmarksAdapter extends ListAdapter<Cell, BenchmarksAdapter.ItemHolder> {

    private boolean running;


    BenchmarksAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Cell> DIFF_CALLBACK = new DiffUtil.ItemCallback<Cell>() {

        @Override
        public boolean areItemsTheSame(@NonNull Cell oldItem, @NonNull Cell newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(Cell oldItem, Cell newItem) {
            return oldItem.name.equals(newItem.name);
        }
    };

    @Override
    @NonNull
    public BenchmarksAdapter.ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BenchmarksAdapter.ItemHolder holder, int position) {
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
            operation.setText(getItem(position).name);

            if (running) {
                progressBar.animate()
                        .translationY(progressBar.getHeight())
                        .alpha(0.0f)
                        .setDuration(300)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationStart(Animator animation) {
                                super.onAnimationStart(animation);
                                progressBar.setVisibility(View.VISIBLE);
                            }
                        });
            } else {
                progressBar.animate()
                        .translationY(progressBar.getHeight())
                        .alpha(0.0f)
                        .setDuration(300)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                progressBar.setVisibility(View.GONE);
                            }
                        });
            }
        }

    }
}
