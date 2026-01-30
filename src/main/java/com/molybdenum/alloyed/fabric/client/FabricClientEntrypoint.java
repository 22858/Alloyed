package com.molybdenum.alloyed.fabric.client;
//? fabric {
import com.molybdenum.alloyed.AlloyedClient;
import net.fabricmc.api.ClientModInitializer;

public class FabricClientEntrypoint implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		AlloyedClient.onClientInit();
		AlloyedClient.clientSetup();
	}
}
//?}