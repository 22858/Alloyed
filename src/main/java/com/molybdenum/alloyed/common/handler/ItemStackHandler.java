/*
MIT License

Copyright (c) 2020 vectorwing

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

=============================================================================
This software includes code from the NeoForge project
https://github.com/neoforged/NeoForge/
For specifically InvWrapper and ItemStackHandler logic.

NeoForge is licensed under the LGPL 2.1 license.
You may read the license here:
https://github.com/neoforged/NeoForge/blob/1.21.x/LICENSE.txt

Some files may include others' licenses. Please read the top of the file
for information on other licenses.
=============================================================================
*/
package com.molybdenum.alloyed.common.handler;

import net.minecraft.core.NonNullList;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public class ItemStackHandler
		//? fabric
		extends FabricWrappedInventory
		//? neoforge
		/*extends NeoWrappedInventory*/
{
	private final NonNullList<ItemStack> stacks;

	public ItemStackHandler() {
		this(1);
	}

	public ItemStackHandler(int size) {
		super(size);
		this.stacks = NonNullList.withSize(size, ItemStack.EMPTY);
	}

	public int getSlotCount() {
		return this.stacks.size();
	}

	public int getSlotLimit(int slot) {
		return 99;
	}

	protected int getStackLimit(int slot, ItemStack stack) {
		return Math.min(this.getSlotLimit(slot), stack.getMaxStackSize());
	}

	public boolean isItemValid(int slot, ItemStack stack) {
		return true;
	}

	public ItemStack getStackInSlot(int slot) {
		return this.stacks.get(slot);
	}

	public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
		if (stack.isEmpty()) {
			return ItemStack.EMPTY;
		} else if (!this.isItemValid(slot, stack)) {
			return stack;
		} else {
			ItemStack existing = this.stacks.get(slot);
			int limit = this.getStackLimit(slot, stack);
			if (!existing.isEmpty()) {
				if (!ItemStack.isSameItemSameComponents(stack, existing)) {
					return stack;
				}

				limit -= existing.getCount();
			}

			if (limit <= 0) {
				return stack;
			} else {
				boolean reachedLimit = stack.getCount() > limit;
				if (!simulate) {
					if (existing.isEmpty()) {
						this.stacks.set(slot, reachedLimit ? stack.copyWithCount(limit) : stack);
					} else {
						existing.grow(reachedLimit ? limit : stack.getCount());
					}

					this.onContentsChanged(slot);
				}

				return reachedLimit ? stack.copyWithCount(stack.getCount() - limit) : ItemStack.EMPTY;
			}
		}
	}

	public ItemStack extractItem(int slot, int amount, boolean simulate) {
		if (amount == 0) {
			return ItemStack.EMPTY;
		} else {
			ItemStack existing = this.stacks.get(slot);
			if (existing.isEmpty()) {
				return ItemStack.EMPTY;
			} else {
				int toExtract = Math.min(amount, existing.getMaxStackSize());
				if (existing.getCount() <= toExtract) {
					if (!simulate) {
						this.stacks.set(slot, ItemStack.EMPTY);
						this.onContentsChanged(slot);
						return existing;
					} else {
						return existing.copy();
					}
				} else {
					if (!simulate) {
						this.stacks.set(slot, existing.copyWithCount(existing.getCount() - toExtract));
						this.onContentsChanged(slot);
					}

					return existing.copyWithCount(toExtract);
				}
			}
		}
	}

	public void setStackInSlot(int slot, ItemStack stack) {
		this.stacks.set(slot, stack);
		this.onContentsChanged(slot);
	}

	public void serialize(ValueOutput output) {
		ContainerHelper.saveAllItems(output, this.stacks);
	}

	public void deserialize(ValueInput input) {
		this.stacks.clear();
		ContainerHelper.loadAllItems(input, this.stacks);
	}

	protected void onContentsChanged(int slot) {
	}
}
