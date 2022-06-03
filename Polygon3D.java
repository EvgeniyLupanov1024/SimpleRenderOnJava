import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Point;
import java.awt.Color;

public class Polygon3D 
{
    private Color color;
    private Point3D[] points3d;

    public Polygon3D(Color color, Point3D... points3d)
    {
        this.color = color;
        this.points3d = points3d;
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
}
