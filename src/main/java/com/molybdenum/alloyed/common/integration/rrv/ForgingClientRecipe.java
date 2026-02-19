package com.molybdenum.alloyed.common.integration.rrv;

import cc.cassian.rrv.api.recipe.ReliableClientRecipe;
import cc.cassian.rrv.common.recipe.inventory.RecipeViewMenu;
import cc.cassian.rrv.common.recipe.inventory.RecipeViewScreen;
import cc.cassian.rrv.common.recipe.inventory.SlotContent;
import com.molybdenum.alloyed.client.screen.ForgeScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static cc.cassian.rrv.common.builtin.BuiltInReliableRecipeViewerIntegration.DEFAULT_SLOT_TEXTURE;

public class ForgingClientRecipe implements ReliableClientRecipe {
    private final ItemStack result;
    private final List<Ingredient> ingredients;
    private final int cookTime;
    private final boolean shapeless;

    @Override
    public AbstractForgingClientRecipeType getViewType() {
        return AbstractForgingClientRecipeType.INSTANCE;
    }

    public ForgingClientRecipe(ShapelessForgingServerRecipe modRecipe) {
        this.ingredients = modRecipe.getIngredients();
        this.cookTime = modRecipe.getCookTime();
        this.result = modRecipe.getResult();
        this.shapeless = true;
    }

    @Override
    public void bindSlots(RecipeViewMenu.SlotFillContext slotFillContext) {
        AtomicInteger slotID = new AtomicInteger();
        getIngredients().forEach((ingredient -> {
            slotFillContext.bindOptionalSlot(slotID.getAndIncrement(), ingredient, OptionalSlotRenderer.NONE);
        }));

        slotFillContext.bindOptionalSlot(9, SlotContent.of(Items.COAL), RecipeViewMenu.OptionalSlotRenderer.DEFAULT);
        slotFillContext.addAdditionalStackModifier(9, (stack, components) -> {
            components.set(0, Component.translatable("rrv.cooking.furnace_fuel"));
        });

        var result = SlotContent.of(this.result);
        result.setType(SlotContent.Type.RESULT);
        slotFillContext.bindSlot(10, result);
    }

    @Override
    public void renderRecipe(RecipeViewScreen screen, RecipePosition recipePosition, GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        int i = 9;
        int x = 37;
        int y = 38;
        while (i > 0) {
            guiGraphics.blit(RenderPipelines.GUI_TEXTURED, DEFAULT_SLOT_TEXTURE, x, y, 0, 0, 18, 18, 18, 18);
            i--;
            if (i==3 || i==6) {
                y -=18;
                x = 37;
            } else {
                x -=18;
            }
        }
        if (shapeless) {
            guiGraphics.blitSprite(RenderPipelines.GUI_TEXTURED, Identifier.fromNamespaceAndPath("rrv", "crafting_shapeless"), 26, 14, 0, 0, 92, 0, 26, 14);
            if ((mouseX > 92 && mouseX < 122) && (mouseY>0 && mouseY < 14)) {
                guiGraphics.setComponentTooltipForNextFrame(screen.getFont(), List.of(Component.translatable("view.rrv.type.crafting.shapeless")), mouseX+recipePosition.left(), mouseY+recipePosition.top());
            }
        }
        if ((mouseX > 56 && mouseX < 84) && (mouseY>20 && mouseY < 40)) {
            drawCookTime(cookTime, guiGraphics, mouseX+recipePosition.left(), mouseY+recipePosition.top());
        }
    }

    protected void drawCookTime(int cookTime, GuiGraphics guiGraphics, int x, int y) {
        if (cookTime > 0) {
            int cookTimeSeconds = cookTime / 20;
            Component timeString = Component.translatable("rrv.cooking.time", cookTimeSeconds);
            Minecraft minecraft = Minecraft.getInstance();
            Font fontRenderer = minecraft.font;
            guiGraphics.setComponentTooltipForNextFrame(fontRenderer, List.of(timeString), x, y);
        }
    }


    public interface OptionalSlotRenderer {
        RecipeViewMenu.OptionalSlotRenderer NONE = (guiGraphics, mouseX, mouseY, partialTicks) -> {};
    }

    @Override
    public List<SlotContent> getIngredients() {
        return ingredients.stream().map(SlotContent::of).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public List<SlotContent> getResults() {
        return List.of(SlotContent.of(result));
    }

    @Override
    public boolean supportsItemTransfer() {
        return true;
    }

    @Override
    public void mapRecipeItems(RecipeTransferMap transferMap, AbstractContainerScreen<?> screen) {
        transferMap.linkSlots(0, 36);
        transferMap.linkSlots(1, 37);
        transferMap.linkSlots(2, 38);
        transferMap.linkSlots(3, 39);
        transferMap.linkSlots(4, 40);
        transferMap.linkSlots(5, 41);
        transferMap.linkSlots(6, 42);
        transferMap.linkSlots(7, 43);
        transferMap.linkSlots(8, 44);
    }

    @Override
    public List<Class<? extends AbstractContainerScreen<?>>> getTransferClasses() {
        return List.of(ForgeScreen.class);
}

}
