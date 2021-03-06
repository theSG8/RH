package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JPasswordField;

import businessLogic.ApplicationFacadeInterfaceWS;
import domain.Client;
import domain.Owner;
import exceptions.PasswordsDoesNotMatch;
import exceptions.UserAlreadyExists;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ResourceBundle;

public class EditClientGUI extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private Client currentClient;
	private ApplicationFacadeInterfaceWS businessLogic;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditClientGUI frame = new EditClientGUI(null, null);
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
	public EditClientGUI(Client current, ApplicationFacadeInterfaceWS bl) {
		currentClient = current;
		businessLogic = bl;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 454);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Editar perfil");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2.setBounds(160, 13, 93, 16);
		contentPane.add(lblNewLabel_2);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setBounds(0, 45, 432, 382);
		contentPane.add(panel);
		
		JLabel label_1 = new JLabel("Password");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_1.setBounds(29, 42, 185, 16);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("Repeat the password");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_2.setBounds(29, 71, 185, 16);
		panel.add(label_2);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(218, 39, 148, 21);
		panel.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(218, 68, 148, 22);
		panel.add(passwordField_1);
		
		JLabel label_3 = new JLabel("Nombre");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_3.setBounds(29, 100, 185, 16);
		panel.add(label_3);
		
		textField_1 = new JTextField();
		textField_1.setText(currentClient.getNombre());
		textField_1.setColumns(10);
		textField_1.setBounds(218, 97, 148, 22);
		panel.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setText(currentClient.getApellido());
		textField_2.setColumns(10);
		textField_2.setBounds(218, 126, 148, 22);
		panel.add(textField_2);
		
		JLabel label_4 = new JLabel("Apellido");
		label_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_4.setBounds(29, 129, 185, 16);
		panel.add(label_4);
		
		JLabel label_5 = new JLabel("DNI");
		label_5.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_5.setBounds(29, 156, 185, 16);
		panel.add(label_5);
		
		textField_3 = new JTextField();
		textField_3.setText(currentClient.getDni());
		textField_3.setColumns(10);
		textField_3.setBounds(218, 153, 148, 22);
		panel.add(textField_3);
		
		JLabel label_6 = new JLabel("Email");
		label_6.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_6.setBounds(29, 185, 185, 16);
		panel.add(label_6);
		
		textField_4 = new JTextField();
		textField_4.setText(currentClient.getEmail());
		textField_4.setColumns(10);
		textField_4.setBounds(218, 182, 148, 22);
		panel.add(textField_4);
		
		JLabel label_7 = new JLabel("Cuenta");
		label_7.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_7.setBounds(29, 214, 185, 16);
		panel.add(label_7);
		
		textField_5 = new JTextField();
		textField_5.setText(currentClient.getCuenta());
		textField_5.setColumns(10);
		textField_5.setBounds(218, 211, 148, 22);
		panel.add(textField_5);
		
		JButton btnEdit = new JButton("Confirmar cambios");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					businessLogic.modifyClient(currentClient, String.valueOf(passwordField.getPassword()),
							String.valueOf(passwordField_1.getPassword()), textField_1.getText(), textField_2.getText(),
							String.valueOf(textField_3.getText()), String.valueOf(textField_4.getText()),
							String.valueOf(textField_5.getText()));
					showDialog("Datos editados correctamente");
					closeFrame();
				} catch (PasswordsDoesNotMatch | UserAlreadyExists e1) {
					showDialog(ResourceBundle.getBundle("Etiquetas").getString("PasswordDoesNotMatch"));
					e1.printStackTrace();
				}
			}
		});
		btnEdit.setBounds(135, 270, 148, 54);
		panel.add(btnEdit);
	}
	private void showDialog(String msg) {
		JOptionPane.showMessageDialog(this, msg);

	}
	protected void closeFrame() {
		this.setVisible(false);

	}
}
