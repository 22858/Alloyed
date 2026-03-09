package com.molybdenum.alloyed.common.compat.createdeco;

import com.github.talrey.createdeco.api.Catwalks;
import com.github.talrey.createdeco.blocks.CatwalkBlock;
import com.github.talrey.createdeco.blocks.CatwalkRailingBlock;
import com.github.talrey.createdeco.blocks.CatwalkStairBlock;
import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.common.compat.createdeco.connected.SteelCatwalkCTBehaviour;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.BlockEntry;

import static com.github.talrey.createdeco.BlockRegistry.*;
import static com.molybdenum.alloyed.common.compat.create.CreateAlloyedBlocks.REGISTRATE;

public class CreateAlloyedDecoBlocks {
	private static final String metal = "steel";


	public static final BlockEntry<CatwalkBlock> STEEL_CATWALK = Catwalks.build(
					REGISTRATE, metal)
			.onRegister(CreateRegistrate.connectedTextures(SteelCatwalkCTBehaviour::new)).register();

	public static final BlockEntry<CatwalkStairBlock> STEEL_CATWALK_STAIRS = Catwalks.buildStair(
					REGISTRATE, metal)
			.register();

	public static final BlockEntry<CatwalkRailingBlock> STEEL_CATWALK_RAILING = Catwalks.buildRailing(
					REGISTRATE, metal)
			.register();

	public static void register() {
		CATWALKS.put(metal, STEEL_CATWALK);
		CATWALK_RAILINGS.put(metal, STEEL_CATWALK_RAILING);
		CATWALK_STAIRS.put(metal, STEEL_CATWALK_STAIRS);
		Alloyed.LOGGER.debug("Registering ModCompatBlocks!");
	}


}
