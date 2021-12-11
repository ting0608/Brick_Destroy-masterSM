package Brick;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class ClayBrickTest {

    @Test
    void setImpact() {
        ClayBrick clay = new ClayBrick(new Point(), new Dimension(0,0));
        clay.impact(1);
        assertTrue(clay.isBroken());

    }
}