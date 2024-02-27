package com.thatmg393.usefulhuds.config.gui.clothconfig.categories;

import java.util.ArrayList;

import com.thatmg393.usefulhuds.config.gui.clothconfig.base.AbstractConfigCategory;
import com.thatmg393.usefulhuds.config.gui.clothconfig.base.AbstractConfigSubCategory;

import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
import net.minecraft.text.Text;

@SuppressWarnings("rawtypes")
public class FPSCategory extends AbstractConfigCategory {
    public class SubCategory extends AbstractConfigSubCategory {
        public SubCategory() {
            super(Text.of("Advanced"), FPSCategory.this);
        }

        @Override
        public ArrayList<AbstractConfigListEntry> getEntries() {
            ArrayList<AbstractConfigListEntry> entries = new ArrayList<>();

            entries.add(
                entryBuilder.startBooleanToggle(
                    Text.of("Show MinAvgMax"),
                    config.FPS.ADVANCED.showAdvanceInfo
                ).setDefaultValue(defaultConfig.FPS.ADVANCED.showAdvanceInfo)
                .setSaveConsumer(v -> config.FPS.ADVANCED.showAdvanceInfo = v)
                .build()
            );

            return entries;
        }
    }

    private SubCategory ADVANCED = new SubCategory();

    public FPSCategory() {
        super(Text.of("FPS"));
    }

    @Override
    public ArrayList<AbstractConfigListEntry> getEntries() {
        ArrayList<AbstractConfigListEntry> entries = new ArrayList<>();

        entries.add(
            entryBuilder.startBooleanToggle(
                Text.of("Show HUD"),
                config.FPS.showHud
            ).setDefaultValue(defaultConfig.FPS.showHud)
            .setSaveConsumer(nv -> config.FPS.showHud = nv)
            .build()
        );

        entries.add(
            entryBuilder.startColorField(
                Text.of("HUD Text Color"),
                config.FPS.textColor
            ).setDefaultValue(defaultConfig.FPS.textColor)
            .setSaveConsumer(nv -> config.FPS.textColor = nv)
            .build()
        );

        entries.add(ADVANCED.build());
        return entries;
    }
}
