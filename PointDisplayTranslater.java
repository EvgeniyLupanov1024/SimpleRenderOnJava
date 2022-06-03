import java.awt.Point;

public class PointDisplayTranslater 
{
    public static Point converterPoint(Point3D point3D)
    {
        int x2d = Display.HALF_WIDTH + (int) point3D.y;
        int y2d = Display.HALF_HEIGTH - (int) point3D.z;

        return new Point(x2d, y2d);
    }
}