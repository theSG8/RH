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
import javax.swing.border.EmptyBorder;

import domain.ClientReport;
import domain.HouseReport;
import domain.OwnerReport;
import domain.Report;

public class ViewReportsGUI extends JFrame {

	private JPanel contentPane;
	public Vector<ClientReport> cliRep;
	public Vector<HouseReport> houseRep;
	public Vector<OwnerReport> ownRep;

	private DefaultListModel<Report> listModel = new DefaultListModel<Report>();
	private JList<Report> list;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Create the frame.
	 * 
	 * @param ownRep
	 * @param houseRep
	 * @param cliRep
	 */
	public ViewReportsGUI(Vector<ClientReport> cliRep, Vector<HouseReport> houseRep, Vector<OwnerReport> ownRep) {

		this.cliRep = cliRep;
		this.houseRep = houseRep;
		this.ownRep = ownRep;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 614, 439);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		list = new JList<Report>();
		list.setBounds(345, 30, 219, 339);
		list.setModel(listModel);
		for (HouseReport rh : houseRep) {
			listModel.addElement(rh);
		}
		contentPane.add(list);

		JButton btnVer = new JButton("Ver");
		btnVer.setBounds(89, 304, 145, 38);
		contentPane.add(btnVer);

		JRadioButton rdbtnCasaRurales = new JRadioButton("Casa Rurales");
		buttonGroup.add(rdbtnCasaRurales);
		rdbtnCasaRurales.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				for (HouseReport rh : houseRep) {
					listModel.clear();
					listModel.addElement(rh);
				}
			}

		});
		rdbtnCasaRurales.setBounds(37, 124, 109, 23);
		contentPane.add(rdbtnCasaRurales);

		JRadioButton rdbtnClientes = new JRadioButton("Clientes");
		buttonGroup.add(rdbtnClientes);
		rdbtnClientes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				listModel.clear();
				for (ClientReport cl : cliRep) {
					listModel.addElement(cl);
				}
			}
		});
		rdbtnClientes.setBounds(37, 169, 109, 23);
		contentPane.add(rdbtnClientes);

		JRadioButton rdbtnPropietario = new JRadioButton("Propietario");
		buttonGroup.add(rdbtnPropietario);
		rdbtnPropietario.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				listModel.clear();
				for (OwnerReport ow : ownRep) {
					listModel.addElement(ow);
				}
			}
		});
		rdbtnPropietario.setBounds(37, 212, 88, 23);
		contentPane.add(rdbtnPropietario);

		rdbtnCasaRurales.setSelected(true);

		JLabel lblInformes = new JLabel("Informes");
		lblInformes.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 24));
		lblInformes.setBounds(108, 30, 121, 56);
		contentPane.add(lblInformes);
	}
}
