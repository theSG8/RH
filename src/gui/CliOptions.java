package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.ApplicationFacadeInterfaceWS;
import domain.Booking;
import domain.Client;
import domain.RuralHouse;

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
				closeCliOp();
			}
		});
		btnLogout.setBounds(318, 261, 89, 23);
		contentPane.add(btnLogout);

		JButton btnBuscarOferta = new JButton(
				ResourceBundle.getBundle("Etiquetas").getString("CliOptions.btnBuscarOferta.text")); //$NON-NLS-1$ //$NON-NLS-2$
		btnBuscarOferta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				Vector<RuralHouse> houses = businessLogic.getAllRuralHouses();
				Vector<RuralHouse> confirmedHouses = new Vector<RuralHouse>();

				for (RuralHouse rh : houses) {
					if (rh.getConfirmed()) {
						confirmedHouses.addElement(rh);
					}
				}

				if (confirmedHouses.isEmpty()) {
					showDialog("No hay casas rurales");

				} else {
					AllHouses ah = new AllHouses(businessLogic, confirmedHouses);
					ah.setVisible(true);
				}

			}
		});
		btnBuscarOferta.setBounds(112, 49, 183, 33);
		contentPane.add(btnBuscarOferta);

		JLabel lblEstsLogeadoComo = new JLabel(
				ResourceBundle.getBundle("Etiquetas").getString("CliOptions.lblEstsLogeadoComo.text")); //$NON-NLS-1$ //$NON-NLS-2$
		lblEstsLogeadoComo.setBounds(10, 265, 174, 14);
		contentPane.add(lblEstsLogeadoComo);
		lblEstsLogeadoComo.setText("Estás logeado como: " + currentClient.getUsername());

		JButton btnMostrarReservas = new JButton(
				ResourceBundle.getBundle("Etiquetas").getString("CliOptions.btnMostrarReservas.text")); //$NON-NLS-1$ //$NON-NLS-2$
		btnMostrarReservas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Vector<Booking> of = businessLogic.getClientBookings(currentClient);
				if (of.size() == 0) {
					showDialog("No tienes ninguna reserva");
				} else {
					RemoveBooking sro = new RemoveBooking(bl, of);
					sro.setVisible(true);
				}
			}
		});
		btnMostrarReservas.setBounds(112, 93, 183, 33);
		contentPane.add(btnMostrarReservas);
		
		JButton btnEditPerfil = new JButton("Editar Perfil");
		btnEditPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditClientGUI ec = new EditClientGUI(currentClient, businessLogic);
				ec.setVisible(true);
			}
		});
		btnEditPerfil.setBounds(112, 139, 183, 33);
		contentPane.add(btnEditPerfil);
	}

	private void setBusinessLogic(ApplicationFacadeInterfaceWS bl) {
		businessLogic = bl;

	}

	private void closeCliOp() {
		this.setVisible(false);

	}

	private void showDialog(String msg) {
		JOptionPane.showMessageDialog(this, msg);

	}
}
