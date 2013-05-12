package net.kibotu.android.painter.model;

/**
 * User: Jan Rabe
 * Date: 10/01/08
 * Time: 23:58
 */
public class Line {

    private Point a;
    private Point b;
    private int color;
    private float strokeWidth;
    private boolean hasBeenDrawn;

    public Line ( Point a, Point b, int color, float strokeWidth ) {
        this.a = a;
        this.b = b;
        this.color = color;
        this.strokeWidth = strokeWidth;
        hasBeenDrawn = false;
    }

    public int getColor () {
        return color;
    }

    public void setColor ( int color ) {
        this.color = color;
    }

    public float getStrokeWidth () {
        return strokeWidth;
    }

    public void setStrokeWidth ( float strokeWidth ) {
        this.strokeWidth = strokeWidth;
    }

    public Point getA () {
        return a;
    }

    public void setA ( Point a ) {
        this.a = a;
    }

    public Point getB () {
        return b;
    }

    public void setB ( Point b ) {
        this.b = b;
    }

    @Override
    public boolean equals ( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;

        Line line = ( Line ) o;

        if ( color != line.color ) return false;
        if ( Float.compare( line.strokeWidth, strokeWidth ) != 0 ) return false;
        if ( ! a.equals( line.a ) ) return false;
        if ( ! b.equals( line.b ) ) return false;

        return true;
    }

    @Override
    public int hashCode () {
        int result = color;
        result = 31 * result + ( strokeWidth != + 0.0f ? Float.floatToIntBits( strokeWidth ) : 0 );
        result = 31 * result + a.hashCode();
        result = 31 * result + b.hashCode();
        return result;
    }

    @Override
    public String toString () {
        return "Line{" +
                "color=" + color +
                ", strokeWidth=" + strokeWidth +
                ", a=" + a +
                ", b=" + b +
                '}';
    }

    public boolean hasBeenDrawn () {
        return hasBeenDrawn;
    }

    public void hasBeenDrawn ( boolean hasBeenDrawn ) {
        this.hasBeenDrawn = hasBeenDrawn;
    }
}
