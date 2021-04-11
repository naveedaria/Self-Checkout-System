package panels;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.math.BigDecimal;
import java.math.RoundingMode;

import org.lsmr.selfcheckout.*;

public class CashPaymentScreen extends JPanel {
	BigDecimal cashPayed = new BigDecimal(0.0);
	BigDecimal balance = new BigDecimal(0.70);
	//Keep track of what cash has been inserted
	int bills5 = 0;
	int bills10 = 0;
	int bills20 = 0;
	int bills50 = 0;
	int bills100 = 0;
	
	int nickels = 0;
	int dimes = 0;
	int quarters = 0;
	int loonies = 0;
	int toonies = 0;
	
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
	//Returns a hashmap that will allow control software to see how many of each type of cash was payed
	public HashMap<String,Integer> getCashPayed(){
		HashMap<String,Integer> map = new HashMap<String, Integer>();
		map.put("bills5", bills5);
		map.put("bills10", bills10);
		map.put("bills20", bills20);
		map.put("bills50", bills50);
		map.put("bills100", bills100);
		map.put("nickels", nickels);
		map.put("dimes", dimes);
		map.put("quarters", quarters);
		map.put("loonies", loonies);
		map.put("toonies", toonies);
		return map;
	}
	
	/**
	 * Create the panel.
	 */
	public CashPaymentScreen() {
		
		JLabel lblNewLabel = new JLabel("Cash Payment");

		JLabel lblNewLabel_1 = new JLabel(cashPayed.toString());
		
		JLabel lblNewLabel_2 = new JLabel(balance.toString());
		BigDecimal combined = cashPayed.add(balance);
		JLabel lblNewLabel_3 = new JLabel(combined.toString());
		
		JButton btnNewButton = new JButton("Bill 5");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(balance.compareTo(new BigDecimal(0.0).setScale(2, RoundingMode.HALF_UP)) == 1) {
					bills5 += 1;
					cashPayed = cashPayed.add(new BigDecimal(5.0));
					balance = balance.subtract(new BigDecimal(5.0));
					lblNewLabel_1.setText(cashPayed.toString());
					lblNewLabel_2.setText(balance.toString());
				}
			}
		});
		
		JButton btnNewButton_1 = new JButton("Nickel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(balance.compareTo(new BigDecimal(0.0).setScale(2, RoundingMode.HALF_UP)) == 1) {
					nickels += 1;
					cashPayed = cashPayed.add(new BigDecimal(0.05).setScale(2, RoundingMode.HALF_UP));
					balance = balance.subtract(new BigDecimal(0.05).setScale(2, RoundingMode.HALF_UP));
					lblNewLabel_1.setText(cashPayed.toString());
					lblNewLabel_2.setText(balance.toString());
				}
			}
		});
		
		JButton btnNewButton_2 = new JButton("Loonie");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(balance.compareTo(new BigDecimal(0.0).setScale(2, RoundingMode.HALF_UP)) == 1) {
					loonies += 1;
					cashPayed = cashPayed.add(new BigDecimal(1.0));
					balance = balance.subtract(new BigDecimal(1.0));
					lblNewLabel_1.setText(cashPayed.toString());
					lblNewLabel_2.setText(balance.toString());
				}
			}
		});
		
		JButton btnNewButton_3 = new JButton("Quarter");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					if(balance.compareTo(new BigDecimal(0.0).setScale(2, RoundingMode.HALF_UP)) == 1) {
					quarters += 1;
					cashPayed = cashPayed.add(new BigDecimal(0.25));
					balance = balance.subtract(new BigDecimal(0.25));
					lblNewLabel_1.setText(cashPayed.toString());
					lblNewLabel_2.setText(balance.toString());
				}
			}
		});
		JButton btnNewButton_4 = new JButton("Bill 10");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(balance.compareTo(new BigDecimal(0.0).setScale(2, RoundingMode.HALF_UP)) == 1) {
					bills10 += 1;
					cashPayed = cashPayed.add(new BigDecimal(10.0));
					balance = balance.subtract(new BigDecimal(10.0));
					lblNewLabel_1.setText(cashPayed.toString());
					lblNewLabel_2.setText(balance.toString());
				}
			}
		});
		JButton btnNewButton_5 = new JButton("Bill 50");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(balance.compareTo(new BigDecimal(0.0).setScale(2, RoundingMode.HALF_UP)) == 1) {
					bills50 += 1;
					cashPayed = cashPayed.add(new BigDecimal(50.0));
					balance = balance.subtract(new BigDecimal(50.0));
					lblNewLabel_1.setText(cashPayed.toString());
					lblNewLabel_2.setText(balance.toString());
				}
			}
		});
		JButton btnNewButton_6 = new JButton("Dime");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(balance.compareTo(new BigDecimal(0.0).setScale(2, RoundingMode.HALF_UP)) == 1) {
					dimes += 1;
					cashPayed = cashPayed.add(new BigDecimal(0.10).setScale(2, RoundingMode.HALF_UP));
					balance = balance.subtract(new BigDecimal(0.10).setScale(2, RoundingMode.HALF_UP));
					lblNewLabel_1.setText(cashPayed.toString());
					lblNewLabel_2.setText(balance.toString());
				}
			}
		});
		JButton btnNewButton_7 = new JButton("Bill 20");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(balance.compareTo(new BigDecimal(0.0).setScale(2, RoundingMode.HALF_UP)) == 1) {
					bills20 += 1;
					cashPayed = cashPayed.add(new BigDecimal(20.0));
					balance = balance.subtract(new BigDecimal(20.0));
					lblNewLabel_1.setText(cashPayed.toString());
					lblNewLabel_2.setText(balance.toString());
				}
			}
		});
		JButton btnNewButton_8 = new JButton("Bill 100");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(balance.compareTo(new BigDecimal(0.0).setScale(2, RoundingMode.HALF_UP)) == 1) {
					bills100 += 1;
					cashPayed = cashPayed.add(new BigDecimal(100.0));
					balance = balance.subtract(new BigDecimal(100.0));
					lblNewLabel_1.setText(cashPayed.toString());
					lblNewLabel_2.setText(balance.toString());
				}
			}
		});
		JButton btnNewButton_9 = new JButton("Toonie");
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(balance.compareTo(new BigDecimal(0.0).setScale(2, RoundingMode.HALF_UP)) == 1) {
					toonies += 1;
					cashPayed = cashPayed.add(new BigDecimal(2.0));
					balance = balance.subtract(new BigDecimal(2.0));
					lblNewLabel_1.setText(cashPayed.toString());
					lblNewLabel_2.setText(balance.toString());
				}
			}
		});
		String[] columnNames = {"Cash Payed",
                "Balance"};
		Object[][] data = {
			    {cashPayed, balance,},
			};
		
		JLabel lblNewLabel_4 = new JLabel("Cash Payed");
		
		JLabel lblNewLabel_5 = new JLabel("Balance");
		
		JLabel lblNewLabel_6 = new JLabel("Total");
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnNewButton_7, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnNewButton_3, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnNewButton_5, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED, 307, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnNewButton_8, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(btnNewButton_9, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
									.addGap(60)
									.addComponent(lblNewLabel_1)
									.addPreferredGap(ComponentPlacement.RELATED, 88, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
											.addComponent(btnNewButton_4, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(btnNewButton_6, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
											.addComponent(lblNewLabel_4)
											.addGap(25))
										.addGroup(groupLayout.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED, 234, Short.MAX_VALUE)
											.addComponent(lblNewLabel)))
									.addGap(35)))
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel_5)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lblNewLabel_6))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel_2)
									.addGap(74)
									.addComponent(lblNewLabel_3)))
							.addGap(23)))
					.addGap(59))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addGap(16)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_1)
						.addComponent(lblNewLabel_1)
						.addComponent(lblNewLabel_3)
						.addComponent(lblNewLabel_2))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnNewButton_4)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnNewButton_6)
							.addComponent(lblNewLabel_6)
							.addComponent(lblNewLabel_5)
							.addComponent(lblNewLabel_4)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_7)
						.addComponent(btnNewButton_3))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_5)
						.addComponent(btnNewButton_2))
					.addGap(6)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_8)
						.addComponent(btnNewButton_9))
					.addContainerGap(120, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}
}
