package com.molybdenum.alloyed.common.screen;

import com.molybdenum.alloyed.common.CommonRegistry;
import net.fabricmc.fabric.api.menu.v1.ExtendedMenuType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.inventory.MenuType;

import java.util.function.Supplier;

public class ModMenuTypes {
	public static final Supplier<MenuType<ForgeMenu>> FORGE_MENU = CommonRegistry.registerMenu("oven", () -> new ExtendedMenuType<>(ForgeMenu::new, BlockPos.STREAM_CODEC));

	public static void register() {

	}
}