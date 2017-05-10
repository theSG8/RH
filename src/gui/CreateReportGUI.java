package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import businessLogic.ApplicationFacadeInterfaceWS;
import domain.Client;
import domain.ClientReport;
import domain.HouseReport;
import domain.Owner;
import domain.OwnerReport;
import domain.RuralHouse;
import domain.User;

public class CreateReportGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	public Vector<ClientReport> cliRep;
	public Vector<HouseReport> houseRep;
	public Vector<OwnerReport> ownRep;
	private ApplicationFacadeInterfaceWS businessLogic;

	private DefaultListModel<User> listModelU = new DefaultListModel<User>();
	private DefaultListModel<RuralHouse> listModelH = new DefaultListModel<RuralHouse>();

	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Create the frame.
	 */
	public CreateReportGUI(ApplicationFacadeInterfaceWS bl) {

		businessLogic = bl;
		Vector<RuralHouse> houses = bl.getAllRuralHouses();
		Vector<Client> clients = bl.getAllClients();
		Vector<Owner> owners = bl.getAllOwners();

		cliRep = businessLogic.getClientReports();
		houseRep = businessLogic.getHouseReports();
		ownRep = businessLogic.getOwnerReports();

		setBounds(100, 100, 454, 578);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// AQUI

		JList list = new JList();
		list.setBounds(37, 323, 354, 147);

		list.setModel(listModelH);
		for (RuralHouse rh : houses) {
			listModelH.addElement(rh);
		}

		contentPane.add(list);

		JList list_1 = new JList();
		list_1.setBounds(37, 323, 354, 147);
		list_1.setModel(listModelU);
		contentPane.add(list_1);

		list_1.setVisible(false);

		// AQUI

		JRadioButton rdbtnCasaRural = new JRadioButton("Casa Rural");
		buttonGroup.add(rdbtnCasaRural);
		rdbtnCasaRural.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				list.setVisible(true);
				list_1.setVisible(false);

				listModelH.clear();
				for (RuralHouse hs : houses) {
					listModelH.addElement(hs);
				}

			}
		});
		rdbtnCasaRural.setBounds(37, 68, 109, 23);
		contentPane.add(rdbtnCasaRural);

		JRadioButton rdbtnCliente = new JRadioButton("Cliente");
		buttonGroup.add(rdbtnCliente);
		rdbtnCliente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				list.setVisible(false);
				list_1.setVisible(true);

				listModelU.clear();
				for (Client cl : clients) {
					listModelU.addElement(cl);
				}

			}
		});
		rdbtnCliente.setBounds(171, 68, 81, 23);
		contentPane.add(rdbtnCliente);

		JRadioButton rdbtnPropietario = new JRadioButton("Propietario");
		buttonGroup.add(rdbtnPropietario);
		rdbtnPropietario.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				list.setVisible(false);
				list_1.setVisible(true);

				listModelU.clear();
				for (Owner ow : owners) {
					listModelU.addElement(ow);
				}

			}
		});
		rdbtnPropietario.setBounds(282, 68, 109, 23);
		contentPane.add(rdbtnPropietario);

		textField = new JTextField();
		textField.setBounds(37, 131, 145, 23);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(37, 192, 360, 94);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblDescripcin = new JLabel("Descripción");
		lblDescripcin.setBounds(37, 167, 81, 14);
		contentPane.add(lblDescripcin);

		JLabel lblTtulo = new JLabel("Título");
		lblTtulo.setBounds(37, 106, 46, 14);
		contentPane.add(lblTtulo);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ClientReport cl = new ClientReport();
			}
		});
		btnAceptar.setBounds(171, 494, 89, 23);
		contentPane.add(btnAceptar);

		JLabel lblAadirInforme = new JLabel("Añadir Informe");
		lblAadirInforme.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblAadirInforme.setBounds(144, 22, 135, 23);
		contentPane.add(lblAadirInforme);
	}
}
