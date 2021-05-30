package com.wolfsea.kotlinmvp.drakeer;
import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;

public interface OneToManyFlow<T> {

    @NonNull
    @CheckResult
    @SuppressWarnings("unchecked")
    OneToManyEndpoint<T> to(@NonNull ItemViewBinder<T, ?>... binders);
}
