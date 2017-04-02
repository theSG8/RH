package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import javax.imageio.stream.FileImageInputStream;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import domain.RuralHouse;
import businessLogic.ApplicationFacadeInterfaceWS;
import businessLogic.FacadeImplementationWS;
import javax.swing.JTextArea;

public class AddRuralHouseGUI extends JFrame {

	private JPanel contentPane;
	private JTextField cityTextField;
	private JTextField descriptionTextField;
	private JLabel lbl_img;
	private ApplicationFacadeInterfaceWS businessLogic;
	private File current_image=new File("img/default.png");
	private JTextField dormTextField;
	private JTextField garTextField;
	private JTextField baTextField;
	private JTextField cocTextField;
	private JTextField comTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					AddRuralHouseGUI frame = new AddRuralHouseGUI(new FacadeImplementationWS());
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
	public AddRuralHouseGUI(ApplicationFacadeInterfaceWS bl) {
		setBusinessLogic(bl);
		
		setBounds(100, 100, 619, 449);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("AddRuralHouse"));
		lblNewLabel.setBounds(243, 23, 142, 16);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("City"));
		lblNewLabel_1.setBounds(22, 89, 56, 16);
		contentPane.add(lblNewLabel_1);

		cityTextField = new JTextField();
		cityTextField.setBounds(121, 86, 142, 22);
		cityTextField.setText("");
		contentPane.add(cityTextField);
		cityTextField.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Description"));
		lblNewLabel_2.setBounds(22, 121, 81, 16);
		contentPane.add(lblNewLabel_2);

		descriptionTextField = new JTextField();
		descriptionTextField.setBounds(121, 118, 142, 22);
		descriptionTextField.setText("");
		contentPane.add(descriptionTextField);
		descriptionTextField.setColumns(10);

		JButton btnAddRuralHouse = new JButton(ResourceBundle.getBundle("Etiquetas").getString("AddRuralHouse"));
		btnAddRuralHouse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
				if (Integer.parseInt(dormTextField.getText())>=3 && Integer.parseInt(baTextField.getText())>=2
				&& Integer.parseInt(cocTextField.getText())>=1){
					RuralHouse rh =businessLogic.addHouse(descriptionTextField.getText(), cityTextField.getText(),
					OwnOptions.currentOwner, cocTextField.getText(), dormTextField.getText(), baTextField.getText(), 
					comTextField.getText(), garTextField.getText());
					showDialog("Casa rural aï¿½adida correctamente");
					cityTextField.setText("");
					descriptionTextField.setText("");
					cocTextField.setText("");
					dormTextField.setText("");
					baTextField.setText("");
					comTextField.setText("");
					garTextField.setText("");  
					copyImageOnProject(String.valueOf(rh.getHouseNumber()));
					setDefaultImage();
				} else {
					showDialog("Una casa rural requiere de por lo menos 1 cocina, 3 habitaciones y 2 baños");
				}
				} catch (NumberFormatException e){
					showDialog("Los parametros dormitorios, cocina y baños hay que rellenarlos obligatoriamente");
				}
				
				
				
				

			}
		});
		btnAddRuralHouse.setBounds(22, 329, 155, 38);
		contentPane.add(btnAddRuralHouse);
		
		 lbl_img = new JLabel("");
		lbl_img.setBounds(212, 238, 152, 130);
		setDefaultImage();
		
	
		contentPane.add(lbl_img);
		
		JButton btnSeleccionarImagen = new JButton(ResourceBundle.getBundle("Etiquetas").getString("AddRuralHouseGUI.btnSeleccionarImagen.text")); //$NON-NLS-1$ //$NON-NLS-2$
		btnSeleccionarImagen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				File f = GetImageFromChooser();
				if(f!=null){
				current_image=f;
				ImageIcon newimage = new ImageIcon(f.getAbsolutePath());
				newimage.setImage(newimage.getImage().getScaledInstance(lbl_img.getWidth(), lbl_img.getHeight(), Image.SCALE_SMOOTH));
				lbl_img.setIcon(newimage);
				}
				else{
					
					showDialog("Error");
				}
		
				
			}
		});
		btnSeleccionarImagen.setBounds(402, 329, 155, 36);
		contentPane.add(btnSeleccionarImagen);
		
		JLabel label = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Bedrooms"));
		label.setBounds(320, 143, 102, 16);
		contentPane.add(label);
		
		dormTextField = new JTextField();
		dormTextField.setText(""); //$NON-NLS-1$ //$NON-NLS-2$
		dormTextField.setBounds(415, 140, 142, 22);
		contentPane.add(dormTextField);
		dormTextField.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ParkingSpaces")); //$NON-NLS-1$ //$NON-NLS-2$
		lblNewLabel_4.setBounds(22, 191, 92, 16);
		contentPane.add(lblNewLabel_4);
		
		garTextField = new JTextField();
		garTextField.setText(""); //$NON-NLS-1$ //$NON-NLS-2$
		garTextField.setBounds(121, 188, 142, 22);
		contentPane.add(garTextField);
		garTextField.setColumns(10);
		
		baTextField = new JTextField();
		baTextField.setText(""); //$NON-NLS-1$ //$NON-NLS-2$
		baTextField.setBounds(415, 172, 142, 22);
		contentPane.add(baTextField);
		baTextField.setColumns(10);
		
		JLabel label_1 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Kitchens"));
		label_1.setBounds(320, 114, 102, 16);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("DinningRooms"));
		label_2.setBounds(22, 156, 102, 16);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Bathrooms"));
		label_3.setBounds(320, 177, 102, 16);
		contentPane.add(label_3);
		
		cocTextField = new JTextField();
		cocTextField.setText(""); //$NON-NLS-1$ //$NON-NLS-2$
		cocTextField.setBounds(415, 108, 142, 22);
		contentPane.add(cocTextField);
		cocTextField.setColumns(10);
		
		comTextField = new JTextField();
		comTextField.setText(""); //$NON-NLS-1$ //$NON-NLS-2$
		comTextField.setBounds(121, 153, 142, 22);
		contentPane.add(comTextField);
		comTextField.setColumns(10);
	}

	protected void setDefaultImage() {
		ImageIcon def = new ImageIcon("img/default.png");
		def.setImage(def.getImage().getScaledInstance(lbl_img.getWidth(), lbl_img.getHeight(), Image.SCALE_SMOOTH));
		lbl_img.setIcon(def);
		
	}

	protected void copyImageOnProject(String imageName) {
		
		String extension =current_image.getName().substring(current_image.getName().lastIndexOf("."),current_image.getName().length());
		String output= "img/"+imageName+".png";
		File o =new File(output);
		try {
			Files.copy(current_image.toPath(), o.toPath());
		} catch (IOException e) {
			showDialog("Error al copiar la imagen al sistema");
			e.printStackTrace();
		}
	
	}

	private void setBusinessLogic(ApplicationFacadeInterfaceWS bl) {
		businessLogic = bl;
	}

	private void showDialog(String msg) {
		JOptionPane.showMessageDialog(this, msg);

	}
	
	protected File GetImageFromChooser() {
		
		final FileNameExtensionFilter filter = new FileNameExtensionFilter("Imágenes", "jpg","png","jpeg");
		File f;
		final JFileChooser FileChooser = new JFileChooser();
		FileChooser.setFileFilter(filter);
		int returnState = FileChooser.showOpenDialog(this);
		if (returnState == JFileChooser.APPROVE_OPTION) {
			return new File(FileChooser.getSelectedFile().getPath());
		} else
			return null;

	}
}
