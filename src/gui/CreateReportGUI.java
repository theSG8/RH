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
import domain.Report;
import domain.RuralHouse;

public class CreateReportGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	public Vector<ClientReport> cliRep;
	public Vector<HouseReport> houseRep;
	public Vector<OwnerReport> ownRep;
	private ApplicationFacadeInterfaceWS businessLogic;

	private DefaultListModel<Object> listModel = new DefaultListModel<>();
	private JList<Report> list;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Create the frame.
	 */
	public CreateReportGUI(ApplicationFacadeInterfaceWS bl) {

		businessLogic = bl;

		cliRep = businessLogic.getClientReports();
		houseRep = businessLogic.getHouseReports();
		ownRep = businessLogic.getOwnerReports();

		JList list = new JList();
		list.setBounds(37, 314, 360, 145);
		list.setModel(listModel);
		for (RuralHouse hs : bl.getAllRuralHouses()) {
			listModel.addElement(hs);
		}
		contentPane.add(list);

		setBounds(100, 100, 454, 578);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JRadioButton rdbtnCasaRural = new JRadioButton("Casa Rural");
		buttonGroup.add(rdbtnCasaRural);
		rdbtnCasaRural.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				listModel.clear();
				for (RuralHouse hs : bl.getAllRuralHouses()) {
					listModel.addElement(hs);
				}
			}
		});
		rdbtnCasaRural.setBounds(37, 68, 109, 23);
		contentPane.add(rdbtnCasaRural);
		rdbtnCasaRural.setSelected(true);

		JRadioButton rdbtnCliente = new JRadioButton("Cliente");
		buttonGroup.add(rdbtnCliente);
		rdbtnCliente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				listModel.clear();
				for (Client cl : bl.getAllClients()) {
					listModel.addElement(cl);
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
				listModel.clear();
				for (Owner ow : bl.getAllOwners()) {
					listModel.addElement(ow);
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
