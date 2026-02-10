//
//package com.molybdenum.alloyed.common.registry;
//
//import com.molybdenum.alloyed.Alloyed;
//import com.molybdenum.alloyed.common.util.CCStress;
//import com.zurrtum.create.AllBlocks;
//import com.zurrtum.create.AllBlockTags;
//import com.zurrtum.create.AllItemTags;
//import com.zurrtum.create.Create;
//import com.zurrtum.create.content.decoration.MetalScaffoldingBlock;
//import com.zurrtum.create.content.decoration.MetalScaffoldingBlockItem;
//import com.zurrtum.create.client.content.decoration.MetalScaffoldingCTBehaviour;
//import com.zurrtum.create.content.decoration.encasing.CasingBlock;
//import com.zurrtum.create.client.content.decoration.encasing.EncasedCTBehaviour;
//import com.zurrtum.create.content.kinetics.base.RotatedPillarKineticBlock;
//import com.zurrtum.create.client.content.kinetics.simpleRelays.encased.EncasedCogCTBehaviour;
//import com.zurrtum.create.content.kinetics.simpleRelays.encased.EncasedCogwheelBlock;
//import com.zurrtum.create.content.kinetics.simpleRelays.encased.EncasedShaftBlock;
//import com.zurrtum.create.client.foundation.block.connected.CTSpriteShiftEntry;
//import com.zurrtum.create.client.foundation.data.AssetLookup;
//import com.zurrtum.create.client.foundation.data.CreateRegistrate;
//import com.zurrtum.create.client.foundation.data.SharedProperties;
//import com.tterrag.registrate.builders.BlockBuilder;
//import com.tterrag.registrate.util.DataIngredient;
//import com.tterrag.registrate.util.nullness.NonNullUnaryOperator;
////? fabric
//import io.github.fabricators_of_create.porting_lib.models.generators.ConfiguredModel;
//import net.minecraft.client.renderer.RenderType;
//import net.minecraft.core.Direction;
//import net.minecraft.data.recipes.RecipeCategory;
//import net.minecraft.resources.Identifier;
//import net.minecraft.tags.BlockTags;
//import net.minecraft.world.level.ItemLike;
//import net.minecraft.world.level.block.Block;
//import net.minecraft.world.level.block.Blocks;
//import net.minecraft.world.level.block.SoundType;
//import net.minecraft.world.level.block.state.BlockBehaviour;
//import net.minecraft.world.level.material.MapColor;
////? neoforge
///*import net.neoforged.neoforge.client.model.generators.ConfiguredModel;*/
//
//import java.util.function.Supplier;
//
//import static com.zurrtum.create.foundation.data.BlockStateGen.axisBlock;
//import static com.zurrtum.create.foundation.data.CreateRegistrate.casingConnectivity;
//import static com.zurrtum.create.foundation.data.CreateRegistrate.connectedTextures;
//import static com.zurrtum.create.foundation.data.TagGen.axeOrPickaxe;
//import static com.zurrtum.create.foundation.data.TagGen.pickaxeOnly;
//
//public class ModTransformers {
//    public static <B extends EncasedShaftBlock, P> NonNullUnaryOperator<BlockBuilder<B, P>> encasedShaft(String casing,
//                                                                                                         Supplier<CTSpriteShiftEntry> casingShift) {
//        return builder -> encasedBase(builder, AllBlocks.SHAFT::get)
//                .onRegister(CreateRegistrate.connectedTextures(() -> new EncasedCTBehaviour(casingShift.get())))
//                .onRegister(CreateRegistrate.casingConnectivity((block, cc) -> cc.make(block, casingShift.get(),
//                        (s, f) -> f.getAxis() != s.getValue(EncasedShaftBlock.AXIS))))
//                .blockstate((c, p) -> axisBlock(c, p, blockState -> p.models()
//                        .getExistingFile(p.modLoc("block/encased_shaft/block_" + casing)), true))
//                .item()
//                .model(AssetLookup.customBlockItemModel("encased_shaft", "item_" + casing))
//                .build();
//    }
//
//    public static <B extends EncasedCogwheelBlock, P> NonNullUnaryOperator<BlockBuilder<B, P>> encasedCogwheel(
//            String casing, Supplier<CTSpriteShiftEntry> casingShift) {
//        return b -> encasedCogwheelBase(b, casing, casingShift, AllBlocks.COGWHEEL::get, false);
//    }
//
//    public static <B extends EncasedCogwheelBlock, P> NonNullUnaryOperator<BlockBuilder<B, P>> encasedLargeCogwheel(
//            String casing, Supplier<CTSpriteShiftEntry> casingShift) {
//        return b -> encasedCogwheelBase(b, casing, casingShift, AllBlocks.LARGE_COGWHEEL::get, true)
//                .onRegister(CreateRegistrate.connectedTextures(() -> new EncasedCogCTBehaviour(casingShift.get())));
//    }
//
//    private static <B extends EncasedCogwheelBlock, P> BlockBuilder<B, P> encasedCogwheelBase(BlockBuilder<B, P> b,
//                                                                                              String casing, Supplier<CTSpriteShiftEntry> casingShift, Supplier<ItemLike> drop, boolean large) {
//        String encasedSuffix = "_encased_cogwheel_side" + (large ? "_connected" : "");
//        String blockFolder = large ? "encased_large_cogwheel" : "encased_cogwheel";
//        String wood = casing.equals("brass") ? "dark_oak" : "spruce";
//        String gearbox = casing.equals("brass") ? "brass_gearbox" : "gearbox";
//        return encasedBase(b, drop).addLayer(() -> RenderType::cutoutMipped)
//                .onRegister(CreateRegistrate.casingConnectivity((block, cc) -> cc.make(block, casingShift.get(),
//                        (s, f) -> f.getAxis() == s.getValue(EncasedCogwheelBlock.AXIS)
//                                && !s.getValue(f.getAxisDirection() == Direction.AxisDirection.POSITIVE ? EncasedCogwheelBlock.TOP_SHAFT
//                                : EncasedCogwheelBlock.BOTTOM_SHAFT))))
//                .blockstate((c, p) -> axisBlock(c, p, blockState -> {
//                    String suffix = (blockState.getValue(EncasedCogwheelBlock.TOP_SHAFT) ? "_top" : "")
//                            + (blockState.getValue(EncasedCogwheelBlock.BOTTOM_SHAFT) ? "_bottom" : "");
//                    String modelName = c.getName() + suffix;
//                    return p.models()
//                            .withExistingParent(modelName, p.modLoc("block/" + blockFolder + "/block" + suffix))
//                            .texture("casing", Create.asResource("block/" + casing + "_casing"))
//                            .texture("particle", Create.asResource("block/" + casing + "_casing"))
//                            .texture("4", Create.asResource("block/" + gearbox))
//                            .texture("1", Alloyed.asVanillaResource("block/stripped_" + wood + "_log_top"))
//                            .texture("side", Create.asResource("block/" + casing + encasedSuffix));
//                }, false))
//                .item()
//                .model((c, p) -> p.withExistingParent(c.getName(), p.modLoc("block/" + blockFolder + "/item"))
//                        .texture("casing", Create.asResource("block/" + casing + "_casing"))
//                        .texture("particle", Create.asResource("block/" + casing + "_casing"))
//                        .texture("1", Alloyed.asVanillaResource("block/stripped_" + wood + "_log_top"))
//                        .texture("side", Create.asResource("block/" + casing + encasedSuffix)))
//                .build();
//    }
//
//    private static <B extends RotatedPillarKineticBlock, P> BlockBuilder<B, P> encasedBase(BlockBuilder<B, P> b,
//                                                                                           Supplier<ItemLike> drop) {
//        return b.initialProperties(SharedProperties::stone)
//                .properties(BlockBehaviour.Properties::noOcclusion)
//                .transform(CCStress.setNoImpact())
//                .loot((p, lb) -> p.dropOther(lb, drop.get()));
//    }
//
//    public static <B extends CasingBlock> NonNullUnaryOperator<BlockBuilder<B, CreateRegistrate>> casing(
//            Supplier<CTSpriteShiftEntry> ct) {
//        return b -> b.initialProperties(SharedProperties::stone)
//                .properties(p -> p.sound(SoundType.WOOD))
//                .transform(axeOrPickaxe())
//                .blockstate((c, p) -> p.simpleBlock(c.get()))
//                .onRegister(connectedTextures(() -> new EncasedCTBehaviour(ct.get())))
//                .onRegister(casingConnectivity((block, cc) -> cc.makeCasing(block, ct.get())))
//                .tag(AllBlockTags.CASING)
//                .item()
//                .tag(AllItemTags.CASING)
//                .build();
//    }
//
//    public static <B extends Block, P> NonNullUnaryOperator<BlockBuilder<B, P>> scaffold(String name,
//                                                                                         Supplier<DataIngredient> ingredient, MapColor color, CTSpriteShiftEntry scaffoldShift,
//                                                                                         CTSpriteShiftEntry scaffoldInsideShift, CTSpriteShiftEntry casingShift) {
//        return b -> b.initialProperties(() -> Blocks.SCAFFOLDING)
//                .properties(p -> p.sound(SoundType.COPPER)
//                        .mapColor(color))
//                .addLayer(() -> RenderType::cutout)
//                .blockstate((c, p) -> p.getVariantBuilder(c.get())
//                        .forAllStatesExcept(s -> {
//                            String suffix = s.getValue(MetalScaffoldingBlock.BOTTOM) ? "_horizontal" : "";
//                            return ConfiguredModel.builder()
//                                    .modelFile(p.models()
//                                            .withExistingParent(c.getName() + suffix, p.modLoc("block/scaffold/block" + suffix))
//                                            .texture("top", p.modLoc("block/funnel/" + name + "_funnel_frame"))
//                                            .texture("inside", p.modLoc("block/scaffold/" + name + "_scaffold_inside"))
//                                            .texture("side", p.modLoc("block/scaffold/" + name + "_scaffold"))
//                                            .texture("casing", p.modLoc("block/" + name + "_casing"))
//                                            .texture("particle", p.modLoc("block/scaffold/" + name + "_scaffold")))
//                                    .build();
//                        }, MetalScaffoldingBlock.WATERLOGGED, MetalScaffoldingBlock.DISTANCE))
//                .onRegister(connectedTextures(
//                        () -> new MetalScaffoldingCTBehaviour(scaffoldShift, scaffoldInsideShift, casingShift)))
//                .transform(pickaxeOnly())
//                .tag(BlockTags.CLIMBABLE)
//                .item(MetalScaffoldingBlockItem::new)
//                .recipe((c, p) -> p.stonecutting(ingredient.get(), RecipeCategory.DECORATIONS, c::get, 2))
//                .model((c, p) -> p.withExistingParent(c.getName(), p.modLoc("block/" + c.getName())))
//                .build();
//    }
//}