package com.molybdenum.alloyed.mixin.shears;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.molybdenum.alloyed.common.registry.ModItems;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.decoration.LeashFenceKnotEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin({Entity.class})
public class EntityMixin {

	//? fabric {
	@WrapOperation(method = "interact", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Ljava/lang/Object;)Z", ordinal = 0))
	private boolean shearsWork(ItemStack instance, Object o, Operation<Boolean> original) {
		return instance.is(ModItems.STEEL_SHEARS.get()) || original.call(instance, o);
	}
	@WrapOperation(method = "interact", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Ljava/lang/Object;)Z", ordinal = 1))
	private boolean shearsWork2(ItemStack instance, Object o, Operation<Boolean> original) {
		return instance.is(ModItems.STEEL_SHEARS.get()) || original.call(instance, o);
	}
	//?}
}
