package com.molybdenum.alloyed;

import com.molybdenum.alloyed.client.registry.ModSoundEvents;
import com.molybdenum.alloyed.common.item.ModCreativeModeTab;
import com.molybdenum.alloyed.common.registry.*;
import com.molybdenum.alloyed.common.util.Platform;
import com.simibubi.create.foundation.data.CreateRegistrate;
import net.minecraft.resources.ResourceLocation;
//? forge
/*import net.minecraftforge.eventbus.api.IEventBus;*/
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
            //? forge
            /*IEventBus bus*/
    ) {

        isFarmersDelightLoaded = Platform.isLoaded("farmersdelight");
        isCreateDecoLoaded = Platform.isLoaded("createdeco");

        ModBlockSetTypes.register();
        ModBlocks.register();
        ModItems.register();
        ModCreativeModeTab.register(
                //? forge
                /*bus*/
        );
        ModCompatItems.register();
        if (isCreateDecoLoaded)
            ModCompatBlocks.register();
        ModSoundEvents.register(
                //? forge
                /*bus*/
        );
    }

    @SuppressWarnings("all")
    public static ResourceLocation asResource(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

}