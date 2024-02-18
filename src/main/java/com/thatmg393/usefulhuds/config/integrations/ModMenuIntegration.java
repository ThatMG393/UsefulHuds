package com.thatmg393.usefulhuds.config.integrations;

import com.thatmg393.usefulhuds.config.gui.ModClothGui;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.fabricmc.loader.api.FabricLoader;

public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> ModClothGui.getConfigGui(parent);
    }
}