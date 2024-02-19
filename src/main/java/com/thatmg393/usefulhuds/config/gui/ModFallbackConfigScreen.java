package com.thatmg393.usefulhuds.config.gui;

import com.thatmg393.usefulhuds.config.ModConfigManager;
import com.thatmg393.usefulhuds.config.data.ModConfigData;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;

public class ModFallbackConfigScreen extends Screen {
    protected final Screen parent;
    protected final ModConfigData config;

    public ModFallbackConfigScreen(Screen parent) {
        super(Text.of("UsefulHUDs config"));
        this.parent = parent;
        this.config = ModConfigManager.loadConfig();
    }

    @Override
    protected void init() {
        ButtonWidget doneButton = ButtonWidget.builder(ScreenTexts.DONE, (button) -> {
            this.close();
        }).width(200).position(this.width / 2 - 100, this.height - 27).build();
        
        this.addDrawableChild(doneButton);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta);
        context.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 2, 20, 0xFFFFFF);
        context.drawCenteredTextWithShadow(this.textRenderer, Text.of("Please install \'Cloth Config\' to access the configs"), this.width / 2, this.height / 2, 0xFFFFFF);
        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public void close() {
        this.client.setScreen(this.parent);
    }
}
