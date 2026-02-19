package com.molybdenum.alloyed.common.content.recipes;

import com.mojang.serialization.MapCodec;
import com.molybdenum.alloyed.common.CommonRegistry;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

import java.util.function.Supplier;

public class ModRecipes {

	public static final Supplier<RecipeType<ShapelessForgingRecipe>> SHAPELESS_FORGING_TYPE =
			CommonRegistry.registerRecipe("forging", () -> new RecipeType<>() {});

	public static final Supplier<RecipeType<ShapedForgingRecipe>> SHAPED_FORGING_TYPE =
			CommonRegistry.registerRecipe("forging_shaped", () -> new RecipeType<>() {});

	public static final Supplier<RecipeSerializer<ShapelessForgingRecipe>> SHAPELESS_FORGING_SERIALIZER =
			CommonRegistry.registerRecipeSerializer("forging", () -> new RecipeSerializer<>() {
				@Override
				public MapCodec<ShapelessForgingRecipe> codec() {
					return ShapelessForgingRecipe.CODEC;
				}

				@Override
				public StreamCodec<RegistryFriendlyByteBuf, ShapelessForgingRecipe> streamCodec() {
					return ShapelessForgingRecipe.STREAM_CODEC;
				}
			});

	public static final Supplier<RecipeSerializer<ShapedForgingRecipe>> SHAPED_FORGING_SERIALIZER =
			CommonRegistry.registerRecipeSerializer("forging_shaped", () -> new RecipeSerializer<>() {
				@Override
				public MapCodec<ShapedForgingRecipe> codec() {
					return ShapedForgingRecipe.CODEC;
				}

				@Override
				public StreamCodec<RegistryFriendlyByteBuf, ShapedForgingRecipe> streamCodec() {
					return ShapedForgingRecipe.STREAM_CODEC;
				}
			});


	public static void register() {

	}
}