package com.molybdenum.alloyed.mixin.shears;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.molybdenum.alloyed.common.handler.ItemStackHandler;
import com.molybdenum.alloyed.common.registry.ModItems;
import net.minecraft.world.entity.animal.MushroomCow;
import net.minecraft.world.entity.animal.SnowGolem;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.monster.Bogged;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin({Sheep.class, SnowGolem.class, MushroomCow.class, Bogged.class})
public class ShearableMobsMixin {

	//? fabric {
	@WrapOperation(method = "mobInteract", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z", ordinal = 0))
	private boolean shearsWork(ItemStack instance, Item item, Operation<Boolean> original) {
		return instance.is(ModItems.STEEL_SHEARS.get()) || original.call(instance, item);
	}
	//?}
}
