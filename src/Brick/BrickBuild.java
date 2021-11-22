package Brick;

import java.awt.*;

public class BrickBuild {
    private String name;
    private Point pos;
    private Dimension size;
    private Color border;
    private Color inner;
    private int strength;

    public BrickBuild setName(String name) {
        this.name = name;
        return this;
    }

    public BrickBuild setPos(Point pos) {
        this.pos = pos;
        return this;
    }

    public BrickBuild setSize(Dimension size) {
        this.size = size;
        return this;
    }

    public BrickBuild setBorder(Color border) {
        this.border = border;
        return this;
    }

    public BrickBuild setInner(Color inner) {
        this.inner = inner;
        return this;
    }

    public BrickBuild setStrength(int strength) {
        this.strength = strength;
        return this;
    }

    public Brick createBrick() {
        return new Brick(name, pos, size, border, inner, strength) {

            @Override
            protected Shape makeBrickFace(Point pos, Dimension size) {
                return null;
            }

            @Override
            public Shape getBrick() {
                return null;
            }
        };
    }
}

