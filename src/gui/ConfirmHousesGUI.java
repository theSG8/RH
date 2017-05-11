package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.ApplicationFacadeInterfaceWS;
import domain.RuralHouse;

public class ConfirmHousesGUI extends JFrame {

	private JPanel contentPane;
	private DefaultListModel<RuralHouse> listModel = new DefaultListModel<RuralHouse>();
	private ApplicationFacadeInterfaceWS businessLogic;
	private Vector<RuralHouse> houses;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					ConfirmHousesGUI frame = new ConfirmHousesGUI(null);
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
	public ConfirmHousesGUI(ApplicationFacadeInterfaceWS bl) {

		businessLogic = bl;

		houses = businessLogic.getAllRuralHouses();

		setBounds(100, 100, 380, 425);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JList<RuralHouse> list = new JList<RuralHouse>();
		list.setBounds(10, 77, 344, 231);

		list.setModel(listModel);

		for (RuralHouse rh : houses) {
			if (!rh.isConfirmed) {
				listModel.addElement(rh);
			}
		}

		contentPane.add(list);

		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				businessLogic.confirmHouse(list.getSelectedValue());

				listModel.clear();

				houses = businessLogic.getAllRuralHouses();

				for (RuralHouse rh : houses) {
					if (!rh.isConfirmed) {
						listModel.addElement(rh);
					}
				}
			}
		});
		btnConfirmar.setBounds(119, 319, 127, 31);
		contentPane.add(btnConfirmar);

		JLabel lblConfirmarCasas = new JLabel("Confirmar Casas");
		lblConfirmarCasas.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblConfirmarCasas.setBounds(119, 27, 139, 39);
		contentPane.add(lblConfirmarCasas);
	}
}
