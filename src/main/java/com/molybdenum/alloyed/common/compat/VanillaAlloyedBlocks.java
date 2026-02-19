package com.molybdenum.alloyed.common.compat;

import com.molybdenum.alloyed.common.registry.BlockEntry;
import com.simibubi.create.content.decoration.MetalLadderBlock;
import net.minecraft.world.level.block.LadderBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

import static com.molybdenum.alloyed.common.registry.ModBlocks.registerBlock;

public class VanillaAlloyedBlocks {
	public static final BlockEntry<LadderBlock> STEEL_LADDER = registerBlock("steel_ladder", LadderBlock::new, BlockBehaviour.Properties.of().noOcclusion());

	public static void register() {

	}
}
