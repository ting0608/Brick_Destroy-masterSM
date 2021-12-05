package Brick;

import Sound.Sound;
import User.*;

import java.awt.*;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.Random;

/**
 * Created by a 189cm lengzaii, tingcc.
 * @author tingcc
 * @since 11/11/2021
 */
abstract public class Brick  {
    public static final int MIN_CRACK = 1;
    public static final int DEF_CRACK_DEPTH = 1;
    public static final int DEF_STEPS = 35;

    public static final int UP_IMPACT = 100;
    public static final int DOWN_IMPACT = 200;
    public static final int LEFT_IMPACT = 300;
    public static final int RIGHT_IMPACT = 400;

    public static Random rnd;
    public static int Score = 0;

    private String name;
    Shape brickFace;

    private Color border;
    private Color inner;
    public int GainScore = 0;
    private int fullStrength;
    private int strength;

    private boolean broken;


    /**
     * @param name is brick's name
     * @param pos is position
     * @param size is size of bricks
     * @param border border of bricks
     * @param inner the inner fill of bricks
     * @param strength this one was like brick's health, when ball hit bricks, strength--
     */
    //here is brick() with constructors, which refactor to a new BrickBuild class
    Brick(String name, Point pos, Dimension size, Color border,Color inner,  int strength){
        rnd = new Random();
        broken = false;
        this.name = name;
        this.border = border;
        this.inner = inner;
        brickFace = makeBrickFace(pos,size);
        this.fullStrength = this.strength = strength;
    }


    protected abstract Shape makeBrickFace(Point pos,Dimension size);
    public abstract Shape getBrick();

    /**
     * @param point set impact point, or we could say break point
     * @param dir set the crack direction
     * @return broken will be return while the bricks get impact/break
     * GainScore to gain different score from different bricks
     */
    public  boolean setImpact(Point2D point , int dir){
        if(broken)
            return false;
        impact(GainScore);
        return  broken;
    }

    public Color getBorderColor(){
        return  border;
    }

    public Color getInnerColor(){
        return inner;
    }


    public final int findImpact(Ball b){
        if(broken)
            return 0;
        int out  = 0;
        if(brickFace.contains(b.right))
            out = LEFT_IMPACT;
        else if(brickFace.contains(b.left))
            out = RIGHT_IMPACT;
        else if(brickFace.contains(b.up))
            out = DOWN_IMPACT;
        else if(brickFace.contains(b.down))
            out = UP_IMPACT;
        return out;
    }

    /**
     * boolean to know that whether is broken or not
     * @return broken when confirm the is broken.
     */
    public final boolean isBroken(){
        return broken;
    }

    public void repair() {
        broken = false;
        strength = fullStrength;
    }

    /**
     * @param plusScore which contains the sum of score
     * while impact, strength will be minus 1 and score will plus into it
     * also put an if condition here that if the bricks broken, call new sound(), which play sound effect.
     */
    public void impact(int plusScore){
        strength--;
        broken = (strength == 0);
        if(broken){
            Score+=plusScore;
            new Sound();

        }

    }

    /**
     * @return score
     * getScore() to return the final sum score
     */
    public static int getScore(){
        return Score;
    }


}



