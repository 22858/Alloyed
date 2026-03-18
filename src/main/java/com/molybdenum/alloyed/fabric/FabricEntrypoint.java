package com.molybdenum.alloyed.fabric;
//? fabric {
import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.common.CommonEventsHandler;
import com.molybdenum.alloyed.common.compat.create.CreateAlloyedBlocks;
import com.molybdenum.alloyed.common.compat.create.CreateCompat;
import com.molybdenum.alloyed.common.item.ModCreativeModeTab;
import com.molybdenum.alloyed.common.registry.ModItems;
import com.simibubi.create.AllBlockEntityTypes;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;

import static com.molybdenum.alloyed.Alloyed.MOD_ID;
import static com.molybdenum.alloyed.common.compat.create.CreateAlloyedBlocks.*;

public class FabricEntrypoint implements ModInitializer {
	@Override
	public void onInitialize() {
		Alloyed.init();
		CommonEventsHandler.setupCommon();
		if (Alloyed.CONFIG.integratedForges)
			ResourceManagerHelper.registerBuiltinResourcePack(Alloyed.asResource("integrated_forges"), FabricLoader.getInstance().getModContainer(MOD_ID).orElseThrow(), ResourcePackActivationType.DEFAULT_ENABLED);
		if (FabricLoader.getInstance().isModLoaded("create")) {
			CreateCompat.register();
			ServerLifecycleEvents.SERVER_STARTING.register(minecraftServer -> {
				CreateCompat.addBlocks();
			});
			ItemGroupEvents.modifyEntriesEvent(ModCreativeModeTab.MAIN_TAB_KEY).register(itemGroupEntries->{
				itemGroupEntries.accept(BRONZE_CASING.asItem());
				itemGroupEntries.accept(STEEL_CASING.asItem());
				itemGroupEntries.accept(STEEL_LADDER.asItem());
				itemGroupEntries.accept(STEEL_SCAFFOLD.asItem());
			});
		}
	}
}
//?}
