import java.io.*;
import java.util.*;


public class Game {


    public static void main(String arg[]) {

        // in the game, player have the first hand by default,player always use
        // white chess, AI uses black chess



        System.out.println("welcome to five-in-a-row");
        System.out.println("Once you put 5 consecutive pieces on one axis"+
                "(could be horizontal line,vertical line or diagonal line)"+ "then you win!");
        System.out.println();
        Board b = new Board();
        Player player = new Player();
        AI ai = new AI();
        b.print();
        while (true) {
            System.out.print("row input: ");
            Scanner srow = new Scanner(System.in);
            int row = srow.nextInt() -1;
            System.out.print("column iput:");
            Scanner scol = new Scanner(System.in);
            int col = scol.nextInt() -1;
            boolean checkmove = player.move(b,row,col);
            if (!checkmove) {
                System.out.println("invalid move! Please try again");
                continue;
            }
            if (player.getWin() == true) break;
            ai.algorithm(b);
            if (ai.getWin() == true) break;
        }
        System.out.println("Game over");
    }

}
