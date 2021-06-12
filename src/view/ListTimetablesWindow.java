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
import view.gui.TimetableButton;
import view.gui.TimetableButtonEditor;
import view.gui.ButtonRenderer;
import model.Timetable;
import util.Util;  

public class ListTimetablesWindow extends Window {
	
	private static final long serialVersionUID = 8969121511313736097L;

	public ListTimetablesWindow() {
		
		ArrayList<Timetable> list = DBHandler.db.getTimetables();
		
		if (list.size() == 0) {
			JOptionPane.showMessageDialog(null, "No data!");
			throw new IllegalArgumentException(); 
		}
		
		Object[][] data = new Object[list.size()][6];

		int i = 0;
		for (Timetable timetable : list) {
			data[i][0] = " " + (i + 1);
			data[i][1] = " " + timetable.getTitle();
			data[i][2] = " " + Util.dateToString(timetable.getStartDate());
			data[i][3] = " " + Util.dateToString(timetable.getEndDate());
			data[i][4] = new TimetableButton("", timetable) {
				private static final long serialVersionUID = 4117497482580748439L;
				@Override
				public String toString() {
					return "View";
				}
			};
			data[i][5] = new TimetableButton("", timetable) {
				private static final long serialVersionUID = -6252861590543651403L;
				@Override
				public String toString() {
					return "Delete";
				}
			};
		}

		Object[] tableHeader = new Object[] {"#", "Title", "Start Date",
				"End Date", "Schedule", " "};

		DefaultTableModel model = new DefaultTableModel();
		model.setDataVector(data, tableHeader);

		JTable table = new JTable(model);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(50); 
		table.getColumnModel().getColumn(1).setPreferredWidth(250);
		table.getColumnModel().getColumn(2).setPreferredWidth(150);
		table.getColumnModel().getColumn(3).setPreferredWidth(150); 
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.getColumnModel().getColumn(5).setPreferredWidth(100);
		
		table.setRowHeight(25);
		
		table.getTableHeader().setFont(new Font(table.getTableHeader().getFont().getFontName(), Font.BOLD, 12));

		table.getColumn("Schedule").setCellRenderer(new ButtonRenderer());
		table.getColumn("Schedule").setCellEditor(new TimetableButtonEditor(new JCheckBox()) {
			private static final long serialVersionUID = -2682863002618839997L; 
			@Override
			public Object getCellEditorValue() {
				if (isPushed) {
					new ViewTimetableCoursesScheduleWindow(((TimetableButton) button).getTimetable()).setVisible(true);
				}
				isPushed = false;
				return new String(label);
			}
		});

		table.getColumn(" ").setCellRenderer(new ButtonRenderer());
		table.getColumn(" ").setCellEditor(new TimetableButtonEditor(new JCheckBox()) {
			private static final long serialVersionUID = -2682863002618839997L; 
			@Override
			public Object getCellEditorValue() {
				if (isPushed) {
					DBHandler.db.deleteTimetable(((TimetableButton) button).getTimetable());
					setVisible(false);
				}
				isPushed = false;
				return new String(label);
			}
		});
   
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
