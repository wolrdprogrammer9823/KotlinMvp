package com.wolfsea.kotlinmvp.drakeer;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;

public interface Linker<T> {

    @IntRange(from = 0)
    int index(@NonNull T t);
}
