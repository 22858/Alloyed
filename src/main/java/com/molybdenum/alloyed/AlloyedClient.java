package com.molybdenum.alloyed;

import com.molybdenum.alloyed.client.registry.ModPartialModels;
import com.molybdenum.alloyed.client.registry.ModTransformers;
import com.molybdenum.alloyed.client.screen.ForgeScreen;
import com.molybdenum.alloyed.common.compat.create.CreateCompat;
import com.molybdenum.alloyed.common.screen.ModMenuTypes;
import com.molybdenum.alloyed.common.util.Platform;
import net.minecraft.client.gui.screens.MenuScreens;

public class AlloyedClient {
    public static void onClientInit() {
        if (Platform.isLoaded("create")) {
			ModPartialModels.register();
            ModTransformers.register();
		}
    }

    public static void clientSetup() {
        // Register ponders
        if (Platform.isLoaded("create")) {
			CreateCompat.registerPonders();
		}
		MenuScreens.register(ModMenuTypes.FORGE_MENU.get(), ForgeScreen::new);
    }
}
