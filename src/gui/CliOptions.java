package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.ApplicationFacadeInterfaceWS;
import domain.Client;

public class CliOptions extends JFrame {

	private ApplicationFacadeInterfaceWS businessLogic;
	private JPanel contentPane;
	public static Client currentClient;

	public CliOptions(ApplicationFacadeInterfaceWS bl, Client current) {

		setBusinessLogic(bl);
		currentClient = current;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 433, 334);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnLogout = new JButton(ResourceBundle.getBundle("Etiquetas").getString("OwnOptions.btnLogout.text")); //$NON-NLS-1$ //$NON-NLS-2$
		btnLogout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				LoginGUI lg = new LoginGUI(bl);
				currentClient = null;
				lg.setVisible(true);
				closeCliOp(false);
			}
		});
		btnLogout.setBounds(318, 261, 89, 23);
		contentPane.add(btnLogout);
		
		JButton btnBuscarOferta = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CliOptions.btnBuscarOferta.text")); //$NON-NLS-1$ //$NON-NLS-2$
		btnBuscarOferta.setBounds(108, 51, 158, 23);
		contentPane.add(btnBuscarOferta);
	}

	private void setBusinessLogic(ApplicationFacadeInterfaceWS bl) {
		businessLogic = bl;

	}

	private void closeCliOp(boolean cl) {
		this.setVisible(cl);

	}
}
