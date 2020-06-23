import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class SudokuForm extends JPanel {

	private JButton novaIgra;
	private JButton prijava;
	private JButton preveri;
	private JComboBox tezavnost;
	private JLabel izbTezavnost;
	private JLabel prijaviSe;
	private JLabel cas;

	private PrijavaUporabnika uporabnik;
	private SudokuBoard sudokuBoard;
	private MainFrame mainFrame;

	public SudokuForm(SudokuBoard sudokuBoard, MainFrame mainFrame) {

		this.sudokuBoard = sudokuBoard;

		this.mainFrame = mainFrame;

		Dimension dim = getPreferredSize();
		dim.width = 400;
		setPreferredSize(dim);
		setMinimumSize(dim);
		setBounds(20,20,800, 600);

		setBackground(new Color(204, 224, 255));


		componentsOdBoard();
		layoutComponents();

	}

	public void componentsOdBoard() {

		Color lightRed = new Color(255, 230, 230);
		novaIgra = new JButton("Nova igra!");
		novaIgra.setBackground(lightRed);
		prijava = new JButton("Prijava");
		prijava.setBackground(lightRed);
		preveri = new JButton("Preveri");
		preveri.setBackground(lightRed);
		cas = new JLabel("" + "" + "");

		izbTezavnost = new JLabel("Izberite težavnost");
		prijaviSe = new JLabel("Prijava uporabnika");

		tezavnost = new JComboBox();
		tezavnost.setBackground(lightRed);
		setDifficultyInCombo();

		tezavnost.setPreferredSize(new Dimension(100, 25));

		prijava.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}

		});

		preveri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					sudokuBoard.preveriSudoku();
				}
				catch(NumberFormatException manjkajocePolje) {
					JOptionPane.showMessageDialog(SudokuForm.this, "Niso izpolnjena vsa polja, izpolnite vsa polja in preverite ponovno.",
							"Prazna polja.", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		novaIgra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainFrame.reloadSudokuBoard();
				System.out.println("novaIgra");
			}
		});
	}

	public void setDifficultyInCombo() {
		DefaultComboBoxModel tezavnostModel = new DefaultComboBoxModel();
		setIndexInCombo();
		tezavnostModel.addElement("Lahko");
		tezavnostModel.addElement("Srednje");
		tezavnostModel.addElement("Tezko");
		tezavnost.setModel(tezavnostModel);

		//tezavnost.setSelectedIndex(novIndex(getTezavnost()));	
	}

	public void setIndexInCombo() {
		if(tezavnost.getSelectedItem() == null) {
			
		}else {
		tezavnost.setSelectedIndex(novIndex(getTezavnost()));	
		}
	}

	public String getTezavnost() {
		System.out.println(tezavnost.getSelectedItem());
		return (String)tezavnost.getSelectedItem();
	}

	public void setTezavnost(JComboBox string) {
		this.tezavnost = string;
	}

	public int novIndex(String string) {
		int index = 0;
		String izbranIndex = string;
		if(izbranIndex == null || izbranIndex.isEmpty()) {
		
		}else {
			switch(izbranIndex) {
			case "Lahko":
				index = 0;
				break;
			case "Srednje":
				index = 1;
				break;
			case "Tezko":
				index = 2;
				break;
			default:
				return 0;
			}
			
		}
		return index;
	}

	private long timeStart() {
		long start = System.currentTimeMillis();
		return start;
	}

	private long totalTime() {
		long end = timeStart() - System.currentTimeMillis();
		return end;
	}

	public void layoutComponents() {

		setLayout(new GridBagLayout());

		GridBagConstraints gc = new GridBagConstraints();

		////////First row /////////////////

		gc.gridy = 0;

		gc.weightx = 1;
		gc.weighty = 0.1;

		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(novaIgra, gc);

		gc.gridx = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 5, 0, 0);
		add(preveri, gc);

		//// Nova vrsta

		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.1;

		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.insets = new Insets(0, 0, 0, 10);
		gc.anchor = GridBagConstraints.LINE_END;
		add(prijaviSe, gc);

		gc.gridx = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(prijava, gc);

		//// Nova vrsta

		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.1;

		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.insets = new Insets(0, 0, 0, 10);
		gc.anchor = GridBagConstraints.LINE_END;
		add(izbTezavnost, gc);

		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.LINE_START;
		add(tezavnost, gc);

		//// Nova vrsta

		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.1;

		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.insets = new Insets(0, 0, 0, 10);
		gc.anchor = GridBagConstraints.LINE_END;
		add(cas, gc);

	}
}
