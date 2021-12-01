package Brick;

import test.*;

import java.awt.*;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

/**
 * Created by a 189cm lengzaii in 2021.
 *
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

    public  boolean setImpact(Point2D point , int dir){
        if(broken)
            return false;
        impact(GainScore);
        return  broken;
    }

    public abstract Shape getBrick();

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

    public final boolean isBroken(){
        return broken;
    }

    public void repair() {
        broken = false;
        strength = fullStrength;
    }

    public void impact(int plusScore){
        strength--;
        broken = (strength == 0);
        if(broken){
            Score+=plusScore;
        }

    }

    public static int getScore(){
        return Score;
    }


}



