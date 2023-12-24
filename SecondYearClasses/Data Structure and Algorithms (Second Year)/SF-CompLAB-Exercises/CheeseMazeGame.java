import java.util.*; /** LinkedList, Queue, Random, Scanner, Stack **/
/*
Asterisk (*): Importing all classes from the java.util package.
LinkedList: A class that implements a linked list.
Queue: An interface representing a collection for holding elements before processing.
Random: A class for generating pseudo-random numbers.
Stack: A class for representing a last-in-first-out stack.
In the program, we use the LinkedList and Queue classes to represent the mouse's path through the maze.
We also use the Random class to generate the maze layout, the initial positions of the mouse and cheese,
and to simulate the mouse moving through the maze. Finally, we use the Stack class to
backtrack the mouse's path when it encounters a dead end.
**/
//--------------------------------------------

/**
 * CheeseMazeGame class represents a simple maze game where a mouse tries to reach the cheese.
 */
public class CheeseMazeGame { 
    private char[][] maze; // 2D array to represent the maze
    private int mazeSize, mouseRow, mouseCol, cheeseRow, cheeseCol;
    // Size of the maze, Current row of the mouse, Current column of the mouse, Row and Column where the cheese is located
    private boolean hasPath = true; // Flag to check if a path exists
    private char[][] path_grid; // 2D array to represent the path the mouse takes


    /**
     * Constructor for CheeseMazeGame.
     * @param mazeSize Size of the maze.
     */
    public CheeseMazeGame(int mazeSize) { // constructor
        this.mazeSize = mazeSize;
        this.maze = new char[mazeSize][mazeSize]; // Create the maze array
        this.path_grid = new char[mazeSize][mazeSize]; // Create the path grid
        initializeMaze(); // Initialize the maze
    }



    public void initializeMaze() { // Initializes the maze with random walls, mouse, and cheese positions.
        Random random = new Random();

        // Fill the maze with walls and empty spaces
        for (int i = 0; i < mazeSize; i++) {
            for (int j = 0; j < mazeSize; j++) {
                if (random.nextDouble() < 0.3) {
                    maze[i][j] = '#'; // Randomly add walls
                } else {
                    maze[i][j] = ' '; // Otherwise, add empty spaces
                }
                path_grid[i][j] = ' '; // Initialize the path grid with empty cells
            }
        }

        // Randomly place the mouse and cheese
        mouseRow = random.nextInt(mazeSize); 
        mouseCol = random.nextInt(mazeSize); 
        cheeseRow = random.nextInt(mazeSize); 
        cheeseCol = random.nextInt(mazeSize); 

        // Ensure/Make sure the mouse and cheese are not placed on walls
        while (maze[mouseRow][mouseCol] == '#' || maze[cheeseRow][cheeseCol] == '#') {
            mouseRow = random.nextInt(mazeSize);
            mouseCol = random.nextInt(mazeSize);
            cheeseRow = random.nextInt(mazeSize);
            cheeseCol = random.nextInt(mazeSize);
        }

        maze[mouseRow][mouseCol] = 'M'; // Set the mouse position
        maze[cheeseRow][cheeseCol] = 'C'; // Set the cheese position
    }

    public void printMaze() {
        // Print the top border with column index numbers
        System.out.print("   ");
        for (int i = 0; i < mazeSize; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
    
        // Print the maze with borders and row index numbers
        for (int i = 0; i < mazeSize; i++) {
            System.out.print(i + " |"); // Left border and row index
    
            for (int j = 0; j < mazeSize; j++) {
                if (path_grid[i][j] == '*') {
                    System.out.print("* "); // Print '*' for the path taken by the mouse
                } else {
                    System.out.print(maze[i][j] + " "); // Print the maze or walls
                }
            }
            System.out.println("|"); // Right border
        }
    
        // Print the bottom border with column index numbers
        System.out.print("   ");
        for (int i = 0; i < mazeSize; i++) {
            System.out.print("--");
        }
        System.out.println();
        System.out.println(); // Add an extra line for separation
    }
    
    
    

    public void play() {
        LinkedList<String> path = new LinkedList<>();
        Queue<Integer> rowQueue = new LinkedList<>();
        Queue<Integer> colQueue = new LinkedList<>();
        rowQueue.add(mouseRow); // Add the mouse's initial row to the row queue
        colQueue.add(mouseCol); // Add the mouse's initial column to the column queue

        int[][] parent = new int[mazeSize][mazeSize]; // Keep track of parent positions

        for (int i = 0; i < mazeSize; i++) {
            for (int j = 0; j < mazeSize; j++) {
                parent[i][j] = -1; // Initialize the parent array with -1
            }
        }

        Stack<Integer> backtrackStack = new Stack<>(); // Stack for backtracking

        boolean foundPath = false; // Flag to check if a path to cheese is found
        int cheeseFoundRow = -1; // Initialize the row where the cheese is found
        int cheeseFoundCol = -1; // Initialize the column where the cheese is found

        int[] directionX = { -1, 0, 1, 0 }; // Possible row directions (up, right, down, left)
        int[] directionY = { 0, 1, 0, -1 }; // Possible column directions (up, right, down, left)

        while (!rowQueue.isEmpty()) {
            int currentRow = rowQueue.poll(); // Get the current row from the queue
            int currentCol = colQueue.poll(); // Get the current column from the queue

            boolean deadEnd = true; // Flag to check for dead ends

            for (int dir = 0; dir < 4; dir++) {
                int newRow = currentRow + directionX[dir]; // Calculate the new row
                int newCol = currentCol + directionY[dir]; // Calculate the new column

                if (newRow >= 0 && newRow < mazeSize &&
                newCol >= 0 && newCol < mazeSize &&
                maze[newRow][newCol] != '#' && parent[newRow][newCol] == -1) {

                    rowQueue.add(newRow); // Add the new row to the queue
                    colQueue.add(newCol); // Add the new column to the queue
                    parent[newRow][newCol] = currentRow * mazeSize + currentCol;
                    deadEnd = false; // There is a valid path, so not a dead end

                    if ((newRow == cheeseRow && Math.abs(newCol - cheeseCol) == 1) ||
                    (newCol == cheeseCol && Math.abs(newRow - cheeseRow) == 1)) {
                        foundPath = true; // The mouse found a path to the cheese
                        cheeseFoundRow = newRow; // Record the row where the cheese is found
                        cheeseFoundCol = newCol; // Record the column where the cheese is found
                        break;
                    }
                }
            }

            if (deadEnd) {
                // Mouse reached a dead end, so backtrack
                if (!backtrackStack.isEmpty()) {
                    int lastPosition = backtrackStack.pop(); // Pop the last position from the stack
                    int parentRow = lastPosition / mazeSize;
                    int parentCol = lastPosition % mazeSize;
                    rowQueue.add(parentRow);
                    colQueue.add(parentCol);
                }
            } else {
                backtrackStack.push(currentRow * mazeSize + currentCol); // Push current position to the stack for backtracking
            }

            if (foundPath) {
                break;
            }
        }

        if (foundPath) {
            int row = cheeseFoundRow; // Start from the row where the cheese is found
            int col = cheeseFoundCol; // Start from the column where the cheese is found
            while (row != mouseRow || col != mouseCol) {
                int parentRow = parent[row][col] / mazeSize; // Get the parent row
                int parentCol = parent[row][col] % mazeSize; // Get the parent column
                path.addFirst("(" + row + ", " + col + ")"); // Add the current position to the path
                path_grid[row][col] = '*'; // Mark the path_grid with '*' for the mouse's path
                row = parentRow; // Move to the parent row
                col = parentCol; // Move to the parent column
            }
            path.addFirst("(" + mouseRow + ", " + mouseCol + ")"); // Add the initial position of the mouse to the path
            path_grid[mouseRow][mouseCol] = 'M'; // Mark the path_grid with 'M' for the mouse's position
            printMaze(); // Display the maze with the mouse's path
            System.out.println("Congratulations! The mouse ate the cheese at (" + "Row: "+ cheeseRow + ", " + "Col: " + cheeseCol + ")!"); // Print a success message
            System.out.print("The Path taken by the mouse: " + path); // Display the path taken by the mouse
            System.out.println("\nCheese Located at (" + cheeseRow + ", " + cheeseCol + ")"); // Display the cheese location
        } else {
            hasPath = false; // There's no valid path
            printMaze(); // Display the maze
            System.out.println("PATH NOT FOUND. \n - The mouse couldn't reach the cheese."); // Print a failure message
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int mazeSize = 0;
        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.print("Enter the maze dimension: ");
                mazeSize = scanner.nextInt();
                validInput = true; // If no exception is thrown, set validInput to true to exit the loop
            } catch (java.util.InputMismatchException e) {
                System.out.println("Please enter a valid integer for the maze dimension.");
                scanner.nextLine(); // Consume the invalid input
            }
        }

        CheeseMazeGame game = new CheeseMazeGame(mazeSize); // Create a new game
        game.play(); // Start the game
    }
}
