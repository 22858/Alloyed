package com.molybdenum.alloyed.common.content.recipes;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.molybdenum.alloyed.common.handler.RecipeWrapper;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;

public class ShapelessForgingRecipe extends AbstractForgingRecipe {
	private final NonNullList<Ingredient> inputItems;
	private final ItemStack output;
	private final int cookTime;

	public ShapelessForgingRecipe(NonNullList<Ingredient> inputItems, ItemStack output, int cookTime) {
		super(output, cookTime);
		this.inputItems = inputItems;
		this.output = output;
		this.cookTime = cookTime;
	}

	public NonNullList<Ingredient> getIngredients() {
		return inputItems;
	}

	public ItemStack getResultItem() {
		return this.output.copy();
	}

	public ItemStack assemble(RecipeWrapper inv) {
		return getResultItem().copy();
	}

	public int getCookTime() {
		return this.cookTime;
	}

	public boolean matches(RecipeWrapper inv, Level level) {
		List<ItemStack> inputs = new ArrayList<>();
		int i = 0;

		for (int j = 0; j < 9; ++j) {
			ItemStack itemstack = inv.getItem(j);
			if (!itemstack.isEmpty()) {
				++i;
				inputs.add(itemstack);
			}
		}

		return inv.ingredientAmount() == this.inputItems.size() && inv.stackedContents().canCraft(this, null);
	}

	@Override
	public ItemStack assemble(RecipeWrapper input, HolderLookup.Provider registries) {
		return output.copy();
	}

	public boolean canCraftInDimensions(int width, int height) {
		return width * height >= this.inputItems.size();
	}

	@Override
	public ItemStack getResultItem(HolderLookup.Provider registries) {
		return output.copy();
	}

	public RecipeSerializer<? extends Recipe<RecipeWrapper>> getSerializer() {
		return ModRecipes.SHAPELESS_FORGING_SERIALIZER.get();
	}

	public RecipeType<? extends Recipe<RecipeWrapper>> getType() {
		return ModRecipes.SHAPELESS_FORGING_TYPE.get();
	}

	public boolean equals(Object o) {
		if (this == o) {
			return true;
		} else if (o != null && this.getClass() == o.getClass()) {
			ShapelessForgingRecipe that = (ShapelessForgingRecipe) o;
			if (this.getCookTime() != that.getCookTime()) {
				return false;
			} else if (!this.inputItems.equals(that.inputItems)) {
				return false;
			} else {
				return this.output.equals(that.output);
			}
		} else {
			return false;
		}
	}

	public int hashCode() {
		int result = this.inputItems.hashCode();
		result = 31 * result + this.output.hashCode();
		result = 31 * result + this.getCookTime();
		return result;
	}

	public static final MapCodec<ShapelessForgingRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(

			Ingredient.CODEC.listOf().fieldOf("ingredients").xmap(ingredients -> {
				NonNullList<Ingredient> nonNullList = NonNullList.create();
				nonNullList.addAll(ingredients);
				return nonNullList;
			}, ingredients -> ingredients).forGetter(ShapelessForgingRecipe::getIngredients),
			ItemStack.CODEC.fieldOf("result").forGetter(r -> r.output),
			Codec.INT.optionalFieldOf("cookingtime", 200).forGetter(ShapelessForgingRecipe::getCookTime)
	).apply(inst, ShapelessForgingRecipe::new));

	public static StreamCodec<RegistryFriendlyByteBuf, ShapelessForgingRecipe> STREAM_CODEC =
		StreamCodec.of(ShapelessForgingRecipe::toNetwork, ShapelessForgingRecipe::fromNetwork);


	private static ShapelessForgingRecipe fromNetwork(RegistryFriendlyByteBuf buffer) {
		int i = buffer.readVarInt();
		NonNullList<Ingredient> inputItemsIn = NonNullList.withSize(i, Ingredient.of());
		inputItemsIn.replaceAll((ignored) -> Ingredient.CONTENTS_STREAM_CODEC.decode(buffer));
		ItemStack outputIn = ItemStack.STREAM_CODEC.decode(buffer);
		int cookTimeIn = buffer.readVarInt();
		return new ShapelessForgingRecipe(inputItemsIn, outputIn, cookTimeIn);
	}

	private static void toNetwork(RegistryFriendlyByteBuf buffer, ShapelessForgingRecipe recipe) {
		buffer.writeVarInt(recipe.inputItems.size());
		for (Ingredient ingredient : recipe.inputItems) {
			Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, ingredient);
		}
		ItemStack.STREAM_CODEC.encode(buffer, recipe.output);
		buffer.writeVarInt(recipe.cookTime);
	}
}