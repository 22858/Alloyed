package com.molybdenum.alloyed.common.registry;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public class ItemEntry<T extends Item> implements Supplier<T> {

	private final Identifier id;
	private Supplier<T> supplier;
	private T raw;

	public ItemEntry(Identifier id, Supplier<T> supplier) {
		this.id = id;
		this.supplier = supplier;
	}

	public ItemEntry(Identifier id, T raw) {
		this.id = id;
		this.raw = raw;
	}

	public Identifier getId() {
		return this.id;
	}

	@Override
	public T get() {
		return this.raw != null ? this.raw : this.supplier.get();
	}
}
