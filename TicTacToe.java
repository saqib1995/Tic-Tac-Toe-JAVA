import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    static ArrayList<Integer> playerPositions;
    static ArrayList<Integer> cpuPositions;
    static List<List<Integer>> winners;
    public static void main(String[] args) throws Exception {
        
        char[][] gameBoard = {{' ', '|', ' ', '|', ' '}, 
        {'-', '+', '-', '+', '-'},
        {' ', '|', ' ', '|', ' '},
        {'-', '+', '-', '+', '-'},
        {' ', '|', ' ', '|', ' '}};


        playerPositions = new ArrayList<>();
        cpuPositions = new ArrayList<>();

        List<Integer> topRow = Arrays.asList(1, 2, 3);
        List<Integer> midRow = Arrays.asList(4, 5, 6);
        List<Integer> botRow = Arrays.asList(7, 8, 9);
        List<Integer> topCol = Arrays.asList(1, 4, 7);
        List<Integer> midCol = Arrays.asList(2, 5, 8);
        List<Integer> botCol = Arrays.asList(3, 6, 9);
        List<Integer> cross1 = Arrays.asList(1, 5, 9);
        List<Integer> cross2 = Arrays.asList(3, 5, 7);

        winners = new ArrayList<>();

        winners.add(topRow);
        winners.add(midRow);
        winners.add(botRow);
        winners.add(topCol);
        winners.add(midCol);
        winners.add(botCol);
        winners.add(cross1);
        winners.add(cross2);

        
        startingGame(gameBoard);
        


        
    }

    public static void startingGame(char[][] gameBoard){

        printGameBoard(gameBoard);
        System.out.println("STARTING GAME!");
        boolean gameOn = true;
        int playerPos;
        int cpuPos;

        while(gameOn) {

            System.out.println("your turn!");
            Scanner st = new Scanner(System.in);
            System.out.println("Enter a position(1-9)");
            playerPos = st.nextInt();

            if(!playerPositions.contains(playerPos) && !cpuPositions.contains(playerPos)){
                playerPositions.add(playerPos);
            } else {
                while(playerPositions.contains(playerPos) || cpuPositions.contains(playerPos)) {
                    System.out.println("position already occupied, please enter again");
                    playerPos = st.nextInt();
                }
                playerPositions.add(playerPos);
            }
            
            markBoard(gameBoard, playerPos, "player");
            printGameBoard(gameBoard);

            if(winner(playerPositions, "YOU")) {
                gameOn = false;
                break;
            }



            Random rand = new Random();
            System.out.println("cpu's turn!");
            cpuPos = rand.nextInt(9) + 1;

            if(!playerPositions.contains(cpuPos) && !cpuPositions.contains(cpuPos)) {
                cpuPositions.add(cpuPos);
            } else {
                while(playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos) ) {
                    System.out.println("position already occupied, please enter again");
                    cpuPos = rand.nextInt(9) + 1;
                }
                cpuPositions.add(cpuPos);
            }

            markBoard(gameBoard, cpuPos, "cpu");
            printGameBoard(gameBoard);

            if(winner(cpuPositions, "CPU")) {
                gameOn = false;
                break;
            }

            if(cpuPositions.size() + playerPositions.size() == 9) {
                System.out.println("OOPS! It's a tie.");
                gameOn = false;
                break;
            }

            

            


        }

    }

    public static void markBoard(char[][] gameBoard, int pos, String user) {

        char symbol = ' ';

        if(user.equals("player")){
            symbol = 'X';
        } else {
            symbol = 'O';
        }

        switch(pos) {
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
            default:
                break;


        }


    }

    public static void printGameBoard(char[][] gameBoard) {
        for(char[] cc: gameBoard) {
            for(char c: cc) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static boolean winner(ArrayList<Integer> positions, String user) {

        for(List<Integer> l : winners) {
            if(positions.containsAll(l)){
                System.out.println(user + " WON");
                return true;
            }
        }

        return false;
    }

}
