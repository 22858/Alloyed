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
                    BlockEntityType.Builder.of(ForgeBlockEntity::new,
                            ModBlocks.FORGE.get()).build(null)
            );

    public static void register() {

    }
}
