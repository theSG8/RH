package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTable;
import javax.swing.JScrollPane;

import businessLogic.ApplicationFacadeInterfaceWS;
import domain.Offer;
import domain.RuralHouse;

import java.awt.Rectangle;
import java.util.Vector;

public class ReserveOfferGui extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel tableModel;
	private String[] columnNames = new String[]{"Oferta","PrimerDia","UltimoDia","Precio"};
	private  RuralHouse currentHouse;
	private JLabel lbldes;
	private JLabel lblHouseName;
	private JLabel lblImg;
	private ApplicationFacadeInterfaceWS businessLogic;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReserveOfferGui frame = new ReserveOfferGui(null,new RuralHouse());
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
	public ReserveOfferGui(ApplicationFacadeInterfaceWS bl , RuralHouse rh) {
		setBusinessLogic(bl);
		currentHouse=rh;
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 848, 508);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		 lblHouseName = new JLabel("Nombre de la casa");
		lblHouseName.setFont(new Font("Arial", Font.BOLD, 18));
		lblHouseName.setBounds(38, 27, 206, 34);
		contentPane.add(lblHouseName);
		
		JLabel lblDescripcion = new JLabel("Descripci\u00F3n:  ");
		lblDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDescripcion.setBounds(38, 94, 86, 20);
		contentPane.add(lblDescripcion);
		
		JLabel lblOfertasDisponibles = new JLabel("Ofertas disponibles");
		lblOfertasDisponibles.setFont(new Font("Arial", Font.BOLD, 18));
		lblOfertasDisponibles.setBounds(50, 176, 206, 34);
		contentPane.add(lblOfertasDisponibles);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(new Rectangle(45, 305, 320, 116));
		scrollPane.setBounds(200, 250, 320, 116);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setBounds(68, 234, 554, 135);
		//contentPane.add(table);
	    scrollPane.setViewportView(table);
	    
		tableModel = new DefaultTableModel(
    			null,
            	columnNames);	
		
		
		table.setModel(tableModel);
		
		
		JLabel lblOBienBusque = new JLabel("O bien busque la disponibilidad");
		lblOBienBusque.setBounds(68, 417, 176, 14);
		contentPane.add(lblOBienBusque);
		
	    lblImg = new JLabel("");
		lblImg.setBounds(490, 30, 210, 180);
		contentPane.add(lblImg);
		
		lbldes = new JLabel("%dir%");
		lbldes.setBounds(134, 98, 122, 14);
		contentPane.add(lbldes);
		
		SetLabelValues();
		SetOffers();
		
	}

	private void SetOffers() {
		Vector <Offer> offers =businessLogic.getHouseOffers(currentHouse);
		tableModel.setDataVector(null, columnNames);
		if(offers.isEmpty())showDialog("vacio");
		for (Offer of : offers){
			Vector<Object> row = new Vector<Object>();
			
			
			row.add(of.getOfferNumber());
			row.add(of.getFirstDay());
			row.add(of.getLastDay());
			row.add(of.getPrice());
			tableModel.addRow(row);
		}
		
	}

	private void setBusinessLogic(ApplicationFacadeInterfaceWS bl) {
		businessLogic = bl;
	}
	
	private void SetLabelValues() {
		
		lblHouseName.setText(currentHouse.getHouseNumber() + " : " + currentHouse.getCity());
		lbldes.setText(currentHouse.getDescription());
		ImageIcon image = businessLogic.getHouseImage(currentHouse);
		if (image==null){
			showDialog("Error al encontrar la imagen");
		}
		else {
			image.setImage(image.getImage().getScaledInstance(lblImg.getWidth(), lblImg.getHeight(), Image.SCALE_SMOOTH));
			lblImg.setIcon(image);		
		}
		
	}
	
	private void showDialog(String msg) {
		JOptionPane.showMessageDialog(this, msg);

	}
}
