package com.molybdenum.alloyed.common.util;

//? fabric {
import net.fabricmc.fabric.api.registry.OxidizableBlocksRegistry;
import net.fabricmc.loader.api.FabricLoader;
//?} else {
/*import net.neoforged.fml.ModList;
*///?}
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public class Platform {
	public static boolean isLoaded(String mod) {
		//? fabric
		return FabricLoader.getInstance().isModLoaded(mod);
		//? neoforge
		/*return ModList.get().isLoaded(mod);*/
	}

	public static void addWaxable(Block block, Block waxedBlock) {
		//? fabric
		OxidizableBlocksRegistry.registerWaxable(block, waxedBlock);
	}

	public static void addWeathering(Block block, Block waxedBlock) {
		//? fabric
		OxidizableBlocksRegistry.registerNextStage(block, waxedBlock);
	}
}
