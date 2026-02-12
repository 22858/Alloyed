package com.molybdenum.alloyed.client.registry;

import com.molybdenum.alloyed.Alloyed;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class ModSoundEvents {

    public static final Supplier<SoundEvent> BRONZE_BELL = register("bronze_bell",
            () -> SoundEvent.createVariableRangeEvent(Alloyed.asResource("bronze_bell")));

	private static Supplier<SoundEvent> register(String id, Supplier<SoundEvent> soundEventSupplier) {
		SoundEvent register = Registry.register(BuiltInRegistries.SOUND_EVENT, id, soundEventSupplier.get());
		return ()->register;

	}

    public static void register(
    ) {
        Alloyed.LOGGER.debug("Registering ModSounds!");
    }
}