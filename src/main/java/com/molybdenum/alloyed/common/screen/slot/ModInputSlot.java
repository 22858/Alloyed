//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.molybdenum.alloyed.common.screen.slot;

import com.molybdenum.alloyed.common.handler.ItemHandler;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class ModInputSlot extends Slot {
	private static final Container EMPTY_INVENTORY = new SimpleContainer(0);
	private final ItemHandler itemHandler;

	public ModInputSlot(ItemHandler inventoryIn, int index, int xPosition, int yPosition) {
		super(EMPTY_INVENTORY, index, xPosition, yPosition);
		this.itemHandler = inventoryIn;
	}

	public boolean mayPlace(ItemStack stack) {
		return !stack.isEmpty() && this.itemHandler.isItemValid(this.getContainerSlot(), stack);
	}

	public ItemStack getItem() {
		return this.itemHandler.getStackInSlot(this.getContainerSlot());
	}

	public void set(ItemStack stack) {
		this.itemHandler.setStackInSlot(this.getContainerSlot(), stack);
		this.setChanged();
	}

	public ItemStack remove(int amount) {
		ItemStack stack = this.itemHandler.extractItem(this.getContainerSlot(), amount, false);
		this.setChanged();
		return stack;
	}

	public int getMaxStackSize() {
		return this.itemHandler.getSlotLimit(this.getContainerSlot());
	}

	public ItemHandler getItemHandler() {
		return this.itemHandler;
	}
}
