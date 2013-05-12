package net.kibotu.android.painter.view.drawable;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import net.kibotu.android.painter.model.Point;
import net.kibotu.android.painter.view.drawable.brushes.Brush;
import org.jetbrains.annotations.NotNull;

/**
 * User: Jan Rabe
 * Date: 17/11/12
 * Time: 21:45
 */
public class LinePath {

    private Path path;
    private int color;
    private float strokeWidth;
    private Brush brush;
    private boolean hasBeenDrawn;

    public LinePath ( int color, float strokeWidth, @NotNull Brush brush ) {
        this.color = color;
        this.strokeWidth = strokeWidth;
        this.brush = brush;
        this.path = new Path();
        hasBeenDrawn = false;
    }

    public void draw ( @NotNull Canvas canvas, @NotNull Paint paint ) {
        paint.setColor( color );
        paint.setStrokeWidth( strokeWidth );
        brush.draw( path, canvas, paint );
        hasBeenDrawn( true );
    }

    public void draw ( Bitmap bufferedImage, Paint paint ) {
        paint.setColor( color );
        paint.setStrokeWidth( strokeWidth );
        brush.draw( path, bufferedImage, paint );
    }

    public void startLine ( @NotNull Point point ) {
        path.moveTo( point.x, point.y );
    }

    public void add ( @NotNull Point p ) {
        brush.add( path, p );
        brush.addConnections( p, color, strokeWidth );
    }

    public void add ( @NotNull Point... points ) {
        for ( int i = 0; i < points.length; ++ i ) {
            add( points[i] );
        }
    }

    public void close () {
        path.close();
    }

    public void clear () {
        path.reset();
    }

    public boolean hasBeenDrawn () {
        return hasBeenDrawn;
    }

    public void hasBeenDrawn ( boolean hasBeenDrawn ) {
        this.hasBeenDrawn = hasBeenDrawn;
    }
}
