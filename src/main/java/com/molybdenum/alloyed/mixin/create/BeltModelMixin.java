package com.molybdenum.alloyed.mixin.create;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.molybdenum.alloyed.client.registry.ModPartialModels;
import com.molybdenum.alloyed.common.content.extensions.BeltBlockEntityExtension;
import com.molybdenum.alloyed.common.content.extensions.BeltModelExtension;
import com.molybdenum.alloyed.common.registry.ModSpriteShifts;
import com.zurrtum.create.client.catnip.render.SpriteShiftEntry;
import com.zurrtum.create.client.infrastructure.model.BeltModel;
import com.zurrtum.create.client.infrastructure.model.WrapperBlockStateModel;
import com.zurrtum.create.content.kinetics.belt.BeltBlock;
import net.minecraft.client.renderer.block.model.BlockModelPart;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

@Mixin(BeltModel.class)
public abstract class BeltModelMixin
		//? fabric
		extends WrapperBlockStateModel
		implements BeltModelExtension {

	@Shadow
	protected abstract BlockModelPart replaceQuads(TextureAtlasSprite replace, BlockModelPart part);

	//? fabric {
	@WrapMethod(
			method = "addPartsWithInfo",
			remap = false
	)
	private void handleAlloyedCasingRendering(BlockAndTintGetter world, BlockPos pos, BlockState state, RandomSource random, List<BlockModelPart> parts, Operation<Void> operation) {
		BlockEntity blockentity = world.getBlockEntity(pos);
		if (blockentity instanceof BeltBlockEntityExtension beltBlockEntityExtension && beltBlockEntityExtension.getAlloyedCasingType() != BeltBlockEntityExtension.AlloyedCasingType.NONE) {
			if (beltBlockEntityExtension.getAlloyedCasingType() == BeltBlockEntityExtension.AlloyedCasingType.BRONZE) {
				this.model.collectParts(random, parts);
				if (beltBlockEntityExtension.create_alloyed$isCovered()) {
					boolean alongX = state.getValue(BeltBlock.HORIZONTAL_FACING).getAxis() == Direction.Axis.X;
					parts.add(alongX ? ModPartialModels.BRONZE_BELT_COVER_X.get() : ModPartialModels.BRONZE_BELT_COVER_Z.get());
				}

			} else {
				var SPRITE_SHIFT = ModSpriteShifts.STEEL_BELT_CASING;
				TextureAtlasSprite original = SPRITE_SHIFT.getOriginal();
				if (beltBlockEntityExtension.create_alloyed$isCovered()) {
					boolean alongX = state.getValue(BeltBlock.HORIZONTAL_FACING).getAxis() == Direction.Axis.X;
					parts.add(this.replaceQuads(original, alongX ? ModPartialModels.STEEL_BELT_COVER_X.get() : ModPartialModels.STEEL_BELT_COVER_Z.get()));
				}

				for(BlockModelPart part : this.model.collectParts(random)) {
					parts.add(this.replaceQuads(original, part));
				}

			}
		} else {
			operation.call(world, pos, state, random, parts);
		}
	}
	//?}



}
