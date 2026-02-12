package com.molybdenum.alloyed.neoforge;
//? neoforge {
/*import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.AlloyedClient;
import com.molybdenum.alloyed.client.registry.ModSoundEvents;
import com.molybdenum.alloyed.common.CommonEventsHandler;
import com.molybdenum.alloyed.common.item.ModCreativeModeTab;
import com.molybdenum.alloyed.common.registry.ModBlockSetTypes;
import com.molybdenum.alloyed.common.registry.ModCompatItems;
import com.molybdenum.alloyed.common.registry.ModItems;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.registries.RegisterEvent;

@Mod(Alloyed.MOD_ID)
public class NeoForgeEntrypoint {
	public NeoForgeEntrypoint(IEventBus eventBus, ModContainer container) {

		NeoForgeMod.enableMilkFluid();
		if (FMLEnvironment.getDist().isClient()) {
			AlloyedClient.onClientInit();
			eventBus.addListener(NeoForgeEntrypoint::clientSetup);
		}
		eventBus.addListener(NeoForgeEntrypoint::commonSetup);
		eventBus.addListener(NeoForgeEntrypoint::onRegister);
	}

	public static void onRegister(RegisterEvent event) {
		if (event.getRegistryKey().equals(Registries.BLOCK)) {
			ModBlockSetTypes.register();
			Alloyed.registerBlocks();
		} else if (event.getRegistryKey().equals(Registries.ITEM)) {
			ModItems.register();
			ModCreativeModeTab.register();
			ModCompatItems.register();
		} else if (event.getRegistryKey().equals(Registries.SOUND_EVENT)) {
			ModSoundEvents.register();
		}
	}

	public static void clientSetup(FMLClientSetupEvent event) {
		AlloyedClient.clientSetup();
	}

	public static void commonSetup(FMLCommonSetupEvent event) {
		CommonEventsHandler.setupCommon();
	}

//	public static void addBlocks(final BlockEntityTypeAddBlocksEvent event) {
//		event.modify(AllBlockEntityTypes.ENCASED_COGWHEEL.getKey(), ModBlocks.STEEL_ENCASED_COGWHEEL.get(), ModBlocks.BRONZE_ENCASED_COGWHEEL.get());
//		event.modify(AllBlockEntityTypes.ENCASED_LARGE_COGWHEEL.getKey(), ModBlocks.STEEL_ENCASED_LARGE_COGWHEEL.get(),  ModBlocks.BRONZE_ENCASED_LARGE_COGWHEEL.get());
//		event.modify(AllBlockEntityTypes.ENCASED_SHAFT.getKey(), ModBlocks.STEEL_ENCASED_SHAFT.get(), ModBlocks.BRONZE_ENCASED_SHAFT.get());
//	}
}
*///?}