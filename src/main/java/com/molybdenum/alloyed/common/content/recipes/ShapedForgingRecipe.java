package com.molybdenum.alloyed.common.content.recipes;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.molybdenum.alloyed.common.handler.RecipeWrapper;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.Optional;

public class ShapedForgingRecipe extends AbstractForgingRecipe {

	final ItemStackTemplate output;
	private final ShapedRecipePattern pattern;
	private final int cookTime;

	public ShapedForgingRecipe(ShapedRecipePattern pattern, ItemStackTemplate output, int cookTime) {
		super(output, cookTime);
		this.output = output;
		this.pattern = pattern;
		this.cookTime = cookTime;
	}

	@Override
	public PlacementInfo placementInfo() {
		return PlacementInfo.createFromOptionals(pattern.ingredients());
	}

	@Override
	public RecipeSerializer<? extends Recipe<RecipeWrapper>> getSerializer() {
		return ModRecipes.SHAPED_FORGING_SERIALIZER.get();
	}

	public ItemStack getResultItem() {
		return output.create().copy();
	}

	//    @Override
	public List<Optional<Ingredient>> getIngredients() {
		return pattern.ingredients();
	}

	public int getCookTime() {
		return this.cookTime;
	}


	@Override
	public boolean matches(RecipeWrapper inv, Level level) {
		ItemStack outputSlot = inv.getItem(9);
		if (!outputSlot.isEmpty() && !ItemStack.isSameItem(this.output.create(), outputSlot)) {
			return false;
		}

		if (!outputSlot.isEmpty() && outputSlot.getCount() >= outputSlot.getMaxStackSize()) {
			return false;
		}

		boolean[][] slotUsed = new boolean[3][3]; // Track which slots are used

		// Iterate over the crafting grid
		for (int offsetX = 0; offsetX <= 3 - this.getWidth(); ++offsetX) {
			for (int offsetY = 0; offsetY <= 3 - this.getHeight(); ++offsetY) {
				if (checkIngredients(inv, offsetX, offsetY, slotUsed)) {
					if (areOtherSlotsEmpty(inv, offsetX, offsetY)) {
						return true; // Match found, return true
					}
				}
			}
		}

		return false; // No match found
	}

	private boolean areOtherSlotsEmpty(RecipeWrapper pContainer, int offsetX, int offsetY) {
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j) {
				if (i < offsetX || i >= offsetX + this.getWidth() || j < offsetY || j >= offsetY + this.getHeight()) {
					ItemStack itemStack = pContainer.getItem(i + j * 3); // Use a fixed grid size of 3x3
					if (!itemStack.isEmpty()) {
						return false; // Slot is not empty
					}
				}
			}
		}
		return true; // All other slots are empty
	}
	private boolean checkIngredients(RecipeWrapper pContainer, int offsetX, int offsetY, boolean[][] slotUsed) {
		// Iterate over the recipe's dimensions
		for (int i = 0; i < this.getWidth(); ++i) {
			for (int j = 0; j < this.getHeight(); ++j) {
				int gridX = i + offsetX;
				int gridY = j + offsetY;

				// Check if the current position is within the crafting grid
				if (gridX >= 3 || gridY >= 3) {
					continue;
				}

				// Check if the slot is already used by another recipe
				if (slotUsed[gridX][gridY]) {
					return false;
				}

				Ingredient recipeIngredient = this.pattern.ingredients().get(i + j * this.getWidth()).get();
				ItemStack gridStack = pContainer.getItem(gridX + gridY * 3); // Use a fixed grid size of 3x3

				// Check if the ingredient matches the item in the crafting grid
				if (!recipeIngredient.test(gridStack)) {
					return false;
				}

				// Mark the slot as used
				slotUsed[gridX][gridY] = true;
			}
		}

		return true; // All ingredients matched
	}

	public int getWidth() {
		return this.pattern.width();
	}

	public int getHeight() {
		return this.pattern.height();
	}

	@Override
	public ItemStack assemble(RecipeWrapper recipeInput) {
		return output.create();
	}

	@Override
	public RecipeType<? extends Recipe<RecipeWrapper>> getType() {
		return ModRecipes.SHAPED_FORGING_TYPE.get();
	}

	public static final MapCodec<ShapedForgingRecipe> CODEC = RecordCodecBuilder.mapCodec((recipe) -> recipe.group(
			ShapedRecipePattern.MAP_CODEC.forGetter((p_311733_) -> p_311733_.pattern),
			ItemStackTemplate.CODEC.fieldOf("result").forGetter((forgeShapedRecipe) -> forgeShapedRecipe.output),
			Codec.INT.optionalFieldOf("cookingtime", 200).forGetter(ShapedForgingRecipe::getCookTime)
	).apply(recipe, ShapedForgingRecipe::new));

	public static StreamCodec<RegistryFriendlyByteBuf, ShapedForgingRecipe> STREAM_CODEC =
			 StreamCodec.of(ShapedForgingRecipe::toNetwork, ShapedForgingRecipe::fromNetwork);


	public static ShapedForgingRecipe fromNetwork(RegistryFriendlyByteBuf buffer) {
		ShapedRecipePattern shapedrecipepattern = ShapedRecipePattern.STREAM_CODEC.decode(buffer);

		ItemStackTemplate itemstack = ItemStackTemplate.STREAM_CODEC.decode(buffer);
		int cookTimeIn = buffer.readVarInt();
		return new ShapedForgingRecipe(shapedrecipepattern, itemstack, cookTimeIn);
	}


	public static void toNetwork(RegistryFriendlyByteBuf buffer, ShapedForgingRecipe recipe) {
		ShapedRecipePattern.STREAM_CODEC.encode(buffer, recipe.pattern);

		ItemStackTemplate.STREAM_CODEC.encode(buffer, recipe.output);
		buffer.writeVarInt(recipe.cookTime);
	}
}