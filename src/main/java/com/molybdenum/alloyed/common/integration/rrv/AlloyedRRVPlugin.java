package com.molybdenum.alloyed.common.integration.rrv;

import cc.cassian.rrv.api.ReliableRecipeViewerPlugin;
import cc.cassian.rrv.api.recipe.ItemView;
import cc.cassian.rrv.common.recipe.ServerRecipeManager;
import com.molybdenum.alloyed.common.content.recipes.ModRecipes;

import java.util.Collections;

public class AlloyedRRVPlugin implements ReliableRecipeViewerPlugin {
	public static void init() {
		ItemView.addServerRecipeProvider(recipeList -> {
			ServerRecipeManager.INSTANCE.getRecipesForType(ModRecipes.SHAPELESS_FORGING_TYPE.get()).forEach(recipe -> {
				recipeList.add(new ShapelessForgingServerRecipe(recipe.getIngredients(), recipe.getResultItem(), recipe.getCookTime()));
			});
		});

		ItemView.addClientRecipeWrapper(ShapelessForgingServerRecipe.TYPE, modRecipe -> {
			return Collections.singletonList(new ForgingClientRecipe(modRecipe));
		});
	}

	@Override
	public void onIntegrationInitialize() {
		init();
	}
}
