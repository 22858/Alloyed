package com.molybdenum.alloyed.common;

import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.common.content.blocks.entities.ForgeBlockEntity;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class CommonRegistry {
	public static <R, T extends R> Supplier<T> register(String name, Supplier<T> supplier, Registry<R> reg) {
		T object = supplier.get();
		Registry.register(reg, Alloyed.asResource(name), object);
		return () -> object;
	}

	public static <T> Supplier<DataComponentType<T>> registerComponentType(String name, UnaryOperator<DataComponentType.Builder<T>> builderOperator) {
		return register(name, () -> (builderOperator.apply(DataComponentType.builder())).build(), BuiltInRegistries.DATA_COMPONENT_TYPE);
	}

	public static <B extends Item> Supplier<B> registerItem(String name, Supplier<B> supplier) {
		return register(name, supplier, BuiltInRegistries.ITEM);
	}

	public static <B extends Block> Supplier<B> registerBlock(String name, Supplier<B> supplier) {
		return register(name, supplier, BuiltInRegistries.BLOCK);
	}

	public static <T extends EntityType<?>> Supplier<T> registerEntity(String name, Supplier<T> supplier) {
		return register(name, supplier, BuiltInRegistries.ENTITY_TYPE);
	}

	public static Supplier<SoundEvent> registerSoundEvent(String name, Supplier<SoundEvent> supplier) {
		return register(name, supplier, BuiltInRegistries.SOUND_EVENT);
	}

	public static Supplier<SoundEvent> registerSoundEvent(String name) {
		return registerSoundEvent(name, ()->SoundEvent.createVariableRangeEvent(Alloyed.asResource(name)));
	}

	public static Holder<MobEffect> registerMobEffect(String name, Supplier<MobEffect> supplier) {
		return Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, Alloyed.asResource(name), supplier.get());
	}

	public static <B extends RecipeSerializer<?>> Supplier<B> registerRecipeSerializer(String name, Supplier<B> supplier) {
		return register(name, supplier, BuiltInRegistries.RECIPE_SERIALIZER);
	}

	public static <B extends RecipeType<?>> Supplier<B> registerRecipe(String name, Supplier<B> supplier) {
		return register(name, supplier, BuiltInRegistries.RECIPE_TYPE);
	}

	public static Supplier<BlockEntityType<ForgeBlockEntity>> registerBlockEntity(String name, Supplier<BlockEntityType<ForgeBlockEntity>> supplier) {
		return register(name, supplier, BuiltInRegistries.BLOCK_ENTITY_TYPE);

	}

	public static <B extends MenuType<?>> Supplier<B> registerMenu(String name, Supplier<B> supplier) {
		return register(name, supplier, BuiltInRegistries.MENU);
	}
}