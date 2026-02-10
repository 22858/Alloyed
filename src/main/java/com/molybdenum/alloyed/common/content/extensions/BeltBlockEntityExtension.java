package com.molybdenum.alloyed.common.content.extensions;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.StringRepresentable;

import java.util.Locale;

public interface BeltBlockEntityExtension {
    void create_alloyed$setAlloyedCasingType(AlloyedCasingType value);
    void create_alloyed$setAlloyedCasingTypeRaw(AlloyedCasingType value);

    AlloyedCasingType getAlloyedCasingType();

    boolean create_alloyed$isCovered();

    enum AlloyedCasingType implements StringRepresentable {
        NONE, STEEL, BRONZE;
		public static final Codec<AlloyedCasingType> CODEC = StringRepresentable.fromEnum(AlloyedCasingType::values);
		public static final StreamCodec<ByteBuf, AlloyedCasingType> STREAM_CODEC = ByteBufCodecs.fromCodec(CODEC);

		@Override
		public String getSerializedName() {
			return this.name().toLowerCase(Locale.ROOT);
		}
	}
}
