package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class ShowBookedOffers extends JFrame {

	private JList<Offer> list;
	private JPanel contentPane;
	private DefaultListModel<Offer> listModel = new DefaultListModel<Offer>();
	private ApplicationFacadeInterfaceWS businessLogic;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					ShowBookedOffers frame = new ShowBookedOffers(new FacadeImplementationWS(), null);
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
	public ShowBookedOffers(ApplicationFacadeInterfaceWS bl, Vector<Offer> of) {
		setBusinessLogic(bl);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		list = new JList<Offer>();
		list.setBounds(10, 23, 414, 139);
		contentPane.add(list);
		list.setModel(listModel);

		JButton btnEliminarReserva = new JButton("Eliminar reserva");
		btnEliminarReserva.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (list.getSelectedValue() != null) {
					Offer of = list.getSelectedValue();
					businessLogic.cancelBooking(CliOptions.currentClient, of);
					listModel.remove(list.getSelectedIndex());
				}
			}
		});
		btnEliminarReserva.setBounds(120, 201, 194, 38);
		contentPane.add(btnEliminarReserva);

		for (Offer o : of) {
			listModel.addElement(o);
		}
	}

	private void setBusinessLogic(ApplicationFacadeInterfaceWS bl) {
		businessLogic = bl;

	}

}
