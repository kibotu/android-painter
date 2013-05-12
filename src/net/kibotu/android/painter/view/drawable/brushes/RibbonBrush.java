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
public class RibbonBrush extends ShadedBrush {

    @Override
    public void addConnections ( Point nextPoint, int color, float strokeWidth ) {
        ArrayList<Point> points = PointsList.points;
        for ( int i = 0; i < points.size(); ++ i ) {
            Point curPoint = points.get( i );
            float b = curPoint.x - nextPoint.x;
            float a = curPoint.y - nextPoint.y;
            float distance = b * b + a * a;
            if ( distance < 10000 * Config.DEVICE_DISPLAY_METRICS.density && Math.random() > ( distance / 10000 * Config.DEVICE_DISPLAY_METRICS.density ) ) {
                int alpha = 255 - ( ( int ) ( ( 225f / 2000f * Config.DEVICE_DISPLAY_METRICS.density ) * distance ) );
                int newColor = Color.argb( alpha, Color.red( color ), Color.green( color ), Color.blue( color ) );
                final float c = 0.2f;
                LineList.connections.add( new Line( new Point( nextPoint.x + ( b * c ), nextPoint.y + ( a * c ) ), new Point( curPoint.x - ( b * c ), curPoint.y - ( a * c ) ), newColor, strokeWidth * 0.5f * Config.BRUSH_PRESSURE ) );
            }
        }
    }
}
