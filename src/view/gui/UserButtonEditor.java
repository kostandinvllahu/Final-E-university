package view.gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

public class UserButtonEditor extends DefaultCellEditor {

	private static final long serialVersionUID = 8889482022393810636L;
	protected UserButton button;
	protected String label;
	protected boolean isPushed;

	public UserButtonEditor(JCheckBox checkBox) {
		super(checkBox);
		button = new UserButton(null, null);
		button.setOpaque(true);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fireEditingStopped();
			}
		});
	}

	public Component getTableCellEditorComponent(
			JTable table, Object value, 
			boolean isSelected, int row,
			int column) {
		if (isSelected) {
			button.setForeground(table.getSelectionForeground());
			button.setBackground(table.getSelectionBackground());
		} else {
			button.setForeground(table.getForeground());
			button.setBackground(table.getBackground());
		}
		label = (value == null) ? "" : value.toString();
		button.setText(label);
		button.setUser(((UserButton) value).getUser());
		isPushed = true;
		return button;
	}

	public boolean stopCellEditing() {
		isPushed = false;
		return super.stopCellEditing();
	}

	protected void fireEditingStopped() {
		super.fireEditingStopped();
	}
}
