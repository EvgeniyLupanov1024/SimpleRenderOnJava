import java.awt.Graphics;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

public abstract class RendrableObject 
{
    public List<Polygon3D> magicPoligons = new ArrayList<Polygon3D>();

    public void sortPolygons() 
    {
        Collections.sort(magicPoligons, new Comparator<Polygon3D>(){ // todo -- быстро сортировать уже отсортированные
            
            @Override
            public int compare(Polygon3D o1, Polygon3D o2)
            {
                return (int) (o2.center.x - o1.center.x);
            }
        });
    }

    abstract public void render(Graphics g);
}
