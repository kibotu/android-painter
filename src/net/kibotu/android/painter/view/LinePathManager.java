package net.kibotu.android.painter.view;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import net.kibotu.android.painter.Config;
import net.kibotu.android.painter.model.LineList;
import net.kibotu.android.painter.model.Point;
import net.kibotu.android.painter.model.PointsList;
import net.kibotu.android.painter.utils.Utils;
import net.kibotu.android.painter.utils.log.Logger;
import net.kibotu.android.painter.view.drawable.LinePath;
import net.kibotu.android.painter.view.drawable.brushes.Brush;
import net.kibotu.android.painter.view.drawable.brushes.SimpleBrush;

import java.util.ArrayList;

/**
 * User: Jan Rabe
 * Date: 17/11/12
 * Time: 23:21
 */
public class LinePathManager {

    private ArrayList<LinePath> linePaths;
    private LinePath currentLinePath;
    private Brush currentBrush;
    private int currentLineColor;
    private int currentLineStrokeWidth;
    private boolean isDirty;
    private Point currentStartPoint;

    public LinePathManager() {
        linePaths = new ArrayList<LinePath>(10000);
        setCurrentLineColor(Utils.getRandomColor());
        setCurrentLineStrokeWidth(Config.DEFAULT_LINE_STROKE_WIDTH);
        setCurrentBrush(new SimpleBrush());
    }

    public void draw(Canvas canvas, Paint paint) {
        for (int i = 0; i < linePaths.size(); ++i) {
            linePaths.get(i).draw(canvas, paint);
        }
    }

    public void draw(Bitmap bufferedImage, Paint p) {
        for (int i = 0; i < linePaths.size(); ++i) {
            LinePath line = linePaths.get(i);
            if(!line.hasBeenDrawn()) {
                linePaths.get(i).draw(bufferedImage,p);
            }
        }
    }

    public void startLine(Point point) {
        // on null or dirty create new line
        if (currentLinePath == null || isDirty()) {
            currentLinePath = new LinePath(currentLineColor, currentLineStrokeWidth, currentBrush);
            linePaths.add(currentLinePath);
            isDirty(false);
        }
        currentLinePath.startLine(point);
        // save it only if line more than just one point
        currentStartPoint = point;
    }

    public void endLine() {
        // TODO rework paths to splines
        currentLinePath.close();
        isDirty(true);
    }

    public void addPoint(Point point) {
        // save starting point once
        if (currentStartPoint != null) {
//            PointsDB.addPoint(currentStartPoint);
            PointsList.points.add(currentStartPoint);
            currentStartPoint = null;
        }
        // save point to db
//        PointsDB.addPoint(point);
        PointsList.points.add(point);
        // add to path wrapper
        currentLinePath.add(point);
    }

    public void setCurrentLineColor(int currentLineColor) {
        this.currentLineColor = currentLineColor;
        isDirty(true);
    }

    public void setCurrentBrush(Brush currentBrush) {
        this.currentBrush = currentBrush;
        isDirty(true);
    }

    private void isDirty(boolean isDirty) {
        this.isDirty = isDirty;
    }

    private boolean isDirty() {
        return isDirty;
    }

    public int getCurrentLineStrokeWidth() {
        return currentLineStrokeWidth;
    }

    public void setCurrentLineStrokeWidth(int currentLineStrokeWidth) {
        this.currentLineStrokeWidth = currentLineStrokeWidth;
        isDirty(true);
    }

    public void clear() {
        for (LinePath l : linePaths) {
            l.clear();
        }
        linePaths.clear();
//        PointsDB.clear();
        PointsList.points.clear();
        LineList.connections.clear();
    }
}
