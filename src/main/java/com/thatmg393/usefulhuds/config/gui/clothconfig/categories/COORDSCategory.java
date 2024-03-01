package com.thatmg393.usefulhuds.config.gui.clothconfig.categories;

import java.util.ArrayList;

import com.thatmg393.usefulhuds.config.gui.clothconfig.base.AbstractConfigCategory;
import com.thatmg393.usefulhuds.utils.DisplayUtils;

import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
import net.minecraft.text.Text;

@SuppressWarnings("rawtypes")
public class COORDSCategory extends AbstractConfigCategory {
    public COORDSCategory() {
        super(Text.translatable("usefulhuds.config.COORDS.title"));
    }

    @Override
    public ArrayList<AbstractConfigListEntry> getEntries() {
        ArrayList<AbstractConfigListEntry> entries = new ArrayList<>();

        entries.add(
            entryBuilder.startBooleanToggle(
                Text.translatable("usefulhuds.config.hudvisible"),
                config.COORDS.visible
            ).setDefaultValue(defaultConfig.COORDS.visible)
            .setSaveConsumer(v -> config.COORDS.visible = v)
            .build()
        );

        entries.add(
            entryBuilder.startIntSlider(
                Text.translatable("usefulhuds.config.hudoffsetx"),
                config.COORDS.offsetX,
                0, DisplayUtils.getScreenScaledWH()[0]
            ).setDefaultValue(defaultConfig.COORDS.offsetX)
            .setSaveConsumer(v -> config.COORDS.offsetX = v)
            .build()
        );

        entries.add(
            entryBuilder.startIntSlider(
                Text.translatable("usefulhuds.config.hudoffsety"),
                config.COORDS.offsetY,
                0, DisplayUtils.getScreenScaledWH()[1]
            ).setDefaultValue(defaultConfig.COORDS.offsetX)
            .setSaveConsumer(v -> config.COORDS.offsetY = v)
            .build()
        );

        entries.add(
            entryBuilder.startIntSlider(
                Text.translatable("usefulhuds.config.hudscale"),
                config.COORDS.scale,
                1, 100
            ).setDefaultValue(defaultConfig.COORDS.scale)
            .setSaveConsumer(v -> config.COORDS.scale = v)
            .build()
        );

        entries.add(
            entryBuilder.startIntSlider(
                Text.translatable("usefulhuds.config.hudpaddingx"),
                config.COORDS.paddingX,
                0, 64
            ).setDefaultValue(defaultConfig.COORDS.paddingX)
            .setSaveConsumer(v -> config.COORDS.paddingX = v)
            .build()
        );

        entries.add(
            entryBuilder.startIntSlider(
                Text.translatable("usefulhuds.config.hudpaddingy"),
                config.COORDS.paddingY,
                0, 64
            ).setDefaultValue(defaultConfig.COORDS.paddingY)
            .setSaveConsumer(v -> config.COORDS.paddingY = v)
            .build()
        );

        return entries;
    }
}
