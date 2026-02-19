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

//? fabric {

package com.molybdenum.alloyed.common.handler;

import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.item.base.SingleStackStorage;
import net.minecraft.world.item.ItemStack;

public class ItemHandlerStackWrapper extends SingleStackStorage {
	private final ItemHandler handler;
	private final int slot;

	public ItemHandlerStackWrapper(ItemHandler handler, int slot) {
		this.handler = handler;
		this.slot = slot;
	}

	protected ItemStack getStack() {
		return this.handler.getStackInSlot(this.slot);
	}

	protected void setStack(ItemStack stack) {
		this.handler.setStackInSlot(this.slot, stack);
	}

	protected int getCapacity(ItemVariant itemVariant) {
		return itemVariant.isBlank() ? this.handler.getSlotLimit(this.slot) : Math.min(this.handler.getSlotLimit(this.slot), itemVariant.toStack().getMaxStackSize());
	}
}
//?}