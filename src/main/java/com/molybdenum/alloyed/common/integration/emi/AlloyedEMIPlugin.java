package com.molybdenum.alloyed.common.integration.emi;

import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.common.content.recipes.ModRecipes;
import com.molybdenum.alloyed.common.content.recipes.ShapedForgingRecipe;
import com.molybdenum.alloyed.common.registry.ModBlocks;
import com.molybdenum.alloyed.common.screen.ModMenuTypes;
import dev.emi.emi.api.EmiEntrypoint;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiStack;
import net.minecraft.world.item.crafting.RecipeHolder;

@EmiEntrypoint
public class AlloyedEMIPlugin implements EmiPlugin {

    public static final EmiRecipeCategory FORGING =
        new EmiRecipeCategory(Alloyed.asResource("forging"), EmiStack.of(ModBlocks.FORGE.get()));

    @Override
    public void register(EmiRegistry registry) {
        registry.addCategory(FORGING);
        registry.addWorkstation(FORGING, EmiStack.of(ModBlocks.FORGE.get()));
        for (RecipeHolder<ShapedForgingRecipe> recipe : registry.getRecipeManager().getAllRecipesFor(ModRecipes.SHAPED_FORGING_TYPE.get())) {
            registry.addRecipe(new ForgingEmiRecipe(recipe.id(), recipe.value()));
        }
        registry.addRecipeHandler(ModMenuTypes.FORGE_MENU.get(), new ForgingEmiRecipeHandler());
    }

}
