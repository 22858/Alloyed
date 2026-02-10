package com.molybdenum.alloyed.common.registry;

import com.molybdenum.alloyed.Alloyed;

import java.util.List;
import java.util.function.Supplier;

public enum ModCompat {
    FARMERS_DELIGHT(ModCompatItems::getFDItems, !Alloyed.isFarmersDelightLoaded);

    private final Supplier<List<ItemEntry<?>>> entries;
    private final boolean shouldHide;

    ModCompat(Supplier<List<ItemEntry<?>>> entries, boolean shouldHide) {
        this.entries = entries;
        this.shouldHide = shouldHide;
    }


    public List<ItemEntry<?>> getEntries () {
        return entries.get();
    }

    public Boolean shouldHide () {
        return shouldHide;
    }
}
