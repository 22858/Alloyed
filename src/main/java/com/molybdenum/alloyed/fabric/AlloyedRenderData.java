package com.molybdenum.alloyed.fabric;

import com.molybdenum.alloyed.common.content.extensions.BeltBlockEntityExtension;
import com.simibubi.create.content.kinetics.belt.BeltBlockEntity;

public record AlloyedRenderData(BeltBlockEntityExtension.AlloyedCasingType casingType, boolean covered) {
}
