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

public class AddRuralHouseGUI extends JFrame {

	private JPanel contentPane;
	private JTextField cityTextField;
	private JTextField descriptionTextField;
	private JLabel lbl_img;
	private ApplicationFacadeInterfaceWS businessLogic;
	private File current_image=new File("img/default.png");

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
		
		setBounds(100, 100, 619, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("AddRuralHouse"));
		lblNewLabel.setBounds(144, 25, 142, 16);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("City"));
		lblNewLabel_1.setBounds(51, 92, 56, 16);
		contentPane.add(lblNewLabel_1);

		cityTextField = new JTextField();
		cityTextField.setBounds(211, 89, 142, 22);
		cityTextField.setText("");
		contentPane.add(cityTextField);
		cityTextField.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Description"));
		lblNewLabel_2.setBounds(51, 120, 81, 16);
		contentPane.add(lblNewLabel_2);

		descriptionTextField = new JTextField();
		descriptionTextField.setBounds(211, 122, 142, 22);
		descriptionTextField.setText("");
		contentPane.add(descriptionTextField);
		descriptionTextField.setColumns(10);

		JButton btnAddRuralHouse = new JButton(ResourceBundle.getBundle("Etiquetas").getString("AddRuralHouse"));
		btnAddRuralHouse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				RuralHouse rh =businessLogic.addHouse(descriptionTextField.getText(), cityTextField.getText(),
						OwnOptions.currentOwner);
				showDialog("Casa rural aï¿½adida correctamente");
				cityTextField.setText("");
				descriptionTextField.setText("");
				copyImageOnProject(String.valueOf(rh.getHouseNumber()));
				setDefaultImage();

			}
		});
		btnAddRuralHouse.setBounds(139, 185, 155, 36);
		contentPane.add(btnAddRuralHouse);
		
		 lbl_img = new JLabel("");
		lbl_img.setBounds(415, 63, 152, 130);
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
		btnSeleccionarImagen.setBounds(425, 204, 142, 23);
		contentPane.add(btnSeleccionarImagen);
	}

	protected void setDefaultImage() {
		ImageIcon def = new ImageIcon("img/default.png");
		def.setImage(def.getImage().getScaledInstance(lbl_img.getWidth(), lbl_img.getHeight(), Image.SCALE_SMOOTH));
		lbl_img.setIcon(def);
		
	}

	protected void copyImageOnProject(String imageName) {
		
		String extension =current_image.getName().substring(current_image.getName().lastIndexOf("."),current_image.getName().length());
		String output= "img/"+imageName+extension;
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
