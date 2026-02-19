package com.molybdenum.alloyed.common.integration.rrv;

import cc.cassian.rrv.api.ReliableRecipeViewerPlugin;
import cc.cassian.rrv.api.recipe.ItemView;
import cc.cassian.rrv.common.recipe.ServerRecipeManager;
import com.molybdenum.alloyed.common.content.recipes.ModRecipes;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.Collections;
import java.util.HashMap;

public class AlloyedRRVPlugin implements ReliableRecipeViewerPlugin {
	public static void init() {
		ItemView.addServerRecipeProvider(recipeList -> {
			ServerRecipeManager.INSTANCE.getRecipesForType(ModRecipes.SHAPELESS_FORGING_TYPE.get()).forEach(recipe -> {
				recipeList.add(new ShapelessForgingServerRecipe(recipe.getIngredients(), recipe.getResultItem(), recipe.getCookTime()));
			});
			ServerRecipeManager.INSTANCE.getRecipesForType(ModRecipes.SHAPED_FORGING_TYPE.get()).forEach(recipe -> {
				HashMap<Integer, Ingredient> ingredients = new HashMap<>();

				int i = 0;
				for (int y = 0; y < 3; y++) {
					for (int x = 0; x < 3; x++) {

						if (x >= recipe.getWidth() || y >= recipe.getHeight()) {
							continue;
						}

						if (recipe.getIngredients().get(i).isPresent())
							ingredients.put(x + y * 3, recipe.getIngredients().get(i).get());

						i++;
					}
				}
				recipeList.add(new ShapedForgingServerRecipe(recipe.getWidth(), recipe.getHeight(), ingredients, recipe.getResultItem(), recipe.getCookTime()));
			});
		});

		ItemView.addClientRecipeWrapper(ShapelessForgingServerRecipe.TYPE, modRecipe -> {
			return Collections.singletonList(new ForgingClientRecipe(modRecipe));
		});
		ItemView.addClientRecipeWrapper(ShapedForgingServerRecipe.TYPE, modRecipe -> {
			return Collections.singletonList(new ForgingClientRecipe(modRecipe));
		});
	}

	@Override
	public void onIntegrationInitialize() {
		init();
	}
}
