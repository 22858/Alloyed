package com.molybdenum.alloyed.common.util;

import com.molybdenum.alloyed.common.registry.ModTags;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.ItemStack;

public class AlloyedUtil {
	public static boolean isWrench(ItemStack arg) {
		var tag = BuiltInRegistries.ITEM.getTagOrEmpty(ModTags.Items.WRENCH_TOOLS);
		if (tag.iterator().hasNext()) {
			return arg.is(ModTags.Items.WRENCH_TOOLS);
		}
		return true;
	}
}
