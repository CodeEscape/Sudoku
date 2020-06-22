import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class SudokuBoard extends JPanel{

	private JTextField[][] text = new JTextField[9][9];
	private SudokuForm sudokuForm;
	private MainFrame mainFrame;

	public SudokuBoard(MainFrame mainFrame) {

		setBounds(20,20,800, 600);
		setLayout(new GridBagLayout());
		
		this.mainFrame = mainFrame;
		sudokuForm = new SudokuForm(this, mainFrame);

		board(stevilaIgre(), nastaviTezavnost((String)sudokuForm.getTezavnost()));
		//System.out.println(text[1][1].getText());
		//Ta funckija pregleda èe je sudoku pravilen. Èe je vrne true, èe je napaèen vrne false
		//boolean isValid = ValidateSudoku(stevila1);

		Border innerBorder = BorderFactory.createTitledBorder(BorderFactory.createTitledBorder(""));
		Border outerBorder = BorderFactory.createLineBorder(Color.BLACK, 3);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

		//setVisible(false);

	}

	public void preveriSudoku() {
		int [][]sudokuStevila = stevilaIgre();
		
		/*
		for(int i = 0; i < 9; i ++) {
			for(int j = 0; j < 9; j ++) {
				System.out.println(text[i][j].getText());
				
				if(Integer.parseInt((String)text[i][j].getText()) == sudokuStevila[i][j]) {
					JOptionPane.showMessageDialog(SudokuBoard.this, "Èestitam, rešili ste sudoku!",
                            "Uspešna rešitev.", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
		*/
		boolean sudokuIsValid = true;
		int [][] resenSudoku = new int [9][9];
		for(int i = 0; i < 9; i ++) {
			for(int j = 0; j < 9; j ++) {
			
			//System.out.println(Integer.parseInt((String)text[i][j].getText()));
			
			resenSudoku[i][j] = Integer.parseInt((String)text[i][j].getText());
				
			String value = (String)text[i][j].getText();
			int parsedValue = 0;
			int pravilenValue = sudokuStevila[i][j];
			if(value != null && !value.isEmpty()) {
				parsedValue = Integer.parseInt(value);
			}
			
			//System.out.println(resenSudoku[i][j]);
			if(pravilenValue != parsedValue) {
				sudokuIsValid = false;
				text[i][j].setBackground(Color.RED);
			}
			}
		}
		
		if(sudokuIsValid) {
			JOptionPane.showMessageDialog(SudokuBoard.this, "Èestitam, rešili ste sudoku!",
                    "Uspešna rešitev.", JOptionPane.INFORMATION_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(SudokuBoard.this, "Rešitev ni pravilna",
                    "Napaèna rešitev.", JOptionPane.ERROR_MESSAGE);
		}
		/*
		for(int i = 0; i < 9; i ++) {
			for(int j = 0; j < 9; j ++) {
			
			System.out.println(Integer.parseInt((String)text[i][j].getText()));
			}
		}
		*/
	}

	public void board(int[][] stevila, int nicle) {
		int steviloNicel = nastaviTezavnost(sudokuForm.getTezavnost());
		int[][] stevilaZaBoard = stevilaIgre();

		//Zamenja številke na random mestih v sudokuju z 0
		//int steviloNicel = 50;
		int[][] rezultat = SetZerosOnRandomPlaces(stevilaZaBoard, steviloNicel);

		//Pregleda še enkrat sodoku potem ko so zamenjane številke
		//////isValid = ValidateSudoku(stevilaZaBoard);

		for(int i = 0; i < 9; i ++) {
			for(int j = 0; j < 9; j ++) {
				int st = stevilaZaBoard[i][j];
				
				Font font = new Font("Georgia",Font.PLAIN, 25);
				text[i][j] = new JTextField();
				text[i][j].setPreferredSize(new Dimension(40, 40));
				//text[i][j].setFont(font);
				text[i][j].setHorizontalAlignment(JTextField.CENTER);
				/*
						if (stevila1[randomStolpec][randomVrstica] >= 1 && stevila1[randomStolpec][randomVrstica]  <= 9){
						Jtextfield = stevila1[randomStolpec][randomVrstica];
						}
						else{
						Jtextfield = "";
						}
				 */
				text[i][j].setDocument(new JTextFieldLimit(1));
				text[i][j].setText("" + st + "");
				if(text[i][j].getText().equals("0")) {
					text[i][j].setText("");
				}

				GridBagConstraints c = new GridBagConstraints();
				c.weightx = 1.0;
				c.weighty = 1.0;
				c.fill = GridBagConstraints.HORIZONTAL;
				c.insets = new Insets(0, 0, 0, 0);
				c.gridx = i;
				c.gridy = j;

				add(text[i][j], c);

				text[i][j].setBorder((BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK)));
				if(i == 5 || i == 2) {
					text[i][j].setBorder((BorderFactory.createMatteBorder(1, 1, 1, 4, Color.BLACK)));
				}
				if (j == 5 || j == 2) {
					text[i][j].setBorder((BorderFactory.createMatteBorder(1, 1, 4, 1, Color.BLACK)));
				}
				if((i == 5 && j == 5) || (i == 2 && j == 2) || (i == 5 && j == 2) || (i == 2 && j == 5)) {
					text[i][j].setBorder((BorderFactory.createMatteBorder(1, 1, 4, 4, Color.BLACK)));
				}
			}
		}

	}

	public int[][] stevilaIgre(){
		//naredit switch, ki izbira iz števil
		//int rand = (int)(Math.random() * 2) + 1;
		int [][]stevila = {{4,3,5,2,6,9,7,8,1},{6,8,2,5,7,1,4,9,3},{1,9,7,8,3,4,5,6,2},{8,2,6,1,9,5,3,4,7},{3,7,4,6,8,2,9,1,5},{9,5,1,7,4,3,6,2,8},{5,1,9,3,2,6,8,7,4},{2,4,8,9,5,7,1,3,6},{7,6,3,4,1,8,2,5,9}};
		
		/*
		int[][] stevila = new int [9][9];
		
		
		if(rand == 1) {
			stevila = new int [][] {{4,3,5,2,6,9,7,8,1},{6,8,2,5,7,1,4,9,3},{1,9,7,8,3,4,5,6,2},{8,2,6,1,9,5,3,4,7},{3,7,4,6,8,2,9,1,5},{9,5,1,7,4,3,6,2,8},{5,1,9,3,2,6,8,7,4},{2,4,8,9,5,7,1,3,6},{7,6,3,4,1,8,2,5,9}};
		}
		else if(rand == 2) {
			stevila = new int[][] {{1,7,4,3,5,2,9,6,8},{5,3,6,8,7,1,6,4,5,3},{2,9,8,7,1,6,4,5,3},{4,2,3,1,7,8,6,9,5},{8,5,7,2,6,9,3,4,1},{9,6,1,4,3,5,7,8,2},{3,8,2,6,4,7,5,1,9},{7,4,9,5,2,1,8,3,6},{6,1,5,9,8,3,2,7,4}};
		}
		
		switch(rand) {
		case 1:
			stevila = new int[][] {{4,3,5,2,6,9,7,8,1},{6,8,2,5,7,1,4,9,3},{1,9,7,8,3,4,5,6,2},{8,2,6,1,9,5,3,4,7},{3,7,4,6,8,2,9,1,5},{9,5,1,7,4,3,6,2,8},{5,1,9,3,2,6,8,7,4},{2,4,8,9,5,7,1,3,6},{7,6,3,4,1,8,2,5,9}};
			break;
		case 2:
			stevila = new int[][] {{1,7,4,3,5,2,9,6,8},{5,3,6,8,7,1,6,4,5,3},{2,9,8,7,1,6,4,5,3},{4,2,3,1,7,8,6,9,5},{8,5,7,2,6,9,3,4,1},{9,6,1,4,3,5,7,8,2},{3,8,2,6,4,7,5,1,9},{7,4,9,5,2,1,8,3,6},{6,1,5,9,8,3,2,7,4}};
			break;
		};
		//System.out.println(stevila);
		*/
		return stevila;
	}
	/*
	public void newBoard(int[][] stevila) {
		int[][] novaStevila = stevilaIgre();
		board(novaStevila, nastaviTezavnost(sudokuForm.getTezavnost()));
		
	}
	*/
	public int nastaviTezavnost(String tezavnost) {
		int nicle = 0;
		String tezavnostIgre = tezavnost;
		if(tezavnostIgre.equals("Lahko")) {
			nicle = 50;
		}
		if(tezavnostIgre.equals("Srednje")) {
			nicle = 60;
		}
		if(tezavnostIgre.equals("Tezko")) {
			nicle = 70;
		}
		return nicle;
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
/*
 * 
 * 
		setBounds(20,20,800, 600);
		setLayout(new GridBagLayout());

		Font font = new Font("Georgia",Font.PLAIN, 25);

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

				text[i][j].setDocument(new JTextFieldLimit(1));
				text[i][j].setText("" + st + "");
				if(text[i][j].getText().equals("0")) {
					text[i][j].setText("");
				}

				GridBagConstraints c = new GridBagConstraints();
				c.weightx = 1.0;
				c.weighty = 1.0;
				c.fill = GridBagConstraints.HORIZONTAL;
				c.insets = new Insets(0, 0, 0, 0);
				c.gridx = i;
				c.gridy = j;

				add(text[i][j], c);

				text[i][j].setBorder((BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK)));
				if(i == 5 || i == 2) {
					text[i][j].setBorder((BorderFactory.createMatteBorder(1, 1, 1, 4, Color.BLACK)));
				}
				if (j == 5 || j == 2) {
					text[i][j].setBorder((BorderFactory.createMatteBorder(1, 1, 4, 1, Color.BLACK)));
				}
				if((i == 5 && j == 5) || (i == 2 && j == 2) || (i == 5 && j == 2) || (i == 2 && j == 5)) {
					text[i][j].setBorder((BorderFactory.createMatteBorder(1, 1, 4, 4, Color.BLACK)));
				}

			}
			System.out.println();
 */

