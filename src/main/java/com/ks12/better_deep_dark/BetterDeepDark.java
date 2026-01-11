package com.ks12.better_deep_dark;

import com.ks12.better_deep_dark.registry.*;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BetterDeepDark implements ModInitializer {
    public static final String MOD_ID = "better_deep_dark";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);





    @Override
    public void onInitialize() {
        RegistryHandler.initCommon();
        RegistryHandler.completeAllRegistrations();

//        ModBlocks.load();
//
//        ModItems.registerAll();
//        //ModItems.completeRegistration();
//
//        ModParticles.registerAll();
//        ModSounds.registerAll();
//        ServerCommands.registerAll();
    }
}
