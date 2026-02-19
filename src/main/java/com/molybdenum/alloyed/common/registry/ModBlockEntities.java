package com.molybdenum.alloyed.common.registry;

import com.molybdenum.alloyed.common.CommonRegistry;
import com.molybdenum.alloyed.common.content.blocks.entities.ForgeBlockEntity;
//? fabric
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.Supplier;

public class ModBlockEntities {
    public static final Supplier<BlockEntityType<ForgeBlockEntity>> FORGE_BLOCK_ENTITY =
            CommonRegistry.registerBlockEntity("forge_block_entity", ()->
                    //? if fabric {
                    FabricBlockEntityTypeBuilder.create(ForgeBlockEntity::new,
                            ModBlocks.FORGE.get()).build()
                    //?} else {
                    /*new BlockEntityType<>(ForgeBlockEntity::new,
                            ModBlocks.FORGE.get())
                    *///?}
            );

    public static void register() {

    }
}
