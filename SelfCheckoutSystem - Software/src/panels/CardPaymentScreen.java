package panels;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import driver.CommandLineDriver;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CardPaymentScreen extends JPanel {

	/**
	 * Create the panel.
	 */
	public CardPaymentScreen() {
		
		JLabel lblNewLabel = new JLabel("Card Payment");
		
		JButton gotoGiftcard = new JButton("pay with giftcard");
		
		
		gotoGiftcard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JLabel lblNewLabel_1 = new JLabel("Please turn your attention to the card reader to pay");
		
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(192)
					.addComponent(lblNewLabel)
					.addContainerGap(373, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(102)
					.addComponent(lblNewLabel_1)
					.addContainerGap(280, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(289, Short.MAX_VALUE)
					.addComponent(gotoGiftcard)
					.addGap(72))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addGap(41)
					.addComponent(lblNewLabel_1)
					.addGap(49)
					.addComponent(gotoGiftcard)
					.addContainerGap(148, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

			
		gotoGiftcard.addActionListener(new GotoGiftcardScreen());
	}
	
	
}

class GotoGiftcardScreen implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		CommandLineDriver.goToScreen("giftcard");
		
	}
	
}
