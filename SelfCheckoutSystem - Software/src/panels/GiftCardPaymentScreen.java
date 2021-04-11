package panels;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import javax.swing.LayoutStyle.ComponentPlacement;

import driver.CommandLineDriver;

import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

public class GiftCardPaymentScreen extends JPanel {
	private JTextField giftcardNumberInput;

	/**
	 * Create the panel.
	 */
	public GiftCardPaymentScreen() {
		
		JLabel lblNewLabel = new JLabel("Giftcard Payment");
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		// Import ImageIcon - from stack 
		//Image image = 
		ImageIcon iconLogo = new ImageIcon("GUI Images/gift-card.gif");
		// In init() method write this code
		lblNewLabel.setIcon(iconLogo);

		
		JLabel lblNewLabel_1 = new JLabel("Enter your card number to check the amount available:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		
		giftcardNumberInput = new JTextField();
		giftcardNumberInput.setColumns(10);
		
		JButton btnRedeemGiftcard = new JButton("Tap to Redeem Gift Card!");
		btnRedeemGiftcard.setBackground(new Color(100, 149, 237));
		btnRedeemGiftcard.setForeground(Color.BLACK);
		
		JLabel lblNewLabel_2 = new JLabel("Your remaining payment balance is: ");
		
		JTextPane remainingBalanceOutput = new JTextPane();
		
		JLabel lblNewLabel_3 = new JLabel("CAD $");
		
		JButton btnCompletePayment = new JButton("Complete Payment");
		btnCompletePayment.setBackground(new Color(100, 149, 237));
		
		JLabel lblNewLabel_4 = new JLabel("Current amount on Co-op GIft Card: ");
		
		JTextPane currentAmountOutput = new JTextPane();
		
		JLabel lblNewLabel_3_1 = new JLabel("CAD $");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
							.addComponent(btnCompletePayment)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
								.addGap(28)
								.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
								.addGap(5)
								.addComponent(remainingBalanceOutput, GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblNewLabel_1)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lblNewLabel_3_1, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblNewLabel, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(currentAmountOutput)
								.addComponent(giftcardNumberInput, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))))
					.addGap(140))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(280, Short.MAX_VALUE)
					.addComponent(btnRedeemGiftcard)
					.addGap(126))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(35)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
						.addComponent(giftcardNumberInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblNewLabel_3_1))
						.addComponent(currentAmountOutput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(25)
					.addComponent(btnRedeemGiftcard)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(remainingBalanceOutput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnCompletePayment))
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_3)
							.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		setLayout(groupLayout);

		// tap to redeem button shouldn't go to new screen, only update the payment balance and print in text box 
		//btnNewButton.addActionListener(new GotoAttendantScreen());
		btnCompletePayment.addActionListener(new GotoPaymentSelector());
		
		
		
	}
	
	private class GotoPaymentSelector implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			CommandLineDriver.goToScreen("pay");
		}
	}
	
	
	
	
}
