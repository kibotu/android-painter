package net.kibotu.android.painter.model;

import net.kibotu.android.painter.Config;

/**
 * User: Jan Rabe
 * Date: 17/11/12
 * Time: 18:30
 */
public class Point {

    public final float x;
    public final float y;
    private float pressure;

    public Point ( float x, float y ) {
        this.x = x;
        this.y = y;
        pressure = Config.BRUSH_PRESSURE;
    }

    @Override
    public boolean equals ( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;

        Point point = ( Point ) o;

        if ( Float.compare( point.x, x ) != 0 ) return false;
        if ( Float.compare( point.y, y ) != 0 ) return false;

        return true;
    }

    @Override
    public int hashCode () {
        int result = ( x != + 0.0f ? Float.floatToIntBits( x ) : 0 );
        result = 31 * result + ( y != + 0.0f ? Float.floatToIntBits( y ) : 0 );
        return result;
    }

    @Override
    public String toString () {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                ", pressure=" + pressure +
                '}';
    }

    public float getPressure () {
        return pressure;
    }

    public void setPressure ( float pressure ) {
        this.pressure = pressure;
    }
}
