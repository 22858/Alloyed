package com.molybdenum.alloyed.common.registry;

import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.common.item.ModArmourMaterials;
import com.molybdenum.alloyed.common.item.ModItemTiers;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.*;
import net.minecraft.world.item.equipment.ArmorType;

import java.util.ArrayList;
import java.util.function.Function;

import static com.molybdenum.alloyed.Alloyed.MOD_ID;

public class ModItems {
    public static ArrayList<Item> ITEMS = new ArrayList<>();

    // Ingots

    public static final ItemEntry<Item> BRONZE_INGOT = taggedIngredient(
            "bronze_ingot"
    );

    public static final ItemEntry<Item> STEEL_INGOT = taggedIngredient(
            "steel_ingot"
    );

    // Nuggets

    public static final ItemEntry<Item> BRONZE_NUGGET = taggedIngredient(
            "bronze_nugget"
    );

    public static final ItemEntry<Item> STEEL_NUGGET = taggedIngredient(
            "steel_nugget"
    );

    // Sheets

    public static final ItemEntry<Item> BRONZE_SHEET = taggedIngredient("bronze_sheet", ModTags.Items.BRONZE_SHEET);

    public static final ItemEntry<Item> STEEL_SHEET = taggedIngredient("steel_sheet", ModTags.Items.STEEL_SHEET);

    // Steel toolset.

    public static final ItemEntry<Item> STEEL_SWORD = handheldItem(
            "steel_sword",
            properties -> new Item(properties.sword(ModItemTiers.STEEL, 3, -2.4F))
    );

    public static final ItemEntry<Item> STEEL_SPEAR = handheldItem(
            "steel_spear",
            properties -> new Item(properties.spear(ModItemTiers.STEEL, 0.95F, 1.1F, 0.5F, 2.5F, 8.0F, 6.75F, 5.1F, 11.25F, 4.6F))
    );


    public static final ItemEntry<Item> STEEL_PICKAXE = handheldItem(
            "steel_pickaxe",
            properties -> new Item(properties.pickaxe(ModItemTiers.STEEL, 1, -2.8F))
    );

    public static final ItemEntry<Item> STEEL_AXE = handheldItem(
            "steel_axe",
            properties -> new AxeItem(ModItemTiers.STEEL, 5.0F, -3.0F, properties.axe(ModItemTiers.STEEL, 5.0F, -3.0F))
    );


    public static final ItemEntry<Item> STEEL_SHOVEL = handheldItem(
            "steel_shovel",
            properties -> new ShovelItem(ModItemTiers.STEEL, 1.5F, -3.0F, properties.shovel(ModItemTiers.STEEL, 1.5F, -3.0F))
    );


    public static final ItemEntry<Item> STEEL_HOE = handheldItem(
            "steel_hoe",
            properties -> new HoeItem(ModItemTiers.STEEL,-3, 0.0F, properties.hoe(ModItemTiers.STEEL, -3, 0.0F))
    );


    public static final ItemEntry<ShearsItem> STEEL_SHEARS = registerItem("steel_shears", properties -> new ShearsItem(properties.component(DataComponents.TOOL, ShearsItem.createToolProperties()).durability(750)));


    public static final ItemEntry<FishingRodItem> STEEL_FISHING_ROD = registerItem("steel_fishing_rod", properties -> new FishingRodItem(properties.durability(512)));

    // Steel Armour
    public static final ItemEntry<Item> STEEL_HELMET = registerItem("steel_helmet", properties -> new Item(properties.durability(330).humanoidArmor(ModArmourMaterials.STEEL, ArmorType.HELMET)));

    public static final ItemEntry<Item> STEEL_CHESTPLATE = registerItem("steel_chestplate", properties -> new Item(properties.durability(480).humanoidArmor(ModArmourMaterials.STEEL, ArmorType.CHESTPLATE)));

    public static final ItemEntry<Item> STEEL_LEGGINGS = registerItem("steel_leggings", properties -> new Item(properties.durability(450).humanoidArmor(ModArmourMaterials.STEEL, ArmorType.LEGGINGS)));

    public static final ItemEntry<Item> STEEL_BOOTS = registerItem("steel_boots", properties -> new Item(properties.durability(390).humanoidArmor(ModArmourMaterials.STEEL, ArmorType.BOOTS)));

    public static final ItemEntry<Item> STEEL_HORSE_ARMOR = registerItem("steel_horse_armor", properties -> new Item(properties.stacksTo(1).horseArmor(ModArmourMaterials.STEEL)));

    public static final ItemEntry<Item> STEEL_NAUTILUS_ARMOR = registerItem("steel_nautilus_armor", properties -> new Item(properties.stacksTo(1).nautilusArmor(ModArmourMaterials.STEEL)));

    // End Item Entries

    public static void register() {
        Alloyed.LOGGER.debug("Registering ModItems!");
    }

    @SafeVarargs
    public static <T extends Item> ItemEntry<Item> handheldItem(String name, Function<Item.Properties, T> factory, TagKey<Item>... tags) {
        return (ItemEntry<Item>) registerItem(name, factory);
    }

    @SafeVarargs
    private static ItemEntry<Item> taggedIngredient(String name, TagKey<Item>... tags) {
        return registerItem(name, Item::new);
    }

    protected static <T extends Item> ItemEntry<T> registerItem(String id, Function<Item.Properties, T> factory, Item.Properties settings, boolean hidden) {
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(MOD_ID, id));
        T block = factory.apply(settings.setId(key));
        var entry = Registry.register(BuiltInRegistries.ITEM, key, block);
        if (!hidden)
            ITEMS.add(entry);
        return new ItemEntry<>(key.identifier(), entry);
    }

    private static <T extends Item> ItemEntry<T> registerItem(String id, Function<Item.Properties, T> factory, Item.Properties settings) {
        return registerItem(id, factory, settings, false);
    }

    public static ItemEntry<?> registerBlockItem(BlockEntry<?> blockEntry) {
        return registerItem(blockEntry.getId().getPath(), (properties)-> new BlockItem(blockEntry.get(), properties), new Item.Properties().useBlockDescriptionPrefix());
    }

    private static <T extends Item> ItemEntry<T> registerItem(String id, Function<Item.Properties, T> factory) {
        return registerItem(id, factory, new Item.Properties());
    }
}