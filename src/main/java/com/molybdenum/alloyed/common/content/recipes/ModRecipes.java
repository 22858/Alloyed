package com.molybdenum.alloyed.common.content.recipes;

import com.molybdenum.alloyed.common.CommonRegistry;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

import java.util.function.Supplier;

public class ModRecipes {

	public static final Supplier<RecipeType<ShapelessForgingRecipe>> SHAPELESS_FORGING_TYPE =
			CommonRegistry.registerRecipe("forging", () -> new RecipeType<>() {});

	public static final Supplier<RecipeType<ShapedForgingRecipe>> SHAPED_FORGING_TYPE =
			CommonRegistry.registerRecipe("forging_shaped", () -> new RecipeType<>() {});

	public static final Supplier<RecipeSerializer<ShapelessForgingRecipe>> SHAPELESS_FORGING_SERIALIZER =
			CommonRegistry.registerRecipeSerializer("forging", () -> new RecipeSerializer<>(ShapelessForgingRecipe.CODEC, ShapelessForgingRecipe.STREAM_CODEC));

	public static final Supplier<RecipeSerializer<ShapedForgingRecipe>> SHAPED_FORGING_SERIALIZER =
			CommonRegistry.registerRecipeSerializer("forging_shaped", () -> new RecipeSerializer<>(ShapedForgingRecipe.CODEC, ShapedForgingRecipe.STREAM_CODEC));


	public static void register() {

	}
}