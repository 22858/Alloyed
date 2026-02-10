package com.molybdenum.alloyed;

import com.molybdenum.alloyed.client.ponder.AlloyedPonderPlugin;
import com.molybdenum.alloyed.client.registry.ModPartialModels;
import com.zurrtum.create.client.ponder.foundation.PonderIndex;

public class AlloyedClient {
    public static void onClientInit() {
        ModPartialModels.register();
    }

    public static void clientSetup() {
        // Register ponders
        PonderIndex.addPlugin(new AlloyedPonderPlugin());
    }
}
