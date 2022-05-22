package mines_sweppers_;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class GUI extends JFrame implements ActionListener, WindowListener, ChangeListener {

    private int length;
    private int width;
    private int num_mines;
    private static int local_button_I = 0;
    private static int local_button_J = 0;
    private static int right_click = 0;
    private static int left_click = 0;
    private boolean first_time = true;
    private Board b1;
    private JFrame frame;
    private JFrame frame_open;
    private JPanel panel;
    private static boolean game_over = false;
    private static boolean show_all_mines_win = false;
    private final JButton restart_open = new JButton(" START");
    private final JLabel flages = new JLabel();

  
    
    
    public Board GetBoard() {
        return this.b1;
    }

    public JSlider slider1_open = new JSlider(JSlider.HORIZONTAL, 1, 5, 1);

    public int getSlider1() {
        return slider1_open.getValue();
    }

    public void game_board_screen() {
        b1 = new Board();
        this.length = slider1_open.getValue() * 4;
        this.width = slider1_open.getValue() * 4;
        b1.Board(this.length, this.width);
        b1.Put_Mines((int) ((0.1) * (this.length * this.width)));
       
        JMenuBar mb = new JMenuBar();
        mb.add(flages);
        add_click(b1);
        frame = new JFrame();
        frame.setJMenuBar(mb);
        frame.setTitle(" MineSweeper YAIR.D");
        Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\USER001\\Documents\\New Folder\\Mineswepeers-v5.0\\src\\main\\java\\minelogo.jpg");
        frame.setIconImage(icon);
        panel = new JPanel();
        panel.setLayout(new GridLayout(b1.getLength(), b1.getWidth()));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(200, 200, b1.getLength() * 35, b1.getWidth() * 35);
        frame.add(panel);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + b1.countmines);
        view_graphics(panel, frame, b1);
    }  //view

    public void openning_screen() throws IOException, Exception {
        frame_open = new JFrame();
        frame_open.setTitle(" MineSweeper YAIR.D");
        Image icon_open = Toolkit.getDefaultToolkit().getImage("C:\\Users\\USER001\\Documents\\New Folder\\Mineswepeers-v5.0\\src\\main\\java\\minelogo.jpg");
        frame_open.setIconImage(icon_open);
        frame_open.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame_open.setBounds(500, 250, 300, 150);
        JMenu level, about_open;
        JMenuBar mb_open = new JMenuBar();
        level = new JMenu("Level");
        level.add(slider1_open);
        about_open = new JMenu("About");
        mb_open.add(level);
        mb_open.add(about_open);
        mb_open.add(restart_open);
        Hashtable labelTable = new Hashtable();
        labelTable.put(1, new JLabel("Beginner"));
        labelTable.put(2, new JLabel("                   Intermediate"));
        labelTable.put(5, new JLabel("Expert"));
        slider1_open.setLabelTable(labelTable);
        slider1_open.setPaintLabels(true);
        frame_open.setJMenuBar(mb_open);
        frame_open.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("C:\\Users\\USER001\\Documents\\New Folder\\Mineswepeers-v5.0\\src\\main\\java\\minesweeoer logo.png")))));
        frame_open.pack();
        frame_open.setVisible(true);
        frame_open.setVisible(true);
        restart_open.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                first_time = false;
                frame_open.setVisible(false);
                frame.setVisible(true);
            }
        });
        while (first_time) {
            System.out.print("");
        }
        game_board_screen();
    }//view

    void add_click(Board b1) {
        for (int i = 0; i < b1.getLength(); i++) {
            for (int j = 0; j < b1.getWidth(); j++) {
                b1.getboard()[i][j].addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        button1MouseClicked(evt);
                    }
                });
            }
        }
    }//Add to buttons event of read mouse click

    private void print_all_mines() {
        ImageIcon icon = new ImageIcon();
        for (int i = 0; i < b1.getLength(); i++) {
            for (int j = 0; j < b1.getWidth(); j++) {
                if (b1.getboard()[i][j].isMine()) {
                    b1.getboard()[i][j].setFlag(false);
                    b1.getboard()[i][j].setBorder(BorderFactory.createLineBorder(Color.ORANGE, 2));
                    b1.getboard()[i][j].setBackground(new Color(186, 142, 205));
                }
                b1.getboard()[i][j].icon_button();
                panel.add(b1.getboard()[i][j]);
            }
        }
    }//When the game over,so, show all local's mines.

    void view_graphics(JPanel panel, JFrame frame, Board b1) {

        ImageIcon icon = new ImageIcon();
        for (int i = 0; i < b1.getLength(); i++) {
            for (int j = 0; j < b1.getWidth(); j++) {
                if (b1.getboard()[i][j].isSelect() || b1.getboard()[i][j].isFlag()) {//If is flaf or is need to open show it!
                    b1.getboard()[i][j].icon_button();
                    b1.getboard()[i][j].setBackground(new Color(189, 232, 205));//The color of buttons were pressed
                    b1.getboard()[i][j].setBorder(BorderFactory.createLineBorder(Color.white, 1));//the color of button fram were pressed    
                }
                if (b1.getboard()[i][j].getCountflags() == 0) {//If the flag was delete
                    b1.getboard()[i][j].setIcon(icon);
                    if (b1.getboard()[i][j].isSelect()) {//If is button then was open so the background is whith
                        b1.getboard()[i][j].setBackground(new Color(189, 232, 205));//the color of buttons were pressed
                        b1.getboard()[i][j].setBorder(BorderFactory.createLineBorder(Color.white, 1));//the color of button fram were pressed   
                    } else {//if not so the background is black
                        b1.getboard()[i][j].setBackground(new Color(139, 219, 127));
                        b1.getboard()[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                    }

                }
                panel.add(b1.getboard()[i][j]);// Anyway add this button to panel
            }
        }

        panel.setVisible(true);
        flages.setText("FLAG:" + b1.countflages);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(panel);
        frame.setVisible(true);
        frame.setTitle("Flage:" + b1.countflages);// Show in the title- how many flags left
        if (GUI.game_over) {// If the game over show all the mines
            System.out.println("GAME OVER!");
            print_all_mines();
            return;
        }
        if (GUI.show_all_mines_win) {
            System.out.println("$YOU WIN!$");
            print_all_mines();
        }
    }// Mange the heart of gui.

    private void button1MouseClicked(java.awt.event.MouseEvent evt) {
        for (int row = 0; row < length; row++) {
            for (int col = 0; col < width; col++) {
                if (b1.getboard()[row][col] == evt.getSource()) {
                    if (evt.getButton() == 3) { // if right click
                        GUI.right_click = 1;
                        GUI.left_click = 0;
                    } else {
                        GUI.left_click = 1;
                        GUI.right_click = 0;
                    }
                    System.out.println(row + "," + col);
                    GUI.local_button_I = row;
                    GUI.local_button_J = col;
                }
            }
        }
        if (GUI.left_click == 1) {
            b1.Press(local_button_I, local_button_J, 0, 1);
            GUI.game_over = b1.isGame_over();
            GUI.show_all_mines_win = b1.all_press();
            view_graphics(panel, frame, b1);
        } else {
            b1.Press(local_button_I, local_button_J, 1, 0);
            GUI.game_over = b1.isGame_over();
            GUI.show_all_mines_win = b1.all_press();

            view_graphics(panel, frame, b1);
        }

    }//when cell clicking

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowOpened(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosing(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosed(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowIconified(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowActivated(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void changed(ObservableValue ov, Object t, Object t1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
