package com.molybdenum.alloyed.common.compat.create;

import com.molybdenum.alloyed.client.ponder.AlloyedPonderPlugin;
import com.zurrtum.create.client.ponder.foundation.PonderIndex;

public class CreateCompat {
	public static void registerPonders() {
		PonderIndex.addPlugin(new AlloyedPonderPlugin());
	}
}
