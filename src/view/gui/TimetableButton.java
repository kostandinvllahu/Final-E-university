package view.gui;

import javax.swing.JButton;

import model.Timetable;

public class TimetableButton extends JButton {

	private static final long serialVersionUID = -551678787441350038L;
	private Timetable timetable;

	public TimetableButton(String txt, Timetable timetable) {
		super(txt);
		this.setTimetable(timetable);
	}

	public Timetable getTimetable() {
		return timetable;
	}

	public void setTimetable(Timetable timetable) {
		this.timetable = timetable;
	}

}