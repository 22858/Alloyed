package com.molybdenum.alloyed.common.content.extensions;

public interface BeltBlockEntityExtension {
    void create_alloyed$setAlloyedCasingType(AlloyedCasingType value);
    void create_alloyed$setAlloyedCasingTypeRaw(AlloyedCasingType value);

    AlloyedCasingType getAlloyedCasingType();

    boolean create_alloyed$isCovered();

    enum AlloyedCasingType {
        NONE, STEEL, BRONZE
    }
}
