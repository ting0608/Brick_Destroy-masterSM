package Ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


/**
 * Created by a 189cm lengzaii, tingcc.
 * @author tingcc
 * @since 11/11/2021
 */
public class Info extends JComponent implements MouseListener, MouseMotionListener, KeyListener {
    private Image image1;
    private GameFrame owner;

    //tbc wanna add or not, depends on time
    private Rectangle NextButton;
    private boolean NextClicked;


    /**
     * @param owner actually is come from GameFrame
     * use initialize to init the window
     */
    public Info(GameFrame owner) {

        this.initialize();
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.owner = owner;
        this.addKeyListener(this);
    }

    /**
     * set window size in initialize(), also make it visible
     */
    public void initialize() {
        this.setPreferredSize(new Dimension(720, 480));
        this.setVisible(true);

    }

    public void paint(Graphics g) {
        img((Graphics) g);
    }


    /**
     * @param g
     * use toolkit to call the info image out, then draw it with given width and height
     * btw i make the info page with photoshop first and call it in,
     * which make the project slightly clean (not using tons of icons and images in ide then doing adjustment)
     */
    public void img(Graphics g) {
        image1 = Toolkit.getDefaultToolkit().getImage("src/main/resources/Images/infoback2.png");
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
