package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Arrays;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import controll.Settings;
import data.DBHandler;
import view.gui.ButtonRenderer;
import view.gui.StringComponentDataButton;
import view.gui.StringDataButtonEditor;

public class ListMajorSubjectsWindow extends Window {

	private static final long serialVersionUID = -8763889980015737796L;
	
	public ListMajorSubjectsWindow() {

		setTitle(Settings.SYSTEM_TITLE + " - Major Subjects");
		
		String[] list = Arrays.copyOfRange(DBHandler.db.getMajorSubjects(), 1, DBHandler.db.getMajorSubjects().length);
		
		if (list.length == 0) {
			JOptionPane.showMessageDialog(null, "No data!");
			throw new IllegalArgumentException(); 
		}

		Object[][] data = new Object[list.length][3];
		for (int i = 0; i < data.length; i++) {
			data[i][0] = " " + (i + 1);
			data[i][1] = " " + list[i];
			data[i][2] = new StringComponentDataButton("", list[i], this) {
				private static final long serialVersionUID = 4117497482580748439L;
				@Override
				public String toString() {
					return "Delete";
				}
			};
		}
		Object[] tableHeader = new Object[] {"#", "Subject", ""};

		DefaultTableModel model = new DefaultTableModel();
		model.setDataVector(data, tableHeader);

		JTable table = new JTable(model);
		table.getColumn("").setCellRenderer(new ButtonRenderer());
		table.getColumn("").setCellEditor(new StringDataButtonEditor(new JCheckBox()));

		table.getColumnModel().getColumn(0).setPreferredWidth(50); 
		table.getColumnModel().getColumn(1).setPreferredWidth(250);
		table.getColumnModel().getColumn(2).setPreferredWidth(50);

		table.setRowHeight(25);
		table.getTableHeader().setFont(new Font(table.getTableHeader().getFont().getFontName(), Font.BOLD, 12));
		
		JScrollPane scrollPane = new JScrollPane(table);
		
		JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
		mainPanel.setBackground(Settings.COLOR1);
		mainPanel.setBorder(new LineBorder(Settings.COLOR1, 10));
		mainPanel.add(scrollPane);
		add(mainPanel);

		setMinimumSize(new Dimension(500, 500));
		
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		revalidate();
		setResizable(false);

	}
	
}
