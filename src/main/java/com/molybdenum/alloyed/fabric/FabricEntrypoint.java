package com.molybdenum.alloyed.fabric;
//? fabric {
import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.common.CommonEventsHandler;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.v1.ResourceLoader;
import net.fabricmc.fabric.api.resource.v1.pack.PackActivationType;
import net.fabricmc.loader.api.FabricLoader;

import static com.molybdenum.alloyed.Alloyed.MOD_ID;

public class FabricEntrypoint implements ModInitializer {
	@Override
	public void onInitialize() {
		Alloyed.init();
		CommonEventsHandler.setupCommon();
		if (Alloyed.CONFIG.integratedForges)
			ResourceLoader.registerBuiltinPack(Alloyed.asResource("integrated_forges"), FabricLoader.getInstance().getModContainer(MOD_ID).orElseThrow(), PackActivationType.DEFAULT_ENABLED);
	}
}
//?}
