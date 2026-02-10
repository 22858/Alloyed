package com.molybdenum.alloyed.client.registry;

import com.molybdenum.alloyed.Alloyed;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
//? forge {
/*import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
*///?}
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class ModSoundEvents {

    //? forge {
    /*public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Alloyed.MOD_ID);
    *///?}
    
    public static final Supplier<SoundEvent> BRONZE_BELL = register("bronze_bell",
            () -> SoundEvent.createVariableRangeEvent(Alloyed.asResource("bronze_bell")));

	private static Supplier<SoundEvent> register(String id, Supplier<SoundEvent> soundEventSupplier) {
		//? fabric {
		SoundEvent register = Registry.register(BuiltInRegistries.SOUND_EVENT, id, soundEventSupplier.get());
		return ()->register;
		//?} else if forge
		/*return SOUND_EVENTS.register(id, soundEventSupplier);*/
	}

    public static void register(
            //? forge
            /*IEventBus eventBus*/
    ) {
        Alloyed.LOGGER.debug("Registering ModSounds!");
        //? forge
        /*SOUND_EVENTS.register(eventBus);*/
    }
}