package net.kibotu.android.painter.view.drawable.brushes;

import android.graphics.Color;
import net.kibotu.android.painter.Config;
import net.kibotu.android.painter.model.Line;
import net.kibotu.android.painter.model.LineList;
import net.kibotu.android.painter.model.Point;
import net.kibotu.android.painter.model.PointsList;

import java.util.ArrayList;

/**
 * User: Jan Rabe
 * Date: 11/01/08
 * Time: 02:18
 */
public class WebBrush extends ShadedBrush {

    @Override
    public void addConnections(Point nextPoint, int color, float strokeWidth) {
        ArrayList<Point> points = PointsList.points;
        for (int i = 0; i < points.size(); ++i) {
            Point curPoint = points.get(i);
            float b = curPoint.x - nextPoint.x;
            float a = curPoint.y - nextPoint.y;
            float distance = b * b + a * a;
            if (distance < 2500f * Config.DEVICE_DISPLAY_METRICS.density && Math.random() > 0.9f) {
                LineList.connections.add(new Line(curPoint,nextPoint,color, strokeWidth * 0.3f * Config.BRUSH_PRESSURE));
            }
        }
    }
}
