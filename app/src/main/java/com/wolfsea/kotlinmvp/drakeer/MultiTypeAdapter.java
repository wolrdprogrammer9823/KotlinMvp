package com.wolfsea.kotlinmvp.drakeer;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;


public class MultiTypeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "MultiTypeAdapter";

    private @NonNull
    List<?> items;
    private @NonNull
    TypePool typePool;

    public MultiTypeAdapter() {
        this(Collections.emptyList());
    }

    public MultiTypeAdapter(@NonNull List<?> items) {
        this(items, new MultiTypePool());
    }

    public MultiTypeAdapter(@NonNull List<?> items, int initialCapacity) {
        this(items, new MultiTypePool(initialCapacity));
    }

    public MultiTypeAdapter(@NonNull List<?> items, @NonNull TypePool pool) {
        this.items = items;
        this.typePool = pool;
    }

    public <T> void register(
        @NonNull Class<? extends T> clazz, @NonNull ItemViewBinder<T, ?> binder) {
        checkAndRemoveAllTypesIfNeed(clazz);
        typePool.register(clazz, binder, new DefaultLinker<T>());
    }

    @CheckResult
    public <T> OneToManyFlow<T> register(@NonNull Class<? extends T> clazz) {
        checkAndRemoveAllTypesIfNeed(clazz);
        return new OneToManyBuilder<>(this, clazz);
    }

    public void registerAll(@NonNull final TypePool pool) {
        final int size = pool.size();
        for (int i = 0; i < size; i++) {
            registerWithoutChecking(
                pool.getClass(i),
                pool.getItemViewBinder(i),
                pool.getLinker(i)
            );
        }
    }

    public void setItems(@NonNull List<?> items) {
        this.items = items;
    }


    @NonNull
    public List<?> getItems() {
        return items;
    }

    public void setTypePool(@NonNull TypePool typePool) {
        this.typePool = typePool;
    }


    @NonNull
    public TypePool getTypePool() {
        return typePool;
    }


    @Override
    public final int getItemViewType(int position) {
        Object item = items.get(position);
        return indexInTypesOf(item);
    }


    @Override
    public final RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int indexViewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemViewBinder<?, ?> binder = typePool.getItemViewBinder(indexViewType);
        binder.adapter = this;
        return binder.onCreateViewHolder(inflater, parent);
    }

    @Override @Deprecated
    public final void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        throw new IllegalAccessError("You should not call this method. " +
            "Call RecyclerView.Adapter#onBindViewHolder(holder, position, payloads) instead.");
    }


    @Override @SuppressWarnings("unchecked")
    public final void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List<Object> payloads) {
        Object item = items.get(position);
        ItemViewBinder binder = typePool.getItemViewBinder(holder.getItemViewType());
        binder.onBindViewHolder(holder, item, payloads);
    }


    @Override
    public final int getItemCount() {
        return items.size();
    }

    @Override @SuppressWarnings("unchecked")
    public final long getItemId(int position) {
        Object item = items.get(position);
        int itemViewType = getItemViewType(position);
        ItemViewBinder binder = typePool.getItemViewBinder(itemViewType);
        return binder.getItemId(item);
    }

    @Override @SuppressWarnings("unchecked")
    public final void onViewRecycled(@NonNull RecyclerView.ViewHolder holder) {
        getRawBinderByViewHolder(holder).onViewRecycled(holder);
    }

    @Override @SuppressWarnings("unchecked")
    public final boolean onFailedToRecycleView(@NonNull RecyclerView.ViewHolder holder) {
        return getRawBinderByViewHolder(holder).onFailedToRecycleView(holder);
    }

    @Override @SuppressWarnings("unchecked")
    public final void onViewAttachedToWindow(@NonNull RecyclerView.ViewHolder holder) {
        getRawBinderByViewHolder(holder).onViewAttachedToWindow(holder);
    }

    @Override @SuppressWarnings("unchecked")
    public final void onViewDetachedFromWindow(@NonNull RecyclerView.ViewHolder holder) {
        getRawBinderByViewHolder(holder).onViewDetachedFromWindow(holder);
    }


    @NonNull
    private ItemViewBinder getRawBinderByViewHolder(@NonNull RecyclerView.ViewHolder holder) {
        return typePool.getItemViewBinder(holder.getItemViewType());
    }


    int indexInTypesOf(@NonNull Object item) throws BinderNotFoundException {
        int index = typePool.firstIndexOf(item.getClass());
        if (index != -1) {
            @SuppressWarnings("unchecked")
            Linker<Object> linker = (Linker<Object>) typePool.getLinker(index);
            return index + linker.index(item);
        }
        throw new BinderNotFoundException(item.getClass());
    }


    private void checkAndRemoveAllTypesIfNeed(@NonNull Class<?> clazz) {
        if (typePool.unregister(clazz)) {
            Log.w(TAG, "You have registered the " + clazz.getSimpleName() + " type. " +
                "It will override the original binder(s).");
        }
    }


    <T> void registerWithLinker(
        @NonNull Class<? extends T> clazz,
        @NonNull ItemViewBinder<T, ?> binder,
        @NonNull Linker<T> linker) {
        typePool.register(clazz, binder, linker);
    }

    @SuppressWarnings("unchecked")
    private void registerWithoutChecking(
            @NonNull Class clazz, @NonNull ItemViewBinder itemViewBinder, @NonNull Linker linker) {
        checkAndRemoveAllTypesIfNeed(clazz);
        typePool.register(clazz, itemViewBinder, linker);
    }
}
