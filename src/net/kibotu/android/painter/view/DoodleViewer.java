package net.kibotu.android.painter.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.*;
import android.view.MotionEvent;
import android.view.View;
import net.kibotu.android.painter.Config;
import net.kibotu.android.painter.DoodleDrawer;
import net.kibotu.android.painter.model.Point;
import net.kibotu.android.painter.utils.ColorPickerDialog;
import net.kibotu.android.painter.utils.Utils;
import net.kibotu.android.painter.utils.log.Logger;
import net.kibotu.android.painter.view.drawable.brushes.Brush;

import java.util.Calendar;

/**
 * User: Jan Rabe
 * Date: 17/11/12
 * Time: 15:44
 */
public class DoodleViewer extends View implements Runnable, View.OnTouchListener, ColorPickerDialog.OnColorChangedListener {

    private static SharedPreferences settings;
    protected Paint paint;
    protected DoodleDrawer doodleDrawer;
    protected LinePathManager linePathManager;
    private boolean isRunning;
    private int bgColor;
    private long startTime;
    private Bitmap bufferedImage;
    private boolean isDirty;

    public DoodleViewer ( DoodleDrawer context ) {
        super( context );
        doodleDrawer = context;
        isRunning = false;
        isDirty = true;
        doodleDrawer.getWindowManager().getDefaultDisplay().getMetrics( Config.DEVICE_DISPLAY_METRICS );
        Config.CLEAR_RECTANGLE_SIZE = ( int ) ( 100f * Config.DEVICE_DISPLAY_METRICS.density );
        Logger.i( this, "Display [" + Config.DEVICE_DISPLAY_METRICS.widthPixels + "|" + Config.DEVICE_DISPLAY_METRICS.heightPixels + "]" );
        init();
    }

    @Override
    public void onDraw ( Canvas canvas ) {
        super.onDraw( canvas );
        if ( isDirty ) clearScreen( canvas );
        linePathManager.draw( bufferedImage, paint );
        canvas.drawBitmap( bufferedImage, 0, 0, paint );
    }

    private void init () {

        // enable caching
        setDrawingCacheEnabled( true );

        // enable touch inputs
        setFocusable( true );
        setFocusableInTouchMode( true );
        this.setOnTouchListener( this );

        // care for saved settings
        initSharedPreferences();

        // default line settings
        linePathManager = new LinePathManager();

        // init paint
        initPaint();

        // init points db
//        PointsDB.init(Config.DEVICE_DISPLAY_METRICS);

        // create bitmap
        resetBufferedScreen();
    }

    private void initSharedPreferences () {
        settings = doodleDrawer.getSharedPreferences( "DoodleDrawer", Context.MODE_WORLD_WRITEABLE );
//        bgColor = settings.getInt("bg_defaultColor", Color.BLACK);
//        lineColor = settings.getInt("line_defaultColor", -1);
        bgColor = Color.BLACK;
    }

    private void initPaint () {
        paint = new Paint();
        qualitySettings();
//        speedSettings();
        paint.setColor( Color.WHITE ); // set default color to white
        paint.setStyle( Paint.Style.STROKE ); // set to STOKE
        paint.setStrokeJoin( Paint.Join.ROUND ); // set the join to round you want
        paint.setStrokeCap( Paint.Cap.ROUND );  // set the paint cap to round too
        paint.setPathEffect( new CornerPathEffect( getStrokeWidth() ) ); // set the path effect when they join.
    }

    private void speedSettings () {
        paint.setDither( false );
        paint.setAntiAlias( false );
    }

    private void qualitySettings () {
        paint.setAntiAlias( true ); // enable anti aliasing
        paint.setDither( true ); // enable dithering
    }

    private void clearScreen ( Canvas canvas ) {
        paint.setColor( bgColor );
        paint.setStyle( Paint.Style.FILL );
        Rect rect = new Rect( 0, 0, getWidth(), getHeight() );
        canvas.drawRect( rect, paint );
        paint.setStyle( Paint.Style.STROKE );
    }

    @Override
    public void run () {
        isRunning = true;
        while ( isRunning ) {
            doodleDrawer.updateHandler.sendEmptyMessage( 0 );
        }
    }

    public void start () {
        new Thread( this ).start();
    }

    public void stop () {
        isRunning = false;
        clear();
    }

    @Override
    public boolean onTouch ( View view, MotionEvent motionEvent ) {
        int action = motionEvent.getAction();

        Point point = new Point( motionEvent.getX(), motionEvent.getY() );
        switch ( action ) {
            case MotionEvent.ACTION_DOWN: // touch down
                linePathManager.startLine( point, Utils.getRandomColor() );
                break;
            case MotionEvent.ACTION_MOVE:  // touch drag
                linePathManager.addPoint( point );
                break;
            case MotionEvent.ACTION_UP:
                linePathManager.endLine();
                break;
        }

        // invalidate only finger area
//        invalidate();
        if ( Calendar.getInstance().getTimeInMillis() - startTime > Config.FPS ) {
            invalidate( ( int ) ( point.x - Config.CLEAR_RECTANGLE_SIZE / 1.5 ), ( int ) ( point.y - Config.CLEAR_RECTANGLE_SIZE / 1.5 ), ( int ) point.x + Config.CLEAR_RECTANGLE_SIZE, ( int ) point.y + Config.CLEAR_RECTANGLE_SIZE );
            startTime = Calendar.getInstance().getTimeInMillis();
        }

        //
        if ( Config.DEVICE_SDK_VERSION > 9 ) {
            point.setPressure( motionEvent.getPressure() );
        }
        return true;
    }

    public void clear () {
        linePathManager.clear();
        resetBufferedScreen();
        invalidate();
        settings.edit().commit();
    }

    public void resetBufferedScreen () {
        if ( bufferedImage != null ) bufferedImage.recycle();
        bufferedImage = Bitmap.createBitmap( Config.DEVICE_DISPLAY_METRICS.widthPixels, Config.DEVICE_DISPLAY_METRICS.heightPixels, Bitmap.Config.ARGB_8888 );
    }

    public Bitmap getCurrentScreen () {
        return getDrawingCache();
    }

    @Override
    public void colorChanged ( String key, int color ) {

        if ( "bg_currentColor".equals( key ) ) {
            bgColor = color;
            isDirty( true );
            invalidate();
        } else if ( "line_currentColor".equals( key ) ) {
            linePathManager.setCurrentLineColor( color );
        }// else if ("bg_defaultColor".equals(key)) {
//            settings.edit().putInt("bg_defaultColor", bgColor = color);
//        } else if ("line_defaultColor".equals(key)) {
//            settings.edit().putInt("line_defaultColor", lineColor = color);
//        }
    }

    private boolean isDirty () {
        return isDirty;
    }

    private void isDirty ( boolean isDirty ) {
        this.isDirty = isDirty;
    }

    public void setBrush ( Brush brush ) {
        linePathManager.setCurrentBrush( brush );
    }

    public int getStrokeWidth () {
        return linePathManager.getCurrentLineStrokeWidth();
    }

    public void setStrokeWidth ( int strokeWidth ) {
        linePathManager.setCurrentLineStrokeWidth( strokeWidth );
    }
}