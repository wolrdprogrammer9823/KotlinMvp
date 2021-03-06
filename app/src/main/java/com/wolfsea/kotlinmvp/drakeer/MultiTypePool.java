package com.wolfsea.kotlinmvp.drakeer;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.List;

public class MultiTypePool implements TypePool {

    private @NonNull
    final List<Class<?>> classes;
    private @NonNull final List<ItemViewBinder<?, ?>> binders;
    private @NonNull final List<Linker<?>> linkers;

    public MultiTypePool() {
        this.classes = new ArrayList<>();
        this.binders = new ArrayList<>();
        this.linkers = new ArrayList<>();
    }


    public MultiTypePool(int initialCapacity) {
        this.classes = new ArrayList<>(initialCapacity);
        this.binders = new ArrayList<>(initialCapacity);
        this.linkers = new ArrayList<>(initialCapacity);
    }

    public MultiTypePool(
        @NonNull List<Class<?>> classes,
        @NonNull List<ItemViewBinder<?, ?>> binders,
        @NonNull List<Linker<?>> linkers) {
        this.classes = classes;
        this.binders = binders;
        this.linkers = linkers;
    }


    @Override
    public <T> void register(
        @NonNull Class<? extends T> clazz,
        @NonNull ItemViewBinder<T, ?> binder,
        @NonNull Linker<T> linker) {
        classes.add(clazz);
        binders.add(binder);
        linkers.add(linker);
    }


    @Override
    public boolean unregister(@NonNull Class<?> clazz) {
        boolean removed = false;
        while (true) {
            int index = classes.indexOf(clazz);
            if (index != -1) {
                classes.remove(index);
                binders.remove(index);
                linkers.remove(index);
                removed = true;
            } else {
                break;
            }
        }
        return removed;
    }


    @Override
    public int size() {
        return classes.size();
    }


    @Override
    public int firstIndexOf(@NonNull final Class<?> clazz) {
        int index = classes.indexOf(clazz);
        if (index != -1) {
            return index;
        }
        for (int i = 0; i < classes.size(); i++) {
            if (classes.get(i).isAssignableFrom(clazz)) {
                return i;
            }
        }
        return -1;
    }


    @NonNull @Override
    public Class<?> getClass(int index) {
        return classes.get(index);
    }


    @NonNull @Override
    public ItemViewBinder<?, ?> getItemViewBinder(int index) {
        return binders.get(index);
    }


    @NonNull @Override
    public Linker<?> getLinker(int index) {
        return linkers.get(index);
    }
}
