import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Point;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import java.util.List;
import java.util.ArrayList;

import java.awt.MouseInfo;
import javax.swing.JFrame;

public class Display extends Canvas implements Runnable
{
    private Thread thread;
    private JFrame frame;

    private static String title = "Рендрю кудик в 3d";
    public static final int WIDTH = 800;
    public static final int HEIGTH = 600;
    public static final int HALF_WIDTH = WIDTH / 2;
    public static final int HALF_HEIGTH = HEIGTH / 2;
    private static Color bgColor = new Color(11, 22, 55);

    private static boolean running = false;
    private static List<Object> scene = new ArrayList<Object>();

    public static double mouseLastX;
    public static double mouseDeltaX;
    public static double mouseLastY;
    public static double mouseDeltaY;
    public static boolean mouseLeft;

    public Display() 
    {
        this.frame = new JFrame();

        Dimension size = new Dimension(WIDTH, HEIGTH);
        this.setPreferredSize(size);
    }

    public static void main (String[] arg)
    {
        Display display = new Display();
        display.frame.add(display);
        
        display.frame.setTitle(title);
        display.frame.pack(); // автоустановка размера из всех добавленных элементов
        display.frame.setResizable(false);
        display.frame.setLocationRelativeTo(null);
        display.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        display.frame.setVisible(true);
        display.start();
    }

    public void start()
    {
        running = true;
        this.thread = new Thread(this, "Display");
        this.thread.start();
    }

    public void stop()
    {
        running = false;

        try {
            this.thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
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

                update();
                render();
                ++framesCount;
            }

            if (System.currentTimeMillis() - lastTimeMs > 1000) {
                
                lastTimeMs = System.currentTimeMillis();
                this.frame.setTitle(title + " | " + framesCount + " fps");
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

        scene.add(new MagicCube(100));
    }

    private void update()
    {
        inputUpdate();

        for (Object object : scene) {
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

        for (Object object : scene) {
            object.render(g);
        }

        g.dispose();
        bs.show();
    }
}