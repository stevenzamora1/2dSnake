package snake;

import java.awt.Color;
import java.awt.color.*;

import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {

        JFrame jframe = new JFrame();
        Gameplay gamePlay = new Gameplay();


        jframe.setBounds(10, 10, 905, 700);
        jframe.setBackground(Color.DARK_GRAY);
        jframe.setResizable(false);
        jframe.add(gamePlay);
        jframe.setVisible(true);

        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);




    }

}
