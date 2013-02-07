package net.kibotu.android.painter.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.widget.Toast;
import net.kibotu.android.painter.utils.log.Logger;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.Random;

/**
 * User: Jan Rabe
 * Date: 14/12/12
 * Time: 09:39
 */
public class Utils {

    private static final String TAG = Utils.class.getSimpleName();

    private Utils() {
    }

    public static final Bitmap getBitmap(int resourceId, float scale) {
        final Bitmap bitmap = BitmapFactory.decodeStream(Shared.getContext().getResources().openRawResource(resourceId));
        // scale
        final Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    /**
     * Saves a bitmap into a folder.
     *
     * @param screenshot
     * @param filePath
     * @param fileName
     * @return saved filepath
     */
    public static String saveBitmap(@NotNull Bitmap screenshot, @NotNull String filePath, @NotNull String fileName) {
        OutputStream outStream = null;
        File dir = new File(filePath);
        dir.mkdirs();
        File output;
        String finalPath;
        do {
            finalPath = filePath + fileName + "_" + UIDGenerator.getNewUID() + ".png";
            output = new File(finalPath);
        } while (output.exists());
        try {
            outStream = new FileOutputStream(output);
            screenshot.compress(Bitmap.CompressFormat.PNG, 100, outStream);
            outStream.flush();
            outStream.close();
            Logger.V(TAG, "Saving Screenshot [" + filePath + fileName + "]");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return finalPath;
    }

    /**
     * Toast message within gl thread
     *
     * @param activity
     * @param message
     */
    final public static void toast(final Activity activity, final String message) {
        activity.runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static int[] HSB2RGB(int j, int d, int c) {
        int e = 0, g = 0, l = 0, h = 0, k = 0, b = 0, a = 0, m = 0;
        if (c == 0) {
            return new int[]{0, 0, 0};
        }
        j *= 0.016666667;
        d *= 0.01;
        c *= 0.01;
        h = (int) Math.floor(j);
        k = j - h;
        b = c * (1 - d);
        a = c * (1 - (d * k));
        m = c * (1 - (d * (1 - k)));
        switch (h) {
            case 0:
                e = c;
                g = m;
                l = b;
                break;
            case 1:
                e = a;
                g = c;
                l = b;
                break;
            case 2:
                e = b;
                g = c;
                l = m;
                break;
            case 3:
                e = b;
                g = a;
                l = c;
                break;
            case 4:
                e = m;
                g = b;
                l = c;
                break;
            case 5:
                e = c;
                g = b;
                l = a;
                break;
        }
        return new int[]{e, g, l};
    }

    public static int[] RGB2HSB(int c, int d, int k) {
        int j = 0, h = 0, e = 0, g = 0, b = 0, a = 0;
        j = Math.min(Math.min(c, d), k);
        a = Math.max(Math.max(c, d), k);
        if (j == a) {
            return new int[]{0, 0, a * 100};
        }
        h = (c == j) ? d - k : ((d == j) ? k - c : c - d);
        e = (c == j) ? 3 : ((d == j) ? 5 : 1);
        g = (int) (Math.floor((e - h / (a - j)) * 60) % 360);
        b = (int) Math.floor(((a - j) / a) * 100);
        a = (int) Math.floor(a * 100);
        return new int[]{g, b, a};
    }

    public static int getRandomColor() {
        Random random = new Random();
        return Color.rgb(random.nextInt(255), random.nextInt(255), random.nextInt(255));
    }
}
