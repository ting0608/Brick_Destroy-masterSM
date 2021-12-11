package Brick;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;

/**
 * Created by a 189cm lengzaii, tingcc.
 * @author tingcc
 * @since 11/11/2021
 */
public class HardcoreBrick extends Brick{
    private static final String NAME = "Steel Brick";
    private static final Color DEF_INNER = new Color(185, 142, 255);
    private static final Color DEF_BORDER = Color.BLACK;
    private static final int Hardcore_STRENGTH = 1;
    private static final double Hardcore_PROBABILITY = 0.06;//super low probability to break

    private Random rnd;
    private Shape brickFace;
    public int GainScore = 10;

    public HardcoreBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,Hardcore_STRENGTH);
        rnd = new Random();
        brickFace = super.brickFace;
    }


    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }

    @Override
    public Shape getBrick() {
        return brickFace;
    }

    public  boolean setImpact(Point2D point , int dir){
        if(super.isBroken())
            return false;
        impact();
        return  super.isBroken();
    }

    public void impact(){
        if(rnd.nextDouble() < Hardcore_PROBABILITY){
            super.impact(GainScore);
        }
    }

}
