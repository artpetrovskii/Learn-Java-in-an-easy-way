import javax.swing.Timer;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

public class BubblePanel3 extends JPanel {
    Random rand = new Random();
    ArrayList<Bubble> bubbleList;
    int size = 25;
    Timer timer;
    int delay = 33;
    JSlider slider;   
    public BubblePanel3(){
        timer = new Timer(delay, new BubbleListener() );
        bubbleList = new ArrayList<Bubble>();
        setBackground(Color.BLACK);
        JPanel panel = new JPanel();
        add(panel);
        JButton btnPause = new JButton("Pause");
        btnPause.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JButton btn = (JButton)e.getSource();
                if (btn.getText().equals("Pause")){
                    timer.stop();
                    btn.setText("Start");
                }
                else {
                    timer.start();
                    btn.setText("Pause");
                }
            }
        });        
        JLabel lblAnimationSpeed = new JLabel("Animation Speed:");
        panel.add(lblAnimationSpeed);        
        slider = new JSlider();
        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent arg0) {
                int speed = slider.getValue() + 1;
                int delay = 1000 / speed;
                timer.setDelay(delay);
            }
        });
        slider.setValue(30);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setMinorTickSpacing(5);
        slider.setMaximum(120);
        slider.setMajorTickSpacing(30);
        panel.add(slider);
        panel.add(btnPause);
        JButton btnClear = new JButton("Clear");
        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                bubbleList = new ArrayList<Bubble>();
                repaint();
            }
        });
        panel.add(btnClear);
        //testBubbles();
        addMouseListener( new BubbleListener() );
        addMouseMotionListener( new BubbleListener() );
        addMouseWheelListener( new BubbleListener() );
        timer.start();
    }
    public void paintComponent(Graphics canvas){
        super.paintComponent(canvas);
        for(Bubble b : bubbleList){
            b.draw(canvas);
        }
    }
    public void testBubbles(){
        for(int n = 0; n < 100; n++){
            int x = rand.nextInt(600);
            int y = rand.nextInt(400);
            int size = rand.nextInt(50);
            bubbleList.add( new Bubble(x, y, size) );
        }
        repaint();
    }
    private class BubbleListener extends MouseAdapter implements ActionListener {
        public void mousePressed(MouseEvent e) {
            bubbleList.add(new Bubble(e.getX(), e.getY(), size));
            repaint();
        }
        public void mouseDragged(MouseEvent e) {
            bubbleList.add(new Bubble(e.getX(), e.getY(), size));
            repaint();
        }
        public void mouseWheelMoved(MouseWheelEvent e) {
            if(System.getProperty("os.name").startsWith("Mac"))
                size += e.getUnitsToScroll();
            else
                size -= e.getUnitsToScroll();
            if (size < 3)
                size = 3;
        }
        public void actionPerformed(ActionEvent e) {
             for (Bubble b : bubbleList)
                 b.update();
             repaint();
        }
    }
    private class Bubble {
        private int x;
        private int y;
        private int size;
        private Color color;
        private int xspeed, yspeed;
        private final int MAX_SPEED = 5;
        public Bubble(int newX, int newY, int newSize){
            x = (newX / newSize) * newSize + newSize/2;
            y = (newY / newSize) * newSize + newSize/2;
            size = newSize;
            color = new Color(rand.nextInt(256),
                    rand.nextInt(256),
                    rand.nextInt(256),
                    rand.nextInt(256) );
            xspeed = yspeed = 2;
        }
        public void draw(Graphics canvas){
            canvas.setColor(color);
            canvas.fillRect(x - size/2, y - size/2, size, size);
        }
        public void update(){
            x += xspeed;
            y += yspeed;
            if (x - size/2 <= 0 || x + size/2 >= getWidth())
                xspeed = -xspeed;
            if (y - size/2 <= 0 || y + size/2 >= getHeight())
                yspeed = -yspeed;
        }
    }
}
