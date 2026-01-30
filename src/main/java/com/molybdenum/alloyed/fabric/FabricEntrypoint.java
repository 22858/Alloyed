package com.molybdenum.alloyed.fabric;
//? fabric {
import com.molybdenum.alloyed.Alloyed;
import net.fabricmc.api.ModInitializer;

import static com.molybdenum.alloyed.Alloyed.REGISTRATE;

public class FabricEntrypoint implements ModInitializer {
	@Override
	public void onInitialize() {
		Alloyed.init();
		REGISTRATE.register();

	}
}
//?}
