package net.kibotu.android.painter.view.drawable.brushes;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import net.kibotu.android.painter.model.Point;

public abstract class Brush {

    protected Canvas canvas;

    protected Brush ( Canvas canvas ) {
        this.canvas = canvas;
    }

    public Brush () {
        this( new Canvas() );
    }

    public abstract void draw ( Path path, Canvas canvas, Paint paint );

    public void draw ( Path path, Bitmap bitmap, Paint paint ) {
        canvas.setBitmap( bitmap );
        draw( path, canvas, paint );
    }

    public abstract void add ( Path path, Point p );

    public abstract void addConnections ( Point nextPoint, int color, float strokeWidth );
}