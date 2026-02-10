package com.molybdenum.alloyed;

import com.molybdenum.alloyed.client.registry.ModSoundEvents;
import com.molybdenum.alloyed.common.item.ModCreativeModeTab;
import com.molybdenum.alloyed.common.registry.*;
import com.molybdenum.alloyed.common.util.Platform;
import com.simibubi.create.foundation.data.CreateRegistrate;
import net.minecraft.resources.ResourceLocation;
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
    public static boolean isCreateDecoLoaded = false;

    public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(MOD_ID);

    public static void init(
            //? neoforge
            /*IEventBus bus*/
    ) {

        isFarmersDelightLoaded = Platform.isLoaded("farmersdelight");
        isCreateDecoLoaded = Platform.isLoaded("createdeco");

        //? neoforge
        /*NeoForgeMod.enableMilkFluid();*/

        ModBlockSetTypes.register();
        ModBlocks.register();
        ModItems.register();
        ModCreativeModeTab.register(
                //? neoforge
                /*bus*/
        );
        ModCompatItems.register();
        if (isCreateDecoLoaded)
            ModCompatBlocks.register();
        ModSoundEvents.register(
                //? neoforge
                /*bus*/
        );
    }


    public static ResourceLocation asResource(String path) {
        return Alloyed.asResource(MOD_ID, path);
    }

    public static ResourceLocation asVanillaResource(String path) {
        return Alloyed.asResource("minecraft", path);
    }

    @SuppressWarnings("all")
    public static ResourceLocation asResource(String forge, String name) {
        return ResourceLocation.fromNamespaceAndPath(forge, name);
    }

}