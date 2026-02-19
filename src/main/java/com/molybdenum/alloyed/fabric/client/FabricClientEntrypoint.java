package com.molybdenum.alloyed.fabric.client;

//? fabric {
import com.molybdenum.alloyed.AlloyedClient;
import com.molybdenum.alloyed.client.screen.ForgeScreen;
import com.molybdenum.alloyed.common.screen.ModMenuTypes;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screens.MenuScreens;

public class FabricClientEntrypoint implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		AlloyedClient.onClientInit();
		AlloyedClient.clientSetup();
		MenuScreens.register(ModMenuTypes.FORGE_MENU.get(), ForgeScreen::new);
	}
}
//?}