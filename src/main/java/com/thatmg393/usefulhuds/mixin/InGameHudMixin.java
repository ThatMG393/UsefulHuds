package com.thatmg393.usefulhuds.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.thatmg393.usefulhuds.config.ModConfigManager;
import com.thatmg393.usefulhuds.config.data.ModConfigData;
import com.thatmg393.usefulhuds.utils.DrawUtils;
import com.thatmg393.usefulhuds.utils.FPSHistory;

@Mixin(InGameHud.class)
public class InGameHudMixin {
	final MinecraftClient client = MinecraftClient.getInstance();
	final ModConfigData config = ModConfigManager.loadConfig();
	final FPSHistory fpsHistory = FPSHistory.getInstance();

	@Inject(at = @At("TAIL"), method = "render")
	public void render(DrawContext context, float tickDelta, CallbackInfo info) {
		if (!client.options.hudHidden && !client.getDebugHud().shouldShowDebugHud()) {
			if (config.FPS.showHud) {
				int fps = ((MinecraftClientAccessor) client).getCurrentFps();
				fpsHistory.addFPS(fps);

				String text = fps + " FPS";
				if (config.FPS.ADVANCED.showAdvanceInfo) {
					int min = fpsHistory.getMin(), avg = fpsHistory.getAvg(), max = fpsHistory.getMax();
					text += " ( " + min + " min | " + avg + " avg | " + max + " max )";
				}

				int[] textPos = DrawUtils.getProperOffsets(client, 20, 20, text);

				DrawUtils.renderText(
					context, client.textRenderer,
					text,
					textPos[0], textPos[1],
					1.0f, config.FPS.textColor,
					false
				);
			}

			if (config.STD.showHud) {
				boolean isSprintingHeld = client.player.isSprinting();
				boolean isSprintingToggle = client.options.getSprintToggled().getValue();

				String text = ""; // TODO: add ability to change the text in config
				if (isSprintingToggle) {
					if (isSprintingHeld) {
						text = "§7§oSprint Toggled";
					}
				} else {
					if (isSprintingHeld) {
						text = "§7§oSprint Held";
					}
				}

				int[] textPos = DrawUtils.getProperOffsets(client, 20, 30, text);
				DrawUtils.renderText(
					context, client.textRenderer,
					text,
					textPos[0], textPos[1],
					1.0f, config.STD.textColor,
					false
				);
			}
		}
	}
}
