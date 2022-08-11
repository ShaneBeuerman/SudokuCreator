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

public class main {

    /*
        Main function
        A board must be provided as a .txt file. If none is
        provided, "No Board Provided." will be displayed.
        If the input is not a file, it will display "Not
        acceptable input."
    */
    public static void main(String[] args) throws Exception {
        if(args.length < 1){
            System.out.println("No Board Provided.");
        }else{
            try {
                File file = new File(args[0]);
                boardRead(file);
            } catch (IOException e) {
                System.out.print("Not acceptable input.");
            }
        }
    }

    /*
     The method boardRead() takes in a file and reads it to produce a 2d int
     array, which will be the Sudoku board for this program.
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
            Scanner scan = new Scanner(System.in);
            System.out.println("How many numbers will be marked blank?");
            int n = -1;
            while (n > 81 || n < 0) {
                n = scan.nextInt();
                if (n > 81 || n < 0) {
                    System.out.println("Try again");
                }
            }
            System.out.println("Thank you.");
            createBoard(board, n);
        }
    }    

    /*
     The method verifyBoard() checks if the given sudoku board can be
     verified horizontally, vertically, and by boxes. If it can be, it
     returns true. If not, it returns false.
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
     The method horizontalVerify() checks to see if the sudoku board given has any duplicates
     in its horizontal lines. It does so by adding each number from a horizontal line into a
     list. It then checks if the list contains any of the numbers already added to it. If a 
     duplicates is found, horizontalVerify returns false.
     */
    public static boolean horizontalVerify(int[][] board) {
        ArrayList<Integer> numbers = new ArrayList<>();
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
     The method verticalVerify() checks to see if the Sudoku board given has any duplicates
     in its vertical lines. It does so by adding each number from a vertical line into a list.
     It then checks if the list contains any of the numbers already added to it. If a duplicate
     is found, verticalVerify returns false.
     */
    public static boolean verticalVerify(int[][] board) {
        ArrayList<Integer> numbers = new ArrayList<>();
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
     The method boxVerify() checks to see if the Sudoku board given has any duplicates
     in its boxes. It works by taking the middle value of a Sudoku box and passes
     it to individualBox(), which returns true or false if the box has duplicates.
     */
    public static boolean boxVerify(int[][] board) {
        ArrayList<Integer> numbers = new ArrayList<>();

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
     The method individualBox() takes in the Sudoku board and the x and y value
     of the Sudoku box. It adds the numbers from the surrounding coordinates in
     the Sudoku box into a set. A set doesn't allow duplicates, so it checks
     if the set is 9. If it has a size of 9, then it has no duplicates and
     returns true.
     */
    public static boolean individualBox(int[][] board, int x, int y) {
        Set<Integer> numbers = new HashSet<>();

        numbers.add(board[x - 1][y + 1]);
        numbers.add(board[x][y + 1]);
        numbers.add(board[x + 1][y + 1]);
        numbers.add(board[x - 1][y]);
        numbers.add(board[x][y]);
        numbers.add(board[x + 1][y]);
        numbers.add(board[x - 1][y - 1]);
        numbers.add(board[x][y - 1]);
        numbers.add(board[x + 1][y - 1]);

        return numbers.size() == 9;
        
    }

    /*
     printBoard() prints a 2d int array.
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
     turnLeft() takes a sudoku board
     and rotates it counterclockwise.
     */
    public static int[][] turnLeft(int[][] board) {
        int k;
        int[][] newBoard = new int[9][9];
        for (int i = 0; i < 9; i++) {
            k = 8;
            for (int j = 0; j < 9; j++) {
                newBoard[j][i] = board[i][k];
                k--;
            }
        }

        return newBoard;
    }
    /*
     turnRight takes a sudoku board and rotates
     it clockwise.
     */

    public static int[][] turnRight(int[][] board) {
        int k;
        int[][] newBoard = new int[9][9];
        for (int i = 0; i < 9; i++) {
            k = 8;
            for (int j = 0; j < 9; j++) {
                newBoard[i][j] = board[k][i];
                k--;
            }
        }

        return newBoard;

    }

    /*
     mirrorDiagonal() takes a sudoku board and 
     mirrors the sudoku across a diagonal line.
     */
    public static int[][] mirrorDiagonal(int[][] board) {
        int k;
        int[][] newBoard = new int[9][9];
        for (int i = 8; i >= 0; i--) {
            k = 0;
            for (int j = 0; j < 9; j++) {
                newBoard[k][i] = board[i][j];
                k++;
            }
        }

        return newBoard;
    }

    /*
     mirrorHorizontal() takes a sudoku board and 
     mirrors the sudoku board across a horizontal
     line.
     */
    public static int[][] mirrorHorizontal(int[][] board) {
        int k;
        int[][] newBoard = new int[9][9];
        for (int i = 0; i < 9; i++) {
            k = 0;
            for (int j = 8; j >= 0; j--) {
                newBoard[j][i] = board[i][k];
                k++;
            }
        }

        return newBoard;
    }

    /*
     mirrorVertical() takes in a sudoku board and returns a mirror
     image of it right back mirrored across a vertical line.
     */
    public static int[][] mirrorVertical(int[][] board) {
        int k;
        int[][] newBoard = new int[9][9];
        for (int i = 0; i < 9; i++) {
            k = 0;
            for (int j = 8; j >= 0; j--) {
                newBoard[i][j] = board[i][k];
                k++;
            }
        }
        return newBoard;
    }

    /*
     swapBoard() swaps the x and y values, creating a new board. If the 
     original board is a verified Sudoku board, so will the newly swapped
     board.
     */
    public static int[][] swapBoard(int[][] board) {
        int[][] newBoard = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
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
            if (buttons[x][y].getText().equals("")) {
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
