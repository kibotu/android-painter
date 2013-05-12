package net.kibotu.android.painter.utils.log;

import android.util.Log;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public enum AndroidLogger implements ILogger {

    INSTANCE;

    @Override
    public void toast ( @NotNull String o, String message ) {
    }

    @Override
    public void d ( @NotNull String id, @Nullable String message ) {
        Log.d( id, message );
    }

    @Override
    public void v ( @NotNull String id, @Nullable String message ) {
        Log.v( id, message );
    }

    @Override
    public void i ( @NotNull String id, @Nullable String message ) {
        Log.i( id, message );
    }

    @Override
    public void w ( @NotNull String id, @Nullable String message ) {
        Log.w( id, message );
    }

    @Override
    public void e ( @NotNull String id, @Nullable String message ) {
        Log.e( id, message );
    }

    @Override
    public void save ( String filename ) {
    }
}
