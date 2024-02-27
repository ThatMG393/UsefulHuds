package com.thatmg393.usefulhuds.config.gui.clothconfig.categories;

import java.util.ArrayList;

import com.thatmg393.usefulhuds.config.gui.clothconfig.base.AbstractConfigCategory;

import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
import net.minecraft.text.Text;

@SuppressWarnings("rawtypes")
public class STDCategory extends AbstractConfigCategory {
    public STDCategory() {
        super(Text.of("SprintToggleDisplay"));
    }

    @Override
    public ArrayList<AbstractConfigListEntry> getEntries() {
        ArrayList<AbstractConfigListEntry> entries = new ArrayList<>();

        entries.add(
            entryBuilder.startBooleanToggle(
                Text.of("Show HUD"),
                config.STD.showHud
            ).setDefaultValue(defaultConfig.STD.showHud)
            .setSaveConsumer(v -> config.STD.showHud = v)
            .build()
        );

        entries.add(
            entryBuilder.startColorField(
                Text.of("Text Color"),
                config.STD.textColor
            ).setDefaultValue(config.STD.textColor)
            .setSaveConsumer(v -> config.STD.textColor = v)
            .build()
        );

        return entries;
    }
}
