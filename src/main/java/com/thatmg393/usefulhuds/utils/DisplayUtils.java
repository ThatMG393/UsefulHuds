package com.thatmg393.usefulhuds.utils;

import net.minecraft.client.MinecraftClient;

public class DisplayUtils {
    public static int[] getScreenScaledWH() {
        MinecraftClient mc = MinecraftClient.getInstance();

        return new int[] {
            mc.getWindow().getScaledWidth(),
            mc.getWindow().getScaledHeight()
        };
    }
}
