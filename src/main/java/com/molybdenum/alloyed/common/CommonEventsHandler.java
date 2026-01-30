package com.molybdenum.alloyed.common;

import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.common.compat.farmersdelight.FarmersDelightCompat;
import com.simibubi.create.foundation.block.CopperRegistries;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

import static com.molybdenum.alloyed.common.registry.ModBlocks.*;

public class CommonEventsHandler {

    // Common setup
    public static void setupCommon() {
        if (Alloyed.isFarmersDelightLoaded)
            FarmersDelightCompat.steelKnifeDispenseBehaviour();
        CopperRegistries.addWeathering((Supplier<Block>) CUT_BRONZE.get(0), (Supplier<Block>) CUT_EXPOSED_BRONZE.get(0));
        CopperRegistries.addWeathering((Supplier<Block>) CUT_EXPOSED_BRONZE.get(0), (Supplier<Block>) CUT_WEATHERED_BRONZE.get(0));
        CopperRegistries.addWeathering((Supplier<Block>) CUT_WEATHERED_BRONZE.get(0), (Supplier<Block>) CUT_OXIDIZED_BRONZE.get(0));

        CopperRegistries.addWeathering((Supplier<Block>) BRONZE_PILLAR.get(0), (Supplier<Block>) EXPOSED_BRONZE_PILLAR.get(0));
        CopperRegistries.addWeathering((Supplier<Block>) EXPOSED_BRONZE_PILLAR.get(0), (Supplier<Block>) WEATHERED_BRONZE_PILLAR.get(0));
        CopperRegistries.addWeathering((Supplier<Block>) WEATHERED_BRONZE_PILLAR.get(0), (Supplier<Block>) OXIDIZED_BRONZE_PILLAR.get(0));
    }
}
