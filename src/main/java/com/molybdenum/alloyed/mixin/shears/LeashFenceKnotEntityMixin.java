package com.molybdenum.alloyed.mixin.shears;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.molybdenum.alloyed.common.registry.ModItems;
import net.minecraft.world.entity.animal.cow.MushroomCow;
import net.minecraft.world.entity.animal.golem.CopperGolem;
import net.minecraft.world.entity.animal.golem.SnowGolem;
import net.minecraft.world.entity.animal.sheep.Sheep;
import net.minecraft.world.entity.decoration.LeashFenceKnotEntity;
import net.minecraft.world.entity.monster.skeleton.Bogged;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin({LeashFenceKnotEntity.class})
public class LeashFenceKnotEntityMixin {

	//? fabric {
	@WrapOperation(method = "interact", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Ljava/lang/Object;)Z", ordinal = 0))
	private boolean shearsWork(ItemStack instance, Object o, Operation<Boolean> original) {
		return instance.is(ModItems.STEEL_SHEARS.get()) || original.call(instance, o);
	}
	//?}
}
