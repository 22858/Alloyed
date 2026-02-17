package com.molybdenum.alloyed.common.compat.farmersdelight;

import com.molybdenum.alloyed.common.registry.FDCompatItems;
import vectorwing.farmersdelight.common.block.entity.dispenser.CuttingBoardDispenseBehavior;

public class SteelKnifeDispenseBehaviour {
    public static void register() {
        CuttingBoardDispenseBehavior
                .registerBehaviour(FDCompatItems.STEEL_KNIFE.get(), new CuttingBoardDispenseBehavior());
    }
}