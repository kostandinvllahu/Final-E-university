package view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.GregorianCalendar;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import controll.Settings;
import data.DBHandler;
import model.Timetable;
import view.jdatepicker.DateLabelFormatter;

public class AddTimetableWindow extends Window{

	private static final long serialVersionUID = -5412117534661938568L;

	public AddTimetableWindow() {

		setTitle(getTitle() + " - Add Timetable");
		
		Timetable timetable = new Timetable("", null, null);
		
		JPanel mainPanel = new JPanel(new GridLayout(0, 2, 10, 10));
		mainPanel.setBackground(Settings.COLOR1);
		mainPanel.setBorder(new LineBorder(Settings.COLOR1, 10));
		
		mainPanel.add(new JLabel("Timetable title"));

		JTextField timetableTitleTextField = new JTextField();
		mainPanel.add(timetableTitleTextField);
		
		Properties dateProperties = new Properties(); 
		dateProperties.put("text.today", "Today");
		dateProperties.put("text.month", "Month");
		dateProperties.put("text.year", "Year");
		
		mainPanel.add(new JLabel("Start Date"));
		UtilDateModel startDateModel = new UtilDateModel();
		JDatePanelImpl startDateDateoanel = new JDatePanelImpl(startDateModel, dateProperties);
		JDatePickerImpl startDateDatepicker = new JDatePickerImpl(startDateDateoanel, new DateLabelFormatter());
		startDateDatepicker.setPreferredSize(new Dimension(300, 25));
		mainPanel.add(startDateDatepicker);
		 
		mainPanel.add(new JLabel("End Date"));
		UtilDateModel endDateModel = new UtilDateModel(); 
		JDatePanelImpl endDateDatepanel = new JDatePanelImpl(endDateModel, dateProperties);
		JDatePickerImpl endDateDatepicker = new JDatePickerImpl(endDateDatepanel, new DateLabelFormatter());
		endDateDatepicker.setPreferredSize(new Dimension(300, 25));
		mainPanel.add(endDateDatepicker);
		 
		mainPanel.add(new JLabel("Course Schedule"));
		JButton addCourseScheduleButton = new JButton("Add");
		addCourseScheduleButton.addActionListener((e)->{
			try {
				new AddCourseScheduleWindow(timetable).setVisible(true);
			}
			catch(Exception ex) {
			}
		});
		
		mainPanel.add(addCourseScheduleButton);

		JButton confirm = new JButton("Confirm");
		JButton cancel = new JButton("Cancel");

		mainPanel.add(confirm);
		mainPanel.add(cancel);
		
		confirm.addActionListener((e)->{
			timetable.setTitle(timetableTitleTextField.getText());
			timetable.setStartDate(new GregorianCalendar(
					startDateDatepicker.getModel().getYear(),
					startDateDatepicker.getModel().getMonth(),
					startDateDatepicker.getModel().getDay()));
			timetable.setEndDate(new GregorianCalendar(
					endDateDatepicker.getModel().getYear(),
					endDateDatepicker.getModel().getMonth(),
					endDateDatepicker.getModel().getDay()));
			DBHandler.db.addTimetable(timetable);
			setVisible(false);
		});

		cancel.addActionListener((e)->{
			setVisible(false);
		});
		
		add(mainPanel);

		setMinimumSize(new Dimension(600, 200));
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		revalidate();
		setResizable(false);

	}
	
}
