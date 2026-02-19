package com.molybdenum.alloyed.common.screen.slot;

import com.molybdenum.alloyed.common.handler.ItemStackHandler;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class ModResultSlot extends ModInputSlot {

	public ModResultSlot(ItemStackHandler itemHandler, int index, int x, int y) {
		super(itemHandler, index, x, y);
	}

	@Override
	public boolean mayPlace(ItemStack stack) {
		return false;
	}

	@Override
	public void onTake(Player player, ItemStack stack) {
	}
}