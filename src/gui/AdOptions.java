package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.ApplicationFacadeInterfaceWS;
import domain.Admin;
import domain.ClientReport;
import domain.HouseReport;
import domain.OwnerReport;

public class AdOptions extends JFrame {

	private JPanel contentPane;
	private ApplicationFacadeInterfaceWS businessLogic;
	public static Admin currentAdmin;
	private final Action action = new SwingAction();

	public AdOptions(ApplicationFacadeInterfaceWS bl, Admin current) {

		setBusinessLogic(bl);
		currentAdmin = current;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnReports = new JButton("View Reports");
		btnReports.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Vector<ClientReport> cliRep = businessLogic.getClientReports();
				Vector<HouseReport> houseRep = businessLogic.getHouseReports();
				Vector<OwnerReport> ownRep = businessLogic.getOwnerReports();
				ViewReportsGUI vr = new ViewReportsGUI(cliRep, houseRep, ownRep);
				vr.setVisible(true);
			}
		});
		btnReports.setBounds(120, 25, 192, 39);
		contentPane.add(btnReports);

		JLabel lblEstsLogeadoComo = new JLabel(
				ResourceBundle.getBundle("Etiquetas").getString("CliOptions.lblEstsLogeadoComo.text")); //$NON-NLS-1$ //$NON-NLS-2$
		lblEstsLogeadoComo.setBounds(10, 236, 174, 14);
		contentPane.add(lblEstsLogeadoComo);
		lblEstsLogeadoComo.setText("Estás logeado como: " + currentAdmin.getUsername());
	}

	private void setBusinessLogic(ApplicationFacadeInterfaceWS bl) {
		businessLogic = bl;
	}

	private void closeAdOp() {
		this.setVisible(false);

	}

	private void showDialog(String msg) {
		JOptionPane.showMessageDialog(this, msg);

	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
		}
	}
}
