package panels;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import driver.CommandLineDriver;

import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class MembershipScreen extends JPanel {
	private JTextField membershipNumInput;
	private JTextField nameInput;

	/**
	 * Create the panel.
	 */
	public MembershipScreen() {
		
		JLabel lblNewLabel_1 = new JLabel("Membership card number:");
		
		membershipNumInput = new JTextField();
		membershipNumInput.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Name:");
		
		nameInput = new JTextField();
		nameInput.setColumns(10);
		
		JTextArea txtrIfYouAre = new JTextArea();
		txtrIfYouAre.setBackground(SystemColor.window);
		txtrIfYouAre.setWrapStyleWord(true);
		txtrIfYouAre.setText("If you are a Co-op member, please enter your information below.");
		txtrIfYouAre.setEditable(false);
		txtrIfYouAre.setLineWrap(true);
		
		JButton btnNotMember = new JButton("I'm not a member");
		
		JButton btnContinue = new JButton("Continue");


		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnContinue)
							.addGap(58))
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addGap(60)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
											.addComponent(lblNewLabel_1)
											.addComponent(btnNotMember, Alignment.TRAILING))
										.addComponent(lblNewLabel_2))
									.addGap(48)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(membershipNumInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(nameInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED, 21, Short.MAX_VALUE))
								.addComponent(txtrIfYouAre, GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE))))
					.addGap(27))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(28)
					.addComponent(txtrIfYouAre, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addGap(20)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_2)
						.addComponent(nameInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(33)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_1)
						.addComponent(membershipNumInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnContinue)
						.addComponent(btnNotMember))
					.addGap(20))
		);
		setLayout(groupLayout);
		
		btnNotMember.addActionListener(new GotoMainScreen());
		btnContinue.addActionListener(new GotoMainScreen());

	}
	
	
	
	private class GotoMainScreen implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			CommandLineDriver.goToScreen("m");
		}
	}
	
	
	
	
}
