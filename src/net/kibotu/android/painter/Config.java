package net.kibotu.android.painter;

import android.util.DisplayMetrics;
import net.kibotu.android.painter.utils.log.Logger;
import net.kibotu.android.painter.view.drawable.brushes.Brush;
import net.kibotu.android.painter.view.drawable.brushes.ShadedBrush;

public class Config {

    /**
     * DEBUG
     */
    public static final Logger.Level LOG_LEVEL = Logger.Level.NO_LOGGING;
    public static final DisplayMetrics DEVICE_DISPLAY_METRICS = new DisplayMetrics();
    public static final float LINE_DISTANCE_TRESHOLD = 10000f;
    public static final int DEFAULT_LINE_STROKE_WIDTH = 1;
    public static final int SEEKBAR_MAX_SIZE = 50;
    public static int DEVICE_SDK_VERSION;
    public static float BRUSH_PRESSURE = 1f;
    public static int CLEAR_RECTANGLE_SIZE = 200;
    public static long FPS = 1000 / 100;
    public static final Brush DEFAULT_BRUSH = new ShadedBrush();

    /**
     * Flurry
     */

    public static final String FLURRY_API_KEY = "HCDZ4G4YS79TT6RZ5NJT";

    // satic class
    private Config () {
    }
}