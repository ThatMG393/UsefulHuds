package com.thatmg393.usefulhuds.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.thatmg393.usefulhuds.config.ModConfigData;
import com.thatmg393.usefulhuds.config.ModConfigManager;
import com.thatmg393.usefulhuds.utils.DrawUtils;
import com.thatmg393.usefulhuds.utils.FPSHistory;

@Mixin(InGameHud.class)
public class InGameHudMixin {
	@Inject(at = @At("TAIL"), method = "render")
	public void render(DrawContext context, float tickDelta, CallbackInfo info) {
		MinecraftClient client = MinecraftClient.getInstance();
		ModConfigData config = ModConfigManager.loadConfig();
		
		if (!client.options.hudHidden && !client.getDebugHud().shouldShowDebugHud()) {
			if (config.fps_showHud) {
				FPSHistory fpsHistory = FPSHistory.getInstance();

				int fps = ((MinecraftClientAccessor) client).getCurrentFps();
				fpsHistory.addFPS(fps);

				String text = fps + " FPS";
				if (config.fps_showAdvanceInfo) {
					int min = fpsHistory.getMin(), avg = fpsHistory.getAvg(), max = fpsHistory.getMax();
					text += " ( " + min + " min | " + avg + " avg | " + max + " max )";
				}

				int[] textPos = DrawUtils.getProperOffsets(client, 20, 20, text);

				DrawUtils.renderText(
					context, client.textRenderer,
					text,
					textPos[0], textPos[1],
					1.0f, config.fps_textColor,
					false
				);
			}

			if (config.std_showHud) {
				boolean isSprintingHeld = client.player.isSprinting();
				boolean isSprintingToggled = client.options.getSprintToggled().getValue();

				String text = "§7§oSprint Held"; // TODO: add ability to change the text in config
				if (isSprintingToggled && isSprintingHeld) {
					text = "§7§oSprint Toggled";
				}

				int[] textPos = DrawUtils.getProperOffsets(client, 20, 30, text);
				DrawUtils.renderText(
					context, client.textRenderer,
					text,
					textPos[0], textPos[1],
					1.0f, config.std_textColor,
					false
				);
			}
		}
	}
}