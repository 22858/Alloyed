package com.molybdenum.alloyed.client.registry;

import com.molybdenum.alloyed.Alloyed;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
//? neoforge {
/*import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
*///?}
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class ModSoundEvents {

    //? neoforge {
    /*public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, Alloyed.MOD_ID);
    *///?}
    
    public static final Supplier<SoundEvent> BRONZE_BELL = register("bronze_bell",
            () -> SoundEvent.createVariableRangeEvent(Alloyed.asResource("bronze_bell")));

	private static Supplier<SoundEvent> register(String id, Supplier<SoundEvent> soundEventSupplier) {
		//? fabric {
		SoundEvent register = Registry.register(BuiltInRegistries.SOUND_EVENT, id, soundEventSupplier.get());
		return ()->register;
		//?} else if neoforge
		/*return SOUND_EVENTS.register(id, soundEventSupplier);*/
	}

    public static void register(
            //? neoforge
            /*IEventBus eventBus*/
    ) {
        Alloyed.LOGGER.debug("Registering ModSounds!");
        //? neoforge
        /*SOUND_EVENTS.register(eventBus);*/
    }
}