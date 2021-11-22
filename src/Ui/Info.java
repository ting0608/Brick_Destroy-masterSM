package Ui;

import javax.swing.*;
import java.awt.*;

public class Info {

    JFrame frame = new JFrame();
    JLabel label = new JLabel("");

    Info(){

        label.setBounds(0,0,100,50);
        label.setFont(new Font(null,Font.PLAIN,25));

        frame.add(label);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,350);
        frame.setLayout(null);
        frame.setLocation(520,250);
        frame.setVisible(true);
    }
}
