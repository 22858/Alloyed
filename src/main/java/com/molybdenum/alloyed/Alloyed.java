package com.molybdenum.alloyed;

import com.molybdenum.alloyed.client.registry.ModSoundEvents;
import com.molybdenum.alloyed.common.compat.create.CreateAlloyedBlocks;
import com.molybdenum.alloyed.common.item.ModCreativeModeTab;
import com.molybdenum.alloyed.common.registry.*;
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

    // Compat
    public static boolean isFarmersDelightLoaded = false;

    public static void init() {

        isFarmersDelightLoaded = Platform.isLoaded("farmersdelight");

        //? neoforge
        /*NeoForgeMod.enableMilkFluid();*/

        if (Platform.isLoaded("create")) {
            CreateAlloyedBlocks.register();
        }

        ModBlockSetTypes.register();
        ModBlocks.register();
        ModItems.register();
        ModCreativeModeTab.register();
        ModCompatItems.register();
        ModSoundEvents.register();
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