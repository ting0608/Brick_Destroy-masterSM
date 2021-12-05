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
package Config;

import Brick.wallConfig;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;


/**
 * Created by a 189cm lengzaii, tingcc.
 * @author tingcc
 * @since 11/11/2021
 */
public class DebugPanel extends JPanel {

    private static final Color DEF_BKG = Color.WHITE;


    private JButton skipLevel;
    private JButton resetBalls;


    private JSlider ballXSpeed;
    private JSlider ballYSpeed;

    private wallConfig wallConfig; //change the name from wall to wallConfig, since 'wall' feels meaningless

    /**
     * @param wallConfig needed since we need the nextLevel and resetBallCount method
     * skip level button to next level
     * reset balls button to reset ball count
     * 2 sliders used to adjust ball speed, 1 for ball's x-axis and 1 for ball's y-axis
     */
    public DebugPanel(wallConfig wallConfig){

        this.wallConfig = wallConfig;

        initialize();

        skipLevel = makeButton("Skip Level",e -> wallConfig.nextLevel());
        resetBalls = makeButton("Reset Balls",e -> wallConfig.resetBallCount());

        ballXSpeed = makeSlider(-4,4,e -> wallConfig.setBallXSpeed(ballXSpeed.getValue()));
        ballYSpeed = makeSlider(-4,4,e -> wallConfig.setBallYSpeed(ballYSpeed.getValue()));

        this.add(skipLevel);
        this.add(resetBalls);

        this.add(ballXSpeed);
        this.add(ballYSpeed);

    }

    /**
     * initialize debugPanel background
     */
    private void initialize(){
        this.setBackground(DEF_BKG);
        this.setLayout(new GridLayout(2,2));
    }

    /**
     * @param title button title can be found above
     * @param e, just a parameter for action listener
     * @return
     */
    private JButton makeButton(String title, ActionListener e){
        JButton out = new JButton(title);
        out.addActionListener(e);
        return  out;
    }

    /**
     * @param min minimum Slider could do
     * @param max maximum Slider could do
     * @param e
     * @return
     */
    private JSlider makeSlider(int min, int max, ChangeListener e){
        JSlider out = new JSlider(min,max);
        out.setMajorTickSpacing(1);
        out.setSnapToTicks(true);
        out.setPaintTicks(true);
        out.addChangeListener(e);
        return out;
    }

    public void setValues(int x,int y){
        ballXSpeed.setValue(x);
        ballYSpeed.setValue(y);
    }

}
