package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

/**
 * @author Software Engineering teachers
 */
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import businessLogic.ApplicationFacadeInterfaceWS;
import domain.RuralHouse;

public class MainGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;
	private JButton boton1 = null;
	private JButton boton2 = null;
	private JButton boton3 = null;
	private static ApplicationFacadeInterfaceWS businessLogic;

	private static ApplicationFacadeInterfaceWS appFacadeInterface;

	public static ApplicationFacadeInterfaceWS getBusinessLogic() {
		return appFacadeInterface;
	}

	public static void setBussinessLogic(ApplicationFacadeInterfaceWS afi) {
		appFacadeInterface = afi;
	}

	protected JLabel lblNewLabel;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	private JRadioButton rdbtnNewRadioButton_2;
	private JPanel panel;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * This is the default constructor
	 */
	public MainGUI() {
		super();

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				ApplicationFacadeInterfaceWS facade = MainGUI.getBusinessLogic();
				try {
					// if (ConfigXML.getInstance().isBusinessLogicLocal())
					// facade.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					System.out.println(
							"Error: " + e1.toString() + " , probably problems with Business Logic or Database");
				}
				System.exit(1);
			}
		});

		initialize();
		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		// this.setSize(271, 295);
		this.setSize(495, 392);
		this.setContentPane(getJContentPane());
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getLblNewLabel());
			jContentPane.add(getBoton3());
			jContentPane.add(getBoton2());
			jContentPane.add(getPanel());

			JButton botonOfertas = new JButton();
			botonOfertas.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.botonOfertas.text_1")); //$NON-NLS-1$ //$NON-NLS-2$
			botonOfertas.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					Vector<RuralHouse> houses = getBusinessLogic().getAllRuralHouses();
					if (houses.isEmpty()) {
						showDialog("No hay casas rurales");

					} else {
						AllHouses ah = new AllHouses(getBusinessLogic(), houses);
						ah.setVisible(true);
					}
				}
			});

			botonOfertas.setBounds(0, 191, 479, 65);
			jContentPane.add(botonOfertas);
		}
		return jContentPane;
	}

	/**
	 * This method initializes boton2
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBoton2() {
		if (boton2 == null) {
			boton2 = new JButton();
			boton2.setBounds(0, 130, 479, 65);
			boton2.setText(ResourceBundle.getBundle("Etiquetas").getString("Register"));
			boton2.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					RegisterGUI reg = new RegisterGUI(getBusinessLogic());
					reg.setVisible(true);
					close();
				}
			});
		}
		return boton2;
	}

	/**
	 * This method initializes boton3
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBoton3() {
		if (boton3 == null) {
			boton3 = new JButton();
			boton3.setBounds(0, 68, 479, 65);
			boton3.setText(ResourceBundle.getBundle("Etiquetas").getString("Login"));
			boton3.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					LoginGUI log = new LoginGUI(getBusinessLogic());
					log.setVisible(true);
					close();
				}
			});
		}
		return boton3;
	}

	protected void close() {
		this.setVisible(false);

	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SelectOption"));
			lblNewLabel.setBounds(0, 0, 479, 65);
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblNewLabel.setForeground(Color.BLACK);
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblNewLabel;
	}

	private JRadioButton getRdbtnNewRadioButton() {
		if (rdbtnNewRadioButton == null) {
			rdbtnNewRadioButton = new JRadioButton("English");
			rdbtnNewRadioButton.setSelected(true);
			rdbtnNewRadioButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Locale.setDefault(new Locale("en"));
					System.out.println("Locale: " + Locale.getDefault());
					redibujar();
				}
			});
			buttonGroup.add(rdbtnNewRadioButton);
		}
		return rdbtnNewRadioButton;
	}

	private JRadioButton getRdbtnNewRadioButton_1() {
		if (rdbtnNewRadioButton_1 == null) {
			rdbtnNewRadioButton_1 = new JRadioButton("Euskara");
			rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					Locale.setDefault(new Locale("eus"));
					System.out.println("Locale: " + Locale.getDefault());
					redibujar();
				}
			});
			buttonGroup.add(rdbtnNewRadioButton_1);
		}
		return rdbtnNewRadioButton_1;
	}

	private JRadioButton getRdbtnNewRadioButton_2() {
		if (rdbtnNewRadioButton_2 == null) {
			rdbtnNewRadioButton_2 = new JRadioButton("Castellano");
			buttonGroup.add(rdbtnNewRadioButton_2);
			rdbtnNewRadioButton_2.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Locale.setDefault(new Locale("es"));
					System.out.println("Locale: " + Locale.getDefault());
					redibujar();
				}
			});
		}
		return rdbtnNewRadioButton_2;
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBounds(0, 253, 479, 100);
			panel.add(getRdbtnNewRadioButton_1());
			panel.add(getRdbtnNewRadioButton_2());
			panel.add(getRdbtnNewRadioButton());
		}
		return panel;
	}

	private void redibujar() {
		lblNewLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectOption"));
		boton3.setText(ResourceBundle.getBundle("Etiquetas").getString("Login"));
		boton2.setText(ResourceBundle.getBundle("Etiquetas").getString("Register"));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
	}

	private void showDialog(String msg) {
		JOptionPane.showMessageDialog(this, msg);

	}
} // @jve:decl-index=0:visual-constraint="0,0"
