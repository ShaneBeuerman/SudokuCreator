package main;

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

    public static void main(String[] args) {
        // Verified sudoku board for testing
        int[] sudokuBoard = {1, 6, 3, 4, 5, 9, 8, 2, 7, 4, 2, 8, 3, 7, 1, 5, 6, 9, 7, 9, 5, 8, 2, 6, 1, 4, 3, 5, 4, 2, 7, 6, 8, 3, 9, 1,
            3, 1, 6, 5, 9, 2, 4, 7, 8, 8, 7, 9, 1, 4, 3, 2, 5, 6, 9, 3, 7, 2, 1, 5, 6, 8, 4, 6, 5, 1, 9, 8, 4, 7, 3, 2, 2, 8, 4, 6, 3, 7, 9, 1, 5
        };
        // Check if sudokuBoard follows the correct criteria
        if (horizontal(sudokuBoard)) {
            System.out.println("Success");
        } else {
            System.out.println("Failure");
        }
        if (vertical(sudokuBoard)) {
            System.out.println("Success");
        } else {
            System.out.println("Failure");
        }
        if (box(sudokuBoard)) {
            System.out.println("Success");
        } else {
            System.out.println("Failure");
        }
        //Create a GUI For the sudoku board and set up how many spaces are removed.
        //In this case, 10 spaces will be removed.
        createSudoku(sudokuBoard, 10);

    }
    /*
        The method horizontal() checks to see if the sudoku board given has any doubles
        in its horizontal lines. It does so by seperating the board into nine different
        lines and checks each one. If even one of the lines has a double, the horizontal
        test fails and returns a false. If it passes every test, it returns a true.
    */
    public static boolean horizontal(int[] sudoku) {
        int[] line1 = {sudoku[0], sudoku[1], sudoku[2], sudoku[3], sudoku[4], sudoku[5], sudoku[6], sudoku[7], sudoku[8]};
        int[] line2 = {sudoku[9], sudoku[10], sudoku[11], sudoku[12], sudoku[13], sudoku[14], sudoku[15], sudoku[16], sudoku[17]};
        int[] line3 = {sudoku[18], sudoku[19], sudoku[20], sudoku[21], sudoku[22], sudoku[23], sudoku[24], sudoku[25], sudoku[26]};
        int[] line4 = {sudoku[27], sudoku[28], sudoku[29], sudoku[30], sudoku[31], sudoku[32], sudoku[33], sudoku[34], sudoku[35]};
        int[] line5 = {sudoku[36], sudoku[37], sudoku[38], sudoku[39], sudoku[40], sudoku[41], sudoku[42], sudoku[43], sudoku[44]};
        int[] line6 = {sudoku[45], sudoku[46], sudoku[47], sudoku[48], sudoku[49], sudoku[50], sudoku[51], sudoku[52], sudoku[53]};
        int[] line7 = {sudoku[54], sudoku[55], sudoku[56], sudoku[57], sudoku[58], sudoku[59], sudoku[60], sudoku[61], sudoku[62]};
        int[] line8 = {sudoku[63], sudoku[64], sudoku[65], sudoku[66], sudoku[67], sudoku[68], sudoku[69], sudoku[70], sudoku[71]};
        int[] line9 = {sudoku[72], sudoku[73], sudoku[74], sudoku[75], sudoku[76], sudoku[77], sudoku[78], sudoku[79], sudoku[80]};
        int counter = 0;

        if (findDoubles(line1) == true) {
            counter++;
        }
        if (findDoubles(line2) == true) {
            counter++;
        }
        if (findDoubles(line3) == true) {
            counter++;
        }
        if (findDoubles(line4) == true) {
            counter++;
        }
        if (findDoubles(line5) == true) {
            counter++;
        }
        if (findDoubles(line6) == true) {
            counter++;
        }
        if (findDoubles(line7) == true) {
            counter++;
        }
        if (findDoubles(line8) == true) {
            counter++;
        }
        if (findDoubles(line9) == true) {
            counter++;
        }

        if (counter == 9) {
            return true;
        }

        return false;
    }
    
        /*
        The method vertical() checks to see if the sudoku board given has any doubles
        in its vertical lines. It does so by seperating the board into nine different
        lines and checks each one. If even one of the lines has a double, the vertical
        test fails and returns a false. If it passes every test, it returns a true.
    */
    public static boolean vertical(int[] sudoku) {

        int[] line1 = {sudoku[0], sudoku[9], sudoku[18], sudoku[27], sudoku[36], sudoku[45], sudoku[54], sudoku[63], sudoku[72]};
        int[] line2 = {sudoku[1], sudoku[10], sudoku[19], sudoku[28], sudoku[37], sudoku[46], sudoku[55], sudoku[64], sudoku[73]};
        int[] line3 = {sudoku[2], sudoku[11], sudoku[20], sudoku[29], sudoku[38], sudoku[47], sudoku[56], sudoku[65], sudoku[74]};
        int[] line4 = {sudoku[3], sudoku[12], sudoku[21], sudoku[30], sudoku[39], sudoku[48], sudoku[57], sudoku[66], sudoku[75]};
        int[] line5 = {sudoku[4], sudoku[13], sudoku[22], sudoku[31], sudoku[40], sudoku[49], sudoku[58], sudoku[67], sudoku[76]};
        int[] line6 = {sudoku[5], sudoku[14], sudoku[23], sudoku[32], sudoku[41], sudoku[50], sudoku[59], sudoku[68], sudoku[77]};
        int[] line7 = {sudoku[6], sudoku[15], sudoku[24], sudoku[33], sudoku[42], sudoku[51], sudoku[60], sudoku[69], sudoku[78]};
        int[] line8 = {sudoku[7], sudoku[16], sudoku[25], sudoku[34], sudoku[43], sudoku[52], sudoku[61], sudoku[70], sudoku[79]};
        int[] line9 = {sudoku[8], sudoku[17], sudoku[26], sudoku[35], sudoku[44], sudoku[53], sudoku[62], sudoku[71], sudoku[80]};
        int counter = 0;

        if (findDoubles(line1) == true) {
            counter++;
        }
        if (findDoubles(line2) == true) {
            counter++;
        }
        if (findDoubles(line3) == true) {
            counter++;
        }
        if (findDoubles(line4) == true) {
            counter++;
        }
        if (findDoubles(line5) == true) {
            counter++;
        }
        if (findDoubles(line6) == true) {
            counter++;
        }
        if (findDoubles(line7) == true) {
            counter++;
        }
        if (findDoubles(line8) == true) {
            counter++;
        }
        if (findDoubles(line9) == true) {
            counter++;
        }

        if (counter == 9) {
            return true;
        }
        return false;
    }

    /*
        The method box() checks to see if the sudoku board given has any doubles
        in its boxes. It does so by seperating the board into nine different
        boxes and checks each one. If even one of the boxes has a double, the box
        test fails and returns a false. If it passes every test, it returns a true.
    */
    public static boolean box(int[] sudoku) {
        int[] line1 = {sudoku[0], sudoku[1], sudoku[2], sudoku[9], sudoku[10], sudoku[11], sudoku[18], sudoku[19], sudoku[20]};
        int[] line2 = {sudoku[3], sudoku[4], sudoku[5], sudoku[12], sudoku[13], sudoku[14], sudoku[21], sudoku[22], sudoku[23]};
        int[] line3 = {sudoku[6], sudoku[7], sudoku[8], sudoku[15], sudoku[16], sudoku[17], sudoku[24], sudoku[25], sudoku[26]};
        int[] line4 = {sudoku[27], sudoku[28], sudoku[29], sudoku[36], sudoku[37], sudoku[38], sudoku[45], sudoku[46], sudoku[47]};
        int[] line5 = {sudoku[30], sudoku[31], sudoku[32], sudoku[39], sudoku[40], sudoku[41], sudoku[48], sudoku[49], sudoku[50]};
        int[] line6 = {sudoku[33], sudoku[34], sudoku[35], sudoku[42], sudoku[43], sudoku[44], sudoku[51], sudoku[52], sudoku[53]};
        int[] line7 = {sudoku[54], sudoku[55], sudoku[56], sudoku[63], sudoku[64], sudoku[65], sudoku[72], sudoku[73], sudoku[74]};
        int[] line8 = {sudoku[57], sudoku[58], sudoku[59], sudoku[66], sudoku[67], sudoku[68], sudoku[75], sudoku[76], sudoku[77]};
        int[] line9 = {sudoku[60], sudoku[61], sudoku[62], sudoku[69], sudoku[70], sudoku[71], sudoku[78], sudoku[79], sudoku[80]};
        int counter = 0;

        if (findDoubles(line1) == true) {
            counter++;
        }
        if (findDoubles(line2) == true) {
            counter++;
        }
        if (findDoubles(line3) == true) {
            counter++;
        }
        if (findDoubles(line4) == true) {
            counter++;
        }
        if (findDoubles(line5) == true) {
            counter++;
        }
        if (findDoubles(line6) == true) {
            counter++;
        }
        if (findDoubles(line7) == true) {
            counter++;
        }
        if (findDoubles(line8) == true) {
            counter++;
        }
        if (findDoubles(line9) == true) {
            counter++;
        }

        if (counter == 9) {
            return true;
        }
        return false;
    }
    
    /*
        Simple test to find doubles in a given array. If it finds a double, it
        returnsfalse. If it doesn't find any double, it returns true.
    */
    public static boolean findDoubles(int[] solve) {
        for (int i = 0; i < solve.length; i++) {
            for (int j = 0; j < solve.length; j++) {
                if (i != j) {
                    if (solve[i] == solve[j]) {
                        return false;
                    }
                }
            }
        }

        return true;
    }
    
    /*
        Simple method to find if an object is in an array
    */
    public static boolean findInside(int[] library, int findMe) {
        for (int l = 0; l < library.length; l++) {
            if (library[l] == findMe) {
                return false;
            }
        }
        return true;
    }
    
    /*
        Creates a GUI with 81 buttons and a textfield to input your answer
    */
    public static void createSudoku(int[] board, int blanks) {
        JFrame frame = new JFrame("Sudoku");
        JButton[] buttons = new JButton[81];
        JPanel grid = new JPanel();
        GridLayout gridLayout = new GridLayout(9, 9);
        grid.setLayout(gridLayout);
        JPanel textInput = new JPanel();
        JTextField txtInput = new JTextField("", 10);
        Random blank = new Random();
        int[] totalblanks = new int[blanks];
        for (int j = 0; j < blanks; j++) {
            int temp = blank.nextInt(81);
            if (findInside(totalblanks, temp) == false) {
                j--;
            } else {
                totalblanks[j] = temp;
            }
        }
        for (int i = 0; i < 81; i++) {
            int temp = board[i];
            int currentButton = i;
            buttons[i] = new JButton("" + temp);
            for (int j = 0; j < totalblanks.length; j++) {
                if (i == totalblanks[j]) {
                    buttons[i].setText("");
                }
            }
            buttons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg) {
                    try {
                        int intput = Integer.parseInt(txtInput.getText());
                        if (intput == temp) {
                            System.out.println("Correct!");
                            buttons[currentButton].setText("" + intput);
                        } else {
                            System.out.println("Incorrect!");
                        }
                    } catch (NumberFormatException ex) {
                        System.out.println("Not an acceptable answer!");
                    }
                }
            });
            grid.add(buttons[i]);
        }
        frame.add(grid);
        textInput.add(txtInput);
        frame.add(textInput, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
