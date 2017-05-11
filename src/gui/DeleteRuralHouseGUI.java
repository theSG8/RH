package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import businessLogic.ApplicationFacadeInterfaceWS;
import businessLogic.FacadeImplementationWS;
import domain.Offer;
import domain.RuralHouse;

public class DeleteRuralHouseGUI extends JFrame {

	private JPanel contentPane;
	private ApplicationFacadeInterfaceWS businessLogic;
	private JList<RuralHouse> ruralHouseslist;
	JButton btnDeleteRuralHouse;
	private DefaultListModel<RuralHouse> listModel = new DefaultListModel<RuralHouse>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					DeleteRuralHouseGUI frame = new DeleteRuralHouseGUI(new FacadeImplementationWS(), null);
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
	public DeleteRuralHouseGUI(ApplicationFacadeInterfaceWS bl, Vector<RuralHouse> rh) {

		setBusinessLogic(bl);

		setBounds(100, 100, 450, 341);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("DeleteRuralHouse"));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(151, 30, 131, 16);
		contentPane.add(lblNewLabel);

		JList<RuralHouse> ruralHouseslist = new JList<RuralHouse>();
		ruralHouseslist.setBounds(47, 77, 340, 108);
		contentPane.add(ruralHouseslist);
		ruralHouseslist.setModel(listModel);

		JButton btnDeleteRuralHouse = new JButton(ResourceBundle.getBundle("Etiquetas").getString("DeleteRuralHouse")); //$NON-NLS-1$ //$NON-NLS-2$
		btnDeleteRuralHouse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (ruralHouseslist.getSelectedValue() != null) {

					Vector<Offer> houseOffers = businessLogic.getHouseOffers(ruralHouseslist.getSelectedValue());
					boolean ok = true;
					for (Offer o : houseOffers) {
						if (o.isBooked()) {
							ok = false;
							break;
						}
					}
					if (ok) {

						businessLogic.removeHouse(ruralHouseslist.getSelectedValue());
						listModel.remove(ruralHouseslist.getSelectedIndex());
					} else {
						showDialog("No se puede eliminar: La casa tiene ofertas reservadas");

					}
				}
			}
		});
		btnDeleteRuralHouse.setBounds(139, 253, 157, 39);
		contentPane.add(btnDeleteRuralHouse);
		
		JButton btnEdit = new JButton(ResourceBundle.getBundle("Etiquetas").getString("DeleteRuralHouseGUI.btnEdit.text")); //$NON-NLS-1$ //$NON-NLS-2$
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (ruralHouseslist.getSelectedValue() != null) {
				EditHouse eh = new EditHouse(businessLogic, ruralHouseslist.getSelectedValue());
				eh.setVisible(true);
				close();
				}
			}
		});
		btnEdit.setBounds(139, 203, 157, 39);
		contentPane.add(btnEdit);

		for (RuralHouse h : rh) {
			listModel.addElement(h);
		}
	}

	private void setBusinessLogic(ApplicationFacadeInterfaceWS bl) {
		businessLogic = bl;
	}

	private void showDialog(String msg) {
		JOptionPane.showMessageDialog(this, msg);

	}
	
	private void close() {
		this.setVisible(false);

	}
}
