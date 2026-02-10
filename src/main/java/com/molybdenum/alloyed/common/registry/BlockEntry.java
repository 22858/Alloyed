package com.molybdenum.alloyed.common.registry;

import com.molybdenum.alloyed.Alloyed;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.Nullable;

import java.util.function.Supplier;

public class BlockEntry<T extends Block> implements Supplier<T> {

	private final Identifier id;
	private Supplier<T> supplier;
	private T raw;

	public BlockEntry(Identifier id, Supplier<T> supplier) {
		this.id = id;
		this.supplier = supplier;
	}

	public BlockEntry(Identifier id, T raw) {
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

	public boolean isIn(ItemStack heldItem) {
		return heldItem.is(this.raw.asItem());
	}

	public @Nullable BlockState getDefaultState() {
		return raw.defaultBlockState();
	}
}
