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
import domain.Admin;
import domain.Client;
import domain.Owner;
import exceptions.PasswordsDoesNotMatch;
import exceptions.UserAlreadyExists;
import exceptions.UserNotExist;
import exceptions.WrongPassword;

public class LoginGUI extends JFrame {

	private JPanel contentPane;
	private JTextField userField;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JPasswordField passwordField;
	private JRadioButton ownerRB;
	private JRadioButton clientRB;
	private JRadioButton adminRB;
	private static ApplicationFacadeInterfaceWS businessLogic;

	public static ApplicationFacadeInterfaceWS getBusinessLogic() {
		return businessLogic;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					LoginGUI frame = new LoginGUI(new FacadeImplementationWS());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws UserAlreadyExists
	 * @throws PasswordsDoesNotMatch
	 */
	public LoginGUI(ApplicationFacadeInterfaceWS bl) {

		setBusinessLogic(bl);
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 514, 333);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		userField = new JTextField();
		userField.setBounds(224, 35, 181, 22);
		contentPane.add(userField);
		userField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(223, 74, 182, 21);
		contentPane.add(passwordField);

		JLabel lblNewLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("User"));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(89, 38, 78, 16);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Password"));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(89, 76, 78, 16);
		contentPane.add(lblNewLabel_1);

		JButton loginButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Enter"));
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (ownerRB.isSelected()) {

					try {
						Owner ow = businessLogic.makeOwnerLogin(userField.getText(),
								String.valueOf(passwordField.getPassword()));
						OwnOptions oo = new OwnOptions(bl, ow);
						oo.setVisible(true);
						closeLogin();

					} catch (WrongPassword e) {
						showDialog(ResourceBundle.getBundle("Etiquetas").getString("WrongPassword"));

					} catch (UserNotExist e) {
						showDialog(ResourceBundle.getBundle("Etiquetas").getString("UserDoesNotExist"));

					}

				}
				if (clientRB.isSelected()) {

					try {
						Client cl = businessLogic.makeClientLogin(userField.getText(),
								String.valueOf(passwordField.getPassword()));
						CliOptions co = new CliOptions(bl, cl);
						co.setVisible(true);
						closeLogin();

					} catch (WrongPassword e) {
						showDialog(ResourceBundle.getBundle("Etiquetas").getString("WrongPassword"));

					} catch (UserNotExist e) {
						showDialog(ResourceBundle.getBundle("Etiquetas").getString("UserDoesNotExist"));

					}
				}
				if (adminRB.isSelected()) {

					try {
						Admin ad = businessLogic.makeAdminLogin(userField.getText(),
								String.valueOf(passwordField.getPassword()));
						AdOptions ao = new AdOptions(bl, ad);
						ao.setVisible(true);
						closeLogin();

					} catch (WrongPassword e) {
						showDialog(ResourceBundle.getBundle("Etiquetas").getString("WrongPassword"));

					} catch (UserNotExist e) {
						showDialog(ResourceBundle.getBundle("Etiquetas").getString("UserDoesNotExist"));

					}

				}

			}
		});
		loginButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		loginButton.setBounds(163, 167, 147, 52);
		contentPane.add(loginButton);

		JButton registerButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Register"));
		registerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				RegisterGUI rg = new RegisterGUI(businessLogic);
				rg.setVisible(true);

			}
		});
		registerButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		registerButton.setBounds(375, 249, 109, 31);
		contentPane.add(registerButton);

		JLabel lblNewLabel_2 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("NoAccount"));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setBounds(12, 256, 351, 16);
		contentPane.add(lblNewLabel_2);

		ownerRB = new JRadioButton(ResourceBundle.getBundle("Etiquetas").getString("Owner"));
		ownerRB.setSelected(true);
		buttonGroup.add(ownerRB);
		ownerRB.setBounds(38, 122, 127, 25);
		contentPane.add(ownerRB);

		clientRB = new JRadioButton(ResourceBundle.getBundle("Etiquetas").getString("Client"));
		buttonGroup.add(clientRB);
		clientRB.setBounds(191, 122, 127, 25);
		contentPane.add(clientRB);

		adminRB = new JRadioButton(ResourceBundle.getBundle("Etiquetas").getString("LoginGUI.rdbtnAdmin.text")); //$NON-NLS-1$ //$NON-NLS-2$
		buttonGroup.add(adminRB);
		adminRB.setBounds(331, 123, 109, 23);
		contentPane.add(adminRB);
	}

	private void setBusinessLogic(ApplicationFacadeInterfaceWS bl) {
		businessLogic = bl;
	}

	private void showDialog(String msg) {
		JOptionPane.showMessageDialog(this, msg);

	}

	private void closeLogin() {
		this.setVisible(false);

	}
}
