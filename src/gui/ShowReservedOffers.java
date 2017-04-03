package gui;

import java.awt.EventQueue;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.ApplicationFacadeInterfaceWS;
import businessLogic.FacadeImplementationWS;
import domain.Offer;

public class ShowReservedOffers extends JFrame {

	private JPanel contentPane;
	private DefaultListModel<Offer> listModel = new DefaultListModel<Offer>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					ShowReservedOffers frame = new ShowReservedOffers(new FacadeImplementationWS(), null);
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
	public ShowReservedOffers(ApplicationFacadeInterfaceWS bl, Vector<Offer> of) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JList list = new JList();
		list.setBounds(10, 23, 414, 139);
		contentPane.add(list);
		list.setModel(listModel);

		JButton btnEliminarReserva = new JButton("Eliminar reserva");
		btnEliminarReserva.setBounds(120, 201, 194, 38);
		contentPane.add(btnEliminarReserva);

		for (Offer o : of) {
			listModel.addElement(o);
		}
	}

}
