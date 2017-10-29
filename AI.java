import java.util.*;
import java.io.*;



public class AI {

    public int num; // used to record the number of consecutive white piece
    // dis is a 4D array, first two dimension represents a coordinate(poisition)
    // on the board, the third dimension represents the 8 position surrounding it
    // 4th dimension represents number of black chess or white chess
    private int dis[][][][]= new int[15][15][8][2];
    private int defend[][] = new int[15][15];
    private int attack[][] = new int[15][15];
    public final int xpos[] = {0,1,1,1,0,-1,-1,-1};
    public final int ypos[] = {-1,-1,0,1,1,1,0,-1};
    private boolean win; // find out whether AI wins now



    public AI() { // ctor
        num = 0;
        win = false;
        // initialize the dis super array
        for (int a =0;a<15;++a) {
            for (int b=0;b<15;++b) {
                for (int c = 0;c<8;++c){
                    for (int d=0;d<2;++d) {
                        dis[a][b][c][d] = 0;
                    }
                }
            }
        }
        // initialize defend and attack array
        for (int i=0;i<15;++i) {
            for (int j =0;j<15;++j) {
                this.defend[i][j] = 0;
                this.attack[i][j] = 0;
            }
        }
    }

    // getter
    public boolean getWin() { return this.win; }

    void qiju(Board b) {

        int i,j,k,x,y;
        for (i=0;i<b.getWidth();++i) {
            for (j=0;j<b.getHeight();++j) {

                if (b.board[i][j] == 0) {
                    for (k=0;k<8;++k) {//search 8 different direction
                        //System.out.println("just of checking, num is: "+num);

                        // search the num of white pieces
                        num =0;
                        x =i; y = j;
                        for (int t=0;t<5;++t){
                            x += xpos[k];
                            y += ypos[k];
                            if (x >14||x<0||y<0||y>14) {
                                break;
                            }
                            if (b.board[x][y]==1){
                                ++num;
                            } else { break; }
                        }
                        dis[i][j][k][0] = num;

                        // search num of black pieces
                        num = 0;
                        x = i ; y = j;
                        for (int t=0;t<5;++t){
                            x += xpos[k];
                            y += ypos[k];
                            if (x >14||x<0||y<0||y>14) {
                                break;
                            }
                            if (b.board[x][y]==2){
                                ++num;
                            } else { break; }
                        }
                        dis[i][j][k][1] = num;
                    }
                }
            }
        }
    }


    void value(Board b){
        int i,j,k,score;
        for (i =0;i<15;++i) {
            for (j = 0;j<15;++j) {
                if (b.board[i][j] == 0) {
                    score = 0;

                    for (k =0;k<4;++k) {
                        if (dis[i][j][k][0]+dis[i][j][k+4][0]>=4) {
                            //total white piece on this direction is greater or equal to 4
                            score += 10000; // AI is in very dangerous situation
                            //this.win = true;
                        } else if (dis[i][j][k][0]+dis[i][j][k+4][0]==3) {
                            // total white piece on this direction is 3 so far
                            score += 1000; // AI have great odd
                        } else if (dis[i][j][k][0]+dis[i][j][k+4][0]==2) {
                            // total white piece on this direction is 2 so far
                            score += 100;
                        } else if (dis[i][j][k][0]+dis[i][j][k+4][0]==1) {
                            // total white piece on this direction is 1
                            score += 10;
                        }
                    }
                    this.defend[i][j] = score;
                    score = 0;

                    for (k =0;k<4;++k) {
                        if (dis[i][j][k][1]+dis[i][j][k+4][1]>=4) {
                            //total black piece on this direction is greater or equal to 4
                            score += 10000; // AI win in this step
                            this.win = true;
                        } else if (dis[i][j][k][1]+dis[i][j][k+4][1]==3) {
                            // total white piece on this direction is 3 so far
                            score += 1000; // AI have great odd
                        } else if (dis[i][j][k][1]+dis[i][j][k+4][1]==2) {
                            // total white piece on this direction is 2 so far
                            score += 100;
                        } else if (dis[i][j][k][1]+dis[i][j][k+4][1]==1) {
                            // total white piece on this direction is 1
                            score += 10;
                        }
                    }
                    this.attack[i][j] = score;
                    score = 0;

                }
            }
        }
    }


    void algorithm(Board b) {

        int i,j;
        int dx=0, dy=0, ax=0, ay=0;
        qiju(b);
        value(b);
        for (i=0;i<15;++i) {
            for (j=0;j<15;++j) {
                if (defend[dx][dy]< defend[i][j]) {
                    dx = i;
                    dy = j;
                }
                if (attack[ax][ay]< attack[i][j]) {
                    ax = i;
                    ay = j;
                }
            }
        }
        int na=0;
        int nb= 0;
        if (defend[dx][dy] > attack[ax][ay]) {
            na = dx; nb = dy;
        } else { na = ax; nb = ay; }

        int ca = na +1;// used to show the position of AI's move
        int cb = nb +1;
        b.board[na][nb] = 2;
        b.print();
        System.out.println("AI action: ("+ca+","+cb+")" );
        if (this.win == true) System.out.println("AI is the winner,you lose.");
        // after every move of AI, we need to reset the dis,defend and attack recorder
        // so that next move wont be affected by last move
        dis = new int[15][15][8][2];
        defend = new int[15][15];
        attack = new int[15][15];
    }



}
