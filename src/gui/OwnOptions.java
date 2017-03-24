package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;

import java.awt.Font;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import domain.Owner;
import domain.RuralHouse;
import businessLogic.ApplicationFacadeInterfaceWS;
import businessLogic.FacadeImplementationWS;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OwnOptions extends JFrame {

	private JPanel contentPane;
	private DefaultComboBoxModel ownerOptions = new DefaultComboBoxModel();
	private ApplicationFacadeInterfaceWS businessLogic;
	public static Owner currentOwner;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OwnOptions frame = new OwnOptions(
							new FacadeImplementationWS(), null);
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
	public OwnOptions(ApplicationFacadeInterfaceWS bl, Owner current) {
		setBusinessLogic(bl);
		currentOwner = current;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 433, 334);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel(ResourceBundle.getBundle("Etiquetas")
				.getString("OwnerOptions"));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(105, 32, 207, 28);
		contentPane.add(lblNewLabel);
		ownerOptions.addElement(ResourceBundle.getBundle("Etiquetas")
				.getString("SetOffer"));
		ownerOptions.addElement(ResourceBundle.getBundle("Etiquetas")
				.getString("DeleteOffer"));
		ownerOptions.addElement(ResourceBundle.getBundle("Etiquetas")
				.getString("AddRuralHouse"));
		ownerOptions.addElement(ResourceBundle.getBundle("Etiquetas")
				.getString("DeleteRuralHouse"));

		JButton btnSetOffer = new JButton(ResourceBundle
				.getBundle("Etiquetas").getString("SetOffer")); //$NON-NLS-1$ //$NON-NLS-2$
		btnSetOffer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Vector<RuralHouse> all =businessLogic.getOwnerHouses(currentOwner);
				if(all.isEmpty()){
					showDialog("No hay casas disponbles. Añadir casa primero");
				}
				else{
				SetAvailabilityGUI sa = new SetAvailabilityGUI(businessLogic
						.getOwnerHouses(currentOwner));
				sa.setVisible(true);
				}
			
			
			}
		});
		btnSetOffer.setBounds(105, 85, 207, 28);
		contentPane.add(btnSetOffer);

		JButton btnDeleteOffer = new JButton(ResourceBundle.getBundle(
				"Etiquetas").getString("DeleteOffer")); //$NON-NLS-1$ //$NON-NLS-2$
		btnDeleteOffer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				DeleteOfferGUI dg = new DeleteOfferGUI(bl, businessLogic
						.getAllOffers());
				dg.setVisible(true);
			}
		});
		btnDeleteOffer.setBounds(105, 121, 207, 28);
		contentPane.add(btnDeleteOffer);

		JButton btnAddHouse = new JButton(ResourceBundle
				.getBundle("Etiquetas").getString("AddRuralHouse")); //$NON-NLS-1$ //$NON-NLS-2$
		btnAddHouse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddRuralHouseGUI ag = new AddRuralHouseGUI(businessLogic);
				ag.setVisible(true);

			}
		});
		btnAddHouse.setBounds(105, 161, 207, 28);
		contentPane.add(btnAddHouse);

		JButton btnRemoveHouse = new JButton(ResourceBundle.getBundle(
				"Etiquetas").getString("DeleteRuralHouse")); //$NON-NLS-1$ //$NON-NLS-2$
		btnRemoveHouse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeleteRuralHouseGUI dr = new DeleteRuralHouseGUI(bl,
						businessLogic.getAllRuralHouses());
				dr.setVisible(true);

			}
		});
		btnRemoveHouse.setBounds(105, 200, 207, 28);
		contentPane.add(btnRemoveHouse);
	}

	private void setBusinessLogic(ApplicationFacadeInterfaceWS bl) {
		businessLogic = bl;
	}

	private void showDialog(String msg) {
		JOptionPane.showMessageDialog(this, msg);

	}
}
