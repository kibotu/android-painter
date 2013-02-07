package net.kibotu.android.painter.math;

import java.util.ArrayList;

/**
 * User: Jan Rabe
 * Date: 13/12/12
 * Time: 13:53
 */
public class Path {

    private ArrayList<Vector3> path;
    private long entireLength;

    public Path() {
        this.path = new ArrayList<Vector3>();
        entireLength = 0;
    }

    public void add(Vector3 vector) {
        path.add(vector);
        // accumulate distance from last position to new position
        if (!path.isEmpty()) entireLength += path.get(path.size() - 1).getDistance(vector);
    }

    public void add(int x, int y, int z) {
        add(new Vector3(x, y, z));
    }

    /**
     * gets current vector based
     *
     * @param percent number between 0 and 1
     * @return current location on the path, null if percent not between 0 and 1
     */
    public Vector3 getLocation(float percent) {
        // return null if path empty
        if (path.isEmpty()) return null;
        // if < 0% first element
        if (Float.compare(percent, 0) < 0) return path.get(0);
        // if > 100% last element
        if (Float.compare(percent, 1) > 0) return path.get(path.size() - 1);
        // current length
        float dt = percent * entireLength;
        long tmp = 0;
        // find current position based on vector length
        Vector3 curPos = path.get(0);
        for (int i = 1; i < path.size() - 1; ++i) {
            // get distance between this and next point
            tmp += curPos.getDistance(path.get(i + 1));
            // accumulated distance is never longer than the entire length
            assert tmp <= entireLength;
            // accumulation is bigger than tmp, we've found our 2 vectors
            if (tmp > dt) return getCurrentPosition(curPos, path.get(i), tmp - dt);
            // new curPos
            curPos = path.get(i);
        }
        return null;
    }

    private Vector3 getCurrentPosition(Vector3 current, Vector3 next, float curDistance) {
        float distance = curDistance / current.getDistance(next);
        assert distance > 0 && distance < 1;
        getLinearInterpolatedPoint(current.x, current.y, current.z, next.x, next.y, next.z, distance);
        return new Vector3(temp[0], temp[1], temp[2]);
    }

    private final static float[] temp = new float[3];

    public static float[] getLinearInterpolatedPoint(float x0, float y0, float z0, float x1, float y1, float z1, float percent) {
        temp[0] = x0 + (x1 - x0) * percent;
        temp[1] = y0 + (y1 - y0) * percent;
        temp[2] = z0 + (z1 - z0) * percent;
        return temp;
    }
}
