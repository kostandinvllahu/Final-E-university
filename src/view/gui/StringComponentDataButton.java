package view.gui;

import javax.swing.JButton;
import view.Window;

public class StringComponentDataButton extends JButton {

	private static final long serialVersionUID = -551678787441350038L;
	private String str;
	private Window component;

	public StringComponentDataButton(String txt, String str, Window component) {
		super(txt);
		this.setStr(str);
		this.component = component;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public Window getComponent() {
		return component;
	}

	public void setComponent(Window component) {
		this.component = component;
	}
	
}

