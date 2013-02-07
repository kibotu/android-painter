package net.kibotu.android.painter;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import net.kibotu.android.painter.utils.log.Logger;

/**
 * User: Jan Rabe
 * Date: 17/11/12
 * Time: 17:21
 */
public class BasicActivity extends Activity {

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        Logger.V(this, "Application created.");
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onPause() {
        Logger.V(this, "Application paused.");
        super.onPause();
    }

    @Override
    public void onStop() {
        Logger.V(this, "Application stopped.");
        super.onStop();
    }

    @Override
    public void onStart() {
        Logger.V(this, "Application started.");
        super.onStart();
    }

    @Override
    public void onRestart() {
        Logger.V(this, "Application restarted.");
        super.onRestart();
    }

    @Override
    public void onResume() {
        Logger.V(this, "Application resumed.");
        super.onResume();
    }

    @Override
    public void onDestroy() {
        Logger.V(this, "Application destroyed.");
        super.onDestroy();
    }
}
