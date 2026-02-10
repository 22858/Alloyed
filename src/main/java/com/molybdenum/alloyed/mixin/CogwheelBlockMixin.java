package com.molybdenum.alloyed.mixin;

import com.molybdenum.alloyed.common.util.EncasingHelper;
import com.zurrtum.create.content.kinetics.simpleRelays.CogWheelBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CogWheelBlock.class)
public abstract class CogwheelBlockMixin {
    @Shadow(remap = false)
    boolean isLarge;

    @Inject(
            method = "useItemOn",
            at = @At(value = "INVOKE", target = "Lcom/zurrtum/create/content/kinetics/simpleRelays/CogWheelBlock;tryEncase(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/InteractionHand;Lnet/minecraft/world/phys/BlockHitResult;)Lnet/minecraft/world/InteractionResult;"),
            cancellable = true

    )
    private void tryEncaseWithAlloyedCasings(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult, CallbackInfoReturnable<InteractionResult> cir) {
        ItemStack heldItem = player.getItemInHand(hand);
        InteractionResult result = EncasingHelper
                .tryEncase(EncasingHelper.EncaseType.fromCogSize(isLarge),
                        state, level, pos, heldItem, player, hand, hitResult);

        if (result.consumesAction()) {
            cir.setReturnValue(result);
            cir.cancel();
        }
    }
}