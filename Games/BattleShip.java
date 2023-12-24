
import java.util.*;
    public class BattleShip {
    public static int row = 10;
    public static int col = 10;
    public static int playerShips;
    public static int computerShips;
    public static String[][] ocean = new String[row][col];
    public static int[][] missedGuesses = new int[row][col];
    public static void main(String[] args) {
        System.out.println("\n** Welcome to Battle Ships game **");
        System.out.println("Right now, the sea is empty...\n");
        OceanMap();
        deployPlayerShips();
        deployComputerShips();
        do {
            BattleState();
        }while(BattleShip.playerShips != 0 && BattleShip.computerShips != 0);
        endOfGame();
    }
    public static void OceanMap(){
        System.out.print("  ");
        for(int i = 0; i < col; i++)
        System.out.print(i);
        System.out.println();
        for(int i = 0; i < ocean.length; i++) {
            for (int j = 0; j < ocean[i].length; j++) {
                ocean[i][j] = " ";
                if (j == 0)
                    System.out.print(i + "|" + ocean[i][j]);
                else if (j == ocean[i].length - 1)
                    System.out.print(ocean[i][j] + "|" + i);
                else
                    System.out.print(ocean[i][j]);
            }
            System.out.println();
        }
        System.out.print("  ");
        for(int i = 0; i < col; i++)
        System.out.print(i);
        System.out.println();
    }
    public static void deployPlayerShips (){
        Scanner input = new Scanner(System.in);
        System.out.println("\nYARR! Deploy your ships!");
        BattleShip.playerShips = 5;
        for (int i = 1; i <= BattleShip.playerShips; ) {
            System.out.print("Enter Y coordinate for your ship #" + i + ": ");
            while (!input.hasNextInt()){
                input.next();
                System.out.print("Enter numbers(0-9) of coordinate only, Sire! \nEnter Y coordinate for your ship #" + i + ": ");
            }
            int x = input.nextInt();
            System.out.print("Enter X coordinate for your ship #" + i + ": ");
            while (!input.hasNextInt()){
                input.next();
                System.out.print("Enter numbers(0-9) of coordinate only, Sire! \nEnter X coordinate for your ship #" + i + ": ");
            }
            int y = input.nextInt();
            if((x >= 0 && x < row) && (y >= 0 && y < col) && (ocean[x][y] == " "))
            {
                ocean[x][y] = "@";
                i++;
            }
            else if((x >= 0 && x < row) && (y >= 0 && y < col) && ocean[x][y] == "@")
                System.out.println("You can't place two or more ships on the same location");
            else if((x < 0 || x >= row) || (y < 0 || y >= col))
                System.out.println("You can't place ships outside the " + row + " by " + col + " grid");
        }
        updateOceanMap();
    }
    public static void deployComputerShips(){
        System.out.println("\nComputer is deploying ships...");
        Random randomNum = new Random();
        BattleShip.computerShips = 5;
        for (int i = 1; i <= BattleShip.computerShips; ) {
            int x = randomNum.nextInt(10);
            int y = randomNum.nextInt(10);
            if((x >= 0 && x < row) && (y >= 0 && y < col) && (ocean[x][y] == " "))
            {
                ocean[x][y] = "%";
                System.out.println("ship #" + i +" DEPLOYED");
                i++;
            }
        }
    }
    public static void BattleState(){
        turnOfPlayer();
        turnOfComputer();
        updateOceanMap();
        System.out.println();
        System.out.println("Your ships: " + BattleShip.playerShips + " | Computer ships: " + BattleShip.computerShips);
    }
    public static void turnOfPlayer(){
        System.out.println("\nYOUR TURN, SIRE!");
            int x = -1, y = -1;
            do {
                Scanner input = new Scanner(System.in);
                System.out.print("Enter Y coordinate: ");
                while (!input.hasNextInt()){
                    input.next();
                    System.out.print("Enter numbers(0-9) of coordinate only, Sire! \nEnter Y coordinate: ");
                }
                x = input.nextInt();
                System.out.print("Enter X coordinate: ");
                while (!input.hasNextInt()){
                    input.next();
                    System.out.print("Enter numbers(0-9) of coordinate only, Sire!  \nEnter X coordinate: ");
                }
                y = input.nextInt();
                if ((x >= 0 && x < row) && (y >= 0 && y < col)) {
                    if (ocean[x][y] == "%") {
                        System.out.println("Boom! You sunk the ship!");
                        ocean[x][y] = "!";
                        --BattleShip.computerShips;
                    } else if (ocean[x][y] == "@") {
                        System.out.println("YARR! You sunk your own ship!");
                        ocean[x][y] = "x";
                        --BattleShip.playerShips;
                    } else if (ocean[x][y] == " ") {
                        System.out.println("YARR! You missed!");
                        ocean[x][y] = "-";
                    } else if (ocean[x][y] == "!") {
                        System.out.println("Ship already sunk!");
                    } else if (ocean[x][y] == "x") {
                        System.out.println("Ship already sunk!");
                    } else if (ocean[x][y] == "-") {
                        System.out.println("You missed again!");
                    }
                } else if ((x < 0 || x >= row) || (y < 0 || y >= col))
                    System.out.println("\nYou can't place ships outside the " + row + " by " + col + " grid");
            } while ((x < 0 || x >= row) || (y < 0 || y >= col));
    }
    public static void turnOfComputer(){

        int x = -1, y = -1;
        do {
            x = (int)(Math.random() * 10);
            y = (int)(Math.random() * 10);
            if (missedGuesses[x][y] == 1){
                turnOfComputer();
            }
            else if ((x >= 0 && x < row) && (y >= 0 && y < col))
                System.out.println("\nCOMPUTER'S TURN");
            {
                if (ocean[x][y] == "@") {
                    System.out.println("The Computer sunk one of your ships!");
                    ocean[x][y] = "x";
                    --BattleShip.playerShips;
                    if(missedGuesses[x][y] != 1)
                        missedGuesses[x][y] = 1;
                }
                else if (ocean[x][y] == "%") {
                    System.out.println("The Computer sunk one of its own ships");
                    ocean[x][y] = "!";
                    --BattleShip.computerShips;
                    if(missedGuesses[x][y] != 1)
                        missedGuesses[x][y] = 1;
                }
                else if (ocean[x][y] == "!") {
                    System.out.println("Ship already sunk!");
                    if(missedGuesses[x][y] != 1)
                        missedGuesses[x][y] = 1;
                }
                else if (ocean[x][y] == "-") {
                    System.out.println("Computer missed...!");

                    if(missedGuesses[x][y] != 1)
                        missedGuesses[x][y] = 1;
                }
                else if (ocean[x][y] == " ") {
                    System.out.println("Computer missed...");

                    if(missedGuesses[x][y] != 1)
                        missedGuesses[x][y] = 1;
                }
            }
        }while((x < 0 || x >= row) || (y < 0 || y >= col));
    }
    public static void endOfGame(){
        if(BattleShip.playerShips > 0 && BattleShip.computerShips <= 0)
            System.out.println("\nYARR! You won the battle!!!");
        else
            System.out.println("\nSorry, you lost the battle...");
        System.out.println();
    }
    public static void updateOceanMap(){
        System.out.println();
        System.out.print("  ");
        for(int i = 0; i < col; i++)
            System.out.print(i);
        System.out.println();
        for(int x = 0; x < ocean.length; x++) {
            System.out.print(x + "|");

            for (int y = 0; y < ocean[x].length; y++){
                if(ocean[x][y] == "%"){
                    System.out.print(" ");
                } else {
                    System.out.print(ocean[x][y]);
                }
            }
            System.out.println("|" + x);
        }
        System.out.print("  ");
        for(int i = 0; i < col; i++)
            System.out.print(i);
        System.out.println();
    }
}
