package main;

import java.io.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SudokuCreator {

    public static void main(String[] args) throws Exception {
        try {
            File file = new File(args[0]);
            boardRead(file);
        } catch (IOException e) {
            System.out.print("Not acceptable input.");
        }
    }

    /*
    
     */
    public static void boardRead(File file) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(file));
        int[][] board = new int[9][9];
        String temp;
        int i = 0;
        int j = 0;
        while ((temp = br.readLine()) != null) {
            for (int k = 0; k < temp.length(); k++) {
                if (Character.isDigit(temp.charAt(k))) {
                    board[j][i] = Character.getNumericValue(temp.charAt(k));
                    i++;
                }
            }
            j++;
            i = 0;
        }
        if (verifyBoard(board)) {
            createBoard(board, 45);
        }
    }

    /*
    
     */
    public static boolean verifyBoard(int[][] board) {
        if (verticalVerify(board)
                && horizontalVerify(board)
                && boxVerify(board)) {
            System.out.println("The board is good.");
            return true;
        } else {
            System.out.println("Not a good sudoku board.");
            return false;
        }
    }

    /*
    
     */
    public static boolean horizontalVerify(int[][] board) {
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (numbers.contains(board[i][j])) {
                    return false;
                }
                numbers.add(board[i][j]);
            }
            numbers.clear();
        }
        return true;
    }

    /*
    
     */
    public static boolean verticalVerify(int[][] board) {
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (numbers.contains(board[j][i])) {
                    return false;
                }
                numbers.add(board[j][i]);
            }
            numbers.clear();
        }
        return true;
    }

    /*
    
     */
    public static boolean boxVerify(int[][] board) {
        ArrayList<Integer> numbers = new ArrayList<Integer>();

        for (int i = 1; i < 8; i = i + 3) {
            for (int j = 1; j < 8; j = j + 3) {
                if (individualBox(board, j, i) == false) {
                    return false;
                }
            }
        }

        return true;
    }

    /*
    
     */
    public static boolean individualBox(int[][] board, int x, int y) {
        Set<Integer> numbers = new HashSet<Integer>();

        numbers.add(board[x - 1][y + 1]);
        numbers.add(board[x][y + 1]);
        numbers.add(board[x + 1][y + 1]);
        numbers.add(board[x - 1][y]);
        numbers.add(board[x][y]);
        numbers.add(board[x + 1][y]);
        numbers.add(board[x - 1][y - 1]);
        numbers.add(board[x][y - 1]);
        numbers.add(board[x + 1][y - 1]);

        if (numbers.size() != 9) {
            return false;
        }

        return true;
    }

    /*
    
     */
    public static void printBoard(int[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }

    }



    /*
    
     */
    public static int[][] rotateBoard(int[][] board) {
        int temp = 0;
        int[][] newBoard = new int[9][9];
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                newBoard[i][j] = board[j][i];          
            }
        }
        return newBoard;
    }

     /*
      Creates a GUI with 81 buttons and a text field to input your answer
     */
    public static void createBoard(int[][] board, int blanks) {
        JFrame frame = new JFrame("Sudoku");
        JButton[][] buttons = new JButton[9][9];
        JPanel grid = new JPanel();
        GridLayout gridLayout = new GridLayout(9, 9);
        grid.setLayout(gridLayout);
        JPanel textInput = new JPanel();
        JTextField txtInput = new JTextField("", 10);
        Random rand = new Random();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                buttons[i][j] = new JButton("");
                int currenti = i;
                int currentj = j;
                int temp = board[i][j];
                buttons[i][j].setText("" + board[i][j]);
                buttons[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent arg) {
                        try {
                            int intput = Integer.parseInt(txtInput.getText());
                            if (intput == temp) {
                                System.out.println("Correct!");
                                buttons[currenti][currentj].setText("" + intput);
                            } else {
                                System.out.println("Incorrect!");
                            }
                        } catch (NumberFormatException ex) {
                            System.out.println("Not an acceptable answer!");
                        }
                    }
                });
                grid.add(buttons[i][j]);
            }
        }
        int x;
        int y;
        for (int i = 0; i < blanks; i++) {
            x = rand.nextInt(9);
            y = rand.nextInt(9);
            if (buttons[x][y].getText() == "") {
                i--;
            } else {
                buttons[x][y].setText("");
            }
        }

        frame.add(grid);
        textInput.add(txtInput);
        frame.add(textInput, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

 
}
