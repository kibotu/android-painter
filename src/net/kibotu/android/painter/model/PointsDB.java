package net.kibotu.android.painter.model;

import android.util.DisplayMetrics;
import android.util.FloatMath;
import net.kibotu.android.painter.Config;
import net.kibotu.android.painter.utils.log.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * User: Jan Rabe
 * Date: 10/01/08
 * Time: 02:05
 */
public enum PointsDB {

    // singleton
    INSTANCE;

    private static final String TAG = PointsDB.class.getSimpleName();

    // database
    private static ArrayList[][] points;
    private static int rows;
    private static int cols;

    private PointsDB() {
    }

    public static void init(int rows, int cols) {
        PointsDB.rows = rows++;
        PointsDB.cols = cols++;
        Logger.v(TAG, "PointsDB [Density: rows=" + rows + ", cols=" + cols + "]");
        clear();
        points = new ArrayList[rows][cols];
        for(int y = 0; y < cols; ++y) {
            for(int x = 0; x < rows; ++x) {
                points[x][y] = new ArrayList<Point>(100);
            }
        }
    }

    public static ArrayList<Point> getPoints(int row, int col) {
        checkInit();
        assert rows > row && row >= 0 && cols > col && col >= 0;
        return points[row][col];
    }

    public static void addPoint(Point p, int row, int col) {
        checkInit();
        assert rows > row && row >= 0 && cols > col && col >= 0;
        points[row][col].add(p);
    }

    public static void addPoint(Point point) {
        addPoint(point, computeRow(point.x), computeCol(point.y));
    }

    public static void clear() {
        if(points == null) return;
        for(int y = 0; y < points[0].length; ++y) {
            for(int x = 0; x < points.length; ++x) {
                points[x][y].clear();
            }
        }
    }

    private static void checkInit() {
        if(points == null) throw new IllegalStateException("not initialized yet - PointsDB.INSTANCE.init(rows,cols)");
    }

    public static ArrayList<Point> getPoints(@NotNull Point point) {
        checkInit();
        return getPoints(computeRow(point.x), computeCol(point.y));
    }

    private static int computeRow(float width) {
        return (int) (FloatMath.ceil(width / FloatMath.sqrt(Config.LINE_DISTANCE_TRESHOLD / 2f)));
    }

    private static int computeCol(float height) {
        return (int) (FloatMath.ceil(height / FloatMath.sqrt(Config.LINE_DISTANCE_TRESHOLD / 2f)));
    }

    public static void init(DisplayMetrics metrics) {
        init(computeRow(metrics.widthPixels),computeCol(metrics.heightPixels));
    }
}
