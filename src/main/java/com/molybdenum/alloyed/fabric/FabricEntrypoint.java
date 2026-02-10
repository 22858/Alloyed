package com.molybdenum.alloyed.fabric;
//? fabric {
import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.common.CommonEventsHandler;
import net.fabricmc.api.ModInitializer;

import static com.molybdenum.alloyed.Alloyed.REGISTRATE;

public class FabricEntrypoint implements ModInitializer {
	@Override
	public void onInitialize() {
		REGISTRATE.registerEventListeners();
		Alloyed.init();
		CommonEventsHandler.setupCommon();
	}
}
//?}
