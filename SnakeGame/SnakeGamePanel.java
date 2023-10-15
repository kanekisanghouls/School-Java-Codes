import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.Random;

@SuppressWarnings("serial")
public class SnakeGamePanel extends JPanel implements ActionListener {

    static final int SCREEN_WIDTH = 500;
    static final int SCREEN_HEIGHT = 500;
    static final int UNIT_SIZE = 5;
    static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / (UNIT_SIZE * UNIT_SIZE);
    static final int DELAY = 70;
    final int x[] = new int[GAME_UNITS];
    final int y[] = new int[GAME_UNITS];
    int bodyParts = 6;
    int objectivesEaten;
    int objectiveX;
    int objectiveY;
    char direction = 'R';
    boolean running = false;
    Timer timer;
    Random random;
    boolean gamePaused = false;
    boolean gameStarted = false; // Added for game start

    SnakeGamePanel() {
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }

    public void startGame() {
        newObjective();
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!gameStarted) {
            showInstructions(g); // Show instructions when the game hasn't started
        } else if (running) {
            draw(g);
        } else {
            gameOver(g);
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.red);
        g.fillOval(objectiveX, objectiveY, UNIT_SIZE, UNIT_SIZE);

        for (int i = 0; i < bodyParts; i++) {
            if (i == 0) {
                g.setColor(Color.yellow);
                g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
            } else {
                g.setColor(Color.white);
                g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
            }
        }
        g.setColor(Color.white);
        g.setFont(new Font("Agency FB", Font.BOLD, 25));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Score: " + objectivesEaten, (SCREEN_WIDTH - metrics.stringWidth("Score: " + objectivesEaten)) / 2, g.getFont().getSize());
    }

    public void showInstructions(Graphics g) {
        g.setColor(Color.white);
        g.setFont(new Font("Agency FB", Font.BOLD, 20));
        FontMetrics metrics = getFontMetrics(g.getFont());
        String[] instructions = {
                "WELCOME TO SNAKE GAME!", "\n",
                "Use Arrow keys (or WASD) to control the snake.",
                "Collect the red objectives to grow and score points.",
                "Avoid collisions with the walls and the snake's body.", "\n",
                "Press ENTER to start the game.",
            };
        int lineHeight = g.getFont().getSize();
        int startY = SCREEN_HEIGHT / 2 - (lineHeight * instructions.length) / 2;
        for (int i = 0; i < instructions.length; i++) {
            int textX = (SCREEN_WIDTH - metrics.stringWidth(instructions[i])) / 2;
            int textY = startY + i * lineHeight;
            g.drawString(instructions[i], textX, textY);
        }
    }

    public void newObjective() {
        objectiveX = random.nextInt((int) (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
        objectiveY = random.nextInt((int) (SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
    }

    public void move() {
        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        switch (direction) {
            case 'U':
                y[0] = y[0] - UNIT_SIZE;
                break;
            case 'D':
                y[0] = y[0] + UNIT_SIZE;
                break;
            case 'L':
                x[0] = x[0] - UNIT_SIZE;
                break;
            case 'R':
                x[0] = x[0] + UNIT_SIZE;
                break;
        }
    }

    public void checkObjective() {
        if ((x[0] == objectiveX) && (y[0] == objectiveY)) {
            bodyParts++;
            objectivesEaten++;
            newObjective();
        }
    }

    public void checkCollisions() {
        for (int i = bodyParts; i > 0; i--) {
            if ((x[0] == x[i]) && (y[0] == y[i])) {
                running = false;
            }
        }
        if (x[0] < 0 || x[0] > SCREEN_WIDTH || y[0] < 0 || y[0] > SCREEN_HEIGHT) {
            running = false;
        }
        if (!running) {
            timer.stop();
        }
    }

    public void gameOver(Graphics g) {
        draw(g); // Display the final state
        g.setColor(Color.white);

        Font gameOverFont = new Font("Agency FB", Font.BOLD, 75);
        FontMetrics metrics2 = getFontMetrics(gameOverFont);
        String gameOverText = "GAME OVER";
        int xGameOver = (SCREEN_WIDTH - metrics2.stringWidth(gameOverText)) / 2;
        int yGameOver = (SCREEN_HEIGHT - metrics2.getHeight()) / 2;
        g.setFont(gameOverFont);
        g.drawString(gameOverText, xGameOver, yGameOver);

        g.setFont(new Font("Agency FB", Font.BOLD, 20));
        FontMetrics metrics3 = getFontMetrics(g.getFont());
        g.setColor(Color.green);

        String playAgainText = "Press SPACE to Play Again";
        int xPlayAgain = (SCREEN_WIDTH - metrics3.stringWidth(playAgainText)) / 2;
        g.drawString(playAgainText, xPlayAgain, yGameOver + metrics2.getHeight() + 50);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            checkObjective();
            checkCollisions();
        }
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if (!gameStarted) {
                if (key == KeyEvent.VK_ENTER) {
                    gameStarted = true;
                }
            } else if (running) {
                if ((key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) && direction != 'R') {
                    direction = 'L';
                }
                if ((key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) && direction != 'L') {
                    direction = 'R';
                }
                if ((key == KeyEvent.VK_UP || key == KeyEvent.VK_W) && direction != 'D') {
                    direction = 'U';
                }
                if ((key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) && direction != 'U') {
                    direction = 'D';
                }
            } else if (key == KeyEvent.VK_SPACE) {
                restartGame();
            }
        }
    }

    private void restartGame() {
        bodyParts = 6;
        objectivesEaten = 0;
        direction = 'R';
        running = true;
        gameStarted = false; // Reset the game start status

        for (int i = 0; i < bodyParts; i++) {
            x[i] = 0;
            y[i] = 0;
        }

        newObjective();
        timer.start();
        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Snake Game");
        SnakeGamePanel gamePanel = new SnakeGamePanel();

        frame.add(gamePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
