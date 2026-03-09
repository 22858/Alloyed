package com.molybdenum.alloyed.fabric;
//? fabric {
import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.common.CommonEventsHandler;
import com.molybdenum.alloyed.common.compat.create.CreateCompat;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;

import static com.molybdenum.alloyed.Alloyed.MOD_ID;

public class FabricEntrypoint implements ModInitializer {
	@Override
	public void onInitialize() {
		Alloyed.init();
		CommonEventsHandler.setupCommon();
		if (Alloyed.CONFIG.integratedForges)
			ResourceManagerHelper.registerBuiltinResourcePack(Alloyed.asResource("integrated_forges"), FabricLoader.getInstance().getModContainer(MOD_ID).orElseThrow(), ResourcePackActivationType.DEFAULT_ENABLED);
		if (FabricLoader.getInstance().isModLoaded("create"))
			CreateCompat.register();
	}
}
//?}
