package Ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Info extends JComponent implements MouseListener, MouseMotionListener, KeyListener {
    private Image image1;
    private GameFrame owner;

    //tbc wanna add or not, depends on time
    private Rectangle NextButton;
    private boolean NextClicked;


    public Info(GameFrame owner) {

        this.initialize();
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.owner = owner;
        this.addKeyListener(this);
    }

    public void initialize() {
        this.setPreferredSize(new Dimension(720, 480));
        this.setVisible(true);

    }

    public void paint(Graphics g) {
        img((Graphics) g);
    }


    public void img(Graphics g) {
        image1 = Toolkit.getDefaultToolkit().getImage("Images/infoback2.png");
        g.drawImage(image1, 0, 0, getWidth(), getHeight(), this);

    }




    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {


    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyChar()) {
            case (char) KeyEvent.VK_ESCAPE:
                owner.enableHomeMenu();
                break;

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
