import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Display extends Canvas implements Runnable
{
    private Thread thread;
    private JFrame frame;

    private static String title = "Рендрю кудик в 3d";
    private static final int WIDTH = 800;
    private static final int HEIGTH = 600;

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

    }

    public void stop()
    {
        running = false;

    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        
    }
}