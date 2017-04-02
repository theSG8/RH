package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollBar;
import javax.swing.JList;

import businessLogic.ApplicationFacadeInterfaceWS;
import domain.RuralHouse;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
			public void run() {
				try {
					AllHouses frame = new AllHouses(null,null);
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
	public AllHouses(ApplicationFacadeInterfaceWS bl , Vector<RuralHouse> houses) {
		setBusinessLogic(bl);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 576);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		list= new JList<RuralHouse>();
		list.setBounds(56, 36, 309, 431);
		list.setModel(listModel);
		
		for(RuralHouse rh :houses){
		listModel.addElement(rh);}
		
		contentPane.add(list);
		
		JButton btnVerOfertas = new JButton("Ver Ofertas");
		btnVerOfertas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(list.getSelectedValue()!=null){
					ReserveOfferGui rog = new ReserveOfferGui(businessLogic, list.getSelectedValue());
					rog.setVisible(true);
					
					
				}
				else{
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
	
}
