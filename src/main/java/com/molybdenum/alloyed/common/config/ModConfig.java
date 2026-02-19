package com.molybdenum.alloyed.common.config;

import folk.sisby.kaleido.api.WrappedConfig;
import folk.sisby.kaleido.lib.quiltconfig.api.annotations.Comment;

public class ModConfig extends WrappedConfig {
	@Comment("Enable a built-in datapack to integrate Forges into vanilla progression.")
	public boolean integratedForges = false;

}