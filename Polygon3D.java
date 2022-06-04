import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Point;
import java.awt.Color;

public class Polygon3D 
{
    public Color color;
    public Point3D[] points3d;
    public Point3D center;

    public Polygon3D(Color color, Point3D... points3d)
    {
        this.color = color;
        this.points3d = points3d;
        this.center = refreshCenter();
    }

    public void render(Graphics g)
    {
        Polygon polygon = new Polygon();
        Point point;

        for (Point3D point3d : points3d) 
        {
            point = PointDisplayTranslater.converterPoint(point3d);
            polygon.addPoint(point.x, point.y);
        }

        g.setColor(color);
        g.fillPolygon(polygon);;
    }

    public Point3D refreshCenter()
    {
        double x = 0;
        double y = 0;
        double z = 0;

        for (Point3D point3d : points3d) 
        {
            x += point3d.x;
            y += point3d.y;
            z += point3d.z;
        }

        x /= points3d.length;
        y /= points3d.length;
        z /= points3d.length;

        return new Point3D(x, y, z);
    }
}
