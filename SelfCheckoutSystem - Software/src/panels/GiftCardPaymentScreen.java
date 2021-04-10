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
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.ImageIcon;

public class GiftCardPaymentScreen extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public GiftCardPaymentScreen() {
		
		JLabel lblNewLabel = new JLabel("Giftcard Payment");
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		// Import ImageIcon - from stack    
		ImageIcon iconLogo = new ImageIcon("GUI Images/gift-card.gif");
		// In init() method write this code
		lblNewLabel.setIcon(iconLogo);

		
		JLabel lblNewLabel_1 = new JLabel("Enter your card number to check the amount available:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Tap to Redeem Gift Card!");
		btnNewButton.setBackground(new Color(100, 149, 237));
		btnNewButton.setForeground(Color.BLACK);
		
		JLabel lblNewLabel_2 = new JLabel("Your remaining payment balance is: ");
		
		JTextPane textPane = new JTextPane();
		
		JLabel lblNewLabel_3 = new JLabel("CAD $");
		
		JButton btnNewButton_1 = new JButton("Complete Payment");
		btnNewButton_1.setBackground(new Color(100, 149, 237));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(176, Short.MAX_VALUE)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
					.addGap(170))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
							.addComponent(btnNewButton_1)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
								.addGap(28)
								.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
								.addGap(5)
								.addComponent(textPane, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(21, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(153, Short.MAX_VALUE)
					.addComponent(btnNewButton)
					.addGap(140))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(40)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnNewButton)
					.addGap(37)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(textPane)
							.addGap(18)
							.addComponent(btnNewButton_1))
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblNewLabel_3)))
					.addContainerGap())
		);
		setLayout(groupLayout);

	}
}
