package com.molybdenum.alloyed.common.item;

import java.util.Map;

import com.google.common.collect.Maps;
import com.molybdenum.alloyed.Alloyed;

import com.molybdenum.alloyed.common.registry.ModTags;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;

import static net.minecraft.world.item.equipment.EquipmentAssets.ROOT_ID;

public class ModArmourMaterials {

    public static ArmorMaterial STEEL = new ArmorMaterial(
            15, Maps.newEnumMap(Map.of(
            ArmorType.BOOTS, 3,
            ArmorType.LEGGINGS, 5,
            ArmorType.CHESTPLATE, 7,
            ArmorType.HELMET, 3,
            ArmorType.BODY, 7)),
            12, SoundEvents.ARMOR_EQUIP_GENERIC, 0.0F, 0.0F, ModTags.Items.STEEL_INGOT, ResourceKey.create(ROOT_ID, Alloyed.asResource("steel"))
    );

    public static void register() {

    }
}