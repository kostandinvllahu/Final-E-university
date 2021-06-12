package view.gui;

import javax.swing.JButton;

import model.Course;

public class CourseButton extends JButton {

	private static final long serialVersionUID = -551678787441350038L;
	private Course course;

	public CourseButton(String txt, Course course) {
		super(txt);
		this.setCourse(course);
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

}