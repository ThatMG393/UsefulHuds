package com.thatmg393.usefulhuds.config.gui;

import com.thatmg393.usefulhuds.UsefulHUDs;
import com.thatmg393.usefulhuds.config.ModConfigData;
import com.thatmg393.usefulhuds.config.ModConfigManager;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.impl.builders.SubCategoryBuilder;
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

        ConfigCategory fpsCategory = configBuilder.getOrCreateCategory(Text.of("FPS"));
        fpsCategory.addEntry(
            entryBuilder.startBooleanToggle(
                Text.of("Show HUD"),
                loadedConfig.fps_showHud
            ).setDefaultValue(defaults.fps_showHud)
            .setSaveConsumer(newValue -> loadedConfig.fps_showHud = newValue)
            .build()
        );

        fpsCategory.addEntry(
            entryBuilder.startColorField(
                Text.of("Text Color"),
                loadedConfig.fps_textColor
            ).setDefaultValue(defaults.fps_textColor)
            .setSaveConsumer(newValue -> loadedConfig.fps_textColor = newValue)
            .build()
        );

        SubCategoryBuilder fpsAdvancedCategory = entryBuilder.startSubCategory(Text.of("Advanced"));
        fpsAdvancedCategory.add(
            entryBuilder.startBooleanToggle(
                Text.of("Show MinAvgMax"),
                loadedConfig.fps_showAdvanceInfo
            ).setDefaultValue(defaults.fps_showAdvanceInfo)
            .setSaveConsumer(newValue -> loadedConfig.fps_showAdvanceInfo = newValue)
            .build()
        );

        fpsCategory.addEntry(fpsAdvancedCategory.build());

        ConfigCategory stCategory = configBuilder.getOrCreateCategory(Text.of("SprintToggled"));
        stCategory.addEntry(
            entryBuilder.startBooleanToggle(
                Text.of("Show HUD"),
                loadedConfig.std_showHud
            ).setDefaultValue(defaults.std_showHud)
            .setSaveConsumer(newValue -> loadedConfig.std_showHud = newValue)
            .build()
        );

        stCategory.addEntry(
            entryBuilder.startColorField(
                Text.of("Text Color"),
                loadedConfig.std_textColor
            ).setDefaultValue(defaults.std_textColor)
            .setSaveConsumer(newValue -> loadedConfig.std_textColor = newValue)
            .build()
        );

        return configBuilder.build();
    }
}
