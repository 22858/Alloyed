package com.molybdenum.alloyed.common.compat;

import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.common.item.ModItemTiers;
import com.notunanancyowen.spears.Spears;
import net.minecraft.world.item.Item;

public class SpearsCompat {
	public static Item registerSpear() {
		return Spears.registerSpear(Alloyed.asResource("steel_spear"), ModItemTiers.STEEL, 0.95F, 1.1F, 0.5F, 2.5F, 8.0F, 6.75F, 5.1F, 11.25F, 4.6F);
	}
}
