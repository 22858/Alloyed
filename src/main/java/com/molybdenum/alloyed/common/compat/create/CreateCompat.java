package com.molybdenum.alloyed.common.compat.create;

import com.molybdenum.alloyed.client.ponder.AlloyedPonderPlugin;
import com.molybdenum.alloyed.common.content.blocks.WeatheringBronzePillarBlock;
import com.molybdenum.alloyed.common.registry.BlockEntry;
import com.molybdenum.alloyed.common.registry.ModBlocks;
import com.molybdenum.alloyed.common.util.Platform;
import com.simibubi.create.AllBlockEntityTypes;
import com.simibubi.create.content.decoration.palettes.ConnectedPillarBlock;
import net.createmod.ponder.foundation.PonderIndex;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WeatheringCopper;
//? neoforge {
/*import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;
*///?}

import java.util.List;

import static com.molybdenum.alloyed.common.registry.ModBlocks.registerBlock;

public class CreateCompat {
	public static void registerPonders() {
		PonderIndex.addPlugin(new AlloyedPonderPlugin());
	}

	public static List<BlockEntry<? extends Block>> registerBronzePillarSet(String id, WeatheringCopper.WeatherState state) {
		var block = registerBlock(id, (properties)-> new WeatheringBronzePillarBlock(state, properties), ModBlocks.bronzeProperties());
		var waxedBlock = registerBlock("waxed_"+id, ConnectedPillarBlock::new, ModBlocks.bronzeProperties());
		Platform.addWaxable(block.get(), waxedBlock.get());
		return List.of(block, waxedBlock);
	}

	public static void register(
			//? neoforge
			/*IEventBus eventBus*/
	) {
		CreateAlloyedBlocks.REGISTRATE.registerEventListeners(
				//? neoforge
				/*eventBus*/
		);
	}

	//? fabric {
	public static void addBlocks() {
		AllBlockEntityTypes.ENCASED_COGWHEEL.get().addSupportedBlock(CreateAlloyedBlocks.STEEL_ENCASED_COGWHEEL.get());
		AllBlockEntityTypes.ENCASED_COGWHEEL.get().addSupportedBlock(CreateAlloyedBlocks.BRONZE_ENCASED_COGWHEEL.get());
		AllBlockEntityTypes.ENCASED_LARGE_COGWHEEL.get().addSupportedBlock(CreateAlloyedBlocks.STEEL_ENCASED_LARGE_COGWHEEL.get());
		AllBlockEntityTypes.ENCASED_LARGE_COGWHEEL.get().addSupportedBlock(CreateAlloyedBlocks.BRONZE_ENCASED_LARGE_COGWHEEL.get());
		AllBlockEntityTypes.ENCASED_SHAFT.get().addSupportedBlock(CreateAlloyedBlocks.STEEL_ENCASED_SHAFT.get());
		AllBlockEntityTypes.ENCASED_SHAFT.get().addSupportedBlock(CreateAlloyedBlocks.BRONZE_ENCASED_SHAFT.get());
	}
	//?} else {
	/*public static void addBlocks(final BlockEntityTypeAddBlocksEvent event) {
		event.modify(AllBlockEntityTypes.ENCASED_COGWHEEL.getKey(), CreateAlloyedBlocks.STEEL_ENCASED_COGWHEEL.get(), CreateAlloyedBlocks.BRONZE_ENCASED_COGWHEEL.get());
		event.modify(AllBlockEntityTypes.ENCASED_LARGE_COGWHEEL.getKey(), CreateAlloyedBlocks.STEEL_ENCASED_LARGE_COGWHEEL.get(),  CreateAlloyedBlocks.BRONZE_ENCASED_LARGE_COGWHEEL.get());
		event.modify(AllBlockEntityTypes.ENCASED_SHAFT.getKey(), CreateAlloyedBlocks.STEEL_ENCASED_SHAFT.get(), CreateAlloyedBlocks.BRONZE_ENCASED_SHAFT.get());
	}
	*///?}
}
