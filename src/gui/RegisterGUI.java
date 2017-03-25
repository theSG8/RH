package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import businessLogic.ApplicationFacadeInterfaceWS;
import businessLogic.FacadeImplementationWS;
import exceptions.PasswordsDoesNotMatch;
import exceptions.UserAlreadyExists;

public class RegisterGUI extends JFrame {

	private JPanel contentPane;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JPasswordField passwordField1;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbtnOwner;
	private JRadioButton rdbtnClient;
	private ApplicationFacadeInterfaceWS businessLogic;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					RegisterGUI frame = new RegisterGUI(new FacadeImplementationWS());
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
	public RegisterGUI(ApplicationFacadeInterfaceWS bl) {
		setBusinessLogic(bl);
		setTitle("Register");
		setBounds(100, 100, 450, 307);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUsername = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("User"));
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblUsername.setBounds(29, 59, 177, 16);
		contentPane.add(lblUsername);

		usernameField = new JTextField();
		usernameField.setBounds(226, 56, 148, 22);
		contentPane.add(usernameField);
		usernameField.setColumns(10);

		JLabel lblPassword1 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Password"));
		lblPassword1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPassword1.setBounds(29, 88, 185, 16);
		contentPane.add(lblPassword1);

		JRadioButton rdbtnOwner = new JRadioButton(ResourceBundle.getBundle("Etiquetas").getString("Owner"));
		buttonGroup.add(rdbtnOwner);
		rdbtnOwner.setBounds(105, 157, 127, 25);
		contentPane.add(rdbtnOwner);

		JRadioButton rdbtnClient = new JRadioButton(ResourceBundle.getBundle("Etiquetas").getString("Client"));
		buttonGroup.add(rdbtnClient);
		rdbtnClient.setBounds(235, 157, 127, 25);
		contentPane.add(rdbtnClient);

		JLabel lblPassword2 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("RepeatPassword"));
		lblPassword2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPassword2.setBounds(29, 117, 185, 16);
		contentPane.add(lblPassword2);

		JButton btnRegisterButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Register"));
		btnRegisterButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (rdbtnOwner.isSelected()) {
						businessLogic.checkAddOwner(usernameField.getText(),
								String.valueOf(passwordField.getPassword()),
								String.valueOf(passwordField1.getPassword()));
						showDialog(ResourceBundle.getBundle("Etiquetas").getString("CorrectRegister"));
						closeFrame();
					}
					if (rdbtnClient.isSelected()) {
						businessLogic.checkAddClient(usernameField.getText(),
								String.valueOf(passwordField.getPassword()),
								String.valueOf(passwordField1.getPassword()));
						showDialog(ResourceBundle.getBundle("Etiquetas").getString("CorrectRegister"));
						closeFrame();
					}
				} catch (UserAlreadyExists e) {
					showDialog(ResourceBundle.getBundle("Etiquetas").getString("UserAlreadyExists"));
				} catch (PasswordsDoesNotMatch e) {
					showDialog(ResourceBundle.getBundle("Etiquetas").getString("PasswordsDoesNotMatch"));
				}
			}
		});
		btnRegisterButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnRegisterButton.setBounds(132, 191, 138, 44);
		contentPane.add(btnRegisterButton);

		passwordField = new JPasswordField();
		passwordField.setBounds(226, 85, 148, 21);
		contentPane.add(passwordField);

		passwordField1 = new JPasswordField();
		passwordField1.setBounds(226, 114, 148, 22);
		contentPane.add(passwordField1);
	}

	protected void closeFrame() {
		this.setVisible(false);

	}

	private void setBusinessLogic(ApplicationFacadeInterfaceWS bl) {
		businessLogic = bl;
	}

	private void showDialog(String msg) {
		JOptionPane.showMessageDialog(this, msg);

	}
}
