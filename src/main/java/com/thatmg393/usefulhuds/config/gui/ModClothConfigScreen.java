package com.thatmg393.usefulhuds.config.gui;

import com.thatmg393.usefulhuds.UsefulHUDs;
import com.thatmg393.usefulhuds.config.ModConfigManager;
import com.thatmg393.usefulhuds.config.data.ModConfigData;

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
                loadedConfig.FPS.showHud
            ).setDefaultValue(defaults.FPS.showHud)
            .setSaveConsumer(newValue -> loadedConfig.FPS.showHud = newValue)
            .build()
        );

        fpsCategory.addEntry(
            entryBuilder.startColorField(
                Text.of("Text Color"),
                loadedConfig.FPS.textColor
            ).setDefaultValue(defaults.FPS.textColor)
            .setSaveConsumer(newValue -> loadedConfig.FPS.textColor = newValue)
            .build()
        );

        SubCategoryBuilder fpsAdvancedCategory = entryBuilder.startSubCategory(Text.of("Advanced"));
        fpsAdvancedCategory.add(
            entryBuilder.startBooleanToggle(
                Text.of("Show MinAvgMax"),
                loadedConfig.FPS.ADVANCED.showAdvanceInfo
            ).setDefaultValue(defaults.FPS.ADVANCED.showAdvanceInfo)
            .setSaveConsumer(newValue -> loadedConfig.FPS.ADVANCED.showAdvanceInfo = newValue)
            .build()
        );

        fpsCategory.addEntry(fpsAdvancedCategory.build());

        ConfigCategory stCategory = configBuilder.getOrCreateCategory(Text.of("SprintToggled"));
        stCategory.addEntry(
            entryBuilder.startBooleanToggle(
                Text.of("Show HUD"),
                loadedConfig.STD.showHud
            ).setDefaultValue(defaults.STD.showHud)
            .setSaveConsumer(newValue -> loadedConfig.STD.showHud = newValue)
            .build()
        );

        stCategory.addEntry(
            entryBuilder.startColorField(
                Text.of("Text Color"),
                loadedConfig.STD.textColor
            ).setDefaultValue(defaults.STD.textColor)
            .setSaveConsumer(newValue -> loadedConfig.STD.textColor = newValue)
            .build()
        );

        return configBuilder.build();
    }
}
