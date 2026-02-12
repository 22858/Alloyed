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
//? neoforge {
/*import net.neoforged.neoforge.client.model.data.ModelData;
*///?} else {
//?}
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
	//? neoforge {
    /*@Inject(
            method = "getQuads",
            at = @At(value = "RETURN", ordinal = 1),
            cancellable = true,
            remap = false
    )
    private void handleAlloyedCasingRendering(BlockState state, Direction side, RandomSource rand, ModelData extraData, RenderType renderType, CallbackInfoReturnable<List<BakedQuad>> cir, @Local List quads, @Local(ordinal = 0) boolean cover) {
        BeltBlockEntityExtension.AlloyedCasingType alloyedType = extraData.get(ALLOYED_CASING_PROPERTY);
        if (alloyedType == BeltBlockEntityExtension.AlloyedCasingType.NONE) return;

        ArrayList<BakedQuad> newQuads = new ArrayList<>(quads);
        var belt_cover_x = ModPartialModels.STEEL_BELT_COVER_X;
        var belt_cover_z = ModPartialModels.STEEL_BELT_COVER_Z;
        var belt_casing = ModSpriteShifts.STEEL_BELT_CASING;
        if (alloyedType == BeltBlockEntityExtension.AlloyedCasingType.BRONZE) {
            belt_cover_x = ModPartialModels.BRONZE_BELT_COVER_X;
            belt_cover_z = ModPartialModels.BRONZE_BELT_COVER_Z;
            belt_casing = ModSpriteShifts.BRONZE_BELT_CASING;
        }

        if (cover) {
            boolean alongX = state.getValue(BeltBlock.HORIZONTAL_FACING)
                    .getAxis() == Direction.Axis.X;
            BakedModel coverModel =
                    (alongX ? belt_cover_x : belt_cover_z).get();
            newQuads.addAll(coverModel.getQuads(state, side, rand));
        }

        for (int i = 0; i < newQuads.size(); i++) {
            BakedQuad quad = newQuads.get(i);
            TextureAtlasSprite original = quad.getSprite();
            if (original != belt_casing.getOriginal())
                continue;

            BakedQuad newQuad = BakedQuadHelper.clone(quad);
            int[] vertexData = newQuad.getVertices();

            for (int vertex = 0; vertex < 4; vertex++) {
                float u = BakedQuadHelper.getU(vertexData, vertex);
                float v = BakedQuadHelper.getV(vertexData, vertex);
                BakedQuadHelper.setU(vertexData, vertex, belt_casing.getTargetU(u));
                BakedQuadHelper.setV(vertexData, vertex, belt_casing.getTargetV(v));
            }

            newQuads.set(i, newQuad);
        }

        cir.setReturnValue(newQuads);
    }
     @Inject(
            method = "getParticleIcon",
            at = @At("HEAD"),
            cancellable = true,
            remap = false
    )
    private void returnAlloyedSpritesIfNeeded(ModelData data, CallbackInfoReturnable<TextureAtlasSprite> cir) {
        if (!data.has(ALLOYED_CASING_PROPERTY)) return;
        if (data.get(ALLOYED_CASING_PROPERTY) == BeltBlockEntityExtension.AlloyedCasingType.STEEL) {
            cir.setReturnValue(ModSpriteShifts.STEEL_CASING.getOriginal());
            cir.cancel();
        }
        if (data.get(ALLOYED_CASING_PROPERTY) == BeltBlockEntityExtension.AlloyedCasingType.BRONZE) {
            cir.setReturnValue(ModSpriteShifts.BRONZE_CASING.getOriginal());
            cir.cancel();
        }
    }
    *///?} else {
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
