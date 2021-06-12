package view.gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

import data.DBHandler; 

public class StringDataButtonEditor extends DefaultCellEditor {

	private static final long serialVersionUID = 8889482022393810636L;
	protected StringComponentDataButton button;
	protected String label;
	protected boolean isPushed;

	public StringDataButtonEditor(JCheckBox checkBox) {
		super(checkBox);
		button = new StringComponentDataButton(null, null, null);
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
		button.setStr(((StringComponentDataButton) value).getStr());
		button.setComponent(((StringComponentDataButton) value).getComponent());
		isPushed = true;
		return button;
	}

	public Object getCellEditorValue() {
		if (isPushed) {
			DBHandler.db.deleteSubject(((StringComponentDataButton) button).getStr());
			((StringComponentDataButton) button).getComponent().setVisible(false);
		}
		isPushed = false;
		return new String(label);
	}

	public boolean stopCellEditing() {
		isPushed = false;
		return super.stopCellEditing();
	}

	protected void fireEditingStopped() {
		super.fireEditingStopped();
	}
}
