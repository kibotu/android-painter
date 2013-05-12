package net.kibotu.android.painter.view.drawable.brushes;

import android.graphics.*;
import android.util.FloatMath;
import net.kibotu.android.painter.model.Point;
import net.kibotu.android.painter.model.PointsList;

import java.util.ArrayList;

public class CircleBrush extends Brush {

    @Override
    public void draw ( Path path, Canvas canvas, Paint paint ) {
        ArrayList<Point> points = PointsList.points;
        Point nextPoint = points.get( 1 );
        for ( int i = 1; i < points.size(); ++ i ) {
            Point curPoint = points.get( i - 1 );
            float b = curPoint.x - nextPoint.x;
            float a = curPoint.y - nextPoint.y;
            float h = FloatMath.sqrt( a * a + b * b ) * 2;
            float f = FloatMath.floor( nextPoint.x / 100 ) * 100 + 50;
            float c = FloatMath.floor( nextPoint.y / 100 ) * 100 + 50;
            float j = FloatMath.floor( ( float ) ( Math.random() * 10 ) );
            a = h / j;
            for ( int distance = 0; distance < j; ++ distance ) {
                RectF shape = new RectF( f, c, ( j - distance ) * a, ( j - distance ) * a );
                canvas.drawArc( shape, 0f, ( float ) ( Math.PI * 2f ), true, paint );
            }
            nextPoint = points.get( i );
        }
    }

    @Override
    public void draw ( Path path, Bitmap bitmap, Paint paint ) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void add ( Path path, Point p ) {
    }

    @Override
    public void addConnections ( Point nextPoint, int color, float strokeWidth ) {
    }
}
