package com.molybdenum.alloyed.common.compat.create;

import com.molybdenum.alloyed.common.content.blocks.AlloyedShaftBlock;
import com.molybdenum.alloyed.common.content.blocks.WeatheringBronzePillarBlock;
import com.molybdenum.alloyed.common.registry.BlockEntry;
import com.molybdenum.alloyed.common.registry.ModBlocks;
import com.molybdenum.alloyed.common.registry.ModSpriteShifts;
import com.molybdenum.alloyed.common.util.Platform;
import com.zurrtum.create.client.foundation.block.connected.CTSpriteShiftEntry;
import com.zurrtum.create.content.decoration.MetalLadderBlock;
import com.zurrtum.create.content.decoration.MetalScaffoldingBlock;
import com.zurrtum.create.content.decoration.encasing.CasingBlock;
import com.zurrtum.create.content.decoration.palettes.ConnectedPillarBlock;
import com.zurrtum.create.content.kinetics.simpleRelays.encased.EncasedCogwheelBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.List;

import static com.molybdenum.alloyed.common.registry.ModBlocks.registerBlock;

public class CreateAlloyedBlocks {
	// bronze
	public static final BlockEntry<CasingBlock> BRONZE_CASING = registerBlock("bronze_casing", CasingBlock::new, ModBlocks.bronzeProperties());

	public static final BlockEntry<AlloyedShaftBlock> BRONZE_ENCASED_SHAFT = registerBlock("bronze_encased_shaft", p -> new AlloyedShaftBlock(p, CreateAlloyedBlocks.BRONZE_CASING::get), ModBlocks.bronzeProperties(), false);

	public static final BlockEntry<EncasedCogwheelBlock> BRONZE_ENCASED_COGWHEEL = registerBlock("bronze_encased_cogwheel", p -> new EncasedCogwheelBlock(p, false, CreateAlloyedBlocks.BRONZE_CASING.get()), ModBlocks.bronzeProperties(), false);

	public static final BlockEntry<EncasedCogwheelBlock> BRONZE_ENCASED_LARGE_COGWHEEL = registerBlock("bronze_encased_large_cogwheel", p -> new EncasedCogwheelBlock(p, true, CreateAlloyedBlocks.BRONZE_CASING.get()), ModBlocks.bronzeProperties(), false);

	// steel
	public static final BlockEntry<CasingBlock> STEEL_CASING = registerBlock("steel_casing", CasingBlock::new, ModBlocks.steelProperties(BlockBehaviour.Properties.of()));

	public static final BlockEntry<AlloyedShaftBlock> STEEL_ENCASED_SHAFT = registerBlock("steel_encased_shaft", p -> new AlloyedShaftBlock(p, CreateAlloyedBlocks.STEEL_CASING::get), ModBlocks.steelProperties(), false);

	public static final BlockEntry<EncasedCogwheelBlock> STEEL_ENCASED_COGWHEEL = registerBlock("steel_encased_cogwheel", p -> new EncasedCogwheelBlock(p, false, CreateAlloyedBlocks.STEEL_CASING.get()), ModBlocks.steelProperties(), false);

	public static final BlockEntry<EncasedCogwheelBlock> STEEL_ENCASED_LARGE_COGWHEEL = registerBlock("steel_encased_large_cogwheel", p -> new EncasedCogwheelBlock(p, true, CreateAlloyedBlocks.STEEL_CASING.get()), ModBlocks.steelProperties(), false);

	public static final BlockEntry<MetalLadderBlock> STEEL_LADDER = registerBlock("steel_ladder", MetalLadderBlock::new, BlockBehaviour.Properties.of());

	public static final BlockEntry<MetalScaffoldingBlock> STEEL_SCAFFOLD =
			registerBlock("steel_scaffolding", MetalScaffoldingBlock::new);

	public static void register() {

	}
}
