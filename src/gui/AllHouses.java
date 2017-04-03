package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.ApplicationFacadeInterfaceWS;
import domain.RuralHouse;

public class AllHouses extends JFrame {

	private JPanel contentPane;
	private DefaultListModel<RuralHouse> listModel = new DefaultListModel<RuralHouse>();
	private JList<RuralHouse> list;
	private ApplicationFacadeInterfaceWS businessLogic;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					AllHouses frame = new AllHouses(null, null);
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
	public AllHouses(ApplicationFacadeInterfaceWS bl, Vector<RuralHouse> houses) {
		setBusinessLogic(bl);
		setBounds(100, 100, 450, 576);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		list = new JList<RuralHouse>();
		list.setBounds(56, 36, 309, 431);
		list.setModel(listModel);

		for (RuralHouse rh : houses) {
			listModel.addElement(rh);
		}

		contentPane.add(list);

		JButton btnVerOfertas = new JButton("Ver Ofertas");
		btnVerOfertas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (list.getSelectedValue() != null) {
					BookOfferGui rog = new BookOfferGui(businessLogic, list.getSelectedValue());
					rog.setVisible(true);
					close();

				} else {
					showDialog("No se ha seleccionado ninguna casa");

				}

			}
		});
		btnVerOfertas.setBounds(66, 478, 299, 37);
		contentPane.add(btnVerOfertas);
	}

	private void setBusinessLogic(ApplicationFacadeInterfaceWS bl) {
		businessLogic = bl;
	}

	private void showDialog(String msg) {
		JOptionPane.showMessageDialog(this, msg);

	}
	private void close(){
		
		this.setVisible(false);
	}
}
