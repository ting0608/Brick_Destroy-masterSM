package Ui;

import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.*;

class GameBoardTest {

    @Test
    void getHighScore() throws IOException {
        GameFrame owner = new GameFrame();
        GameBoard gameBoard = new GameBoard(owner);
        File file = new File("Highscore.txt");
        FileWriter w = new FileWriter(file);
        PrintWriter p = new PrintWriter(w);
        p.println("ting:10");
        p.close();

        assertEquals("ting:10", gameBoard.GetHighScore());
    }

    @Test
    void checkScore() {
    }
}