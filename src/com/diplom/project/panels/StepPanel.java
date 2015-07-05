package com.diplom.project.panels;

import java.awt.*;

import javax.swing.*;

public class StepPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String[] step = new String[]{ };

	public StepPanel(int index) {
		JLabel label = new JLabel(step[index]);
		add(label, BorderLayout.CENTER);
	}

}
