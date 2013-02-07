package net.kibotu.android.painter;

import android.util.DisplayMetrics;
import net.kibotu.android.painter.utils.log.Logger;

public class Config {

    /**
     * DEBUG
     */
    public final static Logger.Level LOG_LEVEL = Logger.Level.NO_LOGGING;
    public static final DisplayMetrics DEVICE_DISPLAY_METRICS = new DisplayMetrics();
    public static int DEVICE_SDK_VERSION;
    public static final float LINE_DISTANCE_TRESHOLD = 10000f;
    public static float BRUSH_PRESSURE = 1f;
    public static int CLEAR_RECTANGLE_SIZE = 200;
    public static final int DEFAULT_LINE_STROKE_WIDTH = 2;
    public static final int SEEKBAR_MAX_SIZE = 50;
    public static long FPS = 1000/100;

    // satic class
    private Config() {
    }
}