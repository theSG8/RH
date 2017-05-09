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
	private JTextField nombreField;
	private JTextField apellidoField;
	private JTextField dniField;
	private JTextField emailField;
	private JTextField cntcorField;

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
		setBounds(100, 100, 450, 429);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUsername = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("User"));
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblUsername.setBounds(29, 13, 177, 16);
		contentPane.add(lblUsername);

		usernameField = new JTextField();
		usernameField.setBounds(218, 10, 148, 22);
		contentPane.add(usernameField);
		usernameField.setColumns(10);

		JLabel lblPassword1 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Password"));
		lblPassword1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPassword1.setBounds(29, 42, 185, 16);
		contentPane.add(lblPassword1);

		JRadioButton rdbtnOwner = new JRadioButton(ResourceBundle.getBundle("Etiquetas").getString("Owner"));
		buttonGroup.add(rdbtnOwner);
		rdbtnOwner.setBounds(88, 291, 127, 25);
		contentPane.add(rdbtnOwner);

		JRadioButton rdbtnClient = new JRadioButton(ResourceBundle.getBundle("Etiquetas").getString("Client"));
		buttonGroup.add(rdbtnClient);
		rdbtnClient.setBounds(220, 291, 127, 25);
		contentPane.add(rdbtnClient);

		JLabel lblPassword2 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("RepeatPassword"));
		lblPassword2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPassword2.setBounds(29, 71, 185, 16);
		contentPane.add(lblPassword2);

		JButton btnRegisterButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Register"));
		btnRegisterButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (!rdbtnOwner.isSelected() && !rdbtnClient.isSelected()) {
						businessLogic.checkAddAdmin("admin", "admin", "admin");
					}
					if (rdbtnOwner.isSelected()) {
						businessLogic.checkAddOwner(usernameField.getText(),
								String.valueOf(passwordField.getPassword()),
								String.valueOf(passwordField1.getPassword()), nombreField.getText(),
								apellidoField.getText(), String.valueOf(dniField.getText()),
								String.valueOf(emailField.getText()), String.valueOf(cntcorField.getText()));
						showDialog(ResourceBundle.getBundle("Etiquetas").getString("CorrectRegister"));
						closeFrame();
					}
					if (rdbtnClient.isSelected()) {
						businessLogic.checkAddClient(usernameField.getText(),
								String.valueOf(passwordField.getPassword()),
								String.valueOf(passwordField1.getPassword()), nombreField.getText(),
								apellidoField.getText(), String.valueOf(dniField.getText()),
								String.valueOf(emailField.getText()), String.valueOf(cntcorField.getText()));
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
		btnRegisterButton.setBounds(137, 325, 138, 44);
		contentPane.add(btnRegisterButton);

		passwordField = new JPasswordField();
		passwordField.setBounds(218, 39, 148, 21);
		contentPane.add(passwordField);

		passwordField1 = new JPasswordField();
		passwordField1.setBounds(218, 68, 148, 22);
		contentPane.add(passwordField1);

		JLabel lblNombre = new JLabel("Nombre"); //$NON-NLS-1$ //$NON-NLS-2$
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNombre.setBounds(29, 100, 185, 16);
		contentPane.add(lblNombre);

		nombreField = new JTextField();
		nombreField.setBounds(218, 97, 148, 22);
		contentPane.add(nombreField);
		nombreField.setColumns(10);

		apellidoField = new JTextField();
		apellidoField.setBounds(218, 126, 148, 22);
		contentPane.add(apellidoField);
		apellidoField.setColumns(10);

		JLabel lblApellido = new JLabel("Apellido"); //$NON-NLS-1$ //$NON-NLS-2$
		lblApellido.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblApellido.setBounds(29, 129, 185, 16);
		contentPane.add(lblApellido);

		JLabel lblDni = new JLabel("DNI"); //$NON-NLS-1$ //$NON-NLS-2$
		lblDni.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDni.setBounds(29, 156, 185, 16);
		contentPane.add(lblDni);

		dniField = new JTextField();
		dniField.setBounds(218, 153, 148, 22);
		contentPane.add(dniField);
		dniField.setColumns(10);

		JLabel lblEmail = new JLabel("Email"); //$NON-NLS-1$ //$NON-NLS-2$
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblEmail.setBounds(29, 185, 185, 16);
		contentPane.add(lblEmail);

		emailField = new JTextField();
		emailField.setBounds(218, 182, 148, 22);
		contentPane.add(emailField);
		emailField.setColumns(10);

		JLabel lblCuentaCorriente = new JLabel("Cuenta");
		lblCuentaCorriente.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCuentaCorriente.setBounds(29, 214, 185, 16);
		contentPane.add(lblCuentaCorriente);

		cntcorField = new JTextField();
		cntcorField.setBounds(218, 211, 148, 22);
		contentPane.add(cntcorField);
		cntcorField.setColumns(10);
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
