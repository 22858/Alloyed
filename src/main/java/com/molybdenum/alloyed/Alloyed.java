package com.molybdenum.alloyed;

import com.molybdenum.alloyed.client.registry.ModSoundEvents;
import com.molybdenum.alloyed.common.compat.VanillaAlloyedBlocks;
import com.molybdenum.alloyed.common.compat.create.CreateAlloyedBlocks;
import com.molybdenum.alloyed.common.config.ModConfig;
import com.molybdenum.alloyed.common.content.recipes.ModRecipes;
import com.molybdenum.alloyed.common.item.ModCreativeModeTab;
import com.molybdenum.alloyed.common.registry.*;
import com.molybdenum.alloyed.common.screen.ModMenuTypes;
import com.molybdenum.alloyed.common.util.Platform;
import net.minecraft.resources.Identifier;
//? neoforge {
/*import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.bus.api.IEventBus;
*///?}
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Alloyed {

    public static final String MOD_ID = "alloyed";
    public static final Logger LOGGER = LogManager.getLogger();
    public static final ModConfig CONFIG = ModConfig.createToml(Platform.getConfigDir(), "", MOD_ID, ModConfig.class);

    // Compat
    public static boolean isFarmersDelightLoaded = false;

    public static void init() {

        isFarmersDelightLoaded = Platform.isLoaded("farmersdelight");

        //? neoforge
        /*NeoForgeMod.enableMilkFluid();*/

        ModBlockSetTypes.register();
        registerBlocks();
        ModCreativeModeTab.register();
        registerItems();
        ModSoundEvents.register();
        ModRecipes.register();
        ModBlockEntities.register();
        ModMenuTypes.register();
    }

    public static void registerBlocks() {
        ModBlocks.register();
        if (Platform.isLoaded("create")) {
            CreateAlloyedBlocks.register();
        } else {
            VanillaAlloyedBlocks.register();
        }
    }

    public static void registerItems() {
        ModItems.register();
        if (Platform.isLoaded("farmersdelight"))
            FDCompatItems.register();
    }

    public static Identifier asResource(String path) {
        return Alloyed.asResource(MOD_ID, path);
    }

    public static Identifier asVanillaResource(String path) {
        return Alloyed.asResource("minecraft", path);
    }

    @SuppressWarnings("all")
    public static Identifier asResource(String forge, String name) {
        return Identifier.fromNamespaceAndPath(forge, name);
    }

	public static Identifier asCreateResource(String s) {
		return Alloyed.asResource("create", s);
	}
}