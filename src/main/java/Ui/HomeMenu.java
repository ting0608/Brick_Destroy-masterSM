/*
 *  Brick Destroy - A simple Arcade video game
 *   Copyright (C) 2017  Filippo Ranza
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package Ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;


/**
 * Created by a 189cm lengzaii, tingcc.
 * @author tingcc
 * @since 11/11/2021
 */
public class HomeMenu extends JComponent implements MouseListener, MouseMotionListener {

    private static final String GREETINGS = "Welcome to";
    private static final String GAME_TITLE = "Brick Destroy Game";
    private static final String CREDITS = "Version 2.1";
    private static final String START_TEXT = "START";
    private static final String MENU_TEXT = "EXIT";
    private static final String Info_TEXT = "INSTRUCTION";

    private static final Color BORDER_COLOR = new Color(200,8,21); //
    private static final Color DASH_BORDER_COLOR = new  Color(255, 216, 0);//
    private static final Color TEXT_COLOR = new Color(200, 255, 254);//
    private static final Color CLICKED_BUTTON_COLOR = Color.GREEN;
    private static final Color CLICKED_TEXT = Color.WHITE;
    private static final int BORDER_SIZE = 5;
    private static final float[] DASHES = {12,6};

    private final Rectangle menuFace;
    private final Rectangle startButton;
    private final Rectangle menuButton;
    private final Rectangle InfoButton;

    private BasicStroke borderStoke;
    private BasicStroke borderStoke_noDashes;

    private Font greetingsFont;
    private Font gameTitleFont;
    private Font creditsFont;
    private Font buttonFont;

    private GameFrame owner;

    private boolean startClicked;
    private boolean menuClicked;
    private boolean InfoClicked;

    private Image background;

    /**
     * @param owner is the gameFrame
     * @param area is the button dimension
     * also set the button size and some font here
     */
    public HomeMenu(GameFrame owner,Dimension area){

        this.setFocusable(true);
        this.requestFocusInWindow();

        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        this.owner = owner;

        menuFace = new Rectangle(new Point(0,0),area);
        this.setPreferredSize(area);

        Dimension btnDim = new Dimension(area.width / 3, area.height / 12);
        startButton = new Rectangle(btnDim);
        menuButton = new Rectangle(btnDim);
        InfoButton = new Rectangle(btnDim);

        borderStoke = new BasicStroke(BORDER_SIZE,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,0,DASHES,0);
        borderStoke_noDashes = new BasicStroke(BORDER_SIZE,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);

        greetingsFont = new Font("Noto Mono",Font.PLAIN,30);
        gameTitleFont = new Font("Noto Mono",Font.BOLD,40);
        creditsFont = new Font("Destroy",Font.PLAIN,15);
        buttonFont = new Font("Monospaced",Font.PLAIN,startButton.height-4);


    }


    /**
     * @param g is graphics, used to draw
     */
    public void paint(Graphics g){
        drawMenu((Graphics2D)g);
    }


    /**
     * @param g2d graphics 2d
     * here to drawMenu, also call drawText and DrawButton to finalize the menu page
     */
    public void drawMenu(Graphics2D g2d){

        drawContainer(g2d);

        /*
        all the following method calls need a relative
        painting directly into the HomeMenu rectangle,
        so the translation is made here so the other methods do not do that.
         */
        Color prevColor = g2d.getColor();
        Font prevFont = g2d.getFont();

        double x = menuFace.getX();
        double y = menuFace.getY();

        g2d.translate(x,y);

        //methods calls
        drawText(g2d);
        drawButton(g2d);
        //end of methods calls

        g2d.translate(-x,-y);
        g2d.setFont(prevFont);
        g2d.setColor(prevColor);
    }

    /**
     * @param g2d
     * drawBackground is used to get image in file and set it as background image
     */
    private void drawBackground(Graphics2D g2d){
        background = Toolkit.getDefaultToolkit().getImage("Images/wall.png");
        g2d.drawImage(background,0,0, getWidth(), getHeight(),this);
    }

    private void drawContainer(Graphics2D g2d){
        drawBackground(g2d);

        Stroke tmp = g2d.getStroke();

        g2d.setStroke(borderStoke_noDashes);
        g2d.setColor(DASH_BORDER_COLOR);
        g2d.draw(menuFace);

        g2d.setStroke(borderStoke);
        g2d.setColor(BORDER_COLOR);
        g2d.draw(menuFace);
        g2d.setStroke(tmp);
    }

    /**
     * @param g2d
     * drawText used to draw out text, and those text font set in public class menu
     */
    private void drawText(Graphics2D g2d){

        g2d.setColor(TEXT_COLOR);

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D greetingsRect = greetingsFont.getStringBounds(GREETINGS,frc);
        Rectangle2D gameTitleRect = gameTitleFont.getStringBounds(GAME_TITLE,frc);
        Rectangle2D creditsRect = creditsFont.getStringBounds(CREDITS,frc);

        int sX,sY;

        sX = (int)(menuFace.getWidth() - greetingsRect.getWidth()) / 2;
        sY = (int)(menuFace.getHeight() / 4);

        g2d.setFont(greetingsFont);
        g2d.drawString(GREETINGS,sX,sY);

        sX = (int)(menuFace.getWidth() - gameTitleRect.getWidth()) / 2;
        sY += (int) gameTitleRect.getHeight() * 1.1;//add 10% of String height between the two strings

        g2d.setFont(gameTitleFont);
        g2d.drawString(GAME_TITLE,sX,sY);

        sX = (int)(menuFace.getWidth() - creditsRect.getWidth()) / 2;
        sY += (int) creditsRect.getHeight() * 1.1;

        g2d.setFont(creditsFont);
        g2d.drawString(CREDITS,sX,sY);


    }

    /**
     * @param g2d
     * drawButton to draw all the start, info and exit button(in rectangle shape)
     * x and y location of button also been set here
     */
    private void drawButton(Graphics2D g2d){

        FontRenderContext frc = g2d.getFontRenderContext();
        Rectangle2D txtRect = buttonFont.getStringBounds(START_TEXT,frc);
        Rectangle2D mTxtRect = buttonFont.getStringBounds(MENU_TEXT,frc);
        Rectangle2D nTxtRect = buttonFont.getStringBounds(Info_TEXT,frc);
        g2d.setFont(buttonFont);
        //start location and button setup
        int x = (menuFace.width - startButton.width) / 2;
        int y =(int) ((menuFace.height - startButton.height) * 0.6);

        startButton.setLocation(x,y);

        x = (int)(startButton.getWidth() - txtRect.getWidth()) / 2;
        y = (int)(startButton.getHeight() - txtRect.getHeight()) / 2;

        x += startButton.x;
        y += startButton.y + (startButton.height * 0.9);

        if(startClicked){
            Color tmp = g2d.getColor();
            g2d.setColor(CLICKED_BUTTON_COLOR);
            g2d.draw(startButton);
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(START_TEXT,x,y);
            g2d.setColor(tmp);
        }
        else{
            g2d.draw(startButton);
            g2d.drawString(START_TEXT,x,y);
        }


        //Info button
        x = startButton.x;
        y = startButton.y;

        y *= 1.2;

        InfoButton.setLocation(x,y);

        x = (int)(InfoButton.getWidth() - nTxtRect.getWidth()) / 2;
        y = (int)(InfoButton.getHeight() - nTxtRect.getHeight()) / 2;

        x += InfoButton.x;
        y += InfoButton.y + (startButton.height * 0.9);

        if(InfoClicked){
            Color tmp = g2d.getColor();

            g2d.setColor(CLICKED_BUTTON_COLOR);
            g2d.draw(InfoButton);
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(Info_TEXT,x,y);
            g2d.setColor(tmp);
        }
        else{
            g2d.draw(InfoButton);
            g2d.drawString(Info_TEXT,x,y);
        }

        //menu location and button setup
        x = startButton.x;
        y = startButton.y;

        y *= 1.4;

        menuButton.setLocation(x,y);

        x = (int)(menuButton.getWidth() - mTxtRect.getWidth()) / 2;
        y = (int)(menuButton.getHeight() - mTxtRect.getHeight()) / 2;

        x += menuButton.x;
        y += menuButton.y + (startButton.height * 0.9);

        if(menuClicked){
            Color tmp = g2d.getColor();

            g2d.setColor(CLICKED_BUTTON_COLOR);
            g2d.draw(menuButton);
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(MENU_TEXT,x,y);
            g2d.setColor(tmp);
        }
        else{
            g2d.draw(menuButton);
            g2d.drawString(MENU_TEXT,x,y);
        }

    }


    /**
     * @param mouseEvent
     * if mouse clicked those button(start, info and exit), do the relevant action
     */
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(startButton.contains(p)){
           owner.enableGameBoard();

        }
        else if(InfoButton.contains(p)){
            //owner.enableInfo(); , make a new page which contain info
            owner.enableInfo();

        }


        else if(menuButton.contains(p)){
            System.out.println("Goodbye " + System.getProperty("user.name"));
            System.exit(0);
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(startButton.contains(p)){
            startClicked = true;
            RepaintStart();

        }


        else if(InfoButton.contains(p)){
            InfoClicked = true;
            RepaintInfo();
        }

        else if(menuButton.contains(p)){
            menuClicked = true;
            RepaintMenu();
        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if(startClicked ){
            startClicked = false;
            RepaintStart();
        }


        else if(InfoClicked){
            InfoClicked = false;
            RepaintInfo();
        }

        else if(menuClicked){
            menuClicked = false;
            RepaintMenu();
        }
    }

    //these are required for mouse listener
    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }


    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    /**
     * @param mouseEvent
     * hand cursor used to make the cursor become hand when moved to clickable button
     */
    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(startButton.contains(p) || menuButton.contains(p) || InfoButton.contains(p))
            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        else
            this.setCursor(Cursor.getDefaultCursor());

    }
    //make 2 separate methods to avoid repeating same method in mouse_pressed/mouse_released, and also allow user to modify easily(once)
    public void RepaintStart(){
        repaint(startButton.x,startButton.y,startButton.width+1,startButton.height+1);
    }

    public void RepaintInfo(){
        repaint(InfoButton.x,InfoButton.y,InfoButton.width+1,InfoButton.height+1);
    }

    public void RepaintMenu(){
        repaint(menuButton.x,menuButton.y,menuButton.width+1,menuButton.height+1);

    }
}
