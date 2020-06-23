import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class MainFrame extends JFrame{
	
	private SudokuForm sudokuForm;
	public SudokuBoard sudokuBoard;
	public boolean isReloaded;
	
	public MainFrame(){
		
		setTitle("Sudoku");
		setBounds(20,20,800, 600);
		setLayout(new BorderLayout());
		
		isReloaded = false;
		
		sudokuBoard = new SudokuBoard(this);
		sudokuForm = new SudokuForm(sudokuBoard, this);
		
		add(sudokuBoard, BorderLayout.WEST);
		add(sudokuForm, BorderLayout.CENTER);
		//f.add(cell, BorderLayout.EAST);

		//f.add(dva, BorderLayout.NORTH);

		setVisible(true);
		pack();
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	

	
	public void reloadSudokuBoard() {
		//isReloaded = true;
		//reval<idate();
		String novaTezavnost = (sudokuForm.getTezavnost());
		//remove(sudokuBoard);
		//sudokuForm.setTezavnost(novaTezavnost);
		//sudokuBoard.board(sudokuBoard.stevilaIgre(), sudokuBoard.nastaviTezavnost(novaTezavnost));
		//System.out.println("reload");
		sudokuBoard.nastaviTezavnost(novaTezavnost);
		sudokuBoard = new SudokuBoard(this);
		//dokuBoard.nastaviTezavnost(novaTezavnost);
		add(sudokuBoard, BorderLayout.WEST);
		SwingUtilities.updateComponentTreeUI(sudokuBoard);
		sudokuForm.setTezavnost(novaTezavnost);
	}
	

}
