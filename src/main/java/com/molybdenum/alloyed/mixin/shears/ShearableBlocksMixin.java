package com.molybdenum.alloyed.mixin.shears;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.molybdenum.alloyed.common.registry.ModItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.BeehiveBlock;
import net.minecraft.world.level.block.PumpkinBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin({PumpkinBlock.class, BeehiveBlock.class})
public class ShearableBlocksMixin {

	//? fabric {
	@WrapOperation(method = "use", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z", ordinal = 0))
	private boolean shear(ItemStack instance, Item item, Operation<Boolean> original) {
		return instance.is(ModItems.STEEL_SHEARS.get()) || original.call(instance, item);
	}
	//?}
}
