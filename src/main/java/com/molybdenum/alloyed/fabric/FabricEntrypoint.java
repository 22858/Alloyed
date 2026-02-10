package com.molybdenum.alloyed.fabric;
//? fabric {
import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.common.CommonEventsHandler;
import net.fabricmc.api.ModInitializer;

public class FabricEntrypoint implements ModInitializer {
	@Override
	public void onInitialize() {
		Alloyed.init();
		CommonEventsHandler.setupCommon();
	}
}
//?}
