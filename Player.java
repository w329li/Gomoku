import java.io.*;
import java.util.*;


public class Player {

    private boolean win;// find out whether player wins now
    public final int xpos[] = {0,1,1,1,0,-1,-1,-1};
    public final int ypos[] = {-1,-1,0,1,1,1,0,-1};
    private int eightDirection[];


    public Player() { // ctor
        eightDirection = new int[8];
        for (int i=0;i<8;++i) eightDirection[i] = 0;
        this.win = false;
    }

    public boolean getWin() {return this.win;} //getter

    public boolean move(Board b,int row,int col) {
        if (row < 0||row > 14 || col <0 || col >14) {
            return false;
        } else if (b.board[row][col] != 0) {
            return false;
        } else {
            //System.out.println("we reach to getFive");
            getFive(b, row, col); // check whether player win by this step
            b.board[row][col] = 1;
            //System.out.println("then we print");
            b.print();
            // now we show the action of player
            int crow = row+1;
            int ccol = col +1;
            System.out.println("Player action: ("+crow+","+ccol+")");
            if (this.win == true) System.out.println("You are the winner! AI loses");
            return true;
        }
    }

    public void getFive(Board b, int row,int col) {
        int num = 0;
        for (int k=0;k< 8;++k) {
            int x = row;
            int y = col;
            for (int t =0;t < 5;++t) {
                x += xpos[k];
                y += ypos[k];
                if (x <0 || x> 14||y<0||y>14) break;
                if (b.board[x][y] == 1) {
                    ++num;
                } else { break; }
            }
            eightDirection[k] = num;
            num = 0;
        }
        for (int i=0;i< 4;++i) {
            // if there r totally more than 4 white chess on this axis,then after
            // this step, we win the game, so we reset player statue in this case
            if (eightDirection[i] + eightDirection[i+4] >= 4) {
                this.win = true;
            }
        }
    }


}
