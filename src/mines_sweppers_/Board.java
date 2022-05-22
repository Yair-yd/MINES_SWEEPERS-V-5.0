package mines_sweppers_;
import java.awt.Color;
import java.util.Random;
import javax.swing.BorderFactory;

public class Board {

    public static int Length;
    public static int Width;
    private int count;
    private final boolean showall = false;
    private boolean game_over = false;
    private Cell[][] board;
    int temp_count;
    int countmines;
    int countflages;

    public static void setWidth(int Width) {
        Board.Width = Width;
    }

    public static void setLength(int Length) {
        Board.Length = Length;
    }

    public void Board(int Length, int Width)  {
        Board.Length = Length;
        Board.Width = Width;
        this.board = new Cell[Length][Width];
        for (Cell[] board1 : this.board) {
            for (int j = 0; j < this.board.length; j++) {
                board1[j] = new Cell();
                board1[j].setBackground(new Color(139, 219, 127));
                board1[j].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            }
        }
    }  //Creat the board game length X  Width.

    public boolean isGame_over() {
        return game_over;
    }/// Getter and Setter {

    public Cell[][] getboard() {
        return board;
    }//

    public boolean isShowall() {
        return showall;
    }//

    public int getLength() {
        return Length;
    }//

    public int getWidth() {
        return Width;
    }//                             }

    public boolean all_press() {
        int preesing_count = 0;
        for (int i = 0; i < Board.Length; i++) {
            for (int j = 0; j < Board.Width; j++) {
                if (this.board[i][j].getShow() > 0 || this.board[i][j].isSelect() && this.board[i][j].isMine() == false) {
                    preesing_count++;
                }
            }
        }
        return preesing_count == (Board.Length * Board.Width) - countmines;
    } //cheack if all the legal cell openning

    boolean if_in_arr(int i, int j) {
        return i >= 0 && i < this.board.length && j >= 0 && j < this.board[1].length;
    } //Checking if the local(i:j) in the board range.

    void Put_Mines(int num_of_mines) {

        Random rnd = new Random();
        int temp_a, temp_b;
        while (num_of_mines != 0) {
            temp_a = rnd.nextInt(Board.Length);
            temp_b = rnd.nextInt(Board.Width);
            if (this.board[temp_a][temp_b].isMine()) {
                continue;
            }
            this.board[temp_a][temp_b].setMine(true);
            countmines++;
            countflages++;
            num_of_mines--;
        }
    }//Disperse mines randomly

    int NumMines(int i, int j) {
        int count_mines = 0;
        int tempi = i - 1;
        int tempj = j - 1;
        int ii = i + 1;
        int jj = j + 1;
        for (int k = tempi; k <= ii; k++) {
            for (int t = tempj; t <= jj; t++) {
                if (if_in_arr(k, t)) {
                    if (k == i && t == j) {
                        continue;
                    }
                    if (this.board[k][t].isMine()) {
                        count_mines++;
                    }
                }
            }
        }
        this.board[i][j].setShow(count_mines);
        if (this.board[i][j].isMine() == false) {
            this.board[i][j].setSelect(true);
        }
        return count_mines;
    }  //Number of MINES around local (i:j).

    void Move(int i, int j) {
        NumMines(i, j);
        if (this.board[i][j].getShow() == 0) {
            int tempi = i - 1;
            int tempj = j - 1;
            int ii = i + 1;
            int jj = j + 1;
            for (int k = tempi; k <= ii; k++) {
                for (int t = tempj; t <= jj; t++) {
                    if (if_in_arr(k, t)) {
                        if (k == i && t == j) {
                            continue;
                        }
                        if (!this.board[k][t].isSelect()) {// Checking for will not return to same place.
                            Move(k, t);
                        }
                    }
                }
            }
        }

    } //Recursy function make move- the heart of the game!.

    void Press(int i, int j, int right_clik, int left_clik) {
        if (right_clik == 1) {// אם נלחץ מקש ימני  
            if (this.board[i][j].isFlag()) {//אם יש דגל
                this.board[i][j].setFlag(false);
                this.board[i][j].setCountflags(0);
                this.countflages++;
            } else {
                this.board[i][j].setFlag(true);
                this.board[i][j].setCountflags(1);

                this.countflages--;
            }
        }
        if (this.board[i][j].isMine() && left_clik == 1) {// אם נלחץ במקש שמאלי ויש מוקש נגמר משחק
            this.game_over = true;
            return;
        }
        if (left_clik == 1 && this.board[i][j].isMine() == false) {//אחרת אם אין מוקש ומקש ימני או שמאלי נלחצו תראה כמה מוקשים יש סביב הכפתור שנלחץ
            Move(i, j);
        }

    }  // When Pressing on button...

}
