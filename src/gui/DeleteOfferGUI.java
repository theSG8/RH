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

public class DeleteOfferGUI extends JFrame {

	private JPanel contentPane;
	private JList<Offer> offerList;
	private ApplicationFacadeInterfaceWS businessLogic;
	private DefaultListModel<Offer> listModel = new DefaultListModel<Offer>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					DeleteOfferGUI frame = new DeleteOfferGUI(new FacadeImplementationWS(), null);
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
	public DeleteOfferGUI(ApplicationFacadeInterfaceWS bl, Vector<Offer> allOffers) {

		setBusinessLogic(bl);
		setBounds(100, 100, 709, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("DeleteOffer"));
		lblNewLabel.setBounds(294, 30, 105, 16);
		contentPane.add(lblNewLabel);

		JButton btnDeleteOffer = new JButton(ResourceBundle.getBundle("Etiquetas").getString("DeleteOffer")); //$NON-NLS-1$ //$NON-NLS-2$
		btnDeleteOffer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (offerList.getSelectedValue() != null) {
					Offer selectedOffer = offerList.getSelectedValue();
					businessLogic.removeOffer(selectedOffer);
					listModel.remove(offerList.getSelectedIndex());
				}
			}
		});
		btnDeleteOffer.setBounds(273, 205, 146, 35);
		contentPane.add(btnDeleteOffer);

		offerList = new JList<Offer>();

		offerList.setBounds(43, 59, 607, 133);
		contentPane.add(offerList);
		offerList.setModel(listModel);

		for (Offer of : allOffers) {
			listModel.addElement(of);
		}

	}

	private void setBusinessLogic(ApplicationFacadeInterfaceWS bl) {
		businessLogic = bl;
	}

	private void showDialog(String msg) {
		JOptionPane.showMessageDialog(this, msg);

	}
}
