package Brick;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class SteelBrickTest {


    @Test
    void setImpact() {
        SteelBrick steel = new SteelBrick(new Point(), new Dimension(0,0));
        steel.impact(3);
        assertTrue(steel.isBroken());

    }

    @Test
    void impact() {

    }
}