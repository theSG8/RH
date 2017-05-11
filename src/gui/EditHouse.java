package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;

import domain.RuralHouse;
import businessLogic.ApplicationFacadeInterfaceWS;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class EditHouse extends JFrame {

	private JPanel Panel;
	private JTextField cityField;
	private JTextField desField;
	private JTextField bedField;
	private JTextField ParkField;
	private JTextField BathField;
	private JTextField KitchnField;
	private JTextField DinField;
	private ApplicationFacadeInterfaceWS businessLogic;
	private RuralHouse currentHouse;
	private JLabel lbl_img;
	private File current_image = null;
	private File oldImage = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditHouse frame = new EditHouse(null, null);
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
	public EditHouse(ApplicationFacadeInterfaceWS bl, RuralHouse rh) {
		setBusinessLogic(bl);
		currentHouse = rh;

		current_image = new File("img/" + currentHouse.getHouseNumber()
				+ ".png");
		oldImage = current_image;

		setBounds(100, 100, 612, 422);
		Panel = new JPanel();
		Panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(Panel);
		Panel.setLayout(null);

		JLabel lblEditRuralHouse = new JLabel("Edit rural house");
		lblEditRuralHouse.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEditRuralHouse.setBounds(231, 11, 142, 16);
		Panel.add(lblEditRuralHouse);

		JLabel label_1 = new JLabel("City");
		label_1.setBounds(10, 77, 56, 16);
		Panel.add(label_1);

		cityField = new JTextField();
		cityField.setText("");
		cityField.setColumns(10);
		cityField.setBounds(109, 74, 142, 22);
		Panel.add(cityField);

		JLabel label_2 = new JLabel("Description");
		label_2.setBounds(10, 109, 81, 16);
		Panel.add(label_2);

		desField = new JTextField();
		desField.setText("");
		desField.setColumns(10);
		desField.setBounds(109, 106, 142, 22);
		Panel.add(desField);

		JButton btnConfirmChanges = new JButton("Confirm changes");
		btnConfirmChanges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					if (Integer.parseInt(bedField.getText()) >= 3
							&& Integer.parseInt(BathField.getText()) >= 2
							&& Integer.parseInt(KitchnField.getText()) >= 1
							&& !cityField.getText().equals("")) {
						RuralHouse rh = businessLogic.updateHouse(
								currentHouse.getHouseNumber(),
								desField.getText(), cityField.getText(),
								KitchnField.getText(), bedField.getText(),
								BathField.getText(), DinField.getText(),
								ParkField.getText());
						showDialog("Casa rural modificada correctamente");
						/*
						 * cityField.setText(""); desField.setText("");
						 * KitchnField.setText(""); bedField.setText("");
						 * BathField.setText(""); DinField.setText("");
						 * ParkField.setText("");
						 */

						copyImageOnProject(String.valueOf(rh.getHouseNumber()));
						// setDefaultImage();
						close();
					} else {
						if (cityField.getText().equals("")) {
							showDialog("Una casa rural debe tener una ciudad");
						}
						if (Integer.parseInt(bedField.getText()) < 3
								|| Integer.parseInt(BathField.getText()) < 2
								|| Integer.parseInt(KitchnField.getText()) < 1) {
							showDialog("Una casa rural requiere de por lo menos 1 cocina, 3 habitaciones y 2 baños");
						}
					}
				} catch (NumberFormatException e) {
					showDialog("Los parametros dormitorios, cocina y ba�os hay que rellenarlos obligatoriamente");
				}

			}
		});
		btnConfirmChanges.setBounds(35, 316, 155, 38);
		Panel.add(btnConfirmChanges);

		lbl_img = new JLabel("");
		lbl_img.setBounds(221, 224, 152, 130);
		Panel.add(lbl_img);

		setImage();

		JButton btnSelecImg = new JButton("Seleccionar Imagen");
		btnSelecImg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				File f = GetImageFromChooser();
				if (f != null) {
					current_image = f;
					ImageIcon newimage = new ImageIcon(f.getAbsolutePath());
					newimage.setImage(newimage.getImage().getScaledInstance(
							lbl_img.getWidth(), lbl_img.getHeight(),
							Image.SCALE_SMOOTH));
					lbl_img.setIcon(newimage);
				} else {

					showDialog("Error");
				}

			}
		});
		btnSelecImg.setBounds(390, 317, 155, 36);
		Panel.add(btnSelecImg);

		JLabel label_4 = new JLabel("Bedrooms");
		label_4.setBounds(308, 131, 102, 16);
		Panel.add(label_4);

		bedField = new JTextField();
		bedField.setText("");
		bedField.setColumns(10);
		bedField.setBounds(403, 128, 142, 22);
		Panel.add(bedField);

		JLabel label_5 = new JLabel("Parking spaces");
		label_5.setBounds(10, 179, 92, 16);
		Panel.add(label_5);

		ParkField = new JTextField();
		ParkField.setText("");
		ParkField.setColumns(10);
		ParkField.setBounds(109, 176, 142, 22);
		Panel.add(ParkField);

		BathField = new JTextField();
		BathField.setText("");
		BathField.setColumns(10);
		BathField.setBounds(403, 160, 142, 22);
		Panel.add(BathField);

		JLabel label_6 = new JLabel("Kitchens");
		label_6.setBounds(308, 102, 102, 16);
		Panel.add(label_6);

		JLabel label_7 = new JLabel("Dinning rooms");
		label_7.setBounds(10, 144, 102, 16);
		Panel.add(label_7);

		JLabel label_8 = new JLabel("Bathrooms");
		label_8.setBounds(308, 165, 102, 16);
		Panel.add(label_8);

		KitchnField = new JTextField();
		KitchnField.setText("");
		KitchnField.setColumns(10);
		KitchnField.setBounds(403, 96, 142, 22);
		Panel.add(KitchnField);

		DinField = new JTextField();
		DinField.setText("");
		DinField.setColumns(10);
		DinField.setBounds(109, 141, 142, 22);
		Panel.add(DinField);
		initializeFields();
	}

	private void initializeFields() {
		cityField.setText(currentHouse.getCity());
		desField.setText(currentHouse.getDescription());
		ParkField.setText(currentHouse.getnParking());
		BathField.setText(currentHouse.getnBathrooms());
		bedField.setText(currentHouse.getnBedrooms());
		KitchnField.setText(currentHouse.getnKitchens());
		DinField.setText(currentHouse.getnDrooms());

	}

	private void setBusinessLogic(ApplicationFacadeInterfaceWS bl) {
		businessLogic = bl;

	}

	private void close() {
		this.setVisible(false);

	}

	private void showDialog(String msg) {
		JOptionPane.showMessageDialog(this, msg);

	}

	protected void setImage() {
		ImageIcon def = new ImageIcon("img/" + currentHouse.getHouseNumber()
				+ ".png");
		def.setImage(def.getImage().getScaledInstance(lbl_img.getWidth(),
				lbl_img.getHeight(), Image.SCALE_SMOOTH));
		lbl_img.setIcon(def);

	}

	protected void copyImageOnProject(String imageName) {

		String extension = current_image.getName().substring(
				current_image.getName().lastIndexOf("."),
				current_image.getName().length());

		String output = "img/" + imageName + ".png";
		File o = new File(output);
		try {
			Files.copy(current_image.toPath(), o.toPath());
		} catch (IOException e) {
			showDialog("Error al copiar la imagen al sistema");
			e.printStackTrace();
		}

	
	}

	protected File GetImageFromChooser() {

		final FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"Im�genes", "jpg", "png", "jpeg");
		File f;
		final JFileChooser FileChooser = new JFileChooser();
		FileChooser.setFileFilter(filter);
		int returnState = FileChooser.showOpenDialog(this);
		if (returnState == JFileChooser.APPROVE_OPTION) {
			return new File(FileChooser.getSelectedFile().getPath());
		} else {
			return null;
		}

	}

}
