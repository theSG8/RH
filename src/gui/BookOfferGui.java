package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;

import businessLogic.ApplicationFacadeInterfaceWS;
import domain.Offer;
import domain.RuralHouse;

public class BookOfferGui extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel tableModel;
	private String[] columnNames = new String[] { "Oferta", "PrimerDia", "UltimoDia", "Precio" };
	private RuralHouse currentHouse;
	private JLabel lbldes;
	private JLabel lblHouseName;
	private JLabel lblImg;
	private ApplicationFacadeInterfaceWS businessLogic;
	private JTextField text_noches;
	private JCalendar calendar;
	private JLabel lblNewLabel;
	private JLabel lblNBaos;
	private JLabel lblNcoc;
	private JLabel lnlNbañ;
	private JLabel lblNDor;
	private JLabel lblNDormitorios;
	private JLabel lblNGar;
	private JLabel lblNGarajes;
	private JLabel lblNComedores;
	private JLabel lblNComed;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					BookOfferGui frame = new BookOfferGui(null, new RuralHouse());
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
	public BookOfferGui(ApplicationFacadeInterfaceWS bl, RuralHouse rh) {
		setBusinessLogic(bl);
		currentHouse = rh;

		setBounds(100, 100, 959, 616);
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
		// contentPane.add(table);
		scrollPane.setViewportView(table);

		tableModel = new DefaultTableModel(null, columnNames);

		table.setModel(tableModel);

		JLabel lblOBienBusque = new JLabel("Filtrar las ofertas por fecha");
		lblOBienBusque.setBounds(68, 417, 176, 14);
		contentPane.add(lblOBienBusque);

		lblImg = new JLabel("");
		lblImg.setBounds(490, 30, 210, 180);
		contentPane.add(lblImg);

		lbldes = new JLabel("%dir%");
		lbldes.setBounds(134, 98, 122, 14);
		contentPane.add(lbldes);

		calendar = new JCalendar();
		calendar.setBounds(new Rectangle(190, 60, 225, 150));
		calendar.setBounds(282, 402, 225, 150);
		contentPane.add(calendar);

		JLabel label = new JLabel();
		label.setText("Numero de noches");
		label.setBounds(new Rectangle(40, 250, 115, 25));
		label.setBounds(62, 463, 115, 25);
		contentPane.add(label);

		text_noches = new JTextField();
		text_noches.setText("0");
		text_noches.setBounds(new Rectangle(190, 250, 155, 25));
		text_noches.setBounds(187, 463, 57, 25);
		contentPane.add(text_noches);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				Vector<Offer> offers = searchByCurrentDate();

				if (offers.isEmpty()) {
					showDialog("No hay ofertas disponibles para estas fechas. Se mostraran todas");
					tableModel.setRowCount(0);
					SetOffers();
				} else {

					tableModel.setRowCount(0);

					for (Offer of : offers) {

						if (!of.isBooked()) {
							Vector<Object> row = new Vector<Object>();

							row.add(of.getOfferNumber());
							row.add(of.getFirstDay());
							row.add(of.getLastDay());
							row.add(of.getPrice());
							tableModel.addRow(row);
						}
					}
					showDialog("Mostrando ofertas encontradas");
				}

			}
		});
		btnBuscar.setBounds(131, 529, 89, 23);
		contentPane.add(btnBuscar);

		JButton btnReservar = new JButton("RESERVAR");
		btnReservar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				Integer value = (Integer) table.getValueAt(table.getSelectedRow(), 0);

				businessLogic.bookOffer(CliOptions.currentClient, value);

				showDialog("Oferta reservada correctamente");

				tableModel.setRowCount(0);
				SetOffers();

			}
		});
		btnReservar.setBounds(642, 278, 215, 47);
		contentPane.add(btnReservar);
		
		lblNewLabel = new JLabel("N\u00BA Cocinas");
		lblNewLabel.setBounds(38, 124, 74, 14);
		contentPane.add(lblNewLabel);
		
		lblNBaos = new JLabel("N\u00BA Ba\u00F1os");
		lblNBaos.setBounds(38, 151, 74, 14);
		contentPane.add(lblNBaos);
		
		lblNcoc = new JLabel((String) null);
		lblNcoc.setBounds(134, 124, 122, 14);
		contentPane.add(lblNcoc);
		
		lnlNbañ = new JLabel((String) null);
		lnlNbañ.setBounds(134, 151, 122, 14);
		contentPane.add(lnlNbañ);
		
		lblNDor = new JLabel((String) null);
		lblNDor.setBounds(378, 100, 122, 14);
		contentPane.add(lblNDor);
		
		lblNDormitorios = new JLabel("N\u00BA Dormitorios");
		lblNDormitorios.setBounds(282, 100, 74, 14);
		contentPane.add(lblNDormitorios);
		
		lblNGar = new JLabel((String) null);
		lblNGar.setBounds(378, 124, 122, 14);
		contentPane.add(lblNGar);
		
		lblNGarajes = new JLabel("N\u00BA Garajes");
		lblNGarajes.setBounds(282, 124, 74, 14);
		contentPane.add(lblNGarajes);
		
		lblNComedores = new JLabel("N\u00BA Comedores");
		lblNComedores.setBounds(282, 151, 74, 14);
		contentPane.add(lblNComedores);
		
		lblNComed = new JLabel((String) null);
		lblNComed.setBounds(378, 151, 122, 14);
		contentPane.add(lblNComed);

		SetLabelValues();
		SetOffers();

	}

	protected Vector<Offer> searchByCurrentDate() {
		Date firstDay = BookOfferGui.this.getCalendar().getCalendar().getTime();
		firstDay = trim(firstDay);
		Calendar calendar = Calendar.getInstance();
		int numDias = Integer.parseInt(text_noches.getText());
		calendar.add(Calendar.DAY_OF_YEAR, numDias);
		Date lastDay = calendar.getTime();
		return businessLogic.getOffers(currentHouse, firstDay, lastDay);
	}

	private void SetOffers() {
		Vector<Offer> offers = businessLogic.getHouseOffers(currentHouse);
		if (offers.isEmpty()) {
			Vector<Object> row = new Vector<Object>();
			row.add("No hay ofertas disponibles");
			tableModel.addRow(row);
		}
		for (Offer of : offers) {
			if (!of.isBooked()) {
				Vector<Object> row = new Vector<Object>();

				row.add(of.getOfferNumber());
				row.add(of.getFirstDay());
				row.add(of.getLastDay());
				row.add(of.getPrice());
				tableModel.addRow(row);
			}
		}

	}

	private void setBusinessLogic(ApplicationFacadeInterfaceWS bl) {
		businessLogic = bl;
	}

	private void SetLabelValues() {

		lblHouseName.setText(currentHouse.getHouseNumber() + " : " + currentHouse.getCity());
		lbldes.setText(currentHouse.getDescription());
		lblNcoc.setText(currentHouse.getnKitchens());
		lnlNbañ.setText(currentHouse.getnBathrooms());
		lblNDor.setText(currentHouse.getnBedrooms());
		lblNGar.setText(currentHouse.getnParking());
		lblNComed.setText(currentHouse.getnDrooms());
		ImageIcon image = businessLogic.getHouseImage(currentHouse);
		if (image == null) {
			showDialog("Error al encontrar la imagen");
		} else {
			image.setImage(
					image.getImage().getScaledInstance(lblImg.getWidth(), lblImg.getHeight(), Image.SCALE_SMOOTH));
			lblImg.setIcon(image);
		}

	}

	private void showDialog(String msg) {
		JOptionPane.showMessageDialog(this, msg);

	}

	private Date trim(Date date) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		return calendar.getTime();
	}

	public JCalendar getCalendar() {
		return calendar;
	}

}
