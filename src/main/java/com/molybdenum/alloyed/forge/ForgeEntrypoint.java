package com.molybdenum.alloyed.forge;
//? forge {
/*import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.AlloyedClient;
import com.molybdenum.alloyed.common.CommonEventsHandler;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;

import static com.molybdenum.alloyed.Alloyed.REGISTRATE;

@Mod(Alloyed.MOD_ID)
public class ForgeEntrypoint {
	public ForgeEntrypoint() {
		var context = FMLJavaModLoadingContext.get();
		IEventBus eventBus = context.getModEventBus();
		MinecraftForge.EVENT_BUS.register(this);
		REGISTRATE.registerEventListeners(eventBus);
		ForgeMod.enableMilkFluid();
		Alloyed.init(eventBus);
		if (FMLEnvironment.dist.isClient()) {
			AlloyedClient.onClientInit();
			eventBus.addListener(ForgeEntrypoint::clientSetup);
		}
		eventBus.addListener(ForgeEntrypoint::commonSetup);
	}

	public static void clientSetup(FMLClientSetupEvent event) {
		AlloyedClient.clientSetup();
	}

	public static void commonSetup(FMLCommonSetupEvent event) {
		CommonEventsHandler.setupCommon();
	}
}
*///?}