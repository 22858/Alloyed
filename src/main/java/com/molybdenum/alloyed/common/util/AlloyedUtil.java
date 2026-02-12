package com.molybdenum.alloyed.common.util;

import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.world.item.ItemStack;

public class AlloyedUtil {
	public static boolean isWrench(ItemStack arg) {
		return arg.is(ConventionalItemTags.WRENCH_TOOLS);
	}
}
