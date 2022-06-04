import java.awt.Color;
import java.awt.Graphics;

public class MagicCube extends Object
{
    public MagicCube(int size)
    {
        this(size, new Point3D(0, 0, 0));
    }

    public MagicCube(int size, Point3D spawnPoint)
    {
        this.center = spawnPoint;

        Point3D front_top_left = new Point3D(-size + spawnPoint.x, -size + spawnPoint.y, size + spawnPoint.z);
        Point3D front_top_right = new Point3D(-size + spawnPoint.x, size + spawnPoint.y, size + spawnPoint.z);
        Point3D front_down_left = new Point3D(-size + spawnPoint.x, -size + spawnPoint.y, -size + spawnPoint.z);
        Point3D front_down_right = new Point3D(-size + spawnPoint.x, size + spawnPoint.y, -size + spawnPoint.z);
        Point3D back_top_left = new Point3D(size + spawnPoint.x, -size + spawnPoint.y, size + spawnPoint.z);
        Point3D back_top_right = new Point3D(size + spawnPoint.x, size + spawnPoint.y, size + spawnPoint.z);
        Point3D back_down_left = new Point3D(size + spawnPoint.x, -size + spawnPoint.y, -size + spawnPoint.z);
        Point3D back_down_right = new Point3D(size + spawnPoint.x, size + spawnPoint.y, -size + spawnPoint.z);

        magicPoints.add(front_top_left);
        magicPoints.add(front_top_right);
        magicPoints.add(front_down_left);
        magicPoints.add(front_down_right);
        magicPoints.add(back_top_left);
        magicPoints.add(back_top_right);
        magicPoints.add(back_down_left);
        magicPoints.add(back_down_right);

        Polygon3D front = new Polygon3D(Color.RED, front_top_left, front_top_right, front_down_right, front_down_left);
        Polygon3D back = new Polygon3D(Color.BLACK, back_top_left, back_top_right, back_down_right, back_down_left);
        Polygon3D top = new Polygon3D(Color.ORANGE, front_top_left, front_top_right, back_top_right, back_top_left);
        Polygon3D down = new Polygon3D(Color.BLUE, back_down_left, front_down_right, front_down_right, front_down_left);
        Polygon3D left = new Polygon3D(Color.CYAN, front_top_left, back_top_left, back_down_left, front_down_left);
        Polygon3D right = new Polygon3D(Color.YELLOW, back_top_right, front_top_right, front_down_right, back_down_right);

        magicPoligons.add(front);
        magicPoligons.add(back);
        magicPoligons.add(top);
        magicPoligons.add(down);
        magicPoligons.add(left);
        magicPoligons.add(right);

        sortPolygons();
    }

    public void render(Graphics g)
    {
        sortPolygons();

        for (Polygon3D polygon3d : magicPoligons) 
        {
            polygon3d.render(g);    
        }
    }

    public void update()
    {
        rotateAxisX(0.78);
        rotateAxisZ(0.23);

        for (Polygon3D magicPoligon : magicPoligons) 
        {
            magicPoligon.refreshCenter();
        }
    }

    public void rotateAxisX(double degrees)
    {
        for (Point3D point3d : magicPoints) 
        {
            double radius = Math.sqrt(point3d.y * point3d.y + point3d.z * point3d.z);
            double angle = Math.atan2(point3d.y, point3d.z);
            angle += Math.toRadians(degrees);

            point3d.y = radius * Math.sin(angle);
            point3d.z = radius * Math.cos(angle);
        }
    }

    public void rotateAxisZ(double degrees)
    {
        for (Point3D point3d : magicPoints) 
        {
            double radius = Math.sqrt(point3d.x * point3d.x + point3d.y * point3d.y);
            double angle = Math.atan2(point3d.x, point3d.y);
            angle += Math.toRadians(degrees);

            point3d.x = radius * Math.sin(angle);
            point3d.y = radius * Math.cos(angle);
        }
    }
}
