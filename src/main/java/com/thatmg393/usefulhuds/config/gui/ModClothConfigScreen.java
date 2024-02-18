package com.thatmg393.usefulhuds.config.gui;

import com.thatmg393.usefulhuds.UsefulHUDs;
import com.thatmg393.usefulhuds.config.ModConfigData;
import com.thatmg393.usefulhuds.config.ModConfigManager;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class ModClothConfigScreen {
    public static Screen getConfigGui(Screen parent) {
        ConfigBuilder configBuilder = ConfigBuilder.create();
        configBuilder.setParentScreen(parent);
        configBuilder.setTitle(Text.of("cOnFIG")); // FIXME: Use Text.translatable() instead
        configBuilder.setSavingRunnable(() -> {
            UsefulHUDs.LOGGER.info("Saving configs...");
            ModConfigManager.saveConfig();
        });

        ConfigEntryBuilder entryBuilder = ConfigEntryBuilder.create();

        ModConfigData defaults = new ModConfigData();
        ModConfigData loadedConfig = ModConfigManager.loadConfig();

        ConfigCategory generalCategory = configBuilder.getOrCreateCategory(Text.of("General"));
        generalCategory.addEntry(
            entryBuilder.startBooleanToggle(
                Text.of("Show FPS Display"),
                loadedConfig.showFpsHud
            ).setDefaultValue(defaults.showFpsHud)
            .setSaveConsumer(newValue -> loadedConfig.showFpsHud = newValue)
            .build()
        );

        return configBuilder.build();
    }
}
