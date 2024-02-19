package com.thatmg393.usefulhuds.utils;

import java.util.ArrayList;
import java.util.Collections;

import com.thatmg393.usefulhuds.config.ModConfigManager;
import com.thatmg393.usefulhuds.config.data.ModConfigData;

public class FPSHistory {
    private static final FPSHistory INSTANCE = new FPSHistory();
    public static FPSHistory getInstance() {
        return INSTANCE;
    }

    private final ModConfigData config = ModConfigManager.loadConfig();
    private final ArrayList<Integer> fpsList = new ArrayList<>();

    public void addFPS(int fps) {
        fpsList.add(fps);

        if (fpsList.size() > config.FPS.ADVANCED.histroyMax) {
            fpsList.remove(0);
        }
    }

    public int getMin() {
        return Collections.min(fpsList);
    }

    public int getMax() {
        return Collections.max(fpsList);
    }

    public int getAvg() {
        return (int) fpsList.parallelStream()
            .mapToInt(Integer::intValue)
            .average()
            .orElse(0);
    }
}
