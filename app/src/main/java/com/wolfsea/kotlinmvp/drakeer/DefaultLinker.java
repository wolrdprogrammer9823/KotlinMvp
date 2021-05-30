package com.wolfsea.kotlinmvp.drakeer;
import androidx.annotation.NonNull;

final class DefaultLinker<T> implements Linker<T> {

    @Override
    public int index(@NonNull T t) {
        return 0;
    }
}
