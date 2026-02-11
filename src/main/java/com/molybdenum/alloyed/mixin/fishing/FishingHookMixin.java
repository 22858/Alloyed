package com.molybdenum.alloyed.mixin.fishing;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.molybdenum.alloyed.common.registry.ModItems;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(FishingHook.class)
public class FishingHookMixin {

	//? fabric {
	@WrapOperation(method = "shouldStopFishing", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Ljava/lang/Object;)Z", ordinal = 0))
	private boolean allowModdedRodsInMainhand(ItemStack instance, Object item, Operation<Boolean> original) {
		return instance.is(ModItems.STEEL_FISHING_ROD.get()) || original.call(instance, item);
	}

	@WrapOperation(method = "shouldStopFishing", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Ljava/lang/Object;)Z", ordinal = 1))
	private boolean allowModdedRodsInOffhand(ItemStack instance, Object item, Operation<Boolean> original) {
		return instance.is(ModItems.STEEL_FISHING_ROD.get()) || original.call(instance, item);
	}
	//?}
}
