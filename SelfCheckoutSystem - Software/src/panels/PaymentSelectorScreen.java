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
		
		JLabel lblNewLabel = new JLabel("Please Select a Payment option");
		
		JButton backButton = new JButton("<-");
		
		JButton cardButton = new JButton("Card");
		


		
		
		JButton giftcardButton = new JButton("Giftcard");
		
		JButton cashButton = new JButton("Cash");
		
		
		
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(57)
							.addComponent(cardButton)
							.addGap(55)
							.addComponent(giftcardButton)
							.addGap(55)
							.addComponent(cashButton))
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
						.addComponent(cardButton)
						.addComponent(giftcardButton)
						.addComponent(cashButton))
					.addPreferredGap(ComponentPlacement.RELATED, 157, Short.MAX_VALUE)
					.addComponent(backButton)
					.addContainerGap())
		);
		setLayout(groupLayout);
		
		backButton.addActionListener(new GotoPrevScreen());
		cardButton.addActionListener(new GotoCard());
		giftcardButton.addActionListener(new GotoGiftcard());
		cashButton.addActionListener(new GotoCash());
	}
	
	private class GotoPrevScreen implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			CommandLineDriver.goToScreen("main");
		}
		
	}
	
	
	private class GotoCard implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			CommandLineDriver.goToScreen("card");
		}
		
	}
	
	
	
	private class GotoGiftcard implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			CommandLineDriver.goToScreen("giftcard");
		}
		
	}
	
	
	private class GotoCash implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			CommandLineDriver.cash.lblNewLabel_2.setText(CommandLineDriver.controlSoftware.paymentTotal.toString());
			CommandLineDriver.cash.lblNewLabel_3.setText(CommandLineDriver.controlSoftware.paymentTotal.toString());
			CommandLineDriver.cash.balance = CommandLineDriver.controlSoftware.paymentTotal;
			
			CommandLineDriver.goToScreen("cash");
		}
	
	
	}
}
