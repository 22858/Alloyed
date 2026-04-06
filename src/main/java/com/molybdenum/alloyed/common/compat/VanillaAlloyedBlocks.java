package com.molybdenum.alloyed.common.compat;

import com.molybdenum.alloyed.common.registry.BlockEntry;
import com.simibubi.create.content.decoration.MetalLadderBlock;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.List;

import static com.molybdenum.alloyed.common.compat.create.CreateCompat.registerBronzePillarSet;
import static com.molybdenum.alloyed.common.registry.ModBlocks.registerBlock;

public class VanillaAlloyedBlocks {
	public static final BlockEntry<LadderBlock> STEEL_LADDER = registerBlock("steel_ladder", LadderBlock::new, BlockBehaviour.Properties.of().noOcclusion());

	public static final List<BlockEntry<? extends Block>> BRONZE_PILLAR = registerBronzePillarSet("bronze_pillar", WeatheringCopper.WeatherState.UNAFFECTED);
	public static final List<BlockEntry<? extends Block>> EXPOSED_BRONZE_PILLAR = registerBronzePillarSet("exposed_bronze_pillar", WeatheringCopper.WeatherState.EXPOSED);
	public static final List<BlockEntry<? extends Block>> WEATHERED_BRONZE_PILLAR = registerBronzePillarSet("weathered_bronze_pillar", WeatheringCopper.WeatherState.WEATHERED);
	public static final List<BlockEntry<? extends Block>> OXIDIZED_BRONZE_PILLAR = registerBronzePillarSet("oxidized_bronze_pillar", WeatheringCopper.WeatherState.OXIDIZED);

	public static final BlockEntry<Block> STEEL_SHEET_METAL = registerBlock("steel_sheet_metal",Block::new, Blocks.IRON_BLOCK);

	public static final BlockEntry<StairBlock> STEEL_SHEET_STAIRS = registerBlock("steel_sheet_stairs", properties ->
			new StairBlock(Blocks.BRICK_STAIRS.defaultBlockState(), properties));

	public static final BlockEntry<SlabBlock> STEEL_SHEET_SLAB = registerBlock("steel_sheet_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK));


	public static void register() {

	}
}
