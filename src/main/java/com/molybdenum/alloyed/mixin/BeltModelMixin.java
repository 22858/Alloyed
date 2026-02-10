package com.molybdenum.alloyed.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.sugar.Local;
import com.molybdenum.alloyed.client.registry.ModPartialModels;
import com.molybdenum.alloyed.common.content.extensions.BeltBlockEntityExtension;
import com.molybdenum.alloyed.common.content.extensions.BeltModelExtension;
import com.molybdenum.alloyed.common.registry.ModSpriteShifts;
import com.simibubi.create.AllPartialModels;
import com.simibubi.create.AllSpriteShifts;
import com.simibubi.create.content.kinetics.belt.BeltBlock;
import com.simibubi.create.content.kinetics.belt.BeltBlockEntity;
import com.simibubi.create.content.kinetics.belt.BeltModel;
import com.simibubi.create.foundation.model.BakedQuadHelper;
import net.createmod.catnip.render.SpriteShiftEntry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;
//? forge {
/*import net.minecraftforge.client.model.data.ModelData;
*///?} else {
import net.fabricmc.fabric.api.renderer.v1.render.RenderContext;
import net.fabricmc.fabric.api.renderer.v1.model.ForwardingBakedModel;
import net.fabricmc.fabric.api.renderer.v1.model.SpriteFinder;
//?}
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@Mixin(BeltModel.class)
public class BeltModelMixin
		//? fabric
		extends ForwardingBakedModel
		implements BeltModelExtension {


    //? forge {
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
			method = "emitBlockQuads",
			remap = false
	)
	private void handleAlloyedCasingRendering(BlockAndTintGetter blockView, BlockState state, BlockPos pos, Supplier<RandomSource> randomSupplier, RenderContext context, Operation<Void> original) {
		if (blockView.getBlockEntity(pos) instanceof BeltBlockEntityExtension data && !data.getAlloyedCasingType().equals(BeltBlockEntityExtension.AlloyedCasingType.NONE)) {
//			if (!(blockView.getBlockEntityRenderData(pos) instanceof BeltBlockEntity.RenderData data)) {
//				super.emitBlockQuads(blockView, state, pos, randomSupplier, context);
//				return;
//			}

			boolean cover = data.create_alloyed$isCovered();
			BeltBlockEntityExtension.AlloyedCasingType type = data.getAlloyedCasingType();
			boolean steelCasing = type == BeltBlockEntityExtension.AlloyedCasingType.STEEL;
			SpriteShiftEntry SPRITE_SHIFT;
			if (steelCasing) {
				SPRITE_SHIFT = ModSpriteShifts.STEEL_BELT_CASING;
			} else {
				SPRITE_SHIFT = ModSpriteShifts.BRONZE_BELT_CASING;
			}

//			if (type == BeltBlockEntityExtension.AlloyedCasingType.NONE || steelCasing && !cover) {
//				super.emitBlockQuads(blockView, state, pos, randomSupplier, context);
//				return;
//			}

			SpriteFinder spriteFinder = SpriteFinder.get(Minecraft.getInstance().getModelManager().getAtlas(InventoryMenu.BLOCK_ATLAS));
			context.pushTransform(quad -> {
				TextureAtlasSprite sprite = spriteFinder.find(quad, 0);
				if (sprite == SPRITE_SHIFT.getOriginal()) {
					for (int vertex = 0; vertex < 4; vertex++) {
						float u = quad.spriteU(vertex, 0);
						float v = quad.spriteV(vertex, 0);
						quad.sprite(vertex, 0,
								SPRITE_SHIFT.getTargetU(u),
								SPRITE_SHIFT.getTargetV(v)
						);
					}
				}
				return true;
			});

			super.emitBlockQuads(blockView, state, pos, randomSupplier, context);

			if (cover) {
				boolean alongX = state.getValue(BeltBlock.HORIZONTAL_FACING)
						.getAxis() == Direction.Axis.X;
				BakedModel coverModel;
				if (steelCasing)
					coverModel = (alongX ? ModPartialModels.STEEL_BELT_COVER_X : ModPartialModels.STEEL_BELT_COVER_Z).get();
				else
					coverModel = (alongX ? ModPartialModels.BRONZE_BELT_COVER_X : ModPartialModels.BRONZE_BELT_COVER_Z).get();
				coverModel.emitBlockQuads(blockView, state, pos, randomSupplier, context);
			}

			if (!steelCasing) {
				context.popTransform();
			}
		} else {
			original.call(blockView, state, pos, randomSupplier, context);
		}
	}
    //?}



}
