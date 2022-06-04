import java.awt.Point;

public class PointDisplayTranslater 
{
    public static Point converterPoint(Point3D point3D)
    {
        double depth = (Display.WIDTH + point3D.x) / Display.WIDTH;

        int x2d = Display.HALF_WIDTH + (int) (point3D.y / depth);
        int y2d = Display.HALF_HEIGTH - (int) (point3D.z / depth);

        return new Point(x2d, y2d);
    }
}