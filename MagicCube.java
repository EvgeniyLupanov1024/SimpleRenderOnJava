import java.util.spi.ToolProvider;
import java.awt.Color;
import java.awt.Graphics;

public class MagicCube 
{
    public Polygon3D[] magicPoligons;

    public MagicCube(int size)
    {
        this(size, new Point3D(0, 0, 0));
    }

    public MagicCube(int size, Point3D spawnPoint)
    {
        Point3D front_top_left = new Point3D(-size + spawnPoint.x, -size + spawnPoint.y, size + spawnPoint.z);
        Point3D front_top_right = new Point3D(-size + spawnPoint.x, size + spawnPoint.y, size + spawnPoint.z);
        Point3D front_down_left = new Point3D(-size + spawnPoint.x, -size + spawnPoint.y, -size + spawnPoint.z);
        Point3D front_down_right = new Point3D(-size + spawnPoint.x, size + spawnPoint.y, -size + spawnPoint.z);
        Point3D back_top_left = new Point3D(size + spawnPoint.x, -size + spawnPoint.y, size + spawnPoint.z);
        Point3D back_top_right = new Point3D(size + spawnPoint.x, size + spawnPoint.y, size + spawnPoint.z);
        Point3D back_down_left = new Point3D(size + spawnPoint.x, -size + spawnPoint.y, -size + spawnPoint.z);
        Point3D back_down_right = new Point3D(size + spawnPoint.x, size + spawnPoint.y, -size + spawnPoint.z);

        Polygon3D front = new Polygon3D(Color.RED, front_top_left, front_top_right, front_down_right, front_down_left);
        Polygon3D back = new Polygon3D(Color.BLACK, back_top_left, back_top_right, back_down_right, back_down_left);
        Polygon3D top = new Polygon3D(Color.ORANGE, front_top_left, front_top_right, back_top_right, back_top_left);
        Polygon3D down = new Polygon3D(Color.BLUE, back_down_left, front_down_right, front_down_right, front_down_left);
        Polygon3D left = new Polygon3D(Color.CYAN, front_top_left, back_top_left, back_down_left, front_down_left);
        Polygon3D right = new Polygon3D(Color.YELLOW, back_top_right, front_top_right, front_down_right, back_down_right);

        magicPoligons = new Polygon3D[] {
            front,
            back,
            top,
            down,
            left,
            right,
        };
    }

    public void render(Graphics g)
    {
        for (Polygon3D polygon3d : magicPoligons) 
        {
            polygon3d.render(g);    
        }
    }
}
