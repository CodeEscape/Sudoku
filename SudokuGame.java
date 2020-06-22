import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;

public class SudokuGame {

	//private static JTextField[][] text = new JTextField[9][9];;


	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	new MainFrame();
            }
        });
	}
}
		
		
/*
		JFrame f = new JFrame("Sudoku");
		f.setBounds(20,20,800, 600);
		f.setLayout(new BorderLayout());
		JPanel ena = new JPanel();
		ena.setSize(200,  400);
		JPanel dva = new JPanel();
		dva.setLayout(new FlowLayout());

		Font font = new Font("Georgia",Font.PLAIN, 25);

		ena.setBounds(20,20,800, 600);
		ena.setLayout(new GridBagLayout());

		SudokuForm sudokuForm = new SudokuForm();
		//SudokuCell cell = new SudokuCell();


		int [][] stevila1= {{4,3,5,2,6,9,7,8,1},{6,8,2,5,7,1,4,9,3},{1,9,7,8,3,4,5,6,2},{8,2,6,1,9,5,3,4,7},
				{3,7,4,6,8,2,9,1,5},{9,5,1,7,4,3,6,2,8},{5,1,9,3,2,6,8,7,4},{2,4,8,9,5,7,1,3,6},{7,6,3,4,1,8,2,5,9}};

		//Ta funckija pregleda èe je sudoku pravilen. Èe je vrne true, èe je napaèen vrne false
		boolean isValid = ValidateSudoku(stevila1);

		//Zamenja številke na random mestih v sudokuju z 0
		int steviloNicel = 50;
		int[][] rezultat = SetZerosOnRandomPlaces(stevila1, steviloNicel);

		//Pregleda še enkrat sodoku potem ko so zamenjane številke
		isValid = ValidateSudoku(stevila1);
/*
		Integer [] colm = {1,2,3,4,5,6,7,8,9};
		List<Integer> ints = new ArrayList<Integer>(Arrays.asList(colm));
		Random rand =  new Random();
*
		for(int i = 0; i < 9; i ++) {
			for(int j = 0; j < 9; j ++) {
				int st = stevila1[i][j];
				
				text[i][j] = new JTextField();
				text[i][j].setPreferredSize(new Dimension(40, 40));
				text[i][j].setFont(font);
				text[i][j].setHorizontalAlignment(JTextField.CENTER);
				/*
				if (stevila1[randomStolpec][randomVrstica] >= 1 && stevila1[randomStolpec][randomVrstica]  <= 9){
				Jtextfield = stevila1[randomStolpec][randomVrstica];
				}
				else{
				Jtextfield = "";
				}
				*
				text[i][j].setDocument(new JTextFieldLimit(1));
				text[i][j].setText("" + st + "");
				if(text[i][j].getText().equals("0")) {
					text[i][j].setText("");
				}
				
				GridBagConstraints c = new GridBagConstraints();
				c.weightx = 1.0;
				c.weighty = 1.0;
				c.fill = GridBagConstraints.HORIZONTAL;
				c.gridx = i;
				c.gridy = j;

				ena.add(text[i][j], c);
			}
			System.out.println();
		}


		f.add(ena, BorderLayout.WEST);
		f.add(sudokuForm, BorderLayout.CENTER);
		//f.add(cell, BorderLayout.EAST);

		//f.add(dva, BorderLayout.NORTH);

		f.setVisible(true);
		f.pack();
		f.setResizable(false);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private static int[][] SetZerosOnRandomPlaces(int[][] stevila1, int steviloNicel){

		if (steviloNicel > 81){
			return stevila1;
		}


		while (steviloNicel != 0){

			int randomStolpec = new Random().nextInt(9);
			int randomVrstica = new Random().nextInt(9);

			if (stevila1[randomStolpec][randomVrstica] == 0){
				continue;
			}

			stevila1[randomStolpec][randomVrstica] = 0;
			steviloNicel = steviloNicel - 1;
		}

		return stevila1;
	}

	private static boolean ValidateSudoku(int[][] stevila) {

		boolean rowsAreValid = RowsOrColumnsAreValid(stevila, "row");
		if (rowsAreValid == false){
			return false;
		}

		boolean columnsAreValid = RowsOrColumnsAreValid(stevila, "column");
		if (columnsAreValid == false){
			return false;
		}
		return true;
	}

	private static boolean RowsOrColumnsAreValid(int[][] stevila, String validating) {

		if (validating != "row" && validating != "column"){
			return false;
		}

		for (int i = 0; i < stevila.length; i++){

			List<int[][]> distinctNumbers = new ArrayList<int[][]>();

			int sum = 0;
			int place = 0;
			for (int j = 0; j < stevila[0].length; j++){
				place++;
				if (validating == "row"){

					sum += stevila[i][j];

					//distinctNumbers.add(place, stevila[i][j]);//(index, stevila[i][j]);
				}
				else{
					sum += stevila[j][i];
					//distinctNumbers.add(stevila[j][i]);
				}
			}
			if (sum != 45){
				return false;
			}
			distinctNumbers = distinctNumbers.stream().distinct().collect(Collectors.toList());
			if (distinctNumbers.size() != 9){
				return false;
			}
		}
		return true;
	}

	private static boolean areQubesValid(int [][] stevila) {
		int[][]sudoku = new int [9][9];
		int[][]vsote = new int [3][3];
		for(int i = 0; i < 9; i++) {
			int stVrstice = i % 3;
			for(int j = 0; j < 9; j++) {
				int stolpca = j % 3;
				vsote[stVrstice][stolpca] = vsote[stVrstice][stolpca] + sudoku[i][j];
			}
		}
		for(int k = 0; k < 3; k++) {
			for(int l = 0; l < 3; l++) {
				if(vsote[k][l] != 45) {
					return false;
				}
			}
		}
		return true;
	}
}

*/
