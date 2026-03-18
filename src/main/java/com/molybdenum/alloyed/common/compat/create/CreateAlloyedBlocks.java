package com.molybdenum.alloyed.common.compat.create;

import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.client.registry.ModTransformers;
import com.molybdenum.alloyed.common.compat.create.connected.SteelSheetMetalCTBehaviour;
import com.molybdenum.alloyed.common.compat.create.connected.SteelSheetSlabCTBehaviour;
import com.molybdenum.alloyed.common.content.blocks.AlloyedShaftBlock;
import com.molybdenum.alloyed.common.content.blocks.WeatheringBronzePillarBlock;
import com.molybdenum.alloyed.common.registry.ModBlocks;
import com.molybdenum.alloyed.common.registry.ModSpriteShifts;
import com.molybdenum.alloyed.common.registry.ModTags;
import com.simibubi.create.AllTags;
import com.simibubi.create.content.decoration.MetalLadderBlock;
import com.simibubi.create.content.decoration.MetalScaffoldingBlock;
import com.simibubi.create.content.decoration.encasing.CasingBlock;
import com.simibubi.create.content.decoration.palettes.ConnectedPillarBlock;
import com.simibubi.create.content.kinetics.simpleRelays.encased.EncasedCogCTBehaviour;
import com.simibubi.create.content.kinetics.simpleRelays.encased.EncasedCogwheelBlock;
import com.simibubi.create.foundation.block.CopperBlockSet;
import com.simibubi.create.foundation.block.connected.CTSpriteShiftEntry;
import com.simibubi.create.foundation.block.connected.RotatedPillarCTBehaviour;
import com.simibubi.create.foundation.data.BuilderTransformers;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.data.TagGen;
import com.tterrag.registrate.util.DataIngredient;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.createmod.catnip.data.Couple;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.material.MapColor;

import java.util.List;

@SuppressWarnings("unused")
public class CreateAlloyedBlocks {

	public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(Alloyed.MOD_ID);

	// BRONZE

	public static final CopperBlockSet BRONZE_BLOCKS = new CopperBlockSet( // Ignore that it says COPPER block set. The code works for any oxidizing metal.
			REGISTRATE,
			"bronze_block",
			"bronze_block",
			new CopperBlockSet.Variant<?>[] { CopperBlockSet.BlockVariant.INSTANCE },
			"bronze/"
	);

	public static final List<BlockEntry<? extends Block>> BRONZE_PILLAR = registerBronzePillarSet("bronze_pillar", WeatheringCopper.WeatherState.UNAFFECTED, ModSpriteShifts.BRONZE_PILLAR, ModSpriteShifts.BRONZE_CAP);
	public static final List<BlockEntry<? extends Block>> EXPOSED_BRONZE_PILLAR = registerBronzePillarSet("exposed_bronze_pillar", WeatheringCopper.WeatherState.EXPOSED, ModSpriteShifts.EXPOSED_BRONZE_PILLAR, ModSpriteShifts.EXPOSED_BRONZE_CAP);
	public static final List<BlockEntry<? extends Block>> WEATHERED_BRONZE_PILLAR = registerBronzePillarSet("weathered_bronze_pillar", WeatheringCopper.WeatherState.WEATHERED, ModSpriteShifts.WEATHERED_BRONZE_PILLAR, ModSpriteShifts.WEATHERED_BRONZE_CAP);
	public static final List<BlockEntry<? extends Block>> OXIDIZED_BRONZE_PILLAR = registerBronzePillarSet("oxidized_bronze_pillar", WeatheringCopper.WeatherState.OXIDIZED, ModSpriteShifts.OXIDIZED_BRONZE_PILLAR, ModSpriteShifts.OXIDIZED_BRONZE_CAP);

	public static final BlockEntry<CasingBlock> BRONZE_CASING = REGISTRATE.block("bronze_casing", CasingBlock::new)
			.transform(BuilderTransformers.casing(() -> ModSpriteShifts.BRONZE_CASING))
			.properties(ModBlocks::bronzeProperties).item().build()
			.register();

	public static final BlockEntry<AlloyedShaftBlock> BRONZE_ENCASED_SHAFT = REGISTRATE
			.block("bronze_encased_shaft", p -> new AlloyedShaftBlock(p, BRONZE_CASING::get))
			.properties(ModBlocks::bronzeProperties)
			.transform(ModTransformers.encasedShaft("bronze", () -> ModSpriteShifts.BRONZE_CASING))
			.transform(TagGen.axeOrPickaxe())
			.register();

	public static final BlockEntry<EncasedCogwheelBlock> BRONZE_ENCASED_COGWHEEL = REGISTRATE
			.block("bronze_encased_cogwheel", p -> new EncasedCogwheelBlock(p, false, BRONZE_CASING::get))
			.properties(ModBlocks::bronzeProperties)
			.transform(ModTransformers.encasedCogwheel("bronze", () -> ModSpriteShifts.BRONZE_CASING))
			.item()
			.build()
			.onRegister(CreateRegistrate.connectedTextures(() -> new EncasedCogCTBehaviour(ModSpriteShifts.BRONZE_CASING,
					Couple.create(ModSpriteShifts.BRONZE_ENCASED_COGWHEEL_SIDE,
							ModSpriteShifts.BRONZE_ENCASED_COGWHEEL_OTHERSIDE))))
			.register();


	public static final BlockEntry<EncasedCogwheelBlock> BRONZE_ENCASED_LARGE_COGWHEEL = REGISTRATE
			.block("bronze_encased_large_cogwheel", p -> new EncasedCogwheelBlock(p, true, BRONZE_CASING::get))
			.properties(ModBlocks::bronzeProperties)
			.transform(ModTransformers.encasedLargeCogwheel("bronze", () -> ModSpriteShifts.BRONZE_CASING))
			.item()
			.build()
			.register();


	// STEEL

	public static final BlockEntry<CasingBlock> STEEL_CASING = REGISTRATE.block("steel_casing", CasingBlock::new)
			.transform(BuilderTransformers.casing(() -> ModSpriteShifts.STEEL_CASING))
			.properties(ModBlocks::steelProperties).item().build()
			.register();

	public static final BlockEntry<AlloyedShaftBlock> STEEL_ENCASED_SHAFT = REGISTRATE
			.block("steel_encased_shaft", p -> new AlloyedShaftBlock(p, STEEL_CASING::get))
			.properties(ModBlocks::steelProperties)
			.transform(ModTransformers.encasedShaft("steel", () -> ModSpriteShifts.STEEL_CASING))
			.register();

	public static final BlockEntry<EncasedCogwheelBlock> STEEL_ENCASED_COGWHEEL = REGISTRATE
			.block("steel_encased_cogwheel", p -> new EncasedCogwheelBlock(p, false, STEEL_CASING::get))
			.properties(ModBlocks::steelProperties)
			.transform(ModTransformers.encasedCogwheel("steel", () -> ModSpriteShifts.STEEL_CASING))
			.onRegister(CreateRegistrate.connectedTextures(() -> new EncasedCogCTBehaviour(ModSpriteShifts.STEEL_CASING,
					Couple.create(ModSpriteShifts.STEEL_ENCASED_COGWHEEL_SIDE,
							ModSpriteShifts.STEEL_ENCASED_COGWHEEL_OTHERSIDE))))
			.register();


	public static final BlockEntry<EncasedCogwheelBlock> STEEL_ENCASED_LARGE_COGWHEEL = REGISTRATE
			.block("steel_encased_large_cogwheel", p -> new EncasedCogwheelBlock(p, true, STEEL_CASING::get))
			.properties(ModBlocks::steelProperties)
			.transform(ModTransformers.encasedLargeCogwheel("steel", () -> ModSpriteShifts.STEEL_CASING))
			.register();


	public static final BlockEntry<MetalScaffoldingBlock> STEEL_SCAFFOLD =
			REGISTRATE.block("steel_scaffolding", MetalScaffoldingBlock::new)
					.transform(ModTransformers.scaffold("steel",
							() -> DataIngredient.tag(AllTags.commonItemTag("ingots/steel")), MapColor.COLOR_GRAY,
							ModSpriteShifts.STEEL_SCAFFOLD, ModSpriteShifts.STEEL_SCAFFOLD_INSIDE, ModSpriteShifts.STEEL_CASING))
					.properties(ModBlocks::steelProperties)
					.item().build()
					.register();

	public static final BlockEntry<Block> STEEL_SHEET_METAL = REGISTRATE
			.block("steel_sheet_metal",Block::new)
			.initialProperties(() -> Blocks.IRON_BLOCK)
			.properties(ModBlocks::steelProperties)
			.simpleItem()
			.onRegister(CreateRegistrate.connectedTextures(SteelSheetMetalCTBehaviour::new))
			.register();

	public static final BlockEntry<StairBlock> STEEL_SHEET_STAIRS = REGISTRATE
			.block("steel_sheet_stairs", properties ->
					new StairBlock(Blocks.BRICK_STAIRS.defaultBlockState(), properties))
			.initialProperties(() -> Blocks.IRON_BLOCK)
			.properties(ModBlocks::steelProperties)
			.item().build()
			.onRegister(CreateRegistrate.connectedTextures(SteelSheetMetalCTBehaviour::new))
			.register();

	public static final BlockEntry<SlabBlock> STEEL_SHEET_SLAB = REGISTRATE
			.block("steel_sheet_slab", SlabBlock::new)
			.initialProperties(() -> Blocks.IRON_BLOCK)
			.properties(ModBlocks::steelProperties)
			.item().build()
			.onRegister(CreateRegistrate.connectedTextures(SteelSheetSlabCTBehaviour::new))
			.register();

	public static final BlockEntry<MetalLadderBlock> STEEL_LADDER = REGISTRATE
			.block("steel_ladder", MetalLadderBlock::new)
			.transform(BuilderTransformers.ladder("steel",
					() -> DataIngredient.tag(ModTags.Items.STEEL_SHEET), MapColor.COLOR_GRAY))
			.item().build()
			.register();

	public static final BlockEntry<FenceBlock> STEEL_MESH_FENCE = REGISTRATE
			.block("steel_mesh_fence", FenceBlock::new)
			.initialProperties(() -> Blocks.IRON_BLOCK)
			.properties(properties -> properties.sound(SoundType.CHAIN))
			.item().build()
			.register();


	public static void register() {
		Alloyed.LOGGER.debug("Registering ModBlocks!");
	}

	private static List<BlockEntry<? extends Block>> registerBronzePillarSet(String id, WeatheringCopper.WeatherState state, CTSpriteShiftEntry pillar, CTSpriteShiftEntry cap) {
		var block = REGISTRATE.block(id, (properties)-> new WeatheringBronzePillarBlock(state, properties))
				.properties(ModBlocks::bronzeProperties).item().build()
				.onRegister(CreateRegistrate.connectedTextures(() -> new RotatedPillarCTBehaviour(pillar, cap)))
				.register();
		var waxedBlock = REGISTRATE.block("waxed_"+id, ConnectedPillarBlock::new)
				.properties(ModBlocks::bronzeProperties).item().build()
				.onRegister(CreateRegistrate.connectedTextures(() -> new RotatedPillarCTBehaviour(pillar, cap)))
				.register();
		return List.of(block, waxedBlock);
	}
}