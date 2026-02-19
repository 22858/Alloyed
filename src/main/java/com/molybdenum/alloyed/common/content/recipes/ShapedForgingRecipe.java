package com.molybdenum.alloyed.common.content.recipes;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.molybdenum.alloyed.common.handler.RecipeWrapper;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

public class ShapedForgingRecipe extends AbstractForgingRecipe {

	final ItemStack output;
	private final ShapedRecipePattern pattern;
	private final int cookTime;

	public ShapedForgingRecipe(ShapedRecipePattern pattern, ItemStack output, int cookTime) {
		super(output, cookTime);
		this.output = output;
		this.pattern = pattern;
		this.cookTime = cookTime;
	}

	@Override
	public RecipeSerializer<? extends Recipe<RecipeWrapper>> getSerializer() {
		return ModRecipes.SHAPED_FORGING_SERIALIZER.get();
	}

	public ItemStack getResultItem() {
		return output.copy();
	}

	public NonNullList<Ingredient> getIngredients() {
		return pattern.ingredients();
	}

	public int getCookTime() {
		return this.cookTime;
	}


	@Override
	public boolean matches(RecipeWrapper inv, Level level) {
		return this.pattern.matches(CraftingInput.of(3, 3, inv.stacks()));
	}

	public int getWidth() {
		return this.pattern.width();
	}

	public int getHeight() {
		return this.pattern.height();
	}

	@Override
	public ItemStack assemble(RecipeWrapper recipeInput, HolderLookup.Provider provider) {
		return output.copy();
	}

	@Override
	public boolean canCraftInDimensions(int width, int height) {
		return true;
	}

	@Override
	public ItemStack getResultItem(HolderLookup.Provider registries) {
		return output.copy();
	}

	@Override
	public RecipeType<? extends Recipe<RecipeWrapper>> getType() {
		return ModRecipes.SHAPED_FORGING_TYPE.get();
	}

	public static final MapCodec<ShapedForgingRecipe> CODEC = RecordCodecBuilder.mapCodec((recipe) -> recipe.group(
			ShapedRecipePattern.MAP_CODEC.forGetter((p_311733_) -> p_311733_.pattern),
			ItemStack.CODEC.fieldOf("result").forGetter((forgeShapedRecipe) -> forgeShapedRecipe.output),
			Codec.INT.optionalFieldOf("cookingtime", 200).forGetter(ShapedForgingRecipe::getCookTime)
	).apply(recipe, ShapedForgingRecipe::new));

	public static StreamCodec<RegistryFriendlyByteBuf, ShapedForgingRecipe> STREAM_CODEC =
			 StreamCodec.of(ShapedForgingRecipe::toNetwork, ShapedForgingRecipe::fromNetwork);


	public static ShapedForgingRecipe fromNetwork(RegistryFriendlyByteBuf buffer) {
		ShapedRecipePattern shapedrecipepattern = ShapedRecipePattern.STREAM_CODEC.decode(buffer);

		ItemStack itemstack = ItemStack.STREAM_CODEC.decode(buffer);
		int cookTimeIn = buffer.readVarInt();
		return new ShapedForgingRecipe(shapedrecipepattern, itemstack, cookTimeIn);
	}


	public static void toNetwork(RegistryFriendlyByteBuf buffer, ShapedForgingRecipe recipe) {
		ShapedRecipePattern.STREAM_CODEC.encode(buffer, recipe.pattern);

		ItemStack.STREAM_CODEC.encode(buffer, recipe.output);
		buffer.writeVarInt(recipe.cookTime);
	}
}