package com.molybdenum.alloyed.fabric.client;

//? fabric {
import com.molybdenum.alloyed.AlloyedClient;
import com.molybdenum.alloyed.client.screen.ForgeScreen;
import com.molybdenum.alloyed.common.compat.VanillaAlloyedBlocks;
import com.molybdenum.alloyed.common.registry.ModBlocks;
import com.molybdenum.alloyed.common.screen.ModMenuTypes;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.RenderType;

public class FabricClientEntrypoint implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		AlloyedClient.onClientInit();
		AlloyedClient.clientSetup();
		MenuScreens.register(ModMenuTypes.FORGE_MENU.get(), ForgeScreen::new);
		BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutout(), ModBlocks.STEEL_MESH_FENCE.get(), ModBlocks.STEEL_TRAPDOOR.get(), ModBlocks.STEEL_BARS.get());
		if (!FabricLoader.getInstance().isModLoaded("create")) {
			BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutout(), VanillaAlloyedBlocks.STEEL_LADDER.get());
		}
	}
}
//?}