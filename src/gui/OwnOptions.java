package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import businessLogic.ApplicationFacadeInterfaceWS;
import businessLogic.FacadeImplementationWS;
import domain.Offer;
import domain.Owner;
import domain.RuralHouse;

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
			@Override
			public void run() {
				try {
					OwnOptions frame = new OwnOptions(new FacadeImplementationWS(), null);
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

		JLabel lblNewLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("OwnerOptions"));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(105, 13, 207, 28);
		contentPane.add(lblNewLabel);
		ownerOptions.addElement(ResourceBundle.getBundle("Etiquetas").getString("SetOffer"));
		ownerOptions.addElement(ResourceBundle.getBundle("Etiquetas").getString("DeleteOffer"));
		ownerOptions.addElement(ResourceBundle.getBundle("Etiquetas").getString("AddRuralHouse"));
		ownerOptions.addElement(ResourceBundle.getBundle("Etiquetas").getString("DeleteRuralHouse"));

		JButton btnSetOffer = new JButton(ResourceBundle.getBundle("Etiquetas").getString("SetOffer")); //$NON-NLS-1$ //$NON-NLS-2$
		btnSetOffer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				Vector<RuralHouse> all = businessLogic.getOwnerHouses(currentOwner);
				if (all.isEmpty()) {
					showDialog("No hay casas disponbles. A�adir casa primero");
				} else {
					SetAvailabilityGUI sa = new SetAvailabilityGUI(all);
					sa.setVisible(true);
				}

			}
		});
		btnSetOffer.setBounds(105, 54, 207, 28);
		contentPane.add(btnSetOffer);

		JButton btnDeleteOffer = new JButton(ResourceBundle.getBundle("Etiquetas").getString("DeleteOffer")); //$NON-NLS-1$ //$NON-NLS-2$
		btnDeleteOffer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				Vector<Offer> all = businessLogic.getOwnerOffers(currentOwner);
				if (all.isEmpty()) {
					showDialog("No se han creado ofertas para sus casas rurales");
				} else {
					DeleteOfferGUI dg = new DeleteOfferGUI(bl, all);
					dg.setVisible(true);
				}
			}
		});
		btnDeleteOffer.setBounds(105, 95, 207, 28);
		contentPane.add(btnDeleteOffer);

		JButton btnAddHouse = new JButton(ResourceBundle.getBundle("Etiquetas").getString("AddRuralHouse")); //$NON-NLS-1$ //$NON-NLS-2$
		btnAddHouse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AddRuralHouseGUI ag = new AddRuralHouseGUI(businessLogic);
				ag.setVisible(true);

			}
		});
		btnAddHouse.setBounds(105, 136, 207, 28);
		contentPane.add(btnAddHouse);

		JButton btnRemoveHouse = new JButton(ResourceBundle.getBundle("Etiquetas").getString("DeleteRuralHouse")); //$NON-NLS-1$ //$NON-NLS-2$
		btnRemoveHouse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				Vector<RuralHouse> all = businessLogic.getOwnerHouses(currentOwner);
				if (all.isEmpty()) {
					showDialog("No hay casas disponbles. A�adir casa primero");
				} else {
					DeleteRuralHouseGUI dr = new DeleteRuralHouseGUI(bl, all);
					dr.setVisible(true);
				}
			}
		});
		btnRemoveHouse.setBounds(105, 177, 207, 28);
		contentPane.add(btnRemoveHouse);

		JButton btnLogout = new JButton(ResourceBundle.getBundle("Etiquetas").getString("OwnOptions.btnLogout.text")); //$NON-NLS-1$ //$NON-NLS-2$
		btnLogout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				LoginGUI lg = new LoginGUI(bl);
				currentOwner = null;
				lg.setVisible(true);
				closeOwnOp(false);
			}
		});
		btnLogout.setBounds(318, 261, 89, 23);
		contentPane.add(btnLogout);

		JLabel lblestasLogeadoComo = new JLabel(
				ResourceBundle.getBundle("Etiquetas").getString("OwnOptions.lblestasLogeadoComo.text")); //$NON-NLS-1$ //$NON-NLS-2$
		lblestasLogeadoComo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblestasLogeadoComo.setBounds(10, 265, 207, 14);
		contentPane.add(lblestasLogeadoComo);
		lblestasLogeadoComo.setText("Estás logeado como: " + currentOwner.getUsername());
		
		JButton btnEditPerfil = new JButton("Editar Perfil"); //$NON-NLS-1$ //$NON-NLS-2$
		btnEditPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EditOwnerGUI eo = new EditOwnerGUI(currentOwner, businessLogic);
				eo.setVisible(true);
			}
		});
		btnEditPerfil.setBounds(105, 218, 207, 28);
		contentPane.add(btnEditPerfil);
	}

	private void setBusinessLogic(ApplicationFacadeInterfaceWS bl) {
		businessLogic = bl;
	}

	private void showDialog(String msg) {
		JOptionPane.showMessageDialog(this, msg);

	}

	private void closeOwnOp(boolean cl) {
		this.setVisible(cl);

	}
}
