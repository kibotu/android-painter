package net.kibotu.android.painter.view.drawable.brushes;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import net.kibotu.android.painter.model.Point;

public class SimpleBrush extends Brush {

    @Override
    public void draw(Path path, Canvas canvas, Paint paint) {
        canvas.drawPath(path, paint);
    }

    @Override
    public void add(Path path, Point p) {
        path.lineTo(p.x, p.y);
        path.moveTo(p.x, p.y);
    }

    @Override
    public void addConnections(Point nextPoint, int color, float strokeWidth) {
    }
}
