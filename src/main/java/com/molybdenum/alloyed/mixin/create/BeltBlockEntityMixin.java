package com.molybdenum.alloyed.mixin.create;

import com.molybdenum.alloyed.common.compat.create.CreateAlloyedBlocks;
import com.molybdenum.alloyed.common.content.extensions.BeltBlockEntityExtension;
import com.zurrtum.create.content.kinetics.base.KineticBlockEntity;
import com.zurrtum.create.content.kinetics.belt.BeltBlock;
import com.zurrtum.create.content.kinetics.belt.BeltBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
//? neoforge {
/*import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
*///?}
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BeltBlockEntity.class)
public class BeltBlockEntityMixin extends KineticBlockEntity implements BeltBlockEntityExtension {
    @Shadow(remap=false) public BeltBlockEntity.CasingType casing;
    @Shadow(remap=false) public boolean covered;
    @Unique
    AlloyedCasingType create_alloyed$alloyedCasing = AlloyedCasingType.NONE;

    public BeltBlockEntityMixin(BlockEntityType<?> typeIn, BlockPos pos, BlockState state) {
        super(typeIn, pos, state);
    }

    @Inject(method = "write", at = @At(value = "RETURN"), remap = false)
    private void writeAlloyedCasingNBT(ValueOutput view, boolean clientPacket, CallbackInfo ci) {
        view.store("AlloyedCasing", BeltBlockEntityExtension.AlloyedCasingType.CODEC, create_alloyed$alloyedCasing);
    }

    @Inject(method = "read", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/storage/ValueInput;getBooleanOr(Ljava/lang/String;Z)Z"))
    private void readAlloyedCasingNBT(ValueInput view, boolean clientPacket, CallbackInfo ci) {
        AlloyedCasingType previous = create_alloyed$alloyedCasing;
        create_alloyed$alloyedCasing = view.read("AlloyedCasing", BeltBlockEntityExtension.AlloyedCasingType.CODEC).orElse(AlloyedCasingType.NONE);

        if (!clientPacket) return;
        if (previous == create_alloyed$alloyedCasing) return;

        if (!isVirtual()) {
            //? neoforge
			/*requestModelDataUpdate();*/
		}
        if (hasLevel()) {
            assert level != null;
            level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 16);
        }
    }

    @Inject(
            method = "setCasingType(Lcom/zurrtum/create/content/kinetics/belt/BeltBlockEntity$CasingType;)V",
            at = @At(
                    value = "FIELD",
                    target = "Lcom/zurrtum/create/content/kinetics/belt/BeltBlockEntity;casing:Lcom/zurrtum/create/content/kinetics/belt/BeltBlockEntity$CasingType;",
                    opcode = Opcodes.PUTFIELD),
            remap = false
    )
    private void clearAlloyedCasing(BeltBlockEntity.CasingType type, CallbackInfo ci) {
        create_alloyed$alloyedCasing = AlloyedCasingType.NONE;
    }


    @Override
    public void create_alloyed$setAlloyedCasingType(AlloyedCasingType type) {
        if (create_alloyed$alloyedCasing == type)
            return;

        BlockState blockState = getBlockState();
        boolean shouldBlockHaveCasing = type != AlloyedCasingType.NONE;

        if (getLevel().isClientSide()) {
            create_alloyed$alloyedCasing = type;
            casing = BeltBlockEntity.CasingType.NONE;

            level.setBlock(worldPosition, blockState.setValue(BeltBlock.CASING, shouldBlockHaveCasing), 0);
            //? neoforge
            /*requestModelDataUpdate();*/
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 16);
            return;
        }

        if (create_alloyed$alloyedCasing == AlloyedCasingType.STEEL)
            level.levelEvent(2001, worldPosition,
                    Block.getId(CreateAlloyedBlocks.STEEL_CASING.getDefaultState()));
        else if (create_alloyed$alloyedCasing == AlloyedCasingType.BRONZE)
            level.levelEvent(2001, worldPosition,
                    Block.getId(CreateAlloyedBlocks.BRONZE_CASING.getDefaultState()));
        if (blockState.getValue(BeltBlock.CASING) != shouldBlockHaveCasing)
            KineticBlockEntity.switchToBlockState(level, worldPosition,
                    blockState.setValue(BeltBlock.CASING, shouldBlockHaveCasing));

        create_alloyed$alloyedCasing = type;
        casing = BeltBlockEntity.CasingType.NONE;
        setChanged();
        sendData();
    }

    @Override
    public void create_alloyed$setAlloyedCasingTypeRaw(AlloyedCasingType value) {
        create_alloyed$alloyedCasing = value;
    }

    @Override
    public AlloyedCasingType getAlloyedCasingType() {
        return create_alloyed$alloyedCasing;
    }

    public boolean create_alloyed$isCovered() {
        return covered;
    }
}
