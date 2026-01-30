package com.molybdenum.alloyed;

import com.molybdenum.alloyed.client.ponder.AlloyedPonderPlugin;
import com.molybdenum.alloyed.client.registry.ModItemProperties;
import com.molybdenum.alloyed.client.registry.ModPartialModels;
import com.molybdenum.alloyed.client.registry.ModPonders;
import net.createmod.ponder.foundation.PonderIndex;

public class AlloyedClient {
    public static void onClientInit() {
        ModPartialModels.register();
    }

    public static void clientSetup() {
        // Set up steel fishing rod
        ModItemProperties.register();
        // Register ponders
        PonderIndex.addPlugin(new AlloyedPonderPlugin());
    }
}
