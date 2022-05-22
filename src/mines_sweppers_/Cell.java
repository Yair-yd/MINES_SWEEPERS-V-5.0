package mines_sweppers_;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.*;
import java.awt.*;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Cell extends JButton /*mplements MouseListener*/ {

    private boolean Mine = false;
    private boolean flag = false;
    private int show = 0;
    private boolean select = false;
    private int countflags = 3;

    private static Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }//Image size adapter for button

    public int getCountflags() {
        return countflags;
    }/// Getter and Setter {

    public void setCountflags(int countflags) {
        this.countflags = countflags;
    }//

    public boolean isSelect() {
        return select;
    }//

    public void setSelect(boolean select) {
        this.select = select;
    }//

    public int getShow() {
        return show;
    }//

    public void setShow(int show) {
        this.show = show;
    }//

    public boolean isFlag() {
        return flag;
    }//

    public void setFlag(boolean flag) {
        this.flag = flag;
    }//

    public boolean isMine() {
        return Mine;
    }//

    public void setMine(boolean Mine) {
        this.Mine = Mine;
    }//           }

    public void icon_button() {
        ImageIcon icon = new ImageIcon("");
        if (this.isMine()) {
            icon = new ImageIcon("C:\\Users\\USER001\\Documents\\New Folder\\Mineswepeers-v5.0\\src\\main\\java\\new boom.png");
        }
        if (this.isFlag()) {
            icon = new ImageIcon("C:\\Users\\USER001\\Documents\\New Folder\\Mineswepeers-v5.0\\src\\main\\java\\Minesweeper_flag.svg.png");
        }
        if (this.show == 1) {
            icon = new ImageIcon("C:\\Users\\USER001\\Documents\\New Folder\\Mineswepeers-v5.0\\src\\main\\java\\Minesweeper_1.svg.png");
        }
        if (this.show == 2) {
            icon = new ImageIcon("C:\\Users\\USER001\\Documents\\New Folder\\Mineswepeers-v5.0\\src\\main\\java\\Minesweeper_2.svg.png");
        }
        if (this.show == 3) {
            icon = new ImageIcon("C:\\Users\\USER001\\Documents\\New Folder\\Mineswepeers-v5.0\\src\\main\\java\\Minesweeper_3.svg.png");
        }
        if (this.show == 4) {
            icon = new ImageIcon("C:\\Users\\USER001\\Documents\\New Folder\\Mineswepeers-v5.0\\src\\main\\java\\Minesweeper_4.svg.png");
        }
        if (this.show == 5) {
            icon = new ImageIcon("C:\\Users\\USER001\\Documents\\New Folder\\Mineswepeers-v5.0\\src\\main\\java\\Minesweeper_5.svg.png");
        }
        if (this.show == 6) {
            icon = new ImageIcon("C:\\Users\\USER001\\Documents\\New Folder\\Mineswepeers-v5.0\\src\\main\\java\\Minesweeper_6.svg.png");
        }
        if (this.show == 7) {
            icon = new ImageIcon("C:\\Users\\USER001\\Documents\\New Folder\\Mineswepeers-v5.0\\src\\main\\java\\Minesweeper_7.svg.png");
        }
        if (this.show == 8) {
            icon = new ImageIcon("C:\\Users\\USER001\\Documents\\New Folder\\Mineswepeers-v5.0\\src\\main\\java\\Minesweeper_8.svg.png");
        }
        this.setIcon(resizeIcon(icon, (int) sqrt(pow(Board.Length * 35, 2) / (Board.Length * Board.Width)) - 5, (int) sqrt(pow(Board.Length * 35, 2) / (Board.Length * Board.Width)) - 5));
    }//Match the icon(image) to button

}
