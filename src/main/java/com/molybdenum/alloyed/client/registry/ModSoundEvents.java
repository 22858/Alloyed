package com.molybdenum.alloyed.client.registry;

import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.common.CommonRegistry;
import com.molybdenum.alloyed.common.registry.BlockEntry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class ModSoundEvents {

    public static final Supplier<SoundEvent> BRONZE_BELL = CommonRegistry.registerSoundEvent("bronze_bell");
	public static final Supplier<SoundEvent> FORGE_CRACKLE = CommonRegistry.registerSoundEvent("forge_crackle");

    public static void register(
    ) {
        Alloyed.LOGGER.debug("Registering ModSounds!");
    }
}