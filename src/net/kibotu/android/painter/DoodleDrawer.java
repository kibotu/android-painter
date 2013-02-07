package net.kibotu.android.painter;

import android.app.Dialog;
import android.graphics.Color;
import android.os.*;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import net.kibotu.android.painter.utils.ColorPickerDialog;
import net.kibotu.android.painter.utils.Utils;
import net.kibotu.android.painter.utils.log.Logger;
import net.kibotu.android.painter.view.DoodleViewer;
import net.kibotu.android.painter.view.drawable.brushes.*;

public class DoodleDrawer extends BasicActivity {

    public Handler updateHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            viewer.invalidate();
            super.handleMessage(msg);
        }
    };
    private DoodleViewer viewer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // check device abilities
        Config.DEVICE_SDK_VERSION = Build.VERSION.SDK_INT;

        // viewer
        initViewer();
    }

    private void initViewer() {
        viewer = new DoodleViewer(this);
        setContentView(viewer);
        viewer.requestFocus();
        viewer.setKeepScreenOn(true);
    }

    @Override
    public void onPause() {
        viewer.stop();
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        onCreate(null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_screen:
                viewer.clear();
//                Utils.toast(this, "New canvas created.");
                return true;
            case R.id.save_screen:
                Utils.toast(this, "Saved in: " + Utils.saveBitmap(viewer.getCurrentScreen(), Environment.getExternalStorageDirectory().toString() + "/DoodleDrawer/", "Screenshot"));
                return true;
            case R.id.bg_color_button:
                new ColorPickerDialog(this, viewer, "bg", Color.BLACK, Color.BLACK).show();
                return true;
            case R.id.line_color_button:
                new ColorPickerDialog(this, viewer, "line", Color.BLACK, Color.BLACK).show();
                return true;
            case R.id.brush_default:
                viewer.setBrush(new SimpleBrush());
                return true;
            case R.id.brush_shaded:
                viewer.setBrush(new ShadedBrush());
                return true;
            case R.id.brush_sketchy:
                viewer.setBrush(new SketchyBrush());
                return true;
            case R.id.brush_chrome:
                viewer.setBrush(new ChromeBrush());
                return true;
            case R.id.brush_web:
                viewer.setBrush(new WebBrush());
                return true;
            case R.id.brush_fur:
                viewer.setBrush(new FurBrush());
                return true;
            case R.id.brush_ribbon:
                viewer.setBrush(new RibbonBrush());
                return true;
            case R.id.brush_circle:
                viewer.setBrush(new CircleBrush());
                return true;
            case R.id.line_stroke_seekbar:
                setSize();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void setSize() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.spinner);
        dialog.setTitle("Choose Stroke Size");
        dialog.setCancelable(true);
        //there are a lot of settings, for dialog, check them all out!
        dialog.show();

        final TextView sizeText = (TextView) dialog.findViewById(R.id.set_size_help_text);
        sizeText.setText("Size:\t"+viewer.getStrokeWidth());

        final SeekBar seekbar = (SeekBar) dialog.findViewById(R.id.size_seekbar);
        seekbar.setMax(Config.SEEKBAR_MAX_SIZE);
        seekbar.setProgress(viewer.getStrokeWidth());
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                viewer.setStrokeWidth(i);
                sizeText.setText("Size:\t"+viewer.getStrokeWidth());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                dialog.dismiss();
            }
        });
    }
}