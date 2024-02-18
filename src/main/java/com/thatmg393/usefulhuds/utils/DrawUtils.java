package com.thatmg393.usefulhuds.utils;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.MatrixStack;

public class DrawUtils {
    public static void renderText(
		DrawContext context,
		TextRenderer renderer,
		String text,
		int posX, int posY,
		float scale, int color,
		boolean shadowed
	) {
		MatrixStack matrixStack = null;
		if (scale != 1.0f) {
			matrixStack = context.getMatrices();
			matrixStack.push();
			matrixStack.translate(posX, posY, 0);
			matrixStack.scale(scale, scale, scale);
			matrixStack.translate(-posX, -posY, 0);
		}
		
		context.drawText(renderer, text, posX, posY, color, shadowed);

		if (matrixStack != null) {
			matrixStack.pop();
		}
	}

	public static int[] getProperOffsets(MinecraftClient client, int posX, int posY, String text) {
		int maxTextPosX = client.getWindow().getScaledWidth() - client.textRenderer.getWidth(text);
		int maxTextPosY = client.getWindow().getScaledHeight() - client.textRenderer.fontHeight;

		return new int[] {
			Math.min(posX, maxTextPosX),
			Math.min(posY, maxTextPosY)
		};
	}
}