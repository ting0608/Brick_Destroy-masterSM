package Brick;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class CementBrickTest {

    @Test
    void makeBrickFace() {
    }

    @Test
    void setImpact() {
        CementBrick cement = new CementBrick(new Point(), new Dimension(0,0));
        cement.impact(2);
        assertFalse(cement.isBroken());

    }

    @Test
    void getBrick() {
    }

    @Test
    void repair() {
    }
}