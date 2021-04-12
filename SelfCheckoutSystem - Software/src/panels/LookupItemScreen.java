package panels;

import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import driver.CommandLineDriver;

import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JList;

public class LookupItemScreen extends JPanel {
	private JTextField textField;
	JList itemLst;
	/**
	 * Create the panel.
	 */
	public LookupItemScreen() {
		
		JLabel lblNewLabel = new JLabel("Look Up an Item");
		
		JLabel searchLbl = new JLabel("Search item:");
		
		textField = new JTextField();
		textField.setText("Enter item name and press \"Search\"...");
		textField.setColumns(10);
		
		
		JButton searchBtn = new JButton("Search");
		
		JButton goBackBtn = new JButton("Go Back");
		
		itemLst = new JList();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(itemLst, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addGap(172))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(searchLbl)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField, GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(searchBtn)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(goBackBtn)
							.addContainerGap())))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(searchLbl)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(searchBtn))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(itemLst, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(goBackBtn)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		searchBtn.addActionListener(new SearchItem());
		goBackBtn.addActionListener(new GoBackToMain());
		textField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textFieldMouseClicked(evt);
            }
        });

	}
	private class GoBackToMain implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			CommandLineDriver.goToScreen("main");
			
		}
		
	}
	private class SearchItem implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			
			
		}
		
	}
	private void textFieldMouseClicked(java.awt.event.MouseEvent evt) {                                       
        // TODO add your handling code here:
        if (this.textField.getText().equals("Enter item name and press \"Search\"...")) {
            textField.setText("");
        }
    
}
}
