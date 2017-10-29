import java.io.*;
import java.util.*;

public class Board {

    public int board[][] = new int[15][15];
    private int height,width;// the height, width of board
    private boolean playerWin; // find out whether player wins now

    public Board() {
        this.width= 15; // width of board
        this.height= 15;// height of board
        //int [][] board = new int[15][15];// initialize the board

        /*for (int i=0;i<15;++i) {
            for (int j=0; j< 15;++j) {
                board[i][j] = 0;
                System.out.print(board[i][j]);
            }
            System.out.println();
        }*/


        System.out.println("Chess board initialization completed!");
    }

    int getHeight() {return this.height;}
    int getWidth() {return this.width; }


    public void print() { /// print the current chess board

        System.out.println("  123456789ABCDEF");
        int num = 1;
        for (int i=0;i< this.width;++i) {
            System.out.print(num);
            if (num < 10) System.out.print(" ");
            for (int j=0; j < this.height;++j){
                int sym  = board[i][j];
                if (sym == 0) System.out.print(".");
                if (sym == 1) System.out.print("0");
                if (sym == 2) System.out.print("A");
            }
            System.out.println();
            ++num;
        }
    }


}
