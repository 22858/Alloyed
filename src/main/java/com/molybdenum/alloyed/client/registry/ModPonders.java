package com.molybdenum.alloyed.client.registry;

import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.client.ponder.BronzeBellPonder;
import com.molybdenum.alloyed.common.registry.ModBlocks;
import com.simibubi.create.infrastructure.ponder.AllCreatePonderTags;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.createmod.ponder.foundation.ui.PonderTagScreen;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;

import java.util.function.BiConsumer;

public class ModPonders {

    public static void register(PonderSceneRegistrationHelper<ResourceLocation> helper) {
        Alloyed.LOGGER.debug("Registering ModPonders!");
        PonderSceneRegistrationHelper<PonderTagScreen.ItemEntry> PONDER = helper.withKeyFunction(PonderTagScreen.ItemEntry::key);


        PONDER.forComponents(new PonderTagScreen.ItemEntry(ModBlocks.BRONZE_BELL.get(), ModBlocks.BRONZE_BELL.getId()))
                .addStoryBoard("bronze_bell/decoration", BronzeBellPonder::decoration, AllCreatePonderTags.DECORATION)
                .addStoryBoard("bronze_bell/instrument", BronzeBellPonder::instrument, AllCreatePonderTags.DECORATION);
    }

    public static void registerLang() {

    }
}