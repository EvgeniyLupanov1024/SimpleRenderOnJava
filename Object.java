import java.awt.Graphics;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

public abstract class Object 
{
    public List<Polygon3D> magicPoligons = new ArrayList<Polygon3D>();
    public List<Point3D> magicPoints = new ArrayList<Point3D>();
    public Point3D center;

    public void sortPolygons() 
    {
        for (Polygon3D magicPoligon : magicPoligons) 
        {
            magicPoligon.refreshCenter();
        }

        Collections.sort(magicPoligons, new Comparator<Polygon3D>(){ // todo -- быстро сортировать уже отсортированные
            
            @Override
            public int compare(Polygon3D o1, Polygon3D o2)
            {
                return (int) (o2.center.x - o1.center.x);
            }
        });
    }

    public void refreshCenter()
    {
        double x = 0;
        double y = 0;
        double z = 0;

        for (Polygon3D magicPoligon : magicPoligons) 
        {
            x += magicPoligon.center.x;
            y += magicPoligon.center.y;
            z += magicPoligon.center.z;
        }

        x /= magicPoligons.size();
        y /= magicPoligons.size();
        z /= magicPoligons.size();

        this.center = new Point3D(x, y, z);
    }

    abstract public void render(Graphics g);
    abstract public void update();
}
