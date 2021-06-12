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
import view.gui.CourseButtonEditor;
import view.gui.ButtonRenderer;
import view.gui.CourseButton;
import model.Course;  

public class ListConnectionsCoursesWindow extends Window {
	
	private static final long serialVersionUID = 8969121511313736097L;

	public ListConnectionsCoursesWindow() {
		
		ArrayList<Course> list = DBHandler.db.getConnectionsCourses();
		
		if (list.size() == 0) {
			JOptionPane.showMessageDialog(null, "No data!");
			throw new IllegalArgumentException() { 
				private static final long serialVersionUID = 7159707671657765083L; 
				@Override
				public void printStackTrace() { 
				}
			}; 
		}
		
		Object[][] data = new Object[list.size()][7];

		int i = 0;
		for (Course course : list) {
			data[i][0] = " " + (i + 1);
			data[i][1] = " " + course.getTitle();
			data[i][2] = " " + course.getSubject();
			data[i][3] = " " + course.getNumberOfCredits();
			data[i][4] = " " + course.getSemesterNumber();
			data[i][5] = new CourseButton("", course) {
				private static final long serialVersionUID = 4117497482580748439L;
				@Override
				public String toString() {
					return "View";
				}
			};
			data[i][6] = new CourseButton("", course) {
				private static final long serialVersionUID = 4117497482580748439L;
				@Override
				public String toString() {
					return "View";
				}
			};
			i++;
		}

		Object[] tableHeader = new Object[] {"#", "Title", "Subject",
				"Credits", "Semester", "Announcements", "Ratings"};

		DefaultTableModel model = new DefaultTableModel();
		model.setDataVector(data, tableHeader);

		JTable table = new JTable(model);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(50); 
		table.getColumnModel().getColumn(1).setPreferredWidth(250);
		table.getColumnModel().getColumn(2).setPreferredWidth(150);
		table.getColumnModel().getColumn(3).setPreferredWidth(100); 
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.getColumnModel().getColumn(5).setPreferredWidth(150);
		table.getColumnModel().getColumn(6).setPreferredWidth(100);
		
		table.setRowHeight(25);
		
		table.getTableHeader().setFont(new Font(table.getTableHeader().getFont().getFontName(), Font.BOLD, 12));

		table.getColumn("Announcements").setCellRenderer(new ButtonRenderer());
		table.getColumn("Announcements").setCellEditor(new CourseButtonEditor(new JCheckBox()) {
			private static final long serialVersionUID = -2682863002618839997L; 
			@Override
			public Object getCellEditorValue() {
				if (isPushed) {
					new ViewCourseAnnouncementsWindow(((CourseButton) button).getCourse()).setVisible(true);
				}
				isPushed = false;
				return new String(label);
			}
		});

		table.getColumn("Ratings").setCellRenderer(new ButtonRenderer());
		table.getColumn("Ratings").setCellEditor(new CourseButtonEditor(new JCheckBox()) {
			private static final long serialVersionUID = -2682863002618839997L; 
			@Override
			public Object getCellEditorValue() {
				if (isPushed) {
					new ViewCourseRatingsWindow(((CourseButton) button).getCourse()).setVisible(true);
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

		setMinimumSize(new Dimension(1100, 400));
		pack();
		setLocationRelativeTo(null);

	}

}
