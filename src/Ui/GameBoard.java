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
import java.awt.event.*;
import java.awt.font.FontRenderContext;
import java.io.*;

import test.*;
import Brick.*;
import Config.*;


public class GameBoard extends JComponent implements KeyListener,MouseListener,MouseMotionListener {

    private static final String CONTINUE = "Continue";
    private static final String RESTART = "Restart";
    private static final String HOME = "Home";
    private static final String EXIT = "Exit";
    private static final String PAUSE = "Pause Menu";
    private static final int TEXT_SIZE = 30;
    private static final Color MENU_COLOR = new Color(0,255,0);


    private static final int DEF_WIDTH = 600;
    private static final int DEF_HEIGHT = 450;
    private static final Color BG_COLOR = Color.WHITE;

    private Timer gameTimer;
    private wallConfig wallConfig;
    private String message;

    private boolean showPauseMenu;

    private Font menuFont;
    private Font messageFont;
    private Rectangle continueButtonRect;
    private Rectangle exitButtonRect;
    private Rectangle restartButtonRect;
    private Rectangle HomeButtonRect;
    private int strLen;
    private  String highscore = "";
    private int score;


    private DebugConsole debugConsole;
    private GameFrame owner;
    private GameBoard board;
    private String StrHighscore;

    public GameBoard(GameFrame owner){
        super();
        this.owner = owner;
        strLen = 0;
        showPauseMenu = false;

        menuFont = new Font("Destroy",Font.PLAIN,TEXT_SIZE);
        messageFont = new Font("Monospaced",Font.BOLD,18);

        this.initialize();
        message = "";
        wallConfig = new wallConfig(new Rectangle(0,0,DEF_WIDTH,DEF_HEIGHT),30,3,6/2,new Point(300,430));

        debugConsole = new DebugConsole(owner, wallConfig,this);
        //initialize the first level
        wallConfig.nextLevel();

        gameTimer = new Timer(10,e ->{
            wallConfig.move();
            wallConfig.findImpacts();
            message = String.format("Bricks: %d Score: %d Balls: %d", wallConfig.getBrickCount(), Brick.getScore() , wallConfig.getBallCount());

            StrHighscore = String.format("HighScore: "+GetHighScore());
            score = Brick.getScore();
            highscore = this.GetHighScore();

            if(wallConfig.isBallLost()){
                if(wallConfig.ballEnd()){
                    wallConfig.wallReset();
                    wallConfig.scoreReset();
                    message = "Game over";
                    CheckScore();

                }
                wallConfig.ballReset();
                gameTimer.stop();
            }
            else if(wallConfig.isDone()){
                if(wallConfig.hasLevel()){
                    message = "Go to Next Level";
                    gameTimer.stop();
                    //wallConfig.ballReset();
                    wallConfig.wallReset();
                    wallConfig.nextLevel();
                }
                else{
                    message = "ALL WALLS DESTROYED";
                    gameTimer.stop();
                    wallConfig.scoreReset();
                }
            }

            repaint();
        });

    }

    public String GetHighScore() {
        //format: hi : 100
        FileReader readFile;
        BufferedReader reader = null;
        try{
            readFile = new FileReader("highscore.txt");
            reader=  new BufferedReader(readFile);
            return reader.readLine();
        }
        catch (Exception e){
            return "0";
        }
        finally {
            try {
                if(reader!=null)
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void CheckScore(){
        int intHighScore;

        String parts[]=StrHighscore.split(":");

        if(GetHighScore() == "0" || GetHighScore() == null){
            intHighScore = 0;
        }
        else{
            intHighScore = Integer.parseInt(parts[2]);
        }
        if(score>intHighScore || GetHighScore()==null)
        {
            String name = JOptionPane.showInputDialog("You break the record. May i know your name?");
            StrHighscore = name+":"+score;

            File scoreFile = new File("Highscore.txt");
            if(!scoreFile.exists())
            {
                try{
                    scoreFile.createNewFile();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            FileWriter writeFile;
            BufferedWriter writer = null;
            try{
                writeFile = new FileWriter(scoreFile);
                writer = new BufferedWriter(writeFile);
                writer.write(this.StrHighscore);
            }
            catch (Exception e)
            {
                //errors

            }
            finally {
                try{
                    if (writer!=null)
                        writer.close();
                }
                catch(Exception e){}
            }

        }
    }



    private void initialize(){
        this.setPreferredSize(new Dimension(DEF_WIDTH,DEF_HEIGHT));
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }


    public void paint(Graphics g){

        Graphics2D g2d = (Graphics2D) g;
        clear(g2d);

        g2d.setColor(Color.BLUE);
        g2d.setFont(messageFont);
        g2d.drawString(message,150,225);
        g2d.drawString(highscore,150,250);
        drawBall(wallConfig.ball,g2d);

        for(Brick b : wallConfig.bricks)
            if(!b.isBroken())
                drawBrick(b,g2d);

        drawPlayer(wallConfig.player,g2d);

        if(showPauseMenu)
            drawMenu(g2d);

        Toolkit.getDefaultToolkit().sync();

    }


    private void clear(Graphics2D g2d){
        Color tmp = g2d.getColor();
        g2d.setColor(BG_COLOR);
        g2d.fillRect(0,0,getWidth(),getHeight());
        g2d.setColor(tmp);
    }

    private void drawBrick(Brick Brick,Graphics2D g2d){
        Color tmp = g2d.getColor();

        g2d.setColor(Brick.getInnerColor());
        g2d.fill(Brick.getBrick());

        g2d.setColor(Brick.getBorderColor());
        g2d.draw(Brick.getBrick());


        g2d.setColor(tmp);
    }

    private void drawBall(Ball ball,Graphics2D g2d){
        Color tmp = g2d.getColor();

        Shape s = ball.getBallFace();

        g2d.setColor(ball.getInnerColor());
        g2d.fill(s);

        g2d.setColor(ball.getBorderColor());
        g2d.draw(s);

        g2d.setColor(tmp);
    }

    private void drawPlayer(Player p,Graphics2D g2d){
        Color tmp = g2d.getColor();

        Shape s = p.getPlayerFace();
        g2d.setColor(Player.INNER_COLOR);
        g2d.fill(s);

        g2d.setColor(Player.BORDER_COLOR);
        g2d.draw(s);

        g2d.setColor(tmp);
    }

    private void drawMenu(Graphics2D g2d){
        obscureGameBoard(g2d);
        drawPauseMenu(g2d);
    }

    private void obscureGameBoard(Graphics2D g2d){

        Composite tmp = g2d.getComposite();
        Color tmpColor = g2d.getColor();

        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.55f);
        g2d.setComposite(ac);

        g2d.setColor(Color.BLACK);
        g2d.fillRect(0,0,DEF_WIDTH,DEF_HEIGHT);

        g2d.setComposite(tmp);
        g2d.setColor(tmpColor);
    }

    private void drawPauseMenu(Graphics2D g2d){
        Font tmpFont = g2d.getFont();
        Color tmpColor = g2d.getColor();

        g2d.setFont(menuFont);
        g2d.setColor(MENU_COLOR);

        if(strLen == 0){
            FontRenderContext frc = g2d.getFontRenderContext();
            strLen = menuFont.getStringBounds(PAUSE,frc).getBounds().width;
        }

        int x = (this.getWidth() - strLen) / 2;
        int y = this.getHeight() / 10;

        g2d.drawString(PAUSE,x,y);

        x = this.getWidth() / 8;
        y = this.getHeight() / 5;

        //continue button
        if(continueButtonRect == null){
            FontRenderContext frc = g2d.getFontRenderContext();
            continueButtonRect = menuFont.getStringBounds(CONTINUE,frc).getBounds();
            continueButtonRect.setLocation(x,y-continueButtonRect.height);
        }

        g2d.drawString(CONTINUE,x,y);

        y *= 2;

        //restart button
        if(restartButtonRect == null){
            restartButtonRect = (Rectangle) continueButtonRect.clone();
            restartButtonRect.setLocation(x,y-restartButtonRect.height);
        }

        g2d.drawString(RESTART,x,y);

        y *= 3.0/2;

        //Home button
        if(HomeButtonRect == null){
            HomeButtonRect = (Rectangle) continueButtonRect.clone();
            HomeButtonRect.setLocation(x,y-HomeButtonRect.height);
        }

        g2d.drawString(HOME,x,y);

        y *= 4.0/3;

        //exit button
        if(exitButtonRect == null){
            exitButtonRect = (Rectangle) continueButtonRect.clone();
            exitButtonRect.setLocation(x,y-exitButtonRect.height);
        }

        g2d.drawString(EXIT,x,y);


        g2d.setFont(tmpFont);
        g2d.setColor(tmpColor);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch(keyEvent.getKeyCode()){
            case KeyEvent.VK_A:
                wallConfig.player.moveLeft();
                break;
            case KeyEvent.VK_D:
                wallConfig.player.movRight();
                break;
            case KeyEvent.VK_ESCAPE:
                showPauseMenu = !showPauseMenu;
                repaint();
                gameTimer.stop();
                break;
            case KeyEvent.VK_SPACE:
                if(!showPauseMenu)
                    if(gameTimer.isRunning())
                        gameTimer.stop();
                    else
                        gameTimer.start();
                break;
            case KeyEvent.VK_F1:
                if(keyEvent.isAltDown() && keyEvent.isShiftDown())
                    debugConsole.setVisible(true);
            default:
                wallConfig.player.stop();
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        wallConfig.player.stop();
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(!showPauseMenu)
            return;
        if(continueButtonRect.contains(p)){
            showPauseMenu = false;
            repaint();
        }
        else if(restartButtonRect.contains(p)){
            message = "Restarting Game...";
            wallConfig.ballReset();
            wallConfig.wallReset();
            wallConfig.scoreReset();
            showPauseMenu = false;
            repaint();
        }

        else if(HomeButtonRect.contains(p)){

            owner.enableHomeMenu();
            showPauseMenu = false;

        }


        else if(exitButtonRect.contains(p)){
            System.exit(0);
        }

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(exitButtonRect != null && showPauseMenu) {
            if (exitButtonRect.contains(p) || continueButtonRect.contains(p)|| HomeButtonRect.contains(p) || restartButtonRect.contains(p))
                this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            else
                this.setCursor(Cursor.getDefaultCursor());
        }
        else{
            this.setCursor(Cursor.getDefaultCursor());
        }
    }

    public void onLostFocus(){
        gameTimer.stop();
        message = "Focus Lost";
        repaint();
    }

}