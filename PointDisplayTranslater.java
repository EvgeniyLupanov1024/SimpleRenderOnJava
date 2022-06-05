import java.awt.Point;

public class PointDisplayTranslater 
{
    public static enum RotateMode {
        SELF_CENTER,
        WORLD_CENTER
    }

    public static final Point3D worldCenter = new Point3D(0, 0, 0);

    public static Point converterPoint(Point3D point3D)
    {
        double depth = (1400 + point3D.x) / 1400;

        int x2d = Display.HALF_WIDTH + (int) (point3D.y / depth);
        int y2d = Display.HALF_HEIGTH - (int) (point3D.z / depth);

        return new Point(x2d, y2d);
    }
}
