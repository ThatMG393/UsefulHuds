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
	final ModConfigData config = ModConfigManager.loadOrGetConfig();
	final FPSHistory fpsHistory = FPSHistory.getInstance();

	@Inject(at = @At("TAIL"), method = "render")
	public void render(DrawContext context, float tickDelta, CallbackInfo info) {
		if (!client.options.hudHidden && !client.getDebugHud().shouldShowDebugHud()) {
			if (config.FPS.visible) {
				int fps = ((MinecraftClientAccessor) client).getCurrentFps();
				fpsHistory.addFPS(fps);

				String text = fps + " FPS";
				if (config.FPS.ADVANCED.showAdvanceInfo) {
					int min = fpsHistory.getMin(), avg = fpsHistory.getAvg(), max = fpsHistory.getMax();
					text += " ( " + min + " min | " + avg + " avg | " + max + " max )";
				}

				int[] textPos = DrawUtils.getProperOffsets(client, config.FPS.offsetX, config.FPS.offsetY, text);

				DrawUtils.drawText(
					context, client.textRenderer,
					text,
					textPos[0], textPos[1],
					config.FPS.scale, config.FPS.textColor,
					false
				);
			}

			if (config.STD.visible) {
				boolean isSprintingHeld = client.player.isSprinting();
				boolean isSprintingToggle = client.options.getSprintToggled().getValue();

				String text = ""; // TODO: add ability to change the text in config
				if (isSprintingToggle) {
					text = "§7§oSprint Toggled";
				} else {
					if (isSprintingHeld) {
						text = "§7§oSprint Held";
					}
				}

				int[] textPos = DrawUtils.getProperOffsets(client, config.STD.offsetX, config.STD.offsetY, text);
				DrawUtils.drawText(
					context, client.textRenderer,
					text,
					textPos[0], textPos[1],
					config.STD.scale, config.STD.textColor,
					false
				);
			}

			if (config.COORDS.visible) {
				String text = "X: " + client.player.getX() 
				            + "Y: " + client.player.getY()
							+ "Z: " + client.player.getZ();

				int boxPosX = config.COORDS.offsetX - config.COORDS.scale + client.textRenderer.getWidth(text) + (config.COORDS.paddingX / 2);
				int boxPosY = config.COORDS.offsetY - config.COORDS.scale;

				int textPosX = boxPosX + config.COORDS.paddingX; // - client.textRenderer.getWidth(text);
				int textPosY = boxPosY + config.COORDS.paddingY; // - client.textRenderer.fontHeight;
				
				DrawUtils.drawBox(
					context,
					boxPosX, boxPosY,
					config.COORDS.scale, config.COORDS.scale,
					0xFFFFFF
				);

				DrawUtils.drawText(
					context,
					client.textRenderer,
					text,
					textPosX, textPosY,
					1.0f, 0xFFFFFF, false
				);
			}
		}
	}
}
