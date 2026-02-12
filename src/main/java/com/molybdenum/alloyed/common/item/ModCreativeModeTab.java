package com.molybdenum.alloyed.common.item;

import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.common.registry.ModItems;
//? fabric
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.*;

public class ModCreativeModeTab {
    public static final ResourceKey<CreativeModeTab> MAIN_TAB_KEY = ResourceKey.create(Registries.CREATIVE_MODE_TAB, Alloyed.asResource("main_group"));
    public static final CreativeModeTab MAIN_TAB =
            //? fabric
            FabricCreativeModeTab
            //? neoforge
            /*CreativeModeTab*/
            .builder().icon(()-> ModItems.STEEL_INGOT.get().getDefaultInstance()).title(Component.translatable("itemGroup.alloyed.main_group")).displayItems(((itemDisplayParameters, output) -> {
        for (Item item : ModItems.ITEMS) {
            output.accept(item);
        }
    })).build();

    public static void register() {
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, MAIN_TAB_KEY, MAIN_TAB);
    }
}
