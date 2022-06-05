import java.awt.Point;

import java.util.Collections;
import java.util.Comparator;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import java.util.List;
import java.util.ArrayList;

import java.awt.MouseInfo;
import javax.swing.JFrame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Display extends JFrame implements KeyListener, MouseListener
{
    public static String title = "Рендрю кудик в 3d";
    public static final int WIDTH = 1000;
    public static final int HEIGTH = 700;
    public static final int HALF_WIDTH = WIDTH / 2;
    public static final int HALF_HEIGTH = HEIGTH / 2;
    public static Color bgColor = new Color(11, 22, 55);

    public static boolean running = false;
    public static boolean updating = true;
    public static List<Object> scene = new ArrayList<Object>();

    public static double mouseLastX;
    public static double mouseDeltaX;
    public static double mouseLastY;
    public static double mouseDeltaY;
    public static boolean mouseLeft;

    public Display() 
    {
        setSize(WIDTH, HEIGTH);
        setTitle(title);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addKeyListener(this);
        addMouseListener(this);

        running = true;
        setVisible(true);
    }

    public static void main (String[] arg)
    {
        Display display = new Display();
        display.start();
    }

    public void start()
    {
        running = true;
        this.run();
    }

    public void stop()
    {
        running = false;
    }

    public void run() 
    {
        long lastTimeUpdate = System.nanoTime();
        final double nsUpdate = 1000000000.0 / 60;
        
        long lastTimeMs = System.currentTimeMillis();
        int framesCount = 0;

        init();

        while (running)
        {
            if (System.nanoTime() - lastTimeUpdate > nsUpdate) {

                lastTimeUpdate = System.nanoTime();

                if (updating) {
                    update();
                }

                render();
                ++framesCount;
            }

            if (System.currentTimeMillis() - lastTimeMs > 1000) {
                
                lastTimeMs = System.currentTimeMillis();
                this.setTitle(title + " | " + framesCount + " fps");
                framesCount = 0;
            }
        }
    }

    private void init()
    {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(2);
        }

        scene.add(new MagicCube(60));
         
        scene.add(new MagicCube(60, new Point3D(200, 200, 200)));
        scene.add(new MagicCube(60, new Point3D(-200, -200, -200)));
        scene.add(new MagicCube(60, new Point3D(-200, 200, -200)));
        scene.add(new MagicCube(60, new Point3D(200, -200, 200)));
    }

    private void update()
    {
        inputUpdate();

        for (Object object : scene) 
        {
            object.update();
        }
    }

    private void inputUpdate()
    {
        Point mousePoint = MouseInfo.getPointerInfo().getLocation();
        mouseDeltaX = mousePoint.x - mouseLastX;
        mouseDeltaY = mousePoint.y - mouseLastY;
        mouseLastX = mousePoint.x;
        mouseLastY = mousePoint.y;
    }

    private void render()
    {
        BufferStrategy bs = getBufferStrategy();
        Graphics g = bs.getDrawGraphics();

        g.setColor(bgColor);
        g.fillRect(0, 0, getWidth(), getHeight());

        sortObjects();

        for (Object object : scene) 
        {
            object.render(g);
        }

        g.dispose();
        bs.show();
    }

    public void sortObjects() 
    {
        for (Object object : scene) 
        {
            object.refreshCenter();
        }

        Collections.sort(scene, new Comparator<Object>(){ // todo -- быстро сортировать уже отсортированные
            
            @Override
            public int compare(Object o1, Object o2)
            {
                return (int) (o2.center.x - o1.center.x);
            }
        });
    }

    /*
     * key handler
     */

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        switch (e.getKeyCode()) 
        {
            case KeyEvent.VK_SPACE:
                updating = !updating;
                break;

            case KeyEvent.VK_L:
                System.out.println();
                break;

            case KeyEvent.VK_ESCAPE:
                System.exit(0);
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    /*
     * mouse handler
     */

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mousePressed(MouseEvent e) 
    {
        mouseLeft = true;    
    }

    @Override
    public void mouseReleased(MouseEvent e) 
    {
        mouseLeft = false;    
    }
}