package com.diplom.project.panels;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import com.diplom.project.resource.ForHelpText;

import java.awt.Toolkit;
import java.awt.Font;
import javax.swing.JLabel;

public class HelpDialog extends JDialog implements ForHelpText{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application for testing
	 */
	public static void main(String[] args) {
		try {
			HelpDialog dialog = new HelpDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public HelpDialog() {
//		setType(Type.POPUP);
		setFont(new Font("Dialog", Font.PLAIN, 14));
		setIconImage(Toolkit.getDefaultToolkit().getImage(HelpDialog.class.getResource("/com/sun/java/swing/plaf/windows/icons/Question.gif")));
		setSize(470, 500);
		setTitle("Help");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setEnabled(true);
			scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			contentPanel.add(scrollPane, BorderLayout.CENTER);
			{
				JTextArea textArea = new JTextArea();
				textArea.setLineWrap(true);
				textArea.setEditable(false);
				textArea.setColumns(6);
				textArea.setFont(new Font("Arial", Font.PLAIN, 14));
				textArea.setText(ALL_TEXT_HELP);
				
				scrollPane.setViewportView(textArea);
			}
			{
				JLabel lblNewLabel = new JLabel("\t MCO (Multiple Criteria Optimization)");
				lblNewLabel.setFont(new Font("Serief", Font.BOLD, 16));
				scrollPane.setColumnHeaderView(lblNewLabel);
				
			}
		}
	}
	
}


