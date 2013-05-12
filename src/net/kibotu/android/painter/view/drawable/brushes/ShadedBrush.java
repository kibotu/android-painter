package net.kibotu.android.painter.view.drawable.brushes;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import net.kibotu.android.painter.Config;
import net.kibotu.android.painter.model.Line;
import net.kibotu.android.painter.model.LineList;
import net.kibotu.android.painter.model.Point;
import net.kibotu.android.painter.model.PointsList;

import java.util.ArrayList;

public class ShadedBrush extends SimpleBrush {

    @Override
    public void draw ( Path path, Canvas canvas, Paint paint ) {
        super.draw( path, canvas, paint );

        // draw connections
        for ( int i = 0; i < LineList.connections.size(); ++ i ) {
            Line line = LineList.connections.get( i );
            if ( line.hasBeenDrawn() ) continue;
            paint.setStrokeWidth( line.getStrokeWidth() );
            paint.setColor( line.getColor() );
            canvas.drawLine( line.getA().x, line.getA().y, line.getB().x, line.getB().y, paint );
            line.hasBeenDrawn( true );
        }
    }

    @Override
    public void addConnections ( Point nextPoint, int color, float strokeWidth ) {
        ArrayList<Point> points = PointsList.points;
        for ( int i = 0; i < points.size(); ++ i ) {
            Point curPoint = points.get( i );
            float b = curPoint.x - nextPoint.x;
            float a = curPoint.y - nextPoint.y;
            float distance = b * b + a * a;
            if ( distance < 2000f * Config.DEVICE_DISPLAY_METRICS.density ) {
                int alpha = 255 - ( ( int ) ( 225f / 2000f * Config.DEVICE_DISPLAY_METRICS.density * distance ) );
                int newColor = Color.argb( alpha, Color.red( color ), Color.green( color ), Color.blue( color ) );
                LineList.connections.add( new Line( nextPoint, curPoint, newColor, strokeWidth * Config.BRUSH_PRESSURE * 0.5f ) );
            }
        }
    }
}