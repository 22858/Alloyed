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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageView;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleSlotStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.minecraft.CrashReport;
import net.minecraft.ReportedException;
import org.jetbrains.annotations.NotNull;

public abstract class FabricWrappedInventory implements ItemHandler {
	private final List<ItemHandlerStackWrapper> fabricWrappers;

	public FabricWrappedInventory(int size) {
		this.fabricWrappers = new ArrayList(size);

		for(int i = 0; i < size; ++i) {
			this.fabricWrappers.add(new ItemHandlerStackWrapper(this, i));
		}

	}

	public SingleSlotStorage<ItemVariant> getSlot(int slot) {
		return this.fabricWrappers.get(slot);
	}

	public long insert(ItemVariant resource, long maxAmount, TransactionContext transaction) {
		long amount = 0L;

		try {
			for(ItemHandlerStackWrapper wrapper : this.fabricWrappers) {
				amount += wrapper.insert(resource, maxAmount - amount, transaction);
				if (amount == maxAmount) {
					return maxAmount;
				}
			}

			return amount;
		} catch (Exception ex) {
			CrashReport report = CrashReport.forThrowable(ex, "Inserting resources into slots");
			report.addCategory("Slotted insertion details").setDetail("Slots", () -> Objects.toString(this.fabricWrappers, (String)null)).setDetail("Resource", () -> Objects.toString(resource, (String)null)).setDetail("Max amount", maxAmount).setDetail("Transaction", transaction);
			throw new ReportedException(report);
		}
	}

	public long extract(ItemVariant resource, long maxAmount, TransactionContext transaction) {
		long amount = 0L;

		try {
			for(ItemHandlerStackWrapper wrapper : this.fabricWrappers) {
				amount += wrapper.extract(resource, maxAmount - amount, transaction);
				if (amount == maxAmount) {
					return maxAmount;
				}
			}

			return amount;
		} catch (Exception ex) {
			CrashReport report = CrashReport.forThrowable(ex, "Inserting resources into slots");
			report.addCategory("Slotted insertion details").setDetail("Slots", () -> Objects.toString(this.fabricWrappers, (String)null)).setDetail("Resource", () -> Objects.toString(resource, (String)null)).setDetail("Max amount", maxAmount).setDetail("Transaction", transaction);
			throw new ReportedException(report);
		}
	}


	@Override
	public @NotNull Iterator<StorageView<ItemVariant>> iterator() {
		return getSlots().stream()
				.map(storageViews -> (StorageView<ItemVariant>)storageViews)
				.iterator();
	}
}
