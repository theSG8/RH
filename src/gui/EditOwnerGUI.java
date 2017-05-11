package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import businessLogic.ApplicationFacadeInterfaceWS;
import domain.Owner;
import exceptions.PasswordsDoesNotMatch;
import exceptions.UserAlreadyExists;

public class EditOwnerGUI extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private ApplicationFacadeInterfaceWS businessLogic;
	private Owner currentOwner;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					EditOwnerGUI frame = new EditOwnerGUI(null, null);
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
	public EditOwnerGUI(Owner current, ApplicationFacadeInterfaceWS bl) {
		currentOwner = current;
		businessLogic = bl;

		setBounds(100, 100, 450, 446);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setBounds(0, 64, 432, 382);
		contentPane.add(panel);

		JLabel lblNewPassword = new JLabel("New Password");
		lblNewPassword.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewPassword.setBounds(29, 42, 185, 16);
		panel.add(lblNewPassword);

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
		textField_1.setText(currentOwner.getNombre());
		textField_1.setColumns(10);
		textField_1.setBounds(218, 97, 148, 22);
		panel.add(textField_1);

		textField_2 = new JTextField();
		textField_2.setText(currentOwner.getApellido());
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
		textField_3.setText(currentOwner.getDni());
		textField_3.setColumns(10);
		textField_3.setBounds(218, 153, 148, 22);
		panel.add(textField_3);

		JLabel label_6 = new JLabel("Email");
		label_6.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_6.setBounds(29, 185, 185, 16);
		panel.add(label_6);

		textField_4 = new JTextField();
		textField_4.setText(currentOwner.getEmail());
		textField_4.setColumns(10);
		textField_4.setBounds(218, 182, 148, 22);
		panel.add(textField_4);

		JLabel label_7 = new JLabel("Cuenta");
		label_7.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_7.setBounds(29, 214, 185, 16);
		panel.add(label_7);

		textField_5 = new JTextField();
		textField_5.setText(currentOwner.getCuenta());
		textField_5.setColumns(10);
		textField_5.setBounds(218, 211, 148, 22);
		panel.add(textField_5);

		JButton button = new JButton("Confirmar cambios");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					businessLogic.modifyOwner(currentOwner, String.valueOf(passwordField.getPassword()),
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
		button.setBounds(129, 257, 148, 54);
		panel.add(button);

		JLabel lblNewLabel = new JLabel("Editar Perfil");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(150, 23, 115, 16);
		contentPane.add(lblNewLabel);
	}

	private void showDialog(String msg) {
		JOptionPane.showMessageDialog(this, msg);

	}

	protected void closeFrame() {
		this.setVisible(false);

	}

}
