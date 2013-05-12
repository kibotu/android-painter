package net.kibotu.android.painter.view.drawable.brushes;

import net.kibotu.android.painter.Config;
import net.kibotu.android.painter.model.Line;
import net.kibotu.android.painter.model.LineList;
import net.kibotu.android.painter.model.Point;
import net.kibotu.android.painter.model.PointsList;

import java.util.ArrayList;

/**
 * User: Jan Rabe
 * Date: 11/01/08
 * Time: 02:59
 */
public class FurBrush extends ShadedBrush {

    @Override
    public void addConnections ( Point nextPoint, int color, float strokeWidth ) {
        ArrayList<Point> points = PointsList.points;
        for ( int i = 0; i < points.size(); ++ i ) {
            Point curPoint = points.get( i );
            float b = curPoint.x - nextPoint.x;
            float a = curPoint.y - nextPoint.y;
            float distance = b * b + a * a;
            if ( distance < 4000f * Config.DEVICE_DISPLAY_METRICS.density && Math.random() > distance / 4000f * Config.DEVICE_DISPLAY_METRICS.density ) {
                final float c = 0.5f;
                LineList.connections.add( new Line( new Point( nextPoint.x + ( b * c ), nextPoint.y + ( a * c ) ), new Point( nextPoint.x - ( b * c ), nextPoint.y - ( a * c ) ), color, strokeWidth * Config.BRUSH_PRESSURE * 0.5f ) );
            }
        }
    }
}
