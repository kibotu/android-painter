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
 * Time: 02:16
 */
public class ChromeBrush extends ShadedBrush {

    @Override
    public void addConnections ( Point nextPoint, int color, float strokeWidth ) {
        ArrayList<Point> points = PointsList.points;
        for ( int i = 0; i < points.size(); ++ i ) {
            Point curPoint = points.get( i );
            float b = curPoint.x - nextPoint.x;
            float a = curPoint.y - nextPoint.y;
            float distance = b * b + a * a;
            if ( distance < 3000f ) {
                int newColor = Color.argb( ( int ) Math.floor( Math.random() * Color.alpha( color ) ), ( int ) Math.floor( Math.random() * Color.red( color ) ), ( int ) Math.floor( Math.random() * Color.green( color ) ), ( int ) Math.floor( Math.random() * Color.blue( color ) ) );
                final float c = 0.2f;
                LineList.connections.add( new Line( new Point( nextPoint.x + ( b * c ), nextPoint.y + ( a * c ) ), new Point( curPoint.x - ( b * c ), curPoint.y - ( a * c ) ), newColor, strokeWidth * 0.7f * Config.BRUSH_PRESSURE ) );
            }
        }
    }
}
