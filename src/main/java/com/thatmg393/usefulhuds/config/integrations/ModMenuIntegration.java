package com.thatmg393.usefulhuds.config.integrations;

import com.thatmg393.usefulhuds.config.gui.ModClothConfigScreen;
import com.thatmg393.usefulhuds.config.gui.ModFallbackConfigScreen;

import net.fabricmc.loader.api.FabricLoader;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        if (FabricLoader.getInstance().isModLoaded("cloth-config")) {
            return parent -> ModClothConfigScreen.getConfigGui(parent);
        }

        return parent -> new ModFallbackConfigScreen(parent);
    }
}