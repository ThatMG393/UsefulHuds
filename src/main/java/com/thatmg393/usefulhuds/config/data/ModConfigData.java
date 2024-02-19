package com.thatmg393.usefulhuds.config.data;

public class ModConfigData {
    public class FPSSection {
        public boolean showHud = true;
        public int textColor = 0xFFFFFF;

        public class AdvancedSection {
            public boolean showAdvanceInfo = false;
            public int histroyMax = 60;
        }

        public final AdvancedSection ADVANCED = new AdvancedSection();
    }

    // STD (Sprint toggle display)
    public class STDSection {
        public boolean showHud = false;
        public int textColor = 0xFFFFFF;
    }

    public class CoordsSection {
        public boolean showHud = true;
    }

    public final FPSSection FPS = new FPSSection();
    public final STDSection STD = new STDSection();
    public final CoordsSection COORDS = new CoordsSection();
}