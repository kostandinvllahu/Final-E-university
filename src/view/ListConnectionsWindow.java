package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import controll.Settings;
import data.DBHandler;
import model.Student;
import view.gui.ButtonRenderer;
import view.gui.StudentButton;
import view.gui.StudentButtonEditor;
import view.gui.UserButton;
import view.gui.UserButtonEditor;

public class ListConnectionsWindow extends Window{

	private static final long serialVersionUID = 5694447836026883304L;

	public ListConnectionsWindow() {
		
		ArrayList<Student> list = DBHandler.db.getMyConnections();
	
		
		if (list.size() == 0) {
			JOptionPane.showMessageDialog(null, "0 connections!");
			throw new IllegalArgumentException() {
				private static final long serialVersionUID = 4783436025141197239L;
				@Override
				public void printStackTrace() { 
				}
			};   
		}
		
		Object[][] data = new Object[list.size()][8];

		int i = 0;
		for (Student student : list) {
			data[i][0] = " " + (i + 1);
			data[i][1] = " " + student.getName();
			data[i][2] = " " + student.getSurname();
			data[i][3] = " " + student.getUsername();
			data[i][4] = " " + ((student.isMinorDegreeGraduated() == true) ? 
									student.getMajorSubject() : 
									student.getMinorSubject());
			data[i][5] = " " + student.getCurrentSemesterNumber();
			data[i][6] = new UserButton("", student) {
				private static final long serialVersionUID = -6252861590543651403L;
				@Override
				public String toString() {
					return "View";
				}
			};
			data[i][7] = new StudentButton("", student) {
				private static final long serialVersionUID = 4117497482580748439L;
				@Override
				public String toString() {
					return "Delete";
				}
			};
			i++;
		}

		Object[] tableHeader = new Object[] {"#", "Name", "Surname",
				"Username", "Subject", "Semester", "", " "};

		DefaultTableModel model = new DefaultTableModel();
		model.setDataVector(data, tableHeader);

		JTable table = new JTable(model);
		 
		table.getColumn("").setCellRenderer(new ButtonRenderer());
		table.getColumn("").setCellEditor(new UserButtonEditor(new JCheckBox()) {
			private static final long serialVersionUID = -7452346365499903627L;
			@Override
			public Object getCellEditorValue() {
				if (isPushed) {
					new ViewUserWindow(((UserButton) button).getUser()).setVisible(true);
				}
				isPushed = false;
				return new String(label);
			}
		});
		table.getColumn(" ").setCellRenderer(new ButtonRenderer());
		table.getColumn(" ").setCellEditor(new StudentButtonEditor(new JCheckBox()) {
			private static final long serialVersionUID = 2428426751296043876L;
			@Override
			public Object getCellEditorValue() {
				if (isPushed) {
					DBHandler.db.removeStudentConnection(((StudentButton) button).getStudent());
					setVisible(false);
				}
				isPushed = false;
				return new String(label);
			}
		});
   
		table.getColumnModel().getColumn(0).setPreferredWidth(50); 
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setPreferredWidth(150);
		table.getColumnModel().getColumn(3).setPreferredWidth(200); 
		table.getColumnModel().getColumn(4).setPreferredWidth(250);
		table.getColumnModel().getColumn(5).setPreferredWidth(150);
		table.getColumnModel().getColumn(6).setPreferredWidth(120);
		table.getColumnModel().getColumn(7).setPreferredWidth(120);
		
		table.setRowHeight(25);
		
		table.getTableHeader().setFont(new Font(table.getTableHeader().getFont().getFontName(), Font.BOLD, 12));
		
		JScrollPane scrollPane = new JScrollPane(table);
		
		JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
		mainPanel.setBackground(Settings.COLOR1);
		mainPanel.setBorder(new LineBorder(Settings.COLOR1, 10));
		mainPanel.add(scrollPane);
		add(mainPanel);

		setMinimumSize(new Dimension(900, 400));
		pack();
		setLocationRelativeTo(null);

	}

}
