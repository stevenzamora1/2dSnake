package snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.Timer;


import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Gameplay extends JPanel implements KeyListener, ActionListener {

    private int[] snakeXLength = new int[750];
    private int[] snakeYLength = new int[750];

    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;

    private boolean count = true;
    private int  ballCounter = 0;
    private int bonusPointCounter = 0;
    private boolean bonus = false;

    private ImageIcon titleImage;

    private ImageIcon leftmouth;
    private ImageIcon rightmouth;
    private ImageIcon upmouth;
    private ImageIcon downmouth;
    private ImageIcon snakeImage;


    private int[] enemyXpos = {25, 50, 75, 100, 125, 150, 175, 200, 225, 250, 275, 300,
            325, 350, 375, 400, 425, 450, 475, 500, 525, 550, 575, 600,
            625, 650, 675, 700, 725, 750, 775, 800, 825, 850};

    private int[] enemyYpos = { 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325,
            350, 375, 400, 425, 450, 475, 500, 525, 550, 575, 600, 625};

    private int[] enemy2Xpos = { 50, 75, 150, 175,  225, 250, 275, 300,
             350, 375, 400, 425, 450, 475, 500, 525, 550, 575, 600,
             625, 650, 675, 700, 750, 775, 800, };

    private int[] enemy2Ypos = {  125, 150, 175, 200, 225, 250, 275, 300, 325,
            350, 375, 400, 425, 450, 475, 500, 525, 550, 575, };

    private ImageIcon enemyImage;
    private ImageIcon enemyImage2;

    private Random random = new Random();

    private int xpos = random.nextInt(34);
    private int ypos = random.nextInt(23);

    private int x2pos = random.nextInt(26);
    private int y2pos = random.nextInt(18);

    private int foodCount = 0;

    private int score = 0;
    private int finalScore, highScore, finalSnakeLength, maxSnakeLength;

    private int lengthOfSnake = 3;
    private int moves = 0;
    private boolean gameOver, started;

    private Timer timer;

    //Make the game faster or slower
    private int delay = 60;




    public Gameplay() {


        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();



    }

    public void paint(Graphics g) {

        if(moves == 0) {

            snakeXLength[2] = 50;
            snakeXLength[1] = 75;
            snakeXLength[0] = 100;

            snakeYLength[2] = 100;
            snakeYLength[1] = 100;
            snakeYLength[0] = 100;

        }




        //Draw THE title and image border
        g.setColor(Color.white);
        g.drawRect(24, 10, 851, 55);


        //Draw the title image
        titleImage = new ImageIcon(getClass().getClassLoader().
                getResource("snaketitle.jpg"));
        titleImage.paintIcon(this, g, 25, 11);

        //Draw Border
        g.setColor(Color.WHITE);
        g.drawRect(24, 75, 851, 577);

        //Draw background for gameplay
        g.setColor(Color.black);
        g.fillRect(25, 75, 850, 577);

        //Draw Score
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.PLAIN, 14));
        g.drawString("Score " + score, 780, 30);

        //Draw length of snake
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.PLAIN, 14));
        g.drawString("Length: " + lengthOfSnake, 780, 50);



        //Starts off with the right mouth
        rightmouth = new ImageIcon(getClass().getClassLoader().getResource("rightmouth.png"));
        rightmouth.paintIcon(this, g, snakeXLength[0], snakeYLength[0]);



        for(int a = 0; a< lengthOfSnake; a++) {

            if(a == 0 && right) {


                rightmouth = new ImageIcon(getClass().getClassLoader().getResource("rightmouth.png"));
                rightmouth.paintIcon(this, g, snakeXLength[a], snakeYLength[a]);



            }


            if(a == 0 && left) {


                leftmouth = new ImageIcon(getClass().getClassLoader().getResource("leftmouth.png"));
                leftmouth.paintIcon(this, g, snakeXLength[a], snakeYLength[a]);



            }

            if(a == 0 && up) {


                upmouth = new ImageIcon(getClass().getClassLoader().getResource("upmouth.png"));
                upmouth.paintIcon(this, g, snakeXLength[a], snakeYLength[a]);



            }
            if(a == 0 && down) {


                downmouth = new ImageIcon(getClass().getClassLoader().getResource("downmouth.png"));
                downmouth.paintIcon(this, g, snakeXLength[a], snakeYLength[a]);



            }

            if(a != 0 ) {
                snakeImage = new ImageIcon(getClass().getClassLoader().getResource("snakeimage2.png"));
                snakeImage.paintIcon(this, g, snakeXLength[a], snakeYLength[a]);

            }

        }


        //Regular ball
        enemyImage = new ImageIcon(getClass().getClassLoader().getResource("enemy.png"));

        if((enemyXpos[xpos] == snakeXLength[0] && enemyYpos[ypos] == snakeYLength[0])) {


            score += 5;
            finalScore = score;
            lengthOfSnake++;
            finalSnakeLength = lengthOfSnake;

            if(finalScore > highScore) {
                highScore = finalScore;
            }

            if(finalSnakeLength > maxSnakeLength) {
                maxSnakeLength = finalSnakeLength;
            }

            xpos = random.nextInt(34);
            ypos = random.nextInt(23);

        }

        enemyImage.paintIcon(this, g, enemyXpos[xpos], enemyYpos[ypos]);


        //Second Ball that will count as extra points for the player
        enemyImage2 = new ImageIcon(getClass().getClassLoader().getResource("bonus.png"));

        if((enemy2Xpos[x2pos] == snakeXLength[0] && enemy2Ypos[y2pos] == snakeYLength[0])) {


            score += 20;




            finalScore = score;
            lengthOfSnake++;
            finalSnakeLength = lengthOfSnake;
            count = true;
            bonus = true;

            if(finalScore > highScore) {
                highScore = finalScore;
            }

            if(finalSnakeLength > maxSnakeLength) {
                maxSnakeLength = finalSnakeLength;
            }

            x2pos = random.nextInt(26);
            y2pos = random.nextInt(18);

        }
        if(bonus == true){

            g.setColor(Color.white);

            g.setFont(new Font("Arial", Font.PLAIN, 14));
            g.drawString("Bonus Point!", 600, 430);
            bonusPointCounter++;

            if(bonusPointCounter == 35){
                bonus = false;
            }
        }

        //This count the amount of time needed before the ball can be on the screen
        if(count == true) {

            foodCount++;
            System.out.println(foodCount);

        }

        if(foodCount % 100 == 0) {

            enemyImage2.paintIcon(this, g, enemy2Xpos[x2pos], enemy2Ypos[y2pos]);
            count = false;


            }


            //This makes the ball be on screen for a limited amount of time
            if(count == false){

                ballCounter++;

                System.out.println("second count " + ballCounter);

                if(ballCounter == 50){

                    count = true;

                }


         }

        for(int b = 1; b< lengthOfSnake; b++) {

            if(snakeXLength[b] == snakeXLength[0] && snakeYLength[b] == snakeYLength[0]) {

                right = false;
                left = false;
                up = false;
                down = false;


                g.setColor(Color.white);
                g.setFont(new Font("Arial", Font.BOLD, 50));
                g.drawString("Game Over ", 300, 300);

                g.setFont(new Font("Arial", Font.BOLD, 20));
                g.drawString("Space Bar to restart ", 340, 350);
            }


        }

        if(gameOver) {
            right = false;
            left = false;
            up = false;
            down = false;

            g.setColor(Color.white);
            g.setFont(new Font("Arial", Font.BOLD, 50));
            g.drawString("Game Over ", 300, 300);

            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("Space Bar to restart ", 340, 350);


            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("Final score is : " + String.valueOf(finalScore), 375, 400);
            g.drawString("Snake length is : " + String.valueOf(lengthOfSnake), 375, 450);
            g.drawString("Highscore is : " + String.valueOf(highScore), 375, 500);
            g.drawString("Greatest snake length is : " + String.valueOf(maxSnakeLength), 375, 550);

            gameOver = false;

        }

        if(!started) {
            g.setColor(Color.white);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString("RIGHT ARROW KEY TO START", 200, 350);
            started = true;
        }



        g.dispose();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if(right) {
            for(int r = lengthOfSnake - 1; r >= 0; r--) {

                snakeYLength[r+1] = snakeYLength[r];


            }
            for(int r = lengthOfSnake; r >= 0; r--) {

                if(r == 0) {
                    snakeXLength[r] = snakeXLength[r] + 25;
                }

                else {
                    snakeXLength[r] = snakeXLength[r - 1];
                }

                if(snakeXLength[r] > 849) {


                    //snakeXLength[r] = 25;  Will use for another gamemode.
                    gameOver = true;
                }

            }
            repaint();
        }

        if(left) {
            for(int r = lengthOfSnake - 1; r >= 0; r--) {

                snakeYLength[r+1] = snakeYLength[r];


            }
            for(int r = lengthOfSnake; r >= 0; r--) {

                if(r == 0) {
                    snakeXLength[r] = snakeXLength[r] - 25;
                }

                else {
                    snakeXLength[r] = snakeXLength[r - 1];
                }

                if(snakeXLength[r] < 26) {

                    //snakeXLength[r] = 850; Will use for another gamemode

                    gameOver = true;
                }

            }
            repaint();
        }

        if(up) {
            for(int r = lengthOfSnake - 1; r >= 0; r--) {

                snakeXLength[r+1] = snakeXLength[r];


            }
            for(int r = lengthOfSnake; r >= 0; r--) {

                if(r == 0) {
                    snakeYLength[r] = snakeYLength[r] - 25;
                }

                else {
                    snakeYLength[r] = snakeYLength[r - 1];
                }

                if(snakeYLength[r] < 76) {

                    //snakeXLength[r] = 625; Will use for another gamemode

                    gameOver = true;
                }

            }
            repaint();
        }

        if(down) {
            for(int r = lengthOfSnake - 1; r >= 0; r--) {

                snakeXLength[r+1] = snakeXLength[r];


            }
            for(int r = lengthOfSnake; r >= 0; r--) {

                if(r == 0) {
                    snakeYLength[r] = snakeYLength[r] + 25;
                }

                else {
                    snakeYLength[r] = snakeYLength[r - 1];
                }

                if(snakeYLength[r] > 624) {

                    //snakeYLength[r] = 75; Will use for another gamemode

                    gameOver = true;
                }

            }
            repaint();
        }




    }

    @Override
    public void keyTyped(KeyEvent e) {


    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE) {


            moves= 0;
            score = 0;
            lengthOfSnake = 3;
            repaint();
        }


        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {


            moves++;
            right = true;

            if(!left) {
                right = true;


            }

            else {
                right = false;
                left = true;
            }
            up = false;
            down = false;
        }


        if(e.getKeyCode() == KeyEvent.VK_LEFT) {

            moves++;
            left = true;

            if(!right) {
                left = true;


            }

            else {

                right = true;
                left = false;
            }
            up = false;
            down = false;

        }


        if(e.getKeyCode() == KeyEvent.VK_UP) {


            moves++;
            up = true;

            if(!down) {
                up = true;


            }

            else {
                down = true;
                up = false;
            }
            right = false;
            left = false;
        }

        if(e.getKeyCode() == KeyEvent.VK_DOWN) {


            moves++;
            down = true;

            if(!up) {
                down = true;


            }

            else {
                down= false;
                up = true;
            }
            left = false;
            right = false;
        }






    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }

}
