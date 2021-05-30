package com.wolfsea.kotlinmvp.drakeer;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public abstract class ItemViewBinder<T, VH extends RecyclerView.ViewHolder> {

     MultiTypeAdapter adapter;

    @NonNull
    protected abstract VH onCreateViewHolder(
        @NonNull LayoutInflater inflater, @NonNull ViewGroup parent);

    protected abstract void onBindViewHolder(@NonNull VH holder, @NonNull T item);


    protected void onBindViewHolder(
        @NonNull VH holder, @NonNull T item, @NonNull List<Object> payloads) {
        onBindViewHolder(holder, item);
    }

    protected final int getPosition(@NonNull final RecyclerView.ViewHolder holder) {
        return holder.getAdapterPosition();
    }


    @NonNull
    protected final MultiTypeAdapter getAdapter() {
        return adapter;
    }

    protected long getItemId(@NonNull T item) {
        return RecyclerView.NO_ID;
    }

    protected void onViewRecycled(@NonNull VH holder) {}

    protected boolean onFailedToRecycleView(@NonNull VH holder) {
        return false;
    }

    protected void onViewAttachedToWindow(@NonNull VH holder) {}

    protected void onViewDetachedFromWindow(@NonNull VH holder) {}
}
