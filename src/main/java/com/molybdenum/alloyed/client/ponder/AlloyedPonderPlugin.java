package com.molybdenum.alloyed.client.ponder;

import com.molybdenum.alloyed.client.registry.ModPonders;
import com.molybdenum.alloyed.common.registry.ModBlocks;
import com.zurrtum.create.client.infrastructure.ponder.AllCreatePonderTags;
import com.zurrtum.create.client.ponder.api.registration.PonderPlugin;
import com.zurrtum.create.client.ponder.api.registration.PonderSceneRegistrationHelper;
import com.zurrtum.create.client.ponder.api.registration.PonderTagRegistrationHelper;
import net.minecraft.resources.Identifier;

public class AlloyedPonderPlugin implements PonderPlugin {
    @Override
    public String getModId() {
        return "alloyed";
    }

    @Override
    public void registerScenes(PonderSceneRegistrationHelper<Identifier> helper) {
        ModPonders.register(helper);
    }

    @Override
    public void registerTags(PonderTagRegistrationHelper<Identifier> helper) {
        helper.addToTag(AllCreatePonderTags.DECORATION, ModBlocks.BRONZE_BELL.getId());
    }
}
