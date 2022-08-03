package com.foxstudent.collectionsandmaps;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MapFragmentRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Cell> mData;
    private LayoutInflater mInflater;
    static final int TYPE_HEADER = 0;
    static final int TYPE_ITEM = 1;

    MapFragmentRecyclerViewAdapter(Context context, List<Cell> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    @Override
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == TYPE_ITEM){
            View view = mInflater.inflate(R.layout.recyclerview_item, parent, false);
            return new ViewHolderItem(view);
        }else if(viewType == TYPE_HEADER){
            View view = mInflater.inflate(R.layout.header, parent, false);
            return new ViewHolderHeader(view);
        }
        throw new RuntimeException("No match for " + viewType + ".");
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case TYPE_ITEM:
                ViewHolderItem viewHolderItem = (ViewHolderItem)holder;
                viewHolderItem.collection.setText(mData.get(position).getName());
                if(mData.get(position).getResult()==null){
                    viewHolderItem.progressBar.setVisibility(View.VISIBLE);
                    viewHolderItem.runtime.setVisibility(View.GONE);
                }else{
                    viewHolderItem.runtime.setText(mData.get(position).getResult());
                    viewHolderItem.progressBar.setVisibility(View.GONE);
                    viewHolderItem.runtime.setVisibility(View.VISIBLE);
                }
                break;

            case TYPE_HEADER:
                ViewHolderHeader viewHolderHeader = (ViewHolderHeader)holder;
                if(position == 0){
                    viewHolderHeader.operation.setText(R.string.addingNew);
                }else if(position == 3){
                    viewHolderHeader.operation.setText(R.string.searchByKey);
                }else if(position == 6){
                    viewHolderHeader.operation.setText(R.string.removing);
                }
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }



    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position)){
            return TYPE_HEADER;
        }
        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        switch (position){
            case 0:
            case 3:
            case 6:
                return true;
            default:
                return false;
        }
    }

    public static class ViewHolderItem extends RecyclerView.ViewHolder {
        TextView runtime,collection;
        ProgressBar progressBar;

        ViewHolderItem(View itemView) {
            super(itemView);
            runtime = itemView.findViewById(R.id.runtime);
            collection = itemView.findViewById(R.id.collection);
            progressBar = itemView.findViewById(R.id.progressBar);
        }
    }

    public static class ViewHolderHeader extends RecyclerView.ViewHolder {
        TextView operation;

        ViewHolderHeader(View itemView) {
            super(itemView);
            operation = itemView.findViewById(R.id.operation);
        }
    }
}
