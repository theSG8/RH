package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.util.ResourceBundle;

import javax.swing.JTextField;
import javax.swing.JButton;

import businessLogic.ApplicationFacadeInterfaceWS;
import businessLogic.FacadeImplementationWS;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddRuralHouseGUI extends JFrame {

	private JPanel contentPane;
	private JTextField cityTextField;
	private JTextField descriptionTextField;
	private ApplicationFacadeInterfaceWS businessLogic;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddRuralHouseGUI frame = new AddRuralHouseGUI(new FacadeImplementationWS());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AddRuralHouseGUI(ApplicationFacadeInterfaceWS bl) {
		setBusinessLogic(bl);
	
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("AddRuralHouse"));
		lblNewLabel.setBounds(144, 25, 142, 16);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("City"));
		lblNewLabel_1.setBounds(51, 92, 56, 16);
		contentPane.add(lblNewLabel_1);
		
		cityTextField = new JTextField();
		cityTextField.setBounds(211, 89, 142, 22);
		cityTextField.setText("");
		contentPane.add(cityTextField);
		cityTextField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Description"));
		lblNewLabel_2.setBounds(51, 120, 81, 16);
		contentPane.add(lblNewLabel_2);
		
		descriptionTextField = new JTextField();
		descriptionTextField.setBounds(211, 122, 142, 22);
		descriptionTextField.setText("");
		contentPane.add(descriptionTextField);
		descriptionTextField.setColumns(10);
		
		JButton btnAddRuralHouse = new JButton(ResourceBundle.getBundle("Etiquetas").getString("AddRuralHouse"));
		btnAddRuralHouse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				businessLogic.addHouse( descriptionTextField.getText(),cityTextField.getText());
				showDialog("Casa rural añadida correctamente");
				
			}
		});
		btnAddRuralHouse.setBounds(139, 185, 155, 36);
		contentPane.add(btnAddRuralHouse);
	}
	
	private void setBusinessLogic(ApplicationFacadeInterfaceWS bl) {
		businessLogic = bl;
	}

	private void showDialog(String msg) {
		JOptionPane.showMessageDialog(this, msg);

	}
}
