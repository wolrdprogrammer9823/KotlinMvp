package com.wolfsea.kotlinmvp.drakeer;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public final class MultiTypeAsserts {

    private MultiTypeAsserts() {
        throw new AssertionError();
    }

    @SuppressWarnings("unchecked")
    public static void assertAllRegistered(
        @NonNull MultiTypeAdapter adapter,
        @NonNull List<?> items)
        throws BinderNotFoundException, IllegalArgumentException, IllegalAccessError {

        if (items.isEmpty()) {
            throw new IllegalArgumentException("Your Items/List is empty.");
        }
        for (Object item : items) {
            adapter.indexInTypesOf(item);
        }
        /* All passed. */
    }

    public static void assertHasTheSameAdapter(
            @NonNull RecyclerView recyclerView, @NonNull MultiTypeAdapter adapter)
        throws IllegalArgumentException, IllegalAccessError {
        if (recyclerView.getAdapter() == null) {
            throw new IllegalAccessError("The assertHasTheSameAdapter() method must " +
                "be placed after recyclerView.setAdapter()");
        }
        if (recyclerView.getAdapter() != adapter) {
            throw new IllegalArgumentException(
                "Your recyclerView's adapter is not the sample with the argument adapter.");
        }
    }
}
