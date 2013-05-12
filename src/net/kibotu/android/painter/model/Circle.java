package net.kibotu.android.painter.model;

/**
 * User: Jan Rabe
 * Date: 11/01/08
 * Time: 01:56
 */
public class Circle {

    private Point center;
    private float radius;
    private int color;
    private float strokeWidth;

    public Circle ( Point center, float radius, int color, float strokeWidth ) {
        this.center = center;
        this.radius = radius;
        this.color = color;
        this.strokeWidth = strokeWidth;
    }

    public Point getCenter () {
        return center;
    }

    public void setCenter ( Point center ) {
        this.center = center;
    }

    public float getRadius () {
        return radius;
    }

    public void setRadius ( float radius ) {
        this.radius = radius;
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

    @Override
    public boolean equals ( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;

        Circle circle = ( Circle ) o;

        if ( color != circle.color ) return false;
        if ( Float.compare( circle.radius, radius ) != 0 ) return false;
        if ( Float.compare( circle.strokeWidth, strokeWidth ) != 0 ) return false;
        if ( ! center.equals( circle.center ) ) return false;

        return true;
    }

    @Override
    public int hashCode () {
        int result = center.hashCode();
        result = 31 * result + ( radius != + 0.0f ? Float.floatToIntBits( radius ) : 0 );
        result = 31 * result + color;
        result = 31 * result + ( strokeWidth != + 0.0f ? Float.floatToIntBits( strokeWidth ) : 0 );
        return result;
    }

    @Override
    public String toString () {
        return "Circle{" +
                "center=" + center +
                ", radius=" + radius +
                ", color=" + color +
                ", strokeWidth=" + strokeWidth +
                '}';
    }
}
