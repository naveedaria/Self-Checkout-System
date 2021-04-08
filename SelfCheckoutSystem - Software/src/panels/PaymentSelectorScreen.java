package panels;

import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import driver.CommandLineDriver;

public class PaymentSelectorScreen extends JPanel {

	/**
	 * Create the panel.
	 */
	public PaymentSelectorScreen() {
		
		JLabel lblNewLabel = new JLabel("Select Payment");
		
		JButton backButton = new JButton("<-");
		
		JButton button1 = new JButton("1");
		
		JButton button2 = new JButton("2");
		
		JButton button3 = new JButton("3");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(57)
							.addComponent(button1)
							.addGap(55)
							.addComponent(button2)
							.addGap(55)
							.addComponent(button3))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(177)
							.addComponent(lblNewLabel))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(backButton)))
					.addContainerGap(58, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addGap(57)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(button1)
						.addComponent(button2)
						.addComponent(button3))
					.addPreferredGap(ComponentPlacement.RELATED, 157, Short.MAX_VALUE)
					.addComponent(backButton)
					.addContainerGap())
		);
		setLayout(groupLayout);
		
		backButton.addActionListener(new GotoPrevScreen());

	}
	
	private class GotoPrevScreen implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			CommandLineDriver.goToScreen("main");
		}
		
	}
}
