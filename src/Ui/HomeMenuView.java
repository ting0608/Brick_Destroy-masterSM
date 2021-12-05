/*package Ui;

import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

public class HomeMenuView extends JComponent {
    private GameFrame owner;

    private static final int BORDER_SIZE = 5;
    private static final float[] DASHES = {12,6};

    private static final String GREETINGS = "Welcome to";
    private static final String GAME_TITLE = "Brick Destroy Game";
    private static final String CREDITS = "Version 2.1";


    private static final Color BORDER_COLOR = new Color(200,8,21); //
    private static final Color DASH_BORDER_COLOR = new  Color(255, 216, 0);//
    private static final Color TEXT_COLOR = new Color(200, 255, 254);//
    private static final Color CLICKED_BUTTON_COLOR = Color.GREEN;
    private static final Color CLICKED_TEXT = Color.WHITE;

    private final Rectangle menuFace;

    private BasicStroke borderStoke;
    private BasicStroke borderStoke_noDashes;

    private Font greetingsFont;
    private Font gameTitleFont;
    private Font creditsFont;
    private Font buttonFont;

    private Image background;
    private static final String START_TEXT = "START";
    private static final String MENU_TEXT = "EXIT";
    private static final String Info_TEXT = "INSTRUCTION";


    public HomeMenuView(GameFrame owner,Dimension area){
        menuFace = new Rectangle(new Point(0,0),area);
        borderStoke = new BasicStroke(BORDER_SIZE,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,0,DASHES,0);
        borderStoke_noDashes = new BasicStroke(BORDER_SIZE,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);

        greetingsFont = new Font("Noto Mono",Font.PLAIN,30);
        gameTitleFont = new Font("Noto Mono",Font.BOLD,40);
        creditsFont = new Font("Destroy",Font.PLAIN,15);
        buttonFont = new Font("Monospaced",Font.PLAIN,startButton.height-4);

    }

    public void paint(Graphics g){
        drawMenu((Graphics2D)g);
    }

    public void drawMenu(Graphics2D g2d){

        drawContainer(g2d);


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

        //g2d.setColor(prev);
    }

    private void drawBackground(Graphics2D g2d){
        background = Toolkit.getDefaultToolkit().getImage("Images/wall.png");
        g2d.drawImage(background,0,0, getWidth(), getHeight(),this);
    }

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

}
*/
