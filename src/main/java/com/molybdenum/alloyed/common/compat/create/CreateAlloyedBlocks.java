package com.molybdenum.alloyed.common.compat.create;

import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.client.registry.ModTransformers;
import com.molybdenum.alloyed.common.compat.create.connected.SteelSheetMetalCTBehaviour;
import com.molybdenum.alloyed.common.compat.create.connected.SteelSheetSlabCTBehaviour;
import com.molybdenum.alloyed.common.content.blocks.SteelDoorBlock;
import com.molybdenum.alloyed.common.content.blocks.AlloyedShaftBlock;
import com.molybdenum.alloyed.common.content.blocks.WeatheringBronzePillarBlock;
import com.molybdenum.alloyed.common.registry.ModBlocks;
import com.molybdenum.alloyed.common.registry.ModSpriteShifts;
import com.simibubi.create.AllTags;
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
import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.util.DataIngredient;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.createmod.catnip.data.Couple;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@SuppressWarnings({"unused"})
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
			.properties(ModBlocks::steelProperties)
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

	public static final BlockEntry<FenceBlock> STEEL_MESH_FENCE = REGISTRATE
			.block("steel_mesh_fence", FenceBlock::new)
			.initialProperties(() -> Blocks.IRON_BLOCK)
			.properties(properties -> properties.sound(SoundType.CHAIN))
			.item()
			.build()
			.register();


	public static void register() {
		Alloyed.LOGGER.debug("Registering ModBlocks!");
	}

	public static BlockBehaviour.@NotNull Properties steelProperties(BlockBehaviour.Properties properties) {
		return properties.sound(SoundType.NETHERITE_BLOCK).strength(5, 14).mapColor(MapColor.COLOR_GRAY);
	}

	public static BlockBehaviour.@NotNull Properties bronzeProperties(BlockBehaviour.Properties properties) {
		return properties.sound(SoundType.COPPER).strength(3, 6).mapColor(MapColor.COLOR_ORANGE);
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


	private static List<BlockEntry<? extends Block>> registerCutBronzeSet(String id, WeatheringCopper.WeatherState state) {
		var block = registerCutBronze(id, state);
		var stairs = registerCutBronzeStairs(id, state);
		var slab = registerCutBronzeSlab(id, state);
		var waxedBlock = REGISTRATE
				.block("waxed_"+id,(Block::new))
				.initialProperties(() -> Blocks.CUT_COPPER)
				.properties(ModBlocks::steelProperties)
				.simpleItem()
				.onRegister(CreateRegistrate.connectedTextures(SteelSheetMetalCTBehaviour::new))
				.register();
		var waxedStairs = REGISTRATE
				.block("waxed_"+id+"_stairs", properties ->
						new WeatheringCopperStairBlock(state, Blocks.BRICK_STAIRS.defaultBlockState(), properties))
				.initialProperties(() -> Blocks.CUT_COPPER)
				.properties(ModBlocks::steelProperties)
				.item().build()
				.onRegister(CreateRegistrate.connectedTextures(SteelSheetMetalCTBehaviour::new))
				.register();
		var waxedSlab = REGISTRATE
				.block("waxed_"+id+"_slab", SlabBlock::new)
				.initialProperties(() -> Blocks.CUT_COPPER)
				.properties(ModBlocks::steelProperties)
				.item().build()
				.onRegister(CreateRegistrate.connectedTextures(SteelSheetSlabCTBehaviour::new))
				.register();
		return List.of(block, stairs, slab, waxedBlock, waxedStairs, waxedSlab);
	}

	private static BlockEntry<? extends Block> registerCutBronze(String id, WeatheringCopper.WeatherState state) {
		return REGISTRATE
				.block(id,(properties -> new WeatheringCopperFullBlock(state, properties)))
				.initialProperties(() -> Blocks.CUT_COPPER)
				.properties(ModBlocks::steelProperties)
				.simpleItem()
				.onRegister(CreateRegistrate.connectedTextures(SteelSheetMetalCTBehaviour::new))
				.register();
	}

	private static BlockEntry<? extends SlabBlock> registerCutBronzeSlab(String id, WeatheringCopper.WeatherState state) {
		return REGISTRATE
				.block(id+"_slab", (p)-> new WeatheringCopperSlabBlock(state, p))
				.initialProperties(() -> Blocks.CUT_COPPER)
				.properties(ModBlocks::steelProperties)
				.item().build()
				.onRegister(CreateRegistrate.connectedTextures(SteelSheetSlabCTBehaviour::new))
				.register();
	}

	private static BlockEntry<WeatheringCopperStairBlock> registerCutBronzeStairs(String id, WeatheringCopper.WeatherState state) {
		return REGISTRATE
				.block(id+"_stairs", properties ->
						new WeatheringCopperStairBlock(state, Blocks.BRICK_STAIRS.defaultBlockState(), properties))
				.initialProperties(() -> Blocks.CUT_COPPER)
				.properties(ModBlocks::steelProperties)
				.item().build()
				.onRegister(CreateRegistrate.connectedTextures(SteelSheetMetalCTBehaviour::new))
				.register();
	}

	private static BlockBuilder<SteelDoorBlock, CreateRegistrate> steelDoorBlock(boolean locked, BlockEntry<SteelDoorBlock> normalDoor) {
		String path = "block/" + (locked ? "locked_" : "") + "steel_door/";
		String name = (locked ? "locked_" : "") + "steel_door";

		return REGISTRATE
				.block(name, properties -> new SteelDoorBlock(properties, locked)).item().build()
				.properties(properties -> properties
						.noOcclusion()
						.sound(SoundType.METAL)
						.strength(5)
						.requiresCorrectToolForDrops());
	}
}