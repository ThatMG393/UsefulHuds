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

@Mixin(InGameHud.class)
public class InGameHudMixin {
	@Inject(at = @At("TAIL"), method = "render")
	public void render(DrawContext context, float tickDelta, CallbackInfo info) {
		MinecraftClient client = MinecraftClient.getInstance();
		ModConfigData config = ModConfigManager.loadConfig();
		
		if (!client.options.hudHidden && !client.getDebugHud().shouldShowDebugHud()) {
			if (config.showFpsHud) {
				int fps = ((MinecraftClientAccessor) client).getCurrentFps();
				String text = fps + " FPS";

				int[] textPos = DrawUtils.getProperOffsets(client, 20, 20, text);

				DrawUtils.renderText(
					context, client.textRenderer,
					text,
					textPos[0], textPos[1],
					1.0f, 0xFFFFFF,
					false
				);
			}
		}
	}
}