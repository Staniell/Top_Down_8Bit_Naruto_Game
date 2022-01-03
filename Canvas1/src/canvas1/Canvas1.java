
package canvas1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.TimerTask;
import java.util.Timer;

public class Canvas1 extends JFrame {

  
    CustomCanvas DrawingArea;
    int x, y;

    public Canvas1() {

        Container Pane;
        Pane = getContentPane();
        Pane.setLayout(null);
        DrawingArea = new CustomCanvas();
        DrawingArea.setBounds(0, 0, 700, 700);

        Pane.add(DrawingArea);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        setSize(700, 730);
        show();

    }

    public static void main(String[] args) {
        Canvas1 app = new Canvas1();
    }

}

class CustomCanvas extends Canvas {

    String str;
    public int x;
    public int y;
    public int virtualX = 220;
    public int virtualY = 120;
    public int pos = 8;
    Timer timer;
    TimerTask task;

    Image imgCurent;
    boolean moving = false;
    int gameAction;
    int frame = 0;
    int index = 0;

    String[] directions = {"U", "D", "L", "R"};
    Image[] img = new Image[16];
    ImageIcon icon;

    Rectangle rect ;   //para kay naruto

    Rectangle r1;
    Rectangle r2;
    Rectangle r3;
    Rectangle r4;
    Rectangle r5;
    Rectangle r6;
    Rectangle r7;

    Rectangle r8;
    Rectangle r9;
    Rectangle r10;
    Rectangle r11;
    Rectangle r12;


    Rectangle d1;

    ImageIcon bg;
    Image backgroundx;
    Image newImage;

    public CustomCanvas() {

  
        
        for (int d = 0; d < directions.length; d++) {
            for (int f = 1; f <= 4; f++) {
                icon = new ImageIcon("Canvas1/"+directions[d] + f + ".png");
                img[index] = icon.getImage();
                index++;
            }//loop frame to create all images in array form
        }
        
        imgCurent = img[0]; //initial image of naruto upon running code

        bg = new ImageIcon("Canvas1/background_img.png");
        backgroundx = bg.getImage();
        newImage = backgroundx.getScaledInstance(700, 700, Image.SCALE_DEFAULT);



        timer = new Timer(true);
        task = new TimerTask() {
            public void run() {
                moveIt();
                System.out.println("X:"+virtualX +" Y:"+virtualY);

            }//end run
        };//end task 

        timer.schedule(task, 1000, 100);
        setBackground(Color.BLUE);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                gameAction = evt.getKeyCode();
                moving = true;
            }

            @Override
            public void keyReleased(KeyEvent evt) {
                gameAction = 0;
                moving = false;
            }
        });

        addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Mouse was clicked at: " + e.getX());
                str = "" + e.getX() + " " + e.getY();
                virtualX = e.getX();
                virtualY = e.getY();
                repaint();
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {

            }

            @Override
            public void mouseExited(MouseEvent arg0) {
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
                System.out.println("Mouse was pressed");

            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
            }
        });

        addMouseMotionListener(new MouseMotionListener() {

            public void mouseDragged(MouseEvent e) {
                virtualX = e.getX();
                virtualY = e.getY();
                repaint();
            }

            public void mouseMoved(MouseEvent e) {
            }
        });

    }

   
    @Override
    public void paint(Graphics g) {

        super.paint(g);
        int Height;
        Height = getHeight();


        g.drawImage(newImage, 0,0, this);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString("Si Naruto ay nasa position: " + virtualX + "," + virtualY + " " + str, 10, Height / 2);

        //ito ang rect ni naruto
        rect = new Rectangle( virtualX , virtualY,imgCurent.getWidth(this)+5, imgCurent.getHeight(this)+5);

//        Color c=new Color(1f,0f,0f,.5f );
//        g.setColor ( c );
//        g.fillRect ( rect.x,rect.y,rect.width,rect.height );
        g.drawImage(imgCurent, virtualX, virtualY, this);
        
        //ito ang rect ng pader
        
//        rect2 = new Rectangle(45,115,wall.getWidth(this)+5, wall.getHeight(this)+5);
//        g.setColor ( Color.BLACK );
//        g.fillRect (rect2.x,rect2.y,rect2.width,rect2.height);
//        g.drawImage(wall, 45, 115, this);

        r1 = new Rectangle(0,65,1500,40);
        g.setColor(Color.RED);
        g.fillRect(0,65,1500,40);

        r2 = new Rectangle(81, 110, 30, 330);
        g.setColor(Color.RED);
        g.fillRect(81, 110, 30, 330);

        r3 = new Rectangle(122, 213, 30, 150);
        g.setColor(Color.RED);
        g.fillRect(122, 213, 30, 150);

        r4 = new Rectangle(161, 252, 30, 100);
        g.setColor(Color.RED);
        g.fillRect(161, 252, 30, 100);

        r5 = new Rectangle(313, 109, 30, 240);
        g.setColor(Color.RED);
        g.fillRect(313, 109, 30, 240);

        r6 = new Rectangle(287, 232, 128, 80);
        g.setColor(Color.RED);
        g.fillRect(287, 232, 128, 80);

        r7 = new Rectangle(400, 112, 30, 120);
        g.setColor(Color.RED);
        g.fillRect(400, 112, 30, 120);

        r8 = new Rectangle(425, 100, 40, 100);
        g.setColor(Color.RED);
        g.fillRect(425, 100, 40, 100);

        r9 = new Rectangle(583, 107, 60, 190);
        g.setColor(Color.RED);
        g.fillRect(583, 107, 60, 190);

        r10 = new Rectangle(477, 280, 130, 30);
        g.setColor(Color.blue);
        g.fillRect(477, 280, 130, 30);

        Collide();


    }

    public void moveIt() {
        if (moving == false) {
            return;
        }
        switch (gameAction) {
            case KeyEvent.VK_DOWN:
                if (rect.intersects(r1) == false && rect.intersects(r2) == false && rect.intersects(r3) == false){
                x = 1;
                virtualY += 5;
                System.out.println(frame);
                frame = (frame + 1) % 4;
                imgCurent = img[frame + 4];
                }
                else
                    virtualY-=7;
                repaint();
                break;
            case KeyEvent.VK_UP:
                if (rect.intersects(r1) == false && rect.intersects(r2) == false && rect.intersects(r3) == false) {
                    x = 2;
                    virtualY -= 5;
                    frame = (frame + 1) % 4;
                    imgCurent = img[frame + 0];

                }
                else
                    virtualY+=7;
                repaint();
                break;
            case KeyEvent.VK_LEFT:
                if (rect.intersects(r1) == false && rect.intersects(r2) == false && rect.intersects(r3) == false) {
                    x = 3;
                    virtualX -= 5;
                    frame = (frame + 1) % 4;
                    imgCurent = img[frame + 8];
                }
                else
                    virtualX+=7;
                repaint();
                break;
            case KeyEvent.VK_RIGHT:
                if (rect.intersects(r1) == false && rect.intersects(r2) == false && rect.intersects(r3) == false) {
                    x = 4;
                    virtualX += 5;
                    frame = (frame + 1) % 4;
                    imgCurent = img[frame + 12];
                }
                else
                    virtualX-=7;
                repaint();
                break;
        }

    }

    
     public void Collide()    {      
//      if (rect.intersects(rect2)){
//          virtualX -=5;
//        System.out.println("wakokok nagbanggaan!!!");
//        virtualX= 0;
//        virtualY= 0;
//        icon = new ImageIcon("Canvas1/die.png");
//        imgCurent = icon.getImage();
//
//        repaint();
//     }
    }

}
