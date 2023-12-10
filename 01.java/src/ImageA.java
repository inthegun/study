import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ImageA extends Frame {
    Image img;

    ImageA(){
        img = Toolkit.getDefaultToolkit().getImage("D:/Desktop/123123111.png");

        addWindowListener(new MyWin());
        setSize(450,450);
        setVisible(true);
    }

    class MyWin extends WindowAdapter{
        @Override
        public void windowClosing(WindowEvent e) {
            super.windowClosing(e);
        }
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(img,50,50,this);
    }

    public static void main(String[] args) {
        new ImageA();
    }
}
