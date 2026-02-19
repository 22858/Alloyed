package com.molybdenum.alloyed.client.screen;

import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.common.screen.ForgeMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

public class ForgeScreen extends AbstractContainerScreen<ForgeMenu> {
	private static final Identifier TEXTURE = Alloyed.asResource("textures/gui/forge_gui.png");

	public ForgeScreen(ForgeMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
		super(pMenu, pPlayerInventory, pTitle);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float v, int i, int i1) {
		// Get the position where the GUI is to be drawn
		int x = (width - imageWidth) / 2;
		int y = (height - imageHeight) / 2;

		// Render the background texture
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, x, y, 0, 0, imageWidth, imageHeight, 256, 256);

		// Render progress bar if crafting
		if (menu.isCrafting()) {
			guiGraphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, x + 90, y + 35, 176, 14, menu.getScaledProgress(), 17, 256, 256);
		}

		// Render fuel bar if the oven is fueled
		if (menu.isFueled()) {
			guiGraphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, x + 93, y + 55, 176, 32, 17, 15, 256, 256);
		}
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
		this.renderBackground(guiGraphics, mouseX, mouseY);
		super.render(guiGraphics, mouseX, mouseY, delta);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	protected void renderBackground(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		// Draw the background texture
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, (width - imageWidth) / 2, (height - imageHeight) / 2, 0, 0, imageWidth, imageHeight, 256, 256);

		// Check and render the crafting progress
		if (menu.isCrafting()) {
			guiGraphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, (width - imageWidth) / 2 + 90, (height - imageHeight) / 2 + 35, 176, 14, menu.getScaledProgress(), 17, 256, 256);
		}

		// Check and render the oven fuel status
		if (menu.isFueled()) {
			guiGraphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, (width - imageWidth) / 2 + 93, (height - imageHeight) / 2 + 55, 176, 32, 17, 15, 256, 256);
		}
	}

}