package com.molybdenum.alloyed.client.registry;

import com.molybdenum.alloyed.common.compat.create.CreateAlloyedBlocks;
import com.molybdenum.alloyed.common.registry.ModSpriteShifts;
import com.zurrtum.create.client.AllModels;
import com.zurrtum.create.client.content.decoration.encasing.EncasedCTBehaviour;
import com.zurrtum.create.client.infrastructure.model.CTModel;

public class ModTransformers {
	public static final EncasedCTBehaviour STEEL_CASING = new EncasedCTBehaviour(ModSpriteShifts.STEEL_CASING);
	public static final EncasedCTBehaviour BRONZE_CASING = new EncasedCTBehaviour(ModSpriteShifts.BRONZE_CASING);

	public static void register() {
		AllModels.register(CreateAlloyedBlocks.STEEL_CASING.get(), CTModel.of(STEEL_CASING));
		AllModels.register(CreateAlloyedBlocks.BRONZE_CASING.get(), CTModel.of(BRONZE_CASING));
	}
}
