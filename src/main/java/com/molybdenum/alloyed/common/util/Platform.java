package com.molybdenum.alloyed.common.util;

//? fabric {
import net.fabricmc.fabric.api.registry.OxidizableBlocksRegistry;
import net.fabricmc.loader.api.FabricLoader;
//?} else {
/*import com.simibubi.create.foundation.block.CopperRegistries;
import net.neoforged.fml.ModList;
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

	public static void addWaxable(Holder<? extends Block> block, Holder<? extends Block> waxedBlock) {
		//? neoforge
		/*CopperRegistries.addWaxable((Holder<Block>) block, (Holder<Block>) waxedBlock);*/
		//? fabric
		OxidizableBlocksRegistry.registerWaxableBlockPair(block.value(), waxedBlock.value());
	}

	public static void addWeathering(Holder<? extends Block> block, Holder<? extends Block> waxedBlock) {
		//? neoforge
		/*CopperRegistries.addWeathering((Holder<Block>) block, (Holder<Block>) waxedBlock);*/
		//? fabric
		OxidizableBlocksRegistry.registerOxidizableBlockPair(block.value(), waxedBlock.value());
	}
}
