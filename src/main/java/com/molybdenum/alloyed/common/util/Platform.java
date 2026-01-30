package com.molybdenum.alloyed.common.util;

//? fabric {
import net.fabricmc.fabric.api.registry.OxidizableBlocksRegistry;
import net.fabricmc.loader.api.FabricLoader;
//?} else {
/*import com.simibubi.create.foundation.block.CopperRegistries;
import net.minecraftforge.fml.ModList;
*///?}
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public class Platform {
	public static boolean isLoaded(String mod) {
		//? fabric
		return FabricLoader.getInstance().isModLoaded(mod);
		//? forge
		/*return ModList.get().isLoaded(mod);*/
	}

	public static void addWaxable(Supplier<? extends Block> block, Supplier<? extends Block> waxedBlock) {
		//? forge
		/*CopperRegistries.addWaxable((Supplier<Block>) block, (Supplier<Block>) waxedBlock);*/
		//? fabric
		OxidizableBlocksRegistry.registerOxidizableBlockPair(block.get(), waxedBlock.get());
	}
}
