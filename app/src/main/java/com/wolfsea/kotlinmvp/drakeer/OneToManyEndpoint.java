package com.wolfsea.kotlinmvp.drakeer;
import androidx.annotation.NonNull;

public interface OneToManyEndpoint<T> {

    void withLinker(@NonNull Linker<T> linker);

    void withClassLinker(@NonNull ClassLinker<T> classLinker);
}
