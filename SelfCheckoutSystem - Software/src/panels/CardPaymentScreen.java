package panels;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import driver.CommandLineDriver;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Cursor;
import java.awt.Dimension;

public class CardPaymentScreen extends JPanel {

	/**
	 * Create the panel.
	 */
	public CardPaymentScreen() {
		setMaximumSize(new Dimension(800, 600));
		setMinimumSize(new Dimension(800, 600));
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		
		JLabel lblNewLabel = new JLabel("Card Payment");
		
			
		
		JButton payWithCredit = new JButton("Credit Card");
		
		
		payWithCredit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JLabel lblNewLabel_1 = new JLabel("Please turn your attention to the card reader to pay");
		lblNewLabel_1.setVisible(false);
		JButton payWithDebit = new JButton("Debit Card");
		
		JLabel cardMethodLabel = new JLabel("Please choose your method of card payment");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(191)
							.addComponent(lblNewLabel))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(70)
							.addComponent(payWithDebit, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
							.addGap(83)
							.addComponent(payWithCredit, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(92)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_1)
								.addComponent(cardMethodLabel))))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(37)
					.addComponent(lblNewLabel)
					.addGap(28)
					.addComponent(cardMethodLabel)
					.addGap(5)
					.addComponent(lblNewLabel_1)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(payWithDebit, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(payWithCredit, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE))))
		);
		setLayout(groupLayout);

			

		
		
		class PayUsingDebitCard implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				lblNewLabel_1.setVisible(true);
				payWithCredit.setVisible(false);			//methods when Debit button is pressed
				payWithDebit.setVisible(false);
				cardMethodLabel.setVisible(false);
			}
			
		}
		
		
		
		class PayUsingCreditCard implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				lblNewLabel_1.setVisible(true);
				payWithCredit.setVisible(false);		//methods when credit button is pressed
				payWithDebit.setVisible(false);
				cardMethodLabel.setVisible(false);
			}
		}
		
		payWithCredit.addActionListener(new PayUsingCreditCard());
		payWithDebit.addActionListener(new PayUsingDebitCard());
		
		
	}
	
	
	
}


	

