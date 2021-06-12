package view.gui;

import javax.swing.JButton;

import model.Student;

public class StudentButton extends JButton {

	private static final long serialVersionUID = -551678787441350038L;
	private Student student;

	public StudentButton(String txt, Student student) {
		super(txt);
		this.setStudent(student);
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

}