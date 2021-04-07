package panels;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;

public class CardPaymentScreen extends JPanel {

	/**
	 * Create the panel.
	 */
	public CardPaymentScreen() {
		
		JLabel lblNewLabel = new JLabel("Card Payment");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(192)
					.addComponent(lblNewLabel)
					.addContainerGap(197, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addContainerGap(278, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}

}
