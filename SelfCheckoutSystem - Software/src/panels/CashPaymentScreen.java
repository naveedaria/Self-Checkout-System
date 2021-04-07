package panels;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;

public class CashPaymentScreen extends JPanel {

	/**
	 * Create the panel.
	 */
	public CashPaymentScreen() {
		
		JLabel lblNewLabel = new JLabel("Cash Payment");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(198, Short.MAX_VALUE)
					.addComponent(lblNewLabel)
					.addGap(191))
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
