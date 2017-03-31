package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTable;
import javax.swing.JScrollPane;

import domain.RuralHouse;

import java.awt.Rectangle;

public class ReserveOfferGui extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel tableModel;
	private String[] columnNames = new String[]{"Oferta","PrimerDia","UltimoDia","Precio"};
	private  RuralHouse currentHouse;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReserveOfferGui frame = new ReserveOfferGui(null);
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
	public ReserveOfferGui(RuralHouse rh) {
		currentHouse=rh;
		SetLabelValues();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 848, 508);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombreDeLa = new JLabel("Nombre de la casa");
		lblNombreDeLa.setFont(new Font("Arial", Font.BOLD, 18));
		lblNombreDeLa.setBounds(38, 27, 206, 34);
		contentPane.add(lblNombreDeLa);
		
		JLabel lblDireccin = new JLabel("Direcci\u00F3n:  ");
		lblDireccin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDireccin.setBounds(38, 94, 86, 20);
		contentPane.add(lblDireccin);
		
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
		contentPane.add(table);
	    scrollPane.setViewportView(table);
	    
		tableModel = new DefaultTableModel(
    			null,
            	columnNames);	
		
		table.setModel(tableModel);
		
		
		JLabel lblOBienBusque = new JLabel("O bien busque la disponibilidad");
		lblOBienBusque.setBounds(68, 417, 176, 14);
		contentPane.add(lblOBienBusque);
		
		JLabel lblImg = new JLabel("");
		lblImg.setBounds(490, 30, 210, 180);
		contentPane.add(lblImg);
		
		JLabel lbldir = new JLabel("%dir%");
		lbldir.setBounds(134, 98, 46, 14);
		contentPane.add(lbldir);
		
		
	
		
	}

	private void SetLabelValues() {
		
		
	}
}
