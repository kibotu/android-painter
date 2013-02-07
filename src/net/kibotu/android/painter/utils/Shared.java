package net.kibotu.android.painter.utils;

import android.content.Context;

/**
 * User: Jan Rabe
 * Date: 17/11/12
 * Time: 15:49
 */
final public class Shared {

    private static Context context;

    // utility class
    private Shared() {
    }

    public static Context getContext() {
        return Shared.context;
    }

    public static void setContext(Context context) {
        Shared.context = context;
    }
}
