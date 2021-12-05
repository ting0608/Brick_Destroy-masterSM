package Brick;

import Brick.Brick;

import java.awt.*;
import java.awt.Point;
import java.awt.geom.Point2D;


/**
 * Created by a 189cm lengzaii, tingcc.
 * @author tingcc
 * @since 11/11/2021
 */
public class ClayBrick extends Brick {

    private static final String NAME = "Clay Brick";
    private static final Color DEF_INNER = new Color(178, 34, 34).darker();
    private static final Color DEF_BORDER = Color.GRAY;
    private static final int CLAY_STRENGTH = 1;
    public int GainScore = 1;


    /**
     * @param point location
     * @param size size of dimension
     */
    public ClayBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,CLAY_STRENGTH);
    }

    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }

    /**
     * @return brickFace, which means return different bricks
     */
    @Override
    public Shape getBrick() {
        return super.brickFace;
    }

    /**
     * @param point set impact point, or we could say break point
     * @param dir   set the crack direction
     * @return if broken or not, if broken then GainScore
     */
    @Override
    public boolean setImpact(Point2D point, int dir) {
        if(super.isBroken())
            return false;
        super.impact(GainScore);
        return true;
    }


}