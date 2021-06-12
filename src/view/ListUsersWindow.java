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
import model.User;
import view.gui.ButtonRenderer;
import view.gui.UserButton;
import view.gui.UserButtonEditor;

public class ListUsersWindow extends Window {
	  
	private static final long serialVersionUID = 8969121511313736097L;

	public ListUsersWindow() {
		
		ArrayList<User> list = DBHandler.db.getUsers();
		
		if (list.size() == 0) {
			JOptionPane.showMessageDialog(null, "No data!");
			throw new IllegalArgumentException(); 
		}
		
		Object[][] data = new Object[list.size()][8];

		int i = 0;
		for (User user : list) {
			data[i][0] = " " + user.getNationalId();
			data[i][1] = " " + user.getName();
			data[i][2] = " " + user.getSurname();
			data[i][3] = " " + user.getUsername();
			data[i][4] = " " + ((user instanceof Student)? "Student" : "Admin");
			data[i][5] = " " + user.isActive();
			data[i][6] = new UserButton("", user) {
				private static final long serialVersionUID = 4117497482580748439L;
				@Override
				public String toString() {
					return "View";
				}
			};
			data[i][7] = new UserButton("", user) {
				private static final long serialVersionUID = -6252861590543651403L;
				@Override
				public String toString() {
					return "Delete";
				}
			};
			i++;
		}

		Object[] tableHeader = new Object[] {"National Id", "Name", "Surname",
				"Username", "Role", "Active", "", " "};

		DefaultTableModel model = new DefaultTableModel();
		model.setDataVector(data, tableHeader);

		JTable table = new JTable(model);
		
		table.getColumn("").setCellRenderer(new ButtonRenderer());
		table.getColumn("").setCellEditor(new UserButtonEditor(new JCheckBox()) {
			private static final long serialVersionUID = -1432410327572748479L;
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
		table.getColumn(" ").setCellEditor(new UserButtonEditor(new JCheckBox()) {
			private static final long serialVersionUID = -1432410327572748479L;
			@Override
			public Object getCellEditorValue() {
				if (isPushed) {
					DBHandler.db.deleteUser(((UserButton) button).getUser());
					setVisible(false);
				}
				isPushed = false;
				return new String(label);
			}
		});
   
		table.getColumnModel().getColumn(0).setPreferredWidth(150); 
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setPreferredWidth(150);
		table.getColumnModel().getColumn(3).setPreferredWidth(200); 
		table.getColumnModel().getColumn(4).setPreferredWidth(150);
		table.getColumnModel().getColumn(5).setPreferredWidth(100);
		table.getColumnModel().getColumn(6).setPreferredWidth(100);
		table.getColumnModel().getColumn(7).setPreferredWidth(150);
		
		table.setRowHeight(25);
		
		table.getTableHeader().setFont(new Font(table.getTableHeader().getFont().getFontName(), Font.BOLD, 12));
		
		JScrollPane scrollPane = new JScrollPane(table);
		
		JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
		mainPanel.setBackground(Settings.COLOR1);
		mainPanel.setBorder(new LineBorder(Settings.COLOR1, 10));
		mainPanel.add(scrollPane);
		add(mainPanel);

		setMinimumSize(new Dimension(700, 400));
		pack();
		setLocationRelativeTo(null);

	}

}
