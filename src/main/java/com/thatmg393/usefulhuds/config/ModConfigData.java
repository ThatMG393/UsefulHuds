package com.thatmg393.usefulhuds.config;

public class ModConfigData {
    public boolean showFpsHud = true;
    public boolean showCoordinatesHud = true;

    public KeyMode showFpsKeyMode = KeyMode.Toggle;
    public KeyMode showCoordinatesKeyMode = KeyMode.Toggle;

    public enum KeyMode {
        Toggle,
        PushToShow
    }
}
