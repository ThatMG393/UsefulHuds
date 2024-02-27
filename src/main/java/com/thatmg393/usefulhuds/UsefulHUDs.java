package com.thatmg393.usefulhuds;

import org.lwjgl.glfw.GLFW;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thatmg393.usefulhuds.config.ModConfigManager;
import com.thatmg393.usefulhuds.config.data.ModConfigData;

@Environment(EnvType.CLIENT)
public class UsefulHUDs implements ModInitializer {
	public static final String MOD_ID = "usefulhuds";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("UsefulHUDs loading...");

		initalizeKeybinds();

		/*
		ClientTickEvents.START_CLIENT_TICK.register(client -> {
			
		});
		*/
		
		LOGGER.info("UsefulHUDs loaded. Enjoy!");
	}

	private void initalizeKeybinds() {
		KeyBinding showFpsKeyBind = KeyBindingHelper.registerKeyBinding(
			new KeyBinding(
				Text.translatable("usefulhuds.keybinds.togglefpshud").getString(),
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_DONT_CARE,
				Text.translatable("usefulhuds.name").getString()
			)
		);

		ModConfigData loadedConfig = ModConfigManager.loadOrGetConfig();

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			int maxFps = client.options.getMaxFps().getValue();
			if (maxFps != loadedConfig.FPS.ADVANCED.histroyMax) {
				loadedConfig.FPS.ADVANCED.histroyMax = maxFps;
			}

			if (showFpsKeyBind.wasPressed()) {
				loadedConfig.FPS.visible = !loadedConfig.FPS.visible;
			}
		});
	}
}