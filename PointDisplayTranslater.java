import java.awt.Point;

public class PointDisplayTranslater 
{
    public static Point3D cameraPoint = new Point3D(500, 0, 0);

    public static Point converterPoint(Point3D point3D)
    {
        double depth = (Display.HEIGTH + point3D.x) / Display.HEIGTH;

        int x2d = Display.HALF_WIDTH + (int) (point3D.y / depth);
        int y2d = Display.HALF_HEIGTH - (int) (point3D.z / depth);

        return new Point(x2d, y2d);
    }
}