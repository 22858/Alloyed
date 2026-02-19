package com.molybdenum.alloyed.common.registry;

import com.molybdenum.alloyed.Alloyed;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class BlockEntry<T extends Block> implements Supplier<T> {

	private final ResourceLocation id;
	private Supplier<T> supplier;
	private T raw;

	public BlockEntry(ResourceLocation id, Supplier<T> supplier) {
		this.id = id;
		this.supplier = supplier;
	}

	public BlockEntry(ResourceLocation id, T raw) {
		this.id = id;
		this.raw = raw;
	}

	public ResourceLocation getId() {
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

	public Supplier<Block> supplier() {
		return (Supplier<Block>) supplier;
	}
}
