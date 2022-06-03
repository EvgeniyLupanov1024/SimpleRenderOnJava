import java.awt.Canvas;
import java.awt.Dimension;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class Display extends Canvas implements Runnable
{
    private Thread thread;
    private JFrame frame;

    private static String title = "Рендрю кудик в 3d";
    private static final int WIDTH = 800;
    private static final int HEIGTH = 600;
    private static Color bgColor = new Color(11, 22, 55);

    private static boolean running = false;

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
            return;
        }
    }

    private void render()
    {
        BufferStrategy bs = getBufferStrategy();
        Graphics g = bs.getDrawGraphics();

        g.setColor(bgColor);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.dispose();
        bs.show();
    }

    private void update()
    {
        
    }
}