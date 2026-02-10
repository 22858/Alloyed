package com.molybdenum.alloyed.common.content.blocks;

import com.zurrtum.create.AllBlockEntityTypes;
import com.zurrtum.create.content.kinetics.base.KineticBlockEntity;
import com.zurrtum.create.content.kinetics.simpleRelays.encased.EncasedShaftBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.Supplier;

public class AlloyedShaftBlock extends EncasedShaftBlock {
    public AlloyedShaftBlock(Properties properties, Supplier<Block> casing) {
        super(properties, casing.get());
    }

    public BlockEntityType<? extends KineticBlockEntity> getBlockEntityType() {
        return AllBlockEntityTypes.ENCASED_SHAFT;
    }
}
