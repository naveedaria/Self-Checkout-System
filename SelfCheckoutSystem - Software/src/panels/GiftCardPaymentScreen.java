package panels;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class GiftCardPaymentScreen extends JPanel {

	/**
	 * Create the panel.
	 */
	public GiftCardPaymentScreen() {
		
		JLabel lblNewLabel = new JLabel("Giftcard Payment");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel_1 = new JLabel("Please turn your attention to the Card Reader to pay");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(50)
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 369, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(179)
							.addComponent(lblNewLabel)))
					.addContainerGap(31, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(40)
					.addComponent(lblNewLabel)
					.addGap(33)
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(151, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}
}
