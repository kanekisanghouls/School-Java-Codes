// Mouse and Cheese Modified (LAB ACTIVITY 13)

import java.util.*;

public class CheeseMazeLast {
    private char[][] maze;
    private int mazeSize, mouseRow, mouseCol, cheeseRow, cheeseCol;
    private boolean hasPath = true;
    private char[][] path_grid;

    public CheeseMazeLast(int mazeSize) {
        this.mazeSize = mazeSize;
        this.maze = new char[mazeSize][mazeSize];
        this.path_grid = new char[mazeSize][mazeSize];
        initializeMaze();
    }

    public void initializeMaze() {
        Random random = new Random();

        for (int i = 0; i < mazeSize; i++) {
            for (int j = 0; j < mazeSize; j++) {
                if (random.nextDouble() < 0.3) {
                    maze[i][j] = '#';
                } else {
                    maze[i][j] = ' ';
                }
                path_grid[i][j] = ' ';
            }
        }

        mouseRow = random.nextInt(mazeSize);
        mouseCol = random.nextInt(mazeSize);
        cheeseRow = random.nextInt(mazeSize);
        cheeseCol = random.nextInt(mazeSize);

        while (maze[mouseRow][mouseCol] == '#' || maze[cheeseRow][cheeseCol] == '#') {
            mouseRow = random.nextInt(mazeSize);
            mouseCol = random.nextInt(mazeSize);
            cheeseRow = random.nextInt(mazeSize);
            cheeseCol = random.nextInt(mazeSize);
        }

        maze[mouseRow][mouseCol] = 'M';
        maze[cheeseRow][cheeseCol] = 'C';
    }

    public void removePath() {
        for (int i = 0; i < mazeSize; i++) {
            for (int j = 0; j < mazeSize; j++) {
                if (path_grid[i][j] == '*') {
                    path_grid[i][j] = ' ';
                }
            }
        }
    }

    private boolean dfs(int row, int col, int[][] parent, Stack<Integer> backtrackStack) {
        int[] directionX = { -1, 0, 1, 0 };
        int[] directionY = { 0, 1, 0, -1 };

        for (int dir = 0; dir < 4; dir++) {
            int newRow = row + directionX[dir];
            int newCol = col + directionY[dir];

            if (newRow >= 0 && newRow < mazeSize &&
                newCol >= 0 && newCol < mazeSize &&
                maze[newRow][newCol] != '#' && parent[newRow][newCol] == -1) {

                parent[newRow][newCol] = row * mazeSize + col;
                backtrackStack.push(row * mazeSize + col);

                if ((newRow == cheeseRow && Math.abs(newCol - cheeseCol) == 1) ||
                    (newCol == cheeseCol && Math.abs(newRow - cheeseRow) == 1)) {
                    return true;
                }

                if (dfs(newRow, newCol, parent, backtrackStack)) {
                    return true;
                }
            }
        }

        return false;
    }

    public void playDFS() {
        LinkedList<String> path = new LinkedList<>();
        int[][] parent = new int[mazeSize][mazeSize];
        Stack<Integer> backtrackStack = new Stack<>();

        boolean foundPath = dfs(mouseRow, mouseCol, parent, backtrackStack);

        if (foundPath) {
            int row = cheeseRow;
            int col = cheeseCol;
            while (row != mouseRow || col != mouseCol) {
                int parentRow = parent[row][col] / mazeSize;
                int parentCol = parent[row][col] % mazeSize;
                path.addFirst("(" + row + ", " + col + ")");
                path_grid[row][col] = '*';
                row = parentRow;
                col = parentCol;
            }
            path.addFirst("(" + mouseRow + ", " + mouseCol + ")");
            path_grid[mouseRow][mouseCol] = 'M';
            printMaze();
            System.out.println("Congratulations! The mouse ate the cheese at (" + "Row: " + cheeseRow + ", " + "Col: " + cheeseCol + ")!");
            System.out.print("The Path taken by the mouse: " + path);
            System.out.println("\nCheese Located at (" + cheeseRow + ", " + cheeseCol + ")");
        } else {
            hasPath = false;
            printMaze();
            System.out.println("PATH NOT FOUND. \n - The mouse couldn't reach the cheese.");
        }
    }

    public void printMaze() {
        System.out.print("   ");
        for (int i = 0; i < mazeSize; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < mazeSize; i++) {
            System.out.print(i + " |");

            for (int j = 0; j < mazeSize; j++) {
                if (path_grid[i][j] == '*') {
                    System.out.print("* ");
                } else {
                    System.out.print(maze[i][j] + " ");
                }
            }
            System.out.println("|");
        }

        System.out.print("   ");
        for (int i = 0; i < mazeSize; i++) {
            System.out.print("--");
        }
        System.out.println();
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int mazeSize = 0;
        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.print("Enter the maze dimension: ");
                mazeSize = scanner.nextInt();
                validInput = true;
            } catch (java.util.InputMismatchException e) {
                System.out.println("Please enter a valid integer for the maze dimension.");
                scanner.nextLine();
            }
        }

        CheeseMazeGame game = new CheeseMazeGame(mazeSize);
        game.play();
    }
}